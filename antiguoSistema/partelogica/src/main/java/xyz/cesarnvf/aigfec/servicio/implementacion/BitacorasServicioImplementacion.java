package xyz.cesarnvf.aigfec.servicio.implementacion;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import xyz.cesarnvf.aigfec.modelo.Bitacoras;
import xyz.cesarnvf.aigfec.modelo.Usuarios;
import xyz.cesarnvf.aigfec.modelo.otd.BitacorasOTD;
import xyz.cesarnvf.aigfec.repositorio.BitacorasRepositorio;
import xyz.cesarnvf.aigfec.repositorio.UsuariosRepositorio;
import xyz.cesarnvf.aigfec.servicio.BitacorasServicio;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BitacorasServicioImplementacion  implements BitacorasServicio{

    BitacorasRepositorio bitacorasRepositorio;

    UsuariosRepositorio usuariosRepositorio;

    @Override
    public List<BitacorasOTD> obtenerPorTerminoConPaginacion(String termino, Integer pagina, Integer cantidad) {
        int nropagina = 0;
        if ((pagina - 1) * cantidad < 0) {
            nropagina = 0;
        } else {
            nropagina = (pagina - 1) * cantidad;
        }
        List<Bitacoras> bitacoras = bitacorasRepositorio.obtenerPorTerminoConPaginacion(termino, nropagina, cantidad);
        List<BitacorasOTD> salida = new ArrayList<>();
        for (Bitacoras _bitacora : bitacoras) {
            Usuarios usuario = usuariosRepositorio.obtenerPorId(_bitacora.getIdusuario());
            BitacorasOTD bitacora = new BitacorasOTD(_bitacora.getIdbitacora(), _bitacora.getIdusuario(), usuario.getUsuario(), _bitacora.getBitacora(), _bitacora.getFecha(), _bitacora.getHora());
            salida.add(bitacora);
        }
        return salida;
    }

    @Override
    public Integer obtenerCantidad(String termino) {
        return bitacorasRepositorio.obtenerCantidad(termino);
    }

    @Override
    public BitacorasOTD obtenerPorId(Long id) {
        Bitacoras _bitacora = bitacorasRepositorio.obtenerPorId(id);
        Usuarios usuario = usuariosRepositorio.obtenerPorId(_bitacora.getIdusuario());
        BitacorasOTD bitacora = new BitacorasOTD(_bitacora.getIdbitacora(), _bitacora.getIdusuario(), usuario.getUsuario(), _bitacora.getBitacora(), _bitacora.getFecha(), _bitacora.getHora());
        return bitacora;
    }


    @Override
    public void registrar(String bitacora) {
        Authentication autentificado = SecurityContextHolder.getContext().getAuthentication();
        Usuarios usuario = usuariosRepositorio.obtenerPorId(Long.parseLong(autentificado.getName()));
        Bitacoras entrada = new Bitacoras();
        entrada.setBitacora(bitacora);
        entrada.setIdusuario(usuario.getIdusuario());
        bitacorasRepositorio.adicionar(entrada);
    }


}
