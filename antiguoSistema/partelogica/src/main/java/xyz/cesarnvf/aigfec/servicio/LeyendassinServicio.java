package xyz.cesarnvf.aigfec.servicio;

import java.rmi.RemoteException;
import java.util.List;

import xyz.cesarnvf.aigfec.modelo.Leyendassin;

public interface LeyendassinServicio {

    List<Leyendassin> obtenerTodos();

    List<Leyendassin> obtenerPorTerminoConPaginacion(String termino, Integer pagina, Integer cantidad);

    Integer obtenerCantidad(String termino);

    void obtenerCodigosSin(Long eidpuntoventa) throws RemoteException;
}
