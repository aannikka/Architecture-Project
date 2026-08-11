package app.dto.request;

import jakarta.validation.constraints.NotNull;

public record EstimateRequest(

    @NotNull
    Long projectId
) {}
