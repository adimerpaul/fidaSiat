package xyz.cesarnvf.aigfec.servicioPDF;

import java.time.LocalDate;

public interface FacturasReportesServicioPDF {

    byte[] reporteValidosPDF(LocalDate fecha);

    byte[] reporteAnuladosPDF(LocalDate fecha);

    byte[] reporteRechazadosAnuladosPDF(LocalDate fecha);

    byte[] reporteOtrosPDF(LocalDate fecha);

    byte[] reporteGeneralPDF(LocalDate fecha);
}
