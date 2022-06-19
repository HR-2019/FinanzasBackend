package pe.edu.upc.finanzasbackend.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.finanzasbackend.domain.model.Bono;
import pe.edu.upc.finanzasbackend.domain.model.Resultado;
import pe.edu.upc.finanzasbackend.domain.model.Usuario;
import pe.edu.upc.finanzasbackend.domain.repository.BonoRepository;
import pe.edu.upc.finanzasbackend.domain.repository.ResultadoRepository;
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

    @Autowired
    ResultadoRepository resultadoRepository;

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

        Resultado resultado = new Resultado();

        int frecuenciaCupon = calcularFrecuenciaCupon(saveBonoResource.getFrecuenciaCupon());
        int diasCapitalizacion = calcularDiasCapitalizacion(saveBonoResource.getCapitalizacion());
        int numeroPeriodosAno = calcularPeriodosPorAno(saveBonoResource.getDiasAno(), frecuenciaCupon);
        int numeroTotalPeriodos = calcularNumTotalPeriodos(numeroPeriodosAno, saveBonoResource.getNumAnos());
        double tasaEfectivaAnual = calcularTEA(saveBonoResource.getTipoTasaInteres(), saveBonoResource.getTasaInteres(), saveBonoResource.getDiasAno(), diasCapitalizacion);
        double tasaEfectivaPeriodo = calcularTasaEfectivaPeriodo(tasaEfectivaAnual, frecuenciaCupon, saveBonoResource.getDiasAno());
        double cok = calcularCokPeriodo(saveBonoResource.getTasaAnualDescuento(), frecuenciaCupon, saveBonoResource.getDiasAno());
        float costesInicialesEmisor = calcularCostesInicialesEmisor(saveBonoResource.getPorcentajeEstructuracion(), saveBonoResource.getPorcentajeColocacion(), saveBonoResource.getPorcentajeFlotacion(), saveBonoResource.getPorcentajeCavali(), saveBonoResource.getValorComercial());
        float costesInicialesBonista = calcularCostesInicialesBonista(saveBonoResource.getPorcentajeFlotacion(), saveBonoResource.getPorcentajeCavali(), saveBonoResource.getValorComercial());

        resultado.setFrecuenciaCupon(frecuenciaCupon);
        resultado.setDiasCapitalizacion(diasCapitalizacion);
        resultado.setNumeroPeriodosAno(numeroPeriodosAno);
        resultado.setNumeroTotalPeriodos(numeroTotalPeriodos);
        resultado.setTasaEfectivaAnual(tasaEfectivaAnual);
        resultado.setTasaEfectivaPeriodo(tasaEfectivaPeriodo);
        resultado.setCok(cok);
        resultado.setCostesInicialesEmisor(costesInicialesEmisor);
        resultado.setCostesInicialesBonista(costesInicialesBonista);
        resultado.setBono(bono);
        
        try {
            bono = bonoRepository.save(bono);
            System.out.println("Bono creado");
            resultado = resultadoRepository.save(resultado);
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

    public double convertirTEP1aTEP2(Float tep1, float n1, float n2){
        Double tep2 = (double)((Math.pow(1 + (tep1 / 100), (n2 / n1)) - 1));
        return tep2;
    }

    public double convertirTNPaTEP(Float tn, float n1, float n2){
        Double tep = (double)((Math.pow(1 + ((tn / 100) / n1), n2) - 1));
        return tep;
    }

    public int calcularFrecuenciaCupon(int frecuenciaCupon){
        switch (frecuenciaCupon){
            case 1: // Mensual
                return 30;
            case 2: // Bimestral
                return 60;
            case 3: // Trimestral
                return 90;
            case 4: // Cuatrimestral
                return 120;
            case 5: // Semestral
                return 180;
            default: // Anual
                return 360;
        }
    }

    public int calcularDiasCapitalizacion(int capitalizacion){
        switch (capitalizacion){
            case 1: // Diaria
                return 1;
            case 2: // Quincenal
                return 15;
            case 3: // Mensual
                return 30;
            case 4: // Bimestral
                return 60;
            case 5: // Trimestral
                return 90;
            case 6: // Cuatrimetral
                return 120;
            case 7: // Semestral
                return 180;
            default: // Anual
                return 360;
        }
    }

    public int calcularPeriodosPorAno(int diasAno, int frecuenciaCupon){
        return diasAno / frecuenciaCupon;
    }

    public int calcularNumTotalPeriodos(int numPeriodosPorAno, int numAnos){
        return numPeriodosPorAno * numAnos;
    }

    public double calcularTEA(int tipoTasaInteres, float tasaInteres, int diasAno, int diasCapitalizacion){
        if (tipoTasaInteres == 1){ // Nominal
            return (double) ((Math.pow((1 + (tasaInteres / 100) / (double) (diasAno / diasCapitalizacion)), (double) diasAno / diasCapitalizacion) - 1) * 100);
        } else { // Efectiva
            return tasaInteres;
        }
    }

    public double calcularTasaEfectivaPeriodo(double tasaEfectivaAnual, int frecuenciaCupon, int diasAno){
        return (double) ((Math.pow(1 + tasaEfectivaAnual / 100, (double) frecuenciaCupon / diasAno) - 1) * 100);
    }

    public double calcularCokPeriodo(float tasaAnualDescuento, int frecuenciaCupon, int diasAno){
        return (double) ((Math.pow(1 + tasaAnualDescuento / 100, (double) frecuenciaCupon / diasAno) - 1) * 100);
    }

    public float calcularCostesInicialesEmisor(float porcentajeEstructuracion, float porcentajeColocacion, float porcentajeFlotacion, float porcentajeCavali, float valorComercial){
        return (porcentajeEstructuracion + porcentajeColocacion + porcentajeFlotacion + porcentajeCavali) / 100 * valorComercial;
    }

    public float calcularCostesInicialesBonista(float porcentajeFlotacion, float porcentajeCavali, float valorComercial){
        return (porcentajeFlotacion + porcentajeCavali) / 100 * valorComercial;
    }

}