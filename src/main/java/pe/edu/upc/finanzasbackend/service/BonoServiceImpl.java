package pe.edu.upc.finanzasbackend.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.finanzasbackend.domain.model.Bono;
import pe.edu.upc.finanzasbackend.domain.model.Usuario;
import pe.edu.upc.finanzasbackend.domain.repository.BonoRepository;
import pe.edu.upc.finanzasbackend.domain.repository.UsuarioRepository;
import pe.edu.upc.finanzasbackend.domain.service.BonoService;
import pe.edu.upc.finanzasbackend.resource.BonoResource;
import pe.edu.upc.finanzasbackend.resource.SaveBonoResource;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BonoServiceImpl implements BonoService {

    @Autowired
    BonoRepository bonoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    private static final ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<BonoResource> getAll() throws Exception {
        final List<Bono> bonos = bonoRepository.findAll();
        return bonos.stream().map(service -> modelMapper.map(service, BonoResource.class)).collect(Collectors.toList());
    }

    @Override
    public BonoResource getById(Long id) throws Exception {
        final Bono bono = bonoRepository.findById(id).orElseThrow(() -> new Exception("Bono_id_no_encontrado"));
        return modelMapper.map(bono, BonoResource.class);
    }

    @Override
    public BonoResource createBono(SaveBonoResource saveBonoResource) throws Exception {
        
        Usuario usuario = usuarioRepository.findById(saveBonoResource.getUsuarioId()).orElseThrow(() -> new Exception("Usuario no encontrado"));
        Bono bono = new Bono();
        bono.setValorNominal(saveBonoResource.getValorNominal());
        bono.setValorComercial(saveBonoResource.getValorComercial());
        bono.setNumAnos(saveBonoResource.getNumAnos());
        bono.setFrecuenciaCupon(saveBonoResource.getFrecuenciaCupon());
        bono.setDiasAno(saveBonoResource.getDiasAno());
        bono.setTipoTasaInteres(saveBonoResource.getTipoTasaInteres());
        bono.setCapitalizacion(saveBonoResource.getCapitalizacion());
        bono.setTasaInteres(saveBonoResource.getTasaInteres());
        bono.setTasaAnualDescuento(saveBonoResource.getTasaAnualDescuento());
        bono.setImpuestoRenta(saveBonoResource.getImpuestoRenta());
        bono.setFechaEmision(saveBonoResource.getFechaEmision());
        bono.setPorcentajePrima(saveBonoResource.getPorcentajePrima());
        bono.setPorcentajeEstructuracion(saveBonoResource.getPorcentajeEstructuracion());
        bono.setPorcentajeColocacion(saveBonoResource.getPorcentajeColocacion());
        bono.setPorcentajeFlotacion(saveBonoResource.getPorcentajeFlotacion());
        bono.setPorcentajeCavali(saveBonoResource.getPorcentajeCavali());
        bono.setUsuario(usuario);
        
        try {
            bono = bonoRepository.save(bono);
            System.out.println("Bono creado");
        } catch (Exception e) {
            throw new Exception("Internal Server error");
        }

        return modelMapper.map(bono, BonoResource.class);
        
    }

    @Override
    public BonoResource calculate(Long id) throws Exception {
        return null;
    }

    @Override
    public String deleteBono(Long id) throws Exception {
        Bono bono = bonoRepository.findById(id).orElseThrow(() -> new Exception("Bono_id_no_encontrado"));
        try {
            bonoRepository.deleteById(id);
        }catch (Exception e){
            throw new Exception("Internal Server Error");
        }
        return "Bono borrado";
    }

}