package br.ufc.sistemapatrimonio.entities;

import br.ufc.sistemapatrimonio.enums.TipoReserva;

// Representa uma solicitação de manutenção dentro do sistema de patrimônio
public class RequisicaoDeManutencao {
    private TipoReserva tipo; // Tipo de reserva associado à solicitação de manutenção
    private int id; // Identificador único da solicitação de manutenção
    private String nome; // Nome da solicitação de manutenção
    private String descricao; // Descrição etalhada da solicitação de manutenção
    private Boolean status = false; // Status da solicitação (false por padrão, indicando que a solicitação ainda não foi processada)
    private String usuarioCriador; // Usuário que criou a Requisição de manutenção

    // Construtor para inicializar todos os atributos da solicitação de manutenção
    public RequisicaoDeManutencao(int id, String nome, String descricao, TipoReserva tipo, boolean status, String usuarioCriador) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.tipo = tipo;
        this.status = status;
        this.usuarioCriador = usuarioCriador;
    }

    // Getter para o identificador da solicitação
    public int getId() {
        return id;
    }

    // Setter para o identificador da solicitação
    public void setId(int id) {
        this.id = id;
    }

    // Getter para o nome da solicitação
    public String getNome() {
        return nome;
    }

    // Setter para o nome da solicitação
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Getter para a descrição da solicitação
    public String getDescricao() {
        return descricao;
    }

    // Setter para a descrição da solicitação
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    // Getter para o tipo de reserva associado à solicitação
    public TipoReserva getTipo() {
        return tipo;
    }

    // Setter para o tipo de reserva associado à solicitação
    public void setTipo(TipoReserva tipo) {
        this.tipo = tipo;
    }

    // Getter para o status da solicitação
    public Boolean getStatus() {
        return status;
    }

    // Setter para o status da solicitação
    public void setStatus(Boolean status) {
        this.status = status;
    }

    // Getter para o usuário que criou a solicitação
    public String getUsuarioCriador() {
        return usuarioCriador;
    }

    // Setter para o usuário que criou a solicitação
    public void setUsuarioCriador(String usuarioCriador) {
        this.usuarioCriador = usuarioCriador;
    }
}
