package xyz.cesarnvf.aigfec.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import xyz.cesarnvf.aigfec.modelo.otd.MenusOTD;
import xyz.cesarnvf.aigfec.servicio.EnlacesServicio;

@RestController
@RequestMapping("/api/v1/menus")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MenusControlador {

    EnlacesServicio enlacesServicio;

    @GetMapping
    ResponseEntity<?> generarMenuPorUsuario() {
        return new ResponseEntity<List<MenusOTD>>(enlacesServicio.obtenerEnlacePorIdusuario(), HttpStatus.OK);
    }

}
