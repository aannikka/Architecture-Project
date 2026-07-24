package app.dto.shorts;

public record BuildingShortResponse(
        Long id,
        double length,
        double width,
        String roofType
){}
