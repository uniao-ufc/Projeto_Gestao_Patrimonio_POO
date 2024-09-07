package br.ufc.sistemapatrimonio.entities;

import br.ufc.sistemapatrimonio.enums.TipoUsuario;

public class Requisitante extends Usuario {
    public Requisitante(String username, String password, TipoUsuario tipo) {
        super(username, password, tipo);
    }

}
