package xyz.cesarnvf.aigfec.servicio;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.List;

import xyz.cesarnvf.aigfec.modelo.otd.DetallesOTD;
import xyz.cesarnvf.aigfec.modelo.otd.FacturasOTD;

public interface FacturasServicio {

    List<FacturasOTD> obtenerPorTerminoConPaginacion(String termino, Integer pagina, Integer cantidad);

    List<FacturasOTD> obtenerPorTerminoIdpedido();

    List<FacturasOTD> obtenerNoEnviados();

    List<FacturasOTD> obtenerNoEnviadoso();

    Integer obtenerCantidad(String termino);

    FacturasOTD obtenerPorId(Long id);

    Long adicionar(FacturasOTD dato) throws RemoteException, Exception;

    BigDecimal obtenerDescuento(List<DetallesOTD> datos, Long idcliente, Long idproducto);

}
