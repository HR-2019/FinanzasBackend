package pe.edu.upc.finanzasbackend.resource;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import pe.edu.upc.finanzasbackend.domain.model.Bono;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
public class ResultadoResource {

    private Long id;

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
