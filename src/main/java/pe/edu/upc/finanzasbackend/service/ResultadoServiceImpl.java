package pe.edu.upc.finanzasbackend.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.finanzasbackend.domain.model.Bono;
import pe.edu.upc.finanzasbackend.domain.model.Resultado;
import pe.edu.upc.finanzasbackend.domain.repository.BonoRepository;
import pe.edu.upc.finanzasbackend.domain.repository.ResultadoRepository;
import pe.edu.upc.finanzasbackend.domain.service.ResultadoService;
import pe.edu.upc.finanzasbackend.resource.BonoResource;
import pe.edu.upc.finanzasbackend.resource.ResultadoResource;
import pe.edu.upc.finanzasbackend.resource.SaveResultadoResource;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResultadoServiceImpl implements ResultadoService {

    @Autowired
    ResultadoRepository resultadoRepository;

    @Autowired
    BonoRepository bonoRepository;

    private static final ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<ResultadoResource> getAll() throws Exception {
        final List<Resultado> resultados = resultadoRepository.findAll();
        return resultados.stream().map(service -> modelMapper.map(service, ResultadoResource.class)).collect(Collectors.toList());
    }

    @Override
    public ResultadoResource getById(Long id) throws Exception {
        final Resultado resultado = resultadoRepository.findById(id).orElseThrow(() -> new Exception("Resultado_id_no_encontrado"));
        return modelMapper.map(resultado, ResultadoResource.class);
    }

    @Override
    public ResultadoResource createResultado(SaveResultadoResource saveResultadoResource) throws Exception {

        Bono bono = bonoRepository.findById(saveResultadoResource.getBonoId()).orElseThrow(() -> new Exception("Bono no encontrado"));

        Resultado resultado = new Resultado();

        resultado.setFrecuenciaCupon(saveResultadoResource.getFrecuenciaCupon());
        resultado.setDiasCapitalizacion(saveResultadoResource.getDiasCapitalizacion());
        resultado.setNumeroPeriodosAno(saveResultadoResource.getNumeroPeriodosAno());
        resultado.setNumeroTotalPeriodos(saveResultadoResource.getNumeroTotalPeriodos());
        resultado.setTasaEfectivaAnual(saveResultadoResource.getTasaEfectivaAnual());
        resultado.setTasaEfectivaPeriodo(saveResultadoResource.getTasaEfectivaPeriodo());
        resultado.setCok(saveResultadoResource.getCok());
        resultado.setCostesInicialesEmisor(saveResultadoResource.getCostesInicialesEmisor());
        resultado.setCostesInicialesBonista(saveResultadoResource.getCostesInicialesBonista());
        resultado.setBono(bono);

        try {
            resultado = resultadoRepository.save(resultado);
            System.out.println("Resultado creado");
        } catch (Exception e){
            throw new Exception("Internal Server Error");
        }

        return modelMapper.map(resultado, ResultadoResource.class);

    }
}
