package pe.edu.upc.finanzasbackend.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveUsuarioResource {

    @NotNull
    @NotBlank
    private String nombre;

    @NotNull
    @NotBlank
    private String apellido;

    @NotNull
    @NotBlank
    @Size(max = 8)
    private String dni;

    @NotNull
    @NotBlank
    private String correo;

    @NotNull
    @NotBlank
    @Size(max = 20)
    private String contrasena;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}