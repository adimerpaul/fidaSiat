package xyz.cesarnvf.aigfec.servicioPDF;

public interface FacturasServicioPDF {

    byte[] generaFacturaPDF(Long idfactura);

    byte[] generaFacturaCPDF(Long idfactura);

    void guardaFacturaPDF(Long idfactura);
}
