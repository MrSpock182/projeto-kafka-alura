package br.com.alura.bytebank.service;

import br.com.alura.bytebank.domain.PixRequest;
import br.com.alura.bytebank.exception.BadRequest;
import br.com.alura.bytebank.exception.InternalServerError;
import br.com.alura.bytebank.messaging.EnvioPix;
import br.com.alura.bytebank.messaging.ProdutorPixBancoCentral;
import br.com.alura.bytebank.repository.ContaRepository;
import br.com.alura.bytebank.repository.orm.Conta;
import org.springframework.stereotype.Service;

@Service
public class PagamentoService {

    private final ContaRepository repository;
    private final ProdutorPixBancoCentral produtor;

    public PagamentoService(ContaRepository repository, ProdutorPixBancoCentral produtor) {
        this.repository = repository;
        this.produtor = produtor;
    }

    public void realizarPagamento(final PixRequest pixRequest) {
        try {
            Conta conta = repository.procurarPelaChavePix(pixRequest.chaveProdutor());
            if (conta.saldo().compareTo(pixRequest.valor()) < 0) {
                throw new BadRequest("Saldo insuficiente");
            }
            produtor.enviaMensagem(new EnvioPix(
                    pixRequest.chaveProdutor(),
                    pixRequest.chaveQuemRecebe(),
                    pixRequest.valor()
            ));
        } catch (BadRequest ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalServerError("erro", ex);
        }
    }
}
