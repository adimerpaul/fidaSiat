package xyz.cesarnvf.aigfec.servicio.implementacion;

import java.rmi.RemoteException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.gob.impuestos.siat.ParametricasDto;
import lombok.AllArgsConstructor;
import xyz.cesarnvf.aigfec.modelo.Documentosidentidadessin;
import xyz.cesarnvf.aigfec.modelo.auxi.Respuestastiposdocumentosidentidad;
import xyz.cesarnvf.aigfec.repositorio.DocumentosidentidadessinRepositorio;
import xyz.cesarnvf.aigfec.servicio.DocumentosidentidadessinServicio;
import xyz.cesarnvf.aigfec.servicio.FuncionesSIATServicio;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DocumentosidentidadessinServicioImplementacion implements DocumentosidentidadessinServicio{

    DocumentosidentidadessinRepositorio documentosidentidadessinRepositorio;

    FuncionesSIATServicio funcionesSIATServicio;

    @Override
    public List<Documentosidentidadessin> obtenerTodos() {
        return documentosidentidadessinRepositorio.obtenerTodos();
    }

    @Override
    public List<Documentosidentidadessin> obtenerPorTerminoConPaginacion(String termino, Integer pagina, Integer cantidad) {
        int nropagina = 0;
        if ((pagina - 1) * cantidad < 0) {
            nropagina = 0;
        } else {
            nropagina = (pagina - 1) * cantidad;
        }
        return documentosidentidadessinRepositorio.obtenerPorTerminoConPaginacion(termino, nropagina, cantidad);
    }

    @Override
    public Integer obtenerCantidad(String termino) {
        return documentosidentidadessinRepositorio.obtenerCantidad(termino);
    }

    @Override
    public void obtenerCodigosSin(Long eidpuntoventa) throws RemoteException {

        Respuestastiposdocumentosidentidad sindocumentosidentidades = funcionesSIATServicio.obtenerTipoDocumentoIdentidad(eidpuntoventa);
        for (int i = 0; i < sindocumentosidentidades.getTiposdocumentosidentidad().length; i++) {
            ParametricasDto dato = sindocumentosidentidades.getTiposdocumentosidentidad()[i];
            Documentosidentidadessin entrada = documentosidentidadessinRepositorio.verificarCodigosin(dato.getCodigoClasificador());
            if (entrada==null) {
                Documentosidentidadessin nuevo = new Documentosidentidadessin();
                nuevo.setCodigosin(dato.getCodigoClasificador());
                nuevo.setDocumentoidentidadsin(dato.getDescripcion());
                documentosidentidadessinRepositorio.adicionar(nuevo);
            }
        }

    }



}
