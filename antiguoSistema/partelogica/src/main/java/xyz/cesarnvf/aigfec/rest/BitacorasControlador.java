package xyz.cesarnvf.aigfec.rest;

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
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import xyz.cesarnvf.aigfec.modelo.otd.BitacorasOTD;
import xyz.cesarnvf.aigfec.servicio.BitacorasServicio;

@RestController
@RequestMapping("/api/v1/bitacoras")
@Tag(description = "Controlador", name = "Bitacoras")
@SecurityRequirement(name = "AutentificacionSICOSfida")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BitacorasControlador {

    BitacorasServicio bitacorasServicio;

    @GetMapping
    @Operation(summary = "Obtener por Termino")
    ResponseEntity<?> buscarPorTermino(
        @RequestParam(name = "termino", defaultValue = "") String termino,
        @RequestParam(name = "pagina", defaultValue = "0") Integer pagina,
        @RequestParam(name = "cantidad", defaultValue = "5") Integer cantidad
    ) {
        return new ResponseEntity<List<BitacorasOTD>>(bitacorasServicio.obtenerPorTerminoConPaginacion(termino, pagina, cantidad), HttpStatus.OK);
    }

    @GetMapping("/cantidad")
    @Operation(summary = "cantidad")
    ResponseEntity<?> cantidad(
        @RequestParam(name = "termino", defaultValue = "") String termino
    ) {
        return new ResponseEntity<Integer>(bitacorasServicio.obtenerCantidad(termino), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener por id")
    ResponseEntity<?> dato(@PathVariable Long id) {
        return new ResponseEntity<BitacorasOTD>(bitacorasServicio.obtenerPorId(id), HttpStatus.OK);
    }

}

