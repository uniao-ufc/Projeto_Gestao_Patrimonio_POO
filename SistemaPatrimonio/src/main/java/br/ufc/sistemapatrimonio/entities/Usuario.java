package br.ufc.sistemapatrimonio.entities;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private static int id = 0;
    private final int idUser;
    private String username;
    private String password;
    private TipoUsuario tipoUsuario;
    private final List<Patrimonio> meusPatrimonios = new ArrayList<>();
    private final List<Bem> meusBens = new ArrayList<>();
    private final List<RequisicaoDeManutencao> minhasManutencoes = new ArrayList<>();

    public Usuario(String username, String password, TipoUsuario tipoUsuario) {
        this.username = username;
        this.password = password;
        this.tipoUsuario = tipoUsuario;
        this.idUser = id;
        id++;
    }

    // Getter para username
    public String getUsername() {
        return username;
    }

    // Setter para username
    public void setUsername(String username) {
        this.username = username;
    }

    // Getter para password
    public String getPassword() {
        return password;
    }

    // Setter para password
    public void setPassword(String password) {
        this.password = password;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public int getId() {
        return idUser;
    }

    public List<Patrimonio> getMeusPatrimonios() {
        return meusPatrimonios;
    }

    public List<Bem> getMeusBens() {
        return meusBens;
    }

    public List<RequisicaoDeManutencao> getMinhasManutencoes() {
        return minhasManutencoes;
    }

    public boolean adicionarBem(Bem bem) {
        // Verifica se o bem existe e se está disponível
        if (bem != null && !bem.isAlocstatus()) {
            // Faz uma cópia do bem
            Bem bemCopia = new Bem();
            bemCopia.setId(bem.getId());
            bemCopia.setNome(bem.getNome());
            bemCopia.setTipo(bem.getTipo());
            bemCopia.setAlocstatus(true);

            // Adiciona à lista do usuário
            meusBens.add(bemCopia);

            // Atualiza o status do bem original para alocado
            bem.setAlocstatus(true);

            return true; // Sucesso
        }
        return false; // Falha
    }

    public boolean adicionarPatrimonio(Patrimonio patrimonio) {
        // Verifica se o patrimônio existe e se está disponível
        if (patrimonio != null && !patrimonio.isAlocstatus()) {
            // Faz uma cópia do patrimônio
            Patrimonio patrimonioCopia = new Patrimonio();
            patrimonioCopia.setId(patrimonio.getId());
            patrimonioCopia.setNome(patrimonio.getNome());
            patrimonioCopia.setTipo(patrimonio.getTipo());
            patrimonioCopia.setNumeroTombamento(patrimonio.getNumeroTombamento());
            patrimonioCopia.setAlocstatus(true);

            // Adiciona à lista do usuário
            meusPatrimonios.add(patrimonioCopia);

            // Atualiza o status do patrimônio original para alocado
            patrimonio.setAlocstatus(true);

            return true; // Sucesso
        }
        return false; // Falha
    }
}
