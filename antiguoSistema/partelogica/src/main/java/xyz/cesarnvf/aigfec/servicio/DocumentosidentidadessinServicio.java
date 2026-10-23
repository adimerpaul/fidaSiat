package xyz.cesarnvf.aigfec.servicio;

import java.rmi.RemoteException;
import java.util.List;

import xyz.cesarnvf.aigfec.modelo.Documentosidentidadessin;

public interface DocumentosidentidadessinServicio {

    List<Documentosidentidadessin> obtenerTodos();

    List<Documentosidentidadessin> obtenerPorTerminoConPaginacion(String termino, Integer pagina, Integer cantidad);

    Integer obtenerCantidad(String termino);

    void obtenerCodigosSin(Long eidpuntoventa) throws RemoteException;

}
