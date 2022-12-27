package br.com.alura.bytebank.repository;

import br.com.alura.bytebank.repository.orm.Conta;

public interface ContaRepository {
    Conta save(Conta conta);

    Conta procurarPelaChavePix(String chavePix);
}
