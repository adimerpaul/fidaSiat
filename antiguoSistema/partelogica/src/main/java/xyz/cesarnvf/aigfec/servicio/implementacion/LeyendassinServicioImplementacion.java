package xyz.cesarnvf.aigfec.servicio.implementacion;

import java.rmi.RemoteException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.gob.impuestos.siat.ParametricaLeyendasDto;
import lombok.AllArgsConstructor;
import xyz.cesarnvf.aigfec.modelo.Leyendassin;
import xyz.cesarnvf.aigfec.modelo.auxi.Respuestasleyendas;
import xyz.cesarnvf.aigfec.repositorio.LeyendassinRepositorio;
import xyz.cesarnvf.aigfec.servicio.FuncionesSIATServicio;
import xyz.cesarnvf.aigfec.servicio.LeyendassinServicio;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class LeyendassinServicioImplementacion implements LeyendassinServicio{

    LeyendassinRepositorio leyendassinRepositorio;

    FuncionesSIATServicio funcionesSIATServicio;

    @Override
    public List<Leyendassin> obtenerTodos() {
        return leyendassinRepositorio.obtenerTodos();
    }

    @Override
    public List<Leyendassin> obtenerPorTerminoConPaginacion(String termino, Integer pagina, Integer cantidad) {
        int nropagina = 0;
        if ((pagina - 1) * cantidad < 0) {
            nropagina = 0;
        } else {
            nropagina = (pagina - 1) * cantidad;
        }
        return leyendassinRepositorio.obtenerPorTerminoConPaginacion(termino, nropagina, cantidad);
    }

    @Override
    public Integer obtenerCantidad(String termino) {
        return leyendassinRepositorio.obtenerCantidad(termino);
    }

    @Override
    public void obtenerCodigosSin(Long eidpuntoventa) throws RemoteException {

        Respuestasleyendas sinleyendas = funcionesSIATServicio.obtenerLeyendas(eidpuntoventa);
        for (int i = 0; i < sinleyendas.getLeyendas().length; i++) {
            ParametricaLeyendasDto dato = sinleyendas.getLeyendas()[i];
            Leyendassin entrada = leyendassinRepositorio.verificarCodigosin(Integer.parseInt(dato.getCodigoActividad()), dato.getDescripcionLeyenda());
            if (entrada==null) {
                Leyendassin nuevo = new Leyendassin();
                nuevo.setCodigosin(Integer.parseInt(dato.getCodigoActividad()));
                nuevo.setLeyendasin(dato.getDescripcionLeyenda());
                leyendassinRepositorio.adicionar(nuevo);
            }
        }

    }

}

