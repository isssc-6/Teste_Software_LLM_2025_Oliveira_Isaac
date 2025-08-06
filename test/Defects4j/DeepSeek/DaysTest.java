import org.joda.time.*;
import org.joda.time.format.PeriodFormatter;
import org.junit.Test;
import static org.junit.Assert.*;

public class DaysTest {

    @Test
    public void testDays_int() {
        Days days = Days.days(5);
        assertEquals(5, days.getDays());
    }

    @Test
    public void testDaysBetween_ReadableInstant() {
        DateTime start = new DateTime(2023, 1, 1, 0, 0);
        DateTime end = new DateTime(2023, 1, 6, 0, 0);
        Days days = Days.daysBetween(start, end);
        assertEquals(5, days.getDays());
    }

    @Test
    public void testDaysBetween_ReadablePartial() {
        LocalDate start = new LocalDate(2023, 1, 1);
        LocalDate end = new LocalDate(2023, 1, 6);
        Days days = Days.daysBetween(start, end);
        assertEquals(5, days.getDays());
    }

    @Test
    public void testDaysIn_ReadableInterval() {
        DateTime start = new DateTime(2023, 1, 1, 0, 0);
        DateTime end = new DateTime(2023, 1, 6, 0, 0);
        Interval interval = new Interval(start, end);
        Days days = Days.daysIn(interval);
        assertEquals(5, days.getDays());
    }

    @Test
    public void testStandardDaysIn() {
        Period period = new Period(0, 0, 0, 5, 0, 0, 0, 0);
        Days days = Days.standardDaysIn(period);
        assertEquals(5, days.getDays());
    }

    @Test
    public void testParseDays() {
        Days days = Days.parseDays("P5D");
        assertEquals(5, days.getDays());
    }

    @Test
    public void testGetFieldType() {
        Days days = Days.days(5);
        assertEquals(DurationFieldType.days(), days.getFieldType());
    }

    @Test
    public void testGetPeriodType() {
        Days days = Days.days(5);
        assertEquals(PeriodType.days(), days.getPeriodType());
    }

    @Test
    public void testToStandardWeeks() {
        Days days = Days.days(14);
        Weeks weeks = days.toStandardWeeks();
        assertEquals(2, weeks.getWeeks());
    }

    @Test
    public void testToStandardHours() {
        Days days = Days.days(2);
        Hours hours = days.toStandardHours();
        assertEquals(48, hours.getHours());
    }

    @Test
    public void testToStandardMinutes() {
        Days days = Days.days(1);
        Minutes minutes = days.toStandardMinutes();
        assertEquals(1440, minutes.getMinutes());
    }

    @Test
    public void testToStandardSeconds() {
        Days days = Days.days(1);
        Seconds seconds = days.toStandardSeconds();
        assertEquals(86400, seconds.getSeconds());
    }

    @Test
    public void testToStandardDuration() {
        Days days = Days.days(1);
        Duration duration = days.toStandardDuration();
        assertEquals(86400000L, duration.getMillis());
    }

    @Test
    public void testGetDays() {
        Days days = Days.days(5);
        assertEquals(5, days.getDays());
    }

    @Test
    public void testPlus_int() {
        Days days = Days.days(5).plus(3);
        assertEquals(8, days.getDays());
    }

    @Test
    public void testPlus_Days() {
        Days days1 = Days.days(5);
        Days days2 = Days.days(3);
        Days result = days1.plus(days2);
        assertEquals(8, result.getDays());
    }

    @Test
    public void testMinus_int() {
        Days days = Days.days(5).minus(3);
        assertEquals(2, days.getDays());
    }

    @Test
    public void testMinus_Days() {
        Days days1 = Days.days(5);
        Days days2 = Days.days(3);
        Days result = days1.minus(days2);
        assertEquals(2, result.getDays());
    }

    @Test
    public void testMultipliedBy() {
        Days days = Days.days(5).multipliedBy(3);
        assertEquals(15, days.getDays());
    }

    @Test
    public void testDividedBy() {
        Days days = Days.days(6).dividedBy(2);
        assertEquals(3, days.getDays());
    }

    @Test
    public void testNegated() {
        Days days = Days.days(5).negated();
        assertEquals(-5, days.getDays());
    }

    @Test
    public void testIsGreaterThan() {
        Days days1 = Days.days(5);
        Days days2 = Days.days(3);
        assertTrue(days1.isGreaterThan(days2));
        assertFalse(days2.isGreaterThan(days1));
    }

    @Test
    public void testIsLessThan() {
        Days days1 = Days.days(3);
        Days days2 = Days.days(5);
        assertTrue(days1.isLessThan(days2));
        assertFalse(days2.isLessThan(days1));
    }

    @Test
    public void testToString() {
        Days days = Days.days(5);
        assertEquals("P5D", days.toString());
    }

    @Test
    public void testConstants() {
        assertEquals(0, Days.ZERO.getDays());
        assertEquals(1, Days.ONE.getDays());
        assertEquals(2, Days.TWO.getDays());
        assertEquals(3, Days.THREE.getDays());
        assertEquals(4, Days.FOUR.getDays());
        assertEquals(5, Days.FIVE.getDays());
        assertEquals(6, Days.SIX.getDays());
        assertEquals(7, Days.SEVEN.getDays());
        assertEquals(Integer.MAX_VALUE, Days.MAX_VALUE.getDays());
        assertEquals(Integer.MIN_VALUE, Days.MIN_VALUE.getDays());
    }
}