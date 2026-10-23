package xyz.cesarnvf.aigfec.servicio.implementacion;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import xyz.cesarnvf.aigfec.modelo.Clientes;
import xyz.cesarnvf.aigfec.modelo.Detalles;
import xyz.cesarnvf.aigfec.modelo.Facturas;
import xyz.cesarnvf.aigfec.modelo.Kardex;
import xyz.cesarnvf.aigfec.modelo.Notasentrega;
import xyz.cesarnvf.aigfec.modelo.Productos;
import xyz.cesarnvf.aigfec.modelo.Puntosventas;
import xyz.cesarnvf.aigfec.modelo.Usuarios;
import xyz.cesarnvf.aigfec.modelo.otd.NotasentregaOTD;
import xyz.cesarnvf.aigfec.repositorio.ClientesRepositorio;
import xyz.cesarnvf.aigfec.repositorio.DetallesRepositorio;
import xyz.cesarnvf.aigfec.repositorio.FacturasRepositorio;
import xyz.cesarnvf.aigfec.repositorio.KardexRepositorio;
import xyz.cesarnvf.aigfec.repositorio.LeyendassinRepositorio;
import xyz.cesarnvf.aigfec.repositorio.NotasentregaRepositorio;
import xyz.cesarnvf.aigfec.repositorio.ProductosRepositorio;
import xyz.cesarnvf.aigfec.repositorio.PuntosventasRepositorio;
import xyz.cesarnvf.aigfec.repositorio.UsuariosRepositorio;
import xyz.cesarnvf.aigfec.servicio.BitacorasServicio;
import xyz.cesarnvf.aigfec.servicio.FuncionesSIATServicio;
import xyz.cesarnvf.aigfec.servicio.NotasentregaServicio;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class NotasentregaServicioImplementacion implements NotasentregaServicio{

    BitacorasServicio bitacorasServicio;

    DetallesRepositorio detallesRepositorio;

    FacturasRepositorio facturasRepositorio;

    FuncionesSIATServicio funcionesSIATServicio;

    LeyendassinRepositorio leyendassinRepositorio;

    PuntosventasRepositorio puntosventasRepositorio;

    ProductosRepositorio productosRepositorio;

    NotasentregaRepositorio notasentregaRepositorio;

    KardexRepositorio kardexRepositorio;

    ClientesRepositorio clientesRepositorio;

    UsuariosRepositorio usuariosRepositorio;

    @Override
    public List<NotasentregaOTD> obtenerNotasentrega(String termino) {
        Authentication autentificado = SecurityContextHolder.getContext().getAuthentication();
        Usuarios usuario = usuariosRepositorio.obtenerPorId(Long.parseLong(autentificado.getName()));
        LocalDate ahora = LocalDate.now();
        LocalDate fechainicialmes = ahora.withDayOfMonth(1);
        LocalDate fechafinalmes = ahora.withDayOfMonth(ahora.lengthOfMonth());
        List<NotasentregaOTD> salida = new ArrayList<>();
        List<Notasentrega> entrada = notasentregaRepositorio.obtenerNotasentrega(termino, fechainicialmes, fechafinalmes);
        for (Notasentrega notaentrega : entrada) {
            Boolean sw = Boolean.TRUE;
            Long verificacion = notasentregaRepositorio.verificarExistenciaFactura(notaentrega.getId_pedido());
            if (verificacion==null) {
                Clientes cliente = clientesRepositorio.buscarPorDocumentoResgistradoImpuestos(notaentrega.getNit());
                if (cliente==null) {
                    sw = Boolean.FALSE;
                }
                String verificaPropiedad = notasentregaRepositorio.verificaPropiedad(notaentrega.getId_pedido());
                if (verificaPropiedad.equals(usuario.getUsuario())) {
                    NotasentregaOTD datosalida = new NotasentregaOTD(notaentrega.getId_pedido(), notaentrega.getNombres(), notaentrega.getNit(), notaentrega.getNum_cladoc(), notaentrega.getCadena1(), notaentrega.getCadena2(), notaentrega.getCadena3(),notaentrega.getPrecio_total(), sw);
                    salida.add(datosalida);
                }
            }
        }
        return salida;
    }

    @Override
    public NotasentregaOTD obtenerPorId(Long id) {
        Notasentrega notaentrega = notasentregaRepositorio.obtenerPorId(id);
        NotasentregaOTD datosalida = new NotasentregaOTD(notaentrega.getId_pedido(), notaentrega.getNombres(), notaentrega.getNit(), notaentrega.getNum_cladoc(), notaentrega.getCadena1(), notaentrega.getCadena2(), notaentrega.getCadena3(),notaentrega.getPrecio_total(), true);
        return datosalida;
    }

    @Override
    public Long migrarNotasEntrega(Long id_pedido) throws RemoteException, Exception {

        Long verificacion = notasentregaRepositorio.verificarExistenciaFactura(id_pedido);
        if (verificacion==null) {
            verificacion=0l;
        }
        if (verificacion>0) {
            return 0l;
        }

        Notasentrega notaentrega = notasentregaRepositorio.obtenerPorId(id_pedido);
        List<Kardex> kardexes = kardexRepositorio.obtenerPorIdPedido(notaentrega.getId_pedido());
        Clientes cliente = clientesRepositorio.buscarPorDocumentoResgistradoImpuestos(notaentrega.getNit());
        if (cliente==null) {
            cliente = new Clientes();
            cliente.setCliente(notaentrega.getNombres());
            cliente.setNumerodocumento(notaentrega.getNit());
            //FIXME: verificar este dato
            cliente.setIddocumentoidentidadsin(1l);
            clientesRepositorio.adicionar(cliente);
        }
        cliente = clientesRepositorio.buscarPorDocumentoResgistradoImpuestos(notaentrega.getNit());
        BigDecimal total = BigDecimal.ZERO;

        for (Kardex kardex : kardexes) {
            Productos producto = productosRepositorio.obtenerPorId(kardex.getId_producto());
            BigDecimal descuento = BigDecimal.ZERO;
            BigDecimal subtotal = BigDecimal.ZERO;
            subtotal =  producto.getPreciounitario().multiply(kardex.getCantidad());
            descuento =  subtotal.multiply(new BigDecimal(kardex.getPrioridad()/100.0));
            total = total.add(subtotal.subtract(descuento));
        }

        Facturas venta = new Facturas();
        Authentication autentificado = SecurityContextHolder.getContext().getAuthentication();
        Long idpuntoventa = puntosventasRepositorio.obtenerPuntoventaAsignado(Long.parseLong(autentificado.getName()));
        Puntosventas puntoventa = puntosventasRepositorio.obtenerPorId(idpuntoventa);

        Long _contador =  puntosventasRepositorio.obtenerNumerofactura(idpuntoventa);
        if (_contador == null) {
            _contador = 1l;
        }

        puntoventa.setNumerofactura(_contador.intValue());
        puntosventasRepositorio.modificar(puntoventa);

        venta.setIdcliente(cliente.getIdcliente());
        venta.setIdpuntoventa(idpuntoventa);
        venta.setIdusuario(Long.parseLong(autentificado.getName()));
        venta.setNumerofactura(_contador);
        venta.setTotal(total);
        venta.setEfectivo(total);
        venta.setDescuento(BigDecimal.ZERO);
        venta.setCambio(BigDecimal.ZERO);
        venta.setIdmetodopagosin(67l);
        venta.setEstado("Proforma");
        venta.setCodigo(0);
        venta.setFechaemision(LocalDateTime.now());
        venta.setLeyenda(leyendassinRepositorio.obtenerLeyendaAleatoriamente(471902).getLeyendasin());
        venta.setIdpedido(id_pedido);
        facturasRepositorio.adicionar(venta);

        for (Kardex _item : kardexes) {

            Detalles datod = new Detalles();
            Productos producto = productosRepositorio.obtenerPorId(_item.getId_producto());
            datod.setCantidad(_item.getCantidad());
            datod.setPreciounitario(_item.getPreciounitario());
            datod.setDescuento(producto.getPreciounitario().multiply(_item.getCantidad()).multiply(new BigDecimal(_item.getPrioridad()/100.0)));
            datod.setIdgrupo(producto.getIdgrupo());
            datod.setIdfactura(venta.getIdfactura());
            datod.setIdproducto(_item.getId_producto());
            detallesRepositorio.adicionar(datod);
        }
        bitacorasServicio.registrar("ADICIONAR - Datos: "+notaentrega.toString() + kardexes.toString());

        funcionesSIATServicio.emitirFactura(venta.getIdfactura());

        return venta.getIdfactura();
    }

}
