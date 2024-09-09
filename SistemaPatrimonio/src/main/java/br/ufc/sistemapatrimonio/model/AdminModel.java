package br.ufc.sistemapatrimonio.model;

import br.ufc.sistemapatrimonio.entities.*;
import br.ufc.sistemapatrimonio.enums.TipoReserva;
import br.ufc.sistemapatrimonio.exceptions.BemException;
import br.ufc.sistemapatrimonio.exceptions.PatrimonioException;

import java.io.IOException;
import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;

public class AdminModel extends UsuarioModel {
    Model model = new Model();

    public void adicionarPatrimonio(int id, String nome, String descricao, double depreciacao, String tipo, int numeroTombamento) throws PatrimonioException {
        // Verificar se já existe um patrimônio com o mesmo id
        for (Patrimonio patrimonio : Model.getPatrimonios()) {
            if (patrimonio.getId() == id) {
                throw new PatrimonioException(PatrimonioException.EXISTENTE, "O patrimônio com o ID " + id + " já existe.");
            }
        }
        // Criar um novo TipoPatrimonio
        TipoPatrimonio tipoPatrimonio = new TipoPatrimonio(tipo, descricao, depreciacao);

        // Criar um novo Patrimonio
        Patrimonio novoPatrimonio = new Patrimonio(id, nome, tipoPatrimonio, numeroTombamento, false);

        // Adicionar o novo patrimônio à lista
        Model.getPatrimonios().add(novoPatrimonio);
    }

    public void editarPatrimonio(int id, String nome, String descricao, double depreciacao, String tipo, int numeroTombamento) throws PatrimonioException {
        Optional<Patrimonio> patrimonioExistente = Model.getPatrimonios().stream().filter(patrimonio -> patrimonio.getId() == id).findFirst();
        if (patrimonioExistente.isPresent()) {
            Patrimonio patrimonio = patrimonioExistente.get();
            patrimonio.setNome(nome);
            patrimonio.getTipo().setNome(tipo);
            patrimonio.getTipo().setDescricao(descricao);
            patrimonio.getTipo().setDepreciacaoAnual(depreciacao);
            patrimonio.setNumeroTombamento(numeroTombamento);
        } else {
            throw new PatrimonioException(PatrimonioException.NAO_ENCONTRADO, "Patrimônio com o ID " + id + " não encontrado.");
        }
    }

    public void removerPatrimonio(int id) throws PatrimonioException {
        boolean encontrado = false;

        // Usando um iterator para garantir que a remoção seja segura e eficiente
        Iterator<Patrimonio> iteratorPatrimonios = Model.getPatrimonios().iterator();
        while (iteratorPatrimonios.hasNext()) {
            Patrimonio patrimonio = iteratorPatrimonios.next();
            if (patrimonio.getId() == id) {

                // 1. Remover o patrimônio da lista do sistema
                iteratorPatrimonios.remove(); // Remove o patrimônio da lista do sistema
                encontrado = true;

                // 2. Remover as requisições de manutenção e reserva associadas ao patrimônio do usuário correto
                for (Usuario usuario : Model.getUsers()) {
                    // Remover requisições de manutenção associadas ao patrimônio deste usuário
                    Iterator<RequisicaoDeManutencao> iteratorManutencoesUsuario = usuario.getMinhasManutencoes().iterator();
                    while (iteratorManutencoesUsuario.hasNext()) {
                        RequisicaoDeManutencao manutencao = iteratorManutencoesUsuario.next();
                        if (manutencao.getId() == id && manutencao.getTipo() == TipoReserva.PATRIMONIO) {
                            iteratorManutencoesUsuario.remove(); // Remove a manutenção associada ao patrimônio do usuário
                            break; // Remove e sai do loop
                        }
                    }
                }
                for (Usuario usuario : Model.getUsers()) {
                    // Remover requisições de reserva associadas ao patrimônio deste usuário
                    Iterator<RequisicaoDeReserva> iteratorReservasUsuario = usuario.getMinhasRequisicaoDeReservas().iterator();
                    while (iteratorReservasUsuario.hasNext()) {
                        RequisicaoDeReserva reserva = iteratorReservasUsuario.next();
                        if (reserva.getId() == id && reserva.getTipoReserva() == TipoReserva.PATRIMONIO) {
                            iteratorReservasUsuario.remove(); // Remove a reserva associada ao patrimônio do usuário
                            break; // Remove e sai do loop
                        }
                    }
                }
            }
        }

        if (!encontrado) {
            throw new PatrimonioException(PatrimonioException.NAO_ENCONTRADO, "Patrimônio com o ID " + id + " não encontrado.");
        }
    }

    public String listarPatrimonios() {
        StringBuilder lista = new StringBuilder();
        for (Patrimonio patrimonio : Model.getPatrimonios()) {
            // Adiciona todas as informações relevantes do patrimônio
            lista.append("ID: ").append(patrimonio.getId())
                    .append(", Nome: ").append(patrimonio.getNome())
                    .append(", Tipo: ").append(patrimonio.getTipo().getNome())
                    .append(", Descrição do Tipo: ").append(patrimonio.getTipo().getDescricao())
                    .append(", Depreciação Anual: ").append(String.format("%.2f", patrimonio.getTipo().getDepreciacaoAnual()))
                    .append("%") // Adiciona o símbolo de porcentagem
                    .append(", Número de Tombamento: ").append(patrimonio.getNumeroTombamento())
                    .append(", Status: ").append(patrimonio.isAlocstatus() ? "Alocado" : "Não alocado")
                    .append("\n");
        }
        return lista.toString();
    }

    public void adicionarBem(int id, String nome, String descricao, double depreciacao, String tipo) throws BemException {
        // Verificar se já existe um bem com o mesmo id
        for (Bem bem : Model.getBens()) {
            if (bem.getId() == id) {
                throw new BemException(BemException.EXISTENTE, "O bem com o ID " + id + " já existe.");
            }
        }
        // Criar um novo TipoBem
        TipoBem tipoBem = new TipoBem(tipo, descricao, depreciacao);

        // Criar um novo Bem
        Bem novoBem = new Bem(id, nome, tipoBem, false);

        // Adicionar o novo bem à lista
        Model.getBens().add(novoBem);
    }

    public void editarBem(int id, String nome, String descricao, double depreciacao, String tipo) throws BemException {
        boolean encontrado = false;

        for (Bem bem : Model.getBens()) {
            if (bem.getId() == id) {
                encontrado = true;
                bem.setNome(nome);
                bem.getTipo().setNome(tipo);
                bem.getTipo().setDescricao(descricao);
                bem.getTipo().setDepreciacaoAnual(depreciacao);
                break;
            }
        }

        if (!encontrado) {
            throw new BemException(BemException.NAO_ENCONTRADO, "Bem com o ID " + id + " não encontrado.");
        }
    }

    public void removerBem(int id) throws BemException {
        boolean encontrado = false;

        // Usando um iterator para garantir que a remoção seja segura e eficiente
        Iterator<Bem> iteratorBens = Model.getBens().iterator();
        while (iteratorBens.hasNext()) {
            Bem bem = iteratorBens.next();
            if (bem.getId() == id) {

                // 1. Remover o patrimônio da lista do sistema
                iteratorBens.remove(); // Remove o patrimônio da lista do sistema
                encontrado = true;

                // 2. Remover as requisições de manutenção e reserva associadas ao patrimônio do usuário correto
                for (Usuario usuario : Model.getUsers()) {

                    // Remover requisições de manutenção associadas ao patrimônio deste usuário
                    Iterator<RequisicaoDeManutencao> iteratorManutencoesUsuario = usuario.getMinhasManutencoes().iterator();
                    while (iteratorManutencoesUsuario.hasNext()) {
                        RequisicaoDeManutencao manutencao = iteratorManutencoesUsuario.next();
                        if (manutencao.getId() == id && manutencao.getTipo() == TipoReserva.BEM) {
                            iteratorManutencoesUsuario.remove(); // Remove a manutenção associada ao patrimônio do usuário
                            break; // Remove e sai do loop
                        }
                    }
                }
                for (Usuario usuario : Model.getUsers()) {

                    // Remover requisições de reserva associadas ao patrimônio deste usuário
                    Iterator<RequisicaoDeReserva> iteratorReservasUsuario = usuario.getMinhasRequisicaoDeReservas().iterator();
                    while (iteratorReservasUsuario.hasNext()) {
                        RequisicaoDeReserva reserva = iteratorReservasUsuario.next();
                        if (reserva.getId() == id && reserva.getTipoReserva() == TipoReserva.BEM) {
                            iteratorReservasUsuario.remove(); // Remove a reserva associada ao patrimônio do usuário
                            break; // Remove e sai do loop
                        }
                    }
                }
            }
        }

        if (!encontrado) {
            throw new BemException(BemException.NAO_ENCONTRADO, "Bem com o ID " + id + " não encontrado.");
        }
    }

    public String listarBens() {
        StringBuilder lista = new StringBuilder();
        for (Bem bem : Model.getBens()) {
            // Adiciona todas as informações relevantes do bem
            lista.append("ID: ").append(bem.getId())
                    .append(", Nome: ").append(bem.getNome())
                    .append(", Tipo: ").append(bem.getTipo().getNome())
                    .append(", Descrição do Tipo: ").append(bem.getTipo().getDescricao())
                    .append(", Depreciação Anual: ").append(String.format("%.2f", bem.getTipo().getDepreciacaoAnual()))
                    .append("%") // Adiciona o símbolo de porcentagem
                    .append(", Status: ").append(bem.isAlocstatus() ? "Alocado" : "Não alocado")
                    .append("\n");
        }
        return lista.toString();
    }

    public String listarReservas() {
        StringBuilder lista = new StringBuilder();
        for (RequisicaoDeReserva requisicaoDeReserva : model.getrequisicaoDeReservas()) {
            // Adiciona todas as informações relevantes das reservas
            lista.append("ID: ").append(requisicaoDeReserva.getId())
                    .append(", Nome: ").append(requisicaoDeReserva.getNome())
                    .append(", Local: ").append(requisicaoDeReserva.getLocal().getEndereco())
                    .append(", Descrição: ").append(requisicaoDeReserva.getDescricao())
                    .append(", Tipo: ").append(requisicaoDeReserva.getTipoReserva().toString())
                    .append("\n");
        }
        return lista.toString();
    }

    public String listarManutencoes() {
        StringBuilder lista = new StringBuilder();
        for (RequisicaoDeManutencao requisicaoDeManutencao : model.getRequisicaoDeManutencao()) {
            // Adiciona todas as informações relevantes das reservas
            lista.append("ID: ").append(requisicaoDeManutencao.getId())
                    .append(", Nome: ").append(requisicaoDeManutencao.getNome())
                    .append(", Descrição: ").append(requisicaoDeManutencao.getDescricao())
                    .append(", Tipo: ").append(requisicaoDeManutencao.getTipo().toString())
                    .append("\n");
        }
        return lista.toString();
    }

    public void removerHistorico(int id, String tipoRM, TipoReserva tipoBP) throws BemException, IOException {
        boolean encontrado = false;
        if (Objects.equals(tipoRM, "reserva")) {
            if (tipoBP == TipoReserva.PATRIMONIO) {
                Iterator<RequisicaoDeReserva> iteratorReservas = model.getrequisicaoDeReservas().iterator();
                while (iteratorReservas.hasNext()) {
                    RequisicaoDeReserva reserva = iteratorReservas.next();
                    if (reserva.getId() == id && reserva.getTipoReserva() == TipoReserva.PATRIMONIO) {
                        iteratorReservas.remove();
                        encontrado = true;
                    }
                }
                if (!encontrado) {
                    throw new BemException(BemException.NAO_ENCONTRADO, tipoBP + " com o ID " + id + " não encontrado.");
                }
            } else if (tipoBP == TipoReserva.BEM) {
                Iterator<RequisicaoDeReserva> iteratorReservas = model.getrequisicaoDeReservas().iterator();
                while (iteratorReservas.hasNext()) {
                    RequisicaoDeReserva reserva = iteratorReservas.next();
                    if (reserva.getId() == id && reserva.getTipoReserva() == TipoReserva.BEM) {
                        iteratorReservas.remove();
                        encontrado = true;
                    }
                }
                if (!encontrado) {
                    throw new BemException(BemException.NAO_ENCONTRADO, tipoBP + " com o ID " + id + " não encontrado.");
                }
            } else {
                throw new IOException("Algum erro ocorreu");
            }
        } else if (Objects.equals(tipoRM, "manutencao")) {
            if (tipoBP == TipoReserva.PATRIMONIO) {
                Iterator<RequisicaoDeManutencao> iteratorManutencao = model.getRequisicaoDeManutencao().iterator();
                while (iteratorManutencao.hasNext()) {
                    RequisicaoDeManutencao manutencao = iteratorManutencao.next();
                    if (manutencao.getId() == id && manutencao.getTipo() == TipoReserva.PATRIMONIO) {
                        iteratorManutencao.remove();
                        encontrado = true;
                    }
                    if (!encontrado) {
                        throw new BemException(BemException.NAO_ENCONTRADO, tipoBP + " com o ID " + id + " não encontrado.");
                    }
                }
            } else if (tipoBP == TipoReserva.BEM) {
                Iterator<RequisicaoDeManutencao> iteratorManutencao = model.getRequisicaoDeManutencao().iterator();
                while (iteratorManutencao.hasNext()) {
                    RequisicaoDeManutencao manutencao = iteratorManutencao.next();
                    if (manutencao.getId() == id && manutencao.getTipo() == TipoReserva.BEM) {
                        iteratorManutencao.remove();
                        encontrado = true;
                    }
                    if (!encontrado) {
                        throw new BemException(BemException.NAO_ENCONTRADO, tipoBP + " com o ID " + id + " não encontrado.");
                    }
                }
            } else {
                throw new IOException("Algum erro ocorreu");
            }
        } else {
            throw new IOException("Algum erro ocorreu");
        }
    }
}
