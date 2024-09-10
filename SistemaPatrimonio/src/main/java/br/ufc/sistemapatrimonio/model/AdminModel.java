package br.ufc.sistemapatrimonio.model;

import br.ufc.sistemapatrimonio.entities.*;
import br.ufc.sistemapatrimonio.enums.TipoReserva;
import br.ufc.sistemapatrimonio.exceptions.BemException;
import br.ufc.sistemapatrimonio.exceptions.PatrimonioException;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class AdminModel extends UsuarioModel {

    // Instância do modelo que gerencia o sistema
    private final Model model = new Model();

    // Método para adicionar um novo patrimônio
    public void adicionarPatrimonio(int id, String nome, String descricao, double depreciacao, String tipo, int numeroTombamento) throws PatrimonioException {
        // Verifica se já existe um patrimônio com o mesmo id
        if (Model.getPatrimonios().stream().anyMatch(p -> p.getId() == id)) {
            throw new PatrimonioException(PatrimonioException.EXISTENTE, "O patrimônio com o ID " + id + " já existe.");
        }

        // Cria um novo tipo de patrimônio
        TipoPatrimonio tipoPatrimonio = new TipoPatrimonio(tipo, descricao, depreciacao);

        // Cria um novo patrimônio com os dados fornecidos
        Patrimonio novoPatrimonio = new Patrimonio(id, nome, tipoPatrimonio, numeroTombamento, false);

        // Adiciona o novo patrimônio à lista de patrimônios
        Model.getPatrimonios().add(novoPatrimonio);
    }

    // Método para editar um patrimônio existente
    public void editarPatrimonio(int id, String nome, String descricao, double depreciacao, String tipo, int numeroTombamento) throws PatrimonioException {
        // Procura o patrimônio existente na lista
        Optional<Patrimonio> patrimonioExistente = Model.getPatrimonios().stream().filter(p -> p.getId() == id).findFirst();
        if (patrimonioExistente.isPresent()) {
            Patrimonio patrimonio = patrimonioExistente.get();
            // Atualiza os dados do patrimônio encontrado
            patrimonio.setNome(nome);
            patrimonio.getTipo().setNome(tipo);
            patrimonio.getTipo().setDescricao(descricao);
            patrimonio.getTipo().setDepreciacaoAnual(depreciacao);
            patrimonio.setNumeroTombamento(numeroTombamento);
        } else {
            throw new PatrimonioException(PatrimonioException.NAO_ENCONTRADO, "Patrimônio com o ID " + id + " não encontrado.");
        }
    }

    // Método para remover um patrimônio
    public void removerPatrimonio(int id) throws PatrimonioException {
        boolean encontrado = false;

        // Usando um iterator para remoção segura
        Iterator<Patrimonio> iteratorPatrimonios = Model.getPatrimonios().iterator();
        while (iteratorPatrimonios.hasNext()) {
            Patrimonio patrimonio = iteratorPatrimonios.next();
            if (patrimonio.getId() == id) {
                // Remove o patrimônio da lista
                iteratorPatrimonios.remove();
                encontrado = true;

                // Remove as requisições de manutenção e reserva associadas ao patrimônio
                for (Usuario usuario : Model.getUsers()) {
                    // Remove requisições de manutenção associadas ao patrimônio
                    Iterator<RequisicaoDeManutencao> iteratorManutencoesUsuario = usuario.getMinhasManutencoes().iterator();
                    while (iteratorManutencoesUsuario.hasNext()) {
                        RequisicaoDeManutencao manutencao = iteratorManutencoesUsuario.next();
                        if (manutencao.getId() == id && manutencao.getTipo() == TipoReserva.PATRIMONIO) {
                            iteratorManutencoesUsuario.remove();
                            break;
                        }
                    }
                }
                // Remove requisições de reserva associadas ao patrimônio
                for (Usuario usuario : Model.getUsers()) {
                    Iterator<RequisicaoDeReserva> iteratorReservasUsuario = usuario.getMinhasRequisicaoDeReservas().iterator();
                    while (iteratorReservasUsuario.hasNext()) {
                        RequisicaoDeReserva reserva = iteratorReservasUsuario.next();
                        if (reserva.getId() == id && reserva.getTipoReserva() == TipoReserva.PATRIMONIO) {
                            iteratorReservasUsuario.remove();
                            break;
                        }
                    }
                }
            }
        }

        if (!encontrado) {
            throw new PatrimonioException(PatrimonioException.NAO_ENCONTRADO, "Patrimônio com o ID " + id + " não encontrado.");
        }
    }

    // Método para listar todos os patrimônios
    public String listarPatrimonios() {
        StringBuilder lista = new StringBuilder();
        for (Patrimonio patrimonio : Model.getPatrimonios()) {
            // Adiciona as informações do patrimônio na lista
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

    // Método para adicionar um novo bem
    public void adicionarBem(int id, String nome, String descricao, double depreciacao, String tipo) throws BemException {
        // Verifica se já existe um bem com o mesmo id
        if (Model.getBens().stream().anyMatch(b -> b.getId() == id)) {
            throw new BemException(BemException.EXISTENTE, "O bem com o ID " + id + " já existe.");
        }

        // Cria um novo tipo de bem
        TipoBem tipoBem = new TipoBem(tipo, descricao, depreciacao);

        // Cria um novo bem com os dados fornecidos
        Bem novoBem = new Bem(id, nome, tipoBem, false);

        // Adiciona o novo bem à lista de bens
        Model.getBens().add(novoBem);
    }

    // Método para editar um bem existente
    public void editarBem(int id, String nome, String descricao, double depreciacao, String tipo) throws BemException {
        boolean encontrado = false;

        // Procura o bem existente na lista
        for (Bem bem : Model.getBens()) {
            if (bem.getId() == id) {
                encontrado = true;
                // Atualiza os dados do bem encontrado
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

    // Método para remover um bem
    public void removerBem(int id) throws BemException {
        boolean encontrado = false;

        // Usando um iterator para remoção segura
        Iterator<Bem> iteratorBens = Model.getBens().iterator();
        while (iteratorBens.hasNext()) {
            Bem bem = iteratorBens.next();
            if (bem.getId() == id) {
                // Remove o bem da lista
                iteratorBens.remove();
                encontrado = true;

                // Remove as requisições de manutenção e reserva associadas ao bem
                for (Usuario usuario : Model.getUsers()) {
                    // Remove requisições de manutenção associadas ao bem
                    Iterator<RequisicaoDeManutencao> iteratorManutencoesUsuario = usuario.getMinhasManutencoes().iterator();
                    while (iteratorManutencoesUsuario.hasNext()) {
                        RequisicaoDeManutencao manutencao = iteratorManutencoesUsuario.next();
                        if (manutencao.getId() == id && manutencao.getTipo() == TipoReserva.BEM) {
                            iteratorManutencoesUsuario.remove();
                            break;
                        }
                    }
                }
                // Remove requisições de reserva associadas ao bem
                for (Usuario usuario : Model.getUsers()) {
                    Iterator<RequisicaoDeReserva> iteratorReservasUsuario = usuario.getMinhasRequisicaoDeReservas().iterator();
                    while (iteratorReservasUsuario.hasNext()) {
                        RequisicaoDeReserva reserva = iteratorReservasUsuario.next();
                        if (reserva.getId() == id && reserva.getTipoReserva() == TipoReserva.BEM) {
                            iteratorReservasUsuario.remove();
                            break;
                        }
                    }
                }
            }
        }

        if (!encontrado) {
            throw new BemException(BemException.NAO_ENCONTRADO, "Bem com o ID " + id + " não encontrado.");
        }
    }

    // Método para listar todos os bens
    public String listarBens() {
        StringBuilder lista = new StringBuilder();
        for (Bem bem : Model.getBens()) {
            // Adiciona as informações do bem na lista
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

    // Método para listar todas as reservas
    public String listarReservas() {
        StringBuilder lista = new StringBuilder();
        for (RequisicaoDeReserva requisicaoDeReserva : model.getrequisicaoDeReservas()) {
            // Adiciona as informações das reservas na lista
            lista.append("ID: ").append(requisicaoDeReserva.getId())
                    .append(", Nome: ").append(requisicaoDeReserva.getNome())
                    .append(", Local: ").append(requisicaoDeReserva.getLocal().getEndereco())
                    .append(", Descrição: ").append(requisicaoDeReserva.getDescricao())
                    .append(", Tipo: ").append(requisicaoDeReserva.getTipoReserva().toString())
                    .append("\n");
        }
        return lista.toString();
    }

    // Método para listar todas as manutenções
    public String listarManutencoes() {
        StringBuilder lista = new StringBuilder();
        for (RequisicaoDeManutencao requisicaoDeManutencao : model.getRequisicaoDeManutencao()) {
            // Adiciona as informações das manutenções na lista
            lista.append("ID: ").append(requisicaoDeManutencao.getId())
                    .append(", Nome: ").append(requisicaoDeManutencao.getNome())
                    .append(", Descrição: ").append(requisicaoDeManutencao.getDescricao())
                    .append(", Tipo: ").append(requisicaoDeManutencao.getTipo().toString())
                    .append("\n");
        }
        return lista.toString();
    }

    // Método para remover histórico de reservas ou manutenções
    public void removerHistorico(int id, String tipoRM, TipoReserva tipoBP) throws BemException, IOException {
        boolean encontrado = false;

        if (Objects.equals(tipoRM, "reserva")) {
            if (tipoBP == TipoReserva.PATRIMONIO) {
                // Remove reservas de patrimônio
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
                // Remove reservas de bem
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
                // Remove manutenções de patrimônio
                Iterator<RequisicaoDeManutencao> iteratorManutencao = model.getRequisicaoDeManutencao().iterator();
                while (iteratorManutencao.hasNext()) {
                    RequisicaoDeManutencao manutencao = iteratorManutencao.next();
                    if (manutencao.getId() == id && manutencao.getTipo() == TipoReserva.PATRIMONIO) {
                        iteratorManutencao.remove();
                        encontrado = true;
                    }
                }
                if (!encontrado) {
                    throw new BemException(BemException.NAO_ENCONTRADO, tipoBP + " com o id " + id + " não encontrado.");
                }
            } else if (tipoBP == TipoReserva.BEM) {
                // Remove manutenções de bem
                Iterator<RequisicaoDeManutencao> iteratorManutencao = model.getRequisicaoDeManutencao().iterator();
                while (iteratorManutencao.hasNext()) {
                    RequisicaoDeManutencao manutencao = iteratorManutencao.next();
                    if (manutencao.getId() == id && manutencao.getTipo() == TipoReserva.BEM) {
                        iteratorManutencao.remove();
                        encontrado = true;
                    }
                }
                if (!encontrado) {
                    throw new BemException(BemException.NAO_ENCONTRADO, tipoBP + " com o ID " + id + " não encontrado.");
                }
            } else {
                throw new IOException("Algum erro ocorreu");
            }
        } else {
            throw new IOException("Algum erro ocorreu");
        }
    }
}
