package com.unitrs.model.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    private int id;
    private String roomNumber;
    private int floorNumber;
    private int capacity;
}
