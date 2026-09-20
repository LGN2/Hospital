package com.CV.Hospital.dto;

import com.CV.Hospital.entities.type.RoomType;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO {

    private Long id;

    @NotBlank(message = "Room number is required")
    @Size(max = 20)
    private String roomNumber;

    @NotNull(message = "Floor is required")
    @PositiveOrZero(message = "Floor cannot be negative")
    private Integer floor;

    @NotNull(message = "Room type is required")
    private RoomType type;

    @NotNull(message = "Capacity is required")
    @Positive(message = "Capacity must be greater than 0")
    private Integer capacity;

    @NotNull(message = "Hospital ID is required")
    private Long hospitalId;
}