package br.ufc.sistemapatrimonio.entities;

// Representa um patrimônio dentro do sistema de patrimônio
public class Patrimonio {
    private int id; // Identificador único do patrimônio
    private String nome; // Nome do patrimônio
    private TipoPatrimonio tipo; // Tipo do patrimônio (por exemplo, imóvel, veículo, etc.)
    private Local local; // Local onde o patrimônio está alocado
    private int numeroTombamento; // Número de tombamento do patrimônio
    private boolean Alocstatus; // Status de alocação do patrimônio (true se alocado, false se disponível)

    // Construtor para inicializar todos os atributos do patrimônio
    public Patrimonio(int id, String nome, TipoPatrimonio tipo, Local local, int numeroTombamento, boolean alocstatus) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.local = local;
        this.numeroTombamento = numeroTombamento;
        Alocstatus = alocstatus;
    }

    // Construtor para inicializar todos os atributos, exceto o local
    public Patrimonio(int id, String nome, TipoPatrimonio tipo, int numeroTombamento, boolean alocstatus) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.numeroTombamento = numeroTombamento;
        Alocstatus = alocstatus;
    }

    // Getter para o nome do patrimônio
    public String getNome() {
        return nome;
    }

    // Setter para o nome do patrimônio
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Getter para o identificador do patrimônio
    public int getId() {
        return id;
    }

    // Setter para o identificador do patrimônio
    public void setId(int id) {
        this.id = id;
    }

    // Getter para o tipo do patrimônio
    public TipoPatrimonio getTipo() {
        return tipo;
    }

    // Setter para o tipo do patrimônio
    public void setTipo(TipoPatrimonio tipo) {
        this.tipo = tipo;
    }

    // Getter para o local do patrimônio
    public Local getLocal() {
        return local;
    }

    // Setter para o local do patrimônio
    public void setLocal(Local local) {
        this.local = local;
    }

    // Getter para o número de tombamento do patrimônio
    public int getNumeroTombamento() {
        return numeroTombamento;
    }

    // Setter para o número de tombamento do patrimônio
    public void setNumeroTombamento(int numeroTombamento) {
        this.numeroTombamento = numeroTombamento;
    }

    // Getter para o status de alocação do patrimônio
    public boolean isAlocstatus() {
        return Alocstatus;
    }

    // Setter para o status de alocação do patrimônio
    public void setAlocstatus(boolean alocstatus) {
        Alocstatus = alocstatus;
    }
}
