package xyz.cesarnvf.aigfec.modelo.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "facturaElectronicaCompraVenta", propOrder = {
        "cabecera",
        "detalle"
})
@XmlRootElement(name = "facturaElectronicaCompraVenta")
public class FacturasXML {

    private CabecerasXML cabecera;
    private List<DetallesXML> detalle;
}
