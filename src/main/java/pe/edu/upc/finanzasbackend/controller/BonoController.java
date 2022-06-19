package pe.edu.upc.finanzasbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.finanzasbackend.domain.service.BonoService;
import pe.edu.upc.finanzasbackend.exception.FinanzasResponse;
import pe.edu.upc.finanzasbackend.resource.BonoResource;
import pe.edu.upc.finanzasbackend.resource.SaveBonoResource;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/bono")
public class BonoController {

    @Autowired
    BonoService bonoService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    FinanzasResponse<List<BonoResource>> getAllUsers() throws Exception{
        return new FinanzasResponse<>("200","Lista de bonos" , bonoService.getAll());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    FinanzasResponse<BonoResource> getUser(@PathVariable Long id) throws Exception{
        return new FinanzasResponse<>("200","Bono consultado" , bonoService.getById(id));
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/create")
    FinanzasResponse<BonoResource> createBono(@RequestBody @Valid SaveBonoResource saveBonoResource) throws Exception{
        return new FinanzasResponse<>("200","Bono registrado" , bonoService.createBono(saveBonoResource));
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/calculate/{id}")
    FinanzasResponse<BonoResource> calcularBono(@PathVariable Long id) throws Exception{
        return new FinanzasResponse<>("200","Bono calculado" , bonoService.calculate(id));
    }

}
