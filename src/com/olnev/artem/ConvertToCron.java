package com.olnev.artem;

import com.digdes.school.DatesToCronConvertException;

import java.text.SimpleDateFormat;
//import java.util.Date;

public class ConvertToCron {
    public static void main(String[] args) {
        getImplementationInfo();
    }

//      Скоро здесь будет решение

//    public static String convert(java.util.Date[] dates) throws DatesToCronConvertException {
//        SimpleDateFormat sdf = new SimpleDateFormat("ss-mm-hh-dd-MM-yyyy");
//        String formatTimeString;
//        try {
//            if (dates != null) {
//                for (java.util.Date date : dates) {
//                    date = sdf.format(date);
//                }
//            }
//        } catch (Exception e) {
//            throw new DatesToCronConvertException();
//        }
//        return formatTimeString = "";
//    }

    public static void getImplementationInfo() {
        String devName = "Olnev Artem Igorevich";
        String githubLink = "https://github.com/kidwithbeard/digdes-feb-22.git";
        ConvertToCron obj = new ConvertToCron();
        System.out.println(devName + "\n" +
                obj.getClass().getSimpleName() + "\n" +
                obj.getClass().getPackageName() + "\n" +
                githubLink);

//        SimpleDateFormat sdf = new SimpleDateFormat("ss mm hh dd MM yyyy");
//        System.out.println(sdf.format("2022-01-25T08:00:00"));
    }
}
