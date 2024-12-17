package com.client.rasplus.api.customer.dto;

import java.time.LocalDateTime;

public record UserRecoveryCodeDto(
        String email,
        String code,
        LocalDateTime creationDate

) {
}
