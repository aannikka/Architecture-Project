package app.dto.response;

public record BuildingResponse (
    Long id,
    String name,
    double length,
    double width,
    int floors,
    double floorHeight,
    double wallThickness,
    String roofType,
    String foundationType
) {}
