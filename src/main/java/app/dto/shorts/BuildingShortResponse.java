package app.dto.shorts;

public record BuildingShortResponse(
        Long id,
        double length,
        double width,
        int floors,
        String roofType,
        String foundationType
){}
