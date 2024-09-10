package br.ufc.sistemapatrimonio.entities;

import br.ufc.sistemapatrimonio.enums.TipoReserva;

// Representa uma solicitação de reserva dentro do sistema de patrimônio
public class RequisicaoDeReserva {
    private static int id = 0; // Contador estático para gerar IDs únicos
    private final int idReserva; // Identificador único da reserva
    private final int idReqReserva; // Identificador único da requisição (definido pelo contador estático)
    private TipoReserva tipoReserva; // Tipo de reserva
    private String nome; // Nome da reserva
    private Local local; // Local associado à reserva
    private String descricao; // Descrição da reserva
    private String usuarioCriador; // Usuário que criou a reserva

    // Construtor para inicializar uma nova solicitação de reserva
    public RequisicaoDeReserva(int idReserva, String nome, Local local, String descricao, TipoReserva tipoReserva, String usuarioCriador) {
        this.idReserva = idReserva;
        this.nome = nome;
        this.local = local;
        this.descricao = descricao;
        this.tipoReserva = tipoReserva;
        this.idReqReserva = id; // Atribui o ID atual e incrementa o contador
        this.usuarioCriador = usuarioCriador;
        id++; // Incrementa o contador estático para o próximo ID
    }

    // Getter para o ID atual do contador estático
    public static int getIdR() {
        return id;
    }

    // Getter para o identificador da reserva
    public int getId() {
        return idReserva;
    }

    // Getter para o nome da reserva
    public String getNome() {
        return nome;
    }

    // Setter para o nome da reserva
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Getter para o local da reserva
    public Local getLocal() {
        return local;
    }

    // Setter para o local da reserva
    public void setLocal(Local local) {
        this.local = local;
    }

    // Getter para a descrição da reserva
    public String getDescricao() {
        return descricao;
    }

    // Setter para a descrição da reserva
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    // Getter para o identificador da requisição
    public int getIdReqReserva() {
        return idReqReserva;
    }

    // Getter para o tipo de reserva
    public TipoReserva getTipoReserva() {
        return tipoReserva;
    }

    // Setter para o tipo de reserva
    public void setTipoReserva(TipoReserva tipoReserva) {
        this.tipoReserva = tipoReserva;
    }

    // Getter para o usuário que criou a reserva
    public String getUsuarioCriador() {
        return usuarioCriador;
    }

    // Setter para o usuário que criou a reserva
    public void setUsuarioCriador(String usuarioCriador) {
        this.usuarioCriador = usuarioCriador;
    }
}
