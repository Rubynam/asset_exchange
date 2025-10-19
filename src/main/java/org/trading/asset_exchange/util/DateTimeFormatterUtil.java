package org.trading.asset_exchange.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class DateTimeFormatterUtil {

  private DateTimeFormatterUtil() {
    //nothing to do here
  }

  public static final String YYYY_MM_DD = "yyyy-MM-dd";

  public static String getFormattedDate(LocalDate localDate, String format) {
    DateTimeFormatter fmt = DateTimeFormatter.ofPattern(format);
    return localDate.format(fmt);
  }

  public static String getFormattedDate(LocalDateTime localDate, String format) {
    DateTimeFormatter fmt = DateTimeFormatter.ofPattern(format);
    return localDate.format(fmt);
  }

  public static LocalDate now() {
    return LocalDate.now();
  }
}
