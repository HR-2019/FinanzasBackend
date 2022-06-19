package pe.edu.upc.finanzasbackend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="resultado")
public class Resultado extends AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "frecuencia_cupon")
    private int frecuenciaCupon;

    @Column(name = "dias_capitalizacion")
    private int diasCapitalizacion;

    @Column(name = "numero_periodos_ano")
    private int numeroPeriodosAno;

    @Column(name = "numero_total_periodos")
    private int numeroTotalPeriodos;

    @Column(name = "tasa_efectiva_anual")
    private Double tasaEfectivaAnual;

    @Column(name = "tasa_efectiva_periodo")
    private Double tasaEfectivaPeriodo;

    @Column(name = "cok")
    private Double cok;

    @Column(name = "costes_iniciales_emisor")
    private Float costesInicialesEmisor;

    @Column(name = "costes_iniciales_bonista")
    private Float costesInicialesBonista;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bono_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Bono bono;

}
