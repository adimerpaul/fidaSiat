package xyz.cesarnvf.aigfec.servicio;

import javax.mail.MessagingException;

import xyz.cesarnvf.aigfec.modelo.Correos;

public interface CorreosServicio {

    Boolean enviarCorreo(Correos correo) throws MessagingException;
}

