package br.ufc.sistemapatrimonio.model;

import br.ufc.sistemapatrimonio.entities.Usuario;
import java.util.ArrayList;
import java.util.List;

public class Model {
    private final ArrayList<Observer> observers = new ArrayList<>();
    public static List<Usuario> users = new ArrayList<>();
    public static List<Object> patrimonios = new ArrayList<>();
    public static List<Object> bens = new ArrayList<>();
    private Usuario usuarioAutenticado;
    public TelaLoginCadastroModel cadastroModel = new TelaLoginCadastroModel();

    public Usuario getUsuarioAutenticado() {
        return usuarioAutenticado;
    }

    public void setUsuarioAutenticado(Usuario usuarioAutenticado) {
        this.usuarioAutenticado = usuarioAutenticado;
        notifica();
    }

    public void autenticarUsuario(String username, String password) {
        for (Usuario user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                setUsuarioAutenticado(user);
                return;
            }
        }
        throw new RuntimeException("Usuário não encontrado ou senha incorreta.");
    }

    public void deslogarUsuario() {
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