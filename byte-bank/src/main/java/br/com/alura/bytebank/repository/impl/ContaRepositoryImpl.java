package br.com.alura.bytebank.repository.impl;

import br.com.alura.bytebank.exception.InternalServerError;
import br.com.alura.bytebank.exception.NotFound;
import br.com.alura.bytebank.repository.ContaRepository;
import br.com.alura.bytebank.repository.mongo.ContaRepositoryWithMongo;
import br.com.alura.bytebank.repository.orm.Conta;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ContaRepositoryImpl implements ContaRepository {

    private final ContaRepositoryWithMongo repository;

    public ContaRepositoryImpl(ContaRepositoryWithMongo repository) {
        this.repository = repository;
    }

    @Override
    public Conta save(final Conta conta) {
        try {
            return repository.save(conta);
        } catch (Exception ex) {
            throw new InternalServerError(ex);
        }
    }

    @Override
    public Conta procurarPelaChavePix(final String chavePix) {
        try {
            Optional<Conta> optional = repository.findById(chavePix);
            if (optional.isEmpty()) {
                throw new NotFound("Conta inexistente");
            }
            return optional.get();
        } catch (NotFound ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalServerError(ex);
        }
    }
}
