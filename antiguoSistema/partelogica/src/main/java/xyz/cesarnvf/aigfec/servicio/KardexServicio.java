package xyz.cesarnvf.aigfec.servicio;

import java.util.List;

import xyz.cesarnvf.aigfec.modelo.otd.KardexOTD;

public interface KardexServicio {

    List<KardexOTD> obtenerPorIdPedido (Long id_pedido);
}
