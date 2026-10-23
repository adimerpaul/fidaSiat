package xyz.cesarnvf.aigfec.servicio.implementacion;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import xyz.cesarnvf.aigfec.modelo.Clientes;
import xyz.cesarnvf.aigfec.modelo.Descuentos;
import xyz.cesarnvf.aigfec.modelo.Grupos;
import xyz.cesarnvf.aigfec.modelo.Productos;
import xyz.cesarnvf.aigfec.modelo.otd.DescuentosOTD;
import xyz.cesarnvf.aigfec.repositorio.ClientesRepositorio;
import xyz.cesarnvf.aigfec.repositorio.DescuentosRepositorio;
import xyz.cesarnvf.aigfec.repositorio.GruposRepositorio;
import xyz.cesarnvf.aigfec.repositorio.ProductosRepositorio;
import xyz.cesarnvf.aigfec.servicio.BitacorasServicio;
import xyz.cesarnvf.aigfec.servicio.DescuentosServicio;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DescuentosServicioImplementacion implements DescuentosServicio{

    DescuentosRepositorio descuentosRepositorio;

    GruposRepositorio gruposRepositorio;

    ProductosRepositorio productosRepositorio;

    ClientesRepositorio clientesRepositorio;

    BitacorasServicio bitacorasServicio;

    @Override
    public List<DescuentosOTD> obtenerTodos() {
        List<DescuentosOTD> salida = new ArrayList<>();
        List<Descuentos> entrada = descuentosRepositorio.obtenerTodos();
        for (Descuentos descuento : entrada) {
            Grupos grupo = gruposRepositorio.obtenerPorId(descuento.getIdgrupo());
            if (grupo==null) {
                grupo = new Grupos();
                grupo.setIdgrupo(null);
                grupo.setGrupo("Sin Asignacion");
            }
            Productos producto = productosRepositorio.obtenerPorId(descuento.getIdproducto());
            if (producto==null) {
                producto = new Productos();
                producto.setIdproducto(null);
                producto.setProducto("Sin Asignacion");
            }
            Clientes cliente = clientesRepositorio.obtenerPorId(descuento.getIdcliente());
            if (cliente==null) {
                cliente = new Clientes();
                cliente.setIdcliente(null);
                cliente.setCliente("Sin Asignacion");
            }
            DescuentosOTD datosalida = new DescuentosOTD(descuento.getIddescuento(), descuento.getDescuento(), descuento.getFecha(), descuento.getCantidadinicial(), descuento.getCantidadfinal(), descuento.getPorcentaje(), grupo.getIdgrupo(), grupo.getGrupo(), producto.getIdproducto(), producto.getProducto(), cliente.getIdcliente(), cliente.getCliente(), cliente.getNumerodocumento(), descuento.getOpcion());
            salida.add(datosalida);
        }
        return salida;
    }

    @Override
    public List<DescuentosOTD> obtenerPorTerminoConPaginacion(String termino, Integer pagina, Integer cantidad) {
        int nropagina = 0;
        if ((pagina - 1) * cantidad < 0) {
            nropagina = 0;
        } else {
            nropagina = (pagina - 1) * cantidad;
        }
        List<DescuentosOTD> salida = new ArrayList<>();
        List<Descuentos> entrada = descuentosRepositorio.obtenerPorTerminoConPaginacion(termino, nropagina, cantidad);
        for (Descuentos descuento : entrada) {
            Grupos grupo = gruposRepositorio.obtenerPorId(descuento.getIdgrupo());
            if (grupo==null) {
                grupo = new Grupos();
                grupo.setIdgrupo(null);
                grupo.setGrupo("Sin Asignacion");
            }
            Productos producto = productosRepositorio.obtenerPorId(descuento.getIdproducto());
            if (producto==null) {
                producto = new Productos();
                producto.setIdproducto(null);
                producto.setProducto("Sin Asignacion");
            }
            Clientes cliente = clientesRepositorio.obtenerPorId(descuento.getIdcliente());
            if (cliente==null) {
                cliente = new Clientes();
                cliente.setIdcliente(null);
                cliente.setCliente("Sin Asignacion");
            }
            DescuentosOTD datosalida = new DescuentosOTD(descuento.getIddescuento(), descuento.getDescuento(), descuento.getFecha(), descuento.getCantidadinicial(), descuento.getCantidadfinal(), descuento.getPorcentaje(), grupo.getIdgrupo(), grupo.getGrupo(), producto.getIdproducto(), producto.getProducto(), cliente.getIdcliente(), cliente.getCliente(), cliente.getNumerodocumento(), descuento.getOpcion());
            salida.add(datosalida);
        }
        return salida;
    }

    @Override
    public Integer obtenerCantidad(String termino) {
        return descuentosRepositorio.obtenerCantidad(termino);
    }

    @Override
    public DescuentosOTD obtenerPorId(Long id) {
        Descuentos descuento = descuentosRepositorio.obtenerPorId(id);
        Grupos grupo = gruposRepositorio.obtenerPorId(descuento.getIdgrupo());
        if (grupo==null) {
            grupo = new Grupos();
            grupo.setIdgrupo(null);
            grupo.setGrupo("Sin Asignacion");
        }
        Productos producto = productosRepositorio.obtenerPorId(descuento.getIdproducto());
        if (producto==null) {
            producto = new Productos();
            producto.setIdproducto(null);
            producto.setProducto("Sin Asignacion");
        }
        Clientes cliente = clientesRepositorio.obtenerPorId(descuento.getIdcliente());
        if (cliente==null) {
            cliente = new Clientes();
            cliente.setIdcliente(null);
            cliente.setCliente("Sin Asignacion");
        }
        DescuentosOTD datosalida = new DescuentosOTD(descuento.getIddescuento(), descuento.getDescuento(), descuento.getFecha(), descuento.getCantidadinicial(), descuento.getCantidadfinal(), descuento.getPorcentaje(), grupo.getIdgrupo(), grupo.getGrupo(), producto.getIdproducto(), producto.getProducto(), cliente.getIdcliente(), cliente.getCliente(), cliente.getNumerodocumento(), descuento.getOpcion());
        return datosalida;
    }

    @Override
    public void adicionar(DescuentosOTD dato) {
        Descuentos descuento = new Descuentos();
        descuento.setDescuento(dato.getDescuento());
        descuento.setFecha(dato.getFecha());
        descuento.setCantidadinicial(dato.getCantidadinicial());
        descuento.setCantidadfinal(dato.getCantidadfinal());
        descuento.setPorcentaje(dato.getPorcentaje());
        descuento.setIdgrupo(dato.getIdgrupo());
        descuento.setIdproducto(dato.getIdproducto());
        descuento.setIdcliente(dato.getIdcliente());
        descuento.setOpcion(dato.getOpcion());
        descuentosRepositorio.adicionar(descuento);
        bitacorasServicio.registrar("ADICIONAR - Datos: "+dato.toString());
    }

    @Override
    public void modificar(DescuentosOTD dato) {
        Descuentos descuento = descuentosRepositorio.obtenerPorId(dato.getIddescuento());
        descuento.setDescuento(dato.getDescuento());
        descuento.setFecha(dato.getFecha());
        descuento.setCantidadinicial(dato.getCantidadinicial());
        descuento.setCantidadfinal(dato.getCantidadfinal());
        descuento.setPorcentaje(dato.getPorcentaje());
        descuento.setIdgrupo(dato.getIdgrupo());
        descuento.setIdproducto(dato.getIdproducto());
        descuento.setIdcliente(dato.getIdcliente());
        descuento.setOpcion(dato.getOpcion());
        descuentosRepositorio.modificar(descuento);
        bitacorasServicio.registrar("MODIFICAR - Datos: "+dato.toString());
    }

    @Override
    public void borrar(Long id) {
        descuentosRepositorio.borrar(id);
    }

}

