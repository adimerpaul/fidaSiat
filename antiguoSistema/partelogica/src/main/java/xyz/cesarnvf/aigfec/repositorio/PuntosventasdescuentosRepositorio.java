package xyz.cesarnvf.aigfec.repositorio;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import xyz.cesarnvf.aigfec.modelo.Puntosventasdescuentos;

@Mapper
public interface PuntosventasdescuentosRepositorio {

    List<Puntosventasdescuentos> obtenerPorIdpuntoventa(Long id);

    void adicionar(Puntosventasdescuentos dato);

    void borrar(Long idpuntoventa);

}
