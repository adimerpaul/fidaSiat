package xyz.cesarnvf.aigfec.servicio;

import java.rmi.RemoteException;
import java.util.List;

import xyz.cesarnvf.aigfec.modelo.otd.NotasentregaOTD;

public interface NotasentregaServicio {

    List<NotasentregaOTD> obtenerNotasentrega(String termino);

    NotasentregaOTD obtenerPorId(Long id);

    Long migrarNotasEntrega(Long id_pedido) throws RemoteException, Exception;

}
