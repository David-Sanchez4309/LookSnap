package controllers;
import DAO.CitaUsuarioDAO;
import DAO.barberoDAO;
import entities.CitaUsuario;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Named("citaBean")
@SessionScoped
public class CitaBean implements Serializable {

    private List<CitaUsuario> citasUsuario;
    private List<CitaUsuario> citasBarbero;

    private int usuarioId;
    private int barberoId;

    private CitaUsuario citaEditando = new CitaUsuario();

    // Getters y Setters

    public List<CitaUsuario> getCitasUsuario() {
        return citasUsuario;
    }

    public List<CitaUsuario> getCitasBarbero() {
        return citasBarbero;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getBarberoId() {
        return barberoId;
    }

    public void setBarberoId(int barberoId) {
        this.barberoId = barberoId;
    }

    public CitaUsuario getCitaEditando() {
        return citaEditando;
    }

    public void setCitaEditando(CitaUsuario citaEditando) {
        this.citaEditando = citaEditando;
    }

    // Métodos de acción

    public void cargarCitasUsuario() {
        CitaUsuarioDAO dao = new CitaUsuarioDAO();
        this.citasUsuario = dao.listarCitasPorUsuario(usuarioId);
    }

    public void cargarCitasBarbero() {
        CitaUsuarioDAO dao = new CitaUsuarioDAO();
        this.citasBarbero = dao.listarCitasPorBarbero(barberoId);
    }

    public void eliminarCita(int idCita) {
        CitaUsuarioDAO dao = new CitaUsuarioDAO();
        dao.eliminarCita(idCita);
        cargarCitasUsuario();
        cargarCitasBarbero();

    }

    public void prepararEdicion(CitaUsuario cita) {
        this.citaEditando = cita;
    }


    public void actualizarCita() {
        CitaUsuarioDAO dao = new CitaUsuarioDAO();
        boolean exito = dao.actualizarCita(citaEditando.getIdCita(),
                citaEditando.getFecha(),
                citaEditando.getHora(),
                citaEditando.getDescripcion());
        if (exito) {
            citaEditando = null;  // Limpia la cita que se estaba editando
            cargarCitasUsuario();  // Recarga las citas del usuario
        } else {
            System.out.println("Error al actualizar la cita.");
        }
    }


    public void actualizarDescripcionCita() {
        CitaUsuarioDAO dao = new CitaUsuarioDAO();
        boolean exito = dao.actualizarDescripcionCita(citaEditando.getIdCita(),
                citaEditando.getDescripcion());
        if (exito) {
            citaEditando = null;  // Limpia la cita que se estaba editando
            cargarCitasUsuario();  // Recarga las citas del usuario
        } else {
            System.out.println("Error al actualizar la descripción de la cita.");
        }
    }

    public void guardarCambios() {
        CitaUsuarioDAO dao = new CitaUsuarioDAO();
        boolean exito = dao.actualizarCita(citaEditando.getIdCita(),
                citaEditando.getFecha(),
                citaEditando.getHora(),
                citaEditando.getDescripcion());
        if (exito) {
            citaEditando = null;  // Limpia la cita que se estaba editando
            cargarCitasUsuario();  // Recarga las citas del usuario
        } else {
            System.out.println("Error al guardar los cambios.");
        }
    }

    public void cancelarEdicion() {
        citaEditando = null;
    }




}
