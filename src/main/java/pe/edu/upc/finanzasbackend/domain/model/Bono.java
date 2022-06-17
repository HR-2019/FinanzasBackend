package pe.edu.upc.finanzasbackend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="bono")
public class Bono extends AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "valor_nominal")
    private Float valorNominal;

    @Column(name = "valor_comercial")
    private Float valorComercial;

    @Column(name = "num_anos")
    private int numAnos;

    @Column(name = "frecuencia_cupon")
    private int frecuenciaCupon;

    @Column(name = "dias_ano")
    private int diasAno;

    @Column(name = "tipo_tasa_interes")
    private int tipoTasaInteres;

    @Column(name = "capitalizacion")
    private int capitalizacion;

    @Column(name = "tasa_interes")
    private Float tasaInteres;

    @Column(name = "tasa_anual_descuento")
    private Float tasaAnualDescuento;

    @Column(name = "impuesto_renta")
    private Float impuestoRenta;

    @Column(name = "fecha_emision")
    private Date fechaEmision;

    @Column(name = "porcentaje_prima")
    private Float porcentajePrima;

    @Column(name = "porcentaje_estructuracion")
    private Float porcentajeEstructuracion;

    @Column(name = "porcentaje_colocacion")
    private Float porcentajeColocacion;

    @Column(name = "porcentaje_flotacion")
    private Float porcentajeFlotacion;

    @Column(name = "porcentaje_cavali")
    private Float porcentajeCavali;

}
