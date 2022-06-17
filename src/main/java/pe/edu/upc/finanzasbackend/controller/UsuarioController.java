package pe.edu.upc.finanzasbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.finanzasbackend.domain.service.UsuarioService;
import pe.edu.upc.finanzasbackend.exception.FinanzasResponse;
import pe.edu.upc.finanzasbackend.resource.SaveUsuarioResource;
import pe.edu.upc.finanzasbackend.resource.UsuarioResource;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    FinanzasResponse<List<UsuarioResource>> getAllUsers() throws Exception{
        return new FinanzasResponse<>("200","Lista de Usuarios" , usuarioService.getAll());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    FinanzasResponse<UsuarioResource> getUser(@PathVariable Long id) throws Exception{
        return new FinanzasResponse<>("200","Usuario consultado" , usuarioService.getById(id));
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/create")
    FinanzasResponse<UsuarioResource> createUser(@RequestBody @Valid SaveUsuarioResource saveUsuarioResource) throws Exception{
        return new FinanzasResponse<>("200","Usuario creado" , usuarioService.createUser(saveUsuarioResource));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/update/{id}")
    FinanzasResponse<UsuarioResource> updateUser(@RequestBody @Valid SaveUsuarioResource saveUsuarioResource,@PathVariable Long id) throws Exception{
        return new FinanzasResponse<>("200","Usuario actualizado" , usuarioService.updateUser(saveUsuarioResource,id));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/delete")
    FinanzasResponse<String> deleteUser(@RequestParam Long id) throws Exception{
        return new FinanzasResponse<>("200","Usuario eliminado" , usuarioService.deleteUser(id));
    }

}