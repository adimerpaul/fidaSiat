package xyz.cesarnvf.aigfec.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import xyz.cesarnvf.aigfec.servicio.ProductosServicio;

@RestController
@RequestMapping("/api")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ImagenesControlador {

    ProductosServicio productosServicio;

    @GetMapping("/productos/descargarProducto/{nombreArchivo}")
    @Operation(summary = "Descargar Producto")
    ResponseEntity<?> descargarProducto(@PathVariable String nombreArchivo) {
        Resource archivo = productosServicio.descargarFotografiaProducto(nombreArchivo);
        HttpHeaders cabecera = new HttpHeaders();
        cabecera.add(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+ archivo.getFilename() + "\"");
        return new ResponseEntity<Resource>(archivo, cabecera, HttpStatus.OK);
    }
}
