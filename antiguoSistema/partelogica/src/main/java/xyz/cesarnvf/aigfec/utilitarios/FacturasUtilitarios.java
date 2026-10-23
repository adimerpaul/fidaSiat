package xyz.cesarnvf.aigfec.utilitarios;

import java.io.File;
import java.io.FileInputStream;
import java.io.StringWriter;
import java.nio.file.Files;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;

import xyz.cesarnvf.aigfec.excepcion.ProcesoExcepcion;
import xyz.cesarnvf.aigfec.modelo.Cufds;
import xyz.cesarnvf.aigfec.modelo.auxi.DatosCUF;
import xyz.cesarnvf.aigfec.modelo.xml.FacturasXML;

@Component
public class FacturasUtilitarios {

    @Value("${ruta.archivos}")
    private String ruta;

    @Autowired
    public GZIPUtilitarios gzipUtilitarios;

    public String obtenerCUF(FacturasXML facturaxml, Cufds cufd) {
        DatosCUF datosFacturaCUF = new DatosCUF();
        datosFacturaCUF.setNitEmisor(facturaxml.getCabecera().getNitEmisor());
        datosFacturaCUF.setFechaHora(Utilitarios.xMLGregorianCalendarToLocalDateTime(facturaxml.getCabecera().getFechaEmision()));
        datosFacturaCUF.setSucursal(facturaxml.getCabecera().getCodigoSucursal());
        datosFacturaCUF.setModalidad(1);
        datosFacturaCUF.setTipoEmision(1);
        datosFacturaCUF.setTipoFacturaDocumentoAjuste(1);
        datosFacturaCUF.setTipoDocumentoSector(facturaxml.getCabecera().getCodigoDocumentoSector());
        datosFacturaCUF.setNumeroFactura(facturaxml.getCabecera().getNumeroFactura());
        datosFacturaCUF.setPuntoVenta(facturaxml.getCabecera().getCodigoPuntoVenta());
        return Utilitarios.obtenerCUF(datosFacturaCUF, cufd.getCufdcontrol());
    }

    public byte[] obtenerArchivo(FacturasXML factura) throws Exception {
        byte[] xml = this.getXmlBytes(factura);
        byte[] xmlFirmado = this.firmarArchivo(xml);
        //XmlObject xmlFacturaObj = XmlObject.Factory.parse(new String(xmlFirmado));
        //this.validarContraXSD(xmlFacturaObj);
        byte[] xmlComprimidoZip = this.comprimirXMLFactura(xmlFirmado, factura.getCabecera().getCuf());
        return xmlComprimidoZip;
    }

    public File obtenerArchivoMasivo(FacturasXML factura) throws Exception {
        byte[] xml = this.getXmlBytes(factura);
        byte[] xmlFirmado = this.firmarArchivo(xml);
        File archivo = this.comprimirXMLFacturaMasivo(xmlFirmado, factura.getCabecera().getCuf());
        return archivo;
    }

    private byte[] getXmlBytes(FacturasXML facturaObject) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(FacturasXML.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        StringWriter writer = new StringWriter();
        marshaller.marshal(facturaObject, writer);
        return writer.toString().getBytes();
    }

    private byte[] firmarArchivo(byte[] xmlBytes) throws Exception {
        /* PrivateKey privateKey = XMLUtilitarios.getPrivateKey(ruta + "/certificados/sfida.pem");
        X509Certificate cert =  XMLUtilitarios.getX509Certificate(ruta + "/certificados/sfida.crt");
        return XMLUtilitarios.firmarDsig(xmlBytes, privateKey, cert); */

        String rutaAlmacen = ruta + "/certificados/sfida.p12";
        String clave = "2310883";

        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        try (FileInputStream fis = new FileInputStream(rutaAlmacen)) {
            keyStore.load(fis, clave.toCharArray());
        }

        String alias = keyStore.aliases().nextElement();
        PrivateKey privateKey = (PrivateKey) keyStore.getKey(alias, clave.toCharArray());
        X509Certificate cert = (X509Certificate) keyStore.getCertificate(alias);

        return XMLUtilitarios.firmarDsig(xmlBytes, privateKey, cert);
    }

    /* private void validarContraXSD(XmlObject xmlFacturaObj) {
        String schemaFile = ruta + "/modelos/facturaElectronicaCompraVenta.xsd";
        if(!XMLUtilitarios.validate(schemaFile, xmlFacturaObj)) {
            throw new ProcesoExcepcion("El XML no cumple las especificaciones de impuestos.");
        }
    } */

    private byte[] comprimirXMLFactura(byte[] xmlFacturaFirmada, String cuf) throws Exception {
        File tempFacturaXml = this.escribirArchivo(xmlFacturaFirmada, cuf);
        File archivoComprimido = this.obtenerArchivoComprimido(tempFacturaXml);
        byte[] comprimidoByte = this.compresionArchivo(archivoComprimido);
        return comprimidoByte;
    }

    private File comprimirXMLFacturaMasivo(byte[] xmlFacturaFirmada, String cuf) throws Exception {
        File tempFacturaXml = this.escribirArchivoMasico(xmlFacturaFirmada, cuf);
        return tempFacturaXml;
    }

    private File escribirArchivo(byte[] xmlFacturaFirmada, String cuf) throws Exception {
        String fullPath = ruta + "/facturas/" + cuf + ".xml";
        File tempFacturaXml = new File(fullPath);
        Document sourceDoc = XMLUtilitarios.leerXML(xmlFacturaFirmada);
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        Result output = new StreamResult(new File(tempFacturaXml.getPath()));
        Source input = new DOMSource(sourceDoc);
        transformer.transform(input, output);
        return tempFacturaXml;
    }

    private File escribirArchivoMasico(byte[] xmlFacturaFirmada, String cuf) throws Exception {
        String fullPath = ruta + "/envios/" + cuf + ".xml";
        File tempFacturaXml = new File(fullPath);
        Document sourceDoc = XMLUtilitarios.leerXML(xmlFacturaFirmada);
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        Result output = new StreamResult(new File(tempFacturaXml.getPath()));
        Source input = new DOMSource(sourceDoc);
        transformer.transform(input, output);
        return tempFacturaXml;
    }

    private File obtenerArchivoComprimido(File tempFacturaXml) {
        if (!gzipUtilitarios.comprimirArchivo(tempFacturaXml)) {
            throw new ProcesoExcepcion("No se pudo comprimir la factura");
        }

        File archivoComprimido = new File(tempFacturaXml.getPath() + ".zip");
        return archivoComprimido;
    }

    private byte[] compresionArchivo(File archivoComprimido) throws Exception {
        byte[] comprimidoByte = Files.readAllBytes(archivoComprimido.toPath());
        return comprimidoByte;
    }

}
