package xyz.cesarnvf.aigfec.repositorio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import xyz.cesarnvf.aigfec.modelo.Facturas;

@Mapper
public interface FacturasRepositorio extends GenericosRepositorio<Facturas>{

    List<Facturas> obtenerNoEnviadosRegistrados(Long idpuntoventa);

    List<Facturas> obtenerNoEnviados(Long idpuntoventa);

    List<Facturas> obtenerNoEnviadoso(Long idpuntoventa);

    LocalDateTime obtenerNoEnviadosmax(Long idpuntoventa);

    LocalDateTime obtenerNoEnviadosmin(Long idpuntoventa);

    LocalDateTime obtenerNoEnviadosmaxo(Long idpuntoventa);

    LocalDateTime obtenerNoEnviadosmino(Long idpuntoventa);

    List<Facturas> obtenerPorTerminoConPaginacion(Long idusuario, String termino, Integer pagina, Integer cantidad);

    Integer obtenerCantidad(Long idusuario, String termino);

    List<Facturas> obtenerPorTerminoIdpedido(Long idusuario, LocalDate fechainicialmes, LocalDate fechafinalmes);

    List<Facturas> obtenerFacturasValidas(Long idusuario, LocalDate fecha);

    List<Facturas> obtenerFacturasAnulados(Long idusuario, LocalDate fecha);

    List<Facturas> obtenerFacturasRechazadosAnulados(Long idusuario, LocalDate fecha);

    List<Facturas> obtenerFacturasOtros(Long idusuario, LocalDate fecha);

    List<Facturas> obtenerFacturasGeneral(Long idusuario, LocalDate fecha);

}
