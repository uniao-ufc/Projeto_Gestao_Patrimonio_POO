package br.ufc.sistemapatrimonio.model;

import br.ufc.sistemapatrimonio.entities.Administrador;
import br.ufc.sistemapatrimonio.entities.Requisitante;
import br.ufc.sistemapatrimonio.entities.Usuario;
import br.ufc.sistemapatrimonio.enums.TipoUsuario;

import java.util.List;

public class TelaLoginCadastroModel {
    public Usuario autenticarUsuario(String login, String senha, TipoUsuario tipoUsuario) {
        for (Usuario usuario : Model.users) {
            if (usuario.getUsername().equals(login) && usuario.getPassword().equals(senha) && usuario.getTipoUsuario() == tipoUsuario) {
                return usuario;
            }
        }
        return null;
    }

    public void cadastrarUsuario(String login, String senha, TipoUsuario tipoUsuario) {
        for (Usuario usuario : Model.users) {
            if (usuario.getUsername().equals(login)) {
                throw new RuntimeException("Usuário já cadastrado.");
            }
        }
        Usuario novoUsuario;
        if (tipoUsuario == TipoUsuario.REQUISITANTE) {
            novoUsuario = new Requisitante(login, senha, TipoUsuario.REQUISITANTE);
        } else if (tipoUsuario == TipoUsuario.ADMINISTRADOR) {
            novoUsuario = new Administrador(login, senha, TipoUsuario.ADMINISTRADOR);
        } else {
            throw new RuntimeException("Tipo de usuário inválido.");
        }

        novoUsuario.setTipoUsuario(tipoUsuario);
        Model.users.add(novoUsuario);
    }
}