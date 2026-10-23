package xyz.cesarnvf.aigfec.servicio.implementacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import xyz.cesarnvf.aigfec.modelo.Empresas;
import xyz.cesarnvf.aigfec.repositorio.EmpresasRepositorio;
import xyz.cesarnvf.aigfec.servicio.BitacorasServicio;
import xyz.cesarnvf.aigfec.servicio.EmpresasServicio;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EmpresasServicioImplementacion implements EmpresasServicio{

    EmpresasRepositorio empresasRepositorio;

    BitacorasServicio bitacorasServicio;

    @Override
    public List<Empresas> obtenerTodos() {
        return empresasRepositorio.obtenerTodos();
    }

    @Override
    public List<Empresas> obtenerPorTerminoConPaginacion(String termino, Integer pagina, Integer cantidad) {
        int nropagina = 0;
        if ((pagina - 1) * cantidad < 0) {
            nropagina = 0;
        } else {
            nropagina = (pagina - 1) * cantidad;
        }
        return empresasRepositorio.obtenerPorTerminoConPaginacion(termino, nropagina, cantidad);
    }

    @Override
    public Integer obtenerCantidad(String termino) {
        return empresasRepositorio.obtenerCantidad(termino);
    }

    @Override
    public Empresas obtenerPorId(Long id) {
        return empresasRepositorio.obtenerPorId(id);
    }

    @Override
    public void adicionar(Empresas dato) {
        empresasRepositorio.adicionar(dato);
        bitacorasServicio.registrar("ADICIONAR - Datos: "+dato.toString());
    }

    @Override
    public void modificar(Empresas dato) {
        empresasRepositorio.modificar(dato);
        bitacorasServicio.registrar("MODIFICAR - Datos: "+dato.toString());
    }

}
