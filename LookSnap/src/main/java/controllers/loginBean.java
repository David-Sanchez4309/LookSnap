package controllers;
import entities.CitaUsuario;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import entities.administrador;
import  DAO.usuariosDAO;
import DAO.administradorDAO;
import DAO.barberoDAO;
import entities.barbero;
import entities.usuario;


@Named("loginBean")
@SessionScoped
public class loginBean implements Serializable {
    private String correo;
    private String contrasena;

    private usuario u;

    private usuariosDAO ud;

    private barbero b;

    private barberoDAO bd;


    private administrador a;

    private administradorDAO ad;


    public loginBean() {
        ud = new usuariosDAO();
        bd = new barberoDAO();
        ad = new administradorDAO();
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

    public String iniciarSesion() {
        u = ud.login(correo, contrasena);
        b = bd.login(correo, contrasena);
        a = ad.login(correo, contrasena);
        if (u != null) {
            return "/index?faces-redirect=true";

        } else if (b != null) {
            return "/barbero?faces-redirect=true";
        } else if (a != null) {
            return "/administrador?faces-redirect=true";
        } else {
            return "/login?faces-redirect=true";
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
        return "/login?faces-redirect=true";
    }


}
