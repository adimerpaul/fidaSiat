package xyz.cesarnvf.aigfec.rest;

import java.rmi.RemoteException;
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
import xyz.cesarnvf.aigfec.modelo.otd.ClientesOTD;
import xyz.cesarnvf.aigfec.servicio.ClientesServicio;

@RestController
@RequestMapping("/api/v1/clientes")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ClientesControlador {

    ClientesServicio clientesServicio;

    @GetMapping("/todos")
    ResponseEntity<?> obtenerTodos() {
        return new ResponseEntity<List<ClientesOTD>>(clientesServicio.obtenerTodos(), HttpStatus.OK);
    }

    @GetMapping
    ResponseEntity<?> obtenerPorTermino(
        @RequestParam(name = "termino", defaultValue = "") String termino,
        @RequestParam(name = "pagina", defaultValue = "0") Integer pagina,
        @RequestParam(name = "cantidad", defaultValue = "5") Integer cantidad) {
        return new ResponseEntity<List<ClientesOTD>>(clientesServicio.obtenerPorTerminoConPaginacion(termino, pagina, cantidad), HttpStatus.OK);
    }

    @GetMapping("/cantidad")
    ResponseEntity<?> cantidad(
        @RequestParam(name = "termino", defaultValue = "") String termino) {
        return new ResponseEntity<Integer>(clientesServicio.obtenerCantidad(termino), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<?> dato(@PathVariable Long id) {
        return new ResponseEntity<ClientesOTD>(clientesServicio.obtenerPorId(id), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<?> adicionar(@RequestBody ClientesOTD dato) throws NumberFormatException, RemoteException {
        clientesServicio.adicionar(dato);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    ResponseEntity<?> modificar(@RequestBody ClientesOTD dato) throws NumberFormatException, RemoteException {
        clientesServicio.modificar(dato);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/buscarcliente")
    ResponseEntity<?> dato(
        @RequestParam(value = "buscar", defaultValue = "0") String buscar
    ) {
        return new ResponseEntity<ClientesOTD>(clientesServicio.buscarPorDocumentoResgistradoImpuestos(buscar), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> borrar(@PathVariable Long id) {
        clientesServicio.borrar(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
