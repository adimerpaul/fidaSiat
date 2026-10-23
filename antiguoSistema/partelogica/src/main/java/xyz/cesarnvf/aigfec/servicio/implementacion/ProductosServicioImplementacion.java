package xyz.cesarnvf.aigfec.servicio.implementacion;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import xyz.cesarnvf.aigfec.modelo.Grupos;
import xyz.cesarnvf.aigfec.modelo.Productos;
import xyz.cesarnvf.aigfec.modelo.Productossin;
import xyz.cesarnvf.aigfec.modelo.Unidadesmedidas;
import xyz.cesarnvf.aigfec.modelo.otd.ProductosOTD;
import xyz.cesarnvf.aigfec.repositorio.GruposRepositorio;
import xyz.cesarnvf.aigfec.repositorio.ProductosRepositorio;
import xyz.cesarnvf.aigfec.repositorio.ProductossinRepositorio;
import xyz.cesarnvf.aigfec.repositorio.UnidadesmedidasRepositorio;
import xyz.cesarnvf.aigfec.servicio.BitacorasServicio;
import xyz.cesarnvf.aigfec.servicio.ProductosServicio;

@Service
public class ProductosServicioImplementacion implements ProductosServicio{

    @Autowired
    GruposRepositorio gruposRepositorio;

    @Autowired
    ProductosRepositorio productosRepositorio;

    @Autowired
    ProductossinRepositorio productossinRepositorio;

    @Autowired
    UnidadesmedidasRepositorio unidadesmedidasRepositorio;

    @Autowired
    BitacorasServicio bitacorasServicio;

    @Value("${ruta.archivos}")
    private String rutaprincipal;



    @Override
    public List<ProductosOTD> obtenerTodos() {
        List<ProductosOTD> salida = new ArrayList<>();
        List<Productos> entrada = productosRepositorio.obtenerTodos();
        for (Productos producto : entrada) {
            Grupos grupo = gruposRepositorio.obtenerPorId(producto.getIdgrupo());
            Unidadesmedidas unidadmedida = unidadesmedidasRepositorio.obtenerPorId(producto.getIdunidadmedida());
            Productossin productosin = productossinRepositorio.obtenerPorId(producto.getIdproductosin());

            ProductosOTD datosalida = new ProductosOTD(producto.getIdproducto(), productosin.getIdproductosin(), productosin.getCodigosin(), productosin.getCodigoactividadsin(), grupo.getIdgrupo(), grupo.getGrupo(), unidadmedida.getIdunidadmedida(), unidadmedida.getCodigosin(),  unidadmedida.getUnidadmedida(), producto.getProducto(), producto.getCantidadcaja(), producto.getPreciounitario(), producto.getEnlaceproducto(), producto.getHabilitado(), producto.getCodigocorto(), producto.getDescuento());
            salida.add(datosalida);
        }
        return salida;
    }

    @Override
    public List<ProductosOTD> obtenerPorTerminoConPaginacion(String termino, Integer pagina, Integer cantidad) {
        int nropagina = 0;
        if ((pagina - 1) * cantidad < 0) {
            nropagina = 0;
        } else {
            nropagina = (pagina - 1) * cantidad;
        }
        List<ProductosOTD> salida = new ArrayList<>();
        List<Productos> entrada = productosRepositorio.obtenerPorTerminoConPaginacion(termino, nropagina, cantidad);
        for (Productos producto : entrada) {
            Grupos grupo = gruposRepositorio.obtenerPorId(producto.getIdgrupo());
            Unidadesmedidas unidadmedida = unidadesmedidasRepositorio.obtenerPorId(producto.getIdunidadmedida());
            Productossin productosin = productossinRepositorio.obtenerPorId(producto.getIdproductosin());

            ProductosOTD datosalida = new ProductosOTD(producto.getIdproducto(), productosin.getIdproductosin(), productosin.getCodigosin(), productosin.getCodigoactividadsin(), grupo.getIdgrupo(), grupo.getGrupo(), unidadmedida.getIdunidadmedida(), unidadmedida.getCodigosin(),  unidadmedida.getUnidadmedida(), producto.getProducto(), producto.getCantidadcaja(), producto.getPreciounitario(), producto.getEnlaceproducto(), producto.getHabilitado(), producto.getCodigocorto(), producto.getDescuento());
            salida.add(datosalida);
        }
        return salida;

    }

    @Override
    public Integer obtenerCantidad(String termino) {
        return productosRepositorio.obtenerCantidad(termino);
    }

    @Override
    public ProductosOTD obtenerPorId(Long id) {
        Productos producto = productosRepositorio.obtenerPorId(id);
        Grupos grupo = gruposRepositorio.obtenerPorId(producto.getIdgrupo());
        Unidadesmedidas unidadmedida = unidadesmedidasRepositorio.obtenerPorId(producto.getIdunidadmedida());
        Productossin productosin = productossinRepositorio.obtenerPorId(producto.getIdproductosin());

        ProductosOTD datosalida = new ProductosOTD(producto.getIdproducto(), productosin.getIdproductosin(), productosin.getCodigosin(), productosin.getCodigoactividadsin(), grupo.getIdgrupo(), grupo.getGrupo(), unidadmedida.getIdunidadmedida(), unidadmedida.getCodigosin(),  unidadmedida.getUnidadmedida(), producto.getProducto(), producto.getCantidadcaja(), producto.getPreciounitario(), producto.getEnlaceproducto(), producto.getHabilitado(), producto.getCodigocorto(), producto.getDescuento());
        return datosalida;
    }

    @Override
    public void adicionar(ProductosOTD dato) {
        Productos producto = new Productos();
        producto.setIdproductosin(dato.getIdproductosin());
        producto.setIdgrupo(dato.getIdgrupo());
        producto.setIdunidadmedida(dato.getIdunidadmedida());
        producto.setProducto(dato.getProducto());
        producto.setCantidadcaja(dato.getCantidadcaja());
        producto.setPreciounitario(dato.getPreciounitario());
        producto.setEnlaceproducto(dato.getEnlaceproducto());
        producto.setHabilitado(dato.getHabilitado());
        producto.setCodigocorto(dato.getCodigocorto());
        productosRepositorio.adicionar(producto);
        bitacorasServicio.registrar("ADICIONAR - Datos: "+dato.toString());

    }

    @Override
    public void modificar(ProductosOTD dato) {
        Productos producto = productosRepositorio.obtenerPorId(dato.getIdproducto());
        producto.setIdproductosin(dato.getIdproductosin());
        producto.setIdgrupo(dato.getIdgrupo());
        producto.setIdunidadmedida(dato.getIdunidadmedida());
        producto.setProducto(dato.getProducto());
        producto.setCantidadcaja(dato.getCantidadcaja());
        producto.setPreciounitario(dato.getPreciounitario());
        producto.setEnlaceproducto(dato.getEnlaceproducto());
        producto.setHabilitado(dato.getHabilitado());
        producto.setCodigocorto(dato.getCodigocorto());
        productosRepositorio.modificar(producto);
        bitacorasServicio.registrar("MODIFICAR - Datos: "+dato.toString());

    }

    @Override
    public void cargarFotografiaProducto(Long id, MultipartFile archivo) {
        if (!archivo.isEmpty()) {
            String ruta = rutaprincipal+"/productos";
            String nombreArchivo = UUID.randomUUID().toString()+"."+StringUtils.getFilenameExtension(archivo.getOriginalFilename());

            Productos entrada =  productosRepositorio.obtenerPorId(id);

            entrada.setEnlaceproducto(nombreArchivo);
            productosRepositorio.modificar(entrada);

            Path rutaArchivo = Paths.get(ruta).resolve(nombreArchivo).toAbsolutePath();
            Path rutaArchivoAnterior = Paths.get(ruta).resolve(entrada.getEnlaceproducto()).toAbsolutePath();
            try {
                Files.deleteIfExists(rutaArchivoAnterior);
                Files.copy(archivo.getInputStream(), rutaArchivo);
            } catch (Exception e) {
                throw new RuntimeException("No se pudo almacenar el archivo. Error: " + e.getMessage());
            }
            redimensionar(nombreArchivo);
        }
    }

    public void redimensionar(String nombreArchivo) {
        String ruta = rutaprincipal+"/productos";
        int ancho = 300;
        int alto = 300;
        try {
            fredimensionar(nombreArchivo, ruta, ancho, alto);
        } catch (Exception e) {
            throw new RuntimeException("No se pudo redimensionar el archivo. Error: " + e.getMessage());
        }
    }

    public static void fredimensionar(String nombreArchivo, String ruta, int ancho, int alto) throws IOException {
        try {
            File entradaarchivo = new File(ruta + "/" + nombreArchivo);
            File salidaarchivo = new File(ruta + "/" + nombreArchivo);
            BufferedImage entradaImagen = ImageIO.read(entradaarchivo);
            BufferedImage salidaImagen = new BufferedImage(ancho, alto, entradaImagen.getType());
            Graphics2D g2d = salidaImagen.createGraphics();
            g2d.drawImage(entradaImagen, 0, 0, ancho, alto, null);
            g2d.dispose();
            ImageIO.write(salidaImagen, "png", salidaarchivo);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }


    @Override
    public Resource descargarFotografiaProducto(String nombreArchivo) {
        String ruta = rutaprincipal+"/productos";
        Path rutaArchivo = Paths.get(ruta).resolve(nombreArchivo).toAbsolutePath();
        Path rutaArchivoSinImagen = Paths.get(ruta).resolve("sinimagen.png").toAbsolutePath();
        Resource recurso = null;
        try {
            recurso = new UrlResource(rutaArchivo.toUri());
            if (recurso.exists() || recurso.isReadable()) {
                return recurso;
            } else {
                recurso = new UrlResource(rutaArchivoSinImagen.toUri());
                return recurso;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return recurso;

    }

    @Override
    public List<ProductosOTD> obtenerPorIdgrupoConPaginacion(Long idgrupo, Integer pagina, Integer cantidad) {
        int nropagina = 0;
        if ((pagina - 1) * cantidad < 0) {
            nropagina = 0;
        } else {
            nropagina = (pagina - 1) * cantidad;
        }
        List<ProductosOTD> salida = new ArrayList<>();
        List<Productos> entrada = productosRepositorio.obtenerPorIdgrupoConPaginacion(idgrupo, nropagina, cantidad);
        for (Productos producto : entrada) {
            Grupos grupo = gruposRepositorio.obtenerPorId(producto.getIdgrupo());
            Unidadesmedidas unidadmedida = unidadesmedidasRepositorio.obtenerPorId(producto.getIdunidadmedida());
            Productossin productosin = productossinRepositorio.obtenerPorId(producto.getIdproductosin());

            ProductosOTD datosalida = new ProductosOTD(producto.getIdproducto(), productosin.getIdproductosin(), productosin.getCodigosin(), productosin.getCodigoactividadsin(), grupo.getIdgrupo(), grupo.getGrupo(), unidadmedida.getIdunidadmedida(), unidadmedida.getCodigosin(),  unidadmedida.getUnidadmedida(), producto.getProducto(), producto.getCantidadcaja(), producto.getPreciounitario(), producto.getEnlaceproducto(), producto.getHabilitado(), producto.getCodigocorto(), producto.getDescuento());
            salida.add(datosalida);
        }
        return salida;

    }

    @Override
    public Integer obtenerCantidadPorIdgrupo(Long idgrupo) {
        return productosRepositorio.obtenerCantidadPorIdgrupo(idgrupo);
    }

    @Override
    public ProductosOTD obtenerPorCodigocorto(Integer codigocorto) {
        Productos producto = productosRepositorio.obtenerPorCodigocorto(codigocorto);
        Grupos grupo = gruposRepositorio.obtenerPorId(producto.getIdgrupo());
        Unidadesmedidas unidadmedida = unidadesmedidasRepositorio.obtenerPorId(producto.getIdunidadmedida());
        Productossin productosin = productossinRepositorio.obtenerPorId(producto.getIdproductosin());

        ProductosOTD datosalida = new ProductosOTD(producto.getIdproducto(), productosin.getIdproductosin(), productosin.getCodigosin(), productosin.getCodigoactividadsin(), grupo.getIdgrupo(), grupo.getGrupo(), unidadmedida.getIdunidadmedida(), unidadmedida.getCodigosin(),  unidadmedida.getUnidadmedida(), producto.getProducto(), producto.getCantidadcaja(), producto.getPreciounitario(), producto.getEnlaceproducto(), producto.getHabilitado(), producto.getCodigocorto(), producto.getDescuento());
        return datosalida;
    }

}
