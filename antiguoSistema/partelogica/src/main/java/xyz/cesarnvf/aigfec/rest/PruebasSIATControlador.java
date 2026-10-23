package xyz.cesarnvf.aigfec.rest;

import java.rmi.RemoteException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
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
import xyz.cesarnvf.aigfec.servicio.FuncionesSIATServicio;

@RestController
@RequestMapping("/api/v1/pruebasSIAT")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PruebasSIATControlador {

    FuncionesSIATServicio funcionesSIATServicio;

    @GetMapping("/verificarConexion")
    ResponseEntity<?> verificarConexion() throws RemoteException {
        return new ResponseEntity<List<Respuestas>>(funcionesSIATServicio.verificarConexion(), HttpStatus.OK);
    }

    @GetMapping("/obtenerCUI")
    ResponseEntity<?> obtenerCui(
        @RequestParam(name = "idpuntoventa", defaultValue = "0") Long idpuntoventa
    ) throws RemoteException {
        return new ResponseEntity<Respuestacuis>(funcionesSIATServicio.obtenerCUIS(idpuntoventa), HttpStatus.OK);
    }

    @GetMapping("/registrarPuntoventa")
    ResponseEntity<?> registrarPuntoventa(
        @RequestParam(name = "idpuntoventa", defaultValue = "1") Long idpuntoventa
    ) throws RemoteException {
        return new ResponseEntity<List<Respuestas>>(funcionesSIATServicio.registrarPuntoventa(idpuntoventa), HttpStatus.OK);
    }

    @GetMapping("/registrarCUIS")
    ResponseEntity<?> registrarCUIS(
        @RequestParam(name = "idpuntoventa", defaultValue = "1") Long idpuntoventa
    ) throws RemoteException {
        return new ResponseEntity<List<Respuestas>>(funcionesSIATServicio.registrarCUIS(idpuntoventa), HttpStatus.OK);
    }

    @GetMapping("/obtenerActividades")
    ResponseEntity<?> obtenerActividades(
        @RequestParam(name = "idpuntoventa", defaultValue = "0") Long idpuntoventa
    ) throws RemoteException {
        return new ResponseEntity<Respuestasactividades>(funcionesSIATServicio.obtenerActividades(idpuntoventa), HttpStatus.OK);
    }

    @GetMapping("/obtenerFechahora")
    ResponseEntity<?> obtenerFechahora(
        @RequestParam(name = "idpuntoventa", defaultValue = "0") Long idpuntoventa
    ) throws RemoteException {
        return new ResponseEntity<Respuestasfechas>(funcionesSIATServicio.obtenerFechahora(idpuntoventa), HttpStatus.OK);
    }

    @GetMapping("/obtenerActividadesSector")
    ResponseEntity<?> obtenerActividadesSector(
        @RequestParam(name = "puntoventa", defaultValue = "0") Integer puntoventa
    ) throws RemoteException {
        return new ResponseEntity<Respuestasactividadessector>(funcionesSIATServicio.obtenerActividadesSector(puntoventa), HttpStatus.OK);
    }

    @GetMapping("/obtenerLeyendas")
    ResponseEntity<?> obtenerLeyendas(
        @RequestParam(name = "idpuntoventa", defaultValue = "0") Long idpuntoventa
    ) throws RemoteException {
        return new ResponseEntity<Respuestasleyendas>(funcionesSIATServicio.obtenerLeyendas(idpuntoventa), HttpStatus.OK);
    }

    @GetMapping("/obtenerMensajesServicio")
    ResponseEntity<?> obtenerMensajesServicio(
        @RequestParam(name = "puntoventa", defaultValue = "0") Integer puntoventa
    ) throws RemoteException {
        return new ResponseEntity<Respuestasmensajesservicio>(funcionesSIATServicio.obtenerMensajesServicio(puntoventa), HttpStatus.OK);
    }

    @GetMapping("/obtenerProductos")
    ResponseEntity<?> obtenerProductosServicio(
        @RequestParam(name = "idpuntoventa", defaultValue = "0") Long idpuntoventa
    ) throws RemoteException {
        return new ResponseEntity<Respuestasproductos>(funcionesSIATServicio.obtenerProductos(idpuntoventa), HttpStatus.OK);
    }

    @GetMapping("/obtenerEventosSignificativos")
    ResponseEntity<?> obtenerEventosSignificativos(
        @RequestParam(name = "idpuntoventa", defaultValue = "0") Long idpuntoventa
    ) throws RemoteException {
        return new ResponseEntity<Respuestaseventossignificativos>(funcionesSIATServicio.obtenerEventosSignificativos(idpuntoventa), HttpStatus.OK);
    }

    @GetMapping("/obtenerMotivoAnulacion")
    ResponseEntity<?> obtenerMotivoAnulacion(
        @RequestParam(name = "puntoventa", defaultValue = "0") Integer puntoventa
    ) throws RemoteException {
        return new ResponseEntity<Respuestasmotivoanulacion>(funcionesSIATServicio.obtenerMotivoAnulacion(puntoventa), HttpStatus.OK);
    }

    @GetMapping("/obtenerPaises")
    ResponseEntity<?> obtenerPaises(
        @RequestParam(name = "puntoventa", defaultValue = "0") Integer puntoventa
    ) throws RemoteException {
        return new ResponseEntity<Respuestaspaises>(funcionesSIATServicio.obtenerPaises(puntoventa), HttpStatus.OK);
    }

    @GetMapping("/obtenerTipoDocumentoIdentidad")
    ResponseEntity<?> obtenerTipoDocumentoIdentidad(
        @RequestParam(name = "idpuntoventa", defaultValue = "0") Long idpuntoventa
    ) throws RemoteException {
        return new ResponseEntity<Respuestastiposdocumentosidentidad>(funcionesSIATServicio.obtenerTipoDocumentoIdentidad(idpuntoventa), HttpStatus.OK);
    }

    @GetMapping("/obtenerTipoDocumentosector")
    ResponseEntity<?> obtenerTipoDocumentosector(
        @RequestParam(name = "puntoventa", defaultValue = "0") Integer puntoventa
    ) throws RemoteException {
        return new ResponseEntity<Respuestastiposdocumentossector>(funcionesSIATServicio.obtenerTipoDocumentosector(puntoventa), HttpStatus.OK);
    }

    @GetMapping("/obtenerTipoEmision")
    ResponseEntity<?> obtenerTipoEmision(
        @RequestParam(name = "puntoventa", defaultValue = "0") Integer puntoventa
    ) throws RemoteException {
        return new ResponseEntity<Respuestastiposemision>(funcionesSIATServicio.obtenerTipoEmision(puntoventa), HttpStatus.OK);
    }

    @GetMapping("/obtenerTipoHabitacion")
    ResponseEntity<?> obtenerTipoHabitacion(
        @RequestParam(name = "puntoventa", defaultValue = "0") Integer puntoventa
    ) throws RemoteException {
        return new ResponseEntity<Respuestastiposhabitacion>(funcionesSIATServicio.obtenerTipoHabitacion(puntoventa), HttpStatus.OK);
    }

    @GetMapping("/obtenerTipoMetodoPago")
    ResponseEntity<?> obtenerTipoMetodoPago(
        @RequestParam(name = "idpuntoventa", defaultValue = "0") Long idpuntoventa
    ) throws RemoteException {
        return new ResponseEntity<Respuestastiposmetodopago>(funcionesSIATServicio.obtenerTipoMetodoPago(idpuntoventa), HttpStatus.OK);
    }

    @GetMapping("/obtenerTipoMoneda")
    ResponseEntity<?> obtenerTipoMoneda(
        @RequestParam(name = "puntoventa", defaultValue = "0") Integer puntoventa
    ) throws RemoteException {
        return new ResponseEntity<Respuestastiposmoneda>(funcionesSIATServicio.obtenerTipoMoneda(puntoventa), HttpStatus.OK);
    }

    @GetMapping("/obtenerTipoPuntoVenta")
    ResponseEntity<?> obtenerTipoPuntoVenta(
        @RequestParam(name = "puntoventa", defaultValue = "0") Integer puntoventa
    ) throws RemoteException {
        return new ResponseEntity<Respuestastipospuntoventa>(funcionesSIATServicio.obtenerTipoPuntoVenta(puntoventa), HttpStatus.OK);
    }

    @GetMapping("/obtenerTipoFactura")
    ResponseEntity<?> obtenerTipoFactura(
        @RequestParam(name = "puntoventa", defaultValue = "0") Integer puntoventa
    ) throws RemoteException {
        return new ResponseEntity<Respuestastiposfactura>(funcionesSIATServicio.obtenerTipoFactura(puntoventa), HttpStatus.OK);
    }

    @GetMapping("/obtenerTipoUnidadMedida")
    ResponseEntity<?> obtenerTipoUnidadMedida(
        @RequestParam(name = "puntoventa", defaultValue = "0") Integer puntoventa
    ) throws RemoteException {
        return new ResponseEntity<Respuestastiposunidadmedida>(funcionesSIATServicio.obtenerTipoUnidadMedida(puntoventa), HttpStatus.OK);
    }

    @GetMapping("/obtenerCUFDS")
    ResponseEntity<?> obtenerCUFDS(
        @RequestParam(name = "idpuntoventa", defaultValue = "0") Long idpuntoventa
    ) throws RemoteException {
        return new ResponseEntity<Respuestacufds>(funcionesSIATServicio.obtenerCUFDS(idpuntoventa), HttpStatus.OK);
    }

    @GetMapping("/registrarCUFDS")
    ResponseEntity<?> registrarCUFDS(
        @RequestParam(name = "idpuntoventa", defaultValue = "1") Long idpuntoventa
    ) throws RemoteException {
        return new ResponseEntity<List<Respuestas>>(funcionesSIATServicio.registrarCUFDS(idpuntoventa), HttpStatus.OK);
    }

    @GetMapping("/emitirFactura")
    ResponseEntity<?> emitirFactura(
        @RequestParam(name = "idpuntoventa", defaultValue = "1") Long idpuntoventa
    ) throws Exception {
        return new ResponseEntity<RespuestasFacturas>(funcionesSIATServicio.emitirFactura(idpuntoventa), HttpStatus.OK);
    }

    @GetMapping("/obtenerEventoSignificativo")
    ResponseEntity<?> obtenerEventoSignificativo(
        @RequestParam(name = "ideventosignificativo", defaultValue = "1") Long ideventosignificativo
    ) throws Exception {
        return new ResponseEntity<Long>(funcionesSIATServicio.obtenerEventoSignificativo567(ideventosignificativo), HttpStatus.OK);
    }

    @GetMapping("/regularizarEventoInaccesibilidadSIATo")
    ResponseEntity<?> regularizarEventoInaccesibilidadSIATo(
        @RequestParam(name = "codigo", defaultValue = "1") Long codigo
    ) throws Exception {
        return new ResponseEntity<RespuestasFacturas>(funcionesSIATServicio.regularizarEventoInaccesibilidadSIATo( codigo), HttpStatus.OK);
    }

    @GetMapping("/anularFactura")
    ResponseEntity<?> anularFactura(
        @RequestParam(name = "idfactura", defaultValue = "1") Long idfactura,
        @RequestParam(name = "idanulacionsin", defaultValue = "1") Long idanulacionsin,
        @RequestParam(name = "motivoanulacion", defaultValue = "") String motivoanulacion
    ) throws Exception {
        return new ResponseEntity<RespuestasFacturas>(funcionesSIATServicio.anularFactura(idfactura, idanulacionsin, motivoanulacion), HttpStatus.OK);
    }

}
