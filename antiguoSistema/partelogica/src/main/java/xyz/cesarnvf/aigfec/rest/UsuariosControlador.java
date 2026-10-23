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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import xyz.cesarnvf.aigfec.modelo.Usuarios;
import xyz.cesarnvf.aigfec.modelo.otd.UsuariosOTD;
import xyz.cesarnvf.aigfec.servicio.UsuariosServicio;

@RestController
@RequestMapping("/api/v1/usuarios")
@Tag(description = "Controlador", name = "Usuarios")
@SecurityRequirement(name = "AutentificacionAIGFEC")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UsuariosControlador {

    UsuariosServicio usuariosServicio;

    @GetMapping
    @Operation(summary = "Obtener por Termino")
    ResponseEntity<?> obtenerPorTermino(
        @RequestParam(name = "termino", defaultValue = "") String termino,
        @RequestParam(name = "pagina", defaultValue = "0") Integer pagina,
        @RequestParam(name = "cantidad", defaultValue = "5") Integer cantidad
    ) {
        return new ResponseEntity<List<UsuariosOTD>>(usuariosServicio.obtenerPorTerminoConPaginacion(termino, pagina, cantidad), HttpStatus.OK);
    }

    @GetMapping("/cantidad")
    @Operation(summary = "cantidad")
    ResponseEntity<?> cantidad(
        @RequestParam(name = "termino", defaultValue = "") String termino
    ) {
        return new ResponseEntity<Integer>(usuariosServicio.obtenerCantidad(termino), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener por id")
    ResponseEntity<?> dato(@PathVariable Long id) {
        return new ResponseEntity<UsuariosOTD>(usuariosServicio.obtenerPorId(id), HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Adicionar")
    ResponseEntity<?> adicionar(@RequestBody UsuariosOTD dato) {
        usuariosServicio.adicionar(dato);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    @Operation(summary = "Modificar")
    ResponseEntity<?> modificar(@RequestBody UsuariosOTD dato) {
        usuariosServicio.modificar(dato);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/todosvendedores")
    ResponseEntity<?> obtenerTodosRolVendedor() {
        return new ResponseEntity<List<Usuarios>>(usuariosServicio.obtenerTodosRolVendedor(), HttpStatus.OK);
    }

    @PutMapping("/inicializaclave/{id}")
    @Operation(summary = "Obtener por id")
    ResponseEntity<?> inicializaclave(@PathVariable Long id) {
        usuariosServicio.inicializarclave(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/cambiarclave")
    @Operation(summary = "Modificar")
    ResponseEntity<?> cambiarclave(@RequestBody UsuariosOTD dato) {
        usuariosServicio.cambiarclave(dato);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
