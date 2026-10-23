package xyz.cesarnvf.aigfec.servicio.implementacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import xyz.cesarnvf.aigfec.modelo.Sucursales;
import xyz.cesarnvf.aigfec.repositorio.SucursalesRepositorio;
import xyz.cesarnvf.aigfec.servicio.BitacorasServicio;
import xyz.cesarnvf.aigfec.servicio.SucursalesServicio;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SucursalesServicioImplementacion implements SucursalesServicio{

    SucursalesRepositorio sucursalesRepositorio;

    BitacorasServicio bitacorasServicio;

    @Override
    public List<Sucursales> obtenerTodos(Long idempresa) {
        return sucursalesRepositorio.obtenerTodos(idempresa);
    }

    @Override
    public List<Sucursales> obtenerPorTerminoConPaginacion(String termino, Integer pagina, Integer cantidad, Long idempresa) {
        int nropagina = 0;
        if ((pagina - 1) * cantidad < 0) {
            nropagina = 0;
        } else {
            nropagina = (pagina - 1) * cantidad;
        }
        return sucursalesRepositorio.obtenerPorTerminoConPaginacion(termino, nropagina, cantidad, idempresa);
    }

    @Override
    public Integer obtenerCantidad(String termino, Long idempresa) {
        return sucursalesRepositorio.obtenerCantidad(termino, idempresa);
    }

    @Override
    public Sucursales obtenerPorId(Long id) {
        return sucursalesRepositorio.obtenerPorId(id);
    }

    @Override
    public void adicionar(Sucursales dato) {
        sucursalesRepositorio.adicionar(dato);
        bitacorasServicio.registrar("ADICIONAR - Datos: "+dato.toString());
    }

    @Override
    public void modificar(Sucursales dato) {
        sucursalesRepositorio.modificar(dato);
        bitacorasServicio.registrar("MODIFICAR - Datos: "+dato.toString());
    }

    @Override
    public void borrar(Long id) {
        sucursalesRepositorio.borrar(id);
    }

}
