package pe.edu.upc.finanzasbackend.resource;

import lombok.Getter;
import lombok.Setter;
import pe.edu.upc.finanzasbackend.domain.model.Bono;

@Getter
@Setter
public class SaveResultadoResource {

    private int frecuenciaCupon;

    private int diasCapitalizacion;

    private int numeroPeriodosAno;

    private int numeroTotalPeriodos;

    private Double tasaEfectivaAnual;

    private Double tasaEfectivaPeriodo;

    private Double cok;

    private Float costesInicialesEmisor;

    private Float costesInicialesBonista;

    private Long bonoId;

}
