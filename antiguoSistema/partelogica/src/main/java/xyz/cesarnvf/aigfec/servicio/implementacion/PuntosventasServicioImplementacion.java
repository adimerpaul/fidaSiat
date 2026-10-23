package xyz.cesarnvf.aigfec.servicio.implementacion;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import xyz.cesarnvf.aigfec.modelo.Cufds;
import xyz.cesarnvf.aigfec.modelo.Cuis;
import xyz.cesarnvf.aigfec.modelo.Descuentos;
import xyz.cesarnvf.aigfec.modelo.Puntosventas;
import xyz.cesarnvf.aigfec.modelo.Puntosventasdescuentos;
import xyz.cesarnvf.aigfec.modelo.Usuarios;
import xyz.cesarnvf.aigfec.modelo.otd.PuntosventasOTD;
import xyz.cesarnvf.aigfec.repositorio.CufdsRepositorio;
import xyz.cesarnvf.aigfec.repositorio.CuisRepositorio;
import xyz.cesarnvf.aigfec.repositorio.DescuentosRepositorio;
import xyz.cesarnvf.aigfec.repositorio.PuntosventasRepositorio;
import xyz.cesarnvf.aigfec.repositorio.PuntosventasdescuentosRepositorio;
import xyz.cesarnvf.aigfec.repositorio.UsuariosRepositorio;
import xyz.cesarnvf.aigfec.servicio.BitacorasServicio;
import xyz.cesarnvf.aigfec.servicio.PuntosventasServicio;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PuntosventasServicioImplementacion implements PuntosventasServicio{

    PuntosventasRepositorio puntosventasRepositorio;

    DescuentosRepositorio descuentosRepositorio;

    PuntosventasdescuentosRepositorio puntosventasdescuentosRepositorio;

    CuisRepositorio cuisRepositorio;

    CufdsRepositorio cufdsRepositorio;

    UsuariosRepositorio usuariosRepositorio;

    BitacorasServicio bitacorasServicio;

    @Override
    public List<PuntosventasOTD> obtenerTodos(Long idsucursal) {
        List<PuntosventasOTD> salida = new ArrayList<>();
        List<Puntosventas> entrada = puntosventasRepositorio.obtenerTodos(idsucursal);
        for (Puntosventas puntoventa : entrada) {
            Cuis cui = cuisRepositorio.obtenerVigente(puntoventa.getIdpuntoventa());
            if (cui==null) {
                cui = new Cuis();
                cui.setCui("Sin Asignacion");
                cui.setFechavigencia(LocalDateTime.now());
            }
            Cufds cufd = cufdsRepositorio.obtenerVigente(puntoventa.getIdpuntoventa());
            if (cufd==null) {
                cufd = new Cufds();
                cufd.setCufd("Sin Asignacion");
                cufd.setFechavigencia(LocalDateTime.now());
            }
            Usuarios usuario = usuariosRepositorio.obtenerPorId(puntoventa.getIdusuario());
            if (usuario==null) {
                usuario = new Usuarios();
                usuario.setIdusuario(1l);
                usuario.setUsuario("Sin Asignacion");
            }
            List<Puntosventasdescuentos> puntosventasdescuentos = puntosventasdescuentosRepositorio.obtenerPorIdpuntoventa(puntoventa.getIdpuntoventa());
            List<String> descuentos = new ArrayList<>();
            List<Long> iddescuentos = new ArrayList<>();

            for (Puntosventasdescuentos puntoventadescuento : puntosventasdescuentos) {
                Descuentos descuento = descuentosRepositorio.obtenerPorId(puntoventadescuento.getIddescuento());
                descuentos.add(descuento.getDescuento());
                iddescuentos.add(descuento.getIddescuento());
            }
            PuntosventasOTD datosalida = new PuntosventasOTD();
            datosalida.setIdpuntoventa(puntoventa.getIdpuntoventa());
            datosalida.setIdsucursal(puntoventa.getIdsucursal());
            datosalida.setPuntoventa(puntoventa.getPuntoventa());
            datosalida.setNombrepuntoventa(puntoventa.getNombrepuntoventa());
            datosalida.setNumerofactura(puntoventa.getNumerofactura());
            datosalida.setIdusuario(usuario.getIdusuario());
            datosalida.setUsuario(usuario.getUsuario());
            datosalida.setCui(cui.getCui());
            datosalida.setCuifechavigencia(cui.getFechavigencia());
            datosalida.setCufd(cufd.getCufd());
            datosalida.setCufdcodigo(cufd.getCufdcontrol());
            datosalida.setCufdfechainicio(cufd.getFechainicio());
            datosalida.setCufdfechavigencia(cufd.getFechavigencia());
            datosalida.setDescuentos(descuentos);
            datosalida.setIddescuentos(iddescuentos);
            datosalida.setEstadosistema(puntoventa.getEstadosistema());
            datosalida.setCafc(puntoventa.getCafc());
            datosalida.setIdcufd(puntoventa.getIdcufd());
            salida.add(datosalida);
        }
        return salida;
    }

    @Override
    public List<PuntosventasOTD> obtenerPorTerminoConPaginacion(String termino, Integer pagina, Integer cantidad, Long idsucursal) {
        int nropagina = 0;
        if ((pagina - 1) * cantidad < 0) {
            nropagina = 0;
        } else {
            nropagina = (pagina - 1) * cantidad;
        }
        List<PuntosventasOTD> salida = new ArrayList<>();
        List<Puntosventas> entrada = puntosventasRepositorio.obtenerPorTerminoConPaginacion(termino, nropagina, cantidad, idsucursal);
        for (Puntosventas puntoventa : entrada) {
            Cuis cui = cuisRepositorio.obtenerVigente(puntoventa.getIdpuntoventa());
            if (cui==null) {
                cui = new Cuis();
                cui.setCui("Sin Asignacion");
                cui.setFechavigencia(LocalDateTime.now());
            }
            Cufds cufd = cufdsRepositorio.obtenerVigente(puntoventa.getIdpuntoventa());
            if (cufd==null) {
                cufd = new Cufds();
                cufd.setCufd("Sin Asignacion");
                cufd.setFechavigencia(LocalDateTime.now());
            }
            Usuarios usuario = usuariosRepositorio.obtenerPorId(puntoventa.getIdusuario());
            if (usuario==null) {
                usuario = new Usuarios();
                usuario.setIdusuario(1l);
                usuario.setUsuario("Sin Asignacion");
            }
            List<Puntosventasdescuentos> puntosventasdescuentos = puntosventasdescuentosRepositorio.obtenerPorIdpuntoventa(puntoventa.getIdpuntoventa());
            List<String> descuentos = new ArrayList<>();
            List<Long> iddescuentos = new ArrayList<>();

            for (Puntosventasdescuentos puntoventadescuento : puntosventasdescuentos) {
                Descuentos descuento = descuentosRepositorio.obtenerPorId(puntoventadescuento.getIddescuento());
                descuentos.add(descuento.getDescuento());
                iddescuentos.add(descuento.getIddescuento());
            }
            PuntosventasOTD datosalida = new PuntosventasOTD();
            datosalida.setIdpuntoventa(puntoventa.getIdpuntoventa());
            datosalida.setIdsucursal(puntoventa.getIdsucursal());
            datosalida.setPuntoventa(puntoventa.getPuntoventa());
            datosalida.setNombrepuntoventa(puntoventa.getNombrepuntoventa());
            datosalida.setNumerofactura(puntoventa.getNumerofactura());
            datosalida.setIdusuario(usuario.getIdusuario());
            datosalida.setUsuario(usuario.getUsuario());
            datosalida.setCui(cui.getCui());
            datosalida.setCuifechavigencia(cui.getFechavigencia());
            datosalida.setCufd(cufd.getCufd());
            datosalida.setCufdcodigo(cufd.getCufdcontrol());
            datosalida.setCufdfechainicio(cufd.getFechainicio());
            datosalida.setCufdfechavigencia(cufd.getFechavigencia());
            datosalida.setDescuentos(descuentos);
            datosalida.setIddescuentos(iddescuentos);
            datosalida.setEstadosistema(puntoventa.getEstadosistema());
            datosalida.setCafc(puntoventa.getCafc());
            datosalida.setIdcufd(puntoventa.getIdcufd());
            salida.add(datosalida);
        }
        return salida;
    }

    @Override
    public Integer obtenerCantidad(String termino, Long idsucursal) {
        return puntosventasRepositorio.obtenerCantidad(termino, idsucursal);
    }

    @Override
    public PuntosventasOTD obtenerPorId(Long id) {
        Puntosventas puntoventa = puntosventasRepositorio.obtenerPorId(id);
        Cuis cui = cuisRepositorio.obtenerVigente(puntoventa.getIdpuntoventa());
        if (cui==null) {
            cui = new Cuis();
            cui.setCui("Sin Asignacion");
            cui.setFechavigencia(LocalDateTime.now());
        }
        Cufds cufd = cufdsRepositorio.obtenerVigente(puntoventa.getIdpuntoventa());
        if (cufd==null) {
            cufd = new Cufds();
            cufd.setCufd("Sin Asignacion");
            cufd.setFechavigencia(LocalDateTime.now());
        }
        Usuarios usuario = usuariosRepositorio.obtenerPorId(puntoventa.getIdusuario());
        if (usuario==null) {
            usuario = new Usuarios();
            usuario.setIdusuario(1l);
            usuario.setUsuario("Sin Asignacion");
        }
        List<Puntosventasdescuentos> puntosventasdescuentos = puntosventasdescuentosRepositorio.obtenerPorIdpuntoventa(puntoventa.getIdpuntoventa());
        List<String> descuentos = new ArrayList<>();
        List<Long> iddescuentos = new ArrayList<>();

        for (Puntosventasdescuentos puntoventadescuento : puntosventasdescuentos) {
            Descuentos descuento = descuentosRepositorio.obtenerPorId(puntoventadescuento.getIddescuento());
            descuentos.add(descuento.getDescuento());
            iddescuentos.add(descuento.getIddescuento());
        }
        PuntosventasOTD datosalida = new PuntosventasOTD();
        datosalida.setIdpuntoventa(puntoventa.getIdpuntoventa());
        datosalida.setIdsucursal(puntoventa.getIdsucursal());
        datosalida.setPuntoventa(puntoventa.getPuntoventa());
        datosalida.setNombrepuntoventa(puntoventa.getNombrepuntoventa());
        datosalida.setNumerofactura(puntoventa.getNumerofactura());
        datosalida.setIdusuario(usuario.getIdusuario());
        datosalida.setUsuario(usuario.getUsuario());
        datosalida.setCui(cui.getCui());
        datosalida.setCuifechavigencia(cui.getFechavigencia());
        datosalida.setCufd(cufd.getCufd());
        datosalida.setCufdcodigo(cufd.getCufdcontrol());
        datosalida.setCufdfechainicio(cufd.getFechainicio());
        datosalida.setCufdfechavigencia(cufd.getFechavigencia());
        datosalida.setDescuentos(descuentos);
        datosalida.setIddescuentos(iddescuentos);
        datosalida.setEstadosistema(puntoventa.getEstadosistema());
        datosalida.setCafc(puntoventa.getCafc());
        datosalida.setIdcufd(puntoventa.getIdcufd());
        return datosalida;
    }

    @Override
    public void adicionar(PuntosventasOTD dato) {
        Puntosventas puntoventa = new Puntosventas();
        puntoventa.setIdpuntoventa(dato.getIdpuntoventa());
        puntoventa.setIdsucursal(dato.getIdsucursal());
        puntoventa.setPuntoventa(dato.getPuntoventa());
        puntoventa.setNombrepuntoventa(dato.getNombrepuntoventa());
        puntoventa.setNumerofactura(dato.getNumerofactura());
        puntoventa.setIdusuario(dato.getIdusuario());
        puntoventa.setEstadosistema(dato.getEstadosistema());
        puntoventa.setCafc(dato.getCafc());
        puntoventa.setIdcufd(dato.getIdcufd());
        puntosventasRepositorio.adicionar(puntoventa);
        for (Long _iddescuento: dato.getIddescuentos()) {
            Puntosventasdescuentos entrada = new Puntosventasdescuentos();
            entrada.setIdpuntoventa(puntoventa.getIdpuntoventa());
            entrada.setIddescuento(_iddescuento);
            puntosventasdescuentosRepositorio.adicionar(entrada);
        }
        bitacorasServicio.registrar("ADICIONAR - Datos: "+dato.toString());
    }

    @Override
    public void modificar(PuntosventasOTD dato) {
        Puntosventas puntoventa = new Puntosventas();
        puntoventa.setIdpuntoventa(dato.getIdpuntoventa());
        puntoventa.setIdsucursal(dato.getIdsucursal());
        puntoventa.setPuntoventa(dato.getPuntoventa());
        puntoventa.setNombrepuntoventa(dato.getNombrepuntoventa());
        puntoventa.setNumerofactura(dato.getNumerofactura());
        puntoventa.setIdusuario(dato.getIdusuario());
        puntoventa.setEstadosistema(dato.getEstadosistema());
        puntoventa.setCafc(dato.getCafc());
        puntoventa.setIdcufd(dato.getIdcufd());
        puntosventasRepositorio.modificar(puntoventa);
        puntosventasdescuentosRepositorio.borrar(puntoventa.getIdpuntoventa());
        for (Long _iddescuento: dato.getIddescuentos()) {
            Puntosventasdescuentos entrada = new Puntosventasdescuentos();
            entrada.setIdpuntoventa(puntoventa.getIdpuntoventa());
            entrada.setIddescuento(_iddescuento);
            puntosventasdescuentosRepositorio.adicionar(entrada);
        }
        bitacorasServicio.registrar("MODIFICAR - Datos: "+dato.toString());
    }

    @Override
    public void borrar(Long id) {
        puntosventasRepositorio.borrar(id);
    }

}
