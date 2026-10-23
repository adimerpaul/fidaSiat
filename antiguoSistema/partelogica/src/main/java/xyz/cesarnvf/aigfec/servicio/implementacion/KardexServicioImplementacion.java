package xyz.cesarnvf.aigfec.servicio.implementacion;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import xyz.cesarnvf.aigfec.modelo.Kardex;
import xyz.cesarnvf.aigfec.modelo.Productos;
import xyz.cesarnvf.aigfec.modelo.otd.KardexOTD;
import xyz.cesarnvf.aigfec.repositorio.KardexRepositorio;
import xyz.cesarnvf.aigfec.repositorio.ProductosRepositorio;
import xyz.cesarnvf.aigfec.servicio.KardexServicio;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class KardexServicioImplementacion implements KardexServicio {

    KardexRepositorio kardexRepositorio;

    ProductosRepositorio productosRepositorio;

    @Override
    public List<KardexOTD> obtenerPorIdPedido(Long id_pedido) {
        List<KardexOTD> salida = new ArrayList<>();
        List<Kardex> entrada = kardexRepositorio.obtenerPorIdPedido(id_pedido);
        for (Kardex kardex : entrada) {
            Productos producto = productosRepositorio.obtenerPorId(kardex.getId_producto());
            BigDecimal descuento = BigDecimal.ZERO;
            BigDecimal subtotal = BigDecimal.ZERO;
            subtotal =  producto.getPreciounitario().multiply(kardex.getCantidad());
            descuento =  subtotal.multiply(new BigDecimal(kardex.getPrioridad()/100.0));
            System.out.println(descuento);
            KardexOTD dato = new KardexOTD(kardex.getId_pedido(), kardex.getId_kardex(), producto.getIdproducto(), producto.getProducto(), kardex.getCantidad(), kardex.getPreciounitario(), descuento, kardex.getPrioridad());
            salida.add(dato);
        }
        return salida;
    }


}
