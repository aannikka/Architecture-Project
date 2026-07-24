package app.dto.response;

import app.dto.shorts.BuildingShortResponse;
import app.dto.shorts.UserShortResponse;

public record ProjectResponse(
    Long id,
    String name,
    String description,
    String address,
    UserShortResponse user,
    BuildingShortResponse building
){}
