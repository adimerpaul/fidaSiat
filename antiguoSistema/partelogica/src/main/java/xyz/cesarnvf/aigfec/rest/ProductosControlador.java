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
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import xyz.cesarnvf.aigfec.modelo.otd.ProductosOTD;
import xyz.cesarnvf.aigfec.servicio.ProductosServicio;

@RestController
@RequestMapping("/api/v1/productos")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProductosControlador {

    ProductosServicio productosServicio;

    @GetMapping("/todos")
    ResponseEntity<?> obtenerTodos() {
        return new ResponseEntity<List<ProductosOTD>>(productosServicio.obtenerTodos(), HttpStatus.OK);
    }

    @GetMapping
    ResponseEntity<?> obtenerPorTermino(
            @RequestParam(name = "termino", defaultValue = "") String termino,
            @RequestParam(name = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(name = "cantidad", defaultValue = "5") Integer cantidad) {
        return new ResponseEntity<List<ProductosOTD>>(productosServicio.obtenerPorTerminoConPaginacion(termino, pagina, cantidad),
                HttpStatus.OK);
    }

    @GetMapping("/cantidad")
    ResponseEntity<?> cantidad(
            @RequestParam(name = "termino", defaultValue = "") String termino) {
        return new ResponseEntity<Integer>(productosServicio.obtenerCantidad(termino), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<?> dato(@PathVariable Long id) {
        return new ResponseEntity<ProductosOTD>(productosServicio.obtenerPorId(id), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<?> adicionar(@RequestBody ProductosOTD dato) {
        productosServicio.adicionar(dato);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    ResponseEntity<?> modificar(@RequestBody ProductosOTD dato) {
        productosServicio.modificar(dato);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/subirImagen/{id}")
    ResponseEntity<?> cargarFotografiaProductos(@PathVariable Long id, @RequestParam("archivo") MultipartFile archivo) {
        productosServicio.cargarFotografiaProducto(id, archivo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/obtenerPorIdgrupoConPaginacion")
    ResponseEntity<?> obtenerPorIdgrupoConPaginacion(
        @RequestParam(name = "idgrupo", defaultValue = "0") Long idgrupo,
        @RequestParam(name = "pagina", defaultValue = "0") Integer pagina,
        @RequestParam(name = "cantidad", defaultValue = "5") Integer cantidad
    ) {
        return new ResponseEntity<List<ProductosOTD>>(productosServicio.obtenerPorIdgrupoConPaginacion(idgrupo, pagina, cantidad), HttpStatus.OK);
    }

    @GetMapping("/cantidadPorIdgrupo")
    ResponseEntity<?> cantidadPorIdgrupo(
        @RequestParam(name = "idgrupo", defaultValue = "0") Long idgrupo)
    {
        return new ResponseEntity<Integer>(productosServicio.obtenerCantidadPorIdgrupo(idgrupo), HttpStatus.OK);
    }

    @GetMapping("/codigocorto/{codigocorto}")
    ResponseEntity<?> datocodiigocorto(@PathVariable Integer codigocorto) {
        return new ResponseEntity<ProductosOTD>(productosServicio.obtenerPorCodigocorto(codigocorto), HttpStatus.OK);
    }

}
