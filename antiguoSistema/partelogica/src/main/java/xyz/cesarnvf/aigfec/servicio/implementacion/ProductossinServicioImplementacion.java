package xyz.cesarnvf.aigfec.servicio.implementacion;

import java.rmi.RemoteException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.gob.impuestos.siat.ProductosDto;
import lombok.AllArgsConstructor;
import xyz.cesarnvf.aigfec.modelo.Productossin;
import xyz.cesarnvf.aigfec.modelo.auxi.Respuestasproductos;
import xyz.cesarnvf.aigfec.repositorio.ProductossinRepositorio;
import xyz.cesarnvf.aigfec.servicio.FuncionesSIATServicio;
import xyz.cesarnvf.aigfec.servicio.ProductossinServicio;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProductossinServicioImplementacion implements ProductossinServicio{

    ProductossinRepositorio productossinRepositorio;

    FuncionesSIATServicio funcionesSIATServicio;

    @Override
    public List<Productossin> obtenerTodos() {
        return productossinRepositorio.obtenerTodos();
    }

    @Override
    public List<Productossin> obtenerPorTerminoConPaginacion(String termino, Integer pagina, Integer cantidad) {
        int nropagina = 0;
        if ((pagina - 1) * cantidad < 0) {
            nropagina = 0;
        } else {
            nropagina = (pagina - 1) * cantidad;
        }
        return productossinRepositorio.obtenerPorTerminoConPaginacion(termino, nropagina, cantidad);
    }

    @Override
    public Integer obtenerCantidad(String termino) {
        return productossinRepositorio.obtenerCantidad(termino);
    }

    @Override
    public void obtenerCodigosSin(Long eidpuntoventa) throws RemoteException {

        Respuestasproductos sinproductossin = funcionesSIATServicio.obtenerProductos(eidpuntoventa);
        for (int i = 0; i < sinproductossin.getProductos().length; i++) {
            ProductosDto dato = sinproductossin.getProductos()[i];
            Productossin entrada = productossinRepositorio.verificarCodigosin(dato.getCodigoProducto().intValue(), Integer.parseInt(dato.getCodigoActividad()));
            if (entrada==null) {
                Productossin nuevo = new Productossin();
                nuevo.setCodigosin(dato.getCodigoProducto().intValue());
                nuevo.setCodigoactividadsin(Integer.parseInt(dato.getCodigoActividad()));
                nuevo.setProductosin(dato.getDescripcionProducto());
                productossinRepositorio.adicionar(nuevo);
            }
        }

    }

}
