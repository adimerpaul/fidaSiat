package xyz.cesarnvf.aigfec.rest;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import lombok.AllArgsConstructor;
import xyz.cesarnvf.aigfec.modelo.Facturas;
import xyz.cesarnvf.aigfec.modelo.Puntosventas;
import xyz.cesarnvf.aigfec.modelo.otd.DetallesOTD;
import xyz.cesarnvf.aigfec.modelo.otd.FacturasOTD;
import xyz.cesarnvf.aigfec.repositorio.FacturasRepositorio;
import xyz.cesarnvf.aigfec.repositorio.PuntosventasRepositorio;
import xyz.cesarnvf.aigfec.servicio.FacturasServicio;
import xyz.cesarnvf.aigfec.servicioPDF.FacturasReportesServicioPDF;
import xyz.cesarnvf.aigfec.servicioPDF.FacturasServicioPDF;

@RestController
@RequestMapping("/api/v1/facturas")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class FacturasControlador {

    FacturasServicio facturasServicio;

    PuntosventasRepositorio puntosventasRepositorio;

    FacturasRepositorio facturasRepositorio;

    FacturasServicioPDF facturaServicioPDF;

    FacturasReportesServicioPDF facturasReportesServicioPDF;

    @GetMapping
    ResponseEntity<?> obtenerPorTermino(
        @RequestParam(name = "termino", defaultValue = "") String termino,
        @RequestParam(name = "pagina", defaultValue = "0") Integer pagina,
        @RequestParam(name = "cantidad", defaultValue = "5") Integer cantidad) {
        return new ResponseEntity<List<FacturasOTD>>(facturasServicio.obtenerPorTerminoConPaginacion(termino, pagina, cantidad), HttpStatus.OK);
    }

    @GetMapping("/cantidad")
    ResponseEntity<?> cantidad(
        @RequestParam(name = "termino", defaultValue = "") String termino) {
        return new ResponseEntity<Integer>(facturasServicio.obtenerCantidad(termino), HttpStatus.OK);
    }

    @GetMapping("/notasentrega")
    ResponseEntity<?> obtenerPorIdpedido() {
        return new ResponseEntity<List<FacturasOTD>>(facturasServicio.obtenerPorTerminoIdpedido(), HttpStatus.OK);
    }

    @GetMapping("/noenviados")
    ResponseEntity<?> obtenerNoEnviados() {
        return new ResponseEntity<List<FacturasOTD>>(facturasServicio.obtenerNoEnviados(), HttpStatus.OK);
    }

    @GetMapping("/noenviadoso")
    ResponseEntity<?> obtenerNoEnviadoso() {
        return new ResponseEntity<List<FacturasOTD>>(facturasServicio.obtenerNoEnviadoso(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<?> dato(@PathVariable Long id) {
        return new ResponseEntity<FacturasOTD>(facturasServicio.obtenerPorId(id), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<?> adicionar(@RequestBody FacturasOTD dato) throws RemoteException, Exception {
        return new ResponseEntity<Long>(facturasServicio.adicionar(dato), HttpStatus.CREATED);
    }

    @GetMapping(value = "/facturaPDF", produces = MediaType.APPLICATION_PDF_VALUE)
    @Operation(summary = "Reporte Todos PDF")
    ResponseEntity<?> reciboPDF(
        @RequestParam(name = "idfactura", defaultValue = "0") Long idfactura
    ) {
        Facturas factura = facturasRepositorio.obtenerPorId(idfactura);
        if (factura.getIdpedido()>0) {
            return new ResponseEntity<byte[]>(facturaServicioPDF.generaFacturaCPDF(idfactura), HttpStatus.OK);
        } else {
            Puntosventas puntoventa = puntosventasRepositorio.obtenerPorId(factura.getIdpuntoventa());
            if (puntoventa.getEstadosistema()==1) {
                return new ResponseEntity<byte[]>(facturaServicioPDF.generaFacturaPDF(idfactura), HttpStatus.OK);
            } else {
                return new ResponseEntity<byte[]>(facturaServicioPDF.generaFacturaCPDF(idfactura), HttpStatus.OK);
            }
        }
    }

    @PutMapping("/descuento/{idproducto}/{idcliente}")
    ResponseEntity<?> descuentos(@PathVariable Long idproducto, @PathVariable Long idcliente, @RequestBody List<DetallesOTD> dato) {
        return new ResponseEntity<BigDecimal>(facturasServicio.obtenerDescuento(dato, idcliente, idproducto), HttpStatus.OK);
    }

    @GetMapping(value = "/facturaValidosPDF", produces = MediaType.APPLICATION_PDF_VALUE)
    @Operation(summary = "Reporte Validos")
    ResponseEntity<?> reporteValidosPDF(
        @RequestParam(value = "fecha", defaultValue = "") String fecha
    ) {
        LocalDate fechaactual = LocalDate.parse(fecha);
        return new ResponseEntity<byte[]>(facturasReportesServicioPDF.reporteValidosPDF(fechaactual), HttpStatus.OK);
    }


    @GetMapping(value = "/facturaAnuladosPDF", produces = MediaType.APPLICATION_PDF_VALUE)
    @Operation(summary = "Reporte Validos")
    ResponseEntity<?> reporteAnuladosPDF(
        @RequestParam(value = "fecha", defaultValue = "") String fecha
    ) {
        LocalDate fechaactual = LocalDate.parse(fecha);
        return new ResponseEntity<byte[]>(facturasReportesServicioPDF.reporteAnuladosPDF(fechaactual), HttpStatus.OK);
    }

    @GetMapping(value = "/facturaRechazadosAnuladosPDF", produces = MediaType.APPLICATION_PDF_VALUE)
    @Operation(summary = "Reporte Validos")
    ResponseEntity<?> reporteRechazadosAnuladosPDF(
        @RequestParam(value = "fecha", defaultValue = "") String fecha
    ) {
        LocalDate fechaactual = LocalDate.parse(fecha);
        return new ResponseEntity<byte[]>(facturasReportesServicioPDF.reporteRechazadosAnuladosPDF(fechaactual), HttpStatus.OK);
    }

    @GetMapping(value = "/facturaOtrosPDF", produces = MediaType.APPLICATION_PDF_VALUE)
    @Operation(summary = "Reporte Otros")
    ResponseEntity<?> reporteOtrosPDF(
        @RequestParam(value = "fecha", defaultValue = "") String fecha
    ) {
        LocalDate fechaactual = LocalDate.parse(fecha);
        return new ResponseEntity<byte[]>(facturasReportesServicioPDF.reporteOtrosPDF(fechaactual), HttpStatus.OK);
    }

    @GetMapping(value = "/facturaGeneralPDF", produces = MediaType.APPLICATION_PDF_VALUE)
    @Operation(summary = "Reporte General")
    ResponseEntity<?> reporteGeneralPDF(
        @RequestParam(value = "fecha", defaultValue = "") String fecha
    ) {
        LocalDate fechaactual = LocalDate.parse(fecha);
        return new ResponseEntity<byte[]>(facturasReportesServicioPDF.reporteGeneralPDF(fechaactual), HttpStatus.OK);
    }
}
