package pe.edu.upc.finanzasbackend.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.finanzasbackend.domain.model.Usuario;
import pe.edu.upc.finanzasbackend.domain.repository.UsuarioRepository;
import pe.edu.upc.finanzasbackend.domain.service.UsuarioService;
import pe.edu.upc.finanzasbackend.resource.SaveUsuarioResource;
import pe.edu.upc.finanzasbackend.resource.UsuarioResource;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    private static final ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<UsuarioResource> getAll() throws Exception{
        final List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream().map(service -> modelMapper.map(service, UsuarioResource.class)).collect(Collectors.toList());
    }

    @Override
    public UsuarioResource getById(Long id) throws Exception{
        final Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new Exception("El_id_del_usuario_no_se_ha_encontrado"));
        return modelMapper.map(usuario, UsuarioResource.class);
    }

    @Override
    public UsuarioResource createUser(SaveUsuarioResource saveUsuarioResource) throws Exception {

        Usuario usuario = modelMapper.map(saveUsuarioResource, Usuario.class);
        try{
            usuario = usuarioRepository.save(usuario);
            System.out.println("Se ha creado el usuario");
        } catch (Exception e){
            throw new Exception("Internal Server error");
        }

        return modelMapper.map(usuario, UsuarioResource.class);

    }

    @Override
    public UsuarioResource updateUser(SaveUsuarioResource saveUsuarioResource, Long id) throws Exception {

        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new Exception("El_id_del_usuario_no_se_ha_encontrado"));

        usuario.setNombre(saveUsuarioResource.getNombre());
        usuario.setDni(saveUsuarioResource.getDni());
        usuario.setCorreo(saveUsuarioResource.getCorreo());
        usuario.setContrasena(saveUsuarioResource.getContrasena());

        try{
            usuario = usuarioRepository.save(usuario);
            System.out.println("Se ha actualizado el usuario");
        } catch (Exception e){
            throw new Exception("Internal Server error");
        }

        return modelMapper.map(usuario, UsuarioResource.class);

    }

    @Override
    public String deleteUser(Long id) throws Exception {

        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new Exception("El_id_del_usuario_no_se_ha_encontrado"));

        try {
            usuarioRepository.deleteById(id);
        } catch (Exception e){
            throw new Exception("Internal Server Error");
        }

        return "Se ha eliminado al usuario";

    }

}