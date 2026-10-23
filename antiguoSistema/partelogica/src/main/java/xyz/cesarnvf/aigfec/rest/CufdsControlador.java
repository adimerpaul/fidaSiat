package xyz.cesarnvf.aigfec.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import xyz.cesarnvf.aigfec.modelo.Cufds;
import xyz.cesarnvf.aigfec.servicio.CufdsServicio;

@RestController
@RequestMapping("/api/v1/cufds")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CufdsControlador {

    CufdsServicio cufdsServicio;

    @GetMapping("/ultimos")
    @Operation(summary = "Obtener Todos")
    ResponseEntity<?> obtenerUltimos(
        @RequestParam(name = "idpuntoventa", defaultValue = "1") Long idpuntoventa
    ) {
        return new ResponseEntity<List<Cufds>>(cufdsServicio.obtenerUltimos(idpuntoventa), HttpStatus.OK);
    }

}
