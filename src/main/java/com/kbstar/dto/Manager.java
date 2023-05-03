package com.kbstar.dto;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Manager {
    private int id;
    private String name;
    private String position;
    private String office;
    private int age;
    private String start_date;
    private int Salary;

}

