package br.com.alura.bytebank.resource;

import br.com.alura.bytebank.domain.PixRequest;
import br.com.alura.bytebank.service.PagamentoService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class PagarResource {

    private final PagamentoService pagamentoService;

    public PagarResource(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    public void pagar(@Valid @RequestBody final PixRequest pixRequest) {
        pagamentoService.realizarPagamento(pixRequest);
    }

}