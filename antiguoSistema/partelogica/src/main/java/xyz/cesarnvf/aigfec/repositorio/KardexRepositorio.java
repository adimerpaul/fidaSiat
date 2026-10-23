package xyz.cesarnvf.aigfec.repositorio;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import xyz.cesarnvf.aigfec.modelo.Kardex;

@Mapper
public interface KardexRepositorio {

    List<Kardex> obtenerPorIdPedido(Long id_pedido);
}
