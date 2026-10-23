package xyz.cesarnvf.aigfec.servicio.implementacion;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import xyz.cesarnvf.aigfec.modelo.Bitacoras;
import xyz.cesarnvf.aigfec.modelo.Personas;
import xyz.cesarnvf.aigfec.modelo.Roles;
import xyz.cesarnvf.aigfec.modelo.Usuarios;
import xyz.cesarnvf.aigfec.modelo.Usuariosroles;
import xyz.cesarnvf.aigfec.modelo.otd.UsuariosOTD;
import xyz.cesarnvf.aigfec.repositorio.BitacorasRepositorio;
import xyz.cesarnvf.aigfec.repositorio.PersonasRepositorio;
import xyz.cesarnvf.aigfec.repositorio.RolesRepositorio;
import xyz.cesarnvf.aigfec.repositorio.UsuariosRepositorio;
import xyz.cesarnvf.aigfec.repositorio.UsuariosrolesRepositorio;
import xyz.cesarnvf.aigfec.servicio.UsuariosServicio;

@Service
public class UsuariosServicioImplementacion implements UsuariosServicio, UserDetailsService {

    @Autowired
    PersonasRepositorio personasRepositorio;

    @Autowired
    BitacorasRepositorio bitacorasRepositorio;

    @Autowired
    UsuariosRepositorio usuariosRepositorio;

    @Autowired
    UsuariosrolesRepositorio usuariosrolesRepositorio;

    @Autowired
    RolesRepositorio rolesRepositorio;

    @Override
    public UserDetails loadUserByUsername(String entradausuario) throws UsernameNotFoundException {
        Usuarios usuario = usuariosRepositorio.obtenerPorUsuario(entradausuario);
        if (usuario == null) {
            throw new UsernameNotFoundException("No se encontro al usuario: " + entradausuario);
        } else {
            List<GrantedAuthority> roles = new ArrayList<>();
            List<Usuariosroles> usuarioroles = usuariosrolesRepositorio.obtenerPorIdusuario(usuario.getIdusuario());
            usuarioroles.forEach((usuariorol) -> {
                roles.add(new SimpleGrantedAuthority(usuariorol.getIdrol().toString()));
            });
            Bitacoras bitacora = new Bitacoras();
            bitacora.setBitacora("Ingreso al sistema SFIDA por el usuario "+entradausuario.toUpperCase());
            bitacora.setIdusuario(usuario.getIdusuario());
            bitacorasRepositorio.adicionar(bitacora);
            return new User(Long.toString(usuario.getIdusuario()), usuario.getClave(), roles);
        }

    }

    @Override
    public List<UsuariosOTD> obtenerPorTerminoConPaginacion(String termino, Integer pagina, Integer cantidad) {
        int nropagina = 0;
        if ((pagina - 1) * cantidad < 0) {
            nropagina = 0;
        } else {
            nropagina = (pagina - 1) * cantidad;
        }
        List<UsuariosOTD> salida = new ArrayList<>();
        List<Usuarios> entrada = usuariosRepositorio.obtenerPorTerminoConPaginacion(termino, nropagina, cantidad);
        for (Usuarios usuario : entrada) {
            Personas persona = personasRepositorio.obtenerPorId(usuario.getIdpersona());
            List<Usuariosroles> usuarioroles = usuariosrolesRepositorio.obtenerPorIdusuario(usuario.getIdusuario());
            List<String> roles = new ArrayList<>();
            List<Long> idroles = new ArrayList<>();
            for (Usuariosroles usuariorol : usuarioroles) {
                Roles rol = rolesRepositorio.obtenerPorId(usuariorol.getIdrol());
                roles.add(rol.getRol());
                idroles.add(rol.getIdrol());
            }
            UsuariosOTD datosalida = new UsuariosOTD(usuario.getIdusuario(), usuario.getIdpersona(), persona.getPrimerapellido(), persona.getSegundoapellido(), persona.getPrimernombre(), persona.getSegundonombre(), persona.getDip(), idroles, roles, usuario.getUsuario(), usuario.getClave(), usuario.getHabilitado());
            salida.add(datosalida);
        }
        return salida;
    }

    @Override
    public Integer obtenerCantidad(String termino) {
        return usuariosRepositorio.obtenerCantidad(termino);
    }

    @Override
    public UsuariosOTD obtenerPorId(Long id) {
        Usuarios usuario = usuariosRepositorio.obtenerPorId(id);
        Personas persona = personasRepositorio.obtenerPorId(usuario.getIdpersona());
        List<Usuariosroles> usuarioroles = usuariosrolesRepositorio.obtenerPorIdusuario(usuario.getIdusuario());
        List<String> roles = new ArrayList<>();
        List<Long> idroles = new ArrayList<>();
        for (Usuariosroles usuariorol : usuarioroles) {
            Roles rol = rolesRepositorio.obtenerPorId(usuariorol.getIdrol());
            roles.add(rol.getRol());
            idroles.add(rol.getIdrol());
        }
        UsuariosOTD datosalida = new UsuariosOTD(usuario.getIdusuario(), usuario.getIdpersona(), persona.getPrimerapellido(), persona.getSegundoapellido(), persona.getPrimernombre(), persona.getSegundonombre(), persona.getDip(), idroles, roles, usuario.getUsuario(), usuario.getClave(), usuario.getHabilitado());
        return datosalida;
    }

    @Override
    public void adicionar(UsuariosOTD dato) {

        Personas entradapersona = new Personas();
        entradapersona.setPrimerapellido(dato.getPrimerapellido());
        entradapersona.setSegundoapellido(dato.getSegundoapellido());
        entradapersona.setPrimernombre(dato.getPrimernombre());
        entradapersona.setSegundonombre(dato.getSegundonombre());
        entradapersona.setIdestado(1);
        Personas verificar = personasRepositorio.buscarPersona(dato.getDip());
        if (verificar==null) {
            personasRepositorio.adicionar(entradapersona);
        }
        Usuarios entradausuario = new Usuarios();
        if (verificar==null) {
            entradausuario.setIdpersona(entradapersona.getIdpersona());
        } else {
            entradausuario.setIdpersona(verificar.getIdpersona());
        }
        entradausuario.setUsuario(dato.getUsuario());
        entradausuario.setHabilitado(Boolean.TRUE);
        usuariosRepositorio.adicionar(entradausuario);

        for (Long _rol: dato.getIdroles()) {
            Usuariosroles entradausuarioroles = new Usuariosroles();
            entradausuarioroles.setIdusuario(entradausuario.getIdusuario());
            entradausuarioroles.setIdrol(_rol);
            usuariosrolesRepositorio.adicionar(entradausuarioroles);
        }
    }

    @Override
    public void modificar(UsuariosOTD dato) {
        Personas entradapersona = personasRepositorio.obtenerPorId(dato.getIdpersona());
        entradapersona.setPrimerapellido(dato.getPrimerapellido());
        entradapersona.setSegundoapellido(dato.getSegundoapellido());
        entradapersona.setPrimernombre(dato.getPrimernombre());
        entradapersona.setSegundonombre(dato.getSegundonombre());
        entradapersona.setIdestado(1);
        personasRepositorio.modificar(entradapersona);

        Usuarios entradausuario = usuariosRepositorio.obtenerPorId(dato.getIdusuario());
        entradausuario.setIdpersona(entradapersona.getIdpersona());
        entradausuario.setUsuario(dato.getUsuario());
        entradausuario.setHabilitado(dato.getHabilitado());
        usuariosRepositorio.modificar(entradausuario);

        usuariosrolesRepositorio.borrar(entradausuario.getIdusuario());

        for (Long _rol: dato.getIdroles()) {
            Usuariosroles entradausuarioroles = new Usuariosroles();
            entradausuarioroles.setIdusuario(entradausuario.getIdusuario());
            entradausuarioroles.setIdrol(_rol);
            usuariosrolesRepositorio.adicionar(entradausuarioroles);
        }
    }


    @Override
    public void inicializarclave(Long id) {
        usuariosRepositorio.inicializarclave(id);
    }

    @Override
    public void cambiarclave(UsuariosOTD usuario) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuarios entradausuario = usuariosRepositorio.obtenerPorId(Long.parseLong(auth.getName()));
        entradausuario.setClave(usuario.getClave());
        usuariosRepositorio.cambiarclave(entradausuario);
    }

    @Override
    public List<Usuarios> obtenerTodosRolVendedor() {
        return usuariosRepositorio.obtenerTodosRolVendedor();
    }

    @Override
    public UsuariosOTD buscarPorIdT(Long id) {
       return usuariosRepositorio.buscarPorIdT(id);
    }

}
