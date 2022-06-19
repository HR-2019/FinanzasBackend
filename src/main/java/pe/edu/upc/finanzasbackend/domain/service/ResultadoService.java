package pe.edu.upc.finanzasbackend.domain.service;

import pe.edu.upc.finanzasbackend.resource.BonoResource;
import pe.edu.upc.finanzasbackend.resource.ResultadoResource;
import pe.edu.upc.finanzasbackend.resource.SaveBonoResource;
import pe.edu.upc.finanzasbackend.resource.SaveResultadoResource;

import java.util.List;

public interface ResultadoService {

    List<ResultadoResource> getAll() throws Exception;
    ResultadoResource getById(Long id) throws Exception;
    ResultadoResource createResultado(SaveResultadoResource saveResultadoResource) throws Exception;

    //String deleteResultado(Long id) throws Exception;

}
