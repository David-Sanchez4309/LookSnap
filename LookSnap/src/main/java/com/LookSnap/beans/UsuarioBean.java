package com.LookSnap.beans;

import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import DAO.CitaUsuarioDAO;
import entities.CitaUsuario;
import entities.usuario;

import jakarta.annotation.PostConstruct;

@Named("usuarioBean")
@ViewScoped
public class UsuarioBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private usuario usuarioLogueado; // para cuando el login esté disponible
    private List<CitaUsuario> citas;
    private CitaUsuario citaSeleccionada;

    private Date nuevaFecha;
    private Date nuevaHora;
    private String nuevaDescripcion;

    private final int USUARIO_ID_FIJO = 2;

    @PostConstruct
    public void init() {
        cargarCitas();
    }

    public usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public List<CitaUsuario> getCitas() {
        return citas;
    }

    public CitaUsuario getCitaSeleccionada() {
        return citaSeleccionada;
    }

    public void setCitaSeleccionada(CitaUsuario citaSeleccionada) {
        this.citaSeleccionada = citaSeleccionada;
        if (citaSeleccionada != null) {
            this.nuevaFecha = citaSeleccionada.getFecha();
            this.nuevaHora = citaSeleccionada.getHora();
            this.nuevaDescripcion = citaSeleccionada.getDescripcion();
        }
    }

    public Date getNuevaFecha() {
        return nuevaFecha;
    }

    public void setNuevaFecha(Date nuevaFecha) {
        this.nuevaFecha = nuevaFecha;
    }

    public Date getNuevaHora() {
        return nuevaHora;
    }

    public void setNuevaHora(Date nuevaHora) {
        this.nuevaHora = nuevaHora;
    }

    public String getNuevaDescripcion() {
        return nuevaDescripcion;
    }

    public void setNuevaDescripcion(String nuevaDescripcion) {
        this.nuevaDescripcion = nuevaDescripcion;
    }

    public String seleccionarCita(CitaUsuario cita) {
        setCitaSeleccionada(cita);
        return null; // Mantiene en la misma página
    }

    public void cargarCitas() {
        CitaUsuarioDAO dao = new CitaUsuarioDAO();
        citas = dao.listarCitasPorUsuario(USUARIO_ID_FIJO);
    }

    public String eliminarCita() {
        CitaUsuarioDAO dao = new CitaUsuarioDAO();
        if (dao.eliminarCita(citaSeleccionada.getIdCita())) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Cita eliminada correctamente"));
            cargarCitas();
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al eliminar la cita", null));
        }
        return null;
    }

    public String guardarCambios() {
        CitaUsuarioDAO dao = new CitaUsuarioDAO();
        if (dao.actualizarCita(
                citaSeleccionada.getIdCita(),
                new java.sql.Date(nuevaFecha.getTime()),
                new java.sql.Time(nuevaHora.getTime()),
                nuevaDescripcion)) {

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Cambios guardados correctamente"));
            cargarCitas();
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al actualizar la cita", null));
        }
        return null;
    }

    public void verificarConexion() {
        try {
            CitaUsuarioDAO dao = new CitaUsuarioDAO();
            List<CitaUsuario> citasTest = dao.listarCitasPorUsuario(1);

            System.out.println("=== PRUEBA DE CONEXIÓN ===");
            System.out.println("Número de citas encontradas: " + citasTest.size());
            for (CitaUsuario c : citasTest) {
                System.out.println(c.getIdCita() + " - " + c.getFecha() + " - " + c.getHora());
            }

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Prueba completada. Ver consola para resultados."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error en prueba: " + e.getMessage(), null));
        }
    }
}
