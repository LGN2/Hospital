package com.CV.Hospital.dto;

import com.CV.Hospital.entities.type.RoomType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO {

    private Long id;
    private String roomNumber;
    private Integer floor;
    private RoomType type;
    private Integer capacity;
    private Long hospitalId;
}