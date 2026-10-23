package xyz.cesarnvf.aigfec.servicio.implementacion;

import java.rmi.RemoteException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.gob.impuestos.siat.ParametricasDto;
import lombok.AllArgsConstructor;
import xyz.cesarnvf.aigfec.modelo.Metodospagossin;
import xyz.cesarnvf.aigfec.modelo.auxi.Respuestastiposmetodopago;
import xyz.cesarnvf.aigfec.repositorio.MetodospagossinRepositorio;
import xyz.cesarnvf.aigfec.servicio.FuncionesSIATServicio;
import xyz.cesarnvf.aigfec.servicio.MetodospagossinServicio;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MetodospagossinServicioImplementacion implements MetodospagossinServicio {

    MetodospagossinRepositorio metodospagossinRepositorio;

    FuncionesSIATServicio funcionesSIATServicio;

    @Override
    public List<Metodospagossin> obtenerTodos() {
        return metodospagossinRepositorio.obtenerTodos();
    }

    @Override
    public List<Metodospagossin> obtenerPorTerminoConPaginacion(String termino, Integer pagina, Integer cantidad) {
        int nropagina = 0;
        if ((pagina - 1) * cantidad < 0) {
            nropagina = 0;
        } else {
            nropagina = (pagina - 1) * cantidad;
        }
        return metodospagossinRepositorio.obtenerPorTerminoConPaginacion(termino, nropagina, cantidad);
    }

    @Override
    public Integer obtenerCantidad(String termino) {
        return metodospagossinRepositorio.obtenerCantidad(termino);
    }

    @Override
    public void obtenerCodigosSin(Long eidpuntoventa) throws RemoteException {

        Respuestastiposmetodopago sinmetodospagos = funcionesSIATServicio.obtenerTipoMetodoPago(eidpuntoventa);
        for (int i = 0; i < sinmetodospagos.getTiposmetodopago().length; i++) {
            ParametricasDto dato = sinmetodospagos.getTiposmetodopago()[i];
            Metodospagossin entrada = metodospagossinRepositorio.verificarCodigosin(dato.getCodigoClasificador());
            if (entrada==null) {
                Metodospagossin nuevo = new Metodospagossin();
                nuevo.setCodigosin(dato.getCodigoClasificador());
                nuevo.setMetodopagosin(dato.getDescripcion());
                metodospagossinRepositorio.adicionar(nuevo);
            }
        }

    }

}
