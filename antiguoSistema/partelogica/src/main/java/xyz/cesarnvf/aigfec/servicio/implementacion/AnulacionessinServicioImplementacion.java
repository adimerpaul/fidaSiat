package xyz.cesarnvf.aigfec.servicio.implementacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import xyz.cesarnvf.aigfec.modelo.Anulacionessin;
import xyz.cesarnvf.aigfec.repositorio.AnulacionessinRepositorio;
import xyz.cesarnvf.aigfec.servicio.AnulacionessinServicio;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AnulacionessinServicioImplementacion implements AnulacionessinServicio{

    AnulacionessinRepositorio anulacionessinRepositorio;

    @Override
    public List<Anulacionessin> obtenerTodos() {
        return anulacionessinRepositorio.obtenerTodos();
    }

}
