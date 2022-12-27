package br.com.alura.bytebank.repository.orm;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "conta")
public record Conta(
        @Id
        String chavePix,
        String nomeCliente,
        Double saldo
) {
}
