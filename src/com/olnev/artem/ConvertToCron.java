package com.olnev.artem;

import com.digdes.school.DatesToCronConvertException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

public class ConvertToCron {
    public static String[] input = {
            "2022-01-25T08:00:00",
            "2022-01-25T08:30:00",
            "2022-01-25T09:00:00",
            "2022-01-25T09:30:00",
            "2022-01-26T08:00:00",
            "2022-01-26T08:30:00",
            "2022-01-26T09:00:00",
            "2022-01-26T09:30:00"};
    public static Date[] dates = new Date[input.length];
    public static Long[] longDates = new Long[input.length];

    // HashMap, где key - разница между датами (в Long-формате), а value - как часто такая разница встречается
    public static HashMap<Long, Integer> differences = new HashMap<>();

    public static void main(String[] args) {
        getImplementationInfo();
        convert(input);
//        print();
    }

    // Скоро ниже будет решение

    // преобразование списка дат со временем в cron-формат
    public static void convert(String[] input) {
        try {
            stringsToDates(input);
            datesToLong(dates);
            Arrays.sort(longDates);
            calculateDateDifferences(longDates);
            requirement50percent();
            cronCreation(); // не написан
        } catch (DatesToCronConvertException e) {
            System.out.println("DatesToCronConvertException caught");
        }
    }

    // вывод информации о реализации интерфейса (ФИО, имя класса реализации, пакет, ссылка на github)
    public static void getImplementationInfo() {
        String devName = "Olnev Artem Igorevich";
        String githubLink = "https://github.com/kidwithbeard/digdes-feb-22.git";
        ConvertToCron obj = new ConvertToCron();
        System.out.println(devName + "\n" +
                obj.getClass().getSimpleName() + "\n" +
                obj.getClass().getPackageName() + "\n" +
                githubLink);
    }

    // парсинг строки в дату
    public static Date parseStringToDate(String str) throws DatesToCronConvertException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
        Date date;
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            System.out.println("Некорректный формат даты");
            throw new DatesToCronConvertException();
        }
        return date;
    }

    // преобразование массива строк в массив дат
    public static void stringsToDates(String[] strings) throws DatesToCronConvertException {
        for (int i = 0; i < strings.length; i++) {
            dates[i] = parseStringToDate(strings[i]);
        }
    }

    // преобразование массива дат в массив Long'ов
    public static void datesToLong(Date[] dates) {
        for (int i = 0; i < dates.length; i++) {
            longDates[i] = dates[i].getTime();
        }
    }

    // запись в HashMap разниц между датами
    public static void calculateDateDifferences(Long[] longDates) {
        if (longDates.length > 1) {
            for (int i = 1; i < longDates.length; i++) {
                long difference = dates[i].getTime() - dates[i - 1].getTime();
                if (differences.containsKey(difference)) {
                    differences.put(difference, differences.get(difference) + 1);
                } else {
                    differences.put(difference, 1);
                }
            }
        }
    }

    // проверка, что cron удовлетворяет не меньше половины дат (> 50%)
    public static void requirement50percent() throws DatesToCronConvertException {
        if (Collections.max(differences.values()) < input.length / 2) {
            System.out.println("Меньше половины дат имеют одинаковую периодичность");
            throw new DatesToCronConvertException();
        }
    }

    // формирование cron
    public static void cronCreation() {

    }

    // метод для проверки
    public static void print() throws DatesToCronConvertException {
        stringsToDates(input);
        for (Date date : dates) {
            System.out.println(date);
        }

        for (HashMap.Entry<Long, Integer> item : differences.entrySet()) {
            System.out.printf("Key: %s  Value: %s \n", item.getKey(), item.getValue());
        }
    }
}
