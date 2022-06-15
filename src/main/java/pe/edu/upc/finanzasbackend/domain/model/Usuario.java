package pe.edu.upc.finanzasbackend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuario")
public class Usuario extends AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nombre;

    @NotNull
    private String apellido;

    @NotNull
    @Size(max = 8)
    @Column(unique = true)
    private String dni;

    @NotNull
    @Column(unique = true)
    private String correo;

    @NotNull
    @Size(max = 20)
    private String contrasena;

}