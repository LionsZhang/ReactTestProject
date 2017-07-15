package app.reactproject.com.utill;


import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateUtil {
    private static String LOG_TAG = "DateUtil";
    private static String defaultDatePattern = null;
    private static String timePattern = "HH:mm";
    public static final String TS_FORMAT = DateUtil.getDatePattern()
            + " HH:mm:ss.S";
    private static Calendar cale = Calendar.getInstance();
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
    private static SimpleDateFormat sdf2 = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm");

    // ~ Methods
    // ================================================================

    public DateUtil() {
    }

    public static String getDateTime() {
        try {
            return sdf2.format(new Date());
        } catch (Exception e) {
            return "";
        }
    }

    public static String parseYYD(String dateStr) {//"2017-01-01 12:23:45";
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateStr);
            String fmt = new SimpleDateFormat("yyyy-MM-dd").format(date);
            return fmt;
        } catch (Exception e) {
            return "";
        }
    }

    public static String getDateTime(String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(new Date());
        } catch (Exception e) {
            return "";
        }
    }

    public static String formateDate(long s) {
        try {
            Date dt = new Date(s);
            return sdf2.format(dt);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 20161213 日期数字
     */
    public static long formateYMDDate(long s) {
        Date dt = new Date(s);
        String time = sdf.format(dt);
        return Long.valueOf(time.replaceAll("-", ""));

    }

    public static String formateDate(long s, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            Date dt = new Date(s);
            return sdf.format(dt);
        } catch (Exception e) {
            return "";
        }
    }

    public static String formateWeekOfDate(long s) {
        try {
            Date dt = new Date(s);
            String str = sdf.format(dt);
            str += " " + getWeekOfDate(dt);
            return str;
        } catch (Exception e) {
            return "";
        }
    }

    public static String getDate() {
        try {
            return sdf.format(cale.getTime());
        } catch (Exception e) {
            return "";
        }
    }

    public static String getTime() {
        String temp = " ";
        try {
            temp += sdf1.format(cale.getTime());
            return temp;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 统计时开始日期的默认值
     */
    public static String getStartDate() {
        try {
            return getYear() + "-01-01";
        } catch (Exception e) {
            return "";
        }
    }

    public static String getEndDate() {
        try {
            return getDate();
        } catch (Exception e) {
            return "";
        }
    }

    public static String getYear() {
        try {
            return String.valueOf(cale.get(Calendar.YEAR));
        } catch (Exception e) {
            return "";
        }
    }

    public static String getMonth() {
        try {
            java.text.DecimalFormat df = new java.text.DecimalFormat();
            df.applyPattern("00;00");
            return df.format((cale.get(Calendar.MONTH) + 1));
        } catch (Exception e) {
            return "";
        }
    }

    public static String getDay() {
        try {
            return String.valueOf(cale.get(Calendar.DAY_OF_MONTH));
        } catch (Exception e) {
            return "";
        }
    }

    public static int getMargin(String date1, String date2) {
        int margin;
        try {
            ParsePosition pos = new ParsePosition(0);
            ParsePosition pos1 = new ParsePosition(0);
            Date dt1 = sdf.parse(date1, pos);
            Date dt2 = sdf.parse(date2, pos1);
            long l = dt1.getTime() - dt2.getTime();
            margin = (int) (l / (24 * 60 * 60 * 1000));
            return margin;
        } catch (Exception e) {
            return 0;
        }
    }

    public static double getDoubleMargin(String date1, String date2) {
        double margin;
        try {
            ParsePosition pos = new ParsePosition(0);
            ParsePosition pos1 = new ParsePosition(0);
            Date dt1 = sdf2.parse(date1, pos);
            Date dt2 = sdf2.parse(date2, pos1);
            long l = dt1.getTime() - dt2.getTime();
            margin = (l / (24 * 60 * 60 * 1000.00));
            return margin;
        } catch (Exception e) {
            return 0;
        }
    }

    public static int getMonthMargin(String date1, String date2) {
        int margin;
        try {
            margin = (Integer.parseInt(date2.substring(0, 4)) - Integer
                    .parseInt(date1.substring(0, 4))) * 12;
            margin += (Integer.parseInt(date2.substring(4, 7).replaceAll("-0",
                    "-")) - Integer.parseInt(date1.substring(4, 7).replaceAll(
                    "-0", "-")));
            return margin;
        } catch (Exception e) {
            return 0;
        }
    }

    public static int getMaxDay(String year, String month) {
        int day = 0;
        try {
            int iyear = Integer.parseInt(year);
            int imonth = Integer.parseInt(month);
            if (imonth == 1 || imonth == 3 || imonth == 5 || imonth == 7
                    || imonth == 8 || imonth == 10 || imonth == 12) {
                day = 31;
            } else if (imonth == 4 || imonth == 6 || imonth == 9
                    || imonth == 11) {
                day = 30;
            } else if ((0 == (iyear % 4)) && (0 != (iyear % 100))
                    || (0 == (iyear % 400))) {
                day = 29;
            } else {
                day = 28;
            }
            return day;
        } catch (Exception e) {
            return 1;
        }
    }

    public static synchronized String getDatePattern() {
        defaultDatePattern = "yyyy-MM-dd";
        return defaultDatePattern;
    }

    public static final String getDate(Date aDate) {
        SimpleDateFormat df = null;
        String returnValue = "";

        if (aDate != null) {
            df = new SimpleDateFormat(getDatePattern());
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }

    public static String getTimeNow(Date theTime) {
        return getDateTime(timePattern, theTime);
    }

    public Calendar getToday() throws ParseException {
        Date today = new Date();
        SimpleDateFormat df = new SimpleDateFormat(getDatePattern());
        String todayAsString = df.format(today);
        Calendar cal = new GregorianCalendar();
        cal.setTime(convertStringToDate(todayAsString));
        return cal;
    }

    public static Calendar getCalendarday(String date) throws ParseException {
        Date today = convertStringToDate(date);
        SimpleDateFormat df = new SimpleDateFormat(getDatePattern());
        String todayAsString = df.format(today);
        Calendar cal = new GregorianCalendar();
        cal.setTime(convertStringToDate(todayAsString));
        return cal;
    }

    public static final String getDateTime(String aMask, Date aDate) {
        SimpleDateFormat df = null;
        String returnValue = "";

        if (aDate == null) {
        } else {
            df = new SimpleDateFormat(aMask);
            returnValue = df.format(aDate);
        }
        return (returnValue);
    }

    public static final String convertDateToString(Date aDate) {
        return getDateTime(getDatePattern(), aDate);
    }

    public static final String convertDateToString(String aMask, Date aDate) {
        return getDateTime(aMask, aDate);
    }

    public static final Date convertStringToDate(String aMask, String strDate)
            throws ParseException {
        SimpleDateFormat df = null;
        Date date = null;
        df = new SimpleDateFormat(aMask);


        try {
            date = df.parse(strDate);
        } catch (ParseException pe) {
            throw pe;
        }
        return (date);
    }

    public static Date convertStringToDate(String strDate)
            throws ParseException {
        Date aDate = null;

        try {

            aDate = convertStringToDate(getDatePattern(), strDate);
        } catch (ParseException pe) {

            throw new ParseException(pe.getMessage(), pe.getErrorOffset());

        }

        return aDate;
    }


    public static String convertDateStringFormat(String strDate, String fromFormat, String toFormat) {
        String date = null;
        try {
            Date aDate = convertStringToDate(fromFormat, strDate);
            date = convertDateToString(toFormat, aDate);
        } catch (ParseException pe) {
            pe.printStackTrace();
        }
        return date;
    }


    public static String getSimpleDateFormat() {
        SimpleDateFormat formatter = new SimpleDateFormat();
        String NDateTime = formatter.format(new Date());
        return NDateTime;
    }

    public static boolean compareTo(String last, String now) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss");
            Date temp1 = formatter.parse(last);
            Date temp2 = formatter.parse(now);
            if (temp1.after(temp2))
                return false;
            else if (temp1.before(temp2))
                return true;
        } catch (ParseException e) {
        }
        return false;
    }

    public static String getMonthLastDay(int year, int month) {
        int[][] day = {{0, 30, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31},
                {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}};
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
            return day[1][month] + "";
        } else {
            return day[0][month] + "";
        }
    }

    @SuppressWarnings("deprecation")
    public static String getTimestamp() {
        Date date = new Date();
        String timestamp = "" + (date.getYear() + 1900) + date.getMonth()
                + date.getDate() + date.getMinutes() + date.getSeconds()
                + date.getTime();
        return timestamp;
    }

    @SuppressWarnings("deprecation")
    public static String getTimestamp(Date date) {
        String timestamp = "" + (date.getYear() + 1900) + date.getMonth()
                + date.getDate() + date.getMinutes() + date.getSeconds()
                + date.getTime();
        return timestamp;
    }


    public static int getDaOfMonth(Date date) {
        cale.setTime(date);
        return cale.get(Calendar.DATE);
    }

    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;

        return weekDays[w];
    }

    public static String getWeek(String wfmt, String dateStr) {
        Date d = null;
        try {
            d = convertStringToDate(wfmt, dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return getWeekOfDate(d);
    }

    public final static String[] monthArr = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};

    public static double[] getCurrentMonthDatesDoubleArray() {
        String currentDate = DateUtil.getDate();
        return getMonthDatesDoubleArray(currentDate);
    }

    public static double[] getMonthDatesDoubleArray(String dateString) {
        int maxDay = DateUtil.getMaxDay(dateString.substring(0, 4), dateString.substring(5, 7));

        double[] dates = new double[maxDay];
        for (int i = 0; i < maxDay; i++) {
            dates[i] = (i + 1);
        }

        return dates;
    }

    public static String[] getCurrentMonthDatesStringArray() {
        String currentDate = DateUtil.getDate();
        int maxDay = DateUtil.getMaxDay(currentDate.substring(0, 4), currentDate.substring(5, 7));

        String[] dates = new String[maxDay];
        for (int i = 0; i < maxDay; i++) {
            String DateTemp = i < 10 ? ("0" + (i + 1)) : ("" + (i + 1));
            dates[i] = DateTemp;
        }

        return dates;
    }


    public static String getRefreshTime() {
        return new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA).format(new Date());
    }


}
