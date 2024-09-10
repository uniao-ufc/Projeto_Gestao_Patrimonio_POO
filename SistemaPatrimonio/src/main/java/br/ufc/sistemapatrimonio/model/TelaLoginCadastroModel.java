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

    // Método para encontrar um usuário baseado no login e senha
    public Usuario usuarioEncontrado(String login, String senha) {
        // Obtém a lista de usuários do sistema
        List<Usuario> usuarios = Model.getUsers();
        Usuario usuarioEncontrado = null;

        // Itera sobre a lista de usuários para encontrar um que corresponda ao login e senha fornecidos
        for (Usuario usuario : usuarios) {
            if (usuario.getUsername().equals(login) && usuario.getPassword().equals(senha)) {
                usuarioEncontrado = usuario; // Define o usuário encontrado
                break; // Sai do loop após encontrar o usuário
            }
        }
        return usuarioEncontrado; // Retorna o usuário encontrado ou null se não encontrado
    }

    // Método para autenticar um usuário e verificar seu tipo
    public HashMap<Integer, Usuario> autenticarUsuario(String login, String senha, TipoUsuario tipoUsuario) throws UsuarioException {
        // Obtem a lista de usuários do sistema
        List<Usuario> usuarios = Model.getUsers();

        boolean usuarioSelecionado = false;

        // Verifica se o usuário existe no sistema
        for (Usuario usuariosSistema : usuarios) {
            if (Objects.equals(usuariosSistema.getUsername(), login)) {
                usuarioSelecionado = true; // Usuário encontrado
                break; // Sai do loop após encontrar o usuário
            }
        }

        // Caso o usuário não seja encontrado no sistema, lança uma exceção
        if (!usuarioSelecionado) {
            throw new UsuarioException(UsuarioException.ERRO, "Usuario ou senha incorretos.");
        }

        // Verifica se o login e a senha correspondem e se o tipo do usuário está correto
        for (Usuario usuario : usuarios) {
            if (usuario.getUsername().equals(login) && usuario.getPassword().equals(senha) && usuario.getTipoUsuario() == tipoUsuario) {
                // Cria um mapa para retornar o usuário autenticado
                HashMap<Integer, Usuario> user = new HashMap<Integer, Usuario>();
                user.put(1, usuario);
                return user;
            }
        }

        // Cria um mapa para retornar um erro se a autenticação falhar
        HashMap<Integer, Usuario> error = new HashMap<Integer, Usuario>();
        error.put(2, null);
        return error;
    }

    // Metodo para cadastrar um novo usuário
    public void cadastrarUsuario(String login, String senha, TipoUsuario tipoUsuario) throws IOException, UsuarioException {
        // Obtém a lista de usuários do sistema
        List<Usuario> usuarios = Model.getUsers();

        // Verifica se já existe um usuário com o mesmo login
        for (Usuario usuariosSistema : usuarios) {
            if (Objects.equals(usuariosSistema.getUsername(), login)) {
                System.out.println(usuariosSistema.getUsername() + login);
                throw new UsuarioException(UsuarioException.EXISTENTE, "O usuario já existe"); // Lança uma exceção se o usuário já existir
            }
        }

        // cria um novo usuário baseado no tipo fornecido e o adiciona à lista de usuários
        Usuario novoUsuario;
        if (tipoUsuario == TipoUsuario.ADMINISTRADOR) {
            novoUsuario = new Administrador(login, senha, TipoUsuario.ADMINISTRADOR);
            usuarios.add(novoUsuario); // Adiciona o novo administrador à lista
        } else if (tipoUsuario == TipoUsuario.REQUISITANTE) {
            novoUsuario = new Requisitante(login, senha, TipoUsuario.REQUISITANTE);
            usuarios.add(novoUsuario); // Adiciona o novo requisitante à lista
        } else {
            throw new IOException("Algum erro ocorreu"); // Lança uma exceção se o tipo de usuário for inválido
        }
    }
}
