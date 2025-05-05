package controllers;
import DAO.usuariosDAO;
import entities.usuario;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;

@Named("registroBean")
@SessionScoped
public class registroBean implements Serializable {

    private String nombre;
    private String correo;
    private String contrasena;
    private String direccion;
    private String telefono;

    private String mensaje; // Para mostrar mensajes si quieres en la página

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    // Método para registrar usuario
    public String registrar() {
        usuariosDAO dao = new usuariosDAO();

        // Crear el usuario usando los datos que ya están en los atributos del Bean
        usuario nuevo = new usuario();
        nuevo.setNombre(nombre);
        nuevo.setCorreo(correo);
        nuevo.setTelefono(telefono);
        nuevo.setDireccion(direccion);
        nuevo.setContrasena(contrasena);

        boolean registrado = dao.registrarUsuario(nuevo);

        if (registrado) {
            // Limpia los campos después de registrar
            limpiarCampos();

            // Redirige al login.xhtml
            return "/login.xhtml?faces-redirect=true";
        } else {
            // Si ya existe el correo
            mensaje = "Este correo ya está registrado.";
            return null; // No redirige, se queda en la misma página
        }
    }

    private void limpiarCampos() {
        nombre = "";
        correo = "";
        contrasena = "";
        direccion = "";
        telefono = "";
        mensaje = "";
    }
}
