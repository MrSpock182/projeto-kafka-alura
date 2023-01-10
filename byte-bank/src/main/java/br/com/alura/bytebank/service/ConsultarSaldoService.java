package br.com.alura.bytebank.service;

import br.com.alura.bytebank.domain.SaldoResponse;
import br.com.alura.bytebank.repository.ContaRepository;
import br.com.alura.bytebank.repository.orm.Conta;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ConsultarSaldoService {

    private final ContaRepository repository;

    public ConsultarSaldoService(ContaRepository repository) {
        this.repository = repository;
    }

    public SaldoResponse realizarConsulta(final String chavePix) {
        Conta conta = repository.procurarPelaChavePix(chavePix);
        return new SaldoResponse(conta.saldo());
    }

    public void addCemReais() {
        repository.save(new Conta("alura@alura.com.br", "alura", BigDecimal.valueOf(100.00)));
    }
}
