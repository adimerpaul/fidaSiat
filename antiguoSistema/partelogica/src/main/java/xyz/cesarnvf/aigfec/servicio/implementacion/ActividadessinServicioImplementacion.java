package xyz.cesarnvf.aigfec.servicio.implementacion;

import java.rmi.RemoteException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.gob.impuestos.siat.ActividadesDto;
import lombok.AllArgsConstructor;
import xyz.cesarnvf.aigfec.modelo.Actividadessin;
import xyz.cesarnvf.aigfec.modelo.auxi.Respuestasactividades;
import xyz.cesarnvf.aigfec.repositorio.ActividadessinRepositorio;
import xyz.cesarnvf.aigfec.servicio.ActividadessinServicio;
import xyz.cesarnvf.aigfec.servicio.FuncionesSIATServicio;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ActividadessinServicioImplementacion implements ActividadessinServicio{

    ActividadessinRepositorio actividadessinRepositorio;

    FuncionesSIATServicio funcionesSIATServicio;

    @Override
    public List<Actividadessin> obtenerTodos() {
        return actividadessinRepositorio.obtenerTodos();
    }

    @Override
    public List<Actividadessin> obtenerPorTerminoConPaginacion(String termino, Integer pagina, Integer cantidad) {
        int nropagina = 0;
        if ((pagina - 1) * cantidad < 0) {
            nropagina = 0;
        } else {
            nropagina = (pagina - 1) * cantidad;
        }
        return actividadessinRepositorio.obtenerPorTerminoConPaginacion(termino, nropagina, cantidad);
    }

    @Override
    public Integer obtenerCantidad(String termino) {
        return actividadessinRepositorio.obtenerCantidad(termino);
    }

    @Override
    public void obtenerCodigosSin(Long idpuntoventa) throws RemoteException {

        Respuestasactividades sinactividades = funcionesSIATServicio.obtenerActividades(idpuntoventa);
        for (int i = 0; i < sinactividades.getActividades().length; i++) {
            ActividadesDto dato = sinactividades.getActividades()[i];
            Actividadessin entrada = actividadessinRepositorio.verificarCodigosin(Integer.parseInt(dato.getCodigoCaeb()));
            if (entrada==null) {
                Actividadessin nuevo = new Actividadessin();
                nuevo.setCodigosin(Integer.parseInt(dato.getCodigoCaeb()));
                nuevo.setActividadsin(dato.getDescripcion());
                actividadessinRepositorio.adicionar(nuevo);
            }
        }

    }



}
