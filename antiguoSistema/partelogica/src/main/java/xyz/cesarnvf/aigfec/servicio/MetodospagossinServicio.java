package xyz.cesarnvf.aigfec.servicio;

import java.rmi.RemoteException;
import java.util.List;

import xyz.cesarnvf.aigfec.modelo.Metodospagossin;

public interface MetodospagossinServicio {

    List<Metodospagossin> obtenerTodos();

    List<Metodospagossin> obtenerPorTerminoConPaginacion(String termino, Integer pagina, Integer cantidad);

    Integer obtenerCantidad(String termino);

    void obtenerCodigosSin(Long eidpuntoventa) throws RemoteException;
}
