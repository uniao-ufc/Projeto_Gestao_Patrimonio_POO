import java.util.ArrayList;

public class ProjectManagementSystem {
    private ArrayList<Bem> bens = new ArrayList<>();
    private ArrayList<Patrimonio> patrimonios = new ArrayList<>();
    private ArrayList<Usuario> usuarios = new ArrayList<>();
    private ArrayList<RequisicaoDeManutencao> manutencoes = new ArrayList<>();


    public void adicinarBens(Bem bem){
        bens.add(bem);
    }

    public void atualizarBens(Bem bem){
        //Logica para atualizar os bens
    }
    public void rastrearBens(Bem bem){
        //Logica para rastrear os bens
    }

    public void reservarPatrimonio(Patrimonio patrimonio){
        patrimonios.add(patrimonio);
    }

    public void requisitarManutencao(RequisicaoDeManutencao requisicao){
        manutencoes.add(requisicao);
    }

    public void registrarUsuario(Usuario usuario){
        usuarios.add(usuario);
    }

    public void autenticarUsuario(Usuario usuario){
        //Logica para autenticar usuario
    }

    public void controleAcesso(Usuario usuario){
        //Logica para controlar acesso baseado no nivel
    }
}
