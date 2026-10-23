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
import xyz.cesarnvf.aigfec.modelo.Documentosidentidadessin;
import xyz.cesarnvf.aigfec.servicio.DocumentosidentidadessinServicio;

@RestController
@RequestMapping("/api/v1/documentosidentidadessin")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DocumentosidentidadessinControlador {

    DocumentosidentidadessinServicio documentosidentidadessinServicio;

    @GetMapping("/todos")
    ResponseEntity<?> obtenerTodos() {
        return new ResponseEntity<List<Documentosidentidadessin>>(documentosidentidadessinServicio.obtenerTodos(), HttpStatus.OK);
    }

    @GetMapping
    ResponseEntity<?> obtenerPorTermino(
        @RequestParam(name = "termino", defaultValue = "") String termino,
        @RequestParam(name = "pagina", defaultValue = "0") Integer pagina,
        @RequestParam(name = "cantidad", defaultValue = "5") Integer cantidad) {
        return new ResponseEntity<List<Documentosidentidadessin>>(documentosidentidadessinServicio.obtenerPorTerminoConPaginacion(termino, pagina, cantidad), HttpStatus.OK);
    }

    @GetMapping("/cantidad")
    ResponseEntity<?> cantidad(
        @RequestParam(name = "termino", defaultValue = "") String termino) {
        return new ResponseEntity<Integer>(documentosidentidadessinServicio.obtenerCantidad(termino), HttpStatus.OK);
    }

    @GetMapping("/obtenerCodigosSin")
    ResponseEntity<?> obtenerDocumentosidentidadessin(
        @RequestParam(name = "idpuntoventa", defaultValue = "0") Long idpuntoventa
    ) throws RemoteException {
        documentosidentidadessinServicio.obtenerCodigosSin(idpuntoventa);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
