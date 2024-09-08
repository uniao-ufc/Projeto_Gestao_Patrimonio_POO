package br.ufc.sistemapatrimonio.model;

import br.ufc.sistemapatrimonio.entities.Administrador;
import br.ufc.sistemapatrimonio.entities.Requisitante;
import br.ufc.sistemapatrimonio.entities.Usuario;
import br.ufc.sistemapatrimonio.enums.TipoUsuario;
import br.ufc.sistemapatrimonio.exceptions.UsuarioException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class TelaLoginCadastroModel {

    public Usuario usuarioEncontrado(String login, String senha){
        // Encontre o tipo do usuário baseado no login e senha
        List<Usuario> usuarios = Model.getUsers();
        Usuario usuarioEncontrado = null;

        for (Usuario usuario : usuarios) {
            if (usuario.getUsername().equals(login) && usuario.getPassword().equals(senha)) {
                usuarioEncontrado = usuario;
                break;
            }
        }
        return usuarioEncontrado;
    }

    public HashMap<Integer, Usuario> autenticarUsuario(String login, String senha, TipoUsuario tipoUsuario) throws UsuarioException {
        List<Usuario> usuarios = Model.getUsers();

        boolean usuarioSelecionado = false;

        // Verificar se o usuario existe no sistema e se já está alocado
        for (Usuario usuariosSistema : usuarios) {
            if (Objects.equals(usuariosSistema.getUsername(), login)) {
                usuarioSelecionado = true;
                break;
            }
        }

        // Caso o usuario não seja encontrado no sistema
        if (!usuarioSelecionado) {
            throw new UsuarioException(UsuarioException.ERRO, "Usuario ou senha incorretos.");
        }

        for (Usuario usuario : usuarios) {
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

    public void cadastrarUsuario(String login, String senha, TipoUsuario tipoUsuario) throws IOException, UsuarioException {
        List<Usuario> usuarios = Model.getUsers();

        for (Usuario usuariosSistema : usuarios) {
            if (Objects.equals(usuariosSistema.getUsername(), login)) {
                System.out.println(usuariosSistema.getUsername() + login);
                throw new UsuarioException(UsuarioException.EXISTENTE, "O usuario já existe");
            }
        }
        Usuario novoUsuario;
        if (tipoUsuario == TipoUsuario.ADMINISTRADOR) {
            novoUsuario = new Administrador(login, senha, TipoUsuario.ADMINISTRADOR);
            usuarios.add(novoUsuario);
        } else if (tipoUsuario == TipoUsuario.REQUISITANTE) {
            novoUsuario = new Requisitante(login, senha, TipoUsuario.REQUISITANTE);
            usuarios.add(novoUsuario);
        } else {
            throw new IOException("Algum erro ocorreu");
        }
    }
}