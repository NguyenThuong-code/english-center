package com.example.centerenglish.request;

import com.example.centerenglish.model.Schedule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String name;
    private LocalDate dateOfBirth;
    private LocalDate startWorkingDate;
    private Integer markType;
    private Schedule schedule;
}
