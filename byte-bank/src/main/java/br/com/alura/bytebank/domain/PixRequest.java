package br.com.alura.bytebank.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public record PixRequest(
        @NotNull
        @NotEmpty
        String chavePix,
        @NotNull
        @NotEmpty
        Double valor
) {
}
