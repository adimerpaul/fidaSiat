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
import xyz.cesarnvf.aigfec.modelo.otd.PuntosventasOTD;
import xyz.cesarnvf.aigfec.servicio.PuntosventasServicio;

@RestController
@RequestMapping("/api/v1/puntosventas")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PuntosventasControlador {

    PuntosventasServicio puntosventasServicio;

    @GetMapping("/todos")
    ResponseEntity<?> obtenerTodos(
        @RequestParam(name = "idsucursal", defaultValue = "") Long idsucursal
    ) {
        return new ResponseEntity<List<PuntosventasOTD>>(puntosventasServicio.obtenerTodos(idsucursal), HttpStatus.OK);
    }

    @GetMapping
    ResponseEntity<?> obtenerPorTermino(
            @RequestParam(name = "termino", defaultValue = "") String termino,
            @RequestParam(name = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(name = "cantidad", defaultValue = "5") Integer cantidad,
            @RequestParam(name = "idsucursal", defaultValue = "") Long idsucursal) {
        return new ResponseEntity<List<PuntosventasOTD>>(puntosventasServicio.obtenerPorTerminoConPaginacion(termino, pagina, cantidad, idsucursal),
                HttpStatus.OK);
    }

    @GetMapping("/cantidad")
    ResponseEntity<?> cantidad(
            @RequestParam(name = "termino", defaultValue = "") String termino,
            @RequestParam(name = "idsucursal", defaultValue = "") Long idsucursal) {
        return new ResponseEntity<Integer>(puntosventasServicio.obtenerCantidad(termino, idsucursal), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<?> dato(@PathVariable Long id) {
        return new ResponseEntity<PuntosventasOTD>(puntosventasServicio.obtenerPorId(id), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<?> adicionar(@RequestBody PuntosventasOTD dato) {
        puntosventasServicio.adicionar(dato);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    ResponseEntity<?> modificar(@RequestBody PuntosventasOTD dato) {
        puntosventasServicio.modificar(dato);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> borrar(@PathVariable Long id) {
        puntosventasServicio.borrar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
