package Task;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TaskTest {
    Task task;

    // region setup
    @Before
    public void setUp() throws Exception {
        task = new Task();
    }

    @After
    public void tearDown() throws Exception {
    }
    // endregion

    // region getters
    @Test
    public void testGetPsd() throws Exception {
        int psd = task.getPsd();
        assertNotNull(psd);
    }

    @Test
    public void testGetPcd() throws Exception {
        int pcd = task.getPcd();
        assertNotNull(pcd);
    }

    @Test
    public void testGetAsd() throws Exception {
        int asd = task.getAsd();
        assertNotNull(asd);
    }

    @Test
    public void testGetAcd() throws Exception {
        int acd = task.getAcd();
        assertNotNull(acd);
    }
    // endregion

    // region setters

    // region planned start date
    @Test(expected = IllegalArgumentException.class)
    public void testSetPsd_negative() throws Exception {
        task.setPsd(-1);
    }

    @Test
    public void testSetPsd_positive_noPcd() throws Exception {
        int expected = 100;
        task.setPsd(expected);
        int actual = task.getPsd();
        assertEquals(expected, actual);
    }

    @Test
    public void testSetPsd_positive_lessThanPcd() throws Exception {
        task.setPcd(150);
        int expected = 100;
        task.setPsd(expected);
        int actual = task.getPsd();
        assertEquals(expected, actual);
    }

    @Test
    public void testSetPsd_positive_equalToPcd() throws Exception {
        int expected = 100;
        task.setPcd(expected);
        task.setPsd(expected);
        int actual = task.getPsd();
        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetPsd_positive_greaterThanPcd() throws Exception {
        task.setPcd(100);
        task.setPsd(150);
    }
    // endregion

    // region planned complete date
    @Test(expected = IllegalArgumentException.class)
    public void testSetPcd_negative() throws Exception {
        task.setPcd(-1);
    }

    @Test
    public void testSetPcd_positive_noPsd() throws Exception {
        int expected = 100;
        task.setPcd(expected);
        int actual = task.getPcd();
        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetPcd_positive_lessThanPsd() throws Exception {
        task.setPsd(150);
        task.setPcd(100);
    }

    @Test
    public void testSetPcd_positive_equalToPsd() throws Exception {
        int expected = 100;
        task.setPsd(expected);
        task.setPcd(expected);
        int actual = task.getPcd();
        assertEquals(expected, actual);
    }

    @Test
    public void testSetPcd_positive_greaterThanPsd() throws Exception {
        task.setPsd(50);
        int expected = 100;
        task.setPcd(expected);
        int actual = task.getPcd();
        assertEquals(expected, actual);
    }
    // endregion

    // region actual start date
    @Test(expected = IllegalArgumentException.class)
    public void testSetAsd_negative() throws Exception {
        task.setAsd(-1);
    }

    @Test
    public void testSetAsd_positive_noAcd() throws Exception {
        int expected = 100;
        task.setAsd(expected);
        int actual = task.getAsd();
        assertEquals(expected, actual);
    }

    @Test
    public void testSetAsd_positive_lessThanAcd() throws Exception {
        task.setAsd(1); // can't set acd prior to asd
        task.setAcd(150);
        int expected = 100;
        task.setAsd(expected);
        int actual = task.getAsd();
        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetAsd_positive_equalToAcd() throws Exception {
        task.setAsd(100);
        task.setAcd(100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetAsd_positive_greaterThanAcd() throws Exception {
        task.setAsd(150);
        task.setAcd(100);
    }
    // endregion

    // region actual complete date
    @Test(expected = IllegalArgumentException.class)
    public void testSetAcd_negative() throws Exception {
        task.setAcd(-1);
    }

    @Test(expected = IllegalStateException.class)
    public void testSetAcd_positive_noAsd() throws Exception {
        int expected = 100;
        task.setAcd(expected);
        int actual = task.getAcd();
        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetAcd_positive_lessThanAsd() throws Exception {
        task.setAsd(150);
        task.setAcd(100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetAcd_positive_equalToAsd() throws Exception {
        task.setAsd(100);
        task.setAcd(100);
    }

    @Test
    public void testSetAcd_positive_greaterThanAsd() throws Exception {
        task.setAsd(50);
        int expected = 100;
        task.setAcd(expected);
        int actual = task.getAcd();
        assertEquals(expected, actual);
    }
    // endregion

    // endregion

    // region requirements

    // region requirement 1
    @Test(expected = IllegalArgumentException.class)
    public void testRequirement1_psdNegative_noPcd() {
        task.setPsd(-1);
    }

    @Test
    public void testRequirement1_psdZero_noPcd() {
        int expected = 0;
        task.setPsd(expected);
        int actual = task.getPsd();
        assertEquals(expected, actual);
    }

    @Test
    public void testRequirement1_psdPositive_noPcd() {
        int expected = 10;
        task.setPsd(expected);
        int actual = task.getPsd();
        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequirement1_noPsd_pcdNegative() {
        task.setPcd(-1);
    }

    @Test
    public void testRequirement1_noPsd_pcdZero() {
        int expected = 0;
        task.setPcd(expected);
        int actual = task.getPcd();
        assertEquals(expected, actual);
    }

    @Test
    public void testRequirement1_noPsd_pcdPositive() {
        int expected = 10;
        task.setPcd(expected);
        int actual = task.getPcd();
        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequirement1_psdNegativeLess_pcdNegativeGreater() {
        task.setPsd(-2);
        task.setPcd(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequirement1_psdNegativeGreater_pcdNegativeLess() {
        task.setPsd(-1);
        task.setPcd(-2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequirement1_psdNegative_pcdPositive() {
        task.setPsd(-1);
        task.setPcd(1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequirement1_psdPositive_pcdNegative() {
        task.setPsd(1);
        task.setPcd(-1);
    }

    @Test
    public void testRequirement1_psdZero_pcdZero() {
        int expectedPsd = 0;
        int expectedPcd = 0;
        task.setPsd(expectedPsd);
        task.setPcd(expectedPcd);
        int actualPsd = task.getPsd();
        int actualPcd = task.getPcd();
        assertEquals(expectedPsd, actualPsd);
        assertEquals(expectedPcd, actualPcd);
        assertTrue(actualPsd == actualPcd);
    }

    @Test
    public void testRequirement1_psdPositiveEqual_pcdPositiveEqual() {
        int expectedPsd = 50;
        int expectedPcd = 50;
        task.setPsd(expectedPsd);
        task.setPcd(expectedPcd);
        int actualPsd = task.getPsd();
        int actualPcd = task.getPcd();
        assertEquals(expectedPsd, actualPsd);
        assertEquals(expectedPcd, actualPcd);
        assertTrue(actualPsd == actualPcd);
    }

    @Test
    public void testRequirement1_psdPositiveLess_pcdPositiveGreater() {
        int expectedPsd = 50;
        int expectedPcd = 100;
        task.setPsd(expectedPsd);
        task.setPcd(expectedPcd);
        int actualPsd = task.getPsd();
        int actualPcd = task.getPcd();
        assertEquals(expectedPsd, actualPsd);
        assertEquals(expectedPcd, actualPcd);
        assertTrue(actualPsd < actualPcd);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequirement1_psdPositiveGreater_pcdPositiveLess() {
        task.setPsd(100);
        task.setPcd(50);
    }
    // endregion

    // region requirement 2
    @Test(expected = IllegalStateException.class)
    public void testRequirement2_noAsd() {
        task.setAcd(100);
    }

    @Test
    public void testRequirement2_withAsd() {
        task.setAsd(150);
        int expected = 200;
        task.setAcd(expected);
        int actual = task.getAcd();
        assertEquals(expected, actual);
    }
    // endregion

    // region requirement 3
    @Test(expected = IllegalArgumentException.class)
    public void testRequirement3_asdNegative_noAcd() {
        task.setAsd(-1);
    }

    @Test
    public void testRequirement3_asdZero_noAcd() {
        int expected = 0;
        task.setAsd(expected);
        int actual = task.getAsd();
        assertEquals(expected, actual);
    }

    @Test
    public void testRequirement3_asdPositive_noAcd() {
        int expected = 10;
        task.setAsd(expected);
        int actual = task.getAsd();
        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequirement3_noAsd_acdNegative() {
        task.setAcd(-1);
    }

    @Test(expected = IllegalStateException.class)
    public void testRequirement3_noAsd_acdZero() {
        task.setAcd(0);
    }

    @Test(expected = IllegalStateException.class)
    public void testRequirement3_noAsd_acdPositive() {
        task.setAcd(10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequirement3_asdNegativeLess_acdNegativeGreater() {
        task.setAsd(-2);
        task.setAcd(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequirement3_asdNegativeGreater_acdNegativeLess() {
        task.setAsd(-1);
        task.setAcd(-2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequirement3_asdNegative_acdPositive() {
        task.setAsd(-1);
        task.setAcd(1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequirement3_asdPositive_acdNegative() {
        task.setAsd(1);
        task.setAcd(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequirement3_asdZero_acdZero() {
        task.setAsd(0);
        task.setAcd(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequirement3_asdPositiveEqual_acdPositiveEqual() {
        task.setAsd(50);
        task.setAcd(50);
    }

    @Test
    public void testRequirement3_asdPositiveLess_acdPositiveGreater() {
        int expectedAsd = 50;
        int expectedAcd = 100;
        task.setAsd(expectedAsd);
        task.setAcd(expectedAcd);
        int actualAsd = task.getAsd();
        int actualAcd = task.getAcd();
        assertEquals(expectedAsd, actualAsd);
        assertEquals(expectedAcd, actualAcd);
        assertTrue(actualAsd < actualAcd);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequirement3_asdPositiveGreater_acdPositiveLess() {
        task.setAsd(100);
        task.setAcd(50);
    }
    // endregion

    // endregion
}