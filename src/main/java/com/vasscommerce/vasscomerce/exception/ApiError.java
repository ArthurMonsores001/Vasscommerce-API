package com.vasscommerce.vasscomerce.exception;

import java.time.OffsetDateTime;
import java.util.List;

public record ApiError(
        OffsetDateTime timestamp,
        int status,
        String error,
        String message,
        String path,
        List<CampoInvalido> campos
) {

    public record CampoInvalido(String campo, String mensagem) {
    }
}