package br.ufc.sistemapatrimonio.model;

import br.ufc.sistemapatrimonio.entities.Administrador;
import br.ufc.sistemapatrimonio.entities.Requisitante;
import br.ufc.sistemapatrimonio.model.DataPersistence;
import br.ufc.sistemapatrimonio.model.TelaLoginCadastroModel;

public class TelaLoginCadastroController {
  /*  private final TelaLoginCadastroModel telaLoginCadastroModel;
    private final DataPersistence dataPersistence;

    public TelaLoginCadastroController(TelaLoginCadastroModel model, DataPersistence dataPersistence) {
        this.telaLoginCadastroModel = model;
        this.dataPersistence = dataPersistence;
    }

    // Método para fazer login de requisitante
    public Requisitante requisitanteLogin(String login, String senha) {
        Requisitante requisitante = telaLoginCadastroModel.autenticarRequisitante(login, senha);
        if (requisitante == null) {
            throw new RuntimeException("Login ou senha inválidos para requisitante.");
        }
        return requisitante;
    }

    // Método para fazer login de administrador
    public Administrador administradorLogin(String login, String senha) {
        Administrador administrador = telaLoginCadastroModel.autenticarAdministrador(login, senha);
        if (administrador == null) {
            throw new RuntimeException("Login ou senha inválidos para administrador.");
        }
        return administrador;
    }

    // Método para cadastrar um novo requisitante
    public void cadastrarNovoRequisitante(String login, String senha) {
        try {
            telaLoginCadastroModel.cadastrarRequisitante(login, senha);
            dataPersistence.salvarRequisitantes(telaLoginCadastroModel.getRequisitantesMap());
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao cadastrar requisitante: " + e.getMessage());
        }
    }

    // Método para cadastrar um novo administrador
    public void cadastrarNovoAdministrador(String login, String senha) {
        try {
            telaLoginCadastroModel.cadastrarAdministrador(login, senha);
            dataPersistence.salvarAdministradores(telaLoginCadastroModel.getAdministradoresMap());
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao cadastrar administrador: " + e.getMessage());
        }
    }*/
}
