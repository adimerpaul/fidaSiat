package xyz.cesarnvf.aigfec.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import xyz.cesarnvf.aigfec.modelo.otd.KardexOTD;
import xyz.cesarnvf.aigfec.servicio.KardexServicio;

@RestController
@RequestMapping("/api/v1/kardex")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class KardexControlador {

    KardexServicio kardexServicio;

    @GetMapping("/porpedido/{id}")
    ResponseEntity<?> datos(@PathVariable Long id) {
        return new ResponseEntity<List<KardexOTD>>(kardexServicio.obtenerPorIdPedido(id), HttpStatus.OK);
    }

}
