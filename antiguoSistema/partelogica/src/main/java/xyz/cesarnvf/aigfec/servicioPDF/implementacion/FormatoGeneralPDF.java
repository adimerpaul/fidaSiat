package xyz.cesarnvf.aigfec.servicioPDF.implementacion;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.core.io.ClassPathResource;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.layout.LayoutArea;
import com.itextpdf.layout.layout.LayoutContext;
import com.itextpdf.layout.layout.LayoutResult;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.properties.VerticalAlignment;
import com.itextpdf.layout.renderer.DocumentRenderer;
import com.itextpdf.layout.renderer.TableRenderer;

public class FormatoGeneralPDF implements IEventHandler{

    private Table tabla;
    private float tablaAlto;
    private Document documento;
    private String titulo;
    private String usuario;
    private int numero_pagina;
    private Image piemteps;
    private Canvas canvas;


    public float getTablaAlto() {
        return this.tablaAlto;
    }

    public FormatoGeneralPDF(Document documento, String titulo, String usuario) throws IOException{
        this.documento = documento;
        this.titulo = titulo;
        this.usuario = usuario;

        inicializarTabla();

        TableRenderer renderer = (TableRenderer) tabla.createRendererSubTree();
        renderer.setParent(new DocumentRenderer(documento));

        LayoutResult result = renderer.layout(new LayoutContext(new LayoutArea(0, PageSize.LETTER)));
        tablaAlto = result.getOccupiedArea().getBBox().getHeight();

        ImageData piedata = ImageDataFactory.create((new ClassPathResource("static/imagenes/pie.png")).getURL());
        this.piemteps = new Image(piedata);
        this.piemteps.setFixedPosition(0, 0);
        this.piemteps.scaleAbsolute(612, 220);

    }

    @Override
    public void handleEvent(Event eventoactual) {
        PdfDocumentEvent docEvent = (PdfDocumentEvent) eventoactual;

        PdfDocument pdfDoc = docEvent.getDocument();
        PdfPage page = docEvent.getPage();
        PdfCanvas canvas = new PdfCanvas(page.newContentStreamBefore(), page.getResources(), pdfDoc);

        numero_pagina = docEvent.getDocument().getPageNumber(docEvent.getPage());

        PageSize pageSize = pdfDoc.getDefaultPageSize();
        float coordX = pageSize.getX() + documento.getLeftMargin();
        float coordY = pageSize.getTop() - documento.getTopMargin();
        float width = pageSize.getWidth() - documento.getRightMargin() - documento.getLeftMargin();
        float height = getTablaAlto();
        Rectangle rect = new Rectangle(coordX, coordY, width, height);

        Paragraph p = new Paragraph().add(new Text("Páginas: "+Integer.toString(numero_pagina)).setFontSize(8).setFontColor(ColorConstants.BLACK));
        Paragraph p1 = new Paragraph().add(new Text("Pie 1").setFontSize(9).setFontColor(ColorConstants.BLACK));
        Paragraph p2 = new Paragraph().add(new Text("Pie 2").setFontSize(9).setFontColor(ColorConstants.BLACK));

        this.canvas = new Canvas(canvas, rect);
        this.canvas
            .add(tabla)
            .add(piemteps)
            .showTextAligned(p, 520, 739, TextAlignment.RIGHT)
            .showTextAligned(p1, 310, 60, TextAlignment.CENTER)
            .showTextAligned(p2, 310, 6, TextAlignment.CENTER)
            .close();
    }

    private void inicializarTabla() throws IOException {

        ImageData logodata = ImageDataFactory.create((new ClassPathResource("static/imagenes/logohorizontal.png")).getURL());
        Image logomteps = new Image(logodata);

        logomteps.scaleAbsolute(160, 40);

        float[] cabecera = {96, 96, 96};
        tabla = new Table(cabecera);
        tabla.setWidth(UnitValue.createPercentValue(100));
        tabla.setFixedLayout();
        Cell celda = new Cell(3,1);
        celda.add(logomteps);
        tabla.addCell(celda);

        PdfFont font_normal = PdfFontFactory.createFont(StandardFonts.HELVETICA);
        PdfFont font_bold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);

        Paragraph p = new Paragraph().add(new Text(titulo.toUpperCase()).setFont(font_bold).setFontSize(10).setFontColor(ColorConstants.BLACK));

        celda = new Cell(3,1);
        celda.add(p);
        celda.setTextAlignment(TextAlignment.CENTER);
        celda.setVerticalAlignment(VerticalAlignment.MIDDLE);
        tabla.addCell(celda);

        DateTimeFormatter formateadorFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy', 'HH:mm");

        p = new Paragraph().add(new Text("Fecha: "+formateadorFecha.format(LocalDateTime.now())).setFont(font_normal).setFontSize(8).setFontColor(ColorConstants.BLACK));

        celda = new Cell();
        celda.add(p);
        celda.setHeight(12f);
        celda.setTextAlignment(TextAlignment.CENTER);
        tabla.addCell(celda);

        p = new Paragraph().add(new Text("Usuario: "+usuario).setFont(font_normal).setFontSize(8).setFontColor(ColorConstants.BLACK));

        celda = new Cell();
        celda.add(p);
        celda.setHeight(12f);
        celda.setTextAlignment(TextAlignment.CENTER);
        tabla.addCell(celda);

        p = new Paragraph().add(new Text(" ").setFont(font_normal).setFontSize(8).setFontColor(ColorConstants.BLACK));

        celda = new Cell();
        celda.add(p);
        celda.setHeight(12f);
        celda.setTextAlignment(TextAlignment.CENTER);
        tabla.addCell(celda);

        celda = new Cell(1, 3);
        celda.add(p);
        celda.setBorder(Border.NO_BORDER);
        celda.setHeight(10f);
        celda.setTextAlignment(TextAlignment.CENTER);
        celda.setVerticalAlignment(VerticalAlignment.MIDDLE);
        tabla.addCell(celda);

    }

}
