import org.joda.time.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class MonthsTest {

    @Test
    public void testMonths_int() {
        Months months = Months.months(5);
        assertEquals(5, months.getMonths());
    }

    @Test
    public void testMonthsBetween_ReadableInstant() {
        DateTime start = new DateTime(2023, 1, 1, 0, 0);
        DateTime end = new DateTime(2023, 6, 1, 0, 0);
        Months months = Months.monthsBetween(start, end);
        assertEquals(5, months.getMonths());
    }

    @Test
    public void testMonthsBetween_ReadablePartial() {
        LocalDate start = new LocalDate(2023, 1, 1);
        LocalDate end = new LocalDate(2023, 6, 1);
        Months months = Months.monthsBetween(start, end);
        assertEquals(5, months.getMonths());
    }

    @Test
    public void testMonthsIn_ReadableInterval() {
        DateTime start = new DateTime(2023, 1, 1, 0, 0);
        DateTime end = new DateTime(2023, 6, 1, 0, 0);
        Interval interval = new Interval(start, end);
        Months months = Months.monthsIn(interval);
        assertEquals(5, months.getMonths());
    }

    @Test
    public void testParseMonths() {
        Months months = Months.parseMonths("P5M");
        assertEquals(5, months.getMonths());
    }

    @Test
    public void testGetFieldType() {
        Months months = Months.months(5);
        assertEquals(DurationFieldType.months(), months.getFieldType());
    }

    @Test
    public void testGetPeriodType() {
        Months months = Months.months(5);
        assertEquals(PeriodType.months(), months.getPeriodType());
    }

    @Test
    public void testGetMonths() {
        Months months = Months.months(5);
        assertEquals(5, months.getMonths());
    }

    @Test
    public void testPlus_int() {
        Months months = Months.months(5).plus(3);
        assertEquals(8, months.getMonths());
    }

    @Test
    public void testPlus_Months() {
        Months months1 = Months.months(5);
        Months months2 = Months.months(3);
        Months result = months1.plus(months2);
        assertEquals(8, result.getMonths());
    }

    @Test
    public void testMinus_int() {
        Months months = Months.months(5).minus(3);
        assertEquals(2, months.getMonths());
    }

    @Test
    public void testMinus_Months() {
        Months months1 = Months.months(5);
        Months months2 = Months.months(3);
        Months result = months1.minus(months2);
        assertEquals(2, result.getMonths());
    }

    @Test
    public void testMultipliedBy() {
        Months months = Months.months(5).multipliedBy(3);
        assertEquals(15, months.getMonths());
    }

    @Test
    public void testDividedBy() {
        Months months = Months.months(6).dividedBy(2);
        assertEquals(3, months.getMonths());
    }

    @Test
    public void testNegated() {
        Months months = Months.months(5).negated();
        assertEquals(-5, months.getMonths());
    }

    @Test
    public void testIsGreaterThan() {
        Months months1 = Months.months(5);
        Months months2 = Months.months(3);
        assertTrue(months1.isGreaterThan(months2));
        assertFalse(months2.isGreaterThan(months1));
    }

    @Test
    public void testIsLessThan() {
        Months months1 = Months.months(3);
        Months months2 = Months.months(5);
        assertTrue(months1.isLessThan(months2));
        assertFalse(months2.isLessThan(months1));
    }

    @Test
    public void testToString() {
        Months months = Months.months(5);
        assertEquals("P5M", months.toString());
    }

    @Test
    public void testConstants() {
        assertEquals(0, Months.ZERO.getMonths());
        assertEquals(1, Months.ONE.getMonths());
        assertEquals(2, Months.TWO.getMonths());
        assertEquals(3, Months.THREE.getMonths());
        assertEquals(4, Months.FOUR.getMonths());
        assertEquals(5, Months.FIVE.getMonths());
        assertEquals(6, Months.SIX.getMonths());
        assertEquals(7, Months.SEVEN.getMonths());
        assertEquals(8, Months.EIGHT.getMonths());
        assertEquals(9, Months.NINE.getMonths());
        assertEquals(10, Months.TEN.getMonths());
        assertEquals(11, Months.ELEVEN.getMonths());
        assertEquals(12, Months.TWELVE.getMonths());
        assertEquals(Integer.MAX_VALUE, Months.MAX_VALUE.getMonths());
        assertEquals(Integer.MIN_VALUE, Months.MIN_VALUE.getMonths());
    }

    @Test
    public void testEdgeCases() {
        // Test month calculations across year boundaries
        LocalDate start = new LocalDate(2022, 11, 1);
        LocalDate end = new LocalDate(2023, 2, 1);
        Months months = Months.monthsBetween(start, end);
        assertEquals(3, months.getMonths());

        // Test month calculations with different month lengths
        LocalDate startShort = new LocalDate(2023, 1, 31); // January has 31 days
        LocalDate endFeb = new LocalDate(2023, 2, 28);     // February has 28 days (2023 not leap year)
        Months monthsDiff = Months.monthsBetween(startShort, endFeb);
        assertEquals(1, monthsDiff.getMonths());
    }

    @Test(expected = ArithmeticException.class)
    public void testOverflow() {
        Months.months(Integer.MAX_VALUE).plus(1);
    }

    @Test(expected = ArithmeticException.class)
    public void testUnderflow() {
        Months.months(Integer.MIN_VALUE).minus(1);
    }
}