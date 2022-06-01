package com.modaniru.platform.list;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {
    private static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d.MM.yyyy");

    public static void main(String[] args) {
        LocalDate date = LocalDate.parse("12.12.2022", FORMATTER);
        LocalDate date1 = LocalDate.parse("12.12.2022", FORMATTER);
        System.out.println(date.isAfter(date1));
    }
}
