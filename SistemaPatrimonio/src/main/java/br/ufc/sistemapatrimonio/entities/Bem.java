package br.ufc.sistemapatrimonio.entities;

// Representa um bem dentro do sistema de patrimônio
public class Bem {
    private int id; // Identificador único do bem
    private String nome; // Nome do bem
    private TipoBem tipo; // Tipo do bem (por exemplo, equipamento, veículo, etc.)
    private Local local; // Local onde o bem está alocado
    private boolean Alocstatus; // Status de alocação do bem (true se alocado, false se disponível)

    // Construtor para inicializar todos os atributos do bem
    public Bem(int id, String nome, TipoBem tipo, Local local, boolean alocstatus) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.local = local;
        Alocstatus = alocstatus;
    }

    // Construtor para inicializar todos os atributos, exceto o local
    public Bem(int id, String nome, TipoBem tipo, boolean alocstatus) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        Alocstatus = alocstatus;
    }

    // Getter para o identificador do bem
    public int getId() {
        return id;
    }

    // Setter para o identificador do bem
    public void setId(int id) {
        this.id = id;
    }

    // Getter para o nome do bem
    public String getNome() {
        return nome;
    }

    // Setter para o nome do bem
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Getter para o tipo do bem
    public TipoBem getTipo() {
        return tipo;
    }

    // Setter para o tipo do bem
    public void setTipo(TipoBem tipo) {
        this.tipo = tipo;
    }

    // Getter para o local do bem
    public Local getLocal() {
        return local;
    }

    // Setter para o local do bem
    public void setLocal(Local local) {
        this.local = local;
    }

    // Getter para o status de alocação do bem
    public boolean isAlocstatus() {
        return Alocstatus;
    }

    // Setter para o status de alocação do bem
    public void setAlocstatus(boolean alocstatus) {
        Alocstatus = alocstatus;
    }
}
