package xyz.cesarnvf.aigfec.servicio;

import java.rmi.RemoteException;
import java.util.List;

import xyz.cesarnvf.aigfec.modelo.Eventossignificativossin;

public interface EventossignificativossinServicio {

    List<Eventossignificativossin> obtenerTodos();

    List<Eventossignificativossin> obtenerPorTerminoConPaginacion(String termino, Integer pagina, Integer cantidad);

    Integer obtenerCantidad(String termino);

    void obtenerCodigosSin(Long eidpuntoventa) throws RemoteException;
}
