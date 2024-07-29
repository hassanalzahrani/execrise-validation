package com.example.lab5event.modell;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Event {
@NotEmpty(message ="id should not be EMPTY" )
@Size(min = 2,max = 10)
private String id;
@NotEmpty(message = "description should not be EMPTY ")
@Size(min = 3,max = 1000)
    private String description;
@NotNull(message = "capacity should not be EMPTY")
@Pattern(regexp = "^[0-9]+(\\.[0-9]+)?$", message = "capacity must be a numeric value")
@NotNull(message = "capacity should not be EMPTY")
@Size(min = 25,max = 999999,message = "capacity must be more than 25")
    private int capacity;
@NotNull
    private String starDate;
    private String endDate;
}
