package pe.edu.upc.finanzasbackend.resource;

import lombok.Getter;
import lombok.Setter;
import pe.edu.upc.finanzasbackend.domain.model.AuditModel;

import java.util.Date;

@Getter
@Setter
public class BonoResource extends AuditModel {

    private Long id;

    private Float valorNominal;

    private Float valorComercial;

    private int numAnos;

    private int frecuenciaCupon;

    private int diasAno;

    private int tipoTasaInteres;

    private int capitalizacion;

    private Float tasaInteres;

    private Float tasaAnualDescuento;

    private Float impuestoRenta;

    private Date fechaEmision;

    private Float porcentajePrima;

    private Float porcentajeEstructuracion;

    private Float porcentajeColocacion;

    private Float porcentajeFlotacion;

    private Float porcentajeCavali;

    private Long usuarioId;

}