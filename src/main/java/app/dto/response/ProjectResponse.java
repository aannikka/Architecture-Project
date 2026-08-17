package app.dto.response;

import app.dto.shorts.BuildingShortResponse;
import app.dto.shorts.UserShortResponse;

import java.util.List;

public record ProjectResponse(
    Long id,
    String name,
    String description,
    String address,
    UserShortResponse user,
    List<BuildingShortResponse> buildings
){}
