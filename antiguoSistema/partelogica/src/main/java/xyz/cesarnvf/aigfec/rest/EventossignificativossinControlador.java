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
import xyz.cesarnvf.aigfec.modelo.Eventossignificativossin;
import xyz.cesarnvf.aigfec.servicio.EventossignificativossinServicio;

@RestController
@RequestMapping("/api/v1/eventossignificativossin")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EventossignificativossinControlador {

    EventossignificativossinServicio eventossignificativossinServicio;

    @GetMapping("/todos")
    ResponseEntity<?> obtenerTodos() {
        return new ResponseEntity<List<Eventossignificativossin>>(eventossignificativossinServicio.obtenerTodos(), HttpStatus.OK);
    }

    @GetMapping
    ResponseEntity<?> obtenerPorTermino(
        @RequestParam(name = "termino", defaultValue = "") String termino,
        @RequestParam(name = "pagina", defaultValue = "0") Integer pagina,
        @RequestParam(name = "cantidad", defaultValue = "5") Integer cantidad) {
        return new ResponseEntity<List<Eventossignificativossin>>(eventossignificativossinServicio.obtenerPorTerminoConPaginacion(termino, pagina, cantidad), HttpStatus.OK);
    }

    @GetMapping("/cantidad")
    ResponseEntity<?> cantidad(
        @RequestParam(name = "termino", defaultValue = "") String termino) {
        return new ResponseEntity<Integer>(eventossignificativossinServicio.obtenerCantidad(termino), HttpStatus.OK);
    }

    @GetMapping("/obtenerCodigosSin")
    ResponseEntity<?> obtenerEventossignificativossin(
        @RequestParam(name = "idpuntoventa", defaultValue = "0") Long idpuntoventa
    ) throws RemoteException {
        eventossignificativossinServicio.obtenerCodigosSin(idpuntoventa);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}

