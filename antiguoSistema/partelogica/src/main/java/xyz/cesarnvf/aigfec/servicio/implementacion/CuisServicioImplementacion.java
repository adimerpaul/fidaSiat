package xyz.cesarnvf.aigfec.servicio.implementacion;

import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import xyz.cesarnvf.aigfec.modelo.Cuis;
import xyz.cesarnvf.aigfec.modelo.Empresas;
import xyz.cesarnvf.aigfec.modelo.Puntosventas;
import xyz.cesarnvf.aigfec.modelo.Sucursales;
import xyz.cesarnvf.aigfec.modelo.auxi.Respuestacuis;
import xyz.cesarnvf.aigfec.modelo.auxi.Respuestas;
import xyz.cesarnvf.aigfec.modelo.otd.CuisOTD;
import xyz.cesarnvf.aigfec.repositorio.CuisRepositorio;
import xyz.cesarnvf.aigfec.repositorio.EmpresasRepositorio;
import xyz.cesarnvf.aigfec.repositorio.PuntosventasRepositorio;
import xyz.cesarnvf.aigfec.repositorio.SucursalesRepositorio;
import xyz.cesarnvf.aigfec.servicio.BitacorasServicio;
import xyz.cesarnvf.aigfec.servicio.CuisServicio;
import xyz.cesarnvf.aigfec.servicio.FuncionesSIATServicio;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CuisServicioImplementacion implements CuisServicio{

    CuisRepositorio cuisRepositorio;

    PuntosventasRepositorio puntosventasRepositorio;

    SucursalesRepositorio sucursalesRepositorio;

    EmpresasRepositorio empresasRepositorio;

    FuncionesSIATServicio funcionesSIATServicio;

    BitacorasServicio bitacorasServicio;

    @Override
    public List<CuisOTD> obtenerTodos() {
        List<CuisOTD> salida = new ArrayList<>();
        List<Cuis> entrada = cuisRepositorio.obtenerTodos();
        for (Cuis cui : entrada) {

            Puntosventas puntoventa =  puntosventasRepositorio.obtenerPorId(cui.getIdpuntoventa());
            Sucursales sucursal = sucursalesRepositorio.obtenerPorId(puntoventa.getIdsucursal());
            Empresas empresa = empresasRepositorio.obtenerPorId(sucursal.getIdempresa());

            CuisOTD datosalida = new CuisOTD(cui.getIdcui(), empresa.getIdempresa(), empresa.getNit(), empresa.getRazonsocial(), sucursal.getIdsucursal(), sucursal.getSucursal(), sucursal.getNombresucursal(), puntoventa.getIdpuntoventa(), puntoventa.getPuntoventa(), puntoventa.getNombrepuntoventa(), cui.getCui(), cui.getFechavigencia(), cui.getEstado());
            salida.add(datosalida);
        }
        return salida;
    }

    @Override
    public List<CuisOTD> obtenerPorTerminoConPaginacion(String termino, Integer pagina, Integer cantidad) {
        int nropagina = 0;
        if ((pagina - 1) * cantidad < 0) {
            nropagina = 0;
        } else {
            nropagina = (pagina - 1) * cantidad;
        }
        List<CuisOTD> salida = new ArrayList<>();
        List<Cuis> entrada = cuisRepositorio.obtenerPorTerminoConPaginacion(termino, nropagina, cantidad);
        for (Cuis cui : entrada) {

            Puntosventas puntoventa =  puntosventasRepositorio.obtenerPorId(cui.getIdpuntoventa());
            Sucursales sucursal = sucursalesRepositorio.obtenerPorId(puntoventa.getIdsucursal());
            Empresas empresa = empresasRepositorio.obtenerPorId(sucursal.getIdempresa());

            CuisOTD datosalida = new CuisOTD(cui.getIdcui(), empresa.getIdempresa(), empresa.getNit(), empresa.getRazonsocial(), sucursal.getIdsucursal(), sucursal.getSucursal(), sucursal.getNombresucursal(), puntoventa.getIdpuntoventa(), puntoventa.getPuntoventa(), puntoventa.getNombrepuntoventa(), cui.getCui(), cui.getFechavigencia(), cui.getEstado());
            salida.add(datosalida);
        }
        return salida;
    }

    @Override
    public Integer obtenerCantidad(String termino) {
        return cuisRepositorio.obtenerCantidad(termino);
    }

    @Override
    public CuisOTD obtenerPorId(Long id) {
        Cuis cui = cuisRepositorio.obtenerPorId(id);

        Puntosventas puntoventa =  puntosventasRepositorio.obtenerPorId(cui.getIdpuntoventa());
        Sucursales sucursal = sucursalesRepositorio.obtenerPorId(puntoventa.getIdsucursal());
        Empresas empresa = empresasRepositorio.obtenerPorId(sucursal.getIdempresa());

        CuisOTD datosalida = new CuisOTD(cui.getIdcui(), empresa.getIdempresa(), empresa.getNit(), empresa.getRazonsocial(), sucursal.getIdsucursal(), sucursal.getSucursal(), sucursal.getNombresucursal(), puntoventa.getIdpuntoventa(), puntoventa.getPuntoventa(), puntoventa.getNombrepuntoventa(), cui.getCui(), cui.getFechavigencia(), cui.getEstado());
        return datosalida;
    }

    @Override
    public void adicionarPuntosventas() {
        List<Puntosventas> puntosventas = puntosventasRepositorio.obtenerTodos();
        for (Puntosventas puntoventa: puntosventas) {
            if (cuisRepositorio.obtenerPorIdpuntoventa(puntoventa.getIdpuntoventa()) == null)
            {
                Cuis cui = new Cuis();
                cui.setIdpuntoventa(puntoventa.getIdpuntoventa());
                cui.setEstado(Boolean.FALSE);
                cui.setFechavigencia(LocalDateTime.now());
                cuisRepositorio.adicionar(cui);
            }
        }
    }

    @Override
    public List<Respuestas> obtenerCui(Long id) throws RemoteException{
        Respuestacuis respuestacuis = funcionesSIATServicio.obtenerCUIS(id);
        bitacorasServicio.registrar("Prueba Etapa 1 ejecutado Datos: " + respuestacuis);
        return respuestacuis.getMensajes();
    }

    @Override
    public List<Respuestas> adicionarCui(Long id) throws RemoteException{
        Cuis cui = cuisRepositorio.obtenerPorId(id);
        Respuestacuis respuestacuis = funcionesSIATServicio.obtenerCUIS(0l);
        cui.setCui(respuestacuis.getCodigo());
        cui.setFechavigencia(respuestacuis.getFechavigencia());
        cui.setEstado(Boolean.TRUE);
        cuisRepositorio.modificar(cui);
        bitacorasServicio.registrar("MODIFICAR - Datos: " + respuestacuis);
        return respuestacuis.getMensajes();
    }

    @Override
    public List<Respuestas> registrarPuntoventa(Long id) throws RemoteException{
        List<Respuestas> respuestaregistrarPuntoventa = funcionesSIATServicio.registrarPuntoventa(id);
        bitacorasServicio.registrar("Prueba Etapa 1 registro de punto de venta: "+ respuestaregistrarPuntoventa);
        return respuestaregistrarPuntoventa;
    }

}
