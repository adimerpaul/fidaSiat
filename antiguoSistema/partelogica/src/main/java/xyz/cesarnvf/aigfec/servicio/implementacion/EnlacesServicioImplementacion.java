package xyz.cesarnvf.aigfec.servicio.implementacion;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import xyz.cesarnvf.aigfec.modelo.Enlaces;
import xyz.cesarnvf.aigfec.modelo.Enlacesroles;
import xyz.cesarnvf.aigfec.modelo.Roles;
import xyz.cesarnvf.aigfec.modelo.otd.EnlacesOTD;
import xyz.cesarnvf.aigfec.modelo.otd.MenusOTD;
import xyz.cesarnvf.aigfec.modelo.otd.SubmenusOTD;
import xyz.cesarnvf.aigfec.repositorio.EnlacesRepositorio;
import xyz.cesarnvf.aigfec.repositorio.EnlacesrolesRepositorio;
import xyz.cesarnvf.aigfec.repositorio.RolesRepositorio;
import xyz.cesarnvf.aigfec.servicio.EnlacesServicio;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EnlacesServicioImplementacion implements EnlacesServicio{

    EnlacesRepositorio enlacesRepositorio;

    EnlacesrolesRepositorio enlacesrolesRepositorio;

    RolesRepositorio rolesRepositorio;

    @Override
    public List<MenusOTD> obtenerEnlacePorIdusuario() {
        Long idusuario = 0l;
        Authentication autentificado = SecurityContextHolder.getContext().getAuthentication();
        idusuario = Long.parseLong(autentificado.getName());
        List<Long> enlacespadres =  enlacesRepositorio.obtenerPorIdusuarioPadres(idusuario);
        List<MenusOTD> salida = new ArrayList<MenusOTD>();

        for (Long idenlacep : enlacespadres) {
            Enlaces enlacep = enlacesRepositorio.obtenerPorId(idenlacep);
            List<Long> enlaceshijos = enlacesRepositorio.obtenerPorIdusuarioHijos(idusuario, enlacep.getIdenlace());
            List<SubmenusOTD> salidas = new ArrayList<SubmenusOTD>();
            for (Long idenlace : enlaceshijos) {
                Enlaces enlace = enlacesRepositorio.obtenerPorId(idenlace);
                SubmenusOTD salidasub =  new SubmenusOTD(enlace.getEnlace(), enlace.getIconoenlace(), enlace.getRuta());
                salidas.add(salidasub);
            }
            MenusOTD salidamenu = new MenusOTD(enlacep.getEnlace(), enlacep.getIconoenlace(), enlacep.getRuta(), salidas);
            salida.add(salidamenu);
        }
        return salida;

    }

    @Override
    public List<EnlacesOTD> obtenerPorTerminoConPaginacion(String termino, Integer pagina, Integer cantidad) {
        int nropagina = 0;
        if ((pagina - 1) * cantidad < 0) {
            nropagina = 0;
        } else {
            nropagina = (pagina - 1) * cantidad;
        }
        List<EnlacesOTD> salida = new ArrayList<>();
        List<Enlaces> entrada = enlacesRepositorio.obtenerPorTerminoConPaginacion(termino, nropagina, cantidad);
        for (Enlaces enlace : entrada) {
            String enlacepadredato = "";
            if (enlace.getIdenlacepadre().equals(0l)) {
                enlacepadredato = "Principal";
            } else {
                enlacepadredato = enlacesRepositorio.obtenerPorId(enlace.getIdenlacepadre()).getEnlace();
            }
            List<Enlacesroles> enlacesroles = enlacesrolesRepositorio.obtenerPorIdenlace(enlace.getIdenlace());
            List<String> roles = new ArrayList<>();
            List<Long> idroles = new ArrayList<>();
            for (Enlacesroles enlacerol : enlacesroles) {
                Roles rol = rolesRepositorio.obtenerPorId(enlacerol.getIdrol());
                roles.add(rol.getRol());
                idroles.add(rol.getIdrol());
            }
            EnlacesOTD datosalida = new EnlacesOTD(enlace.getIdenlace(), enlace.getIdenlacepadre(), enlacepadredato, enlace.getEnlace(), enlace.getRuta(), enlace.getIconoenlace(), enlace.getOrden(), idroles, roles);
            salida.add(datosalida);
        }
        return salida;

    }

    @Override
    public Integer obtenerCantidad(String termino) {
        return enlacesRepositorio.obtenerCantidad(termino);
    }

    @Override
    public List<EnlacesOTD> obtenerTodosPrincipales() {
        List<EnlacesOTD> salida = new ArrayList<>();
        List<Enlaces> entrada = enlacesRepositorio.obtenerTodosPrincipales();
        for (Enlaces enlace : entrada) {
            List<String> roles = new ArrayList<>();
            List<Long> idroles = new ArrayList<>();
            EnlacesOTD datosalida = new EnlacesOTD(enlace.getIdenlace(), enlace.getIdenlacepadre(), "", enlace.getEnlace(), enlace.getRuta(), enlace.getIconoenlace(), enlace.getOrden(), idroles, roles);
            salida.add(datosalida);
        }
        return salida;
    }

    @Override
    public EnlacesOTD obtenerPorId(Long id) {
        Enlaces enlace = enlacesRepositorio.obtenerPorId(id);
        String enlacepadredato = "";
        if (enlace.getIdenlacepadre().equals(0l)) {
            enlacepadredato = "Ninguno";
        } else {
            enlacepadredato = enlacesRepositorio.obtenerPorId(enlace.getIdenlacepadre()).getEnlace();
        }
        List<Enlacesroles> enlacesroles = enlacesrolesRepositorio.obtenerPorIdenlace(enlace.getIdenlace());
        List<String> roles = new ArrayList<>();
        List<Long> idroles = new ArrayList<>();
        for (Enlacesroles enlacerol : enlacesroles) {
            Roles rol = rolesRepositorio.obtenerPorId(enlacerol.getIdrol());
            roles.add(rol.getRol());
            idroles.add(rol.getIdrol());
        }
        EnlacesOTD datosalida = new EnlacesOTD(enlace.getIdenlace(), enlace.getIdenlacepadre(), enlacepadredato, enlace.getEnlace(), enlace.getRuta(), enlace.getIconoenlace(), enlace.getOrden(), idroles, roles);
        return datosalida;

    }

    @Override
    public void adicionar(EnlacesOTD dato) {
        Enlaces entradaenlace = new Enlaces();
        entradaenlace.setIdenlace(dato.getIdenlace());
        entradaenlace.setIdenlacepadre(dato.getIdenlacepadre());
        entradaenlace.setEnlace(dato.getEnlace());
        entradaenlace.setRuta(dato.getRuta());
        entradaenlace.setIconoenlace(dato.getIconoenlace());
        entradaenlace.setOrden(dato.getOrden());

        enlacesRepositorio.adicionar(entradaenlace);

        for (Long _rol: dato.getIdroles()) {
            Enlacesroles entradaenlaceroles = new Enlacesroles();
            entradaenlaceroles.setIdenlace(entradaenlace.getIdenlace());
            entradaenlaceroles.setIdrol(_rol);
            enlacesrolesRepositorio.adicionar(entradaenlaceroles);
        }
    }

    @Override
    public void modificar(EnlacesOTD dato) {
        Enlaces entradaenlace = new Enlaces();
        entradaenlace.setIdenlace(dato.getIdenlace());
        entradaenlace.setIdenlacepadre(dato.getIdenlacepadre());
        entradaenlace.setEnlace(dato.getEnlace());
        entradaenlace.setRuta(dato.getRuta());
        entradaenlace.setIconoenlace(dato.getIconoenlace());
        entradaenlace.setOrden(dato.getOrden());

        enlacesRepositorio.modificar(entradaenlace);
        enlacesrolesRepositorio.borrar(entradaenlace.getIdenlace());

        for (Long _rol: dato.getIdroles()) {
            Enlacesroles entradaenlaceroles = new Enlacesroles();
            entradaenlaceroles.setIdenlace(entradaenlace.getIdenlace());
            entradaenlaceroles.setIdrol(_rol);
            enlacesrolesRepositorio.adicionar(entradaenlaceroles);
        }

    }


}
