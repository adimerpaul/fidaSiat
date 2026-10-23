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
import xyz.cesarnvf.aigfec.modelo.Actividadessin;
import xyz.cesarnvf.aigfec.servicio.ActividadessinServicio;

@RestController
@RequestMapping("/api/v1/actividadessin")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ActividadessinControlador {

    ActividadessinServicio actividadessinServicio;

    @GetMapping("/todos")
    ResponseEntity<?> obtenerTodos() {
        return new ResponseEntity<List<Actividadessin>>(actividadessinServicio.obtenerTodos(), HttpStatus.OK);
    }

    @GetMapping
    ResponseEntity<?> obtenerPorTermino(
        @RequestParam(name = "termino", defaultValue = "") String termino,
        @RequestParam(name = "pagina", defaultValue = "0") Integer pagina,
        @RequestParam(name = "cantidad", defaultValue = "5") Integer cantidad) {
        return new ResponseEntity<List<Actividadessin>>(actividadessinServicio.obtenerPorTerminoConPaginacion(termino, pagina, cantidad), HttpStatus.OK);
    }

    @GetMapping("/cantidad")
    ResponseEntity<?> cantidad(
        @RequestParam(name = "termino", defaultValue = "") String termino) {
        return new ResponseEntity<Integer>(actividadessinServicio.obtenerCantidad(termino), HttpStatus.OK);
    }

    @GetMapping("/obtenerCodigosSin")
    ResponseEntity<?> obtenerActividadessin(
        @RequestParam(name = "idpuntoventa", defaultValue = "0") Long idpuntoventa
    ) throws RemoteException {
        actividadessinServicio.obtenerCodigosSin(idpuntoventa);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
