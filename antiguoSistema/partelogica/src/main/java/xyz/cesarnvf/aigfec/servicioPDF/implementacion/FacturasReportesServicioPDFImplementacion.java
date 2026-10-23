package xyz.cesarnvf.aigfec.servicioPDF.implementacion;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

import xyz.cesarnvf.aigfec.modelo.Clientes;
import xyz.cesarnvf.aigfec.modelo.Facturas;
import xyz.cesarnvf.aigfec.repositorio.ClientesRepositorio;
import xyz.cesarnvf.aigfec.repositorio.FacturasRepositorio;
import xyz.cesarnvf.aigfec.repositorio.UsuariosRepositorio;
import xyz.cesarnvf.aigfec.servicioPDF.FacturasReportesServicioPDF;

@Service
public class FacturasReportesServicioPDFImplementacion implements FacturasReportesServicioPDF{

    @Autowired
    FacturasRepositorio facturasRepositorio;

    @Autowired
    UsuariosRepositorio usuariosRepositorio;

    @Autowired
    ClientesRepositorio clientesRepositorio;

    @Override
    public byte[] reporteValidosPDF(LocalDate fecha) {
        Authentication autentificado = SecurityContextHolder.getContext().getAuthentication();
        Long idusuario = Long.parseLong(autentificado.getName());
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        DateTimeFormatter fechaFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        ByteArrayOutputStream salida = new ByteArrayOutputStream();
        try {
            PdfWriter escribir = new PdfWriter(salida);
            PdfDocument pdf = new PdfDocument(escribir);
	        Document documento = new Document(pdf, PageSize.LETTER);
		    documento.setMargins(83f, 30f, 35f, 30f);
            PdfFont font_normal = PdfFontFactory.createFont(StandardFonts.HELVETICA);
            PdfFont font_bold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);

            FormatoReportes evento = new FormatoReportes(documento, "VALIDAS");
            pdf.addEventHandler(PdfDocumentEvent.END_PAGE, evento);


            String[] tituloscolumnas = { "Nro.", "Nro. Factura", "Fecha emision", "Cliente", "C.I. o NIT", "Total", "Descuento", "Total a Pagar"};

            float[] ancho = { 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f};
            Table tabla = new Table(ancho);
            tabla.setWidth(UnitValue.createPercentValue(100));

            Cell celda;

            for (int col = 0; col < tituloscolumnas.length; col++) {
                celda = new Cell()
                        .setTextAlignment(TextAlignment.CENTER)
                        .setFontSize(7)
                        .setFont(font_bold);
                celda.add(new Paragraph(tituloscolumnas[col]));
                tabla.addHeaderCell(celda);
            }

            Integer contador = 1;

            List<Facturas> datos = facturasRepositorio.obtenerFacturasValidas(idusuario, fecha);
            System.out.println(datos);

            BigDecimal sumatotal = BigDecimal.ZERO;
            BigDecimal sumatotalapagar = BigDecimal.ZERO;
            BigDecimal sumadescuento = BigDecimal.ZERO;

            for (Facturas factura : datos) {

                Clientes cliente = clientesRepositorio.obtenerPorId(factura.getIdcliente());

                celda = new Cell().setTextAlignment(TextAlignment.CENTER).setFontSize(7).setFont(font_normal);
                celda.add(new Paragraph(contador.toString()));
                tabla.addCell(celda);

                celda = new Cell().setTextAlignment(TextAlignment.CENTER).setFontSize(7).setFont(font_normal);
                celda.add(new Paragraph(factura.getNumerofactura().toString()));
                tabla.addCell(celda);

                celda = new Cell().setTextAlignment(TextAlignment.CENTER).setFontSize(7).setFont(font_normal);
                celda.add(new Paragraph(fechaFormat.format(factura.getFechaemision())));
                tabla.addCell(celda);

                celda = new Cell().setTextAlignment(TextAlignment.LEFT).setFontSize(7).setFont(font_normal);
                celda.add(new Paragraph(cliente.getCliente()));
                tabla.addCell(celda);

                celda = new Cell().setTextAlignment(TextAlignment.CENTER).setFontSize(7).setFont(font_normal);
                celda.add(new Paragraph(cliente.getNumerodocumento()));
                tabla.addCell(celda);

                celda = new Cell().setTextAlignment(TextAlignment.RIGHT).setFontSize(7).setFont(font_normal);
                celda.add(new Paragraph(decimalFormat.format(factura.getTotal())));
                tabla.addCell(celda);

                celda = new Cell().setTextAlignment(TextAlignment.RIGHT).setFontSize(7).setFont(font_normal);
                celda.add(new Paragraph(decimalFormat.format(factura.getDescuento())));
                tabla.addCell(celda);

                celda = new Cell().setTextAlignment(TextAlignment.RIGHT).setFontSize(7).setFont(font_normal);
                celda.add(new Paragraph(decimalFormat.format(factura.getTotal().subtract(factura.getDescuento()))));
                tabla.addCell(celda);

                sumatotal = sumatotal.add(factura.getTotal());
                sumatotalapagar = sumatotalapagar.add(factura.getTotal().subtract(factura.getDescuento()));
                sumadescuento = sumadescuento.add(factura.getDescuento());

                contador++;
            }

            celda = new Cell(1, 5).setTextAlignment(TextAlignment.RIGHT).setFontSize(7).setFont(font_bold);
            celda.add(new Paragraph("TOTALES"));
            tabla.addCell(celda);

            celda = new Cell().setTextAlignment(TextAlignment.RIGHT).setFontSize(7).setFont(font_bold);
            celda.add(new Paragraph(decimalFormat.format(sumatotal)));
            tabla.addCell(celda);

            celda = new Cell().setTextAlignment(TextAlignment.RIGHT).setFontSize(7).setFont(font_bold);
            celda.add(new Paragraph(decimalFormat.format(sumadescuento)));
            tabla.addCell(celda);

            celda = new Cell().setTextAlignment(TextAlignment.RIGHT).setFontSize(7).setFont(font_bold);
            celda.add(new Paragraph(decimalFormat.format(sumatotalapagar)));
            tabla.addCell(celda);

            documento.add(tabla);
            documento.close();
        } catch (Exception e) {
            System.out.println("Error: "+e.toString());
        }
        return salida.toByteArray();
    }

    @Override
    public byte[] reporteAnuladosPDF(LocalDate fecha) {
        Authentication autentificado = SecurityContextHolder.getContext().getAuthentication();
        Long idusuario = Long.parseLong(autentificado.getName());
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        DateTimeFormatter fechaFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        ByteArrayOutputStream salida = new ByteArrayOutputStream();
        try {
            PdfWriter escribir = new PdfWriter(salida);
            PdfDocument pdf = new PdfDocument(escribir);
	        Document documento = new Document(pdf, PageSize.LETTER);
		    documento.setMargins(83f, 30f, 35f, 30f);
            PdfFont font_normal = PdfFontFactory.createFont(StandardFonts.HELVETICA);
            PdfFont font_bold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);

            FormatoReportes evento = new FormatoReportes(documento, "ANULADAS");
            pdf.addEventHandler(PdfDocumentEvent.END_PAGE, evento);


            String[] tituloscolumnas = { "Nro.", "Nro. Factura", "Fecha emision", "Cliente", "C.I. o NIT", "Total", "Descuento", "Total a Pagar"};

            float[] ancho = { 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f};
            Table tabla = new Table(ancho);
            tabla.setWidth(UnitValue.createPercentValue(100));

            Cell celda;

            for (int col = 0; col < tituloscolumnas.length; col++) {
                celda = new Cell()
                        .setTextAlignment(TextAlignment.CENTER)
                        .setFontSize(7)
                        .setFont(font_bold);
                celda.add(new Paragraph(tituloscolumnas[col]));
                tabla.addHeaderCell(celda);
            }

            Integer contador = 1;

            List<Facturas> datos = facturasRepositorio.obtenerFacturasAnulados(idusuario, fecha);
            System.out.println(datos);

            BigDecimal sumatotal = BigDecimal.ZERO;
            BigDecimal sumatotalapagar = BigDecimal.ZERO;
            BigDecimal sumadescuento = BigDecimal.ZERO;

            for (Facturas factura : datos) {

                Clientes cliente = clientesRepositorio.obtenerPorId(factura.getIdcliente());

                celda = new Cell().setTextAlignment(TextAlignment.CENTER).setFontSize(7).setFont(font_normal);
                celda.add(new Paragraph(contador.toString()));
                tabla.addCell(celda);

                celda = new Cell().setTextAlignment(TextAlignment.CENTER).setFontSize(7).setFont(font_normal);
                celda.add(new Paragraph(factura.getNumerofactura().toString()));
                tabla.addCell(celda);

                celda = new Cell().setTextAlignment(TextAlignment.CENTER).setFontSize(7).setFont(font_normal);
                celda.add(new Paragraph(fechaFormat.format(factura.getFechaemision())));
                tabla.addCell(celda);

                celda = new Cell().setTextAlignment(TextAlignment.LEFT).setFontSize(7).setFont(font_normal);
                celda.add(new Paragraph(cliente.getCliente()));
                tabla.addCell(celda);

                celda = new Cell().setTextAlignment(TextAlignment.CENTER).setFontSize(7).setFont(font_normal);
                celda.add(new Paragraph(cliente.getNumerodocumento()));
                tabla.addCell(celda);

                celda = new Cell().setTextAlignment(TextAlignment.RIGHT).setFontSize(7).setFont(font_normal);
                celda.add(new Paragraph(decimalFormat.format(factura.getTotal())));
                tabla.addCell(celda);

                celda = new Cell().setTextAlignment(TextAlignment.RIGHT).setFontSize(7).setFont(font_normal);
                celda.add(new Paragraph(decimalFormat.format(factura.getDescuento())));
                tabla.addCell(celda);

                celda = new Cell().setTextAlignment(TextAlignment.RIGHT).setFontSize(7).setFont(font_normal);
                celda.add(new Paragraph(decimalFormat.format(factura.getTotal().subtract(factura.getDescuento()))));
                tabla.addCell(celda);

                sumatotal = sumatotal.add(factura.getTotal());
                sumatotalapagar = sumatotalapagar.add(factura.getTotal().subtract(factura.getDescuento()));
                sumadescuento = sumadescuento.add(factura.getDescuento());

                contador++;
            }

            celda = new Cell(1, 5).setTextAlignment(TextAlignment.RIGHT).setFontSize(7).setFont(font_bold);
            celda.add(new Paragraph("TOTALES"));
            tabla.addCell(celda);

            celda = new Cell().setTextAlignment(TextAlignment.RIGHT).setFontSize(7).setFont(font_bold);
            celda.add(new Paragraph(decimalFormat.format(sumatotal)));
            tabla.addCell(celda);

            celda = new Cell().setTextAlignment(TextAlignment.RIGHT).setFontSize(7).setFont(font_bold);
            celda.add(new Paragraph(decimalFormat.format(sumadescuento)));
            tabla.addCell(celda);

            celda = new Cell().setTextAlignment(TextAlignment.RIGHT).setFontSize(7).setFont(font_bold);
            celda.add(new Paragraph(decimalFormat.format(sumatotalapagar)));
            tabla.addCell(celda);

            documento.add(tabla);
            documento.close();
        } catch (Exception e) {
            System.out.println("Error: "+e.toString());
        }
        return salida.toByteArray();
    }

    @Override
    public byte[] reporteRechazadosAnuladosPDF(LocalDate fecha) {
        Authentication autentificado = SecurityContextHolder.getContext().getAuthentication();
        Long idusuario = Long.parseLong(autentificado.getName());
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        DateTimeFormatter fechaFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        ByteArrayOutputStream salida = new ByteArrayOutputStream();
        try {
            PdfWriter escribir = new PdfWriter(salida);
            PdfDocument pdf = new PdfDocument(escribir);
	        Document documento = new Document(pdf, PageSize.LETTER);
		    documento.setMargins(83f, 30f, 35f, 30f);
            PdfFont font_normal = PdfFontFactory.createFont(StandardFonts.HELVETICA);
            PdfFont font_bold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);

            FormatoReportes evento = new FormatoReportes(documento, "RECHAZADAS DE ANULACION");
            pdf.addEventHandler(PdfDocumentEvent.END_PAGE, evento);


            String[] tituloscolumnas = { "Nro.", "Nro. Factura", "Fecha emision", "Cliente", "C.I. o NIT", "Total", "Descuento", "Total a Pagar"};

            float[] ancho = { 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f};
            Table tabla = new Table(ancho);
            tabla.setWidth(UnitValue.createPercentValue(100));

            Cell celda;

            for (int col = 0; col < tituloscolumnas.length; col++) {
                celda = new Cell()
                        .setTextAlignment(TextAlignment.CENTER)
                        .setFontSize(7)
                        .setFont(font_bold);
                celda.add(new Paragraph(tituloscolumnas[col]));
                tabla.addHeaderCell(celda);
            }

            Integer contador = 1;

            List<Facturas> datos = facturasRepositorio.obtenerFacturasRechazadosAnulados(idusuario, fecha);
            System.out.println(datos);

            BigDecimal sumatotal = BigDecimal.ZERO;
            BigDecimal sumatotalapagar = BigDecimal.ZERO;
            BigDecimal sumadescuento = BigDecimal.ZERO;

            for (Facturas factura : datos) {

                Clientes cliente = clientesRepositorio.obtenerPorId(factura.getIdcliente());

                celda = new Cell().setTextAlignment(TextAlignment.CENTER).setFontSize(7).setFont(font_normal);
                celda.add(new Paragraph(contador.toString()));
                tabla.addCell(celda);

                celda = new Cell().setTextAlignment(TextAlignment.CENTER).setFontSize(7).setFont(font_normal);
                celda.add(new Paragraph(factura.getNumerofactura().toString()));
                tabla.addCell(celda);

                celda = new Cell().setTextAlignment(TextAlignment.CENTER).setFontSize(7).setFont(font_normal);
                celda.add(new Paragraph(fechaFormat.format(factura.getFechaemision())));
                tabla.addCell(celda);

                celda = new Cell().setTextAlignment(TextAlignment.LEFT).setFontSize(7).setFont(font_normal);
                celda.add(new Paragraph(cliente.getCliente()));
                tabla.addCell(celda);

                celda = new Cell().setTextAlignment(TextAlignment.CENTER).setFontSize(7).setFont(font_normal);
                celda.add(new Paragraph(cliente.getNumerodocumento()));
                tabla.addCell(celda);

                celda = new Cell().setTextAlignment(TextAlignment.RIGHT).setFontSize(7).setFont(font_normal);
                celda.add(new Paragraph(decimalFormat.format(factura.getTotal())));
                tabla.addCell(celda);

                celda = new Cell().setTextAlignment(TextAlignment.RIGHT).setFontSize(7).setFont(font_normal);
                celda.add(new Paragraph(decimalFormat.format(factura.getDescuento())));
                tabla.addCell(celda);

                celda = new Cell().setTextAlignment(TextAlignment.RIGHT).setFontSize(7).setFont(font_normal);
                celda.add(new Paragraph(decimalFormat.format(factura.getTotal().subtract(factura.getDescuento()))));
                tabla.addCell(celda);

                sumatotal = sumatotal.add(factura.getTotal());
                sumatotalapagar = sumatotalapagar.add(factura.getTotal().subtract(factura.getDescuento()));
                sumadescuento = sumadescuento.add(factura.getDescuento());

                contador++;
            }

            celda = new Cell(1, 5).setTextAlignment(TextAlignment.RIGHT).setFontSize(7).setFont(font_bold);
            celda.add(new Paragraph("TOTALES"));
            tabla.addCell(celda);

            celda = new Cell().setTextAlignment(TextAlignment.RIGHT).setFontSize(7).setFont(font_bold);
            celda.add(new Paragraph(decimalFormat.format(sumatotal)));
            tabla.addCell(celda);

            celda = new Cell().setTextAlignment(TextAlignment.RIGHT).setFontSize(7).setFont(font_bold);
            celda.add(new Paragraph(decimalFormat.format(sumadescuento)));
            tabla.addCell(celda);

            celda = new Cell().setTextAlignment(TextAlignment.RIGHT).setFontSize(7).setFont(font_bold);
            celda.add(new Paragraph(decimalFormat.format(sumatotalapagar)));
            tabla.addCell(celda);

            documento.add(tabla);
            documento.close();
        } catch (Exception e) {
            System.out.println("Error: "+e.toString());
        }
        return salida.toByteArray();
    }

    @Override
    public byte[] reporteOtrosPDF(LocalDate fecha) {
        Authentication autentificado = SecurityContextHolder.getContext().getAuthentication();
        Long idusuario = Long.parseLong(autentificado.getName());
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        DateTimeFormatter fechaFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        ByteArrayOutputStream salida = new ByteArrayOutputStream();
        try {
            PdfWriter escribir = new PdfWriter(salida);
            PdfDocument pdf = new PdfDocument(escribir);
	        Document documento = new Document(pdf, PageSize.LETTER);
		    documento.setMargins(83f, 30f, 35f, 30f);
            PdfFont font_normal = PdfFontFactory.createFont(StandardFonts.HELVETICA);
            PdfFont font_bold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);

            FormatoReportes evento = new FormatoReportes(documento, "OTROS");
            pdf.addEventHandler(PdfDocumentEvent.END_PAGE, evento);


            String[] tituloscolumnas = { "Nro.", "Nro. Factura", "Fecha emision", "Cliente", "C.I. o NIT", "Total", "Descuento", "Total a Pagar"};

            float[] ancho = { 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f};
            Table tabla = new Table(ancho);
            tabla.setWidth(UnitValue.createPercentValue(100));

            Cell celda;

            for (int col = 0; col < tituloscolumnas.length; col++) {
                celda = new Cell()
                        .setTextAlignment(TextAlignment.CENTER)
                        .setFontSize(7)
                        .setFont(font_bold);
                celda.add(new Paragraph(tituloscolumnas[col]));
                tabla.addHeaderCell(celda);
            }

            Integer contador = 1;

            List<Facturas> datos = facturasRepositorio.obtenerFacturasOtros(idusuario, fecha);
            System.out.println(datos);

            BigDecimal sumatotal = BigDecimal.ZERO;
            BigDecimal sumatotalapagar = BigDecimal.ZERO;
            BigDecimal sumadescuento = BigDecimal.ZERO;

            for (Facturas factura : datos) {

                Clientes cliente = clientesRepositorio.obtenerPorId(factura.getIdcliente());

                celda = new Cell().setTextAlignment(TextAlignment.CENTER).setFontSize(7).setFont(font_normal);
                celda.add(new Paragraph(contador.toString()));
                tabla.addCell(celda);

                celda = new Cell().setTextAlignment(TextAlignment.CENTER).setFontSize(7).setFont(font_normal);
                celda.add(new Paragraph(factura.getNumerofactura().toString()));
                tabla.addCell(celda);

                celda = new Cell().setTextAlignment(TextAlignment.CENTER).setFontSize(7).setFont(font_normal);
                celda.add(new Paragraph(fechaFormat.format(factura.getFechaemision())));
                tabla.addCell(celda);

                celda = new Cell().setTextAlignment(TextAlignment.LEFT).setFontSize(7).setFont(font_normal);
                celda.add(new Paragraph(cliente.getCliente()));
                tabla.addCell(celda);

                celda = new Cell().setTextAlignment(TextAlignment.CENTER).setFontSize(7).setFont(font_normal);
                celda.add(new Paragraph(cliente.getNumerodocumento()));
                tabla.addCell(celda);

                celda = new Cell().setTextAlignment(TextAlignment.RIGHT).setFontSize(7).setFont(font_normal);
                celda.add(new Paragraph(decimalFormat.format(factura.getTotal())));
                tabla.addCell(celda);

                celda = new Cell().setTextAlignment(TextAlignment.RIGHT).setFontSize(7).setFont(font_normal);
                celda.add(new Paragraph(decimalFormat.format(factura.getDescuento())));
                tabla.addCell(celda);

                celda = new Cell().setTextAlignment(TextAlignment.RIGHT).setFontSize(7).setFont(font_normal);
                celda.add(new Paragraph(decimalFormat.format(factura.getTotal().subtract(factura.getDescuento()))));
                tabla.addCell(celda);

                sumatotal = sumatotal.add(factura.getTotal());
                sumatotalapagar = sumatotalapagar.add(factura.getTotal().subtract(factura.getDescuento()));
                sumadescuento = sumadescuento.add(factura.getDescuento());

                contador++;
            }

            celda = new Cell(1, 5).setTextAlignment(TextAlignment.RIGHT).setFontSize(7).setFont(font_bold);
            celda.add(new Paragraph("TOTALES"));
            tabla.addCell(celda);

            celda = new Cell().setTextAlignment(TextAlignment.RIGHT).setFontSize(7).setFont(font_bold);
            celda.add(new Paragraph(decimalFormat.format(sumatotal)));
            tabla.addCell(celda);

            celda = new Cell().setTextAlignment(TextAlignment.RIGHT).setFontSize(7).setFont(font_bold);
            celda.add(new Paragraph(decimalFormat.format(sumadescuento)));
            tabla.addCell(celda);

            celda = new Cell().setTextAlignment(TextAlignment.RIGHT).setFontSize(7).setFont(font_bold);
            celda.add(new Paragraph(decimalFormat.format(sumatotalapagar)));
            tabla.addCell(celda);

            documento.add(tabla);
            documento.close();
        } catch (Exception e) {
            System.out.println("Error: "+e.toString());
        }
        return salida.toByteArray();
    }

    @Override
    public byte[] reporteGeneralPDF(LocalDate fecha) {
        Authentication autentificado = SecurityContextHolder.getContext().getAuthentication();
        Long idusuario = Long.parseLong(autentificado.getName());
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        DateTimeFormatter fechaFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        ByteArrayOutputStream salida = new ByteArrayOutputStream();
        try {
            PdfWriter escribir = new PdfWriter(salida);
            PdfDocument pdf = new PdfDocument(escribir);
	        Document documento = new Document(pdf, PageSize.LETTER);
		    documento.setMargins(83f, 30f, 35f, 30f);
            PdfFont font_normal = PdfFontFactory.createFont(StandardFonts.HELVETICA);
            PdfFont font_bold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);

            FormatoReportes evento = new FormatoReportes(documento, "GENERAL");
            pdf.addEventHandler(PdfDocumentEvent.END_PAGE, evento);


            String[] tituloscolumnas = { "Nro.", "Nro. Factura", "Fecha emision", "Cliente", "C.I. o NIT", "Total", "Descuento", "Total a Pagar"};

            float[] ancho = { 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f};
            Table tabla = new Table(ancho);
            tabla.setWidth(UnitValue.createPercentValue(100));

            Cell celda;

            for (int col = 0; col < tituloscolumnas.length; col++) {
                celda = new Cell()
                        .setTextAlignment(TextAlignment.CENTER)
                        .setFontSize(7)
                        .setFont(font_bold);
                celda.add(new Paragraph(tituloscolumnas[col]));
                tabla.addHeaderCell(celda);
            }

            Integer contador = 1;

            List<Facturas> datos = facturasRepositorio.obtenerFacturasGeneral(idusuario, fecha);
            System.out.println(datos);

            BigDecimal sumatotal = BigDecimal.ZERO;
            BigDecimal sumatotalapagar = BigDecimal.ZERO;
            BigDecimal sumadescuento = BigDecimal.ZERO;

            for (Facturas factura : datos) {

                Clientes cliente = clientesRepositorio.obtenerPorId(factura.getIdcliente());

                celda = new Cell().setTextAlignment(TextAlignment.CENTER).setFontSize(7).setFont(font_normal);
                celda.add(new Paragraph(contador.toString()));
                tabla.addCell(celda);

                celda = new Cell().setTextAlignment(TextAlignment.CENTER).setFontSize(7).setFont(font_normal);
                celda.add(new Paragraph(factura.getNumerofactura().toString()));
                tabla.addCell(celda);

                celda = new Cell().setTextAlignment(TextAlignment.CENTER).setFontSize(7).setFont(font_normal);
                celda.add(new Paragraph(fechaFormat.format(factura.getFechaemision())));
                tabla.addCell(celda);

                celda = new Cell().setTextAlignment(TextAlignment.LEFT).setFontSize(7).setFont(font_normal);
                celda.add(new Paragraph(cliente.getCliente()));
                tabla.addCell(celda);

                celda = new Cell().setTextAlignment(TextAlignment.CENTER).setFontSize(7).setFont(font_normal);
                celda.add(new Paragraph(cliente.getNumerodocumento()));
                tabla.addCell(celda);

                celda = new Cell().setTextAlignment(TextAlignment.RIGHT).setFontSize(7).setFont(font_normal);
                celda.add(new Paragraph(decimalFormat.format(factura.getTotal())));
                tabla.addCell(celda);

                celda = new Cell().setTextAlignment(TextAlignment.RIGHT).setFontSize(7).setFont(font_normal);
                celda.add(new Paragraph(decimalFormat.format(factura.getDescuento())));
                tabla.addCell(celda);

                celda = new Cell().setTextAlignment(TextAlignment.RIGHT).setFontSize(7).setFont(font_normal);
                celda.add(new Paragraph(decimalFormat.format(factura.getTotal().subtract(factura.getDescuento()))));
                tabla.addCell(celda);

                sumatotal = sumatotal.add(factura.getTotal());
                sumatotalapagar = sumatotalapagar.add(factura.getTotal().subtract(factura.getDescuento()));
                sumadescuento = sumadescuento.add(factura.getDescuento());

                contador++;
            }

            celda = new Cell(1, 5).setTextAlignment(TextAlignment.RIGHT).setFontSize(7).setFont(font_bold);
            celda.add(new Paragraph("TOTALES"));
            tabla.addCell(celda);

            celda = new Cell().setTextAlignment(TextAlignment.RIGHT).setFontSize(7).setFont(font_bold);
            celda.add(new Paragraph(decimalFormat.format(sumatotal)));
            tabla.addCell(celda);

            celda = new Cell().setTextAlignment(TextAlignment.RIGHT).setFontSize(7).setFont(font_bold);
            celda.add(new Paragraph(decimalFormat.format(sumadescuento)));
            tabla.addCell(celda);

            celda = new Cell().setTextAlignment(TextAlignment.RIGHT).setFontSize(7).setFont(font_bold);
            celda.add(new Paragraph(decimalFormat.format(sumatotalapagar)));
            tabla.addCell(celda);

            documento.add(tabla);
            documento.close();
        } catch (Exception e) {
            System.out.println("Error: "+e.toString());
        }
        return salida.toByteArray();
    }

}
