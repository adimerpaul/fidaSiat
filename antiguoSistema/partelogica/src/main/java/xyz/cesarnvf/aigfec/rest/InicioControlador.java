package xyz.cesarnvf.aigfec.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class InicioControlador {

    @GetMapping
    String inicio() {
        return "<center>SFIDA<br>Terminal Punto de Venta - Facturacion Electronica Computarizada<br>AseInge (Asesoramiento en Ingenieria)<br>Ing. Cesar Nilton Vincenty Funes<br>72461921<br>cesarnvf.profesional.bo@gmail.com</center>";
    }



}
