package br.ufc.sistemapatrimonio.model;
import java.util.HashMap;

public class TelaLoginCadastroModel {
    private final HashMap<String, Requisitante> requisitantesMap = new HashMap<>();
    private final HashMap<String, Administrador> administradoresMap = new HashMap<>();
    
    // Método para autenticar um aluno
    public Requisitante autenticarRequisitante(String login, String senha) {
        Requisitante requisitante = requisitantesMap.get(login);
        if (requisitante != null && requisitante.getPassword().equals(senha)) {
            return requisitante;
        }
        return null;
    }

    // Método para autenticar um administrador
    public Administrador autenticarAdministrador(String login, String senha) {
        Administrador administrador = administradoresMap.get(login);
        if (administrador != null && administrador.getPassword().equals(senha)) {
            return administrador;
        }
        return null;
    }

    // Método para cadastrar um novo aluno
    public void cadastrarRequisitante(String login, String senha) {
        if (!requisitantesMap.containsKey(login)) {
            Requisitante novoRequisitante = new Requisitante(login, senha);
            requisitantesMap.put(login, novoRequisitante);
        } else {
            throw new RuntimeException("Requisitante já cadastrado.");
        }
    }

    // Método para cadastrar um novo administrador
    public void cadastrarAdministrador(String login, String senha) {
        if (!administradoresMap.containsKey(login)) {
            Administrador novoAdministrador = new Administrador(login, senha);
            administradoresMap.put(login, novoAdministrador);
        } else {
            throw new RuntimeException("Administrador já cadastrado.");
        }
    }
}
