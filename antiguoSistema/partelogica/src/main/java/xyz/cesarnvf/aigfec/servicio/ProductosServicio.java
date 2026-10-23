package xyz.cesarnvf.aigfec.servicio;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import xyz.cesarnvf.aigfec.modelo.otd.ProductosOTD;

public interface ProductosServicio {

    List<ProductosOTD> obtenerTodos();

    List<ProductosOTD> obtenerPorTerminoConPaginacion(String termino, Integer pagina, Integer cantidad);

    Integer obtenerCantidad(String termino);

    ProductosOTD obtenerPorId(Long id);

    void adicionar(ProductosOTD dato);

    void modificar(ProductosOTD dato);

    public void cargarFotografiaProducto(Long id, MultipartFile archivo);

    public Resource descargarFotografiaProducto(String nombreArchivo);

    List<ProductosOTD> obtenerPorIdgrupoConPaginacion(Long idgrupo, Integer pagina, Integer cantidad);

    Integer obtenerCantidadPorIdgrupo(Long idgrupo);

    ProductosOTD obtenerPorCodigocorto(Integer codigocroto);

}
