package controllers;

import entities.CitaUsuario;
import entities.administrador;
import entities.barbero;
import entities.usuario;

import DAO.usuariosDAO;
import DAO.administradorDAO;
import DAO.barberoDAO;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;

@Named("loginBean")
@SessionScoped
public class loginBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String correo;
    private String contrasena;

    private usuario u;
    private barbero b;
    private administrador a;

    private usuariosDAO ud = new usuariosDAO();
    private barberoDAO bd = new barberoDAO();
    private administradorDAO ad = new administradorDAO();

    // Constructor
    public loginBean() {
        // Los DAO ya se inicializan arriba (se puede dejar vacío el constructor)
    }

    // Getters y Setters
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

    // Método para iniciar sesión
    public String iniciarSesion() {
        u = ud.login(correo, contrasena);
        b = bd.login(correo, contrasena);
        a = ad.login(correo, contrasena);

        if (u != null) {
            limpiarCampos(); // Limpia los campos después de login
            return "/index.xhtml?faces-redirect=true";
        } else if (b != null) {
            limpiarCampos();
            return "/barbero.xhtml?faces-redirect=true";
        } else if (a != null) {
            limpiarCampos();
            return "/administrador.xhtml?faces-redirect=true";
        } else {
            // Aquí podrías agregar un mensaje de error usando FacesContext
            limpiarCampos();
            return "/login.xhtml?faces-redirect=true";
        }
    }

    // Obtener el nombre del usuario/barbero/admin logueado
    public String getNombreLogueado() {
        if (b != null) {
            return b.getNombre();
        } else if (a != null) {
            return a.getNombre();
        } else if (u != null) {
            return u.getNombre();
        } else {
            return "Invitado";
        }
    }

    // Cerrar sesión y limpiar objetos
    public String cerrarSesion() {
        u = null;
        b = null;
        a = null;
        limpiarCampos();
        return "/login.xhtml?faces-redirect=true";
    }

    // Método para limpiar los campos del formulario
    private void limpiarCampos() {
        correo = null;
        contrasena = null;
    }
}
