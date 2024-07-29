package com.example.lap5tracker.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class Tracker {

@NotEmpty(message = "id must be noy empty")
@Size(min = 3,max = 20, message = "the size of the is must be between 3-20")
@Pattern(regexp = "^[0-9]+(\\.[0-9]+)?$", message = "id must be a numeric value")
    private int id;
@NotEmpty(message =" title shouldn not be empty")
@Size(min = 8,max = 30, message ="the size of the is must be between 8-30" )
    private String title;
@NotEmpty(message ="description should not be  empty" )
@Size(min = 15,max = 123456,message = "description must be higher than 15")

    private String description;
@NotNull(message = "status must be not empty")
    private String status;
    @NotNull(message = "companyName must be not empty")

    @Size(min=7,max=25,message = "companyName should should betweeen 7-25")

    private String companyName;
}
