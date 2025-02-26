package by.com.lifetech.billingapi.utils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtils {
    public static long calculateDaysDifference(LocalDateTime firstDate, LocalDateTime secondDate) {
        return ChronoUnit.DAYS.between(firstDate, secondDate);
    }

    public static long calculateDaysDifference(Date firstDate, Date secondDate) {
        long diffInMillis = Math.abs(secondDate.getTime() - firstDate.getTime());
        return TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);
    }

    public static LocalDateTime offsetDays(LocalDateTime date, long days) {
        return days < 0 ? date.minusDays(Math.abs(days)) : date.plusDays(Math.abs(days));
    }

    public static Date offsetDays(Date date, int days) {
        return offset(date, days, Calendar.DAY_OF_YEAR);
    }

    public static Date offset(Date date, int offset, int type) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(type, offset);
        return calendar.getTime();
    }

    public static Date truncDateMilliseconds(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static LocalDateTime offsetYears(LocalDateTime date, long years) {
        return years < 0 ? date.minusYears(Math.abs(years)) : date.plusYears(Math.abs(years));
    }

    public static Date offsetYears(Date date, int years) {
        return offset(date, years, Calendar.YEAR);
    }

    public static LocalDateTime offsetByTwoDate(LocalDateTime fistDate, LocalDateTime secondDate, LocalDateTime  offsetDate) {
        return offsetByDuration(offsetDate, Duration.between(fistDate, secondDate));
    }

    public static LocalDateTime offsetByDuration(LocalDateTime  offsetDate, Duration duration) {
        return offsetDate.plus(duration);
    }
}
