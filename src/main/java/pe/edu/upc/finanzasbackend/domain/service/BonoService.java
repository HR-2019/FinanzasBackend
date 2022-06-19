package pe.edu.upc.finanzasbackend.domain.service;

import pe.edu.upc.finanzasbackend.resource.BonoResource;
import pe.edu.upc.finanzasbackend.resource.SaveBonoResource;

import java.util.List;

public interface BonoService {

    List<BonoResource> getAll() throws Exception;
    BonoResource getById(Long id) throws Exception;
    BonoResource createBono(SaveBonoResource saveBonoResource) throws Exception;
    BonoResource calculate(Long id) throws Exception;

    String deleteBono(Long id) throws Exception;

}
