package pe.edu.upc.finanzasbackend.domain.service;

import pe.edu.upc.finanzasbackend.resource.SaveUsuarioResource;
import pe.edu.upc.finanzasbackend.resource.UsuarioResource;

import java.util.List;

public interface UsuarioService {

    List<UsuarioResource> getAll() throws Exception;
    UsuarioResource getById(Long id) throws Exception;
    UsuarioResource createUser(SaveUsuarioResource saveUsuarioResource) throws Exception;
    UsuarioResource updateUser(SaveUsuarioResource saveUsuarioResource, Long id) throws Exception;
    String deleteUser(Long id) throws Exception;

}