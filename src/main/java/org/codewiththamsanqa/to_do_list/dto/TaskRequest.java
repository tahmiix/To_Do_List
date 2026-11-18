package org.codewiththamsanqa.to_do_list.dto;

import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record TaskRequest(
        @NotNull String taskName,
        @NotNull LocalDate dueDate) {
}
