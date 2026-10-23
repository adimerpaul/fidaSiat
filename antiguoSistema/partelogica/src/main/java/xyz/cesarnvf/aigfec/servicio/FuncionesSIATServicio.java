package xyz.cesarnvf.aigfec.servicio;

import java.rmi.RemoteException;
import java.util.List;

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

public interface FuncionesSIATServicio {

    List<Respuestas> verificarConexion() throws RemoteException;

    Integer verificarNIT(Long idsucursal, Long nit) throws RemoteException;

    // Etapa Uno

    Respuestacuis obtenerCUIS(Long idpuntoventa) throws RemoteException;

    List<Respuestas> registrarPuntoventa(Long idpuntoventa) throws RemoteException;

    List<Respuestas> registrarCUIS(Long idpuntoventa) throws RemoteException;

    // Etapa Dos

    Respuestasactividades obtenerActividades(Long idpuntoventa) throws RemoteException;

    Respuestasfechas obtenerFechahora(Long idpuntoventa) throws RemoteException;

    Respuestasactividadessector obtenerActividadesSector(Integer epuntoventa) throws RemoteException;

    Respuestasleyendas obtenerLeyendas(Long eidpuntoventa) throws RemoteException;

    Respuestasmensajesservicio obtenerMensajesServicio(Integer epuntoventa) throws RemoteException;

    Respuestasproductos obtenerProductos(Long eidpuntoventa) throws RemoteException;

    Respuestaseventossignificativos obtenerEventosSignificativos(Long eidpuntoventa) throws RemoteException;

    Respuestasmotivoanulacion obtenerMotivoAnulacion(Integer epuntoventa) throws RemoteException;

    Respuestaspaises obtenerPaises(Integer epuntoventa) throws RemoteException;

    Respuestastiposdocumentosidentidad obtenerTipoDocumentoIdentidad(Long eidpuntoventa) throws RemoteException;

    Respuestastiposdocumentossector obtenerTipoDocumentosector(Integer epuntoventa) throws RemoteException;

    Respuestastiposemision obtenerTipoEmision(Integer epuntoventa) throws RemoteException;

    Respuestastiposhabitacion obtenerTipoHabitacion(Integer epuntoventa) throws RemoteException;

    Respuestastiposmetodopago obtenerTipoMetodoPago(Long eidpuntoventa) throws RemoteException;

    Respuestastiposmoneda obtenerTipoMoneda(Integer epuntoventa) throws RemoteException;

    Respuestastipospuntoventa obtenerTipoPuntoVenta(Integer epuntoventa) throws RemoteException;

    Respuestastiposfactura obtenerTipoFactura(Integer epuntoventa) throws RemoteException;

    Respuestastiposunidadmedida obtenerTipoUnidadMedida(Integer epuntoventa) throws RemoteException;

    // Etapa Tres

    Respuestacufds obtenerCUFDS(Long idpuntoventa) throws RemoteException;

    List<Respuestas> registrarCUFDS(Long idpuntoventa) throws RemoteException;

    // Etapa Cuatro

    RespuestasFacturas emitirFactura(Long idfactura) throws RemoteException, Exception;

    // Etapa Quinta

    Long obtenerEventoSignificativo2(Long idpuntoventa) throws RemoteException;

    Long obtenerEventoSignificativo567(Long ideventosignificativo) throws RemoteException;

    // Etapa Sexta

    RespuestasFacturas regularizarEventoInaccesibilidadSIAT(Long idpuntoventa, Long codigo)
            throws RemoteException, Exception;

    RespuestasFacturas regularizarEventoInaccesibilidadSIATo(Long codigo) throws RemoteException, Exception;

    // Etapa Septima

    RespuestasFacturas anularFactura(Long idfactura, Long idanulacionssin, String motivoanulacion) throws RemoteException, Exception;

    RespuestasFacturas reversionFactura(Long idfactura) throws RemoteException, Exception;

}
