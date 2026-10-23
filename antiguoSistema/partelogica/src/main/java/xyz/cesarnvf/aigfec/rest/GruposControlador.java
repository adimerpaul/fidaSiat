package xyz.cesarnvf.aigfec.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import xyz.cesarnvf.aigfec.modelo.Grupos;
import xyz.cesarnvf.aigfec.servicio.GruposServicio;

@RestController
@RequestMapping("/api/v1/grupos")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class GruposControlador {

    GruposServicio gruposServicio;

    @GetMapping("/todos")
    ResponseEntity<?> obtenerTodos() {
        return new ResponseEntity<List<Grupos>>(gruposServicio.obtenerTodos(), HttpStatus.OK);
    }

    @GetMapping("/todosPorTipo")
    ResponseEntity<?> obtenerTodosPorTipo(
        @RequestParam(name = "tipo", defaultValue = "0") Integer tipo
    ) {
        return new ResponseEntity<List<Grupos>>(gruposServicio.obtenerTodosPorTipo(tipo), HttpStatus.OK);
    }

    @GetMapping
    ResponseEntity<?> obtenerPorTermino(
        @RequestParam(name = "termino", defaultValue = "") String termino,
        @RequestParam(name = "pagina", defaultValue = "0") Integer pagina,
        @RequestParam(name = "cantidad", defaultValue = "5") Integer cantidad) {
        return new ResponseEntity<List<Grupos>>(gruposServicio.obtenerPorTerminoConPaginacion(termino, pagina, cantidad), HttpStatus.OK);
    }

    @GetMapping("/cantidad")
    ResponseEntity<?> cantidad(
        @RequestParam(name = "termino", defaultValue = "") String termino) {
        return new ResponseEntity<Integer>(gruposServicio.obtenerCantidad(termino), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<?> dato(@PathVariable Long id) {
        return new ResponseEntity<Grupos>(gruposServicio.obtenerPorId(id), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<?> adicionar(@RequestBody Grupos dato) {
        gruposServicio.adicionar(dato);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    ResponseEntity<?> modificar(@RequestBody Grupos dato) {
        gruposServicio.modificar(dato);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
