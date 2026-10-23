package xyz.cesarnvf.aigfec.repositorio;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import xyz.cesarnvf.aigfec.modelo.Notasentrega;

@Mapper
public interface NotasentregaRepositorio {

    Notasentrega obtenerPorId(Long id);

    List<Notasentrega> obtenerNotasentrega(String termino, LocalDate fechainicialmes, LocalDate fechafinalmes);

    Long verificarExistenciaFactura(Long id);

    String verificaPropiedad(Long id);

}
