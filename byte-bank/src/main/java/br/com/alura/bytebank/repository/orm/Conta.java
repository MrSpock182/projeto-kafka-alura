package br.com.alura.bytebank.repository.orm;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "conta")
public record Conta(
        @Id
        String chavePix,
        String nomeCliente,
        BigDecimal saldo
) {
}
