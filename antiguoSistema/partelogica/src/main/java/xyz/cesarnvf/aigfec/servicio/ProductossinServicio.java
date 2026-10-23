package xyz.cesarnvf.aigfec.servicio;

import java.rmi.RemoteException;
import java.util.List;

import xyz.cesarnvf.aigfec.modelo.Productossin;

public interface ProductossinServicio {

    List<Productossin> obtenerTodos();

    List<Productossin> obtenerPorTerminoConPaginacion(String termino, Integer pagina, Integer cantidad);

    Integer obtenerCantidad(String termino);

    void obtenerCodigosSin(Long eidpuntoventa) throws RemoteException;
}
