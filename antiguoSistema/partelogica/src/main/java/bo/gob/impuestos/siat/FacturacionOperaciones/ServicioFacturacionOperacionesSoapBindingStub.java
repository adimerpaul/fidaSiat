/**
 * ServicioFacturacionOperacionesSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package bo.gob.impuestos.siat.FacturacionOperaciones;

public class ServicioFacturacionOperacionesSoapBindingStub extends org.apache.axis.client.Stub implements bo.gob.impuestos.siat.ServicioFacturacionOperaciones {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[8];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("verificarComunicacion");
        oper.setReturnType(new javax.xml.namespace.QName("https://siat.impuestos.gob.bo/", "respuestaComunicacion"));
        oper.setReturnClass(bo.gob.impuestos.siat.RespuestaComunicacion.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("registroPuntoVenta");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "SolicitudRegistroPuntoVenta"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("https://siat.impuestos.gob.bo/", "solicitudRegistroPuntoVenta"), bo.gob.impuestos.siat.SolicitudRegistroPuntoVenta.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("https://siat.impuestos.gob.bo/", "respuestaRegistroPuntoVenta"));
        oper.setReturnClass(bo.gob.impuestos.siat.RespuestaRegistroPuntoVenta.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "RespuestaRegistroPuntoVenta"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("registroPuntoVentaComisionista");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "SolicitudPuntoVentaComisionista"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("https://siat.impuestos.gob.bo/", "solicitudPuntoVentaComisionista"), bo.gob.impuestos.siat.SolicitudPuntoVentaComisionista.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("https://siat.impuestos.gob.bo/", "respuestaPuntoVentaComisionista"));
        oper.setReturnClass(bo.gob.impuestos.siat.RespuestaPuntoVentaComisionista.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "RespuestaPuntoVentaComisionista"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("cierreOperacionesSistema");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "SolicitudOperaciones"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("https://siat.impuestos.gob.bo/", "solicitudOperaciones"), bo.gob.impuestos.siat.SolicitudOperaciones.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("https://siat.impuestos.gob.bo/", "respuestaCierreSistemas"));
        oper.setReturnClass(bo.gob.impuestos.siat.RespuestaCierreSistemas.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "RespuestaCierreSistemas"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("consultaEventoSignificativo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "SolicitudConsultaEvento"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("https://siat.impuestos.gob.bo/", "solicitudConsultaEvento"), bo.gob.impuestos.siat.SolicitudConsultaEvento.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("https://siat.impuestos.gob.bo/", "respuestaListaEventos"));
        oper.setReturnClass(bo.gob.impuestos.siat.RespuestaListaEventos.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "RespuestaListaEventos"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("consultaPuntoVenta");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "SolicitudConsultaPuntoVenta"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("https://siat.impuestos.gob.bo/", "solicitudConsultaPuntoVenta"), bo.gob.impuestos.siat.SolicitudConsultaPuntoVenta.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("https://siat.impuestos.gob.bo/", "respuestaConsultaPuntoVenta"));
        oper.setReturnClass(bo.gob.impuestos.siat.RespuestaConsultaPuntoVenta.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "RespuestaConsultaPuntoVenta"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("registroEventoSignificativo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "SolicitudEventoSignificativo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("https://siat.impuestos.gob.bo/", "solicitudEventoSignificativo"), bo.gob.impuestos.siat.SolicitudEventoSignificativo.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("https://siat.impuestos.gob.bo/", "respuestaListaEventos"));
        oper.setReturnClass(bo.gob.impuestos.siat.RespuestaListaEventos.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "RespuestaListaEventos"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("cierrePuntoVenta");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "SolicitudCierrePuntoVenta"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("https://siat.impuestos.gob.bo/", "solicitudCierrePuntoVenta"), bo.gob.impuestos.siat.SolicitudCierrePuntoVenta.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("https://siat.impuestos.gob.bo/", "respuestaCierrePuntoVenta"));
        oper.setReturnClass(bo.gob.impuestos.siat.RespuestaCierrePuntoVenta.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "RespuestaCierrePuntoVenta"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[7] = oper;

    }

    public ServicioFacturacionOperacionesSoapBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public ServicioFacturacionOperacionesSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public ServicioFacturacionOperacionesSoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("https://siat.impuestos.gob.bo/", "eventosSignificativosDto");
            cachedSerQNames.add(qName);
            cls = bo.gob.impuestos.siat.EventosSignificativosDto.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://siat.impuestos.gob.bo/", "mensajeServicio");
            cachedSerQNames.add(qName);
            cls = bo.gob.impuestos.siat.MensajeServicio.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://siat.impuestos.gob.bo/", "model");
            cachedSerQNames.add(qName);
            cls = bo.gob.impuestos.siat.Model.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://siat.impuestos.gob.bo/", "modelDto");
            cachedSerQNames.add(qName);
            cls = bo.gob.impuestos.siat.ModelDto.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://siat.impuestos.gob.bo/", "puntosVentasDto");
            cachedSerQNames.add(qName);
            cls = bo.gob.impuestos.siat.PuntosVentasDto.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://siat.impuestos.gob.bo/", "respuestaCierrePuntoVenta");
            cachedSerQNames.add(qName);
            cls = bo.gob.impuestos.siat.RespuestaCierrePuntoVenta.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://siat.impuestos.gob.bo/", "respuestaCierreSistemas");
            cachedSerQNames.add(qName);
            cls = bo.gob.impuestos.siat.RespuestaCierreSistemas.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://siat.impuestos.gob.bo/", "respuestaComunicacion");
            cachedSerQNames.add(qName);
            cls = bo.gob.impuestos.siat.RespuestaComunicacion.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://siat.impuestos.gob.bo/", "respuestaConsultaPuntoVenta");
            cachedSerQNames.add(qName);
            cls = bo.gob.impuestos.siat.RespuestaConsultaPuntoVenta.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://siat.impuestos.gob.bo/", "respuestaListaEventos");
            cachedSerQNames.add(qName);
            cls = bo.gob.impuestos.siat.RespuestaListaEventos.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://siat.impuestos.gob.bo/", "respuestaPuntoVentaComisionista");
            cachedSerQNames.add(qName);
            cls = bo.gob.impuestos.siat.RespuestaPuntoVentaComisionista.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://siat.impuestos.gob.bo/", "respuestaRegistroPuntoVenta");
            cachedSerQNames.add(qName);
            cls = bo.gob.impuestos.siat.RespuestaRegistroPuntoVenta.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://siat.impuestos.gob.bo/", "solicitudCierrePuntoVenta");
            cachedSerQNames.add(qName);
            cls = bo.gob.impuestos.siat.SolicitudCierrePuntoVenta.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://siat.impuestos.gob.bo/", "solicitudConsultaEvento");
            cachedSerQNames.add(qName);
            cls = bo.gob.impuestos.siat.SolicitudConsultaEvento.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://siat.impuestos.gob.bo/", "solicitudConsultaPuntoVenta");
            cachedSerQNames.add(qName);
            cls = bo.gob.impuestos.siat.SolicitudConsultaPuntoVenta.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://siat.impuestos.gob.bo/", "solicitudEventoSignificativo");
            cachedSerQNames.add(qName);
            cls = bo.gob.impuestos.siat.SolicitudEventoSignificativo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://siat.impuestos.gob.bo/", "solicitudOperaciones");
            cachedSerQNames.add(qName);
            cls = bo.gob.impuestos.siat.SolicitudOperaciones.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://siat.impuestos.gob.bo/", "solicitudPuntoVentaComisionista");
            cachedSerQNames.add(qName);
            cls = bo.gob.impuestos.siat.SolicitudPuntoVentaComisionista.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://siat.impuestos.gob.bo/", "solicitudRegistroPuntoVenta");
            cachedSerQNames.add(qName);
            cls = bo.gob.impuestos.siat.SolicitudRegistroPuntoVenta.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setEncodingStyle(null);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public bo.gob.impuestos.siat.RespuestaComunicacion verificarComunicacion() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("https://siat.impuestos.gob.bo/", "verificarComunicacion"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (bo.gob.impuestos.siat.RespuestaComunicacion) _resp;
            } catch (java.lang.Exception _exception) {
                return (bo.gob.impuestos.siat.RespuestaComunicacion) org.apache.axis.utils.JavaUtils.convert(_resp, bo.gob.impuestos.siat.RespuestaComunicacion.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public bo.gob.impuestos.siat.RespuestaRegistroPuntoVenta registroPuntoVenta(bo.gob.impuestos.siat.SolicitudRegistroPuntoVenta solicitudRegistroPuntoVenta) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("https://siat.impuestos.gob.bo/", "registroPuntoVenta"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {solicitudRegistroPuntoVenta});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (bo.gob.impuestos.siat.RespuestaRegistroPuntoVenta) _resp;
            } catch (java.lang.Exception _exception) {
                return (bo.gob.impuestos.siat.RespuestaRegistroPuntoVenta) org.apache.axis.utils.JavaUtils.convert(_resp, bo.gob.impuestos.siat.RespuestaRegistroPuntoVenta.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public bo.gob.impuestos.siat.RespuestaPuntoVentaComisionista registroPuntoVentaComisionista(bo.gob.impuestos.siat.SolicitudPuntoVentaComisionista solicitudPuntoVentaComisionista) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("https://siat.impuestos.gob.bo/", "registroPuntoVentaComisionista"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {solicitudPuntoVentaComisionista});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (bo.gob.impuestos.siat.RespuestaPuntoVentaComisionista) _resp;
            } catch (java.lang.Exception _exception) {
                return (bo.gob.impuestos.siat.RespuestaPuntoVentaComisionista) org.apache.axis.utils.JavaUtils.convert(_resp, bo.gob.impuestos.siat.RespuestaPuntoVentaComisionista.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public bo.gob.impuestos.siat.RespuestaCierreSistemas cierreOperacionesSistema(bo.gob.impuestos.siat.SolicitudOperaciones solicitudOperaciones) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("https://siat.impuestos.gob.bo/", "cierreOperacionesSistema"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {solicitudOperaciones});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (bo.gob.impuestos.siat.RespuestaCierreSistemas) _resp;
            } catch (java.lang.Exception _exception) {
                return (bo.gob.impuestos.siat.RespuestaCierreSistemas) org.apache.axis.utils.JavaUtils.convert(_resp, bo.gob.impuestos.siat.RespuestaCierreSistemas.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public bo.gob.impuestos.siat.RespuestaListaEventos consultaEventoSignificativo(bo.gob.impuestos.siat.SolicitudConsultaEvento solicitudConsultaEvento) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("https://siat.impuestos.gob.bo/", "consultaEventoSignificativo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {solicitudConsultaEvento});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (bo.gob.impuestos.siat.RespuestaListaEventos) _resp;
            } catch (java.lang.Exception _exception) {
                return (bo.gob.impuestos.siat.RespuestaListaEventos) org.apache.axis.utils.JavaUtils.convert(_resp, bo.gob.impuestos.siat.RespuestaListaEventos.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public bo.gob.impuestos.siat.RespuestaConsultaPuntoVenta consultaPuntoVenta(bo.gob.impuestos.siat.SolicitudConsultaPuntoVenta solicitudConsultaPuntoVenta) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("https://siat.impuestos.gob.bo/", "consultaPuntoVenta"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {solicitudConsultaPuntoVenta});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (bo.gob.impuestos.siat.RespuestaConsultaPuntoVenta) _resp;
            } catch (java.lang.Exception _exception) {
                return (bo.gob.impuestos.siat.RespuestaConsultaPuntoVenta) org.apache.axis.utils.JavaUtils.convert(_resp, bo.gob.impuestos.siat.RespuestaConsultaPuntoVenta.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public bo.gob.impuestos.siat.RespuestaListaEventos registroEventoSignificativo(bo.gob.impuestos.siat.SolicitudEventoSignificativo solicitudEventoSignificativo) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("https://siat.impuestos.gob.bo/", "registroEventoSignificativo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {solicitudEventoSignificativo});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (bo.gob.impuestos.siat.RespuestaListaEventos) _resp;
            } catch (java.lang.Exception _exception) {
                return (bo.gob.impuestos.siat.RespuestaListaEventos) org.apache.axis.utils.JavaUtils.convert(_resp, bo.gob.impuestos.siat.RespuestaListaEventos.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public bo.gob.impuestos.siat.RespuestaCierrePuntoVenta cierrePuntoVenta(bo.gob.impuestos.siat.SolicitudCierrePuntoVenta solicitudCierrePuntoVenta) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("https://siat.impuestos.gob.bo/", "cierrePuntoVenta"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {solicitudCierrePuntoVenta});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (bo.gob.impuestos.siat.RespuestaCierrePuntoVenta) _resp;
            } catch (java.lang.Exception _exception) {
                return (bo.gob.impuestos.siat.RespuestaCierrePuntoVenta) org.apache.axis.utils.JavaUtils.convert(_resp, bo.gob.impuestos.siat.RespuestaCierrePuntoVenta.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
