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
import xyz.cesarnvf.aigfec.modelo.Empresas;
import xyz.cesarnvf.aigfec.servicio.EmpresasServicio;

@RestController
@RequestMapping("/api/v1/empresas")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EmpresasControlador {

    EmpresasServicio empresasServicio;

    @GetMapping("/todos")
    ResponseEntity<?> obtenerTodos() {
        return new ResponseEntity<List<Empresas>>(empresasServicio.obtenerTodos(), HttpStatus.OK);
    }

    @GetMapping
    ResponseEntity<?> obtenerPorTermino(
            @RequestParam(name = "termino", defaultValue = "") String termino,
            @RequestParam(name = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(name = "cantidad", defaultValue = "5") Integer cantidad) {
        return new ResponseEntity<List<Empresas>>(empresasServicio.obtenerPorTerminoConPaginacion(termino, pagina, cantidad),
                HttpStatus.OK);
    }

    @GetMapping("/cantidad")
    ResponseEntity<?> cantidad(
            @RequestParam(name = "termino", defaultValue = "") String termino) {
        return new ResponseEntity<Integer>(empresasServicio.obtenerCantidad(termino), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<?> dato(@PathVariable Long id) {
        return new ResponseEntity<Empresas>(empresasServicio.obtenerPorId(id), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<?> adicionar(@RequestBody Empresas dato) {
        empresasServicio.adicionar(dato);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    ResponseEntity<?> modificar(@RequestBody Empresas dato) {
        empresasServicio.modificar(dato);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
