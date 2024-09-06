package br.ufc.sistemapatrimonio.entities;
import br.ufc.sistemapatrimonio.exceptions.*;
import br.ufc.sistemapatrimonio.model.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Administrador extends Usuario {
    private final HashMap<Integer, Bem> bensMap = new HashMap<>();
    private final HashMap<Integer, Patrimonio> patrimoniosMap = new HashMap<>();
    private final HashMap<Integer, RequisicaoDeManutencao> manutencoesMap = new HashMap<>();
    private final ArrayList<String> historicoDeAcoes = new ArrayList<>();

    public Administrador(String login, String senha) {
        super(login, senha);
    }

    public void registrarAcao(String acao) {
        historicoDeAcoes.add(acao);
    }

    public ArrayList<String> getHistoricoDeAcoes() {
        return historicoDeAcoes;
    }

    public void adicionarBem(Bem bem) {
        bensMap.put(bem.getId(), bem);
        registrarAcao("Adicionou o bem: " + bem.getId());
    }

    public void adicionarPatrimonio(Patrimonio patrimonio) {
        patrimoniosMap.put(patrimonio.getId(), patrimonio);
        registrarAcao("Adicionou o patrimônio: " + patrimonio.getId());
    }

    public void adicionarRequisicaoDeManutencao(RequisicaoDeManutencao requisicao) {
        manutencoesMap.put(requisicao.getId(), requisicao);
        registrarAcao("Adicionou a requisição de manutenção: " + requisicao.getId());
    }

    public Bem buscarBem(int id) throws DadoNaoEncontradoException {
        Bem bem = bensMap.get(id);
        if (bem == null) {
            throw new DadoNaoEncontradoException("Bem não encontrado.");
        }
        return bem;
    }

    public Patrimonio buscarPatrimonio(int id) throws DadoNaoEncontradoException {
        Patrimonio patrimonio = patrimoniosMap.get(id);
        if (patrimonio == null) {
            throw new DadoNaoEncontradoException("Patrimônio não encontrado.");
        }
        return patrimonio;
    }

    public RequisicaoDeManutencao buscarRequisicao(int id) throws DadoNaoEncontradoException {
        RequisicaoDeManutencao requisicao = manutencoesMap.get(id);
        if (requisicao == null) {
            throw new DadoNaoEncontradoException("Requisição de Manutenção não encontrada.");
        }
        return requisicao;
    }

    public ArrayList<Bem> listarBens() {
        return new ArrayList<>(bensMap.values());
    }

    public ArrayList<Patrimonio> listarPatrimonios() {
        return new ArrayList<>(patrimoniosMap.values());
    }

    public ArrayList<RequisicaoDeManutencao> listarManutencoes() {
        return new ArrayList<>(manutencoesMap.values());
    }

    public void excluirBem(int id) throws DadoNaoEncontradoException {
        if (bensMap.containsKey(id)) {
            bensMap.remove(id);
            registrarAcao("Excluiu o bem: " + id);
        } else {
            throw new DadoNaoEncontradoException("Bem não encontrado.");
        }
    }
}
