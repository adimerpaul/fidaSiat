/**
 * ServicioFacturacionOperaciones.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package bo.gob.impuestos.siat;

public interface ServicioFacturacionOperaciones extends java.rmi.Remote {
    public bo.gob.impuestos.siat.RespuestaComunicacion verificarComunicacion() throws java.rmi.RemoteException;
    public bo.gob.impuestos.siat.RespuestaRegistroPuntoVenta registroPuntoVenta(bo.gob.impuestos.siat.SolicitudRegistroPuntoVenta solicitudRegistroPuntoVenta) throws java.rmi.RemoteException;
    public bo.gob.impuestos.siat.RespuestaPuntoVentaComisionista registroPuntoVentaComisionista(bo.gob.impuestos.siat.SolicitudPuntoVentaComisionista solicitudPuntoVentaComisionista) throws java.rmi.RemoteException;
    public bo.gob.impuestos.siat.RespuestaCierreSistemas cierreOperacionesSistema(bo.gob.impuestos.siat.SolicitudOperaciones solicitudOperaciones) throws java.rmi.RemoteException;
    public bo.gob.impuestos.siat.RespuestaListaEventos consultaEventoSignificativo(bo.gob.impuestos.siat.SolicitudConsultaEvento solicitudConsultaEvento) throws java.rmi.RemoteException;
    public bo.gob.impuestos.siat.RespuestaConsultaPuntoVenta consultaPuntoVenta(bo.gob.impuestos.siat.SolicitudConsultaPuntoVenta solicitudConsultaPuntoVenta) throws java.rmi.RemoteException;
    public bo.gob.impuestos.siat.RespuestaListaEventos registroEventoSignificativo(bo.gob.impuestos.siat.SolicitudEventoSignificativo solicitudEventoSignificativo) throws java.rmi.RemoteException;
    public bo.gob.impuestos.siat.RespuestaCierrePuntoVenta cierrePuntoVenta(bo.gob.impuestos.siat.SolicitudCierrePuntoVenta solicitudCierrePuntoVenta) throws java.rmi.RemoteException;
}
