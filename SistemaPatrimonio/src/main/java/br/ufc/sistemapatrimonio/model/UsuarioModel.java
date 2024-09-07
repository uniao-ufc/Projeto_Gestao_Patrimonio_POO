package br.ufc.sistemapatrimonio.model;

import br.ufc.sistemapatrimonio.entities.Bem;
import br.ufc.sistemapatrimonio.entities.Patrimonio;
import br.ufc.sistemapatrimonio.entities.Usuario;

public class UsuarioModel {

    private Usuario usuario;

    public UsuarioModel(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean adicionarBem(Bem bem) {
        // Verifica se o bem existe e se está disponível
        if (bem != null && !bem.isAlocstatus()) {
            // Faz uma cópia do bem
            Bem bemCopia = new Bem(bem.getId(), bem.getNome(), bem.getTipo(), true);

            // Adiciona à lista do usuário
            usuario.getMeusBens().add(bemCopia);

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
            Patrimonio patrimonioCopia = new Patrimonio(patrimonio.getId(), patrimonio.getNome(), patrimonio.getTipo(),patrimonio.getNumeroTombamento(), true);

            // Adiciona à lista do usuário
            usuario.getMeusPatrimonios().add(patrimonioCopia);

            // Atualiza o status do patrimônio original para alocado
            patrimonio.setAlocstatus(true);

            return true; // Sucesso
        }
        return false; // Falha
    }
}
