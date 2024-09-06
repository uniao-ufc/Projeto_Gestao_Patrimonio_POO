/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufc.sistemapatrimonio.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public class Model {
    private final List<Observer> observers = new ArrayList<>();
    private Requisitante requisitanteAutenticado;
    private Administrador administradorAutenticado;

    // Método para notificar todos os observadores
    public void notifica() {
        for (Observer observer : observers) {
            observer.update();  // Assumindo que Observer tem um método update()
        }
    }

    // Métodos para acessar e definir administrador
    public Administrador getAdministrador() {
        return administradorAutenticado;
    }

    public void setAdministrador(Administrador admin) {
        this.administradorAutenticado = admin;
        notifica(); // Notifica os observadores sobre a mudança
    }

    // Métodos para acessar e definir requisitante
    public Requisitante getRequisitante() {
        return requisitanteAutenticado;
    }

    public void setRequisitante(Requisitante requisitante) {
        this.requisitanteAutenticado = requisitante;
        notifica(); // Notifica os observadores sobre a mudança
    }

    // Método para autenticar um usuário
    public void autenticarUsuario(String username, String password) {
        // Implementar lógica de autenticação
        // Exemplo: definir requisitanteAutenticado ou administradorAutenticado
    }

    // Método para deslogar um usuário
    public void deslogarUsuario() {
        requisitanteAutenticado = null;
        administradorAutenticado = null;
        notifica(); // Notifica os observadores sobre a mudança
    }

    // Métodos para gerenciar observadores
    public void attachObserver(Observer observer) {
        observers.add(observer);
    }

    public void detachObserver(Observer observer) {
        observers.remove(observer);
    }
}

