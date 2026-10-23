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
import xyz.cesarnvf.aigfec.modelo.Sucursales;
import xyz.cesarnvf.aigfec.servicio.SucursalesServicio;

@RestController
@RequestMapping("/api/v1/sucursales")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SucursalesControlador {

    SucursalesServicio sucursalesServicio;

    @GetMapping("/todos")
    ResponseEntity<?> obtenerTodos(
        @RequestParam(name = "idempresa", defaultValue = "") Long idempresa
    ) {
        return new ResponseEntity<List<Sucursales>>(sucursalesServicio.obtenerTodos(idempresa), HttpStatus.OK);
    }

    @GetMapping
    ResponseEntity<?> obtenerPorTermino(
            @RequestParam(name = "termino", defaultValue = "") String termino,
            @RequestParam(name = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(name = "cantidad", defaultValue = "5") Integer cantidad,
            @RequestParam(name = "idempresa", defaultValue = "") Long idempresa) {
        return new ResponseEntity<List<Sucursales>>(sucursalesServicio.obtenerPorTerminoConPaginacion(termino, pagina, cantidad, idempresa), HttpStatus.OK);
    }

    @GetMapping("/cantidad")
    ResponseEntity<?> cantidad(
            @RequestParam(name = "termino", defaultValue = "") String termino,
            @RequestParam(name = "idempresa", defaultValue = "") Long idempresa) {
        return new ResponseEntity<Integer>(sucursalesServicio.obtenerCantidad(termino, idempresa), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<?> dato(@PathVariable Long id) {
        return new ResponseEntity<Sucursales>(sucursalesServicio.obtenerPorId(id), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<?> adicionar(@RequestBody Sucursales dato) {
        sucursalesServicio.adicionar(dato);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    ResponseEntity<?> modificar(@RequestBody Sucursales dato) {
        sucursalesServicio.modificar(dato);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> borrar(@PathVariable Long id) {
        sucursalesServicio.borrar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
