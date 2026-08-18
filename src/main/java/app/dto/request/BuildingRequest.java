package app.dto.request;

import app.entity.enums.FoundationType;
import app.entity.enums.RoofType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record BuildingRequest (
    @NotBlank
    String name,

    @Positive
    double length,

    @Positive
    double width,

    @Positive
    int floors,

    @Positive
    double floorHeight,

    @Positive
    double wallThickness,

    @NotNull
    RoofType roofType,

    @NotNull
    FoundationType foundationType,

    @NotNull
    Long projectId
){}
