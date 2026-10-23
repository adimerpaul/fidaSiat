package xyz.cesarnvf.aigfec.repositorio;

import org.apache.ibatis.annotations.Mapper;

import xyz.cesarnvf.aigfec.modelo.Clientes;

@Mapper
public interface ClientesRepositorio extends GenericosRepositorio<Clientes>{

    Clientes buscarPorDocumentoResgistradoImpuestos(String cadena);
}
