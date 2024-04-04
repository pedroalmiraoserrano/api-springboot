package com.gerenciarfuncionarios.service;

import com.gerenciarfuncionarios.common.Mensagem;
import com.gerenciarfuncionarios.model.Colaborador;
import java.util.InputMismatchException;
import org.springframework.stereotype.Service;

@Service
public class ValidaColaboradorService {

  public String validar(Colaborador colaborador) {

    if (!validaCpf(colaborador.getCpf())) {
      return String.format(Mensagem.CPF_INVALIDO, colaborador.getCpf());
    }

    return null;
  }

  private boolean validaCpf(String cpf) {

    // Remover caracteres especiais
    cpf = cpf.replaceAll("\\.", "");
    cpf = cpf.replaceAll("-", "");

    if (cpf.equals("00000000000") ||
        cpf.equals("11111111111") ||
        cpf.equals("22222222222") || cpf.equals("33333333333") ||
        cpf.equals("44444444444") || cpf.equals("55555555555") ||
        cpf.equals("66666666666") || cpf.equals("77777777777") ||
        cpf.equals("88888888888") || cpf.equals("99999999999") ||
        (cpf.length() != 11)) {
      return false;
    }

    char decimoDigito, digitoOnze;
    int somatoria, indice, resultado, numero, peso;

    try {
      somatoria = 0;
      peso = 10;
      for (indice = 0; indice < 9; indice++) {
        numero = (cpf.charAt(indice) - 48);
        somatoria = somatoria + (numero * peso);
        peso = peso - 1;
      }

      resultado = 11 - (somatoria % 11);
      if ((resultado == 10) || (resultado == 11)) {
        decimoDigito = '0';
      } else {
        decimoDigito = (char) (resultado + 48);
      }

      somatoria = 0;
      peso = 11;
      for (indice = 0; indice < 10; indice++) {
        numero = (int) (cpf.charAt(indice) - 48);
        somatoria = somatoria + (numero * peso);
        peso = peso - 1;
      }

      resultado = 11 - (somatoria % 11);
      if ((resultado == 10) || (resultado == 11)) {
        digitoOnze = '0';
      } else {
        digitoOnze = (char) (resultado + 48);
      }

      return (decimoDigito == cpf.charAt(9)) && (digitoOnze == cpf.charAt(10));
    } catch (InputMismatchException erro) {
      return false;
    }
  }

}
