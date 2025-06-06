package entities;

import java.sql.Date;
import java.sql.Time;

/**
 * Clase que representa una cita junto con los datos del usuario.
 */
public class CitaUsuario {

    private int idCita;
    private String nombre;
    private String telefono;
    private String correo;
    private String direccion;
    private Date fecha;
    private Time hora;
    private String descripcion;

    // 🔹 Constructores
    public CitaUsuario() {}

    public CitaUsuario(String nombre, String telefono, String correo, String direccion, Date fecha, Time hora, String descripcion) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
        this.fecha = fecha;
        this.hora = hora;
        this.descripcion = descripcion;
    }

    // 🔹 Getters y Setters
    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
