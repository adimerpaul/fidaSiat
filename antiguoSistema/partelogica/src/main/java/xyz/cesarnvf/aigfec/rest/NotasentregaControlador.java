package xyz.cesarnvf.aigfec.rest;

import java.rmi.RemoteException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import xyz.cesarnvf.aigfec.modelo.otd.NotasentregaOTD;
import xyz.cesarnvf.aigfec.servicio.NotasentregaServicio;
import xyz.cesarnvf.aigfec.servicioPDF.FacturasServicioPDF;

@RestController
@RequestMapping("/api/v1/notasentrega")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class NotasentregaControlador {

    NotasentregaServicio notasentregaServicio;

    FacturasServicioPDF facturasServicioPDF;

    @GetMapping
    ResponseEntity<?> obtenerNotasentrega(
            @RequestParam(name = "termino", defaultValue = "") String termino) {
        return new ResponseEntity<List<NotasentregaOTD>>(notasentregaServicio.obtenerNotasentrega(termino), HttpStatus.OK);
    }

    @GetMapping("/migrarFactura/{id}")
    ResponseEntity<?> migrar(@PathVariable Long id) throws RemoteException, Exception {
        return new ResponseEntity<Long>(notasentregaServicio.migrarNotasEntrega(id), HttpStatus.OK);
    }

    @GetMapping(value = "/facturaPDF", produces = MediaType.APPLICATION_PDF_VALUE)
    @Operation(summary = "Reporte Todos PDF")
    ResponseEntity<?> reciboPDF(
        @RequestParam(name = "idfactura", defaultValue = "0") Long idfactura
    ) {
        return new ResponseEntity<byte[]>(facturasServicioPDF.generaFacturaCPDF(idfactura), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<?> dato(@PathVariable Long id) {
        return new ResponseEntity<NotasentregaOTD>(notasentregaServicio.obtenerPorId(id), HttpStatus.OK);
    }
}
