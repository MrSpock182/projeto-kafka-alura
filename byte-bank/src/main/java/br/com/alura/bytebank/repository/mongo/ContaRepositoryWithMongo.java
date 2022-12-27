package br.com.alura.bytebank.repository.mongo;

import br.com.alura.bytebank.repository.orm.Conta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepositoryWithMongo extends MongoRepository<Conta, String> {
}
