package com.gerenciarfuncionarios.service;

import com.gerenciarfuncionarios.common.Mensagem;
import com.gerenciarfuncionarios.exceptions.RegraNegocioException;
import com.gerenciarfuncionarios.model.Funcionario;
import com.gerenciarfuncionarios.model.Gerente;
import com.gerenciarfuncionarios.repository.FuncionarioRepository;
import com.gerenciarfuncionarios.repository.GerenteRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class GerenteService implements ColaboradorService<Gerente> {

    private final ValidaColaboradorService validaColaboradorService;
    private final GerenteRepository gerenteRepository;
    private final FuncionarioRepository funcionarioRepository;

    @Autowired
    public GerenteService(final ValidaColaboradorService validaColaboradorService,
                          final GerenteRepository gerenteRepository,
                          final FuncionarioRepository funcionarioRepository) {
        this.validaColaboradorService = validaColaboradorService;
        this.gerenteRepository = gerenteRepository;
        this.funcionarioRepository = funcionarioRepository;
    }


    @Override
    public Gerente buscar(Long id) {
        return this.gerenteRepository.findById(id).orElse(null);
    }

    @Override
    public List<Gerente> listar() {
       return gerenteRepository.findAll();
    }

    @Override
    public Gerente criar(Gerente gerente) {

        if (this.funcionarioRepository.buscarPorCpf(gerente.getCpf()) != null) {
            final String msgRetorno = String.format(Mensagem.COLABORADOR_CPF_EXISTENTE, gerente.getCpf());
            throw new RegraNegocioException(msgRetorno);
        }

        final String mensagemValidacao = validaColaboradorService.validar(gerente);

        if (mensagemValidacao != null) {
            throw new RegraNegocioException(mensagemValidacao);
        }

        return gerenteRepository.save(gerente);
    }

    @Override
    public Gerente atualizar(Gerente gerente, Long id) {

        final Gerente gerenteEntidade = this.buscar(id);

        if (gerenteEntidade == null) {
            final String msgRetorno = String.format(Mensagem.COLABADORADOR_NAO_ENCONTRADO, id);
            throw new RegraNegocioException(msgRetorno);
        }

        final String mensagemValidacao = validaColaboradorService.validar(gerente);

        if (mensagemValidacao != null) {
            throw new RegraNegocioException(mensagemValidacao);
        }

        gerenteEntidade.setCpf(gerente.getCpf());
        gerenteEntidade.setRg(gerente.getRg());
        gerenteEntidade.setNome(gerente.getNome());
        gerenteEntidade.setDataNascimento(gerente.getDataNascimento());
        gerenteEntidade.setSalario(gerente.getSalario());
        return gerenteRepository.save(gerenteEntidade);
    }

    @Override
    public boolean deletar(Long id) {
        final Gerente gerenteEntidade = this.buscar(id);

        if (gerenteEntidade == null) {
            final String msgRetorno = String.format(Mensagem.COLABADORADOR_NAO_ENCONTRADO, id);
            throw new RegraNegocioException(msgRetorno);
        }

        gerenteEntidade.getFuncionarios().forEach(funcionario -> {
            funcionario.setGerente(null);
            this.funcionarioRepository.save(funcionario);
        });

        gerenteRepository.deleteById(id);
        return true;

    }

    public Gerente adicionarFuncionario(Long id, Long funcionarioId) {

        final Gerente gerenteEntidade = this.buscar(id);

        if (gerenteEntidade == null) {
            final String msgRetorno = String.format(Mensagem.COLABADORADOR_NAO_ENCONTRADO, id);
            throw new RegraNegocioException(msgRetorno);
        }

        return adicionarFuncionario(gerenteEntidade, id);
    }

    public Gerente adicionarFuncionario(Long id, List<Long> funcionarioIds) {

        final Gerente gerenteEntidade = this.buscar(id);

        if (gerenteEntidade == null) {
            final String msgRetorno = String.format(Mensagem.COLABADORADOR_NAO_ENCONTRADO, id);
            throw new RegraNegocioException(msgRetorno);
        }

        return adicionarFuncionarios(gerenteEntidade, funcionarioIds);
    }

    public Gerente adicionarFuncionario(Gerente gerente, Long funcionarioId) {
        final Funcionario funcionario = this.funcionarioRepository.findById(funcionarioId).orElse(null);

        if (funcionario == null) {
            final String msgErro = String.format(Mensagem.COLABADORADOR_NAO_ENCONTRADO, funcionarioId);
            throw new RegraNegocioException(msgErro);
        }

        gerente.adicionarFuncionario(funcionario);
        funcionario.setGerente(gerente);
        this.funcionarioRepository.save(funcionario);
        return this.gerenteRepository.save(gerente);
    }

    public Gerente adicionarFuncionarios(Gerente gerente, List<Long> funcionarioIds) {
        Gerente novoGerente = null;
        for (Long id : funcionarioIds) {
            novoGerente = adicionarFuncionario(gerente, id);
        }
        return novoGerente;
    }

}
