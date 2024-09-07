package br.ufc.sistemapatrimonio.model;

import br.ufc.sistemapatrimonio.entities.Administrador;
import br.ufc.sistemapatrimonio.entities.Requisitante;
import br.ufc.sistemapatrimonio.entities.Usuario;
import br.ufc.sistemapatrimonio.enums.TipoUsuario;
import java.util.HashMap;

public class TelaLoginCadastroModel {
    public HashMap<Integer, Usuario> autenticarUsuario(String login, String senha, TipoUsuario tipoUsuario) {
        for (Usuario usuario : Model.users) {
            if (usuario.getUsername().equals(login) && usuario.getPassword().equals(senha) && usuario.getTipoUsuario() == tipoUsuario) {
                HashMap<Integer, Usuario> user = new HashMap<Integer, Usuario>();
                user.put(1, usuario);
                return user;
            }
        }
        HashMap<Integer, Usuario> error = new HashMap<Integer, Usuario>();
        error.put(2, null);
        return error;
    }

    public int cadastrarUsuario(String login, String senha, TipoUsuario tipoUsuario) {
        for (Usuario usuario : Model.users) {
            if (usuario.getUsername().equals(login) || usuario.getUsername().equals("") || usuario.getPassword().equals("")) {
                return 1;
            }
        }
        Usuario novoUsuario;
        if (tipoUsuario == TipoUsuario.REQUISITANTE) {
            novoUsuario = new Requisitante(login, senha, TipoUsuario.REQUISITANTE);
        } else if (tipoUsuario == TipoUsuario.ADMINISTRADOR) {
            novoUsuario = new Administrador(login, senha, TipoUsuario.ADMINISTRADOR);
        } else {
            return 2;
        }

        novoUsuario.setTipoUsuario(tipoUsuario);
        Model.users.add(novoUsuario);
        return 3;
    }

}