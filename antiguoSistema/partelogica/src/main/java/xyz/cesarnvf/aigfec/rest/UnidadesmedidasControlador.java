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
import xyz.cesarnvf.aigfec.modelo.Unidadesmedidas;
import xyz.cesarnvf.aigfec.servicio.UnidadesmedidasServicio;

@RestController
@RequestMapping("/api/v1/unidadesmedidas")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UnidadesmedidasControlador {

    UnidadesmedidasServicio unidadesmedidasServicio;

    @GetMapping("/todos")
    ResponseEntity<?> obtenerTodos() {
        return new ResponseEntity<List<Unidadesmedidas>>(unidadesmedidasServicio.obtenerTodos(), HttpStatus.OK);
    }

    @GetMapping("/obtenerCodigosSin")
    ResponseEntity<?> obtenerUnidadesmedidas(
        @RequestParam(name = "puntoventa", defaultValue = "0") Integer puntoventa
    ) throws RemoteException {
        unidadesmedidasServicio.obtenerCodigosSin(puntoventa);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
