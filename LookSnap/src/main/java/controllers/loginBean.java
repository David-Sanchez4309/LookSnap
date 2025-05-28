package controllers;

import entities.CitaUsuario;
import entities.administrador;
import entities.barbero;
import entities.usuario;

import DAO.usuariosDAO;
import DAO.administradorDAO;
import DAO.barberoDAO;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;

@Named("loginBean")
@SessionScoped
public class loginBean implements Serializable {
    @Inject
    private CitaBean citaBean;

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
            citaBean.setBarberoId(b.getId());
            limpiarCampos();
            return "/barbero.xhtml?faces-redirect=true";
        } else if (a != null) {
            limpiarCampos();
            return "/administrador.xhtml?faces-redirect=true";
        } else {
            limpiarCampos();
            return "/login.xhtml?faces-redirect=true";
        }
    }

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
    public String cerrarSesion() {
        u = null;
        b = null;
        a = null;
        limpiarCampos();
        return "/login.xhtml?faces-redirect=true";
    }

    private void limpiarCampos() {
        correo = null;
        contrasena = null;
    }

}
