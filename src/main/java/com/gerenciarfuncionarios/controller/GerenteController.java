package com.gerenciarfuncionarios.controller;

import com.gerenciarfuncionarios.common.Mensagem;
import com.gerenciarfuncionarios.dto.AdicionarColaboradorAoGestorDto;
import com.gerenciarfuncionarios.dto.AtualizarGerenteDto;
import com.gerenciarfuncionarios.dto.CriarGerenteDto;
import com.gerenciarfuncionarios.dto.GerenteDto;
import com.gerenciarfuncionarios.dto.ResultadoDto;
import com.gerenciarfuncionarios.mapper.GerenteMapper;
import com.gerenciarfuncionarios.model.Gerente;
import com.gerenciarfuncionarios.service.ColaboradorService;
import com.gerenciarfuncionarios.service.GerenteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/gerentes")
public class GerenteController {

    private final ColaboradorService<Gerente> gerenteService;

    @Autowired
    public GerenteController(final ColaboradorService<Gerente> gerenteService) {
        this.gerenteService = gerenteService;
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> listarGerentes() {
        final List<Gerente> gerentes = this.gerenteService.listar();
        final List<GerenteDto> gerenteDtos = GerenteMapper.entidadeParaDto(gerentes);
        return ResponseEntity.ok(gerenteDtos);
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> criarGerente(@Valid @RequestBody CriarGerenteDto gerenteDto) {

        final Gerente superior = gerenteDto.getGerenteId() == null
                ? null
                : this.gerenteService.buscar(gerenteDto.getGerenteId());

        final Gerente gerente = Gerente.criar(
                gerenteDto.getNome(),
                gerenteDto.getCpf(),
                gerenteDto.getRg(),
                gerenteDto.getDataNascimento(),
                gerenteDto.getSalario(),
                superior
        );

        Gerente novoGerente = this.gerenteService.criar(gerente);

        if (gerenteDto.getFuncionariosIds() != null) {
            novoGerente = ((GerenteService) gerenteService).adicionarFuncionarios(novoGerente, gerenteDto.getFuncionariosIds());
        }

        final GerenteDto novoGerenteDto = GerenteMapper.entidadeParaDto(novoGerente);
        return ResponseEntity.ok(novoGerenteDto);
    }

    @PostMapping(
            value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> adicionarColaboradorAoGestor(@PathVariable Long id, @Valid @RequestBody AdicionarColaboradorAoGestorDto adicionarColaboradorAoGestorDto) {
        final Gerente gerente = ((GerenteService) gerenteService).adicionarFuncionario(id, adicionarColaboradorAoGestorDto.getFuncionarioIds());
        final GerenteDto gerenteDto = GerenteMapper.entidadeParaDto(gerente);
        return ResponseEntity.ok(gerenteDto);
    }

    @PutMapping(
            value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> atualizarGerente(@PathVariable Long id, @Valid @RequestBody AtualizarGerenteDto gerenteDto) {

        final Gerente superior = gerenteDto.getGerenteId() == null
                ? null
                : this.gerenteService.buscar(gerenteDto.getGerenteId());

        final Gerente gerente = Gerente.criar(
                gerenteDto.getNome(),
                gerenteDto.getCpf(),
                gerenteDto.getRg(),
                gerenteDto.getDataNascimento(),
                gerenteDto.getSalario(),
                superior
        );

        final Gerente novoGerente = this.gerenteService.atualizar(gerente, id);
        final GerenteDto novoGerenteDto = GerenteMapper.entidadeParaDto(novoGerente);
        return ResponseEntity.ok(novoGerenteDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarGerente(@PathVariable Long id) {
        this.gerenteService.deletar(id);
        final String msgRetorno = String.format(Mensagem.COLABORADOR_REMOVIDO_COM_SUCESSO, id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResultadoDto(msgRetorno));
    }

}
