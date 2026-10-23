package xyz.cesarnvf.aigfec.servicio.implementacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import xyz.cesarnvf.aigfec.modelo.Grupos;
import xyz.cesarnvf.aigfec.repositorio.GruposRepositorio;
import xyz.cesarnvf.aigfec.servicio.BitacorasServicio;
import xyz.cesarnvf.aigfec.servicio.GruposServicio;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class GruposServicioImplementacion implements GruposServicio{

    GruposRepositorio gruposRepositorio;

    BitacorasServicio bitacorasServicio;

    @Override
    public List<Grupos> obtenerTodos() {
        return gruposRepositorio.obtenerTodos();
    }

    @Override
    public List<Grupos> obtenerTodosPorTipo(Integer tipo) {
        return gruposRepositorio.obtenerTodosPorTipo(tipo);
    }

    @Override
    public List<Grupos> obtenerPorTerminoConPaginacion(String termino, Integer pagina, Integer cantidad) {
        int nropagina = 0;
        if ((pagina - 1) * cantidad < 0) {
            nropagina = 0;
        } else {
            nropagina = (pagina - 1) * cantidad;
        }
        return gruposRepositorio.obtenerPorTerminoConPaginacion(termino, nropagina, cantidad);
    }

    @Override
    public Integer obtenerCantidad(String termino) {
        return gruposRepositorio.obtenerCantidad(termino);
    }

    @Override
    public Grupos obtenerPorId(Long id) {
        return gruposRepositorio.obtenerPorId(id);
    }

    @Override
    public void adicionar(Grupos dato) {
        gruposRepositorio.adicionar(dato);
        bitacorasServicio.registrar("ADICIONAR - Datos: "+dato.toString());
    }

    @Override
    public void modificar(Grupos dato) {
        gruposRepositorio.modificar(dato);
        bitacorasServicio.registrar("MODIFICAR - Datos: "+dato.toString());
    }

}
