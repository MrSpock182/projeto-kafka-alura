package br.com.alura.bytebank.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public record PixRequest(
        @NotNull
        @NotEmpty
        String chaveProdutor,
        @NotNull
        @NotEmpty
        String chaveQuemRecebe,
        @NotNull
        BigDecimal valor
) {
}
