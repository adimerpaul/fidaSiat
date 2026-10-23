package xyz.cesarnvf.aigfec.configuracion;

import java.rmi.RemoteException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import xyz.cesarnvf.aigfec.modelo.Bitacoras;
import xyz.cesarnvf.aigfec.modelo.Puntosventas;
import xyz.cesarnvf.aigfec.modelo.auxi.Respuestas;
import xyz.cesarnvf.aigfec.modelo.auxi.RespuestasFacturas;
import xyz.cesarnvf.aigfec.repositorio.BitacorasRepositorio;
import xyz.cesarnvf.aigfec.repositorio.PuntosventasRepositorio;
import xyz.cesarnvf.aigfec.servicio.DocumentosidentidadessinServicio;
import xyz.cesarnvf.aigfec.servicio.EventossignificativossinServicio;
import xyz.cesarnvf.aigfec.servicio.FuncionesSIATServicio;
import xyz.cesarnvf.aigfec.servicio.LeyendassinServicio;
import xyz.cesarnvf.aigfec.servicio.MetodospagossinServicio;
import xyz.cesarnvf.aigfec.servicio.ProductossinServicio;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class GeneracioncufdsTareas {

    FuncionesSIATServicio funcionesSIATServicio;

    PuntosventasRepositorio puntosventasRepositorio;

    BitacorasRepositorio bitacorasRepositorio;

    LeyendassinServicio leyendassinServicio;

    ProductossinServicio productossinServicio;

    EventossignificativossinServicio eventossignificativossinServicio;

     DocumentosidentidadessinServicio documentosidentidadessinServicio;

     MetodospagossinServicio metodospagossinServicio;

    @Scheduled(cron = "0 0 4 * * ?")
    public void generarCUFD() throws RemoteException {
        try {
            List<Puntosventas> puntosventas = puntosventasRepositorio.obtenerTodos();
            for (Puntosventas puntoventa : puntosventas) {
                try {
                    List<Respuestas> respuesta = funcionesSIATServicio.registrarCUFDS(puntoventa.getIdpuntoventa());
                    Bitacoras bitacora = new Bitacoras();
                    bitacora.setBitacora("Se ha generado Codigo CUFD para el punto "+puntoventa.getPuntoventa().toString());
                    bitacora.setIdusuario(1l);
                    bitacorasRepositorio.adicionar(bitacora);

                    if (puntoventa.getPuntoventa()==0) {
                        leyendassinServicio.obtenerCodigosSin(puntoventa.getIdpuntoventa());
                        productossinServicio.obtenerCodigosSin(puntoventa.getIdpuntoventa());
                        eventossignificativossinServicio.obtenerCodigosSin(puntoventa.getIdpuntoventa());
                        documentosidentidadessinServicio.obtenerCodigosSin(puntoventa.getIdpuntoventa());
                        metodospagossinServicio.obtenerCodigosSin(puntoventa.getIdpuntoventa());
                    }
                } catch (Exception e) {
                    //e.printStackTrace();
                }
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    @Scheduled(cron = "0 * * * * ?")
    public void enviarEventosSignificativos() throws Exception {
        try {
            List<Puntosventas> puntosventas = puntosventasRepositorio.obtenerTodos();

            for (Puntosventas puntoventa : puntosventas) {
                try {
                    Long codigo = null;
                    try {
                        codigo = funcionesSIATServicio.obtenerEventoSignificativo2(puntoventa.getIdpuntoventa());
                        RespuestasFacturas respuesta = funcionesSIATServicio.regularizarEventoInaccesibilidadSIAT(puntoventa.getIdpuntoventa(), codigo);
                        if (!(codigo==null)) {
                            List<Respuestas> respuestacufd = funcionesSIATServicio.registrarCUFDS(puntoventa.getIdpuntoventa());
                            Bitacoras bitacora = new Bitacoras();
                            bitacora.setBitacora("Se ha generado Codigo CUFD para el punto "+puntoventa.getPuntoventa().toString());
                            bitacora.setIdusuario(1l);
                            bitacorasRepositorio.adicionar(bitacora);
                        }
                    } catch (Exception e) {
                        //e.printStackTrace();
                    }
                } catch (Exception e) {
                    //e.printStackTrace();
                }
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

}
