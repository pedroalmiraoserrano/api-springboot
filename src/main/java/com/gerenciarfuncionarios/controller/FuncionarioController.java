package com.gerenciarfuncionarios.controller;

import com.gerenciarfuncionarios.common.Mensagem;
import com.gerenciarfuncionarios.dto.AtualizarFuncionarioDto;
import com.gerenciarfuncionarios.dto.CriarFuncionarioDto;
import com.gerenciarfuncionarios.dto.FuncionarioDto;
import com.gerenciarfuncionarios.dto.ResultadoDto;
import com.gerenciarfuncionarios.mapper.FuncionarioMapper;
import com.gerenciarfuncionarios.model.Funcionario;
import com.gerenciarfuncionarios.model.Gerente;
import com.gerenciarfuncionarios.service.ColaboradorService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private final ColaboradorService<Funcionario> funcionarioService;

    private final ColaboradorService<Gerente> gerenteService;

    @Autowired
    public FuncionarioController(final ColaboradorService<Funcionario> funcionarioService,
                                 final ColaboradorService<Gerente> gerenteService) {
        this.funcionarioService = funcionarioService;
        this.gerenteService = gerenteService;
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> listarFuncionarios() {
        List<Funcionario> funcionarios = funcionarioService.listar();
        List<FuncionarioDto> funcionarioDtos = FuncionarioMapper.entidadeParaDto(funcionarios);
        return ResponseEntity.ok(funcionarioDtos);
    }


    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> criarFuncionario(@Valid @RequestBody CriarFuncionarioDto criarFuncionarioDto) {

        Gerente gerente = null;

        if (criarFuncionarioDto.getGerenteId() != null) {
            gerente = gerenteService.buscar(criarFuncionarioDto.getGerenteId());
        }

        final Funcionario funcionario = Funcionario.criar(
                criarFuncionarioDto.getNome(),
                criarFuncionarioDto.getCpf(),
                criarFuncionarioDto.getRg(),
                criarFuncionarioDto.getDataNascimento(),
                criarFuncionarioDto.getSalario(),
                gerente
        );

        final Funcionario funcionarioSalvo = funcionarioService.criar(funcionario);
        final FuncionarioDto funcionarioDto = FuncionarioMapper.entidadeParaDto(funcionarioSalvo);

        return ResponseEntity.ok(
                funcionarioDto
        );
    }

    @PutMapping(
            value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody AtualizarFuncionarioDto funcionarioDto) {

        final Gerente gerente = funcionarioDto.getGerenteId() == null
                ? null
                : gerenteService.buscar(funcionarioDto.getGerenteId());

        final Funcionario funcionario = Funcionario.criar(
                funcionarioDto.getNome(),
                funcionarioDto.getCpf(),
                funcionarioDto.getRg(),
                funcionarioDto.getDataNascimento(),
                funcionarioDto.getSalario(),
                gerente
        );

        final Funcionario funcionarioAtualizado = this.funcionarioService.atualizar(funcionario, id);
        final FuncionarioDto novoFuncionarioDto = FuncionarioMapper.entidadeParaDto(funcionarioAtualizado);
        return ResponseEntity.ok(novoFuncionarioDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        this.funcionarioService.deletar(id);
        final String msgRetorno = String.format(Mensagem.COLABORADOR_REMOVIDO_COM_SUCESSO, id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResultadoDto(msgRetorno));
    }

}
