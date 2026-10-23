package xyz.cesarnvf.aigfec.servicio.implementacion;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.codec.CharEncoding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import xyz.cesarnvf.aigfec.modelo.Correos;
import xyz.cesarnvf.aigfec.servicio.CorreosServicio;

@Service
public class CorreosServicioImplementacion implements CorreosServicio {

	@Autowired
	private JavaMailSender envio;

	@Override
	public Boolean enviarCorreo(Correos correo) throws MessagingException {
		try {
            return herramientaEnvioCorreo(correo);
        } catch (MessagingException e) {
            System.out.println("Error: " + e.toString());
            return false;
        }
	}

	private Boolean herramientaEnvioCorreo(Correos correo) throws MessagingException {

		MimeMessage mensaje = envio.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mensaje, true, CharEncoding.UTF_8);

        helper.setTo(correo.getCorreo());
        helper.setText(correo.getContenido(), true);
        helper.setSubject(correo.getTema());

        adjuntarArchivo(helper, correo.getRutaDocAdjuntoXML(), correo.getNombreArchivoXML());
        adjuntarArchivo(helper, correo.getRutaDocAdjuntoPDF(), correo.getNombreArchivoPDF());

        envio.send(mensaje);
        return true;
	}

	private void adjuntarArchivo(MimeMessageHelper helper, String rutaDocAdjunto, String nombreAdjunto) throws MessagingException {
        File archivo = new File(rutaDocAdjunto);
        if (archivo.exists()) {
            helper.addAttachment(nombreAdjunto, archivo);
        }
    }

}

