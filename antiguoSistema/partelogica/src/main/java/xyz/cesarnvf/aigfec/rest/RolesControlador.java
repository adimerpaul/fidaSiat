package xyz.cesarnvf.aigfec.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import xyz.cesarnvf.aigfec.modelo.Roles;
import xyz.cesarnvf.aigfec.servicio.RolesServicio;

@RestController
@RequestMapping("/api/v1/roles")
@Tag(description = "Controlador", name = "Roles")
@SecurityRequirement(name = "AutentificacionSICOSfida")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RolesControlador {

    RolesServicio rolesServicio;

    @GetMapping("/todos")
    @Operation(summary = "Obtener Todos")
    ResponseEntity<?> obtenerTodos() {
        return new ResponseEntity<List<Roles>>(rolesServicio.obtenerTodos(), HttpStatus.OK);
    }


}
