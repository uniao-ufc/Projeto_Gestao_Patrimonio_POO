package br.ufc.sistemapatrimonio.model;

import br.ufc.sistemapatrimonio.entities.*;
import br.ufc.sistemapatrimonio.enums.TipoReserva;
import br.ufc.sistemapatrimonio.exceptions.BemException;
import br.ufc.sistemapatrimonio.exceptions.ManutencaoException;
import br.ufc.sistemapatrimonio.exceptions.PatrimonioException;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UsuarioModel {
    private final Model model = new Model();

    public void adicionarRequisicao(int id, String nome, String local, String descricao, TipoReserva tipo) throws BemException, PatrimonioException, IOException {
        if (tipo == TipoReserva.BEM) {
            // Obter a lista de bens do sistema
            List<Bem> bensSistema = Model.getBens();

            Bem bemSelecionado = null;

            // Verificar se o bem existe no sistema e se já está alocado.
            for (Bem bem : bensSistema) {
                if (bem.getId() == id) {
                    if (bem.isAlocstatus()) {
                        throw new BemException(BemException.EXISTENTE, "O bem com o ID " + id + " já foi alocado no sistema.");
                    }
                    bemSelecionado = bem;
                    break;
                }
            }

            // Caso o bem não seja encontrado no sistema
            if (bemSelecionado == null) {
                throw new BemException(BemException.NAO_ENCONTRADO, "O bem com o ID " + id + " não foi encontrado no sistema.");
            }

            // Verificar se o usuário já requisitou o bem
            Usuario usuarioAutenticado = Model.getUsuarioAutenticado();
            for (Bem bem : usuarioAutenticado.getMeusBens()) {
                if (bem.getId() == id) {
                    throw new BemException(BemException.EXISTENTE, "O bem com o ID " + id + " já foi requisitado por você.");
                }
            }

            Local novoLocal = new Local(local);
            bemSelecionado.setLocal(novoLocal);

            // Atualizar o status de alocação do bem no sistema para true
            bemSelecionado.setAlocstatus(true);

            RequisicaoDeReserva requisicaoDeReserva = new RequisicaoDeReserva(bemSelecionado.getId(), nome, novoLocal, descricao, TipoReserva.BEM, Model.getUsuarioAutenticado().getUsername());
            model.getrequisicaoDeReservas().add(requisicaoDeReserva);
            Model.getUsuarioAutenticado().getMinhasRequisicaoDeReservas().add(requisicaoDeReserva);

            // Adicionar uma cópia do bem à lista de bens do usuário
            Model.adicionarBem(bemSelecionado);

        } else if (tipo == TipoReserva.PATRIMONIO) {
            List<Patrimonio> patrimoniosSistema = Model.getPatrimonios();

            Patrimonio patrimonioSelecionado = null;

            // Verificar se o patrimonio existe no sistema e se já está alocado
            for (Patrimonio patrimonio : patrimoniosSistema) {
                if (patrimonio.getId() == id) {
                    if (patrimonio.isAlocstatus()) {
                        throw new PatrimonioException(PatrimonioException.EXISTENTE, "O patrimonio com o ID " + id + " já foi alocado no sistema.");
                    }
                    patrimonioSelecionado = patrimonio;
                    break;
                }
            }

            // Caso o patrimonio não seja encontrado no sistema
            if (patrimonioSelecionado == null) {
                throw new PatrimonioException(PatrimonioException.NAO_ENCONTRADO, "Patrimonio com o ID " + id + " não foi encontrado no sistema.");
            }

            // Verificar se o usuário já requisitou o patrimonio
            Usuario usuarioAutenticado = Model.getUsuarioAutenticado();
            for (Patrimonio patrimonio : usuarioAutenticado.getMeusPatrimonios()) {
                if (patrimonio.getId() == id) {
                    throw new PatrimonioException(PatrimonioException.EXISTENTE, "Patrimonio com o ID " + id + " já foi requisitado por você.");
                }
            }

            Local novoLocal = new Local(local);
            patrimonioSelecionado.setLocal(novoLocal);

            // Atualizar o status de alocação do patrimonio no sistema para true
            patrimonioSelecionado.setAlocstatus(true);

            RequisicaoDeReserva requisicaoDeReserva = new RequisicaoDeReserva(patrimonioSelecionado.getId(), nome, novoLocal, descricao, TipoReserva.PATRIMONIO, Model.getUsuarioAutenticado().getUsername());
            model.getrequisicaoDeReservas().add(requisicaoDeReserva);
            Model.getUsuarioAutenticado().getMinhasRequisicaoDeReservas().add(requisicaoDeReserva);

            // Adicionar uma cópia do bem à lista de patrimonios do usuário
            Model.adicionarPatrimonio(patrimonioSelecionado);
        } else {
            throw new IOException("Algum erro ocorreu");
        }
    }

    public void removerRequisicao(int id, TipoReserva tipo) throws IOException, PatrimonioException, BemException {
        Usuario usuarioAutenticado = Model.getUsuarioAutenticado();
        //System.out.println(id + tipo.toString());
        if (tipo == TipoReserva.BEM) {
            // Remover o bem ou patrimônio
            boolean itemRemovido = false;

            // Remover bem
            for (Bem bem : Model.getBens()) {
                if (bem.getId() == id) {
                    bem.setAlocstatus(false); // Atualizar o status de alocação para falso
                    itemRemovido = true; // Marcar que um item foi removido
                    break; // Sai do loop após remover o bem
                }
            }

            if (!itemRemovido) {
                throw new BemException(BemException.NAO_ENCONTRADO, "Bem com o ID " + id + " não encontrado.");
            }

        } else if (tipo == TipoReserva.PATRIMONIO) {
            boolean itemRemovido = false;
            System.out.println("Chega aq\n");
            // Remover patrimônio, caso o bem não tenha sido removido
            for (Patrimonio patrimonio : Model.getPatrimonios()) {
                System.out.println("Chega NO ID");
                System.out.println(patrimonio.getId());
                if (patrimonio.getId() == id) {
                    patrimonio.setAlocstatus(false); // Atualizar o status de alocação para falso
                    itemRemovido = true; // Marcar que um item foi removido

                    System.out.println("Chega sera?");
                    break; // Sai do loop após remover o patrimônio
                }
            }

            // Se nenhum item foi removido, lançar uma exceção
            if (!itemRemovido) {
                throw new PatrimonioException(PatrimonioException.NAO_ENCONTRADO, "Patrimonio com o ID " + id + " não encontrado.");
            }

        } else {
            throw new IOException("Um erro ocorreu");
        }

        List<RequisicaoDeReserva> removidas = new ArrayList<>();
        for (RequisicaoDeReserva requisicao : usuarioAutenticado.getMinhasRequisicaoDeReservas()) {
            if (requisicao.getId() == id && requisicao.getTipoReserva() == tipo) {
                removidas.add(requisicao);
            }
        }
        usuarioAutenticado.getMinhasRequisicaoDeReservas().removeAll(removidas);

        // Remover a requisição de reserva associada
        //Iterator<RequisicaoDeReserva> requisicaoIterator = usuarioAutenticado.getMinhasRequisicaoDeReservas().iterator();
        // while (requisicaoIterator.hasNext()) {
        //    RequisicaoDeReserva requisicao = requisicaoIterator.next();
        //     if (requisicao.getIdReserva() == id && requisicao.getTipoReserva() == tipo) {
        //    requisicaoIterator.remove(); // Remover a requisição de reserva associada
        //        break; // Sai do loop após remover a requisição
        //   }
        // }
    }

    public String listarReservasUsuario() {
        StringBuilder lista = new StringBuilder();
        for (RequisicaoDeReserva requisicaoDeReserva : Model.getUsuarioAutenticado().getMinhasRequisicaoDeReservas()) {
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

    public void adicionarManutencao(int id, String nome, String descricao, TipoReserva tipo) throws BemException, PatrimonioException, IOException {
        if (tipo == TipoReserva.BEM) {
            // Obter a lista de bens do sistema
            List<RequisicaoDeManutencao> requisicaoDeManutencaos = model.getRequisicaoDeManutencao();

            List<Bem> bensSistema = Model.getBens();

            boolean bemSelecionado = false;

            // Verificar se o bem existe no sistema e se já está alocado
            for (Bem bem : bensSistema) {
                if (bem.getId() == id) {
                    bemSelecionado = true;
                    break;
                }
            }

            // Caso o bem não seja encontrado no sistema
            if (!bemSelecionado) {
                throw new BemException(BemException.NAO_ENCONTRADO, "O bem com o ID " + id + " não foi encontrado no sistema.");
            }

            // Verificar se o bem existe no sistema e se já está alocado
            for (RequisicaoDeManutencao manutencao : requisicaoDeManutencaos) {
                if (manutencao.getId() == id && manutencao.getTipo() == tipo) {
                    if (manutencao.getStatus()) {
                        throw new BemException(BemException.EXISTENTE, "O bem com o ID " + id + " já foi cadastrado para manutenção no sistema.");
                    }
                    break;
                }
            }

            Usuario usuarioAutenticado = Model.getUsuarioAutenticado();

            RequisicaoDeManutencao requisicaoDeManutencao = new RequisicaoDeManutencao(id, nome, descricao, tipo, true, Model.getUsuarioAutenticado().getUsername());

            model.getRequisicaoDeManutencao().add(requisicaoDeManutencao);

            usuarioAutenticado.getMinhasManutencoes().add(requisicaoDeManutencao);

        } else if (tipo == TipoReserva.PATRIMONIO) {
            // Obter a lista de bens do sistema
            List<RequisicaoDeManutencao> requisicaoDeManutencaos = model.getRequisicaoDeManutencao();

            List<Patrimonio> patrimoniosSistema = Model.getPatrimonios();

            boolean patrimonioSelecionado = false;

            // Verificar se o bem existe no sistema e se já está alocado
            for (Patrimonio patrimonio : patrimoniosSistema) {
                if (patrimonio.getId() == id) {
                    patrimonioSelecionado = true;
                    break;
                }
            }

            // Caso o bem não seja encontrado no sistema
            if (!patrimonioSelecionado) {
                throw new PatrimonioException(PatrimonioException.NAO_ENCONTRADO, "O bem com o ID " + id + " não foi encontrado no sistema.");
            }

            // Verificar se o bem existe no sistema e se já está alocado
            for (RequisicaoDeManutencao manutencao : requisicaoDeManutencaos) {
                if (manutencao.getId() == id && manutencao.getTipo() == tipo) {
                    if (manutencao.getStatus()) {
                        throw new PatrimonioException(PatrimonioException.EXISTENTE, "O bem com o ID " + id + " já foi cadastrado para manutenção no sistema.");
                    }
                    break;
                }
            }

            Usuario usuarioAutenticado = Model.getUsuarioAutenticado();

            RequisicaoDeManutencao requisicaoDeManutencao = new RequisicaoDeManutencao(id, nome, descricao, tipo, true, Model.getUsuarioAutenticado().getUsername());

            model.getRequisicaoDeManutencao().add(requisicaoDeManutencao);

            usuarioAutenticado.getMinhasManutencoes().add(requisicaoDeManutencao);

        } else {
            throw new IOException("Algum erro ocorreu");
        }
    }

    public void editarManutencao(int id, String nome, String descricao, TipoReserva tipo) throws ManutencaoException, IOException{
        if(tipo == TipoReserva.BEM){
            List<RequisicaoDeManutencao> requisicaoDeManutencaos = model.getRequisicaoDeManutencao();

            boolean manutencaoSelecionada = false;

            // Verificar se o bem existe no sistema e se já está alocado
            for (RequisicaoDeManutencao requisicaoDeManutencao : requisicaoDeManutencaos) {
                if (requisicaoDeManutencao.getId() == id && requisicaoDeManutencao.getTipo() == tipo) {
                    manutencaoSelecionada = true;
                    requisicaoDeManutencao.setNome(nome);
                    requisicaoDeManutencao.setDescricao(descricao);
                    break;
                }
            }

            // Caso o bem não seja encontrado no sistema
            if (!manutencaoSelecionada) {
                throw new ManutencaoException(ManutencaoException.NAO_ENCONTRADO, "O Manutenção de bem com o ID " + id + " não foi encontrado no sistema.");
            }

        } else if (tipo == TipoReserva.PATRIMONIO) {
            List<RequisicaoDeManutencao> requisicaoDeManutencaos = model.getRequisicaoDeManutencao();

            boolean manutencaoSelecionada = false;

            // Verificar se o bem existe no sistema e se já está alocado
            for (RequisicaoDeManutencao requisicaoDeManutencao : requisicaoDeManutencaos) {
                if (requisicaoDeManutencao.getId() == id && requisicaoDeManutencao.getTipo() == tipo) {
                    manutencaoSelecionada = true;
                    requisicaoDeManutencao.setNome(nome);
                    requisicaoDeManutencao.setDescricao(descricao);
                    break;
                }
            }

            // Caso o bem não seja encontrado no sistema
            if (!manutencaoSelecionada) {
                throw new ManutencaoException(ManutencaoException.NAO_ENCONTRADO, "O Manutenção de bem com o ID " + id + " não foi encontrado no sistema.");
            }
        }else {
            throw new IOException("Algum erro ocorreu");
        }
    }

    public void removerReqManutencao(int id, TipoReserva tipo) throws IOException, ManutencaoException {
        Usuario usuarioAutenticado = Model.getUsuarioAutenticado();
        //System.out.println(id + tipo.toString());
        if (tipo == TipoReserva.BEM) {
            // Remover o bem ou patrimônio
            boolean itemRemovido = false;

            Iterator<RequisicaoDeManutencao> requisicaoIterator = model.getRequisicaoDeManutencao().iterator();
            while (requisicaoIterator.hasNext()) {
                RequisicaoDeManutencao requisicao = requisicaoIterator.next();
                if (requisicao.getId() == id && requisicao.getTipo() == tipo) {
                    requisicaoIterator.remove(); // Remover a requisição de reserva associada
                    itemRemovido = true;
                    break; // Sai do loop após remover a requisição
                }
            }

            if (!itemRemovido) {
                model.mostrarPopup("Erro", "Manutenção de Patrimonio com o ID " + id + " não encontrado.", Alert.AlertType.ERROR);
                throw new ManutencaoException(ManutencaoException.NAO_ENCONTRADO, "Manutenção de Bem com o ID " + id + " não encontrado.");
            }

        } else if (tipo == TipoReserva.PATRIMONIO) {
            boolean itemRemovido = false;

            Iterator<RequisicaoDeManutencao> requisicaoIterator = model.getRequisicaoDeManutencao().iterator();
            while (requisicaoIterator.hasNext()) {
                RequisicaoDeManutencao requisicao = requisicaoIterator.next();
                if (requisicao.getId() == id && requisicao.getTipo() == tipo) {
                    requisicaoIterator.remove(); // Remover a requisição de reserva associada
                    itemRemovido = true;
                    break; // Sai do loop após remover a requisição
                }
            }

            if (!itemRemovido) {
                model.mostrarPopup("Erro", "Manutenção de Patrimonio com o ID " + id + " não encontrado.", Alert.AlertType.ERROR);
                throw new ManutencaoException(ManutencaoException.NAO_ENCONTRADO, "Manutenção de Patrimonio com o ID " + id + " não encontrado.");
            }
        } else {
            throw new IOException("Um erro ocorreu");
        }

        List<RequisicaoDeReserva> removidas = new ArrayList<>();
        for (RequisicaoDeReserva requisicao : usuarioAutenticado.getMinhasRequisicaoDeReservas()) {
            if (requisicao.getId() == id && requisicao.getTipoReserva() == tipo) {
                removidas.add(requisicao);
            }
        }
        usuarioAutenticado.getMinhasRequisicaoDeReservas().removeAll(removidas);

        // Remover a requisição de reserva associada
        Iterator<RequisicaoDeManutencao> requisicaoIterator = usuarioAutenticado.getMinhasManutencoes().iterator();
        while (requisicaoIterator.hasNext()) {
            RequisicaoDeManutencao requisicao = requisicaoIterator.next();
            if (requisicao.getId() == id && requisicao.getTipo() == tipo) {
                requisicaoIterator.remove(); // Remover a requisição de reserva associada
                break; // Sai do loop após remover a requisição
            }
        }
    }

    public String listarManutencoesUsuario() {
        StringBuilder lista = new StringBuilder();
        for (RequisicaoDeManutencao requisicaoDeManutencao : Model.getUsuarioAutenticado().getMinhasManutencoes()) {
            // Adiciona todas as informações relevantes das reservas
            lista.append("ID: ").append(requisicaoDeManutencao.getId())
                    .append(", Nome: ").append(requisicaoDeManutencao.getNome())
                    .append(", Descrição: ").append(requisicaoDeManutencao.getDescricao())
                    .append(", Tipo: ").append(requisicaoDeManutencao.getTipo().toString())
                    .append("\n");
        }
        return lista.toString();
    }

}
