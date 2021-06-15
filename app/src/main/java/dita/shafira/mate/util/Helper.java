package dita.shafira.mate.util;

import android.annotation.SuppressLint;

import java.time.LocalDate;
import java.time.Period;

public class Helper {
    public static String calculateAge(String date) {
        LocalDate birthDate = LocalDate.parse(date);
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthDate, currentDate).getYears()+Double.valueOf(String.format("%.1f",(double)Period.between(birthDate, currentDate).getMonths()/12))+" tahun";
    }

    public static int calculateAgeMonth(String date) {
        LocalDate birthDate = LocalDate.parse(date);
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthDate, currentDate).getMonths();
    }

}