package xyz.cesarnvf.aigfec.servicio.implementacion;

import java.rmi.RemoteException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.gob.impuestos.siat.ParametricasDto;
import lombok.AllArgsConstructor;
import xyz.cesarnvf.aigfec.modelo.Unidadesmedidas;
import xyz.cesarnvf.aigfec.modelo.auxi.Respuestastiposunidadmedida;
import xyz.cesarnvf.aigfec.repositorio.UnidadesmedidasRepositorio;
import xyz.cesarnvf.aigfec.servicio.FuncionesSIATServicio;
import xyz.cesarnvf.aigfec.servicio.UnidadesmedidasServicio;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UnidadesmedidasServicioImplementacion implements UnidadesmedidasServicio{

    UnidadesmedidasRepositorio unidadesmedidasRepositorio;

    FuncionesSIATServicio funcionesSIATServicio;

    @Override
    public List<Unidadesmedidas> obtenerTodos() {
        return unidadesmedidasRepositorio.obtenerTodos();
    }

    @Override
    public void obtenerCodigosSin(Integer epuntoventa) throws RemoteException {

        Respuestastiposunidadmedida sinunidadesmedidas = funcionesSIATServicio.obtenerTipoUnidadMedida(epuntoventa);
        for (int i = 0; i < sinunidadesmedidas.getTiposunidadmedida().length; i++) {
            ParametricasDto dato = sinunidadesmedidas.getTiposunidadmedida()[i];
            Unidadesmedidas entrada = unidadesmedidasRepositorio.verificarCodigosin(dato.getCodigoClasificador());
            if (entrada==null) {
                Unidadesmedidas nuevo = new Unidadesmedidas();
                nuevo.setCodigosin(dato.getCodigoClasificador());
                nuevo.setUnidadmedida(dato.getDescripcion());
                nuevo.setSigla(dato.getDescripcion().substring(0, 2)+".");
                unidadesmedidasRepositorio.adicionar(nuevo);
            }
        }

    }

}
