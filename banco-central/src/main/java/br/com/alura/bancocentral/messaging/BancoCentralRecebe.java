package br.com.alura.bancocentral.messaging;

import java.math.BigDecimal;

public record BancoCentralRecebe(
        String chaveProdutor,
        String chaveQuemRecebe,
        BigDecimal valor
) {
}
