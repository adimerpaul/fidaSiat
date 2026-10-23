package xyz.cesarnvf.aigfec.rest;

import java.rmi.RemoteException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import xyz.cesarnvf.aigfec.modelo.auxi.Respuestas;
import xyz.cesarnvf.aigfec.modelo.otd.CuisOTD;
import xyz.cesarnvf.aigfec.servicio.CuisServicio;

@RestController
@RequestMapping("/api/v1/cuis")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CuisControlador {

    CuisServicio cuisServicio;

    @GetMapping("/todos")
    @Operation(summary = "Obtener Todos")
    ResponseEntity<?> obtenerTodos() {
        return new ResponseEntity<List<CuisOTD>>(cuisServicio.obtenerTodos(), HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "Obtener por Termino")
    ResponseEntity<?> obtenerPorTermino(
            @RequestParam(name = "termino", defaultValue = "") String termino,
            @RequestParam(name = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(name = "cantidad", defaultValue = "5") Integer cantidad) {
        return new ResponseEntity<List<CuisOTD>>(cuisServicio.obtenerPorTerminoConPaginacion(termino, pagina, cantidad),
                HttpStatus.OK);
    }

    @GetMapping("/cantidad")
    ResponseEntity<?> cantidad(
            @RequestParam(name = "termino", defaultValue = "") String termino) {
        return new ResponseEntity<Integer>(cuisServicio.obtenerCantidad(termino), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<?> dato(@PathVariable Long id) {
        return new ResponseEntity<CuisOTD>(cuisServicio.obtenerPorId(id), HttpStatus.OK);
    }

    @GetMapping("/adicionarPuntosventas")
    ResponseEntity<?> adicionarPuntosventas() {
        cuisServicio.adicionarPuntosventas();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/registrarPuntoventa/{id}")
    ResponseEntity<?> registrarPuntoventa(@PathVariable Long id) throws RemoteException {
        return new ResponseEntity<List<Respuestas>>(cuisServicio.registrarPuntoventa(id), HttpStatus.OK);
    }

    @GetMapping("/obtenerCui/{id}")
    ResponseEntity<?> obtenerCui(@PathVariable Long id) throws RemoteException{
        System.out.println(id);
        return new ResponseEntity<List<Respuestas>>(cuisServicio.obtenerCui(id), HttpStatus.OK);
    }

    @GetMapping("/adicionarCui/{id}")
    ResponseEntity<?> adicionarCui(@PathVariable Long id) throws RemoteException{
        return new ResponseEntity<List<Respuestas>>(cuisServicio.adicionarCui(id), HttpStatus.OK);
    }

}

