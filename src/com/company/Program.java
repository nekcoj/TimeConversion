package com.company;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

public class Program {

    public void run(){
        getDaysOld();
        skypeDate();
        fridayTheThirteenth();
        eightyYears();
        depressed();
    }

    private void skypeDate() {
        ZonedDateTime timeNY = LocalDateTime.of(2020, Month.APRIL, 2, 10, 30)
                .atZone(ZoneId.of("America/New_York"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm a VV");
        DateTimeFormatter formatterSWE = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm VV");
        String timeInNY = timeNY.format(formatter);
        ZonedDateTime timeInNewYork = ZonedDateTime.of(LocalDateTime.from(timeNY), ZoneId.of("America/New_York"));
        String timeInSweden = timeInNewYork.toInstant().atZone(ZoneId.of("Europe/Stockholm")).format(formatterSWE);

        System.out.println(timeInNY);
        System.out.println(timeInSweden + "\n");
    }

    public void getDaysOld(){
        LocalDate now = LocalDate.of(2020, Month.FEBRUARY, 19);
        LocalDate bday = LocalDate.of(1986, Month.AUGUST, 5);
        System.out.println("You are " + ChronoUnit.DAYS.between(bday, now) + " days old.\n");

    }

    public void fridayTheThirteenth(){
        LocalDate now = LocalDate.of(2020, Month.FEBRUARY, 19);
        LocalDate then = LocalDate.of(1900, Month.JANUARY, 1);
        long years = ChronoUnit.YEARS.between(then, now);
        List<LocalDate> dates = then.datesUntil(now).collect(Collectors.toList());
        int nrFridays = 0;
        for (LocalDate date: dates){
            if(date.getDayOfMonth() == 13 && date.getDayOfWeek().getValue() == DayOfWeek.FRIDAY.getValue()){
                nrFridays++;
            }
        }
        System.out.println("Number of Friday the 13th's since 1900-01-01: " + nrFridays);
        double avgFridays = (double)nrFridays / years;
        System.out.println("Average number of Friday the 13th's per year: " + avgFridays + "\n");
    }

    public void eightyYears(){
        LocalDate now = LocalDate.of(2020, Month.FEBRUARY, 19);
        long nrDays = ChronoUnit.DAYS.between(now, now.plusYears(80));
        long minutesOnTheShitter = (nrDays * 27);
        double daysOnTheShitter = (double)(minutesOnTheShitter/60)/24;
        System.out.printf("Days spent on the shitter: %.2f\n\n", daysOnTheShitter);
    }

    public void depressed(){
        LocalDate now = LocalDate.of(2020, Month.FEBRUARY, 19);

        long nrDaysBeforeRetirement = ChronoUnit.DAYS.between(now, now.plusYears(65));
        long nrDaysAfterRetirement = ChronoUnit.DAYS.between(now.plusYears(65), now.plusYears(80));

        long totalDays = nrDaysAfterRetirement + nrDaysBeforeRetirement;
        long totalHours = totalDays * 24;

        long sleepingTime = totalDays * 8;

        long workDays = (nrDaysBeforeRetirement / 7) * 5;
        long workAndTravelTime = workDays * 9;

        double toilettBreakHours = (nrDaysBeforeRetirement * 24 * 0.02) + (nrDaysAfterRetirement * 24 * 0.04);
        double hygieneHours = ((double)nrDaysBeforeRetirement / 2) + nrDaysAfterRetirement;
        double cookingHours = ((double)nrDaysBeforeRetirement / 2) + nrDaysAfterRetirement;
        double cleaningHours = nrDaysBeforeRetirement + (nrDaysAfterRetirement * 2);

        double freeTimeHours = totalHours - toilettBreakHours - hygieneHours - cookingHours - cleaningHours - workAndTravelTime - sleepingTime;
        double percentFreeTime = (freeTimeHours / totalHours) * 100;
        double phoneTime = totalDays * 3;
        double freeTimeMinusPhoneTime = freeTimeHours - phoneTime;

        System.out.printf("Percent free time: %.2f percent\n", percentFreeTime);
        System.out.printf("Free time in hours: %.2f\n", freeTimeHours);
        System.out.printf("Free time after phone use: %.2f\nin percent: %.2f", freeTimeMinusPhoneTime
                , ((freeTimeMinusPhoneTime / totalHours)*100));
    }
}
