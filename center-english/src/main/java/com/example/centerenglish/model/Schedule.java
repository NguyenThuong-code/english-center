package com.example.centerenglish.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "shift_1")
    private boolean shift1;

    @Column(name = "shift_2")
    private boolean shift2;
    @Column(name = "shift_3")
    private boolean shift3;

    @Column(name = "shift_4")
    private boolean shift4;
    @Column(name = "shift_5")
    private boolean shift5;

    @Column(name = "shift_6")
    private boolean shift6;
    @Column(name = "shift_7")
    private boolean shift7;

    @Column(name = "shift_8")
    private boolean shift8;
    @Column(name = "shift_9")
    private boolean shift9;

    @Column(name = "shift_10")
    private boolean shift10;
    @Column(name = "shift_11")
    private boolean shift11;

    @Column(name = "shift_12")
    private boolean shift12;
    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public Schedule(Schedule schedule) {
        this.shift1 = schedule.isShift1();
        this.shift2 = schedule.isShift2();
        this.shift3 = schedule.isShift3();
        this.shift4 = schedule.isShift4();
        this.shift5 = schedule.isShift5();
        this.shift6 = schedule.isShift6();
        this.shift7 = schedule.isShift7();
        this.shift8 = schedule.isShift8();
        this.shift9 = schedule.isShift9();
        this.shift10 = schedule.isShift10();
        this.shift11 = schedule.isShift11();
        this.shift12 = schedule.isShift12();
    }
}
