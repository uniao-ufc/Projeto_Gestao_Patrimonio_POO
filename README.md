# Projeto_Gestao_Patrimonio_POO
Trabalho de Programação Orientada a Objetos
Abaixo terá o "esqueleto" da estruturação de nosso código, seguindo o modelo MVC.
___________________________________________________________________________________________________________________________________________________
MAIN

public static void main(String [] args){
	Model model = new Model;
	View view = new View;
	view.iniciarView(model);
}

___________________________________________________________________________________________________________________________________________________
TELA:

login[********] <id = "log">
senha[********] <id = "sen">
  [cadastrar]   <id = cad; ação = "cadastrar">


___________________________________________________________________________________________________________________________________________________
VIEW:
import model*

private login;
private senha;

iniciarView(model){...}
desenharTela(){...}

getLogin(){return #log}
getSenha(){return #sen}

___________________________________________________________________________________________________________________________________________________
CONTROLLER:
import model*

lidarComEvento(evento){
	se(evento == "cadastrar"){
		Usuário usuario = new Usuário();
		usuario.setLogin(view.getLogin());
		usuario.setSenha(view.getSenha());
		model.setUsuario(usuario);
		model.adiciarUsuario(usuario);
		break;
	}
}
___________________________________________________________________________________________________________________________________________________
---------------------------------------------------------------------------------------------------------------------------------------------------
{PACOTE MODEL}:
---------------------------------------------------------------------------------------------------------------------------------------------------
___________________________________________________________________________________________________________________________________________________
MODEL:
private listaUsuarios;
private usuarioAutenticado;

getUsuario(){...}
setUsuario(){...}
getUsuarios(){...}
getUsuarioAutenticado(){...}
excluirUsuario(usuario){...}
adicionarUsuario(usuario){...}
___________________________________________________________________________________________________________________________________________________

USUÁRIO:
private login;
private senha;

getLogin(){...}
getSenha(){...}
setLogin(){...}
setSenha(){...}
