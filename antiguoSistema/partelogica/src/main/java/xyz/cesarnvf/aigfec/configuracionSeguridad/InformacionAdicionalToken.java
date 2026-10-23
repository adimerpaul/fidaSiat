package xyz.cesarnvf.aigfec.configuracionSeguridad;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import xyz.cesarnvf.aigfec.modelo.Personas;
import xyz.cesarnvf.aigfec.modelo.Usuarios;
import xyz.cesarnvf.aigfec.repositorio.PersonasRepositorio;
import xyz.cesarnvf.aigfec.repositorio.UsuariosRepositorio;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class InformacionAdicionalToken implements TokenEnhancer{

    UsuariosRepositorio usuariosRepositorio;

    PersonasRepositorio personasRepositorio;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication autentificado) {
        Map<String, Object> info = new HashMap<String, Object>();
		Usuarios usuario = usuariosRepositorio.obtenerPorId(Long.parseLong(autentificado.getName()));
        Personas persona = personasRepositorio.obtenerPorId(usuario.getIdpersona());
        info.put("nombre", persona.getPrimerapellido()+" "+persona.getSegundoapellido()+" "+persona.getPrimernombre());
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
        return accessToken;
    }

}
