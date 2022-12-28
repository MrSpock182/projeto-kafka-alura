package br.com.alura.bytebank.messaging;

import java.math.BigDecimal;

public record EnvioPix(
        String chaveProdutor,
        String chaveQuemRecebe,
        BigDecimal valor
) {
}
