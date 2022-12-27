package br.com.alura.bytebank.resource;

import br.com.alura.bytebank.domain.SaldoResponse;
import br.com.alura.bytebank.service.ConsultarSaldoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
public class ConsultarSaldo {

    private final ConsultarSaldoService consultarSaldoService;

    public ConsultarSaldo(ConsultarSaldoService consultarSaldoService) {
        this.consultarSaldoService = consultarSaldoService;
    }

    @ResponseStatus(OK)
    @GetMapping("/meu-saldo/{chavePix}")
    public SaldoResponse meuSaldo(@PathVariable("chavePix") final String chavePix) {
        return consultarSaldoService.realizarConsulta(chavePix);
    }

}
