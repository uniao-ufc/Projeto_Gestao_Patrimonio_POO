import java.util.ArrayList;

public class Model {
    private ArrayList<Bem> bens = new ArrayList<>();
    private ArrayList<Patrimonio> patrimonios = new ArrayList<>();
    private ArrayList<Usuario> usuarios = new ArrayList<>();
    private ArrayList<RequisicaoDeManutencao> manutencoes = new ArrayList<>();

    // Métodos para adicionar, atualizar e acessar os dados
    public void adicionarBem(Bem bem) {
        bens.add(bem);
    }

    public void atualizarBem(Bem bem) {
        // Lógica para atualizar um bem
    }

    public Bem buscarBem(String id) {
        for (Bem bem : bens) {
            if (bem.getId().equals(id)) {
                return bem;
            }
        }
        return null;
    }

    public void reservarPatrimonio(Patrimonio patrimonio) {
        patrimonios.add(patrimonio);
    }

    public void requisitarManutencao(RequisicaoDeManutencao requisicao) {
        manutencoes.add(requisicao);
    }

    public void registrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public Usuario autenticarUsuario(String id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId().equals(id)) {
                return usuario;
            }
        }
        return null;
    }

    public void controleAcesso(Usuario usuario) {
        // Lógica para controlar acesso baseado no nível
    }

    // Métodos de acesso aos dados
    public ArrayList<Bem> getBens() {
        return bens;
    }

    public ArrayList<Patrimonio> getPatrimonios() {
        return patrimonios;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public ArrayList<RequisicaoDeManutencao> getManutencoes() {
        return manutencoes;
    }
}
