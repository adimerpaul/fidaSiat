package xyz.cesarnvf.aigfec.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import xyz.cesarnvf.aigfec.modelo.Anulacionessin;
import xyz.cesarnvf.aigfec.servicio.AnulacionessinServicio;

@RestController
@RequestMapping("/api/v1/anulacionessin")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AnulacionessinControlador {

    AnulacionessinServicio anulacionessinServicio;

    @GetMapping("/todos")
    ResponseEntity<?> obtenerTodos() {
        return new ResponseEntity<List<Anulacionessin>>(anulacionessinServicio.obtenerTodos(), HttpStatus.OK);
    }

}
