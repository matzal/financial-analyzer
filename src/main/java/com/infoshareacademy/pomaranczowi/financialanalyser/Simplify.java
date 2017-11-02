package com.infoshareacademy.pomaranczowi.financialanalyser;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

public class Simplify {

    static HashSet<Integer> year = new HashSet<>();
    static HashSet<Integer> month = new HashSet<>();

    static Integer yearSelected;
    static Integer monthSelected;
    static ArrayList<Weeks> week = new ArrayList<>();

    public static void periodYear(Quotation quotation) {
        System.out.println("Upraszczanie danych finansowych. Dostępne są notowania z poniższych lat.\n" +
                "Wybierz z poniższego zestawu rok z którego chcesz otrzymać dane:");
        getYear(quotation);
        System.out.println(year);

        boolean dataOk = false;
//--CHOICE YEAR
        while (!dataOk) {
            try {
                Scanner scanner = new Scanner(System.in);
                yearSelected = scanner.nextInt();
                if (year.contains(yearSelected)) {
                    dataOk = true;
                } else {
                    System.out.println("Wprowadzony rok nie jest jednym z listy\n" +
                            "Wybierz z poniższego zestawu rok z którego chcesz otrzymać dane:");
                    System.out.println(year);
                }
            } catch (InputMismatchException exception) {
                System.out.println("Wprowadź proszę rok w formacie 4 cyfr z poniższej listy");
                System.out.println(year);
            }
        }
        LocalDate date = LocalDate.of(yearSelected, 1, 1);
        System.out.println("Dla roku " + yearSelected + ":");
        result(quotation, date.with(TemporalAdjusters.firstDayOfYear()), date.with(TemporalAdjusters.lastDayOfYear()));

        System.out.println("\nCzy chcesz otrzymać dane uproszczone (dla miesięcy) z roku " + yearSelected + " ?\n" +
                "Jeżeli tak -> wciśnij 'T', jeżeli chcesz wyjść -> wcisnij 'N'");
        dataOk = false;
        while (!dataOk) {
            Scanner scanner = new Scanner(System.in);
            String answer = scanner.nextLine().toUpperCase();
            if (answer.equals("T")) {
                periodMonth(quotation,yearSelected);
                dataOk = true;
            } else if (answer.equals("N")) {
                dataOk = true;
            } else System.out.println("Wprowadź odpowiedź T lub N");
        }
    }

    public static void periodMonth(Quotation quotation,Integer yearSelected) {
        boolean dataOk = false;
        getMonthsForYear(quotation,yearSelected);
        for (int i : month) {
            LocalDate date = LocalDate.of(yearSelected,i,1);
            System.out.println(date.getMonth().getDisplayName(TextStyle.FULL_STANDALONE, Locale.forLanguageTag("pl-PL")));
            result(quotation,date.with(TemporalAdjusters.firstDayOfMonth()),date.with(TemporalAdjusters.lastDayOfMonth()));
        }

        System.out.println("\nCzy chcesz otrzymać dane uproszczone  (dla tygodni) w ramach jednego z miesięcy w roku " + yearSelected + " ?\n" +
                "Jeżeli tak -> wciśnij 'T', jeżeli chcesz wyjść -> wcisnij 'N'");
        dataOk = false;
        boolean data1Ok= false;
        while (!dataOk) {
            Scanner scanner = new Scanner(System.in);
            String answer = scanner.nextLine().toUpperCase();
            if (answer.equals("T")) {
                System.out.println("Wybierz jeden z poniższych miesięcy dostępnych w ramach roku "+yearSelected );
                System.out.println(month);
                while (!data1Ok) {
                    try {
                        Scanner scanner1 = new Scanner(System.in);
                        monthSelected = scanner1.nextInt();
                        if (month.contains(monthSelected)) {
                            data1Ok = true;
                            periodWeek(quotation,yearSelected,monthSelected);
                        } else {
                            System.out.println("Wprowadzony mc nie jest jednym z listy\n" +
                                    "Wybierz jeden z poniższych miesięcy dla którego chcesz otrzymać dane:");
                            System.out.println(month);
                        }
                    } catch (InputMismatchException exception) {
                        System.out.println("Wprowadź proszę miesiąc w formacie cyfry z poniższej listy");
                        System.out.println(month);
                    }
                }
               dataOk = true;
            } else if (answer.equals("N")) {
                dataOk = true;
            } else System.out.println("Wprowadź odpowiedź T lub N");
        }
    }

    public static void periodWeek(Quotation quotation,Integer yearSelected,Integer monthSelected){
        LocalDate date = LocalDate.of(yearSelected,monthSelected,1);
        getWeeksForMonth(date);
        for(Weeks x :week){
            System.out.println("Tydzień "+x.getWeek()+" (od:"+x.getFrom()+" do:"+x.getTo()+")");
            result(quotation,x.getFrom(),x.getTo());
        }
    }

//--- supporting methods - can be exported to external class
    public static void getYear(Quotation quotation) {
        year.clear();
        for (Price x : quotation.getPrices()) {
            year.add(x.getDate().getYear());
        }
    }
    public static void getMonthsForYear(Quotation quotation,Integer yearSelected ) {
        month.clear();
        for (Price x : quotation.getPrices()) {
            if (x.getDate().getYear() == yearSelected)
                month.add(x.getDate().getMonthValue());
        }
    }
    public static void result(Quotation quotation, LocalDate from, LocalDate to) {

        GetLocalExt.setStartDate(from);
        GetLocalExt.setEndDate(to);
        GetLocalExt.ShowAll(quotation);
        /*
        if (GetLocalExt.getMax(quotation, from, to, GetLocalExt.ExtremesParams.OPEN).getDate()==null){
            System.out.println("brak danych dla okresu");
        }else {
            System.out.println("Max Open to: " +
                    GetLocalExt.getMax(quotation, from, to, GetLocalExt.ExtremesParams.OPEN).getValue() +
                    " z dnia: " + GetLocalExt.getMax(quotation, from, to, GetLocalExt.ExtremesParams.OPEN).getDate() +
                    "\nMin Open to: " +
                    GetLocalExt.getMin(quotation, from, to, GetLocalExt.ExtremesParams.OPEN).getValue() +
                    " z dnia: " + GetLocalExt.getMin(quotation, from, to, GetLocalExt.ExtremesParams.OPEN).getDate());
        }
        */
    }
    public static void  getWeeksForMonth(LocalDate date) {

        LocalDate lastDateOfMonth = date.with(TemporalAdjusters.lastDayOfMonth());
        boolean dataOk = false;
        LocalDate firstMonday = date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));

        Calendar calendar = Calendar.getInstance(); //set calendar to first day of week
        calendar.set(Calendar.YEAR,firstMonday.getYear());
        calendar.set(Calendar.MONTH,firstMonday.getMonthValue()-1);
        calendar.set(Calendar.DATE,firstMonday.getDayOfMonth());

        int countWeeksInYear = calendar.getActualMaximum(Calendar.WEEK_OF_YEAR); //quantity of weeks in Year can be between 52-53

        int currWeek = calendar.get(Calendar.WEEK_OF_YEAR); //set first nr of week in selected month to variable

        LocalDate lastDayOfWeek=firstMonday.plusDays(6);

        while (!dataOk) {
            Weeks weeks = new Weeks();
            weeks.setWeek(currWeek);
            weeks.setFrom(date);
            weeks.setTo(lastDayOfWeek);
            week.add(weeks);

            currWeek=currWeek==countWeeksInYear? 1:currWeek+1;

            if(lastDayOfWeek.plusDays(1).isAfter(lastDateOfMonth)){
                dataOk=true;
                break;
            }else {
                date=lastDayOfWeek.plusDays(1);
                if (date.plusDays(6).isAfter(lastDateOfMonth)){
                    lastDayOfWeek=lastDateOfMonth;
                }else lastDayOfWeek=date.plusDays(6);
            }
        }
    }
//--- end of supporting menthods
}

//example of use:
//Simplify.periodYear(aud);

