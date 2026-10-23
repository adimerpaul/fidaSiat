package xyz.cesarnvf.aigfec.servicio.implementacion;

import java.rmi.RemoteException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.gob.impuestos.siat.ParametricasDto;
import lombok.AllArgsConstructor;
import xyz.cesarnvf.aigfec.modelo.Eventossignificativossin;
import xyz.cesarnvf.aigfec.modelo.auxi.Respuestaseventossignificativos;
import xyz.cesarnvf.aigfec.repositorio.EventossignificativossinRepositorio;
import xyz.cesarnvf.aigfec.servicio.EventossignificativossinServicio;
import xyz.cesarnvf.aigfec.servicio.FuncionesSIATServicio;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EventossignificativossinServicioImplementacion implements EventossignificativossinServicio{

    EventossignificativossinRepositorio eventossignificativossinRepositorio;

    FuncionesSIATServicio funcionesSIATServicio;

    @Override
    public List<Eventossignificativossin> obtenerTodos() {
        return eventossignificativossinRepositorio.obtenerTodos();
    }

    @Override
    public List<Eventossignificativossin> obtenerPorTerminoConPaginacion(String termino, Integer pagina, Integer cantidad) {
        int nropagina = 0;
        if ((pagina - 1) * cantidad < 0) {
            nropagina = 0;
        } else {
            nropagina = (pagina - 1) * cantidad;
        }
        return eventossignificativossinRepositorio.obtenerPorTerminoConPaginacion(termino, nropagina, cantidad);
    }

    @Override
    public Integer obtenerCantidad(String termino) {
        return eventossignificativossinRepositorio.obtenerCantidad(termino);
    }

    @Override
    public void obtenerCodigosSin(Long eidpuntoventa) throws RemoteException {

        Respuestaseventossignificativos sineventossginificativos = funcionesSIATServicio.obtenerEventosSignificativos(eidpuntoventa);
        for (int i = 0; i < sineventossginificativos.getEventossignificativos().length; i++) {
            ParametricasDto dato = sineventossginificativos.getEventossignificativos()[i];
            Eventossignificativossin entrada = eventossignificativossinRepositorio.verificarCodigosin(dato.getCodigoClasificador());
            if (entrada==null) {
                Eventossignificativossin nuevo = new Eventossignificativossin();
                nuevo.setCodigosin(dato.getCodigoClasificador());
                nuevo.setEventosignificativosin(dato.getDescripcion());
                eventossignificativossinRepositorio.adicionar(nuevo);
            }
        }

    }

}