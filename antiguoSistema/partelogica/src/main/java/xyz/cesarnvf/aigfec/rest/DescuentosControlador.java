package xyz.cesarnvf.aigfec.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import xyz.cesarnvf.aigfec.modelo.otd.DescuentosOTD;
import xyz.cesarnvf.aigfec.servicio.DescuentosServicio;

@RestController
@RequestMapping("/api/v1/descuentos")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DescuentosControlador {

    DescuentosServicio descuentosServicio;

    @GetMapping("/todos")
    ResponseEntity<?> obtenerTodos() {
        return new ResponseEntity<List<DescuentosOTD>>(descuentosServicio.obtenerTodos(), HttpStatus.OK);
    }

    @GetMapping
    ResponseEntity<?> obtenerPorTermino(
        @RequestParam(name = "termino", defaultValue = "") String termino,
        @RequestParam(name = "pagina", defaultValue = "0") Integer pagina,
        @RequestParam(name = "cantidad", defaultValue = "5") Integer cantidad) {
        return new ResponseEntity<List<DescuentosOTD>>(descuentosServicio.obtenerPorTerminoConPaginacion(termino, pagina, cantidad), HttpStatus.OK);
    }

    @GetMapping("/cantidad")
    ResponseEntity<?> cantidad(
        @RequestParam(name = "termino", defaultValue = "") String termino) {
        return new ResponseEntity<Integer>(descuentosServicio.obtenerCantidad(termino), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<?> dato(@PathVariable Long id) {
        return new ResponseEntity<DescuentosOTD>(descuentosServicio.obtenerPorId(id), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<?> adicionar(@RequestBody DescuentosOTD dato) {
        descuentosServicio.adicionar(dato);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    ResponseEntity<?> modificar(@RequestBody DescuentosOTD dato) {
        descuentosServicio.modificar(dato);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> borrar(@PathVariable Long id) {
        descuentosServicio.borrar(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
