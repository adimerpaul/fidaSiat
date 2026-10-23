package xyz.cesarnvf.aigfec.configuracion;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.soap.MimeHeaders;

import org.apache.axis.AxisFault;
import org.apache.axis.Message;
import org.apache.axis.client.Call;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import bo.gob.impuestos.siat.FacturacionCodigos.ServicioFacturacionCodigos;
import bo.gob.impuestos.siat.FacturacionCodigos.ServicioFacturacionCodigosLocator;
import bo.gob.impuestos.siat.FacturacionCodigos.ServicioFacturacionCodigosSoapBindingStub;
import bo.gob.impuestos.siat.FacturacionOperaciones.ServicioFacturacionOperaciones;
import bo.gob.impuestos.siat.FacturacionOperaciones.ServicioFacturacionOperacionesLocator;
import bo.gob.impuestos.siat.FacturacionOperaciones.ServicioFacturacionOperacionesSoapBindingStub;
import bo.gob.impuestos.siat.FacturacionSincronizacion.ServicioFacturacionSincronizacion;
import bo.gob.impuestos.siat.FacturacionSincronizacion.ServicioFacturacionSincronizacionLocator;
import bo.gob.impuestos.siat.FacturacionSincronizacion.ServicioFacturacionSincronizacionSoapBindingStub;
import bo.gob.impuestos.siat.ServicioFacturacionCompraVenta.ServicioFacturacion;
import bo.gob.impuestos.siat.ServicioFacturacionCompraVenta.ServicioFacturacionLocator;
import bo.gob.impuestos.siat.ServicioFacturacionCompraVenta.ServicioFacturacionSoapBindingStub;
import xyz.cesarnvf.aigfec.repositorio.EmpresasRepositorio;

@Configuration
public class ImpuestosConfiguracion {

    @Autowired
    private EmpresasRepositorio empresasRepositorio;

    private String token;
    private String rutaServidor;

    @Bean
    public bo.gob.impuestos.siat.ServicioFacturacionCodigos servicioFacturacionCodigos() throws AxisFault, MalformedURLException {

        token = empresasRepositorio.obtenerEmpresaActiva().getToken();

        ServicioFacturacionCodigos service = new ServicioFacturacionCodigosLocator() {

            @Override
            public Call createCall() {
                _call = new Call(this) {
                    @Override
                    public void setRequestMessage(Message msg)
                    {
                        super.setRequestMessage(msg);

                        MimeHeaders mimeHeaders = msg.getMimeHeaders();
                        mimeHeaders.addHeader("apiKey", "TokenApi " + token);
                    }
                };
                return _call;
            }
        };
        rutaServidor = empresasRepositorio.obtenerEmpresaActiva().getRuta()+"/v2/FacturacionCodigos";
        return new ServicioFacturacionCodigosSoapBindingStub(new URL(rutaServidor), service);
    }

    @Bean
    public bo.gob.impuestos.siat.ServicioFacturacionOperaciones servicioFacturacionOperaciones() throws AxisFault, MalformedURLException {

        token = empresasRepositorio.obtenerEmpresaActiva().getToken();

        ServicioFacturacionOperaciones service = new ServicioFacturacionOperacionesLocator() {
            @Override
            public Call createCall() {
                _call = new Call(this) {
                    @Override
                    public void setRequestMessage(Message msg) {
                        super.setRequestMessage(msg);

                        MimeHeaders mimeHeaders = msg.getMimeHeaders();
                        mimeHeaders.addHeader("apiKey", "TokenApi " + token);
                    }
                };
                return _call;
            }
        };
        rutaServidor = empresasRepositorio.obtenerEmpresaActiva().getRuta()+"/v2/FacturacionOperaciones";
        return new ServicioFacturacionOperacionesSoapBindingStub(new URL(rutaServidor), service);
    }

    @Bean
    public bo.gob.impuestos.siat.ServicioFacturacionSincronizacion servicioFacturacionSincronizacion() throws AxisFault, MalformedURLException {

        token = empresasRepositorio.obtenerEmpresaActiva().getToken();

        ServicioFacturacionSincronizacion service = new ServicioFacturacionSincronizacionLocator() {
            @Override
            public Call createCall() {
                _call = new Call(this) {
                    @Override
                    public void setRequestMessage(Message msg) {
                        super.setRequestMessage(msg);

                        MimeHeaders mimeHeaders = msg.getMimeHeaders();
                        mimeHeaders.addHeader("apiKey", "TokenApi " + token);
                    }
                };
                return _call;
            }
        };
        rutaServidor = empresasRepositorio.obtenerEmpresaActiva().getRuta()+"/v2/FacturacionSincronizacion";
        return new ServicioFacturacionSincronizacionSoapBindingStub(new URL(rutaServidor), service);
    }

    @Bean
    public bo.gob.impuestos.siat.ServicioFacturacion servicioFacturacion() throws AxisFault, MalformedURLException {

        token = empresasRepositorio.obtenerEmpresaActiva().getToken();

        ServicioFacturacion service = new ServicioFacturacionLocator() {
            @Override
            public Call createCall() {
                _call = new Call(this) {
                    @Override
                    public void setRequestMessage(Message msg) {
                        super.setRequestMessage(msg);

                        MimeHeaders mimeHeaders = msg.getMimeHeaders();
                        mimeHeaders.addHeader("apiKey", "TokenApi " + token);
                    }
                };
                return _call;
            }
        };
        rutaServidor = empresasRepositorio.obtenerEmpresaActiva().getRuta()+"/v2/ServicioFacturacionCompraVenta";
        return new ServicioFacturacionSoapBindingStub(new URL(rutaServidor), service);
    }

}

