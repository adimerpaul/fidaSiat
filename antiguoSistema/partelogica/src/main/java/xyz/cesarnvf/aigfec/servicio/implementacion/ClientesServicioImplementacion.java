package xyz.cesarnvf.aigfec.servicio.implementacion;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import xyz.cesarnvf.aigfec.modelo.Clientes;
import xyz.cesarnvf.aigfec.modelo.Documentosidentidadessin;
import xyz.cesarnvf.aigfec.modelo.otd.ClientesOTD;
import xyz.cesarnvf.aigfec.repositorio.ClientesRepositorio;
import xyz.cesarnvf.aigfec.repositorio.DocumentosidentidadessinRepositorio;
import xyz.cesarnvf.aigfec.servicio.BitacorasServicio;
import xyz.cesarnvf.aigfec.servicio.ClientesServicio;
import xyz.cesarnvf.aigfec.servicio.FuncionesSIATServicio;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ClientesServicioImplementacion implements ClientesServicio {

    ClientesRepositorio clientesRepositorio;

    BitacorasServicio bitacorasServicio;

    DocumentosidentidadessinRepositorio documentosidentidadessinRepositorio;

    FuncionesSIATServicio funcionesSIATServicio;

    @Override
    public List<ClientesOTD> obtenerTodos() {
        List<ClientesOTD> salida = new ArrayList<>();
        List<Clientes> entrada = clientesRepositorio.obtenerTodos();
        for (Clientes cliente : entrada) {
            Documentosidentidadessin documentoidentidadsin = documentosidentidadessinRepositorio.obtenerPorId(cliente.getIddocumentoidentidadsin());

            ClientesOTD datosalida = new ClientesOTD(cliente.getIdcliente(), documentoidentidadsin.getIddocumentoidentidadsin(), documentoidentidadsin.getCodigosin(), documentoidentidadsin.getDocumentoidentidadsin(), cliente.getCliente(),  cliente.getNumerodocumento(), cliente.getComplemento(), cliente.getCorreoelectronico(), cliente.getCelular(), cliente.getCodigoexcepcion());
            salida.add(datosalida);
        }
        return salida;
    }

    @Override
    public List<ClientesOTD> obtenerPorTerminoConPaginacion(String termino, Integer pagina, Integer cantidad) {
        int nropagina = 0;
        if ((pagina - 1) * cantidad < 0) {
            nropagina = 0;
        } else {
            nropagina = (pagina - 1) * cantidad;
        }
        List<ClientesOTD> salida = new ArrayList<>();
        List<Clientes> entrada = clientesRepositorio.obtenerPorTerminoConPaginacion(termino, nropagina, cantidad);
        for (Clientes cliente : entrada) {
            Documentosidentidadessin documentoidentidadsin = documentosidentidadessinRepositorio.obtenerPorId(cliente.getIddocumentoidentidadsin());

            ClientesOTD datosalida = new ClientesOTD(cliente.getIdcliente(), documentoidentidadsin.getIddocumentoidentidadsin(), documentoidentidadsin.getCodigosin(), documentoidentidadsin.getDocumentoidentidadsin(), cliente.getCliente(),  cliente.getNumerodocumento(), cliente.getComplemento(), cliente.getCorreoelectronico(), cliente.getCelular(), cliente.getCodigoexcepcion());
            salida.add(datosalida);
        }
        return salida;
    }

    @Override
    public Integer obtenerCantidad(String termino) {
        return clientesRepositorio.obtenerCantidad(termino);
    }

    @Override
    public ClientesOTD obtenerPorId(Long id) {
        Clientes cliente = clientesRepositorio.obtenerPorId(id);
        Documentosidentidadessin documentoidentidadsin = documentosidentidadessinRepositorio.obtenerPorId(cliente.getIddocumentoidentidadsin());

        ClientesOTD datosalida = new ClientesOTD(cliente.getIdcliente(), documentoidentidadsin.getIddocumentoidentidadsin(), documentoidentidadsin.getCodigosin(), documentoidentidadsin.getDocumentoidentidadsin(), cliente.getCliente(),  cliente.getNumerodocumento(), cliente.getComplemento(), cliente.getCorreoelectronico(), cliente.getCelular(), cliente.getCodigoexcepcion());
        return datosalida;
    }

    @Override
    public Long adicionar(ClientesOTD dato) throws NumberFormatException, RemoteException{
        Clientes cliente = new Clientes();
        cliente.setCliente(dato.getCliente());
        cliente.setNumerodocumento(dato.getNumerodocumento());
        cliente.setComplemento(dato.getComplemento());
        cliente.setIddocumentoidentidadsin(dato.getIddocumentoidentidadsin());
        cliente.setCelular(dato.getCelular());
        cliente.setCodigoexcepcion(0);
        cliente.setCorreoelectronico(dato.getCorreoelectronico());
        if (dato.getIddocumentoidentidadsin()==3l) {
            if (funcionesSIATServicio.verificarNIT(1l, Long.parseLong(dato.getNumerodocumento()))==1) {
                cliente.setCodigoexcepcion(1);
            }
        }
        clientesRepositorio.adicionar(cliente);
        bitacorasServicio.registrar("ADICIONAR - Datos: "+dato.toString());
        return cliente.getIdcliente();
    }

    @Override
    public void modificar(ClientesOTD dato) throws NumberFormatException, RemoteException {
        Clientes cliente = clientesRepositorio.obtenerPorId(dato.getIdcliente());
        cliente.setCliente(dato.getCliente());
        cliente.setNumerodocumento(dato.getNumerodocumento());
        cliente.setComplemento(dato.getComplemento());
        cliente.setIddocumentoidentidadsin(dato.getIddocumentoidentidadsin());
        cliente.setCelular(dato.getCelular());
        cliente.setCodigoexcepcion(0);
        cliente.setCorreoelectronico(dato.getCorreoelectronico());
        if (dato.getIddocumentoidentidadsin()==3l) {
            if (funcionesSIATServicio.verificarNIT(1l, Long.parseLong(dato.getNumerodocumento()))==1) {
                cliente.setCodigoexcepcion(1);
            }
        }
        clientesRepositorio.modificar(cliente);
        bitacorasServicio.registrar("MODIFICAR - Datos: "+dato.toString());
    }

    @Override
    public ClientesOTD buscarPorDocumentoResgistradoImpuestos(String cadena) {
        Clientes cliente = clientesRepositorio.buscarPorDocumentoResgistradoImpuestos(cadena);
        if (cliente==null) {
            return null;
        }
        Documentosidentidadessin documentoidentidadsin = documentosidentidadessinRepositorio.obtenerPorId(cliente.getIddocumentoidentidadsin());

        ClientesOTD datosalida = new ClientesOTD(cliente.getIdcliente(), documentoidentidadsin.getIddocumentoidentidadsin(), documentoidentidadsin.getCodigosin(), documentoidentidadsin.getDocumentoidentidadsin(), cliente.getCliente(),  cliente.getNumerodocumento(), cliente.getComplemento(), cliente.getCorreoelectronico(), cliente.getCelular(), cliente.getCodigoexcepcion());
        return datosalida;
    }

    @Override
    public void borrar(Long id) {
        clientesRepositorio.borrar(id);
    }

}
