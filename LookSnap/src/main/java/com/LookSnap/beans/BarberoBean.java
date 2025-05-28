package com.LookSnap.beans;

import DAO.barberoDAO;
import entities.barbero;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named("barberoBean")
@SessionScoped
public class BarberoBean implements Serializable {

    private List<barbero> listaBarberos;
    private barbero nuevoBarbero = new barbero();
    private barbero barberoEditando;


    private boolean mostrarLista = false;

    // Getters y setters

    public barbero getNuevoBarbero() {
        return nuevoBarbero;
    }

    public void setNuevoBarbero(barbero nuevoBarbero) {
        this.nuevoBarbero = nuevoBarbero;
    }

    public barbero getBarberoEditando() {
        return barberoEditando;
    }

    public void setBarberoEditando(barbero barberoEditando) {
        this.barberoEditando = barberoEditando;
    }
    public List<barbero> getListaBarberos() {
        return listaBarberos;
    }

    public boolean isMostrarLista() {
        return mostrarLista;
    }

    public void setMostrarLista(boolean mostrarLista) {
        this.mostrarLista = mostrarLista;
    }

    // Acción para cargar la lista y mostrarla
    public void cargarBarberos() {
        barberoDAO dao = new barberoDAO();
        this.listaBarberos = dao.obtenerTodos();
    }
    public boolean isListaVacia() {
        return listaBarberos == null || listaBarberos.isEmpty();
    }
    // Agregar
    public void agregarBarbero() {
        barberoDAO dao = new barberoDAO();
        if (dao.registrarBarbero(nuevoBarbero)) {
            cargarBarberos(); // Recarga la lista
            nuevoBarbero = new barbero(); // Limpia el formulario
        } else {
            System.out.println("Error al agregar barbero o ya existe.");
        }
    }

    // Eliminar
    public void eliminarBarbero(int id) {
        barberoDAO dao = new barberoDAO();
        dao.eliminarBarbero(id);
        cargarBarberos();
    }

    // Preparar Edición (puedes luego crear un formulario para editar)
    public void prepararEdicion(barbero b) {
        this.barberoEditando = b;
    }
    public void actualizarBarbero() {
        barberoDAO dao = new barberoDAO();
        dao.actualizarBarbero(barberoEditando);  // Este método debes crearlo si aún no lo tienes
        barberoEditando = null;
        cargarBarberos(); // Recarga la lista
    }

    public void cancelarEdicion() {
        barberoEditando = null;
    }
    private barbero barberoLogueado;

    public barbero getBarberoLogueado() {
        return barberoLogueado;
    }

    public void setBarberoLogueado(barbero barberoLogueado) {
        this.barberoLogueado = barberoLogueado;
    }

}
