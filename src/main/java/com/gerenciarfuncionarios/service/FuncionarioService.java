package com.gerenciarfuncionarios.service;

import com.gerenciarfuncionarios.common.Mensagem;
import com.gerenciarfuncionarios.exceptions.RegraNegocioException;
import com.gerenciarfuncionarios.model.Funcionario;
import com.gerenciarfuncionarios.repository.FuncionarioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FuncionarioService implements ColaboradorService<Funcionario> {

    private final ValidaColaboradorService validaColaboradorService;
    private final FuncionarioRepository funcionarioRepository;

    @Autowired
    public FuncionarioService(final ValidaColaboradorService validaColaboradorService,
                              final FuncionarioRepository funcionarioRepository) {
        this.validaColaboradorService = validaColaboradorService;
        this.funcionarioRepository = funcionarioRepository;
    }

    @Override
    public Funcionario buscar(Long id) {
        return this.funcionarioRepository.findById(id)
            .orElse(null);
    }

    @Override
    public List<Funcionario> listar() {
        return this.funcionarioRepository.findAll();
    }

    @Override
    public Funcionario criar(Funcionario funcionario) {

        if (this.funcionarioRepository.buscarPorCpf(funcionario.getCpf()) != null) {
            final String msgRetorno = String.format(Mensagem.COLABORADOR_CPF_EXISTENTE, funcionario.getCpf());
            throw new RegraNegocioException(msgRetorno);
        }

        final String mensagemValidacao = validaColaboradorService.validar(funcionario);

        if (mensagemValidacao != null) {
            throw new RegraNegocioException(mensagemValidacao);
        }

        return funcionarioRepository.save(funcionario);
    }

    @Override
    public Funcionario atualizar(Funcionario funcionario, Long id) {

        final Funcionario funcionarioEntidade = this.buscar(id);

        if (funcionarioEntidade == null) {
            final String msgRetorno = String.format(Mensagem.COLABADORADOR_NAO_ENCONTRADO, id);
            throw new RegraNegocioException(msgRetorno);
        }

        final String mensagemValidacao = validaColaboradorService.validar(funcionario);

        if (mensagemValidacao != null) {
            throw new RegraNegocioException(mensagemValidacao);
        }

        funcionario.setId(id);
        return funcionarioRepository.save(funcionario);
    }

    @Override
    public boolean deletar(Long id) {

        final Funcionario funcionario = this.buscar(id);

        if (funcionario == null) {
            final String msgRetorno = String.format(Mensagem.COLABADORADOR_NAO_ENCONTRADO, id);
            throw new RegraNegocioException(msgRetorno);
        }

        funcionarioRepository.deleteById(id);
        return true;
    }

}
