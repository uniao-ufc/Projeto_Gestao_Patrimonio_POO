/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufc.sistemapatrimonio.model;
import br.ufc.sistemapatrimonio.controller.LoginViewController;
import br.ufc.sistemapatrimonio.entities.Usuario;
import java.util.ArrayList;
import java.util.List;

public class Model {
    private final ArrayList<Observer> observers = new ArrayList<Observer>();
    private static final List<Object> users = new ArrayList<>();
    private static final List<Object> patrimonios = new ArrayList<>();
    private static final List<Object> bens = new ArrayList<>();
    private Usuario usuarioAutenticado;

    public String ret(){
       return "aaaa";
    }

    public Usuario getUser(String login) {
        if (login != null) {
            return usuarioAutenticado;
        }
        return null;
    }

    public void setUser(Usuario user) {
        this.usuarioAutenticado = user;
        notifica();
    }

    public void autenticarUser(String username, String password) {
        // Implementar lógica de autenticação
        // Exemplo: definir requisitanteAutenticado ou administradorAutenticado
    }

    public void deslogarUser() {
        usuarioAutenticado = null;
        notifica();
    }

    public void notifica() {
        for (Observer o : observers) {
            o.update();
        }
    }

    public void attachObserver(Observer observer) {
        if (observer != null) {
            observers.add(observer);
        }
    }

    public void detachObserver(Observer observer) {
        if (observer != null) {
            observers.remove(observer);
        }
    }
}

