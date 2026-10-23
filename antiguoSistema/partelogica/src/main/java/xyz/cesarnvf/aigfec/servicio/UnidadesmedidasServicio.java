package xyz.cesarnvf.aigfec.servicio;

import java.rmi.RemoteException;
import java.util.List;

import xyz.cesarnvf.aigfec.modelo.Unidadesmedidas;

public interface UnidadesmedidasServicio {

    List<Unidadesmedidas> obtenerTodos();

    void obtenerCodigosSin(Integer epuntoventa) throws RemoteException;
}
