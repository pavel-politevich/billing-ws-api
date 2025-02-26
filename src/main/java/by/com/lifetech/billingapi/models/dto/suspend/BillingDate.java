package by.com.lifetech.billingapi.models.dto.suspend;

import by.com.lifetech.billingapi.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BillingDate {
    @JsonIgnore
    public final static String DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS+03:00";
    @JsonIgnore
    public final static String DEFAULT_END_DATE = "2038-01-01T02:59:59+03:00";
    public final static String DEFAULT_START_DATE = "1970-01-01T03:00:00+03:00";
    private String startDate;
    private String endDate;

    public static String dateToBillingStringWithoutMillis(Date date) {
        return dateToBillingString(DateUtils.truncDateMilliseconds(date));
    }

    public static String dateToBillingStringWithoutMillis(LocalDateTime dateTime) {
        return dateToBillingString(dateTime.withNano(0));
    }

    private static String dateToBillingString(Date date) {
        return getBillingSimpleDateFormat().format(date);
    }

    private static String dateToBillingString(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        return dateTime.format(formatter);
    }

    public static Date billingStringToDateWithoutMillis(String date) throws ParseException {
        return DateUtils.truncDateMilliseconds(billingStringToDate(date));
    }

    public static LocalDateTime billingStringToLocalDateTimeWithoutMillis(String date) throws DateTimeParseException {
        return billingStringToLocalDateTime(date).withNano(0);
    }

    private static Date billingStringToDate(String date) throws ParseException {
        return getBillingSimpleDateFormat().parse(date);
    }

    private static LocalDateTime billingStringToLocalDateTime(String date) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        return LocalDateTime.parse(date, formatter);
    }

    public static SimpleDateFormat getBillingSimpleDateFormat() {
        return new SimpleDateFormat(DATE_PATTERN);
    }
}
