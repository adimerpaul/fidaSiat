package xyz.cesarnvf.aigfec.servicio.implementacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import xyz.cesarnvf.aigfec.modelo.Roles;
import xyz.cesarnvf.aigfec.repositorio.RolesRepositorio;
import xyz.cesarnvf.aigfec.servicio.RolesServicio;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RolesServicioImplementacion implements RolesServicio {

    RolesRepositorio rolesRepositorio;

    @Override
    public List<Roles> obtenerTodos() {
        return rolesRepositorio.obtenerTodos();
    }

}
