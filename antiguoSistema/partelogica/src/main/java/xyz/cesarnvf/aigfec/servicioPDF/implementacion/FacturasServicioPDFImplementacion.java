package xyz.cesarnvf.aigfec.servicioPDF.implementacion;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.itextpdf.barcodes.BarcodeQRCode;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.properties.VerticalAlignment;

import xyz.cesarnvf.aigfec.modelo.Clientes;
import xyz.cesarnvf.aigfec.modelo.Detalles;
import xyz.cesarnvf.aigfec.modelo.Empresas;
import xyz.cesarnvf.aigfec.modelo.Facturas;
import xyz.cesarnvf.aigfec.modelo.Productos;
import xyz.cesarnvf.aigfec.modelo.Puntosventas;
import xyz.cesarnvf.aigfec.modelo.Sucursales;
import xyz.cesarnvf.aigfec.modelo.Unidadesmedidas;
import xyz.cesarnvf.aigfec.modelo.Usuarios;
import xyz.cesarnvf.aigfec.repositorio.ClientesRepositorio;
import xyz.cesarnvf.aigfec.repositorio.DetallesRepositorio;
import xyz.cesarnvf.aigfec.repositorio.DocumentosidentidadessinRepositorio;
import xyz.cesarnvf.aigfec.repositorio.EmpresasRepositorio;
import xyz.cesarnvf.aigfec.repositorio.FacturasRepositorio;
import xyz.cesarnvf.aigfec.repositorio.MetodospagossinRepositorio;
import xyz.cesarnvf.aigfec.repositorio.ProductosRepositorio;
import xyz.cesarnvf.aigfec.repositorio.ProductossinRepositorio;
import xyz.cesarnvf.aigfec.repositorio.PuntosventasRepositorio;
import xyz.cesarnvf.aigfec.repositorio.SucursalesRepositorio;
import xyz.cesarnvf.aigfec.repositorio.UnidadesmedidasRepositorio;
import xyz.cesarnvf.aigfec.repositorio.UsuariosRepositorio;
import xyz.cesarnvf.aigfec.servicioPDF.FacturasServicioPDF;
import xyz.cesarnvf.aigfec.utilitarios.NumerosLiteral;

@Service
public class FacturasServicioPDFImplementacion implements FacturasServicioPDF {

    @Autowired
    FacturasRepositorio facturasRepositorio;

    @Autowired
    DetallesRepositorio detallesRepositorio;

    @Autowired
    UsuariosRepositorio usuariosRepositorio;

    @Autowired
    ClientesRepositorio clientesRepositorio;

    @Autowired
    ProductosRepositorio productosRepositorio;

    @Autowired
    UnidadesmedidasRepositorio unidadesmedidasRepositorio;

    @Autowired
    DocumentosidentidadessinRepositorio documentosidentidadessinRepositorio;

    @Autowired
    MetodospagossinRepositorio metodospagossinRepositorio;

    @Autowired
    PuntosventasRepositorio puntosventasRepositorio;

    @Autowired
    SucursalesRepositorio sucursalesRepositorio;

    @Autowired
    EmpresasRepositorio empresasRepositorio;

    @Autowired
    ProductossinRepositorio productossinRepositorio;

    @Value("${ruta.archivos}")
    private String ruta;

    @Override
    public byte[] generaFacturaPDF(Long idfactura) {

        Facturas factura = facturasRepositorio.obtenerPorId(idfactura);

        if (factura.getCodigo()==908 || factura.getCodigo()==9081 ) {

            DateTimeFormatter fechaFormat = DateTimeFormatter.ofPattern("dd'/'MM'/'yyyy HH:mm a");
            DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
            DecimalFormat decimalFormatOpcional = new DecimalFormat("#0.00");
            DecimalFormat decimalFormatEntero = new DecimalFormat("00000");

            ByteArrayOutputStream salida = new ByteArrayOutputStream();
            try {

                Authentication autentificado = SecurityContextHolder.getContext().getAuthentication();
                Usuarios usuario = usuariosRepositorio.obtenerPorId(Long.parseLong(autentificado.getName()));
                Long idpuntoventa = puntosventasRepositorio.obtenerPuntoventaAsignado(usuario.getIdusuario());
                Puntosventas puntoventa = puntosventasRepositorio.obtenerPorId(idpuntoventa);
                Sucursales sucursal = sucursalesRepositorio.obtenerPorId(puntoventa.getIdsucursal());
                Empresas empresa = empresasRepositorio.obtenerPorId(sucursal.getIdempresa());

                List<Detalles> detalles = detallesRepositorio.obtenerTodos(idfactura);
                Clientes cliente = clientesRepositorio.obtenerPorId(factura.getIdcliente());

                float anchoMilimetros = 76f;

                float anchoPuntos = anchoMilimetros * 2.83465f;

                float alturaRollo = 790;

                Integer contador = detalles.size();

                switch (contador) {
                    case 1: alturaRollo = 800; break;
                    case 2: alturaRollo = 850; break;
                    case 3: alturaRollo = 930; break;
                    case 4: alturaRollo = 980; break;
                    case 5: alturaRollo = 1040; break;
                    case 6: alturaRollo = 1100; break;
                    case 7: alturaRollo = 1160; break;
                    case 8: alturaRollo = 1230; break;
                    case 9: alturaRollo = 1280; break;
                    case 10: alturaRollo = 1340; break;
                    case 11: alturaRollo = 1400; break;
                    case 12: alturaRollo = 1460; break;
                    case 13: alturaRollo = 1530; break;
                    case 14: alturaRollo = 1610; break;
                    case 15: alturaRollo = 1660; break;
                    default:
                        break;
                }

                PdfWriter escribir = new PdfWriter(salida);
                PdfDocument pdf = new PdfDocument(escribir);

                Document documento = new Document(pdf, new PageSize(anchoPuntos, alturaRollo));
                documento.setMargins(25f, 10f, 15f, 10f);

                PdfFont font_normal = PdfFontFactory.createFont(StandardFonts.HELVETICA);
                PdfFont font_bold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);

                Paragraph p = new Paragraph("FACTURA").setFontSize(9).setFont(font_bold).setTextAlignment(TextAlignment.CENTER);
                documento.add(p);

                p = new Paragraph("(CON DERECHO A CRÉDITO FISCAL)").setFontSize(7).setFont(font_bold).setTextAlignment(TextAlignment.CENTER).setMarginTop(-5f);
                documento.add(p);

                p = new Paragraph("SFIDA INVERSIONES S.A.").setFontSize(8).setFont(font_normal).setTextAlignment(TextAlignment.CENTER).setMarginTop(-5f);
                documento.add(p);

                if (sucursal.getSucursal()==0) {
                    p = new Paragraph("Casa Matriz").setFontSize(8).setFont(font_normal).setTextAlignment(TextAlignment.CENTER).setMarginTop(-5f);
                } else {
                    p = new Paragraph("Sucursal No. "+sucursal.getSucursal().toString()).setFontSize(8).setFont(font_normal).setTextAlignment(TextAlignment.CENTER).setMarginTop(-5f);
                }

                documento.add(p);

                p = new Paragraph("No. Punto de Venta "+puntoventa.getPuntoventa().toString()).setFontSize(8).setFont(font_normal).setTextAlignment(TextAlignment.CENTER).setMarginTop(-5f);
                documento.add(p);

                p = new Paragraph(sucursal.getDireccion()+"\nTelf "+sucursal.getTelefono()+"\n"+sucursal.getMunicipio()).setFontSize(8).setFont(font_normal).setTextAlignment(TextAlignment.CENTER).setMarginTop(-5f);
                documento.add(p);

                p = new Paragraph("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -").setFontSize(8).setFont(font_normal).setTextAlignment(TextAlignment.CENTER).setMarginTop(-5f);
                documento.add(p);

                p = new Paragraph("NIT").setFontSize(8).setFont(font_bold).setTextAlignment(TextAlignment.CENTER).setMarginTop(-5f);
                documento.add(p);

                p = new Paragraph(empresa.getNit().toString()).setFontSize(8).setFont(font_normal).setTextAlignment(TextAlignment.CENTER).setMarginTop(-5f);
                documento.add(p);

                p = new Paragraph("FACTURA N°").setFontSize(8).setFont(font_bold).setTextAlignment(TextAlignment.CENTER).setMarginTop(-5f);
                documento.add(p);

                p = new Paragraph(factura.getNumerofactura().toString()).setFontSize(8).setFont(font_normal).setTextAlignment(TextAlignment.CENTER).setMarginTop(-5f);
                documento.add(p);

                p = new Paragraph("CÓD. AUTORIZACIÓN").setFontSize(8).setFont(font_bold).setTextAlignment(TextAlignment.CENTER).setMarginTop(-5f);
                documento.add(p);

                p = new Paragraph(factura.getCuf()).setFontSize(8).setFont(font_normal).setTextAlignment(TextAlignment.CENTER).setMarginTop(-5f);
                documento.add(p);

                p = new Paragraph("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -").setFontSize(8).setFont(font_normal).setTextAlignment(TextAlignment.CENTER).setMarginTop(-5f);
                documento.add(p);

                float[] anchocabecera = { 2f, 2f};
                Table tabla = new Table(anchocabecera);
                tabla.setWidth(UnitValue.createPercentValue(100));

                Cell celda;

                celda = new com.itextpdf.layout.element.Cell().setTextAlignment(TextAlignment.RIGHT).setFontSize(8).setFont(font_bold).setBorder(Border.NO_BORDER);
                celda.add(new Paragraph("NOMBRE/RAZÓN SOCIAL:"));
                tabla.addCell(celda);

                celda = new com.itextpdf.layout.element.Cell().setTextAlignment(TextAlignment.LEFT).setFontSize(8).setFont(font_normal).setBorder(Border.NO_BORDER);
                celda.add(new Paragraph(cliente.getCliente()));
                tabla.addCell(celda);

                celda = new com.itextpdf.layout.element.Cell().setTextAlignment(TextAlignment.RIGHT).setFontSize(8).setFont(font_bold).setBorder(Border.NO_BORDER);
                celda.add(new Paragraph("NIT/CI/CEX:"));
                tabla.addCell(celda);

                celda = new com.itextpdf.layout.element.Cell().setTextAlignment(TextAlignment.LEFT).setFontSize(8).setFont(font_normal).setBorder(Border.NO_BORDER);
                if (cliente.getComplemento().trim().equals("")){
                    celda.add(new Paragraph(cliente.getNumerodocumento()));
                } else {
                    celda.add(new Paragraph(cliente.getNumerodocumento()+"-"+cliente.getComplemento()));
                }
                tabla.addCell(celda);

                celda = new com.itextpdf.layout.element.Cell().setTextAlignment(TextAlignment.RIGHT).setFontSize(8).setFont(font_bold).setBorder(Border.NO_BORDER);
                celda.add(new Paragraph("COD. CLIENTE:"));
                tabla.addCell(celda);

                celda = new com.itextpdf.layout.element.Cell().setTextAlignment(TextAlignment.LEFT).setFontSize(8).setFont(font_normal).setBorder(Border.NO_BORDER);
                celda.add(new Paragraph(cliente.getIdcliente().toString()));
                tabla.addCell(celda);

                celda = new com.itextpdf.layout.element.Cell().setTextAlignment(TextAlignment.RIGHT).setFontSize(8).setFont(font_bold).setBorder(Border.NO_BORDER);
                celda.add(new Paragraph("FECHA DE EMISIÓN: "));
                tabla.addCell(celda);

                celda = new com.itextpdf.layout.element.Cell().setTextAlignment(TextAlignment.LEFT).setFontSize(8).setFont(font_normal).setBorder(Border.NO_BORDER);
                celda.add(new Paragraph(fechaFormat.format(factura.getFechaemision())));
                tabla.addCell(celda);

                documento.add(tabla);

                p = new Paragraph("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -").setFontSize(8).setFont(font_normal).setTextAlignment(TextAlignment.CENTER).setMarginTop(-5f);
                documento.add(p);

                p = new Paragraph("DETALLE").setFontSize(9).setFont(font_bold).setTextAlignment(TextAlignment.CENTER).setMarginTop(-5f);
                documento.add(p);

                float[] anchodetalle = { 2f, 2f, 2f, 2f};
                tabla = new Table(anchodetalle);
                tabla.setWidth(UnitValue.createPercentValue(100));

                BigDecimal sumasubtotal = BigDecimal.ZERO;

                for (Detalles detalle: detalles) {
                    Productos producto = productosRepositorio.obtenerPorId(detalle.getIdproducto());
                    Unidadesmedidas unidadmedida = unidadesmedidasRepositorio.obtenerPorId(producto.getIdunidadmedida());

                    celda = new com.itextpdf.layout.element.Cell(1, 4).setTextAlignment(TextAlignment.LEFT).setFontSize(8).setFont(font_bold).setBorder(Border.NO_BORDER);
                    celda.add(new Paragraph(decimalFormatEntero.format(producto.getIdproducto())+" - "+producto.getProducto()));
                    tabla.addCell(celda);

                    celda = new com.itextpdf.layout.element.Cell(1, 4).setTextAlignment(TextAlignment.LEFT).setFontSize(6).setFont(font_normal).setBorder(Border.NO_BORDER).setHeight(10f);
                    celda.add(new Paragraph("Unidad de Medida: "+unidadmedida.getUnidadmedida()));
                    tabla.addCell(celda);

                    celda = new com.itextpdf.layout.element.Cell(1,3).setTextAlignment(TextAlignment.CENTER).setFontSize(8).setFont(font_normal).setBorder(Border.NO_BORDER);
                    celda.add(new Paragraph(detalle.getCantidad().toString()+" X "+decimalFormat.format(detalle.getPreciounitario())+" - "+decimalFormat.format(detalle.getDescuento())));
                    tabla.addCell(celda);

                    BigDecimal subtotal = (detalle.getPreciounitario().multiply(detalle.getCantidad())).subtract(detalle.getDescuento());

                    celda = new com.itextpdf.layout.element.Cell().setTextAlignment(TextAlignment.RIGHT).setFontSize(8).setFont(font_normal).setBorder(Border.NO_BORDER);
                    celda.add(new Paragraph(decimalFormat.format(subtotal)));
                    tabla.addCell(celda);

                    sumasubtotal = sumasubtotal.add(subtotal);
                }

                documento.add(tabla);

                p = new Paragraph("..............................................................................").setFontSize(9).setFont(font_normal).setTextAlignment(TextAlignment.CENTER).setMarginTop(-5f);
                documento.add(p);

                float[] anchopie = { 2f, 2f};
                tabla = new Table(anchopie);
                tabla.setWidth(UnitValue.createPercentValue(100));

                celda = new com.itextpdf.layout.element.Cell().setTextAlignment(TextAlignment.RIGHT).setFontSize(8).setFont(font_bold).setBorder(Border.NO_BORDER);
                celda.add(new Paragraph("TOTAL Bs."));
                tabla.addCell(celda);

                celda = new com.itextpdf.layout.element.Cell().setTextAlignment(TextAlignment.RIGHT).setFontSize(8).setFont(font_normal).setBorder(Border.NO_BORDER);
                celda.add(new Paragraph(decimalFormat.format(sumasubtotal)));
                tabla.addCell(celda);

                celda = new com.itextpdf.layout.element.Cell().setTextAlignment(TextAlignment.RIGHT).setFontSize(8).setFont(font_bold).setBorder(Border.NO_BORDER);
                celda.add(new Paragraph("TOTAL A PAGAR Bs. "));
                tabla.addCell(celda);

                celda = new com.itextpdf.layout.element.Cell().setTextAlignment(TextAlignment.RIGHT).setFontSize(8).setFont(font_normal).setBorder(Border.NO_BORDER);
                celda.add(new Paragraph(decimalFormat.format(sumasubtotal)));
                tabla.addCell(celda);

                documento.add(tabla);

                NumerosLiteral dato= new NumerosLiteral();

                p = new Paragraph("Son: "+StringUtils.capitalize(dato.Convertir(decimalFormatOpcional.format(sumasubtotal), false))).setFontSize(8).setFont(font_normal).setTextAlignment(TextAlignment.LEFT);
                documento.add(p);

                p = new Paragraph("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -").setFontSize(8).setFont(font_normal).setTextAlignment(TextAlignment.CENTER).setMarginTop(-5f);
                documento.add(p);

                p = new Paragraph("ESTA FACTURA CONTRIBUYE AL DESARROLLO DEL PAÍS, EL USO ILÍCITO SERÁ SANCIONADO PENALMENTE DE ACUERDO A LEY").setFontSize(8).setFont(font_normal).setTextAlignment(TextAlignment.CENTER).setMarginTop(-5f);
                documento.add(p);

                p = new Paragraph(factura.getLeyenda()).setFontSize(8).setFont(font_normal).setTextAlignment(TextAlignment.CENTER);
                documento.add(p);

                if (factura.getCodigo()==9081) {
                    p = new Paragraph("“Este documento es la Representación Gráfica de un Documento Fiscal Digital emitido fuera de línea, verifique su envío con su proveedor o en la página web www.impuestos.gob.bo”").setFontSize(8).setFont(font_normal).setTextAlignment(TextAlignment.CENTER);
                } else {
                    p = new Paragraph("“Este documento es la Representación Gráfica de un Documento Fiscal Digital emitido en una modalidad de facturación en línea”").setFontSize(8).setFont(font_normal).setTextAlignment(TextAlignment.CENTER);
                }
                documento.add(p);

                String cadenaqrcode = "https://siat.impuestos.gob.bo/consulta/QR?nit="+empresa.getNit().toString()+"&cuf="+factura.getCuf()+"&numero="+factura.getNumerofactura().toString()+"&t=1";

                BarcodeQRCode qrcodedata = new BarcodeQRCode(cadenaqrcode);
                PdfFormXObject qrcodeobj = qrcodedata.createFormXObject(pdf);
                Image qrcode = new Image(qrcodeobj);

                qrcode.scaleAbsolute(113.38583f, 113.38583f);

                p = new Paragraph().setTextAlignment(TextAlignment.CENTER).setMarginTop(-5f);
                p.add(qrcode);
                documento.add(p);

                p = new Paragraph("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -").setFontSize(8).setFont(font_normal).setTextAlignment(TextAlignment.CENTER).setMarginTop(-5f);
                documento.add(p);

                p = new Paragraph("Vendedor: "+usuario.getUsuario()).setFontSize(8).setFont(font_normal).setTextAlignment(TextAlignment.RIGHT).setMarginTop(-5f);
                documento.add(p);

                float[] anchoentrega = { 2f, 2f};
                tabla = new Table(anchoentrega);
                tabla.setWidth(UnitValue.createPercentValue(100));

                for (Detalles detalle : detalles) {
                    Productos producto = productosRepositorio.obtenerPorId(detalle.getIdproducto());
                    celda = new com.itextpdf.layout.element.Cell().setTextAlignment(TextAlignment.RIGHT).setFontSize(8).setFont(font_bold).setBorder(Border.NO_BORDER).setMarginTop(-10f);
                    celda.add(new Paragraph(detalle.getCantidad().toString()));
                    tabla.addCell(celda);

                    celda = new com.itextpdf.layout.element.Cell().setTextAlignment(TextAlignment.LEFT).setFontSize(8).setFont(font_normal).setBorder(Border.NO_BORDER).setMarginTop(-10f);
                    celda.add(new Paragraph(producto.getProducto()));
                    tabla.addCell(celda);
                }

                documento.add(tabla);

                p = new Paragraph("Factura Nº: "+factura.getNumerofactura().toString()).setFontSize(8).setFont(font_normal).setTextAlignment(TextAlignment.LEFT).setMarginTop(-5f);
                documento.add(p);
                p = new Paragraph("NIT/CI: "+cliente.getNumerodocumento()).setFontSize(8).setFont(font_normal).setTextAlignment(TextAlignment.LEFT).setMarginTop(-5f);
                documento.add(p);
                p = new Paragraph("Nombre/Razon Social: "+cliente.getCliente()).setFontSize(8).setFont(font_normal).setTextAlignment(TextAlignment.LEFT).setMarginTop(-5f);
                documento.add(p);
                p = new Paragraph("Fecha: "+fechaFormat.format(factura.getFechaemision())).setFontSize(8).setFont(font_normal).setTextAlignment(TextAlignment.LEFT).setMarginTop(-5f);
                documento.add(p);
                p = new Paragraph("Total: "+decimalFormat.format(sumasubtotal)).setFontSize(8).setFont(font_normal).setTextAlignment(TextAlignment.LEFT).setMarginTop(-5f);
                documento.add(p);

                pdf.getDefaultPageSize().setHeight(alturaRollo);

                documento.close();
            } catch (Exception e) {
                System.out.println("Error: "+e.toString());
            }
            return salida.toByteArray();
        } else {
            return null;
        }

    }

    @Override
    public void guardaFacturaPDF(Long idfactura) {
        Facturas factura = facturasRepositorio.obtenerPorId(idfactura);

        if (factura.getCodigo()==908 || factura.getCodigo()==9081) {

            DateTimeFormatter fechaFormat = DateTimeFormatter.ofPattern("dd'/'MM'/'yyyy HH:mm a");
            DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
            DecimalFormat decimalFormatOpcional = new DecimalFormat("#0.00");
            DecimalFormat decimalFormatEntero = new DecimalFormat("00000");

            String nombrearchivo = ruta+"/facturaspdf/"+factura.getCuf()+".pdf";
            File destino = new File(nombrearchivo);

            try (FileOutputStream salida = new FileOutputStream(destino)){

                Authentication autentificado = SecurityContextHolder.getContext().getAuthentication();
                Usuarios usuario = usuariosRepositorio.obtenerPorId(Long.parseLong(autentificado.getName()));
                Long idpuntoventa = puntosventasRepositorio.obtenerPuntoventaAsignado(usuario.getIdusuario());
                Puntosventas puntoventa = puntosventasRepositorio.obtenerPorId(idpuntoventa);
                Sucursales sucursal = sucursalesRepositorio.obtenerPorId(puntoventa.getIdsucursal());
                Empresas empresa = empresasRepositorio.obtenerPorId(sucursal.getIdempresa());

                List<Detalles> detalles = detallesRepositorio.obtenerTodos(idfactura);
                Clientes cliente = clientesRepositorio.obtenerPorId(factura.getIdcliente());

                float anchoMilimetros = 76f;

                float anchoPuntos = anchoMilimetros * 2.83465f;

                float alturaRollo = 790;

                Integer contador = detalles.size();

                switch (contador) {
                    case 1: alturaRollo = 800; break;
                    case 2: alturaRollo = 850; break;
                    case 3: alturaRollo = 930; break;
                    case 4: alturaRollo = 980; break;
                    case 5: alturaRollo = 1040; break;
                    case 6: alturaRollo = 1100; break;
                    case 7: alturaRollo = 1160; break;
                    case 8: alturaRollo = 1230; break;
                    case 9: alturaRollo = 1280; break;
                    case 10: alturaRollo = 1340; break;
                    case 11: alturaRollo = 1400; break;
                    case 12: alturaRollo = 1460; break;
                    case 13: alturaRollo = 1530; break;
                    case 14: alturaRollo = 1610; break;
                    case 15: alturaRollo = 1660; break;
                    default:
                        break;
                }

                PdfWriter escribir = new PdfWriter(salida);
                PdfDocument pdf = new PdfDocument(escribir);

                Document documento = new Document(pdf, new PageSize(anchoPuntos, alturaRollo));
                documento.setMargins(25f, 10f, 25f, 10f);

                PdfFont font_normal = PdfFontFactory.createFont(StandardFonts.HELVETICA);
                PdfFont font_bold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);

                Paragraph p = new Paragraph("FACTURA").setFontSize(9).setFont(font_bold).setTextAlignment(TextAlignment.CENTER);
                documento.add(p);

                p = new Paragraph("(CON DERECHO A CRÉDITO FISCAL)").setFontSize(7).setFont(font_bold).setTextAlignment(TextAlignment.CENTER).setMarginTop(-5f);
                documento.add(p);

                p = new Paragraph("SFIDA INVERSIONES S.A.").setFontSize(8).setFont(font_normal).setTextAlignment(TextAlignment.CENTER).setMarginTop(-5f);
                documento.add(p);

                if (sucursal.getSucursal()==0) {
                    p = new Paragraph("Casa Matriz").setFontSize(8).setFont(font_normal).setTextAlignment(TextAlignment.CENTER).setMarginTop(-5f);
                } else {
                    p = new Paragraph("Sucursal No. "+sucursal.getSucursal().toString()).setFontSize(8).setFont(font_normal).setTextAlignment(TextAlignment.CENTER).setMarginTop(-5f);
                }

                documento.add(p);

                p = new Paragraph("No. Punto de Venta "+puntoventa.getPuntoventa().toString()).setFontSize(8).setFont(font_normal).setTextAlignment(TextAlignment.CENTER).setMarginTop(-5f);
                documento.add(p);

                p = new Paragraph(sucursal.getDireccion()+"\nTelf "+sucursal.getTelefono()+"\n"+sucursal.getMunicipio()).setFontSize(8).setFont(font_normal).setTextAlignment(TextAlignment.CENTER).setMarginTop(-5f);
                documento.add(p);

                p = new Paragraph("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -").setFontSize(8).setFont(font_normal).setTextAlignment(TextAlignment.CENTER).setMarginTop(-5f);
                documento.add(p);

                p = new Paragraph("NIT").setFontSize(8).setFont(font_bold).setTextAlignment(TextAlignment.CENTER).setMarginTop(-5f);
                documento.add(p);

                p = new Paragraph(empresa.getNit().toString()).setFontSize(8).setFont(font_normal).setTextAlignment(TextAlignment.CENTER).setMarginTop(-5f);
                documento.add(p);

                p = new Paragraph("FACTURA N°").setFontSize(8).setFont(font_bold).setTextAlignment(TextAlignment.CENTER).setMarginTop(-5f);
                documento.add(p);

                p = new Paragraph(factura.getNumerofactura().toString()).setFontSize(8).setFont(font_normal).setTextAlignment(TextAlignment.CENTER).setMarginTop(-5f);
                documento.add(p);

                p = new Paragraph("CÓD. AUTORIZACIÓN").setFontSize(8).setFont(font_bold).setTextAlignment(TextAlignment.CENTER).setMarginTop(-5f);
                documento.add(p);

                p = new Paragraph(factura.getCuf()).setFontSize(8).setFont(font_normal).setTextAlignment(TextAlignment.CENTER).setMarginTop(-5f);
                documento.add(p);

                p = new Paragraph("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -").setFontSize(8).setFont(font_normal).setTextAlignment(TextAlignment.CENTER).setMarginTop(-5f);
                documento.add(p);

                float[] anchocabecera = { 2f, 2f};
                Table tabla = new Table(anchocabecera);
                tabla.setWidth(UnitValue.createPercentValue(100));

                Cell celda;

                celda = new com.itextpdf.layout.element.Cell().setTextAlignment(TextAlignment.RIGHT).setFontSize(8).setFont(font_bold).setBorder(Border.NO_BORDER);
                celda.add(new Paragraph("NOMBRE/RAZÓN SOCIAL:"));
                tabla.addCell(celda);

                celda = new com.itextpdf.layout.element.Cell().setTextAlignment(TextAlignment.LEFT).setFontSize(8).setFont(font_normal).setBorder(Border.NO_BORDER);
                celda.add(new Paragraph(cliente.getCliente()));
                tabla.addCell(celda);

                celda = new com.itextpdf.layout.element.Cell().setTextAlignment(TextAlignment.RIGHT).setFontSize(8).setFont(font_bold).setBorder(Border.NO_BORDER);
                celda.add(new Paragraph("NIT/CI/CEX:"));
                tabla.addCell(celda);

                celda = new com.itextpdf.layout.element.Cell().setTextAlignment(TextAlignment.LEFT).setFontSize(8).setFont(font_normal).setBorder(Border.NO_BORDER);
                if (cliente.getComplemento().trim().equals("")){
                    celda.add(new Paragraph(cliente.getNumerodocumento()));
                } else {
                    celda.add(new Paragraph(cliente.getNumerodocumento()+"-"+cliente.getComplemento()));
                }
                tabla.addCell(celda);

                celda = new com.itextpdf.layout.element.Cell().setTextAlignment(TextAlignment.RIGHT).setFontSize(8).setFont(font_bold).setBorder(Border.NO_BORDER);
                celda.add(new Paragraph("COD. CLIENTE:"));
                tabla.addCell(celda);

                celda = new com.itextpdf.layout.element.Cell().setTextAlignment(TextAlignment.LEFT).setFontSize(8).setFont(font_normal).setBorder(Border.NO_BORDER);
                celda.add(new Paragraph(cliente.getIdcliente().toString()));
                tabla.addCell(celda);

                celda = new com.itextpdf.layout.element.Cell().setTextAlignment(TextAlignment.RIGHT).setFontSize(8).setFont(font_bold).setBorder(Border.NO_BORDER);
                celda.add(new Paragraph("FECHA DE EMISIÓN: "));
                tabla.addCell(celda);

                celda = new com.itextpdf.layout.element.Cell().setTextAlignment(TextAlignment.LEFT).setFontSize(8).setFont(font_normal).setBorder(Border.NO_BORDER);
                celda.add(new Paragraph(fechaFormat.format(factura.getFechaemision())));
                tabla.addCell(celda);

                documento.add(tabla);

                p = new Paragraph("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -").setFontSize(8).setFont(font_normal).setTextAlignment(TextAlignment.CENTER).setMarginTop(-5f);
                documento.add(p);

                p = new Paragraph("DETALLE").setFontSize(9).setFont(font_bold).setTextAlignment(TextAlignment.CENTER).setMarginTop(-5f);
                documento.add(p);

                float[] anchodetalle = { 2f, 2f, 2f, 2f};
                tabla = new Table(anchodetalle);
                tabla.setWidth(UnitValue.createPercentValue(100));

                BigDecimal sumasubtotal = BigDecimal.ZERO;

                for (Detalles detalle: detalles) {
                    Productos producto = productosRepositorio.obtenerPorId(detalle.getIdproducto());
                    Unidadesmedidas unidadmedida = unidadesmedidasRepositorio.obtenerPorId(producto.getIdunidadmedida());

                    celda = new com.itextpdf.layout.element.Cell(1, 4).setTextAlignment(TextAlignment.LEFT).setFontSize(8).setFont(font_bold).setBorder(Border.NO_BORDER);
                    celda.add(new Paragraph(decimalFormatEntero.format(producto.getIdproducto())+" - "+producto.getProducto()));
                    tabla.addCell(celda);

                    celda = new com.itextpdf.layout.element.Cell(1, 4).setTextAlignment(TextAlignment.LEFT).setFontSize(6).setFont(font_normal).setBorder(Border.NO_BORDER).setHeight(10f);
                    celda.add(new Paragraph("Unidad de Medida: "+unidadmedida.getUnidadmedida()));
                    tabla.addCell(celda);

                    celda = new com.itextpdf.layout.element.Cell(1,3).setTextAlignment(TextAlignment.CENTER).setFontSize(8).setFont(font_normal).setBorder(Border.NO_BORDER);
                    celda.add(new Paragraph(detalle.getCantidad().toString()+" X "+decimalFormat.format(detalle.getPreciounitario())+" - "+decimalFormat.format(detalle.getDescuento())));
                    tabla.addCell(celda);

                    BigDecimal subtotal = (detalle.getPreciounitario().multiply(detalle.getCantidad())).subtract(detalle.getDescuento());

                    celda = new com.itextpdf.layout.element.Cell().setTextAlignment(TextAlignment.RIGHT).setFontSize(8).setFont(font_normal).setBorder(Border.NO_BORDER);
                    celda.add(new Paragraph(decimalFormat.format(subtotal)));
                    tabla.addCell(celda);

                    sumasubtotal = sumasubtotal.add(subtotal);
                }

                documento.add(tabla);

                p = new Paragraph("..............................................................................").setFontSize(9).setFont(font_normal).setTextAlignment(TextAlignment.CENTER).setMarginTop(-5f);
                documento.add(p);

                float[] anchopie = { 2f, 2f};
                tabla = new Table(anchopie);
                tabla.setWidth(UnitValue.createPercentValue(100));

                celda = new com.itextpdf.layout.element.Cell().setTextAlignment(TextAlignment.RIGHT).setFontSize(8).setFont(font_bold).setBorder(Border.NO_BORDER);
                celda.add(new Paragraph("TOTAL Bs."));
                tabla.addCell(celda);

                celda = new com.itextpdf.layout.element.Cell().setTextAlignment(TextAlignment.RIGHT).setFontSize(8).setFont(font_normal).setBorder(Border.NO_BORDER);
                celda.add(new Paragraph(decimalFormat.format(sumasubtotal)));
                tabla.addCell(celda);

                celda = new com.itextpdf.layout.element.Cell().setTextAlignment(TextAlignment.RIGHT).setFontSize(8).setFont(font_bold).setBorder(Border.NO_BORDER);
                celda.add(new Paragraph("TOTAL A PAGAR Bs. "));
                tabla.addCell(celda);

                celda = new com.itextpdf.layout.element.Cell().setTextAlignment(TextAlignment.RIGHT).setFontSize(8).setFont(font_normal).setBorder(Border.NO_BORDER);
                celda.add(new Paragraph(decimalFormat.format(sumasubtotal)));
                tabla.addCell(celda);

                documento.add(tabla);

                NumerosLiteral dato= new NumerosLiteral();

                p = new Paragraph("Son: "+StringUtils.capitalize(dato.Convertir(decimalFormatOpcional.format(sumasubtotal), false))).setFontSize(8).setFont(font_normal).setTextAlignment(TextAlignment.LEFT);
                documento.add(p);

                p = new Paragraph("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -").setFontSize(8).setFont(font_normal).setTextAlignment(TextAlignment.CENTER).setMarginTop(-5f);
                documento.add(p);

                p = new Paragraph("ESTA FACTURA CONTRIBUYE AL DESARROLLO DEL PAÍS, EL USO ILÍCITO SERÁ SANCIONADO PENALMENTE DE ACUERDO A LEY").setFontSize(8).setFont(font_normal).setTextAlignment(TextAlignment.CENTER).setMarginTop(-5f);
                documento.add(p);

                p = new Paragraph(factura.getLeyenda()).setFontSize(8).setFont(font_normal).setTextAlignment(TextAlignment.CENTER);
                documento.add(p);

                if (factura.getCodigo()==9081) {
                    p = new Paragraph("“Este documento es la Representación Gráfica de un Documento Fiscal Digital emitido fuera de línea, verifique su envío con su proveedor o en la página web www.impuestos.gob.bo”").setFontSize(8).setFont(font_normal).setTextAlignment(TextAlignment.CENTER);
                } else {
                    p = new Paragraph("“Este documento es la Representación Gráfica de un Documento Fiscal Digital emitido en una modalidad de facturación en línea”").setFontSize(8).setFont(font_normal).setTextAlignment(TextAlignment.CENTER);
                }
                documento.add(p);

                String cadenaqrcode = "https://siat.impuestos.gob.bo/consulta/QR?nit="+empresa.getNit().toString()+"&cuf="+factura.getCuf()+"&numero="+factura.getNumerofactura().toString()+"&t=1";

                BarcodeQRCode qrcodedata = new BarcodeQRCode(cadenaqrcode);
                PdfFormXObject qrcodeobj = qrcodedata.createFormXObject(pdf);
                Image qrcode = new Image(qrcodeobj);

                qrcode.scaleAbsolute(113.38583f, 113.38583f);

                p = new Paragraph().setTextAlignment(TextAlignment.CENTER).setMarginTop(-5f);
                p.add(qrcode);
                documento.add(p);

                p = new Paragraph("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -").setFontSize(8).setFont(font_normal).setTextAlignment(TextAlignment.CENTER).setMarginTop(-5f);
                documento.add(p);

                p = new Paragraph("Vendedor: "+usuario.getUsuario()).setFontSize(8).setFont(font_normal).setTextAlignment(TextAlignment.RIGHT).setMarginTop(-5f);
                documento.add(p);

                float[] anchoentrega = { 2f, 2f};
                tabla = new Table(anchoentrega);
                tabla.setWidth(UnitValue.createPercentValue(100));

                for (Detalles detalle : detalles) {
                    Productos producto = productosRepositorio.obtenerPorId(detalle.getIdproducto());
                    celda = new com.itextpdf.layout.element.Cell().setTextAlignment(TextAlignment.RIGHT).setFontSize(8).setFont(font_bold).setBorder(Border.NO_BORDER).setMarginTop(-10f);
                    celda.add(new Paragraph(detalle.getCantidad().toString()));
                    tabla.addCell(celda);

                    celda = new com.itextpdf.layout.element.Cell().setTextAlignment(TextAlignment.LEFT).setFontSize(8).setFont(font_normal).setBorder(Border.NO_BORDER).setMarginTop(-10f);
                    celda.add(new Paragraph(producto.getProducto()));
                    tabla.addCell(celda);
                }

                documento.add(tabla);

                p = new Paragraph("Factura Nº: "+factura.getNumerofactura().toString()).setFontSize(8).setFont(font_normal).setTextAlignment(TextAlignment.LEFT).setMarginTop(-5f);
                documento.add(p);
                p = new Paragraph("NIT/CI: "+cliente.getNumerodocumento()).setFontSize(8).setFont(font_normal).setTextAlignment(TextAlignment.LEFT).setMarginTop(-5f);
                documento.add(p);
                p = new Paragraph("Nombre/Razon Social: "+cliente.getCliente()).setFontSize(8).setFont(font_normal).setTextAlignment(TextAlignment.LEFT).setMarginTop(-5f);
                documento.add(p);
                p = new Paragraph("Fecha: "+fechaFormat.format(factura.getFechaemision())).setFontSize(8).setFont(font_normal).setTextAlignment(TextAlignment.LEFT).setMarginTop(-5f);
                documento.add(p);
                p = new Paragraph("Total: "+decimalFormat.format(sumasubtotal.subtract(factura.getDescuento()))).setFontSize(8).setFont(font_normal).setTextAlignment(TextAlignment.LEFT).setMarginTop(-5f);
                documento.add(p);

                pdf.getDefaultPageSize().setHeight(alturaRollo);

                documento.close();

                salida.flush();

            } catch (Exception e) {
                System.out.println("Error: "+e.toString());
            }
        }
    }

    @Override
    public byte[] generaFacturaCPDF(Long idfactura) {
        Facturas factura = facturasRepositorio.obtenerPorId(idfactura);

        if (factura.getCodigo()==908 || factura.getCodigo()==9081) {

            DateTimeFormatter fechaFormat = DateTimeFormatter.ofPattern("dd'/'MM'/'yyyy HH:mm a");
            DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
            DecimalFormat decimalFormatOpcional = new DecimalFormat("#0.00");
            DecimalFormat decimalFormatEntero = new DecimalFormat("00000");

            ByteArrayOutputStream salida = new ByteArrayOutputStream();
            try {

                Authentication autentificado = SecurityContextHolder.getContext().getAuthentication();
                Usuarios usuario = usuariosRepositorio.obtenerPorId(Long.parseLong(autentificado.getName()));
                Long idpuntoventa = puntosventasRepositorio.obtenerPuntoventaAsignado(usuario.getIdusuario());
                Puntosventas puntoventa = puntosventasRepositorio.obtenerPorId(idpuntoventa);
                Sucursales sucursal = sucursalesRepositorio.obtenerPorId(puntoventa.getIdsucursal());
                Empresas empresa = empresasRepositorio.obtenerPorId(sucursal.getIdempresa());

                List<Detalles> detalles = detallesRepositorio.obtenerTodos(idfactura);
                Clientes cliente = clientesRepositorio.obtenerPorId(factura.getIdcliente());

                PdfWriter escribir = new PdfWriter(salida);
                PdfDocument pdf = new PdfDocument(escribir);

                Document documento = new Document(pdf, PageSize.LETTER);
                documento.setMargins(35f, 25f, 25f, 35f);

                PdfFont font_normal = PdfFontFactory.createFont(StandardFonts.HELVETICA);
                PdfFont font_bold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
                Cell celda;
                float[] anchoentrega = { 25f, 25f, 25f, 25f};
                Table tabla = new Table(UnitValue.createPercentArray(anchoentrega));
                tabla.setWidth(UnitValue.createPercentValue(100));

                Paragraph p=null;

                if (sucursal.getSucursal()==0) {
                    p = new Paragraph("SFIDA INVERSIONES S.A.\nCasa Matriz\nNo. Punto de Venta "+puntoventa.getPuntoventa().toString()+sucursal.getDireccion()+"\nTelf "+sucursal.getTelefono()+"\n"+sucursal.getMunicipio()).setFontSize(8).setFont(font_normal).setTextAlignment(TextAlignment.CENTER).setMarginTop(-5f);
                } else {
                    p = new Paragraph("SFIDA INVERSIONES S.A.\nSucursal No. "+sucursal.getSucursal().toString()+"\nNo.Punto de Venta "+puntoventa.getPuntoventa().toString()+sucursal.getDireccion()+"\nTelf "+sucursal.getTelefono()+"\n"+sucursal.getMunicipio()).setFontSize(8).setFont(font_normal).setTextAlignment(TextAlignment.CENTER).setMarginTop(-5f);
                }

                celda = new com.itextpdf.layout.element.Cell().setTextAlignment(TextAlignment.CENTER).setFontSize(8).setFont(font_bold).setBorder(Border.NO_BORDER).setMarginTop(-10f).setMaxWidth(50);
                celda.add(p);
                tabla.addCell(celda);

                celda = new com.itextpdf.layout.element.Cell().setTextAlignment(TextAlignment.LEFT).setFontSize(8).setFont(font_normal).setBorder(Border.NO_BORDER).setMarginTop(-10f).setMaxWidth(50);
                celda.add(new Paragraph(""));
                tabla.addCell(celda);

                celda = new com.itextpdf.layout.element.Cell().setTextAlignment(TextAlignment.LEFT).setFontSize(8).setFont(font_normal).setBorder(Border.NO_BORDER).setMarginTop(-10f).setMaxWidth(50);
                celda.add(new Paragraph("NIT\nFACTURA N°\nCÓD. AUTORIZACIÓN"));
                tabla.addCell(celda);

                celda = new com.itextpdf.layout.element.Cell().setTextAlignment(TextAlignment.LEFT).setFontSize(8).setFont(font_normal).setBorder(Border.NO_BORDER).setMarginTop(-10f).setMaxWidth(50);
                celda.add(new Paragraph(empresa.getNit().toString()+"\n"+factura.getNumerofactura().toString()+"\n"+factura.getCuf()));
                tabla.addCell(celda);

                documento.add(tabla);

                p = new Paragraph("FACTURA").setFontSize(12).setFont(font_bold).setTextAlignment(TextAlignment.CENTER);
                documento.add(p);

                p = new Paragraph("(CON DERECHO A CRÉDITO FISCAL)").setFontSize(7).setFont(font_bold).setTextAlignment(TextAlignment.CENTER).setMarginTop(-5f);
                documento.add(p);

                p = new Paragraph("").setFontSize(8).setFont(font_normal).setTextAlignment(TextAlignment.CENTER).setMarginTop(-5f);
                documento.add(p);

                float[] anchocabecera = { 2f, 2f, 2f, 2f};
                tabla = new Table(anchocabecera);
                tabla.setWidth(UnitValue.createPercentValue(100));

                celda = new com.itextpdf.layout.element.Cell().setTextAlignment(TextAlignment.LEFT).setFontSize(8).setFont(font_bold).setBorder(Border.NO_BORDER);
                celda.add(new Paragraph("NOMBRE/RAZÓN SOCIAL:"));
                tabla.addCell(celda);

                celda = new com.itextpdf.layout.element.Cell().setTextAlignment(TextAlignment.LEFT).setFontSize(8).setFont(font_normal).setBorder(Border.NO_BORDER);
                celda.add(new Paragraph(cliente.getCliente()));
                tabla.addCell(celda);

                celda = new com.itextpdf.layout.element.Cell().setTextAlignment(TextAlignment.LEFT).setFontSize(8).setFont(font_bold).setBorder(Border.NO_BORDER);
                celda.add(new Paragraph("NIT/CI/CEX:"));
                tabla.addCell(celda);

                celda = new com.itextpdf.layout.element.Cell().setTextAlignment(TextAlignment.LEFT).setFontSize(8).setFont(font_normal).setBorder(Border.NO_BORDER);
                if (cliente.getComplemento().trim().equals("")){
                    celda.add(new Paragraph(cliente.getNumerodocumento()));
                } else {
                    celda.add(new Paragraph(cliente.getNumerodocumento()+"-"+cliente.getComplemento()));
                }
                tabla.addCell(celda);

                celda = new com.itextpdf.layout.element.Cell().setTextAlignment(TextAlignment.LEFT).setFontSize(8).setFont(font_bold).setBorder(Border.NO_BORDER);
                celda.add(new Paragraph("COD. CLIENTE:"));
                tabla.addCell(celda);

                celda = new com.itextpdf.layout.element.Cell().setTextAlignment(TextAlignment.LEFT).setFontSize(8).setFont(font_normal).setBorder(Border.NO_BORDER);
                celda.add(new Paragraph(cliente.getIdcliente().toString()));
                tabla.addCell(celda);

                celda = new com.itextpdf.layout.element.Cell().setTextAlignment(TextAlignment.LEFT).setFontSize(8).setFont(font_bold).setBorder(Border.NO_BORDER);
                celda.add(new Paragraph("FECHA DE EMISIÓN: "));
                tabla.addCell(celda);

                celda = new com.itextpdf.layout.element.Cell().setTextAlignment(TextAlignment.LEFT).setFontSize(8).setFont(font_normal).setBorder(Border.NO_BORDER);
                celda.add(new Paragraph(fechaFormat.format(factura.getFechaemision())));
                tabla.addCell(celda);

                documento.add(tabla);

                p = new Paragraph("\nDETALLE").setFontSize(12).setFont(font_bold).setTextAlignment(TextAlignment.CENTER).setMarginTop(-5f);
                documento.add(p);

                float[] anchodetalle = { 2f, 2f, 2f, 2f, 2f, 2f, 2f};
                tabla = new Table(anchodetalle);
                tabla.setWidth(UnitValue.createPercentValue(100));

                BigDecimal sumasubtotal = BigDecimal.ZERO;

                celda = new com.itextpdf.layout.element.Cell().setTextAlignment(TextAlignment.CENTER).setFontSize(6).setFont(font_bold).setMaxWidth(50);
                celda.add(new Paragraph("CÓDIGO PRODUCTO"));
                tabla.addCell(celda);

                celda = new com.itextpdf.layout.element.Cell().setTextAlignment(TextAlignment.CENTER).setFontSize(6).setFont(font_bold).setMaxWidth(50);
                celda.add(new Paragraph("CANTIDAD"));
                tabla.addCell(celda);

                celda = new com.itextpdf.layout.element.Cell().setTextAlignment(TextAlignment.CENTER).setFontSize(6).setFont(font_bold).setMaxWidth(50);
                celda.add(new Paragraph("UNIDAD DE MEDIDA"));
                tabla.addCell(celda);

                celda = new com.itextpdf.layout.element.Cell().setTextAlignment(TextAlignment.CENTER).setFontSize(6).setFont(font_bold).setMaxWidth(150);
                celda.add(new Paragraph("DESCRIPCIÓN"));
                tabla.addCell(celda);

                celda = new com.itextpdf.layout.element.Cell().setTextAlignment(TextAlignment.CENTER).setFontSize(6).setFont(font_bold).setMaxWidth(50);
                celda.add(new Paragraph("PRECIO UNITARIO"));
                tabla.addCell(celda);

                celda = new com.itextpdf.layout.element.Cell().setTextAlignment(TextAlignment.CENTER).setFontSize(6).setFont(font_bold).setMaxWidth(50);
                celda.add(new Paragraph("DESCUENTO"));
                tabla.addCell(celda);

                celda = new com.itextpdf.layout.element.Cell().setTextAlignment(TextAlignment.CENTER).setFontSize(6).setFont(font_bold).setMaxWidth(50);
                celda.add(new Paragraph("SUBTOTAL"));
                tabla.addCell(celda);

                for (Detalles detalle: detalles) {
                    Productos producto = productosRepositorio.obtenerPorId(detalle.getIdproducto());
                    Unidadesmedidas unidadmedida = unidadesmedidasRepositorio.obtenerPorId(producto.getIdunidadmedida());

                    celda = new com.itextpdf.layout.element.Cell().setTextAlignment(TextAlignment.CENTER).setFontSize(8).setFont(font_normal);
                    celda.add(new Paragraph(decimalFormatEntero.format(producto.getIdproducto())));
                    tabla.addCell(celda);

                    celda = new com.itextpdf.layout.element.Cell().setTextAlignment(TextAlignment.CENTER).setFontSize(8).setFont(font_normal);
                    celda.add(new Paragraph(detalle.getCantidad().toString()));
                    tabla.addCell(celda);

                    celda = new com.itextpdf.layout.element.Cell().setTextAlignment(TextAlignment.CENTER).setFontSize(6).setFont(font_normal).setHeight(10f);
                    celda.add(new Paragraph(unidadmedida.getUnidadmedida()));
                    tabla.addCell(celda);

                    celda = new com.itextpdf.layout.element.Cell().setTextAlignment(TextAlignment.LEFT).setFontSize(6).setFont(font_normal).setHeight(10f);
                    celda.add(new Paragraph(producto.getProducto()));
                    tabla.addCell(celda);

                    celda = new com.itextpdf.layout.element.Cell().setTextAlignment(TextAlignment.RIGHT).setFontSize(8).setFont(font_normal);
                    celda.add(new Paragraph(decimalFormat.format(detalle.getPreciounitario())));
                    tabla.addCell(celda);

                    celda = new com.itextpdf.layout.element.Cell().setTextAlignment(TextAlignment.RIGHT).setFontSize(8).setFont(font_normal);
                    celda.add(new Paragraph(decimalFormat.format(detalle.getDescuento())));
                    tabla.addCell(celda);

                    BigDecimal subtotal = (detalle.getPreciounitario().multiply(detalle.getCantidad())).subtract(detalle.getDescuento());

                    celda = new com.itextpdf.layout.element.Cell().setTextAlignment(TextAlignment.RIGHT).setFontSize(8).setFont(font_normal);
                    celda.add(new Paragraph(decimalFormat.format(subtotal)));
                    tabla.addCell(celda);

                    sumasubtotal = sumasubtotal.add(subtotal);
                }

                NumerosLiteral dato= new NumerosLiteral();

                celda = new com.itextpdf.layout.element.Cell(2, 4).setTextAlignment(TextAlignment.LEFT).setFontSize(8).setFont(font_bold).setBorder(Border.NO_BORDER).setVerticalAlignment(VerticalAlignment.MIDDLE);
                celda.add(new Paragraph("Son: "+StringUtils.capitalize(dato.Convertir(decimalFormatOpcional.format(sumasubtotal), false))));
                tabla.addCell(celda);

                celda = new com.itextpdf.layout.element.Cell(1, 2).setTextAlignment(TextAlignment.RIGHT).setFontSize(8).setFont(font_normal);
                celda.add(new Paragraph("TOTAL Bs."));
                tabla.addCell(celda);

                celda = new com.itextpdf.layout.element.Cell().setTextAlignment(TextAlignment.RIGHT).setFontSize(8).setFont(font_bold);
                celda.add(new Paragraph(decimalFormat.format(sumasubtotal)));
                tabla.addCell(celda);

                celda = new com.itextpdf.layout.element.Cell(1, 2).setTextAlignment(TextAlignment.RIGHT).setFontSize(8).setFont(font_normal);
                celda.add(new Paragraph("TOTAL A PAGAR Bs. "));
                tabla.addCell(celda);

                celda = new com.itextpdf.layout.element.Cell().setTextAlignment(TextAlignment.RIGHT).setFontSize(8).setFont(font_bold);
                celda.add(new Paragraph(decimalFormat.format(sumasubtotal)));
                tabla.addCell(celda);

                celda = new com.itextpdf.layout.element.Cell(1, 7).setTextAlignment(TextAlignment.RIGHT).setFontSize(12).setFont(font_normal).setBorder(Border.NO_BORDER);
                celda.add(new Paragraph(" "));
                tabla.addCell(celda);

                celda = new com.itextpdf.layout.element.Cell(1, 5).setTextAlignment(TextAlignment.CENTER).setFontSize(8).setFont(font_normal).setBorder(Border.NO_BORDER).setVerticalAlignment(VerticalAlignment.MIDDLE);
                celda.add(new Paragraph("ESTA FACTURA CONTRIBUYE AL DESARROLLO DEL PAÍS, EL USO ILÍCITO SERÁ SANCIONADO PENALMENTE DE ACUERDO A LEY"));
                tabla.addCell(celda);

                String cadenaqrcode = "https://siat.impuestos.gob.bo/consulta/QR?nit="+empresa.getNit().toString()+"&cuf="+factura.getCuf()+"&numero="+factura.getNumerofactura().toString()+"&t=1";

                BarcodeQRCode qrcodedata = new BarcodeQRCode(cadenaqrcode);
                PdfFormXObject qrcodeobj = qrcodedata.createFormXObject(pdf);
                Image qrcode = new Image(qrcodeobj);

                qrcode.scaleAbsolute(80.38583f, 80.38583f);

                p = new Paragraph().setTextAlignment(TextAlignment.CENTER).setMarginTop(-5f);
                p.add(qrcode);

                celda = new com.itextpdf.layout.element.Cell(3, 2).setTextAlignment(TextAlignment.CENTER).setFontSize(8).setFont(font_normal).setBorder(Border.NO_BORDER);
                celda.add(p);
                tabla.addCell(celda);

                celda = new com.itextpdf.layout.element.Cell(1, 5).setTextAlignment(TextAlignment.CENTER).setFontSize(8).setFont(font_normal).setBorder(Border.NO_BORDER).setVerticalAlignment(VerticalAlignment.MIDDLE);
                celda.add(new Paragraph(factura.getLeyenda()));
                tabla.addCell(celda);

                if (factura.getCodigo()==9081) {
                    p = new Paragraph("“Este documento es la Representación Gráfica de un Documento Fiscal Digital emitido fuera de línea, verifique su envío con su proveedor o en la página web www.impuestos.gob.bo”").setFontSize(8).setFont(font_normal).setTextAlignment(TextAlignment.CENTER);
                } else {
                    p = new Paragraph("“Este documento es la Representación Gráfica de un Documento Fiscal Digital emitido en una modalidad de facturación en línea”").setFontSize(8).setFont(font_normal).setTextAlignment(TextAlignment.CENTER);
                }
                celda = new com.itextpdf.layout.element.Cell(1, 5).setTextAlignment(TextAlignment.CENTER).setFontSize(8).setFont(font_normal).setBorder(Border.NO_BORDER).setVerticalAlignment(VerticalAlignment.MIDDLE);
                celda.add(p);
                tabla.addCell(celda);

                documento.add(tabla);

                documento.close();
            } catch (Exception e) {
                System.out.println("Error: "+e.toString());
            }
            return salida.toByteArray();
        } else {
            return null;
        }
    }


}
