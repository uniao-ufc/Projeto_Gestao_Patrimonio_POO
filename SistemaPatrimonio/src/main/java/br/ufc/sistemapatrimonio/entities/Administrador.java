package br.ufc.sistemapatrimonio.entities;

import br.ufc.sistemapatrimonio.enums.TipoUsuario;

public class Administrador extends Usuario {
    public Administrador(String login, String senha, TipoUsuario tipo) {
        super(login, senha, tipo);
    }

}
