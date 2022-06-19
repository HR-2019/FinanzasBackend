package pe.edu.upc.finanzasbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.finanzasbackend.domain.service.ResultadoService;
import pe.edu.upc.finanzasbackend.exception.FinanzasResponse;
import pe.edu.upc.finanzasbackend.resource.BonoResource;
import pe.edu.upc.finanzasbackend.resource.ResultadoResource;
import pe.edu.upc.finanzasbackend.resource.SaveBonoResource;
import pe.edu.upc.finanzasbackend.resource.SaveResultadoResource;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/resultado")
public class ResultadoController {

    @Autowired
    ResultadoService resultadoService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    FinanzasResponse<List<ResultadoResource>> getAllResultados() throws Exception{
        return new FinanzasResponse<>("200","Lista de resultados" , resultadoService.getAll());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    FinanzasResponse<ResultadoResource> getResultado(@PathVariable Long id) throws Exception{
        return new FinanzasResponse<>("200","Resultado consultado" , resultadoService.getById(id));
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/create")
    FinanzasResponse<ResultadoResource> createResultado(@RequestBody @Valid SaveResultadoResource saveResultadoResource) throws Exception{
        return new FinanzasResponse<>("200","Resultado registrado" , resultadoService.createResultado(saveResultadoResource));
    }

}
