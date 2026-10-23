package xyz.cesarnvf.aigfec.servicioPDF.implementacion;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.core.io.ClassPathResource;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.properties.VerticalAlignment;

public class FormatoReportes  implements IEventHandler{

    private final Document documento;
    private String titulo;

    public FormatoReportes(Document doc, String titulo) {
        this.titulo = titulo;
        documento = doc;
    }

    private Rectangle crearRectanguloEncabezado(PdfDocumentEvent docEvent) {
        PdfDocument pdfDoc = docEvent.getDocument();
        PdfPage page = docEvent.getPage();

        float xEncabezado = pdfDoc.getDefaultPageSize().getX() + documento.getLeftMargin();
        float yEncabezado = pdfDoc.getDefaultPageSize().getTop() - documento.getTopMargin();
        float anchoEncabezado = page.getPageSize().getWidth() - 60;
        float altoEncabezado = 50F;

        Rectangle rectanguloEncabezado = new Rectangle(xEncabezado, yEncabezado, anchoEncabezado, altoEncabezado);

        return rectanguloEncabezado;
    }

    private Rectangle crearRectanguloPie(PdfDocumentEvent docEvent) {
        PdfDocument pdfDoc = docEvent.getDocument();
        PdfPage page = docEvent.getPage();

        float xPie = pdfDoc.getDefaultPageSize().getX() + documento.getLeftMargin();
        float yPie = pdfDoc.getDefaultPageSize().getBottom() ;
        float anchoPie = page.getPageSize().getWidth() - 60;
        float altoPie = 30F;

        Rectangle rectanguloPie = new Rectangle(xPie, yPie, anchoPie, altoPie);

        return rectanguloPie;
    }

    private Table crearTablaEncabezado(PdfDocumentEvent docEvent, String titulo) {

        PdfPage page = docEvent.getPage();
        PdfFont font_normal = null;
        PdfFont font_bold = null;
        ImageData logoutodata = null;
        Integer pageNum = docEvent.getDocument().getPageNumber(page);

        DateTimeFormatter fechaFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter horaFormat = DateTimeFormatter.ofPattern("HH:mm:ss");

        try {
            font_normal = PdfFontFactory.createFont(StandardFonts.HELVETICA);
            font_bold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
            logoutodata = ImageDataFactory.create((new ClassPathResource("static/imagenes/logo.png")).getURL());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Image logouto = new Image(logoutodata);
        logouto.setFixedPosition(33, 728);
        logouto.scaleAbsolute(80, 27);

        Paragraph datos = new Paragraph().add(new Text(" Fecha: "+fechaFormat.format(LocalDate.now())+"\nHora: "+horaFormat.format(LocalTime.now())+"\nPágina: "+pageNum).setFontSize(8).setFont(font_normal));

        Paragraph ptitulo = new Paragraph().add(new Text(titulo).setFontSize(16).setFont(font_bold));

        float[] anchos = {30f, 90f, 30f};
        Table tablaEncabezado = new Table(UnitValue.createPercentArray(anchos));
        tablaEncabezado.setWidth(UnitValue.createPercentValue(100));
        tablaEncabezado.setFixedLayout();

        Cell celda = new Cell();
        celda.add(logouto);
        celda.setBorder(Border.NO_BORDER);
        celda.setBorderBottom(new SolidBorder(0.05f));
        tablaEncabezado.addCell(celda);

        celda = new Cell();
        celda.add(ptitulo);
        celda.setTextAlignment(TextAlignment.CENTER);
        celda.setVerticalAlignment(VerticalAlignment.MIDDLE);
        celda.setBorder(Border.NO_BORDER);
        celda.setBorderBottom(new SolidBorder(0.05f));
        tablaEncabezado.addCell(celda);

        celda = new Cell();
        celda.add(datos);
        celda.setBorder(Border.NO_BORDER);
        celda.setBorderBottom(new SolidBorder(0.05f));
        celda.setTextAlignment(TextAlignment.RIGHT);
        tablaEncabezado.addCell(celda);

        return tablaEncabezado;
    }

    private Table crearTablaPie(PdfDocumentEvent docEvent) {

        PdfFont font_normal = null;
        try {
            font_normal = PdfFontFactory.createFont(StandardFonts.HELVETICA);
        } catch (IOException e) {
            e.printStackTrace();
        }

        float[] anchos = {1f};
        Table tablaPie = new Table(UnitValue.createPercentArray(anchos));
        tablaPie.setWidth(UnitValue.createPercentValue(100));
        tablaPie.setFixedLayout();

        Paragraph sico = new Paragraph().add(new Text("SICO - Sistema de Información Contable").setFontSize(8).setFont(font_normal));

        Cell celda = new Cell();
        celda.add(sico);
        celda.setTextAlignment(TextAlignment.CENTER);
        celda.setVerticalAlignment(VerticalAlignment.MIDDLE);
        celda.setBorder(Border.NO_BORDER);
        celda.setBorderTop(new SolidBorder(0.05f));
        tablaPie.addCell(celda);

        return tablaPie;
    }

    @Override
    public void handleEvent(Event event) {
        PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
        PdfDocument pdfDoc = docEvent.getDocument();
        PdfPage page = docEvent.getPage();
        PdfCanvas canvas = new PdfCanvas(page.newContentStreamBefore(), page.getResources(), pdfDoc);

        Table tablaEncabezado = this.crearTablaEncabezado(docEvent, titulo);
        Rectangle rectanguloEncabezado = this.crearRectanguloEncabezado(docEvent);
        try (Canvas canvasEncabezado = new Canvas(canvas, rectanguloEncabezado)) {
            canvasEncabezado.add(tablaEncabezado);
        }
        Table tablaNumeracion = this.crearTablaPie(docEvent);
        Rectangle rectanguloPie = this.crearRectanguloPie(docEvent);
        try (Canvas canvasPie = new Canvas(canvas, rectanguloPie)) {
            canvasPie.add(tablaNumeracion);
        }
    }

}
