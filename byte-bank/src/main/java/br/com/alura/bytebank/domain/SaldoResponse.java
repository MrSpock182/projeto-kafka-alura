package br.com.alura.bytebank.domain;

import java.math.BigDecimal;

public record SaldoResponse(
        BigDecimal saldo
) {
}
