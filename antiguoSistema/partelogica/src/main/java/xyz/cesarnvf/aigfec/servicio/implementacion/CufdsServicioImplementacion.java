package xyz.cesarnvf.aigfec.servicio.implementacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import xyz.cesarnvf.aigfec.modelo.Cufds;
import xyz.cesarnvf.aigfec.repositorio.CufdsRepositorio;
import xyz.cesarnvf.aigfec.servicio.CufdsServicio;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CufdsServicioImplementacion implements CufdsServicio{

    CufdsRepositorio cufdsRepositorio;

    @Override
    public List<Cufds> obtenerUltimos(Long idpuntoventa) {
        return cufdsRepositorio.obtenerUltimos(idpuntoventa);
    }

}
