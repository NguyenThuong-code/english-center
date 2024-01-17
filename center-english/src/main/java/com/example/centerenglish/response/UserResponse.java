package com.example.centerenglish.response;

import com.example.centerenglish.model.Schedule;
import com.example.centerenglish.model.User;
import com.example.centerenglish.type.MarkType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String name;
    private LocalDate dateOfBirth;
    private LocalDate startWorkingDate;
    private EnumResponse markType;
    private Schedule schedule;

    public UserResponse(User user) {
        this.id= user.getId();
        this.name = user.getName();
        this.dateOfBirth = user.getDateOfBirth();
        this.startWorkingDate = user.getStartWorkingDate();
        this.markType = MarkType.getInstance(user.getMarkType());
        this.schedule = user.getSchedule();
    }
}
