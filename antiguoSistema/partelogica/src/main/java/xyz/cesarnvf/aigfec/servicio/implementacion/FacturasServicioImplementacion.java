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
import xyz.cesarnvf.aigfec.modelo.Descuentos;
import xyz.cesarnvf.aigfec.modelo.Detalles;
import xyz.cesarnvf.aigfec.modelo.Documentosidentidadessin;
import xyz.cesarnvf.aigfec.modelo.Facturas;
import xyz.cesarnvf.aigfec.modelo.Metodospagossin;
import xyz.cesarnvf.aigfec.modelo.Puntosventas;
import xyz.cesarnvf.aigfec.modelo.Puntosventasdescuentos;
import xyz.cesarnvf.aigfec.modelo.Usuarios;
import xyz.cesarnvf.aigfec.modelo.otd.DetallesOTD;
import xyz.cesarnvf.aigfec.modelo.otd.FacturasOTD;
import xyz.cesarnvf.aigfec.repositorio.ClientesRepositorio;
import xyz.cesarnvf.aigfec.repositorio.DescuentosRepositorio;
import xyz.cesarnvf.aigfec.repositorio.DetallesRepositorio;
import xyz.cesarnvf.aigfec.repositorio.DocumentosidentidadessinRepositorio;
import xyz.cesarnvf.aigfec.repositorio.FacturasRepositorio;
import xyz.cesarnvf.aigfec.repositorio.LeyendassinRepositorio;
import xyz.cesarnvf.aigfec.repositorio.MetodospagossinRepositorio;
import xyz.cesarnvf.aigfec.repositorio.PuntosventasRepositorio;
import xyz.cesarnvf.aigfec.repositorio.PuntosventasdescuentosRepositorio;
import xyz.cesarnvf.aigfec.repositorio.UsuariosRepositorio;
import xyz.cesarnvf.aigfec.servicio.BitacorasServicio;
import xyz.cesarnvf.aigfec.servicio.FacturasServicio;
import xyz.cesarnvf.aigfec.servicio.FuncionesSIATServicio;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class FacturasServicioImplementacion implements FacturasServicio{

    FacturasRepositorio facturasRepositorio;

    PuntosventasRepositorio puntosventasRepositorio;

    DescuentosRepositorio descuentosRepositorio;

    PuntosventasdescuentosRepositorio puntosventasdescuentosRepositorio;

    DetallesRepositorio detallesRepositorio;

    UsuariosRepositorio usuariosRepositorio;

    ClientesRepositorio clientesRepositorio;

    BitacorasServicio bitacorasServicio;

    DocumentosidentidadessinRepositorio documentosidentidadessinRepositorio;

    MetodospagossinRepositorio metodospagossinRepositorio;

    FuncionesSIATServicio funcionesSIATServicio;

    LeyendassinRepositorio leyendassinRepositorio;

    @Override
    public List<FacturasOTD> obtenerPorTerminoConPaginacion(String termino, Integer pagina, Integer cantidad) {
        Authentication autentificado = SecurityContextHolder.getContext().getAuthentication();
        int nropagina = 0;
        if ((pagina - 1) * cantidad < 0) {
            nropagina = 0;
        } else {
            nropagina = (pagina - 1) * cantidad;
        }
        List<FacturasOTD> salida = new ArrayList<>();
        List<Facturas> entrada = facturasRepositorio.obtenerPorTerminoConPaginacion(Long.parseLong(autentificado.getName()), termino, nropagina, cantidad);
        for (Facturas factura : entrada) {
            Usuarios usuario = usuariosRepositorio.obtenerPorId(factura.getIdusuario());
            Clientes cliente = clientesRepositorio.obtenerPorId(factura.getIdcliente());
            Documentosidentidadessin documentoidentificaciondsin = documentosidentidadessinRepositorio.obtenerPorId(cliente.getIddocumentoidentidadsin());
            Metodospagossin metodopagosin = metodospagossinRepositorio.obtenerPorId(factura.getIdmetodopagosin());

            List<DetallesOTD> detalles = new ArrayList<>();
            FacturasOTD dato = new FacturasOTD(factura.getIdfactura(), usuario.getIdusuario(), cliente.getIdcliente(), factura.getIdpuntoventa(), documentoidentificaciondsin.getIddocumentoidentidadsin(), documentoidentificaciondsin.getCodigosin(), documentoidentificaciondsin.getDocumentoidentidadsin(), cliente.getCliente(), cliente.getNumerodocumento(), cliente.getComplemento(), cliente.getCorreoelectronico(), cliente.getCelular(), metodopagosin.getIdmetodopagosin(), factura.getTotal(), factura.getDescuento(), factura.getEfectivo(), factura.getCambio(), factura.getFechaemision(), factura.getCodigo(), factura.getEstado(), factura.getLeyenda(), factura.getNumerofactura(), factura.getCufd(), factura.getCuf(), detalles);
            salida.add(dato);
        }
        return salida;

    }

    @Override
    public Integer obtenerCantidad(String termino) {
        Authentication autentificado = SecurityContextHolder.getContext().getAuthentication();
        Usuarios usuario = usuariosRepositorio.obtenerPorId(Long.parseLong(autentificado.getName()));
        return facturasRepositorio.obtenerCantidad(usuario.getIdusuario(), termino);
    }

    @Override
    public List<FacturasOTD> obtenerNoEnviados() {
        Authentication autentificado = SecurityContextHolder.getContext().getAuthentication();
        Long idpuntoventa = puntosventasRepositorio.obtenerPuntoventaAsignado(Long.parseLong(autentificado.getName()));
        List<FacturasOTD> salida = new ArrayList<>();
        List<Facturas> entrada = facturasRepositorio.obtenerNoEnviadosRegistrados(idpuntoventa);
        for (Facturas factura : entrada) {
            Usuarios usuario = usuariosRepositorio.obtenerPorId(factura.getIdusuario());
            Clientes cliente = clientesRepositorio.obtenerPorId(factura.getIdcliente());
            Documentosidentidadessin documentoidentificaciondsin = documentosidentidadessinRepositorio.obtenerPorId(cliente.getIddocumentoidentidadsin());
            Metodospagossin metodopagosin = metodospagossinRepositorio.obtenerPorId(factura.getIdmetodopagosin());

            List<DetallesOTD> detalles = new ArrayList<>();
            FacturasOTD dato = new FacturasOTD(factura.getIdfactura(), usuario.getIdusuario(), cliente.getIdcliente(), factura.getIdpuntoventa(), documentoidentificaciondsin.getIddocumentoidentidadsin(), documentoidentificaciondsin.getCodigosin(), documentoidentificaciondsin.getDocumentoidentidadsin(), cliente.getCliente(), cliente.getNumerodocumento(), cliente.getComplemento(), cliente.getCorreoelectronico(), cliente.getCelular(), metodopagosin.getIdmetodopagosin(), factura.getTotal(), factura.getDescuento(), factura.getEfectivo(), factura.getCambio(), factura.getFechaemision(), factura.getCodigo(), factura.getEstado(), factura.getLeyenda(), factura.getNumerofactura(), factura.getCufd(), factura.getCuf(),detalles);
            salida.add(dato);
        }
        return salida;
    }

    @Override
    public List<FacturasOTD> obtenerNoEnviadoso() {
        Authentication autentificado = SecurityContextHolder.getContext().getAuthentication();
        Long idpuntoventa = puntosventasRepositorio.obtenerPuntoventaAsignado(Long.parseLong(autentificado.getName()));
        List<FacturasOTD> salida = new ArrayList<>();
        List<Facturas> entrada = facturasRepositorio.obtenerNoEnviadoso(idpuntoventa);
        for (Facturas factura : entrada) {
            Usuarios usuario = usuariosRepositorio.obtenerPorId(factura.getIdusuario());
            Clientes cliente = clientesRepositorio.obtenerPorId(factura.getIdcliente());
            Documentosidentidadessin documentoidentificaciondsin = documentosidentidadessinRepositorio.obtenerPorId(cliente.getIddocumentoidentidadsin());
            Metodospagossin metodopagosin = metodospagossinRepositorio.obtenerPorId(factura.getIdmetodopagosin());

            List<DetallesOTD> detalles = new ArrayList<>();
            FacturasOTD dato = new FacturasOTD(factura.getIdfactura(), usuario.getIdusuario(), cliente.getIdcliente(), factura.getIdpuntoventa(), documentoidentificaciondsin.getIddocumentoidentidadsin(), documentoidentificaciondsin.getCodigosin(), documentoidentificaciondsin.getDocumentoidentidadsin(), cliente.getCliente(), cliente.getNumerodocumento(), cliente.getComplemento(), cliente.getCorreoelectronico(), cliente.getCelular(), metodopagosin.getIdmetodopagosin(), factura.getTotal(), factura.getDescuento(), factura.getEfectivo(), factura.getCambio(), factura.getFechaemision(), factura.getCodigo(), factura.getEstado(), factura.getLeyenda(), factura.getNumerofactura(), factura.getCufd(), factura.getCuf(),detalles);
            salida.add(dato);
        }
        return salida;
    }

    @Override
    public FacturasOTD obtenerPorId(Long id) {
        Facturas factura = facturasRepositorio.obtenerPorId(id);
        Usuarios usuario = usuariosRepositorio.obtenerPorId(factura.getIdusuario());
        Clientes cliente = clientesRepositorio.obtenerPorId(factura.getIdcliente());
        Documentosidentidadessin documentoidentificaciondsin = documentosidentidadessinRepositorio.obtenerPorId(cliente.getIddocumentoidentidadsin());
        Metodospagossin metodopagosin = metodospagossinRepositorio.obtenerPorId(factura.getIdmetodopagosin());

        List<DetallesOTD> detalles = new ArrayList<>();
        FacturasOTD dato = new FacturasOTD(factura.getIdfactura(), usuario.getIdusuario(), cliente.getIdcliente(), factura.getIdpuntoventa(), documentoidentificaciondsin.getIddocumentoidentidadsin(), documentoidentificaciondsin.getCodigosin(), documentoidentificaciondsin.getDocumentoidentidadsin(), cliente.getCliente(), cliente.getNumerodocumento(), cliente.getComplemento(), cliente.getCorreoelectronico(), cliente.getCelular(), metodopagosin.getIdmetodopagosin(), factura.getTotal(), factura.getDescuento(), factura.getEfectivo(), factura.getCambio(), factura.getFechaemision(), factura.getCodigo(), factura.getEstado(), factura.getLeyenda(), factura.getNumerofactura(), factura.getCufd(), factura.getCuf(),detalles);
        return dato;
    }

    @Override
    public Long adicionar(FacturasOTD dato) throws RemoteException, Exception{

        Facturas venta = new Facturas();
        Authentication autentificado = SecurityContextHolder.getContext().getAuthentication();
        Long idpuntoventa = puntosventasRepositorio.obtenerPuntoventaAsignado(Long.parseLong(autentificado.getName()));
        Puntosventas puntoventa = puntosventasRepositorio.obtenerPorId(idpuntoventa);
        Integer codigoActividad=471902;
        if (idpuntoventa==16l) {
            codigoActividad=681011;
        }

        Long _contador =  puntosventasRepositorio.obtenerNumerofactura(idpuntoventa);
        if (_contador == null) {
            _contador = 1l;
        }

        BigDecimal total = BigDecimal.ZERO;
        for (DetallesOTD _item : dato.getDetalles()) {
            BigDecimal subTotal = (_item.getPreciounitario().multiply(_item.getCantidad())).subtract(_item.getDescuento());
            total = total.add(subTotal);
        }

        venta.setIdcliente(dato.getIdcliente());
        venta.setIdpuntoventa(idpuntoventa);
        venta.setIdusuario(Long.parseLong(autentificado.getName()));
        venta.setNumerofactura(_contador);
        venta.setTotal(total);
        //venta.setTotal(dato.getTotal());
        venta.setEfectivo(dato.getEfectivo());
        venta.setDescuento(dato.getDescuento());
        venta.setCambio(dato.getCambio());
        venta.setIdmetodopagosin(dato.getIdmetodopagosin());
        venta.setEstado("Proforma");
        venta.setCodigo(0);
        venta.setFechaemision(LocalDateTime.now());
        venta.setLeyenda(leyendassinRepositorio.obtenerLeyendaAleatoriamente(codigoActividad).getLeyendasin());
        venta.setIdpedido(0l);
        facturasRepositorio.adicionar(venta);

        for (DetallesOTD _item : dato.getDetalles()) {

            Detalles datod = new Detalles();
            datod.setCantidad(_item.getCantidad());
            datod.setPreciounitario(_item.getPreciounitario());
            datod.setDescuento(_item.getDescuento());
            datod.setIdgrupo(_item.getIdgrupo());
            datod.setIdfactura(venta.getIdfactura());
            datod.setIdproducto(_item.getIdproducto());
            detallesRepositorio.adicionar(datod);
        }
        venta.setTotal(dato.getTotal());
        bitacorasServicio.registrar("ADICIONAR - Datos: "+dato.toString());

        puntoventa.setNumerofactura(_contador.intValue());
        puntosventasRepositorio.modificar(puntoventa);

        funcionesSIATServicio.emitirFactura(venta.getIdfactura());

        return venta.getIdfactura();

    }

    @Override
    public BigDecimal obtenerDescuento(List<DetallesOTD> datos, Long idcliente, Long idproducto) {
        Authentication autentificado = SecurityContextHolder.getContext().getAuthentication();
        Long idpuntoventa = puntosventasRepositorio.obtenerPuntoventaAsignado(Long.parseLong(autentificado.getName()));

        BigDecimal sumagalletas = BigDecimal.ZERO;
        BigDecimal sumafideos = BigDecimal.ZERO;
        BigDecimal sumabalanceados = BigDecimal.ZERO;
        BigDecimal sumasurtidas = BigDecimal.ZERO;

        BigDecimal descuento = BigDecimal.ZERO;
        BigDecimal descuento1 = BigDecimal.ZERO;
        BigDecimal descuento2 = BigDecimal.ZERO;
        BigDecimal descuento3 = BigDecimal.ZERO;


        for (DetallesOTD detalleOTD : datos) {
            switch (detalleOTD.getIdgrupo().intValue()) {
                case 1: sumagalletas = sumagalletas.add(detalleOTD.getCantidad().multiply(detalleOTD.getPreciounitario())); break;
                case 2: sumafideos = sumafideos.add(detalleOTD.getCantidad().multiply(detalleOTD.getPreciounitario())); break;
                case 3: sumabalanceados = sumabalanceados.add(detalleOTD.getCantidad().multiply(detalleOTD.getPreciounitario())); break;
                case 6: sumasurtidas = sumasurtidas.add(detalleOTD.getCantidad().multiply(detalleOTD.getPreciounitario())); break;
                default: break;
            }
        }

        List<Puntosventasdescuentos> puntosventasdescuentos = puntosventasdescuentosRepositorio.obtenerPorIdpuntoventa(idpuntoventa);
        for (Puntosventasdescuentos puntoventadescuento : puntosventasdescuentos) {
            Descuentos analisisdescuento = descuentosRepositorio.obtenerPorId(puntoventadescuento.getIddescuento());
            if (analisisdescuento.getOpcion()==1) {
                if (analisisdescuento.getIdgrupo()==1l) {
                    if (sumagalletas.compareTo(analisisdescuento.getCantidadinicial())>=0 && sumagalletas.compareTo(analisisdescuento.getCantidadfinal())<=0) {
                        for (DetallesOTD detalleOTD : datos) {
                            if (detalleOTD.getIdgrupo()==1l && detalleOTD.getIdproducto()==idproducto) {
                                descuento1 = BigDecimal.valueOf(analisisdescuento.getPorcentaje()*100);
                            }
                        }
                    }
                }
                if (analisisdescuento.getIdgrupo()==2l) {
                    if (sumafideos.compareTo(analisisdescuento.getCantidadinicial())>=0 && sumafideos.compareTo(analisisdescuento.getCantidadfinal())<=0) {
                        for (DetallesOTD detalleOTD : datos) {
                            if (detalleOTD.getIdgrupo()==2l && detalleOTD.getIdproducto()==idproducto) {
                                descuento1 = BigDecimal.valueOf(analisisdescuento.getPorcentaje()*100);
                            }
                        }
                    }
                }
                if (analisisdescuento.getIdgrupo()==3l) {
                    if (sumabalanceados.compareTo(analisisdescuento.getCantidadinicial())>=0 && sumabalanceados.compareTo(analisisdescuento.getCantidadfinal())<=0) {
                        for (DetallesOTD detalleOTD : datos) {
                            if (detalleOTD.getIdgrupo()==3l && detalleOTD.getIdproducto()==idproducto) {
                                descuento1 = BigDecimal.valueOf(analisisdescuento.getPorcentaje()*100);
                            }
                        }
                    }
                }
                if (analisisdescuento.getIdgrupo()==6l) {
                    if (sumasurtidas.compareTo(analisisdescuento.getCantidadinicial())>=0 && sumasurtidas.compareTo(analisisdescuento.getCantidadfinal())<=0) {
                        for (DetallesOTD detalleOTD : datos) {
                            if (detalleOTD.getIdgrupo()==6l && detalleOTD.getIdproducto()==idproducto) {
                                descuento1 = BigDecimal.valueOf(analisisdescuento.getPorcentaje()*100);
                            }
                        }
                    }
                }
            }

            if (analisisdescuento.getOpcion()==2) {
                for (DetallesOTD detalles : datos) {
                    if (detalles.getIdproducto().equals(analisisdescuento.getIdproducto())) {
                        BigDecimal sumaproducto = detalles.getCantidad().multiply(detalles.getPreciounitario());
                        if (sumaproducto.compareTo(analisisdescuento.getCantidadinicial())>=0 && sumaproducto.compareTo(analisisdescuento.getCantidadfinal())<=0) {
                            for (DetallesOTD detalleOTD : datos) {
                                if (detalleOTD.getIdproducto().equals(analisisdescuento.getIdproducto())) {
                                    descuento2 = BigDecimal.valueOf(analisisdescuento.getPorcentaje()*100);
                                }
                            }
                        }
                    }
                }
            }

            if (analisisdescuento.getOpcion()==3) {
                if (analisisdescuento.getIdcliente().equals(idcliente)) {
                    if (analisisdescuento.getIdgrupo()==1l) {
                        if (sumagalletas.compareTo(analisisdescuento.getCantidadinicial())>=0 && sumagalletas.compareTo(analisisdescuento.getCantidadfinal())<=0) {
                            for (DetallesOTD detalleOTD : datos) {
                                if (detalleOTD.getIdgrupo()==1l && detalleOTD.getIdproducto()==idproducto) {
                                    descuento3 = BigDecimal.valueOf(analisisdescuento.getPorcentaje()*100);
                                }
                            }
                        }
                    }
                    if (analisisdescuento.getIdgrupo()==2l) {
                        if (sumafideos.compareTo(analisisdescuento.getCantidadinicial())>=0 && sumafideos.compareTo(analisisdescuento.getCantidadfinal())<=0) {
                            for (DetallesOTD detalleOTD : datos) {
                                if (detalleOTD.getIdgrupo()==2l && detalleOTD.getIdproducto()==idproducto) {
                                    descuento3 = BigDecimal.valueOf(analisisdescuento.getPorcentaje()*100);
                                }
                            }
                        }
                    }
                    if (analisisdescuento.getIdgrupo()==3l) {
                        if (sumabalanceados.compareTo(analisisdescuento.getCantidadinicial())>=0 && sumabalanceados.compareTo(analisisdescuento.getCantidadfinal())<=0) {
                            for (DetallesOTD detalleOTD : datos) {
                                if (detalleOTD.getIdgrupo()==3l && detalleOTD.getIdproducto()==idproducto) {
                                    descuento3 = BigDecimal.valueOf(analisisdescuento.getPorcentaje()*100);
                                }
                            }
                        }
                    }
                    if (analisisdescuento.getIdgrupo()==6l) {
                        if (sumasurtidas.compareTo(analisisdescuento.getCantidadinicial())>=0 && sumasurtidas.compareTo(analisisdescuento.getCantidadfinal())<=0) {
                            for (DetallesOTD detalleOTD : datos) {
                                if (detalleOTD.getIdgrupo()==6l && detalleOTD.getIdproducto()==idproducto) {
                                    descuento3 = BigDecimal.valueOf(analisisdescuento.getPorcentaje()*100);
                                }
                            }
                        }
                    }
                }
            }
            //FIXME: Solo para un grupo o producto
            /* switch (analisisdescuento.getOpcion()) {
                case 1: descuento = descuento1; break;
                case 2: descuento = descuento2; break;
                case 3: descuento = descuento3; break;
                default: break;
            } */
            // TODO: Verificar
            BigDecimal maxDescuento = descuento1;

            if (descuento2.compareTo(maxDescuento) > 0) {
                maxDescuento = descuento2;
            }

            if (descuento3.compareTo(maxDescuento) > 0) {
                maxDescuento = descuento3;
            }
            descuento = maxDescuento;
        }
        return descuento;
    }

    @Override
    public List<FacturasOTD> obtenerPorTerminoIdpedido() {
        Authentication autentificado = SecurityContextHolder.getContext().getAuthentication();
        LocalDate ahora = LocalDate.now();
        LocalDate fechainicialmes = ahora.withDayOfMonth(1);
        LocalDate fechafinalmes = ahora.withDayOfMonth(ahora.lengthOfMonth());
        List<FacturasOTD> salida = new ArrayList<>();
        List<Facturas> entrada = facturasRepositorio.obtenerPorTerminoIdpedido(Long.parseLong(autentificado.getName()), fechainicialmes, fechafinalmes);
        for (Facturas factura : entrada) {
            Usuarios usuario = usuariosRepositorio.obtenerPorId(factura.getIdusuario());
            Clientes cliente = clientesRepositorio.obtenerPorId(factura.getIdcliente());
            Documentosidentidadessin documentoidentificaciondsin = documentosidentidadessinRepositorio.obtenerPorId(cliente.getIddocumentoidentidadsin());
            Metodospagossin metodopagosin = metodospagossinRepositorio.obtenerPorId(factura.getIdmetodopagosin());

            List<DetallesOTD> detalles = new ArrayList<>();
            FacturasOTD dato = new FacturasOTD(factura.getIdfactura(), usuario.getIdusuario(), cliente.getIdcliente(), factura.getIdpuntoventa(), documentoidentificaciondsin.getIddocumentoidentidadsin(), documentoidentificaciondsin.getCodigosin(), documentoidentificaciondsin.getDocumentoidentidadsin(), cliente.getCliente(), cliente.getNumerodocumento(), cliente.getComplemento(), cliente.getCorreoelectronico(), cliente.getCelular(), metodopagosin.getIdmetodopagosin(), factura.getTotal(), factura.getDescuento(), factura.getEfectivo(), factura.getCambio(), factura.getFechaemision(), factura.getCodigo(), factura.getEstado(), factura.getLeyenda(), factura.getNumerofactura(), factura.getCufd(), factura.getCuf(), detalles);
            salida.add(dato);
        }
        return salida;
    }

}
