package xyz.cesarnvf.aigfec.servicio.implementacion;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.mail.MessagingException;
import javax.xml.datatype.DatatypeConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;

import bo.gob.impuestos.siat.MensajeServicio;
import bo.gob.impuestos.siat.RespuestaComunicacion;
import bo.gob.impuestos.siat.RespuestaCufd;
import bo.gob.impuestos.siat.RespuestaCuis;
import bo.gob.impuestos.siat.RespuestaFechaHora;
import bo.gob.impuestos.siat.RespuestaListaActividades;
import bo.gob.impuestos.siat.RespuestaListaActividadesDocumentoSector;
import bo.gob.impuestos.siat.RespuestaListaEventos;
import bo.gob.impuestos.siat.RespuestaListaParametricas;
import bo.gob.impuestos.siat.RespuestaListaParametricasLeyendas;
import bo.gob.impuestos.siat.RespuestaListaProductos;
import bo.gob.impuestos.siat.RespuestaRecepcion;
import bo.gob.impuestos.siat.RespuestaRegistroPuntoVenta;
import bo.gob.impuestos.siat.RespuestaVerificarNit;
import bo.gob.impuestos.siat.ServicioFacturacion;
import bo.gob.impuestos.siat.ServicioFacturacionCodigos;
import bo.gob.impuestos.siat.ServicioFacturacionOperaciones;
import bo.gob.impuestos.siat.ServicioFacturacionSincronizacion;
import bo.gob.impuestos.siat.SolicitudAnulacion;
import bo.gob.impuestos.siat.SolicitudCufd;
import bo.gob.impuestos.siat.SolicitudCuis;
import bo.gob.impuestos.siat.SolicitudEventoSignificativo;
import bo.gob.impuestos.siat.SolicitudRecepcionFactura;
import bo.gob.impuestos.siat.SolicitudRecepcionPaquete;
import bo.gob.impuestos.siat.SolicitudRegistroPuntoVenta;
import bo.gob.impuestos.siat.SolicitudSincronizacion;
import bo.gob.impuestos.siat.SolicitudValidacionRecepcion;
import bo.gob.impuestos.siat.SolicitudVerificarNit;
import xyz.cesarnvf.aigfec.modelo.Anulacionessin;
import xyz.cesarnvf.aigfec.modelo.Clientes;
import xyz.cesarnvf.aigfec.modelo.Correos;
import xyz.cesarnvf.aigfec.modelo.Cufds;
import xyz.cesarnvf.aigfec.modelo.Cuis;
import xyz.cesarnvf.aigfec.modelo.Detalles;
import xyz.cesarnvf.aigfec.modelo.Documentosidentidadessin;
import xyz.cesarnvf.aigfec.modelo.Empresas;
import xyz.cesarnvf.aigfec.modelo.Eventossignificativossin;
import xyz.cesarnvf.aigfec.modelo.Facturas;
import xyz.cesarnvf.aigfec.modelo.Productos;
import xyz.cesarnvf.aigfec.modelo.Productossin;
import xyz.cesarnvf.aigfec.modelo.Puntosventas;
import xyz.cesarnvf.aigfec.modelo.Sucursales;
import xyz.cesarnvf.aigfec.modelo.Unidadesmedidas;
import xyz.cesarnvf.aigfec.modelo.Usuarios;
import xyz.cesarnvf.aigfec.modelo.auxi.Respuestacufds;
import xyz.cesarnvf.aigfec.modelo.auxi.Respuestacuis;
import xyz.cesarnvf.aigfec.modelo.auxi.Respuestas;
import xyz.cesarnvf.aigfec.modelo.auxi.RespuestasFacturas;
import xyz.cesarnvf.aigfec.modelo.auxi.Respuestasactividades;
import xyz.cesarnvf.aigfec.modelo.auxi.Respuestasactividadessector;
import xyz.cesarnvf.aigfec.modelo.auxi.Respuestaseventossignificativos;
import xyz.cesarnvf.aigfec.modelo.auxi.Respuestasfechas;
import xyz.cesarnvf.aigfec.modelo.auxi.Respuestasleyendas;
import xyz.cesarnvf.aigfec.modelo.auxi.Respuestasmensajesservicio;
import xyz.cesarnvf.aigfec.modelo.auxi.Respuestasmotivoanulacion;
import xyz.cesarnvf.aigfec.modelo.auxi.Respuestaspaises;
import xyz.cesarnvf.aigfec.modelo.auxi.Respuestasproductos;
import xyz.cesarnvf.aigfec.modelo.auxi.Respuestastiposdocumentosidentidad;
import xyz.cesarnvf.aigfec.modelo.auxi.Respuestastiposdocumentossector;
import xyz.cesarnvf.aigfec.modelo.auxi.Respuestastiposemision;
import xyz.cesarnvf.aigfec.modelo.auxi.Respuestastiposfactura;
import xyz.cesarnvf.aigfec.modelo.auxi.Respuestastiposhabitacion;
import xyz.cesarnvf.aigfec.modelo.auxi.Respuestastiposmetodopago;
import xyz.cesarnvf.aigfec.modelo.auxi.Respuestastiposmoneda;
import xyz.cesarnvf.aigfec.modelo.auxi.Respuestastipospuntoventa;
import xyz.cesarnvf.aigfec.modelo.auxi.Respuestastiposunidadmedida;
import xyz.cesarnvf.aigfec.modelo.otd.ClientesOTD;
import xyz.cesarnvf.aigfec.modelo.otd.ProductosOTD;
import xyz.cesarnvf.aigfec.modelo.otd.PuntosventasOTD;
import xyz.cesarnvf.aigfec.modelo.xml.CabecerasXML;
import xyz.cesarnvf.aigfec.modelo.xml.DetallesXML;
import xyz.cesarnvf.aigfec.modelo.xml.FacturasXML;
import xyz.cesarnvf.aigfec.repositorio.AnulacionessinRepositorio;
import xyz.cesarnvf.aigfec.repositorio.BitacorasRepositorio;
import xyz.cesarnvf.aigfec.repositorio.ClientesRepositorio;
import xyz.cesarnvf.aigfec.repositorio.CufdsRepositorio;
import xyz.cesarnvf.aigfec.repositorio.CuisRepositorio;
import xyz.cesarnvf.aigfec.repositorio.DetallesRepositorio;
import xyz.cesarnvf.aigfec.repositorio.DocumentosidentidadessinRepositorio;
import xyz.cesarnvf.aigfec.repositorio.EmpresasRepositorio;
import xyz.cesarnvf.aigfec.repositorio.EventossignificativossinRepositorio;
import xyz.cesarnvf.aigfec.repositorio.FacturasRepositorio;
import xyz.cesarnvf.aigfec.repositorio.ProductosRepositorio;
import xyz.cesarnvf.aigfec.repositorio.ProductossinRepositorio;
import xyz.cesarnvf.aigfec.repositorio.PuntosventasRepositorio;
import xyz.cesarnvf.aigfec.repositorio.SucursalesRepositorio;
import xyz.cesarnvf.aigfec.repositorio.UnidadesmedidasRepositorio;
import xyz.cesarnvf.aigfec.repositorio.UsuariosRepositorio;
import xyz.cesarnvf.aigfec.servicio.CorreosServicio;
import xyz.cesarnvf.aigfec.servicio.FuncionesSIATServicio;
import xyz.cesarnvf.aigfec.servicioPDF.FacturasServicioPDF;
import xyz.cesarnvf.aigfec.utilitarios.FacturasUtilitarios;
import xyz.cesarnvf.aigfec.utilitarios.GZIPUtilitarios;
import xyz.cesarnvf.aigfec.utilitarios.Utilitarios;

@Service
public class FuncionesSIATServicioImplementacion implements FuncionesSIATServicio {

    @Autowired
    private ServicioFacturacionOperaciones servicioFacturacionOperaciones;
    @Autowired
    private ServicioFacturacionCodigos servicioFacturacionCodigos;
    @Autowired
    private ServicioFacturacionSincronizacion servicioFacturacionSincronizacion;
    @Autowired
    private ServicioFacturacion servicioFacturacion;
    @Autowired
    private PuntosventasRepositorio puntosventasRepositorio;
    @Autowired
    private SucursalesRepositorio sucursalesRepositorio;
    @Autowired
    private EmpresasRepositorio empresasRepositorio;
    @Autowired
    private CuisRepositorio cuisRepositorio;
    @Autowired
    private CufdsRepositorio cufdsRepositorio;
    @Autowired
    private ClientesRepositorio clientesRepositorio;
    @Autowired
    private DocumentosidentidadessinRepositorio documentosidentidadessinRepositorio;
    @Autowired
    private ProductosRepositorio productosRepositorio;
    @Autowired
    ProductossinRepositorio productossinRepositorio;
    @Autowired
    private UnidadesmedidasRepositorio unidadesmedidasRepositorio;
    @Autowired
    private FacturasRepositorio facturasRepositorio;
    @Autowired
    private DetallesRepositorio detallesRepositorio;
    @Autowired
    private UsuariosRepositorio usuariosRepositorio;
    @Autowired
    public FacturasUtilitarios facturasUtilitarios;
    @Autowired
    BitacorasRepositorio bitacorasRepositorio;
    @Autowired
    public GZIPUtilitarios gzipUtilitarios;
    @Autowired
    public FacturasServicioPDF facturasServicioPDF;
    @Autowired
    public EventossignificativossinRepositorio eventossignificativossinRepositorio;
    @Autowired
    public AnulacionessinRepositorio anulacionessinRepositorio;

    @Autowired
    CorreosServicio correosServicio;

    @Autowired
    private TemplateEngine plantilla;

    @Value("${ruta.archivos}")
    private String ruta;

    @Override
    public List<Respuestas> verificarConexion() throws RemoteException {
        RespuestaComunicacion respuestaComunicacion = this.servicioFacturacionOperaciones.verificarComunicacion();

        List<Respuestas> mensajes = new ArrayList<>();
        for (MensajeServicio mensajeServicio : respuestaComunicacion.getMensajesList()) {
            Respuestas mensaje = new Respuestas(mensajeServicio.getCodigo(), mensajeServicio.getDescripcion());
            mensajes.add(mensaje);
        }
        return mensajes;
    }

    @Override
    public Integer verificarNIT(Long idsucursal, Long nit) throws RemoteException {
        Integer respuesta = 0;

        Sucursales sucursal = sucursalesRepositorio.obtenerPorId(idsucursal);
        Empresas empresa = empresasRepositorio.obtenerPorId(sucursal.getIdempresa());
        Cuis cui = cuisRepositorio.obtenerPorIdpuntoventa(idsucursal);

        SolicitudVerificarNit solicitudVerificarNit = new SolicitudVerificarNit();
        solicitudVerificarNit.setCodigoAmbiente(empresa.getAmbiente());
        solicitudVerificarNit.setCodigoModalidad(empresa.getModalidad());
        solicitudVerificarNit.setCodigoSistema(empresa.getCodigosistema());
        solicitudVerificarNit.setNit(empresa.getNit());
        solicitudVerificarNit.setCodigoSucursal(sucursal.getSucursal());
        solicitudVerificarNit.setCuis(cui.getCui());
        solicitudVerificarNit.setNitParaVerificacion(nit);

        RespuestaVerificarNit respuestaVerificarNit = this.servicioFacturacionCodigos.verificarNit(solicitudVerificarNit);

        List<Respuestas> mensajes = new ArrayList<>();
        if(!respuestaVerificarNit.getTransaccion() && respuestaVerificarNit.getMensajesList() != null) {
            for (MensajeServicio mensajeServicio : respuestaVerificarNit.getMensajesList()) {
                Respuestas mensaje = new Respuestas(mensajeServicio.getCodigo(), mensajeServicio.getDescripcion());
                if (mensajeServicio.getCodigo()==994) {
                    respuesta = 1;
                }
                mensajes.add(mensaje);
            }
        }
        return respuesta;
    }

    @Override
    public Respuestacuis obtenerCUIS(Long idpuntoventa) throws RemoteException {

        Puntosventas puntoventa = puntosventasRepositorio.obtenerPorId(idpuntoventa);
        Sucursales sucursal = sucursalesRepositorio.obtenerPorId(puntoventa.getIdsucursal());
        Empresas empresa = empresasRepositorio.obtenerPorId(sucursal.getIdempresa());

        SolicitudCuis solicitudCuis = new SolicitudCuis();
        solicitudCuis.setCodigoAmbiente(empresa.getAmbiente());
        solicitudCuis.setCodigoModalidad(empresa.getModalidad());
        solicitudCuis.setCodigoSistema(empresa.getCodigosistema());
        solicitudCuis.setNit(empresa.getNit());
        solicitudCuis.setCodigoSucursal(sucursal.getSucursal());
        solicitudCuis.setCodigoPuntoVenta(puntoventa.getPuntoventa());

        RespuestaCuis respuestaCuis = this.servicioFacturacionCodigos.cuis(solicitudCuis);

        LocalDateTime fechavigencia=null;
        Boolean vigencia=null;
        List<Respuestas> mensajes = new ArrayList<>();
        if(!respuestaCuis.getTransaccion() && respuestaCuis.getMensajesList() != null) {
            for (MensajeServicio mensajeServicio : respuestaCuis.getMensajesList()) {
                Respuestas mensaje = new Respuestas(mensajeServicio.getCodigo(), mensajeServicio.getDescripcion());
                mensajes.add(mensaje);
            }
        }
        Date fecha =  respuestaCuis.getFechaVigencia().getTime();
        fechavigencia =  fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        vigencia = Boolean.TRUE;
        return new Respuestacuis(respuestaCuis.getCodigo(), fechavigencia, vigencia, mensajes);
    }

    @Override
    public List<Respuestas> registrarPuntoventa(Long idpuntoventa) throws RemoteException {
        Puntosventas puntoventa = puntosventasRepositorio.obtenerPorId(idpuntoventa);
        Sucursales sucursal = sucursalesRepositorio.obtenerPorId(puntoventa.getIdsucursal());
        Empresas empresa = empresasRepositorio.obtenerPorId(sucursal.getIdempresa());
        Cuis cui = null ;
        if (puntoventa.getPuntoventa()>0) {
            Long idpuntoventa0 = puntosventasRepositorio.obtenerPorIdpuntoventa0(sucursal.getIdsucursal());
            cui = cuisRepositorio.obtenerPorPuntoventa(idpuntoventa0);
        } else {
            cui = cuisRepositorio.obtenerPorPuntoventa(idpuntoventa);
        }

        SolicitudRegistroPuntoVenta solicitudRegistro = new SolicitudRegistroPuntoVenta();
        solicitudRegistro.setCodigoAmbiente(empresa.getAmbiente());
        solicitudRegistro.setCodigoModalidad(empresa.getModalidad());
        solicitudRegistro.setCodigoSistema(empresa.getCodigosistema());
        solicitudRegistro.setCodigoSucursal(sucursal.getSucursal());
        solicitudRegistro.setNit(empresa.getNit());
        solicitudRegistro.setCuis(cui.getCui());
        solicitudRegistro.setCodigoTipoPuntoVenta(5);
        solicitudRegistro.setDescripcion(puntoventa.getNombrepuntoventa());
        solicitudRegistro.setNombrePuntoVenta(puntoventa.getPuntoventa().toString());

        RespuestaRegistroPuntoVenta respuestaRegistroPuntoVenta = this.servicioFacturacionOperaciones.registroPuntoVenta(solicitudRegistro);

        List<Respuestas> mensajes = new ArrayList<>();
        if(!respuestaRegistroPuntoVenta.getTransaccion() && respuestaRegistroPuntoVenta.getMensajesList() != null) {
            for (MensajeServicio mensajeServicio : respuestaRegistroPuntoVenta.getMensajesList()) {
                Respuestas mensaje = new Respuestas(mensajeServicio.getCodigo(), mensajeServicio.getDescripcion());
                System.out.println(mensaje);
                mensajes.add(mensaje);
            }
        }
        return mensajes;
    }

    @Override
    public List<Respuestas> registrarCUIS(Long idpuntoventa) throws RemoteException {
        Puntosventas puntoventa = puntosventasRepositorio.obtenerPorId(idpuntoventa);
        Sucursales sucursal = sucursalesRepositorio.obtenerPorId(puntoventa.getIdsucursal());
        Empresas empresa = empresasRepositorio.obtenerPorId(sucursal.getIdempresa());

        try {
            List<Respuestas> reg = this.registrarPuntoventa(idpuntoventa);
        } catch (Exception e) {
            //e.printStackTrace();
        }

        SolicitudCuis solicitudCuis = new SolicitudCuis();
        solicitudCuis.setCodigoAmbiente(empresa.getAmbiente());
        solicitudCuis.setCodigoModalidad(empresa.getModalidad());
        solicitudCuis.setCodigoSistema(empresa.getCodigosistema());
        solicitudCuis.setNit(empresa.getNit());
        solicitudCuis.setCodigoSucursal(sucursal.getSucursal());
        solicitudCuis.setCodigoPuntoVenta(puntoventa.getPuntoventa());

        RespuestaCuis respuestaCuis = this.servicioFacturacionCodigos.cuis(solicitudCuis);

        List<Respuestas> mensajes = new ArrayList<>();
        if(!respuestaCuis.getTransaccion() && respuestaCuis.getMensajesList() != null) {
            for (MensajeServicio mensajeServicio : respuestaCuis.getMensajesList()) {
                Respuestas mensaje = new Respuestas(mensajeServicio.getCodigo(), mensajeServicio.getDescripcion());
                System.out.println(mensaje);
                mensajes.add(mensaje);
            }
        }

        LocalDateTime fechavigencia=null;
        Respuestasfechas ofechaservidor=null;
        LocalDateTime fechaservidor = LocalDateTime.now();
        try {
            ofechaservidor = obtenerFechahora(puntoventa.getIdpuntoventa());
            fechaservidor=LocalDateTime.parse(ofechaservidor.getFechahora());
        } catch (Exception e) {
            fechaservidor = LocalDateTime.now();
        }

        Date fecha =  respuestaCuis.getFechaVigencia().getTime();
        fechavigencia =  fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        Cuis verificarcui = cuisRepositorio.obtenerPorIdpuntoventa(puntoventa.getIdpuntoventa());
        if (verificarcui==null) {
            verificarcui = new Cuis();
            verificarcui.setIdpuntoventa(puntoventa.getIdpuntoventa());
            verificarcui.setCui(respuestaCuis.getCodigo());
            verificarcui.setFechavigencia(fechavigencia);
            verificarcui.setEstado(Boolean.TRUE);
            cuisRepositorio.adicionar(verificarcui);
        }

        Cuis cui = cuisRepositorio.obtenerPorIdpuntoventa(puntoventa.getIdpuntoventa());
        cui.setEstado(Boolean.FALSE);
        cuisRepositorio.modificar(cui);

        cui = new Cuis();
        cui.setIdpuntoventa(puntoventa.getIdpuntoventa());
        cui.setCui(respuestaCuis.getCodigo());
        cui.setFechavigencia(fechavigencia);
        cui.setEstado(Boolean.TRUE);
        cuisRepositorio.adicionar(cui);

        return mensajes;
    }


    @Override
    public Respuestasactividades obtenerActividades(Long idpuntoventa) throws RemoteException {

        Puntosventas puntoventa = puntosventasRepositorio.obtenerPorId(idpuntoventa);
        Sucursales sucursal = sucursalesRepositorio.obtenerPorId(puntoventa.getIdsucursal());
        Empresas empresa = empresasRepositorio.obtenerPorId(sucursal.getIdempresa());
        Cuis cui = cuisRepositorio.obtenerPorIdpuntoventa(puntoventa.getIdpuntoventa());

        SolicitudSincronizacion solicitudSincronizacion = new SolicitudSincronizacion();
        solicitudSincronizacion.setCodigoAmbiente(empresa.getAmbiente());
        solicitudSincronizacion.setCodigoSistema(empresa.getCodigosistema());
        solicitudSincronizacion.setNit(empresa.getNit());
        solicitudSincronizacion.setCuis(cui.getCui());
        solicitudSincronizacion.setCodigoSucursal(sucursal.getSucursal());
        solicitudSincronizacion.setCodigoPuntoVenta(puntoventa.getPuntoventa());

        RespuestaListaActividades respuestaListadoActividades = this.servicioFacturacionSincronizacion.sincronizarActividades(solicitudSincronizacion);

        List<Respuestas> mensajes = new ArrayList<>();
        if(!respuestaListadoActividades.getTransaccion() && respuestaListadoActividades.getMensajesList() != null) {
            for (MensajeServicio mensajeServicio : respuestaListadoActividades.getMensajesList()) {
                Respuestas mensaje = new Respuestas(mensajeServicio.getCodigo(), mensajeServicio.getDescripcion());
                mensajes.add(mensaje);
            }
        }
        return new Respuestasactividades(respuestaListadoActividades.getListaActividades(), mensajes);
    }

    @Override
    public Respuestasfechas obtenerFechahora(Long idpuntoventa) throws RemoteException {

        Puntosventas puntoventa = puntosventasRepositorio.obtenerPorId(idpuntoventa);
        Sucursales sucursal = sucursalesRepositorio.obtenerPorId(puntoventa.getIdsucursal());
        Empresas empresa = empresasRepositorio.obtenerPorId(sucursal.getIdempresa());
        Cuis cui = cuisRepositorio.obtenerPorIdpuntoventa(puntoventa.getIdpuntoventa());

        SolicitudSincronizacion solicitudSincronizacion = new SolicitudSincronizacion();
        solicitudSincronizacion.setCodigoAmbiente(empresa.getAmbiente());
        solicitudSincronizacion.setCodigoSistema(empresa.getCodigosistema());
        solicitudSincronizacion.setNit(empresa.getNit());
        solicitudSincronizacion.setCuis(cui.getCui());
        solicitudSincronizacion.setCodigoSucursal(sucursal.getSucursal());
        solicitudSincronizacion.setCodigoPuntoVenta(puntoventa.getPuntoventa());

        RespuestaFechaHora respuestaFechaHora = this.servicioFacturacionSincronizacion.sincronizarFechaHora(solicitudSincronizacion);
        List<Respuestas> mensajes = new ArrayList<>();
        if(!respuestaFechaHora.getTransaccion() && respuestaFechaHora.getMensajesList() != null) {
            for (MensajeServicio mensajeServicio : respuestaFechaHora.getMensajesList()) {
                mensajes.add(new Respuestas(mensajeServicio.getCodigo(), mensajeServicio.getDescripcion()));
            }
        }
        return new Respuestasfechas(respuestaFechaHora.getFechaHora(), mensajes);
    }

    @Override
    public Respuestasactividadessector obtenerActividadesSector(Integer epuntoventa) throws RemoteException {

        Puntosventas puntoventa = puntosventasRepositorio.obtenerPorPuntoventa(epuntoventa);
        Sucursales sucursal = sucursalesRepositorio.obtenerPorId(puntoventa.getIdsucursal());
        Empresas empresa = empresasRepositorio.obtenerPorId(sucursal.getIdempresa());
        Cuis cui = cuisRepositorio.obtenerPorIdpuntoventa(puntoventa.getIdpuntoventa());

        SolicitudSincronizacion solicitudSincronizacion = new SolicitudSincronizacion();
        solicitudSincronizacion.setCodigoAmbiente(empresa.getAmbiente());
        solicitudSincronizacion.setCodigoSistema(empresa.getCodigosistema());
        solicitudSincronizacion.setNit(empresa.getNit());
        solicitudSincronizacion.setCuis(cui.getCui());
        solicitudSincronizacion.setCodigoSucursal(sucursal.getSucursal());
        solicitudSincronizacion.setCodigoPuntoVenta(puntoventa.getPuntoventa());

        RespuestaListaActividadesDocumentoSector respuestaListaActividadesDocumentoSector = this.servicioFacturacionSincronizacion.sincronizarListaActividadesDocumentoSector(solicitudSincronizacion);

        List<Respuestas> mensajes = new ArrayList<>();
        if(!respuestaListaActividadesDocumentoSector.getTransaccion() && respuestaListaActividadesDocumentoSector.getMensajesList() != null) {
            for (MensajeServicio mensajeServicio : respuestaListaActividadesDocumentoSector.getMensajesList()) {
                Respuestas mensaje = new Respuestas(mensajeServicio.getCodigo(), mensajeServicio.getDescripcion());
                mensajes.add(mensaje);
            }
        }
        return new Respuestasactividadessector(respuestaListaActividadesDocumentoSector.getListaActividadesDocumentoSector(), mensajes);
    }

    @Override
    public Respuestasleyendas obtenerLeyendas(Long eidpuntoventa) throws RemoteException {

        Puntosventas puntoventa = puntosventasRepositorio.obtenerPorId(eidpuntoventa);
        Sucursales sucursal = sucursalesRepositorio.obtenerPorId(puntoventa.getIdsucursal());
        Empresas empresa = empresasRepositorio.obtenerPorId(sucursal.getIdempresa());
        Cuis cui = cuisRepositorio.obtenerPorIdpuntoventa(puntoventa.getIdpuntoventa());

        SolicitudSincronizacion solicitudSincronizacion = new SolicitudSincronizacion();
        solicitudSincronizacion.setCodigoAmbiente(empresa.getAmbiente());
        solicitudSincronizacion.setCodigoSistema(empresa.getCodigosistema());
        solicitudSincronizacion.setNit(empresa.getNit());
        solicitudSincronizacion.setCuis(cui.getCui());
        solicitudSincronizacion.setCodigoSucursal(sucursal.getSucursal());
        solicitudSincronizacion.setCodigoPuntoVenta(puntoventa.getPuntoventa());

        RespuestaListaParametricasLeyendas respuestaListaParametricasLeyendas = this.servicioFacturacionSincronizacion.sincronizarListaLeyendasFactura(solicitudSincronizacion);

        List<Respuestas> mensajes = new ArrayList<>();
        if(!respuestaListaParametricasLeyendas.getTransaccion() && respuestaListaParametricasLeyendas.getMensajesList() != null) {
            for (MensajeServicio mensajeServicio : respuestaListaParametricasLeyendas.getMensajesList()) {
                Respuestas mensaje = new Respuestas(mensajeServicio.getCodigo(), mensajeServicio.getDescripcion());
                mensajes.add(mensaje);
            }
        }
        return new Respuestasleyendas(respuestaListaParametricasLeyendas.getListaLeyendas(), mensajes);
    }

    @Override
    public Respuestasmensajesservicio obtenerMensajesServicio(Integer epuntoventa) throws RemoteException {
        Puntosventas puntoventa = puntosventasRepositorio.obtenerPorPuntoventa(epuntoventa);
        Sucursales sucursal = sucursalesRepositorio.obtenerPorId(puntoventa.getIdsucursal());
        Empresas empresa = empresasRepositorio.obtenerPorId(sucursal.getIdempresa());
        Cuis cui = cuisRepositorio.obtenerPorIdpuntoventa(puntoventa.getIdpuntoventa());

        SolicitudSincronizacion solicitudSincronizacion = new SolicitudSincronizacion();
        solicitudSincronizacion.setCodigoAmbiente(empresa.getAmbiente());
        solicitudSincronizacion.setCodigoSistema(empresa.getCodigosistema());
        solicitudSincronizacion.setNit(empresa.getNit());
        solicitudSincronizacion.setCuis(cui.getCui());
        solicitudSincronizacion.setCodigoSucursal(sucursal.getSucursal());
        solicitudSincronizacion.setCodigoPuntoVenta(puntoventa.getPuntoventa());

        RespuestaListaParametricas respuestaListaParametricas = this.servicioFacturacionSincronizacion.sincronizarListaMensajesServicios(solicitudSincronizacion);

        List<Respuestas> mensajes = new ArrayList<>();
        if(!respuestaListaParametricas.getTransaccion() && respuestaListaParametricas.getMensajesList() != null) {
            for (MensajeServicio mensajeServicio : respuestaListaParametricas.getMensajesList()) {
                Respuestas mensaje = new Respuestas(mensajeServicio.getCodigo(), mensajeServicio.getDescripcion());
                mensajes.add(mensaje);
            }
        }
        return new Respuestasmensajesservicio(respuestaListaParametricas.getListaCodigos(), mensajes);
    }

    @Override
    public Respuestasproductos obtenerProductos(Long eidpuntoventa) throws RemoteException {
        Puntosventas puntoventa = puntosventasRepositorio.obtenerPorId(eidpuntoventa);
        Sucursales sucursal = sucursalesRepositorio.obtenerPorId(puntoventa.getIdsucursal());
        Empresas empresa = empresasRepositorio.obtenerPorId(sucursal.getIdempresa());
        Cuis cui = cuisRepositorio.obtenerPorIdpuntoventa(puntoventa.getIdpuntoventa());

        SolicitudSincronizacion solicitudSincronizacion = new SolicitudSincronizacion();
        solicitudSincronizacion.setCodigoAmbiente(empresa.getAmbiente());
        solicitudSincronizacion.setCodigoSistema(empresa.getCodigosistema());
        solicitudSincronizacion.setNit(empresa.getNit());
        solicitudSincronizacion.setCuis(cui.getCui());
        solicitudSincronizacion.setCodigoSucursal(sucursal.getSucursal());
        solicitudSincronizacion.setCodigoPuntoVenta(puntoventa.getPuntoventa());

        RespuestaListaProductos respuestaListaProductos = this.servicioFacturacionSincronizacion.sincronizarListaProductosServicios(solicitudSincronizacion);

        List<Respuestas> mensajes = new ArrayList<>();
        if(!respuestaListaProductos.getTransaccion() && respuestaListaProductos.getMensajesList() != null) {
            for (MensajeServicio mensajeServicio : respuestaListaProductos.getMensajesList()) {
                Respuestas mensaje = new Respuestas(mensajeServicio.getCodigo(), mensajeServicio.getDescripcion());
                mensajes.add(mensaje);
            }
        }
        return new Respuestasproductos(respuestaListaProductos.getListaCodigos(), mensajes);
    }

    @Override
    public Respuestaseventossignificativos obtenerEventosSignificativos(Long eidpuntoventa) throws RemoteException {
        Puntosventas puntoventa = puntosventasRepositorio.obtenerPorId(eidpuntoventa);
        Sucursales sucursal = sucursalesRepositorio.obtenerPorId(puntoventa.getIdsucursal());
        Empresas empresa = empresasRepositorio.obtenerPorId(sucursal.getIdempresa());
        Cuis cui = cuisRepositorio.obtenerPorIdpuntoventa(puntoventa.getIdpuntoventa());

        SolicitudSincronizacion solicitudSincronizacion = new SolicitudSincronizacion();
        solicitudSincronizacion.setCodigoAmbiente(empresa.getAmbiente());
        solicitudSincronizacion.setCodigoSistema(empresa.getCodigosistema());
        solicitudSincronizacion.setNit(empresa.getNit());
        solicitudSincronizacion.setCuis(cui.getCui());
        solicitudSincronizacion.setCodigoSucursal(sucursal.getSucursal());
        solicitudSincronizacion.setCodigoPuntoVenta(puntoventa.getPuntoventa());

        RespuestaListaParametricas respuestaListaParametricas = this.servicioFacturacionSincronizacion.sincronizarParametricaEventosSignificativos(solicitudSincronizacion);

        List<Respuestas> mensajes = new ArrayList<>();
        if(!respuestaListaParametricas.getTransaccion() && respuestaListaParametricas.getMensajesList() != null) {
            for (MensajeServicio mensajeServicio : respuestaListaParametricas.getMensajesList()) {
                Respuestas mensaje = new Respuestas(mensajeServicio.getCodigo(), mensajeServicio.getDescripcion());
                mensajes.add(mensaje);
            }
        }
        return new Respuestaseventossignificativos(respuestaListaParametricas.getListaCodigos(), mensajes);
    }

    @Override
    public Respuestasmotivoanulacion obtenerMotivoAnulacion(Integer epuntoventa) throws RemoteException {
        Puntosventas puntoventa = puntosventasRepositorio.obtenerPorPuntoventa(epuntoventa);
        Sucursales sucursal = sucursalesRepositorio.obtenerPorId(puntoventa.getIdsucursal());
        Empresas empresa = empresasRepositorio.obtenerPorId(sucursal.getIdempresa());
        Cuis cui = cuisRepositorio.obtenerPorIdpuntoventa(puntoventa.getIdpuntoventa());

        SolicitudSincronizacion solicitudSincronizacion = new SolicitudSincronizacion();
        solicitudSincronizacion.setCodigoAmbiente(empresa.getAmbiente());
        solicitudSincronizacion.setCodigoSistema(empresa.getCodigosistema());
        solicitudSincronizacion.setNit(empresa.getNit());
        solicitudSincronizacion.setCuis(cui.getCui());
        solicitudSincronizacion.setCodigoSucursal(sucursal.getSucursal());
        solicitudSincronizacion.setCodigoPuntoVenta(puntoventa.getPuntoventa());

        RespuestaListaParametricas respuestaListaParametricas = this.servicioFacturacionSincronizacion.sincronizarParametricaMotivoAnulacion(solicitudSincronizacion);


        List<Respuestas> mensajes = new ArrayList<>();
        if(!respuestaListaParametricas.getTransaccion() && respuestaListaParametricas.getMensajesList() != null) {
            for (MensajeServicio mensajeServicio : respuestaListaParametricas.getMensajesList()) {
                Respuestas mensaje = new Respuestas(mensajeServicio.getCodigo(), mensajeServicio.getDescripcion());
                mensajes.add(mensaje);
            }
        }
        return new Respuestasmotivoanulacion(respuestaListaParametricas.getListaCodigos(), mensajes);
    }

    @Override
    public Respuestaspaises obtenerPaises(Integer epuntoventa) throws RemoteException {
        Puntosventas puntoventa = puntosventasRepositorio.obtenerPorPuntoventa(epuntoventa);
        Sucursales sucursal = sucursalesRepositorio.obtenerPorId(puntoventa.getIdsucursal());
        Empresas empresa = empresasRepositorio.obtenerPorId(sucursal.getIdempresa());
        Cuis cui = cuisRepositorio.obtenerPorIdpuntoventa(puntoventa.getIdpuntoventa());

        SolicitudSincronizacion solicitudSincronizacion = new SolicitudSincronizacion();
        solicitudSincronizacion.setCodigoAmbiente(empresa.getAmbiente());
        solicitudSincronizacion.setCodigoSistema(empresa.getCodigosistema());
        solicitudSincronizacion.setNit(empresa.getNit());
        solicitudSincronizacion.setCuis(cui.getCui());
        solicitudSincronizacion.setCodigoSucursal(sucursal.getSucursal());
        solicitudSincronizacion.setCodigoPuntoVenta(puntoventa.getPuntoventa());

        RespuestaListaParametricas respuestaListaParametricas = this.servicioFacturacionSincronizacion.sincronizarParametricaPaisOrigen(solicitudSincronizacion);

        List<Respuestas> mensajes = new ArrayList<>();
        if(!respuestaListaParametricas.getTransaccion() && respuestaListaParametricas.getMensajesList() != null) {
            for (MensajeServicio mensajeServicio : respuestaListaParametricas.getMensajesList()) {
                Respuestas mensaje = new Respuestas(mensajeServicio.getCodigo(), mensajeServicio.getDescripcion());
                mensajes.add(mensaje);
            }
        }
        return new Respuestaspaises(respuestaListaParametricas.getListaCodigos(), mensajes);
    }

    @Override
    public Respuestastiposdocumentosidentidad obtenerTipoDocumentoIdentidad(Long eidpuntoventa) throws RemoteException{
        Puntosventas puntoventa = puntosventasRepositorio.obtenerPorId(eidpuntoventa);
        Sucursales sucursal = sucursalesRepositorio.obtenerPorId(puntoventa.getIdsucursal());
        Empresas empresa = empresasRepositorio.obtenerPorId(sucursal.getIdempresa());
        Cuis cui = cuisRepositorio.obtenerPorIdpuntoventa(puntoventa.getIdpuntoventa());

        SolicitudSincronizacion solicitudSincronizacion = new SolicitudSincronizacion();
        solicitudSincronizacion.setCodigoAmbiente(empresa.getAmbiente());
        solicitudSincronizacion.setCodigoSistema(empresa.getCodigosistema());
        solicitudSincronizacion.setNit(empresa.getNit());
        solicitudSincronizacion.setCuis(cui.getCui());
        solicitudSincronizacion.setCodigoSucursal(sucursal.getSucursal());
        solicitudSincronizacion.setCodigoPuntoVenta(puntoventa.getPuntoventa());

        RespuestaListaParametricas respuestaListaParametricas = this.servicioFacturacionSincronizacion.sincronizarParametricaTipoDocumentoIdentidad(solicitudSincronizacion);

        List<Respuestas> mensajes = new ArrayList<>();
        if(!respuestaListaParametricas.getTransaccion() && respuestaListaParametricas.getMensajesList() != null) {
            for (MensajeServicio mensajeServicio : respuestaListaParametricas.getMensajesList()) {
                Respuestas mensaje = new Respuestas(mensajeServicio.getCodigo(), mensajeServicio.getDescripcion());
                mensajes.add(mensaje);
            }
        }
        return new Respuestastiposdocumentosidentidad(respuestaListaParametricas.getListaCodigos(), mensajes);
    }

    @Override
    public Respuestastiposdocumentossector obtenerTipoDocumentosector(Integer epuntoventa) throws RemoteException {
        Puntosventas puntoventa = puntosventasRepositorio.obtenerPorPuntoventa(epuntoventa);
        Sucursales sucursal = sucursalesRepositorio.obtenerPorId(puntoventa.getIdsucursal());
        Empresas empresa = empresasRepositorio.obtenerPorId(sucursal.getIdempresa());
        Cuis cui = cuisRepositorio.obtenerPorIdpuntoventa(puntoventa.getIdpuntoventa());

        SolicitudSincronizacion solicitudSincronizacion = new SolicitudSincronizacion();
        solicitudSincronizacion.setCodigoAmbiente(empresa.getAmbiente());
        solicitudSincronizacion.setCodigoSistema(empresa.getCodigosistema());
        solicitudSincronizacion.setNit(empresa.getNit());
        solicitudSincronizacion.setCuis(cui.getCui());
        solicitudSincronizacion.setCodigoSucursal(sucursal.getSucursal());
        solicitudSincronizacion.setCodigoPuntoVenta(puntoventa.getPuntoventa());

        RespuestaListaParametricas respuestaListaParametricas = this.servicioFacturacionSincronizacion.sincronizarParametricaTipoDocumentoSector(solicitudSincronizacion);

        List<Respuestas> mensajes = new ArrayList<>();
        if(!respuestaListaParametricas.getTransaccion() && respuestaListaParametricas.getMensajesList() != null) {
            for (MensajeServicio mensajeServicio : respuestaListaParametricas.getMensajesList()) {
                Respuestas mensaje = new Respuestas(mensajeServicio.getCodigo(), mensajeServicio.getDescripcion());
                mensajes.add(mensaje);
            }
        }
        return new Respuestastiposdocumentossector(respuestaListaParametricas.getListaCodigos(), mensajes);
    }

    @Override
    public Respuestastiposemision obtenerTipoEmision(Integer epuntoventa) throws RemoteException {
        Puntosventas puntoventa = puntosventasRepositorio.obtenerPorPuntoventa(epuntoventa);
        Sucursales sucursal = sucursalesRepositorio.obtenerPorId(puntoventa.getIdsucursal());
        Empresas empresa = empresasRepositorio.obtenerPorId(sucursal.getIdempresa());
        Cuis cui = cuisRepositorio.obtenerPorIdpuntoventa(puntoventa.getIdpuntoventa());

        SolicitudSincronizacion solicitudSincronizacion = new SolicitudSincronizacion();
        solicitudSincronizacion.setCodigoAmbiente(empresa.getAmbiente());
        solicitudSincronizacion.setCodigoSistema(empresa.getCodigosistema());
        solicitudSincronizacion.setNit(empresa.getNit());
        solicitudSincronizacion.setCuis(cui.getCui());
        solicitudSincronizacion.setCodigoSucursal(sucursal.getSucursal());
        solicitudSincronizacion.setCodigoPuntoVenta(puntoventa.getPuntoventa());

        RespuestaListaParametricas respuestaListaParametricas = this.servicioFacturacionSincronizacion.sincronizarParametricaTipoEmision(solicitudSincronizacion);

        List<Respuestas> mensajes = new ArrayList<>();
        if(!respuestaListaParametricas.getTransaccion() && respuestaListaParametricas.getMensajesList() != null) {
            for (MensajeServicio mensajeServicio : respuestaListaParametricas.getMensajesList()) {
                Respuestas mensaje = new Respuestas(mensajeServicio.getCodigo(), mensajeServicio.getDescripcion());
                mensajes.add(mensaje);
            }
        }
        return new Respuestastiposemision(respuestaListaParametricas.getListaCodigos(), mensajes);
    }

    @Override
    public Respuestastiposhabitacion obtenerTipoHabitacion(Integer epuntoventa) throws RemoteException {
        Puntosventas puntoventa = puntosventasRepositorio.obtenerPorPuntoventa(epuntoventa);
        Sucursales sucursal = sucursalesRepositorio.obtenerPorId(puntoventa.getIdsucursal());
        Empresas empresa = empresasRepositorio.obtenerPorId(sucursal.getIdempresa());
        Cuis cui = cuisRepositorio.obtenerPorIdpuntoventa(puntoventa.getIdpuntoventa());

        SolicitudSincronizacion solicitudSincronizacion = new SolicitudSincronizacion();
        solicitudSincronizacion.setCodigoAmbiente(empresa.getAmbiente());
        solicitudSincronizacion.setCodigoSistema(empresa.getCodigosistema());
        solicitudSincronizacion.setNit(empresa.getNit());
        solicitudSincronizacion.setCuis(cui.getCui());
        solicitudSincronizacion.setCodigoSucursal(sucursal.getSucursal());
        solicitudSincronizacion.setCodigoPuntoVenta(puntoventa.getPuntoventa());

        RespuestaListaParametricas respuestaListaParametricas = this.servicioFacturacionSincronizacion.sincronizarParametricaTipoHabitacion(solicitudSincronizacion);

        List<Respuestas> mensajes = new ArrayList<>();
        if(!respuestaListaParametricas.getTransaccion() && respuestaListaParametricas.getMensajesList() != null) {
            for (MensajeServicio mensajeServicio : respuestaListaParametricas.getMensajesList()) {
                Respuestas mensaje = new Respuestas(mensajeServicio.getCodigo(), mensajeServicio.getDescripcion());
                mensajes.add(mensaje);
            }
        }
        return new Respuestastiposhabitacion(respuestaListaParametricas.getListaCodigos(), mensajes);
    }

    @Override
    public Respuestastiposmetodopago obtenerTipoMetodoPago(Long eidpuntoventa) throws RemoteException {
        Puntosventas puntoventa = puntosventasRepositorio.obtenerPorId(eidpuntoventa);
        Sucursales sucursal = sucursalesRepositorio.obtenerPorId(puntoventa.getIdsucursal());
        Empresas empresa = empresasRepositorio.obtenerPorId(sucursal.getIdempresa());
        Cuis cui = cuisRepositorio.obtenerPorIdpuntoventa(puntoventa.getIdpuntoventa());

        SolicitudSincronizacion solicitudSincronizacion = new SolicitudSincronizacion();
        solicitudSincronizacion.setCodigoAmbiente(empresa.getAmbiente());
        solicitudSincronizacion.setCodigoSistema(empresa.getCodigosistema());
        solicitudSincronizacion.setNit(empresa.getNit());
        solicitudSincronizacion.setCuis(cui.getCui());
        solicitudSincronizacion.setCodigoSucursal(sucursal.getSucursal());
        solicitudSincronizacion.setCodigoPuntoVenta(puntoventa.getPuntoventa());

        RespuestaListaParametricas respuestaListaParametricas = this.servicioFacturacionSincronizacion.sincronizarParametricaTipoMetodoPago(solicitudSincronizacion);

        List<Respuestas> mensajes = new ArrayList<>();
        if(!respuestaListaParametricas.getTransaccion() && respuestaListaParametricas.getMensajesList() != null) {
            for (MensajeServicio mensajeServicio : respuestaListaParametricas.getMensajesList()) {
                Respuestas mensaje = new Respuestas(mensajeServicio.getCodigo(), mensajeServicio.getDescripcion());
                mensajes.add(mensaje);
            }
        }
        return new Respuestastiposmetodopago(respuestaListaParametricas.getListaCodigos(), mensajes);
    }

    @Override
    public Respuestastiposmoneda obtenerTipoMoneda(Integer epuntoventa) throws RemoteException {
        Puntosventas puntoventa = puntosventasRepositorio.obtenerPorPuntoventa(epuntoventa);
        Sucursales sucursal = sucursalesRepositorio.obtenerPorId(puntoventa.getIdsucursal());
        Empresas empresa = empresasRepositorio.obtenerPorId(sucursal.getIdempresa());
        Cuis cui = cuisRepositorio.obtenerPorIdpuntoventa(puntoventa.getIdpuntoventa());

        SolicitudSincronizacion solicitudSincronizacion = new SolicitudSincronizacion();
        solicitudSincronizacion.setCodigoAmbiente(empresa.getAmbiente());
        solicitudSincronizacion.setCodigoSistema(empresa.getCodigosistema());
        solicitudSincronizacion.setNit(empresa.getNit());
        solicitudSincronizacion.setCuis(cui.getCui());
        solicitudSincronizacion.setCodigoSucursal(sucursal.getSucursal());
        solicitudSincronizacion.setCodigoPuntoVenta(puntoventa.getPuntoventa());

        RespuestaListaParametricas respuestaListaParametricas = this.servicioFacturacionSincronizacion.sincronizarParametricaTipoMoneda(solicitudSincronizacion);

        List<Respuestas> mensajes = new ArrayList<>();
        if(!respuestaListaParametricas.getTransaccion() && respuestaListaParametricas.getMensajesList() != null) {
            for (MensajeServicio mensajeServicio : respuestaListaParametricas.getMensajesList()) {
                Respuestas mensaje = new Respuestas(mensajeServicio.getCodigo(), mensajeServicio.getDescripcion());
                mensajes.add(mensaje);
            }
        }
        return new Respuestastiposmoneda(respuestaListaParametricas.getListaCodigos(), mensajes);
    }

    @Override
    public Respuestastipospuntoventa obtenerTipoPuntoVenta(Integer epuntoventa) throws RemoteException {
        Puntosventas puntoventa = puntosventasRepositorio.obtenerPorPuntoventa(epuntoventa);
        Sucursales sucursal = sucursalesRepositorio.obtenerPorId(puntoventa.getIdsucursal());
        Empresas empresa = empresasRepositorio.obtenerPorId(sucursal.getIdempresa());
        Cuis cui = cuisRepositorio.obtenerPorIdpuntoventa(puntoventa.getIdpuntoventa());

        SolicitudSincronizacion solicitudSincronizacion = new SolicitudSincronizacion();
        solicitudSincronizacion.setCodigoAmbiente(empresa.getAmbiente());
        solicitudSincronizacion.setCodigoSistema(empresa.getCodigosistema());
        solicitudSincronizacion.setNit(empresa.getNit());
        solicitudSincronizacion.setCuis(cui.getCui());
        solicitudSincronizacion.setCodigoSucursal(sucursal.getSucursal());
        solicitudSincronizacion.setCodigoPuntoVenta(puntoventa.getPuntoventa());

        RespuestaListaParametricas respuestaListaParametricas = this.servicioFacturacionSincronizacion.sincronizarParametricaTipoPuntoVenta(solicitudSincronizacion);

        List<Respuestas> mensajes = new ArrayList<>();
        if(!respuestaListaParametricas.getTransaccion() && respuestaListaParametricas.getMensajesList() != null) {
            for (MensajeServicio mensajeServicio : respuestaListaParametricas.getMensajesList()) {
                Respuestas mensaje = new Respuestas(mensajeServicio.getCodigo(), mensajeServicio.getDescripcion());
                mensajes.add(mensaje);
            }
        }
        return new Respuestastipospuntoventa(respuestaListaParametricas.getListaCodigos(), mensajes);
    }

    @Override
    public Respuestastiposfactura obtenerTipoFactura(Integer epuntoventa) throws RemoteException {
        Puntosventas puntoventa = puntosventasRepositorio.obtenerPorPuntoventa(epuntoventa);
        Sucursales sucursal = sucursalesRepositorio.obtenerPorId(puntoventa.getIdsucursal());
        Empresas empresa = empresasRepositorio.obtenerPorId(sucursal.getIdempresa());
        Cuis cui = cuisRepositorio.obtenerPorIdpuntoventa(puntoventa.getIdpuntoventa());

        SolicitudSincronizacion solicitudSincronizacion = new SolicitudSincronizacion();
        solicitudSincronizacion.setCodigoAmbiente(empresa.getAmbiente());
        solicitudSincronizacion.setCodigoSistema(empresa.getCodigosistema());
        solicitudSincronizacion.setNit(empresa.getNit());
        solicitudSincronizacion.setCuis(cui.getCui());
        solicitudSincronizacion.setCodigoSucursal(sucursal.getSucursal());
        solicitudSincronizacion.setCodigoPuntoVenta(puntoventa.getPuntoventa());

        RespuestaListaParametricas respuestaListaParametricas = this.servicioFacturacionSincronizacion.sincronizarParametricaTiposFactura(solicitudSincronizacion);

        List<Respuestas> mensajes = new ArrayList<>();
        if(!respuestaListaParametricas.getTransaccion() && respuestaListaParametricas.getMensajesList() != null) {
            for (MensajeServicio mensajeServicio : respuestaListaParametricas.getMensajesList()) {
                Respuestas mensaje = new Respuestas(mensajeServicio.getCodigo(), mensajeServicio.getDescripcion());
                mensajes.add(mensaje);
            }
        }
        return new Respuestastiposfactura(respuestaListaParametricas.getListaCodigos(), mensajes);
    }

    @Override
    public Respuestastiposunidadmedida obtenerTipoUnidadMedida(Integer epuntoventa) throws RemoteException {
        Puntosventas puntoventa = puntosventasRepositorio.obtenerPorPuntoventa(epuntoventa);
        Sucursales sucursal = sucursalesRepositorio.obtenerPorId(puntoventa.getIdsucursal());
        Empresas empresa = empresasRepositorio.obtenerPorId(sucursal.getIdempresa());
        Cuis cui = cuisRepositorio.obtenerPorIdpuntoventa(puntoventa.getIdpuntoventa());

        SolicitudSincronizacion solicitudSincronizacion = new SolicitudSincronizacion();
        solicitudSincronizacion.setCodigoAmbiente(empresa.getAmbiente());
        solicitudSincronizacion.setCodigoSistema(empresa.getCodigosistema());
        solicitudSincronizacion.setNit(empresa.getNit());
        solicitudSincronizacion.setCuis(cui.getCui());
        solicitudSincronizacion.setCodigoSucursal(sucursal.getSucursal());
        solicitudSincronizacion.setCodigoPuntoVenta(puntoventa.getPuntoventa());

        RespuestaListaParametricas respuestaListaParametricas = this.servicioFacturacionSincronizacion.sincronizarParametricaUnidadMedida(solicitudSincronizacion);

        List<Respuestas> mensajes = new ArrayList<>();
        if(!respuestaListaParametricas.getTransaccion() && respuestaListaParametricas.getMensajesList() != null) {
            for (MensajeServicio mensajeServicio : respuestaListaParametricas.getMensajesList()) {
                Respuestas mensaje = new Respuestas(mensajeServicio.getCodigo(), mensajeServicio.getDescripcion());
                mensajes.add(mensaje);
            }
        }
        return new Respuestastiposunidadmedida(respuestaListaParametricas.getListaCodigos(), mensajes);
    }

    @Override
    public Respuestacufds obtenerCUFDS(Long idpuntoventa) throws RemoteException {
        Puntosventas puntoventa = puntosventasRepositorio.obtenerPorId(idpuntoventa);
        Sucursales sucursal = sucursalesRepositorio.obtenerPorId(puntoventa.getIdsucursal());
        Empresas empresa = empresasRepositorio.obtenerPorId(sucursal.getIdempresa());
        Cuis cui = cuisRepositorio.obtenerPorIdpuntoventa(puntoventa.getIdpuntoventa());

        SolicitudCufd solicitudCufd = new SolicitudCufd();
        solicitudCufd.setCodigoAmbiente(empresa.getAmbiente());
        solicitudCufd.setCodigoModalidad(empresa.getModalidad());
        solicitudCufd.setCodigoSistema(empresa.getCodigosistema());
        solicitudCufd.setNit(empresa.getNit());
        solicitudCufd.setCuis(cui.getCui());
        solicitudCufd.setCodigoSucursal(sucursal.getSucursal());
        solicitudCufd.setCodigoPuntoVenta(puntoventa.getPuntoventa());

        RespuestaCufd respuestaCufd = this.servicioFacturacionCodigos.cufd(solicitudCufd);

        LocalDateTime fechavigencia=null;
        Boolean vigencia=null;
        List<Respuestas> mensajes = new ArrayList<>();
        if(!respuestaCufd.getTransaccion() && respuestaCufd.getMensajesList() != null) {
            for (MensajeServicio mensajeServicio : respuestaCufd.getMensajesList()) {
                Respuestas mensaje = new Respuestas(mensajeServicio.getCodigo(), mensajeServicio.getDescripcion());
                mensajes.add(mensaje);
            }
        }
        Date fecha =  respuestaCufd.getFechaVigencia().getTime();
        fechavigencia =  fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        vigencia = Boolean.TRUE;
        return new Respuestacufds(respuestaCufd.getCodigo(), respuestaCufd.getCodigoControl(), respuestaCufd.getDireccion(), fechavigencia, vigencia, mensajes);
    }

    @Override
    public List<Respuestas> registrarCUFDS(Long idpuntoventa) throws RemoteException {

        Puntosventas puntoventa = puntosventasRepositorio.obtenerPorId(idpuntoventa);
        Sucursales sucursal = sucursalesRepositorio.obtenerPorId(puntoventa.getIdsucursal());
        Empresas empresa = empresasRepositorio.obtenerPorId(sucursal.getIdempresa());
        Cuis cui = cuisRepositorio.obtenerPorIdpuntoventa(puntoventa.getIdpuntoventa());

        SolicitudCufd solicitudCufd = new SolicitudCufd();
        solicitudCufd.setCodigoAmbiente(empresa.getAmbiente());
        solicitudCufd.setCodigoModalidad(empresa.getModalidad());
        solicitudCufd.setCodigoSistema(empresa.getCodigosistema());
        solicitudCufd.setNit(empresa.getNit());
        solicitudCufd.setCuis(cui.getCui());
        solicitudCufd.setCodigoSucursal(sucursal.getSucursal());
        solicitudCufd.setCodigoPuntoVenta(puntoventa.getPuntoventa());

        RespuestaCufd respuestaCufd = this.servicioFacturacionCodigos.cufd(solicitudCufd);

        List<Respuestas> mensajes = new ArrayList<>();
        if(!respuestaCufd.getTransaccion() && respuestaCufd.getMensajesList() != null) {
            for (MensajeServicio mensajeServicio : respuestaCufd.getMensajesList()) {
                Respuestas mensaje = new Respuestas(mensajeServicio.getCodigo(), mensajeServicio.getDescripcion());
                mensajes.add(mensaje);
            }
        }

        LocalDateTime fechavigencia=null;
        Respuestasfechas ofechaservidor=null;
        LocalDateTime fechaservidor=null;

        try {
            ofechaservidor = obtenerFechahora(puntoventa.getIdpuntoventa());
            fechaservidor=LocalDateTime.parse(ofechaservidor.getFechahora());
        } catch (Exception e) {
            fechaservidor = LocalDateTime.now();
        }

        Date fecha =  respuestaCufd.getFechaVigencia().getTime();
        fechavigencia =  fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        Cufds verificarcufd = cufdsRepositorio.obtenerPorIdpuntoventa(puntoventa.getIdpuntoventa());
        if (verificarcufd==null) {
            verificarcufd = new Cufds();
            verificarcufd.setIdpuntoventa(puntoventa.getIdpuntoventa());
            verificarcufd.setCufd(respuestaCufd.getCodigo());
            verificarcufd.setCufdcontrol(respuestaCufd.getCodigoControl());
            verificarcufd.setFechainicio(fechaservidor);
            verificarcufd.setFechavigencia(fechavigencia);
            verificarcufd.setEstado(Boolean.TRUE);
            cufdsRepositorio.adicionar(verificarcufd);
        }

        Cufds cufd = cufdsRepositorio.obtenerPorIdpuntoventa(puntoventa.getIdpuntoventa());
        cufd.setEstado(Boolean.FALSE);
        cufdsRepositorio.modificar(cufd);

        cufd = new Cufds();
        cufd.setIdpuntoventa(puntoventa.getIdpuntoventa());
        cufd.setCufd(respuestaCufd.getCodigo());
        cufd.setCufdcontrol(respuestaCufd.getCodigoControl());
        cufd.setFechainicio(fechaservidor);
        cufd.setFechavigencia(fechavigencia);
        cufd.setEstado(Boolean.TRUE);
        cufdsRepositorio.adicionar(cufd);

        return mensajes;
    }

    @Override
    public RespuestasFacturas emitirFactura(Long idfactura) throws RemoteException, Exception {

        Facturas efactura = facturasRepositorio.obtenerPorId(idfactura);

        Puntosventas puntoventa = puntosventasRepositorio.obtenerPorId(efactura.getIdpuntoventa());

        Respuestasfechas fecha = null;
        LocalDateTime fechaemision = null;
        try {
            fecha = obtenerFechahora(puntoventa.getIdpuntoventa());
            fechaemision = LocalDateTime.parse(fecha.getFechahora());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (fecha==null) {
            fechaemision = LocalDateTime.now();
        }

        Sucursales sucursal = sucursalesRepositorio.obtenerPorId(puntoventa.getIdsucursal());
        Empresas empresa = empresasRepositorio.obtenerPorId(sucursal.getIdempresa());
        Cuis cui = cuisRepositorio.obtenerPorIdpuntoventa(puntoventa.getIdpuntoventa());


        Cufds cufd = cufdsRepositorio.obtenerPorIdpuntoventa(puntoventa.getIdpuntoventa());

        FacturasXML factura = this.llenarDatos(idfactura, puntoventa.getIdpuntoventa(), fechaemision);
        byte[] xmlComprimidoZip = facturasUtilitarios.obtenerArchivo(factura);
        String sha2 = Utilitarios.obtenerSHA2(xmlComprimidoZip);

        SolicitudRecepcionFactura solicitudRecepcionFactura = new SolicitudRecepcionFactura();
        solicitudRecepcionFactura.setCodigoAmbiente(empresa.getAmbiente());
        solicitudRecepcionFactura.setCodigoModalidad(empresa.getModalidad());
        solicitudRecepcionFactura.setCodigoPuntoVenta(puntoventa.getPuntoventa());
        solicitudRecepcionFactura.setCodigoSistema(empresa.getCodigosistema());
        solicitudRecepcionFactura.setCodigoSucursal(sucursal.getSucursal());
        solicitudRecepcionFactura.setNit(empresa.getNit());
        solicitudRecepcionFactura.setTipoFacturaDocumento(1);
        solicitudRecepcionFactura.setCodigoDocumentoSector(1);
        solicitudRecepcionFactura.setCodigoEmision(1);
        solicitudRecepcionFactura.setCufd(cufd.getCufd());
        solicitudRecepcionFactura.setCuis(cui.getCui());
        solicitudRecepcionFactura.setArchivo(xmlComprimidoZip);

        solicitudRecepcionFactura.setFechaEnvio(fechaemision.toString());
        solicitudRecepcionFactura.setHashArchivo(sha2);

        RespuestaRecepcion respuestaRecepcion = null;
        List<Respuestas> mensajes = new ArrayList<>();
        Facturas facturaactualizar = facturasRepositorio.obtenerPorId(idfactura);

        try {
            if (fecha!=null) {
                respuestaRecepcion = this.servicioFacturacion.recepcionFactura(solicitudRecepcionFactura);
                if(!respuestaRecepcion.getTransaccion() && respuestaRecepcion.getMensajesList() != null) {
                    for (MensajeServicio mensajeServicio : respuestaRecepcion.getMensajesList()) {
                        Respuestas mensaje = new Respuestas(mensajeServicio.getCodigo(), mensajeServicio.getDescripcion());
                        mensajes.add(mensaje);
                    }
                }

                RespuestasFacturas facturarespuesta = new RespuestasFacturas();
                facturarespuesta.setCodigoEstado(0);
                facturarespuesta.setCuf(factura.getCabecera().getCuf());
                facturarespuesta.setNumeroFactura(factura.getCabecera().getNumeroFactura());
                facturarespuesta.setMensajes(mensajes);

                facturaactualizar.setCuf(factura.getCabecera().getCuf());
                facturaactualizar.setNumerofactura(factura.getCabecera().getNumeroFactura());
                facturaactualizar.setCufd(cufd.getCufd());
                facturaactualizar.setFechaemision(fechaemision);
                Integer codigo = respuestaRecepcion.getCodigoEstado();

                facturaactualizar.setCodigo(codigo);

                String mensajesestado = "";
                for (Respuestas mensaje : mensajes) {
                    mensajesestado = mensajesestado + " " + mensaje.getDescripcion();
                }
                facturaactualizar.setEstado(respuestaRecepcion.getCodigoDescripcion()+" "+respuestaRecepcion.getCodigoRecepcion()+" "+mensajesestado);
                facturasRepositorio.modificar(facturaactualizar);

                if (respuestaRecepcion.getCodigoEstado()==908) {

                    facturasServicioPDF.guardaFacturaPDF(idfactura);

                    Clientes cliente = clientesRepositorio.obtenerPorId(facturaactualizar.getIdcliente());
                    if (cliente.getCorreoelectronico()!="") {

                        String correodestino=cliente.getCorreoelectronico();

                        Context context = new Context();
                        context.setVariable("nombreCompleto", cliente.getCliente());
                        context.setVariable("ruta", "sfida.com.bo");
                        context.setVariable("nit", cliente.getNumerodocumento());

                        Correos correo = new Correos();
                        correo.setCorreo(correodestino);
                        correo.setTema("Factura");
                        correo.setContenido(plantilla.process("facturas", context));
                        correo.setRutaDocAdjuntoXML(ruta+"/facturas/"+facturaactualizar.getCuf()+".xml");
                        correo.setNombreArchivoXML("FacturaXML");
                        correo.setRutaDocAdjuntoPDF(ruta+"/facturaspdf/"+facturaactualizar.getCuf()+".pdf");
                        correo.setNombreArchivoPDF("FacturaPDF");
                        try {
                            correosServicio.enviarCorreo(correo);
                        } catch (MessagingException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
                return facturarespuesta;
            }

            if (fecha==null) {
                RespuestasFacturas facturarespuesta = new RespuestasFacturas();
                facturarespuesta.setCodigoEstado(0);
                facturarespuesta.setCuf(factura.getCabecera().getCuf());
                facturarespuesta.setNumeroFactura(factura.getCabecera().getNumeroFactura());
                facturarespuesta.setMensajes(mensajes);

                facturaactualizar.setCuf(factura.getCabecera().getCuf());
                facturaactualizar.setNumerofactura(factura.getCabecera().getNumeroFactura());
                facturaactualizar.setCufd(cufd.getCufd());
                facturaactualizar.setFechaemision(fechaemision);
                Integer codigo = 9081;

                facturaactualizar.setCodigo(codigo);
                String mensajesestado = "";
                for (Respuestas mensaje : mensajes) {
                    mensajesestado = mensajesestado + " " + mensaje.getDescripcion();
                }
                facturaactualizar.setEstado("En espera de Aprobacion"+mensajesestado);
                facturasRepositorio.modificar(facturaactualizar);

                facturasServicioPDF.guardaFacturaPDF(idfactura);

                Clientes cliente = clientesRepositorio.obtenerPorId(facturaactualizar.getIdcliente());
                if (cliente.getCorreoelectronico()!="") {

                    String correodestino=cliente.getCorreoelectronico();

                    Context context = new Context();
                    context.setVariable("nombreCompleto", cliente.getCliente());
                    context.setVariable("ruta", "sfida.com.bo");
                    context.setVariable("nit", cliente.getNumerodocumento());

                    Correos correo = new Correos();
                    correo.setCorreo(correodestino);
                    correo.setTema("Factura");
                    correo.setContenido(plantilla.process("facturas", context));
                    correo.setRutaDocAdjuntoXML(ruta+"/facturas/"+facturaactualizar.getCuf()+".xml");
                    correo.setNombreArchivoXML("FacturaXML");
                    correo.setRutaDocAdjuntoPDF(ruta+"/facturaspdf/"+facturaactualizar.getCuf()+".pdf");
                    correo.setNombreArchivoPDF("FacturaPDF");
                    try {
                        correosServicio.enviarCorreo(correo);
                    } catch (MessagingException e1) {
                        e1.printStackTrace();
                    }
                }
                return facturarespuesta;
            }
        } catch (Exception e) {

            RespuestasFacturas facturarespuesta = new RespuestasFacturas();
            facturarespuesta.setCodigoEstado(0);
            facturarespuesta.setCuf(factura.getCabecera().getCuf());
            facturarespuesta.setNumeroFactura(factura.getCabecera().getNumeroFactura());
            facturarespuesta.setMensajes(mensajes);

            facturaactualizar.setCuf(factura.getCabecera().getCuf());
            facturaactualizar.setNumerofactura(factura.getCabecera().getNumeroFactura());
            facturaactualizar.setCufd(cufd.getCufd());
            facturaactualizar.setFechaemision(fechaemision);
            Integer codigo = respuestaRecepcion.getCodigoEstado();

            facturaactualizar.setCodigo(codigo);
            String mensajesestado = "";
            for (Respuestas mensaje : mensajes) {
                mensajesestado = mensajesestado + " " + mensaje.getDescripcion();
            }
            facturaactualizar.setEstado("Sin Conexion 0"+mensajesestado);
            facturasRepositorio.modificar(facturaactualizar);

            facturasServicioPDF.guardaFacturaPDF(idfactura);

            Clientes cliente = clientesRepositorio.obtenerPorId(facturaactualizar.getIdcliente());
            if (cliente.getCorreoelectronico()!="") {

                String correodestino=cliente.getCorreoelectronico();

                Context context = new Context();
                context.setVariable("nombreCompleto", cliente.getCliente());
                context.setVariable("ruta", "sfida.com.bo");
                context.setVariable("nit", cliente.getNumerodocumento());

                Correos correo = new Correos();
                correo.setCorreo(correodestino);
                correo.setTema("Factura");
                correo.setContenido(plantilla.process("facturas", context));
                correo.setRutaDocAdjuntoXML(ruta+"/facturas/"+facturaactualizar.getCuf()+".xml");
                correo.setNombreArchivoXML("FacturaXML");
                correo.setRutaDocAdjuntoPDF(ruta+"/facturaspdf/"+facturaactualizar.getCuf()+".pdf");
                correo.setNombreArchivoPDF("FacturaPDF");
                try {
                    correosServicio.enviarCorreo(correo);
                } catch (MessagingException e1) {
                    e1.printStackTrace();
                }
            }
            return facturarespuesta;
        }
        return null;
    }

    public FacturasXML llenarDatos(Long idfactura, Long idpuntoventa, LocalDateTime fechaemision) throws DatatypeConfigurationException {

        Puntosventas puntoventa = puntosventasRepositorio.obtenerPorId(idpuntoventa);
        Cufds cufd = cufdsRepositorio.obtenerPorIdpuntoventa(puntoventa.getIdpuntoventa());

        Sucursales sucursal = sucursalesRepositorio.obtenerPorId(puntoventa.getIdsucursal());
        Empresas empresa = empresasRepositorio.obtenerPorId(sucursal.getIdempresa());

        PuntosventasOTD puntoventaOTD = new PuntosventasOTD();
        puntoventaOTD.setNit(empresa.getNit());
        puntoventaOTD.setRazonsocial(empresa.getRazonsocial());
        puntoventaOTD.setSucursal(sucursal.getSucursal());
        puntoventaOTD.setDireccion(sucursal.getDireccion());
        puntoventaOTD.setPuntoventa(puntoventa.getPuntoventa());
        puntoventaOTD.setMunicipio(sucursal.getMunicipio());
        puntoventaOTD.setTelefono(sucursal.getTelefono());

        DetallesXML detallexml = null;
        List<DetallesXML> detallesxml = new LinkedList<>();

        BigDecimal total = BigDecimal.ZERO;
        BigDecimal descuentos = BigDecimal.ZERO;

        Facturas factura = facturasRepositorio.obtenerPorId(idfactura);
        List<Detalles> detalles = detallesRepositorio.obtenerTodos(idfactura);

        for (Detalles detalle : detalles) {

            Productos producto = productosRepositorio.obtenerPorId(detalle.getIdproducto());
            Unidadesmedidas unidadmedida = unidadesmedidasRepositorio.obtenerPorId(producto.getIdunidadmedida());
            Productossin productosin = productossinRepositorio.obtenerPorId(producto.getIdproductosin());

            ProductosOTD productootd = new ProductosOTD();
            productootd.setIdproducto(producto.getIdproducto());
            productootd.setCodigosin(productosin.getCodigosin());
            productootd.setProducto(producto.getProducto());
            productootd.setDescuento(detalle.getDescuento());
            productootd.setPreciounitario(producto.getPreciounitario());
            productootd.setUnidadmedidacodigosin(unidadmedida.getCodigosin());
            detallexml = new DetallesXML.Builder().buildItem(productootd, productosin.getCodigoactividadsin().toString(), detalle.getCantidad(), BigDecimal.ZERO).build();
            detallesxml.add(detallexml);
            total = total.add(detallexml.getSubTotal());
        }

        Clientes cliente = clientesRepositorio.obtenerPorId(factura.getIdcliente());
        Documentosidentidadessin documentossin = documentosidentidadessinRepositorio.obtenerPorId(cliente.getIddocumentoidentidadsin());
        ClientesOTD clienteotd = new ClientesOTD(cliente.getIdcliente(), documentossin.getIddocumentoidentidadsin(), documentossin.getCodigosin(), documentossin.getDocumentoidentidadsin(), cliente.getCliente(),cliente.getNumerodocumento(), cliente.getComplemento(), cliente.getCorreoelectronico(), cliente.getCelular(), cliente.getCodigoexcepcion());
        Usuarios usuario = usuariosRepositorio.obtenerPorId(factura.getIdusuario());

        Boolean Nitexcepcion = Boolean.FALSE;
        if (clienteotd.getNumerodocumento().trim().equals("99001") || clienteotd.getNumerodocumento().trim().equals("99002") || clienteotd.getNumerodocumento().trim().equals("99003")) {
            Nitexcepcion = Boolean.TRUE;
        }

        try {
            Boolean estado = false;
            RespuestaComunicacion respuestaComunicacion = this.servicioFacturacionOperaciones.verificarComunicacion();
            if (respuestaComunicacion.getMensajesList()[0].getCodigo().equals(926)) {
                estado = true;
            }

            if ( estado==false && documentossin.getCodigosin()==5){
                Nitexcepcion = Boolean.TRUE;
            }
        } catch (Exception e) {
            Nitexcepcion = Boolean.TRUE;
            e.printStackTrace();
        }

        if (clienteotd.getCodigoexcepcion()==1) {
            Nitexcepcion = Boolean.TRUE;
        }

        String cafc = null;

        CabecerasXML cabeceraxml = new CabecerasXML.Builder()
                .buildEmpresa(puntoventaOTD)
                .buildCliente(clienteotd)
                .buildPago(total, descuentos)
                .setUsuario(usuario.getUsuario())
                .setNumeroFactura(factura.getNumerofactura())
                .setFechaEmision(Utilitarios.localDateTimeToXMLGregorianCalendar(fechaemision))
                .setLeyenda(factura.getLeyenda())
                .setNitClienteExcepcion(Nitexcepcion)
                .setCafc(cafc)
                .build();

        FacturasXML facturaxml = new FacturasXML();
        facturaxml.setCabecera(cabeceraxml);
        facturaxml.setDetalle(detallesxml);
        String cuf = facturasUtilitarios.obtenerCUF(facturaxml, cufd);
        facturaxml.getCabecera().setCufd(cufd.getCufd());
        facturaxml.getCabecera().setCuf(cuf);

        return facturaxml;
    };

    @Override
    public Long obtenerEventoSignificativo2(Long idpuntoventa) throws RemoteException {

        List<Facturas> facturas = facturasRepositorio.obtenerNoEnviados(idpuntoventa);
        Facturas fatuturacufdevento = facturas.get(0);

        Eventossignificativossin eventosignificativosin = eventossignificativossinRepositorio.obtenerPorId(5l);

        Puntosventas puntoventa = puntosventasRepositorio.obtenerPorId(idpuntoventa);
        Sucursales sucursal = sucursalesRepositorio.obtenerPorId(puntoventa.getIdsucursal());
        Empresas empresa = empresasRepositorio.obtenerPorId(sucursal.getIdempresa());
        Cuis cui = cuisRepositorio.obtenerPorIdpuntoventa(puntoventa.getIdpuntoventa());
        Cufds cufd = cufdsRepositorio.obtenerPorIdpuntoventa(puntoventa.getIdpuntoventa());

        String fechaini = facturasRepositorio.obtenerNoEnviadosmin(idpuntoventa).toString();
        String fechafin = facturasRepositorio.obtenerNoEnviadosmax(idpuntoventa).toString();

        Calendar fechainicial = null;
        Calendar fechafinal = null;
        if (!fechaini.isEmpty()) {
            fechainicial = Calendar.getInstance();
            try {
                fechainicial.setTime(parseDateString(fechaini));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (!fechafin.isEmpty()) {
            fechafinal = Calendar.getInstance();
            try {
                fechafinal.setTime(parseDateString(fechafin));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        SolicitudEventoSignificativo solicitudEventoSignificativo = new SolicitudEventoSignificativo();
        solicitudEventoSignificativo.setCodigoAmbiente(empresa.getAmbiente());
        solicitudEventoSignificativo.setCodigoSistema(empresa.getCodigosistema());
        solicitudEventoSignificativo.setCodigoSucursal(sucursal.getSucursal());
        solicitudEventoSignificativo.setNit(empresa.getNit());
        solicitudEventoSignificativo.setCuis(cui.getCui());
        solicitudEventoSignificativo.setCodigoPuntoVenta(puntoventa.getPuntoventa());
        solicitudEventoSignificativo.setCufd(cufd.getCufd());
        solicitudEventoSignificativo.setCufdEvento(fatuturacufdevento.getCufd());
        solicitudEventoSignificativo.setDescripcion(eventosignificativosin.getEventosignificativosin());
        solicitudEventoSignificativo.setCodigoMotivoEvento(eventosignificativosin.getCodigosin());
        solicitudEventoSignificativo.setFechaHoraInicioEvento(fechainicial);
        solicitudEventoSignificativo.setFechaHoraFinEvento(fechafinal);

        RespuestaListaEventos respuestasEventoSignificativo = this.servicioFacturacionOperaciones.registroEventoSignificativo(solicitudEventoSignificativo);

        List<Respuestas> mensajes = new ArrayList<>();
        if(!respuestasEventoSignificativo.getTransaccion() && respuestasEventoSignificativo.getMensajesList() != null) {
            for (MensajeServicio mensajeServicio : respuestasEventoSignificativo.getMensajesList()) {
                Respuestas mensaje = new Respuestas(mensajeServicio.getCodigo(), mensajeServicio.getDescripcion());
                mensajes.add(mensaje);
            }
        }
        return respuestasEventoSignificativo.getCodigoRecepcionEventoSignificativo();
    }

    @Override
    public Long obtenerEventoSignificativo567(Long ideventosignificativo) throws RemoteException {

        Authentication autentificado = SecurityContextHolder.getContext().getAuthentication();
        Long idpuntoventa = puntosventasRepositorio.obtenerPuntoventaAsignado(Long.parseLong(autentificado.getName()));

        List<Facturas> facturas = facturasRepositorio.obtenerNoEnviadoso(idpuntoventa);
        Facturas fatuturacufdevento = facturas.get(0);

        Eventossignificativossin eventosignificativosin = eventossignificativossinRepositorio.obtenerPorId(ideventosignificativo);

        Puntosventas puntoventa = puntosventasRepositorio.obtenerPorId(idpuntoventa);
        Sucursales sucursal = sucursalesRepositorio.obtenerPorId(puntoventa.getIdsucursal());
        Empresas empresa = empresasRepositorio.obtenerPorId(sucursal.getIdempresa());
        Cuis cui = cuisRepositorio.obtenerPorIdpuntoventa(puntoventa.getIdpuntoventa());
        Cufds cufd = cufdsRepositorio.obtenerPorIdpuntoventa(puntoventa.getIdpuntoventa());

        String fechaini = facturasRepositorio.obtenerNoEnviadosmino(idpuntoventa).toString();
        String fechafin = facturasRepositorio.obtenerNoEnviadosmaxo(idpuntoventa).toString();

        Calendar fechainicial = null;
        Calendar fechafinal = null;
        if (!fechaini.isEmpty()) {
            fechainicial = Calendar.getInstance();
            try {
                fechainicial.setTime(parseDateString(fechaini));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (!fechafin.isEmpty()) {
            fechafinal = Calendar.getInstance();
            try {
                fechafinal.setTime(parseDateString(fechafin));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        SolicitudEventoSignificativo solicitudEventoSignificativo = new SolicitudEventoSignificativo();
        solicitudEventoSignificativo.setCodigoAmbiente(empresa.getAmbiente());
        solicitudEventoSignificativo.setCodigoSistema(empresa.getCodigosistema());
        solicitudEventoSignificativo.setCodigoSucursal(sucursal.getSucursal());
        solicitudEventoSignificativo.setNit(empresa.getNit());
        solicitudEventoSignificativo.setCuis(cui.getCui());
        solicitudEventoSignificativo.setCodigoPuntoVenta(puntoventa.getPuntoventa());
        solicitudEventoSignificativo.setCufd(cufd.getCufd());
        solicitudEventoSignificativo.setCufdEvento(fatuturacufdevento.getCufd());
        solicitudEventoSignificativo.setDescripcion(eventosignificativosin.getEventosignificativosin());
        solicitudEventoSignificativo.setCodigoMotivoEvento(eventosignificativosin.getCodigosin());
        solicitudEventoSignificativo.setFechaHoraInicioEvento(fechainicial);
        solicitudEventoSignificativo.setFechaHoraFinEvento(fechafinal);

        RespuestaListaEventos respuestasEventoSignificativo = this.servicioFacturacionOperaciones.registroEventoSignificativo(solicitudEventoSignificativo);

        List<Respuestas> mensajes = new ArrayList<>();
        if(!respuestasEventoSignificativo.getTransaccion() && respuestasEventoSignificativo.getMensajesList() != null) {
            for (MensajeServicio mensajeServicio : respuestasEventoSignificativo.getMensajesList()) {
                Respuestas mensaje = new Respuestas(mensajeServicio.getCodigo(), mensajeServicio.getDescripcion());
                mensajes.add(mensaje);
            }
        }
        return respuestasEventoSignificativo.getCodigoRecepcionEventoSignificativo();
    }

    private Date parseDateString(String dateString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        return dateFormat.parse(dateString);
    }

    @Override
    public RespuestasFacturas regularizarEventoInaccesibilidadSIAT(Long idpuntoventa, Long codigo) throws RemoteException, Exception {

        List<Path> rutas = new ArrayList<>();

        List<Facturas> facturas = facturasRepositorio.obtenerNoEnviados(idpuntoventa);

        File directorio = new File(ruta + "/envios/" +codigo.toString());
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                System.out.println("Directorio creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        }
        int contador = 0;
        for (Facturas factura : facturas) {
            Path origenRuta = FileSystems.getDefault().getPath("recursos/facturas/"+factura.getCuf()+".xml");
            Path destinoRuta = FileSystems.getDefault().getPath("recursos/envios/"+codigo.toString()+"/"+factura.getCuf()+".xml");
            try {
                Files.move(origenRuta, destinoRuta, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                System.err.println(e);
            }
            rutas.add(Paths.get("recursos/envios/"+codigo.toString()+"/"+factura.getCuf()+".xml"));
            contador = contador +1;
        }
        Path tarGZSalida = Paths.get("recursos/envios/"+codigo.toString()+"/"+"facturas.tar.gz");
        gzipUtilitarios.comprimirDirectorio(tarGZSalida, rutas);

        String sha2 = "";
        byte[] xml = null;
        try {
            xml = Files.readAllBytes(Paths.get("recursos/envios/"+codigo.toString()+"/"+"facturas.tar.gz"));
            sha2 = Utilitarios.obtenerSHA2(xml);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Puntosventas puntoventa = puntosventasRepositorio.obtenerPorId(idpuntoventa);
        Sucursales sucursal = sucursalesRepositorio.obtenerPorId(puntoventa.getIdsucursal());
        Empresas empresa = empresasRepositorio.obtenerPorId(sucursal.getIdempresa());
        Cuis cui = cuisRepositorio.obtenerPorIdpuntoventa(puntoventa.getIdpuntoventa());
        Cufds cufd = cufdsRepositorio.obtenerPorIdpuntoventa(puntoventa.getIdpuntoventa());
        Respuestasfechas fecha = obtenerFechahora(puntoventa.getIdpuntoventa());

        SolicitudRecepcionPaquete solicitudRecepcionPaquete = new SolicitudRecepcionPaquete();
        solicitudRecepcionPaquete.setCodigoAmbiente(empresa.getAmbiente());
        solicitudRecepcionPaquete.setCodigoModalidad(empresa.getModalidad());
        solicitudRecepcionPaquete.setCodigoPuntoVenta(puntoventa.getPuntoventa());
        solicitudRecepcionPaquete.setCodigoSistema(empresa.getCodigosistema());
        solicitudRecepcionPaquete.setCodigoSucursal(sucursal.getSucursal());
        solicitudRecepcionPaquete.setNit(empresa.getNit());
        solicitudRecepcionPaquete.setCodigoDocumentoSector(1);
        solicitudRecepcionPaquete.setCodigoEmision(2);
        solicitudRecepcionPaquete.setCufd(cufd.getCufd());
        solicitudRecepcionPaquete.setCuis(cui.getCui());
        solicitudRecepcionPaquete.setTipoFacturaDocumento(1);
        solicitudRecepcionPaquete.setCodigoEvento(codigo);

        solicitudRecepcionPaquete.setCantidadFacturas(contador);
        solicitudRecepcionPaquete.setCafc(empresa.getCafc());
        solicitudRecepcionPaquete.setArchivo(xml);
        solicitudRecepcionPaquete.setFechaEnvio(fecha.getFechahora());
        solicitudRecepcionPaquete.setHashArchivo(sha2);

        RespuestaRecepcion respuestaRecepcion = this.servicioFacturacion.recepcionPaqueteFactura(solicitudRecepcionPaquete);

        List<Respuestas> mensajes = new ArrayList<>();
        if(!respuestaRecepcion.getTransaccion() && respuestaRecepcion.getMensajesList() != null) {
            for (MensajeServicio mensajeServicio : respuestaRecepcion.getMensajesList()) {
                Respuestas mensaje = new Respuestas(mensajeServicio.getCodigo(), mensajeServicio.getDescripcion());
                mensajes.add(mensaje);
            }
        }

        SolicitudValidacionRecepcion solicitudValidacionRecepcion = new SolicitudValidacionRecepcion();
        solicitudValidacionRecepcion.setCodigoAmbiente(empresa.getAmbiente());
        solicitudValidacionRecepcion.setCodigoModalidad(empresa.getModalidad());
        solicitudValidacionRecepcion.setCodigoPuntoVenta(puntoventa.getPuntoventa());
        solicitudValidacionRecepcion.setCodigoSistema(empresa.getCodigosistema());
        solicitudValidacionRecepcion.setCodigoSucursal(sucursal.getSucursal());
        solicitudValidacionRecepcion.setNit(empresa.getNit());
        solicitudValidacionRecepcion.setCodigoDocumentoSector(1);
        solicitudValidacionRecepcion.setCodigoEmision(2);
        solicitudValidacionRecepcion.setCufd(cufd.getCufd());
        solicitudValidacionRecepcion.setCuis(cui.getCui());
        solicitudValidacionRecepcion.setTipoFacturaDocumento(1);
        solicitudValidacionRecepcion.setCodigoRecepcion(respuestaRecepcion.getCodigoRecepcion());

        RespuestaRecepcion respuestaVerificacion = this.servicioFacturacion.validacionRecepcionPaqueteFactura(solicitudValidacionRecepcion);

        mensajes = new ArrayList<>();
        if(!respuestaVerificacion.getTransaccion() && respuestaVerificacion.getMensajesList() != null) {
            for (MensajeServicio mensajeServicio : respuestaVerificacion.getMensajesList()) {
                Respuestas mensaje = new Respuestas(mensajeServicio.getCodigo(), mensajeServicio.getDescripcion());
                mensajes.add(mensaje);
            }
        }

        for (Facturas factura : facturas) {
            factura.setCodigo(respuestaVerificacion.getCodigoEstado());
            factura.setEstado(respuestaVerificacion.getCodigoDescripcion()+" "+respuestaVerificacion.getCodigoRecepcion());
            facturasRepositorio.modificar(factura);
        }

        RespuestasFacturas facturarespuesta = new RespuestasFacturas();
        facturarespuesta.setCodigoEstado(respuestaRecepcion.getCodigoEstado());
        facturarespuesta.setMensajes(mensajes);

        return facturarespuesta;
    }

    @Override
    public RespuestasFacturas regularizarEventoInaccesibilidadSIATo(Long codigo) throws RemoteException, Exception {

        Authentication autentificado = SecurityContextHolder.getContext().getAuthentication();
        Long idpuntoventa = puntosventasRepositorio.obtenerPuntoventaAsignado(Long.parseLong(autentificado.getName()));

        List<Path> rutas = new ArrayList<>();

        List<Facturas> facturas = facturasRepositorio.obtenerNoEnviadoso(idpuntoventa);

        File directorio = new File(ruta + "/envios/" +codigo.toString());
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                System.out.println("Directorio creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        }
        int contador = 0;
        for (Facturas factura : facturas) {
            Path origenRuta = FileSystems.getDefault().getPath("recursos/facturas/"+factura.getCuf()+".xml");
            Path destinoRuta = FileSystems.getDefault().getPath("recursos/envios/"+codigo.toString()+"/"+factura.getCuf()+".xml");
            try {
                Files.move(origenRuta, destinoRuta, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                System.err.println(e);
            }
            rutas.add(Paths.get("recursos/envios/"+codigo.toString()+"/"+factura.getCuf()+".xml"));
            contador = contador +1;
        }
        Path tarGZSalida = Paths.get("recursos/envios/"+codigo.toString()+"/"+"facturas.tar.gz");
        gzipUtilitarios.comprimirDirectorio(tarGZSalida, rutas);

        String sha2 = "";
        byte[] xml = null;
        try {
            xml = Files.readAllBytes(Paths.get("recursos/envios/"+codigo.toString()+"/"+"facturas.tar.gz"));
            sha2 = Utilitarios.obtenerSHA2(xml);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Puntosventas puntoventa = puntosventasRepositorio.obtenerPorId(idpuntoventa);
        Sucursales sucursal = sucursalesRepositorio.obtenerPorId(puntoventa.getIdsucursal());
        Empresas empresa = empresasRepositorio.obtenerPorId(sucursal.getIdempresa());
        Cuis cui = cuisRepositorio.obtenerPorIdpuntoventa(puntoventa.getIdpuntoventa());
        Cufds cufd = cufdsRepositorio.obtenerPorIdpuntoventa(puntoventa.getIdpuntoventa());
        Respuestasfechas fecha = obtenerFechahora(puntoventa.getIdpuntoventa());

        SolicitudRecepcionPaquete solicitudRecepcionPaquete = new SolicitudRecepcionPaquete();
        solicitudRecepcionPaquete.setCodigoAmbiente(empresa.getAmbiente());
        solicitudRecepcionPaquete.setCodigoModalidad(empresa.getModalidad());
        solicitudRecepcionPaquete.setCodigoPuntoVenta(puntoventa.getPuntoventa());
        solicitudRecepcionPaquete.setCodigoSistema(empresa.getCodigosistema());
        solicitudRecepcionPaquete.setCodigoSucursal(sucursal.getSucursal());
        solicitudRecepcionPaquete.setNit(empresa.getNit());
        solicitudRecepcionPaquete.setCodigoDocumentoSector(1);
        solicitudRecepcionPaquete.setCodigoEmision(2);
        solicitudRecepcionPaquete.setCufd(cufd.getCufd());
        solicitudRecepcionPaquete.setCuis(cui.getCui());
        solicitudRecepcionPaquete.setTipoFacturaDocumento(1);
        solicitudRecepcionPaquete.setCafc(puntoventa.getCafc());
        solicitudRecepcionPaquete.setCodigoEvento(codigo);

        solicitudRecepcionPaquete.setCantidadFacturas(contador);
        solicitudRecepcionPaquete.setCafc(empresa.getCafc());
        solicitudRecepcionPaquete.setArchivo(xml);
        solicitudRecepcionPaquete.setFechaEnvio(fecha.getFechahora());
        solicitudRecepcionPaquete.setHashArchivo(sha2);

        RespuestaRecepcion respuestaRecepcion = this.servicioFacturacion.recepcionPaqueteFactura(solicitudRecepcionPaquete);

        List<Respuestas> mensajes = new ArrayList<>();
        if(!respuestaRecepcion.getTransaccion() && respuestaRecepcion.getMensajesList() != null) {
            for (MensajeServicio mensajeServicio : respuestaRecepcion.getMensajesList()) {
                Respuestas mensaje = new Respuestas(mensajeServicio.getCodigo(), mensajeServicio.getDescripcion());
                mensajes.add(mensaje);
            }
        }

        SolicitudValidacionRecepcion solicitudValidacionRecepcion = new SolicitudValidacionRecepcion();
        solicitudValidacionRecepcion.setCodigoAmbiente(empresa.getAmbiente());
        solicitudValidacionRecepcion.setCodigoModalidad(empresa.getModalidad());
        solicitudValidacionRecepcion.setCodigoPuntoVenta(puntoventa.getPuntoventa());
        solicitudValidacionRecepcion.setCodigoSistema(empresa.getCodigosistema());
        solicitudValidacionRecepcion.setCodigoSucursal(sucursal.getSucursal());
        solicitudValidacionRecepcion.setNit(empresa.getNit());
        solicitudValidacionRecepcion.setCodigoDocumentoSector(1);
        solicitudValidacionRecepcion.setCodigoEmision(2);
        solicitudValidacionRecepcion.setCufd(cufd.getCufd());
        solicitudValidacionRecepcion.setCuis(cui.getCui());
        solicitudValidacionRecepcion.setTipoFacturaDocumento(1);
        solicitudValidacionRecepcion.setCodigoRecepcion(respuestaRecepcion.getCodigoRecepcion());

        RespuestaRecepcion respuestaVerificacion = this.servicioFacturacion.validacionRecepcionPaqueteFactura(solicitudValidacionRecepcion);

        mensajes = new ArrayList<>();
        if(!respuestaVerificacion.getTransaccion() && respuestaVerificacion.getMensajesList() != null) {
            for (MensajeServicio mensajeServicio : respuestaVerificacion.getMensajesList()) {
                Respuestas mensaje = new Respuestas(mensajeServicio.getCodigo(), mensajeServicio.getDescripcion());
                mensajes.add(mensaje);
            }
        }

        if (respuestaVerificacion.getCodigoEstado()==908) {
            for (Facturas factura : facturas) {
                factura.setCodigo(respuestaVerificacion.getCodigoEstado());
                factura.setEstado(respuestaVerificacion.getCodigoDescripcion()+" "+respuestaVerificacion.getCodigoRecepcion()+" REGULARIZADO Evento Significativo");
                facturasRepositorio.modificar(factura);
            }
        }


        RespuestasFacturas facturarespuesta = new RespuestasFacturas();
        facturarespuesta.setCodigoEstado(respuestaRecepcion.getCodigoEstado());
        facturarespuesta.setMensajes(mensajes);

        return facturarespuesta;
    }

    @Override
    public RespuestasFacturas anularFactura(Long idfactura, Long idanulacionssin, String motivoanulacion) throws RemoteException, Exception {

        Facturas factura = facturasRepositorio.obtenerPorId(idfactura);

        Anulacionessin anulacionsin = anulacionessinRepositorio .obtenerPorId(idanulacionssin);

        Puntosventas puntoventa = puntosventasRepositorio.obtenerPorId(factura.getIdpuntoventa());
        Sucursales sucursal = sucursalesRepositorio.obtenerPorId(puntoventa.getIdsucursal());
        Empresas empresa = empresasRepositorio.obtenerPorId(sucursal.getIdempresa());
        Cuis cui = cuisRepositorio.obtenerPorIdpuntoventa(puntoventa.getIdpuntoventa());
        Cufds cufd = cufdsRepositorio.obtenerPorIdpuntoventa(puntoventa.getIdpuntoventa());

        SolicitudAnulacion solicitudAnulacion = new SolicitudAnulacion();
        solicitudAnulacion.setCodigoAmbiente(empresa.getAmbiente());
        solicitudAnulacion.setCodigoModalidad(empresa.getModalidad());
        solicitudAnulacion.setCodigoPuntoVenta(puntoventa.getPuntoventa());
        solicitudAnulacion.setCodigoSistema(empresa.getCodigosistema());
        solicitudAnulacion.setCodigoSucursal(sucursal.getSucursal());
        solicitudAnulacion.setNit(empresa.getNit());
        solicitudAnulacion.setCodigoDocumentoSector(1);
        solicitudAnulacion.setCodigoEmision(1);
        solicitudAnulacion.setCuis(cui.getCui());
        solicitudAnulacion.setCufd(cufd.getCufd());
        solicitudAnulacion.setCuf(factura.getCuf());
        solicitudAnulacion.setTipoFacturaDocumento(1);
        solicitudAnulacion.setCodigoMotivo(anulacionsin.getCodigosin());

        RespuestaRecepcion respuestaRecepcion = this.servicioFacturacion.anulacionFactura(solicitudAnulacion);

        List<Respuestas> mensajes = new ArrayList<>();
        if(!respuestaRecepcion.getTransaccion() && respuestaRecepcion.getMensajesList() != null) {
            for (MensajeServicio mensajeServicio : respuestaRecepcion.getMensajesList()) {
                Respuestas mensaje = new Respuestas(mensajeServicio.getCodigo(), mensajeServicio.getDescripcion());
                mensajes.add(mensaje);
            }
        }

        RespuestasFacturas facturarespuesta = new RespuestasFacturas();
        facturarespuesta.setMensajes(mensajes);

        Facturas facturaactualizar = facturasRepositorio.obtenerPorId(idfactura);
        facturaactualizar.setIdpedido(0l);
        facturaactualizar.setCodigo(respuestaRecepcion.getCodigoEstado());
        facturaactualizar.setEstado(respuestaRecepcion.getCodigoDescripcion());
        facturasRepositorio.modificar(facturaactualizar);

        if (respuestaRecepcion.getCodigoEstado()==905) {

                createPdf(ruta+"/facturaspdf/"+facturaactualizar.getCuf()+".pdf", ruta+"/facturaspdf/"+facturaactualizar.getCuf()+"anulado.pdf");

                facturasServicioPDF.guardaFacturaPDF(idfactura);

                Clientes cliente = clientesRepositorio.obtenerPorId(facturaactualizar.getIdcliente());
                if (cliente.getCorreoelectronico()!="") {

                    String correodestino=cliente.getCorreoelectronico();

                    Context context = new Context();
                    context.setVariable("nombreCompleto", cliente.getCliente());
                    context.setVariable("nit", cliente.getNumerodocumento());
                    context.setVariable("ruta", "sfida.com.bo");
                    context.setVariable("motivosin", anulacionsin.getAnulacionsin());
                    context.setVariable("motivodescripcion", motivoanulacion);
                    context.setVariable("ruta", "sfida.com.bo");
                    context.setVariable("cuf", factura.getCuf());
                    context.setVariable("total", factura.getTotal());
                    context.setVariable("fecha", factura.getFechaemision());
                    context.setVariable("rutaverificacion", "https://siatrest.impuestos.gob.bo/consulta/QR?nit=120987022&cuf="+factura.getCuf()+"&numero="+factura.getNumerofactura()+"&t=1");
                    Correos correo = new Correos();
                    correo.setCorreo(correodestino);
                    correo.setTema("Anulacion de Factura");
                    correo.setContenido(plantilla.process("facturasanuladas", context));
                    correo.setRutaDocAdjuntoXML(ruta+"/facturas/"+facturaactualizar.getCuf()+".xml");
                    correo.setNombreArchivoXML("FacturaXML");
                    correo.setRutaDocAdjuntoPDF(ruta+"/facturaspdf/"+facturaactualizar.getCuf()+"anulado.pdf");
                    correo.setNombreArchivoPDF("FacturaPDF");
                    try {
                        correosServicio.enviarCorreo(correo);
                    } catch (MessagingException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        return facturarespuesta;
    }

    public void createPdf(String src, String dest) throws IOException {
        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdf = new PdfDocument(writer);

        PdfDocument originalPdf = new PdfDocument(new PdfReader(src));
        int numberOfPages = originalPdf.getNumberOfPages();

        for (int i = 1; i <= numberOfPages; i++) {
            pdf.addPage(originalPdf.getPage(i).copyTo(pdf));
        }

        PdfFont font = PdfFontFactory.createFont();
        Color anuladoColor = new DeviceRgb(255, 0, 0);

        float pageSizeX = pdf.getDefaultPageSize().getWidth();
        float pageSizeY = pdf.getDefaultPageSize().getHeight();

        Document documento = new Document(pdf, PageSize.LETTER);
        Paragraph borrador = new Paragraph("A N U L A D O").setFontSize(40).setTextAlignment(TextAlignment.CENTER).setFontColor(ColorConstants.GRAY);
        borrador.setFixedPosition(15, 150, 370);
        borrador.setRotationAngle(290);
        borrador.setOpacity(20f);
        borrador.setFontColor(ColorConstants.RED);
        documento.add(borrador);

        pdf.close();
        originalPdf.close();
        documento.close();
    }

    @Override
    public RespuestasFacturas reversionFactura(Long idfactura) throws RemoteException, Exception {
        return null;
    }

}
