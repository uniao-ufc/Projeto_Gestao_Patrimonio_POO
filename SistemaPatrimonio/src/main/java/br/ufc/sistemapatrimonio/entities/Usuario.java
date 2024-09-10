package br.ufc.sistemapatrimonio.entities;

import br.ufc.sistemapatrimonio.enums.TipoUsuario;

import java.util.ArrayList;
import java.util.List;

// representa um usuário no sistema
public class Usuario {
    private static int id = 0; // contador estático para gerar IDs únicos
    private final int idUser; // ID único do usuário
    private final List<Patrimonio> meusPatrimonios = new ArrayList<>(); // lista de patrimônios do usuário
    private final List<Bem> meusBens = new ArrayList<>(); // lista de bens do usuário
    private final List<RequisicaoDeManutencao> minhasManutencoes = new ArrayList<>(); // lista de requisições de manutenção do usuário
    private final List<RequisicaoDeReserva> minhasRequisicaoDeReservas = new ArrayList<>(); // lista de requisições de reserva do usuário
    private String username; // nome de usuário
    private String password; // senha do usuário
    private TipoUsuario tipoUsuario; // tipo do usuário (por exemplo, admin, comum)

    // construtor para inicializar um usuário com nome, senha e tipo
    public Usuario(String username, String password, TipoUsuario tipoUsuario) {
        this.username = username;
        this.password = password;
        this.tipoUsuario = tipoUsuario;
        this.idUser = id;
        id++;
    }

    // getter para o nome de usuário
    public String getUsername() {
        return username;
    }

    // setter para o nome de usuário
    public void setUsername(String username) {
        this.username = username;
    }

    // getter para a senha
    public String getPassword() {
        return password;
    }

    // setter para a senha
    public void setPassword(String password) {
        this.password = password;
    }

    // getter para o tipo de usuário
    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    // setter para o tipo de usuário
    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    // getter para o ID do usuário
    public int getId() {
        return idUser;
    }

    // getter para a lista de patrimônios do usuário
    public List<Patrimonio> getMeusPatrimonios() {
        return meusPatrimonios;
    }

    // getter para a lista de bens do usuário
    public List<Bem> getMeusBens() {
        return meusBens;
    }

    // getter para a lista de requisições de manutenção do usuário
    public List<RequisicaoDeManutencao> getMinhasManutencoes() {
        return minhasManutencoes;
    }

    // getter para a lista de requisições de reserva do usuário
    public List<RequisicaoDeReserva> getMinhasRequisicaoDeReservas() {
        return minhasRequisicaoDeReservas;
    }
}
