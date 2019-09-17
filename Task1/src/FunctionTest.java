import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FunctionTest{

    private static final double EPS  = 1E-6;
    private static final double DELTA = 1E-5;

    @Test
    public void testFuncNaN(){
        double x = Double.NaN;
        assertEquals(Math.asin(x), Functions.arcsin(x, EPS), DELTA);
    }

    @Test
    public void testFuncMinusOne(){
        double x = -1;
        assertEquals(Math.asin(x), Functions.arcsin(x, EPS), DELTA);
    }

    @Test
    public void testFuncMinusOneDelta(){
        double x = -1 + DELTA;
        assertEquals(Math.asin(x), Functions.arcsin(x, EPS), DELTA);
    }

    @Test
    public void testFuncMinusOneMDelta(){
        double x = -1 - DELTA;
        assertEquals(Math.asin(x), Functions.arcsin(x, EPS), DELTA);
    }

    @Test
    public void testFuncMinusBorder(){
        double x = -0.96;
        assertEquals(Math.asin(x), Functions.arcsin(x, EPS), DELTA);
    }

    @Test
    public void testFuncMinusBorderDelta(){
        double x = -0.96 + DELTA;
        assertEquals(Math.asin(x), Functions.arcsin(x, EPS), DELTA);
    }

    @Test
    public void testFuncMinusBorderMDelta(){
        double x = -0.96 - DELTA;
        assertEquals(Math.asin(x), Functions.arcsin(x, EPS), DELTA);
    }

    @Test
    public void testFuncZero(){
        double x = 0;
        assertEquals(Math.asin(x), Functions.arcsin(x, EPS), DELTA);
    }

    @Test
    public void testFuncZeroDelta(){
        double x = DELTA;
        assertEquals(Math.asin(x), Functions.arcsin(x, EPS), DELTA);
    }

    @Test
    public void testFuncZeroMDelta(){
        double x = -DELTA;
        assertEquals(Math.asin(x), Functions.arcsin(x, EPS), DELTA);
    }

    @Test
    public void testFuncBorder(){
        double x = 0.96;
        assertEquals(Math.asin(x), Functions.arcsin(x, EPS), DELTA);
    }

    @Test
    public void testFuncBorderDelta(){
        double x = 0.96 + DELTA;
        assertEquals(Math.asin(x), Functions.arcsin(x, EPS), DELTA);
    }

    @Test
    public void testFuncBorderMDelta(){
        double x = 0.96 - DELTA;
        assertEquals(Math.asin(x), Functions.arcsin(x, EPS), DELTA);
    }

    @Test
    public void testFuncOne(){
        double x = 1;
        assertEquals(Math.asin(x), Functions.arcsin(x, EPS), DELTA);
    }

    @Test
    public void testFuncOneDelta(){
        double x = 1 + DELTA;
        assertEquals(Math.asin(x), Functions.arcsin(x, EPS), DELTA);
    }

    @Test
    public void testFuncOneMDelta(){
        double x = 1 - DELTA;
        assertEquals(Math.asin(x), Functions.arcsin(x, EPS), DELTA);
    }

    @Test
    public void testFunc1Zone1(){
        double x = Double.NEGATIVE_INFINITY;
        assertEquals(Math.asin(x), Functions.arcsin(x, EPS), DELTA);
    }

    @Test
    public void testFunc1Zone2(){
        double x = -7;
        assertEquals(Math.asin(x), Functions.arcsin(x, EPS), DELTA);
    }

    @Test
    public void testFunc2Zone1(){
        double x = -0.9873;
        assertEquals(Math.asin(x), Functions.arcsin(x, EPS), DELTA);
    }

    @Test
    public void testFunc2Zone2(){
        double x = -0.96222;
        assertEquals(Math.asin(x), Functions.arcsin(x, EPS), DELTA);
    }

    @Test
    public void testFunc3Zone1(){
        double x = -0.5;
        assertEquals(Math.asin(x), Functions.arcsin(x, EPS), DELTA);
    }

    @Test
    public void testFunc3Zone2(){
        double x = -0.001;
        assertEquals(Math.asin(x), Functions.arcsin(x, EPS), DELTA);
    }

    @Test
    public void testFunc4Zone1(){
        double x = 0.07;
        assertEquals(Math.asin(x), Functions.arcsin(x, EPS), DELTA);
    }

    @Test
    public void testFunc4Zone2(){
        double x = 0.933333;
        assertEquals(Math.asin(x), Functions.arcsin(x, EPS), DELTA);
    }

    @Test
    public void testFunc5Zone1(){
        double x = 0.96123123;
        double xx = Math.asin(x);
        assertEquals(Math.asin(x), Functions.arcsin(x, EPS), DELTA);
    }

    @Test
    public void testFunc5Zone2(){
        double x = 0.99;
        assertEquals(Math.asin(x), Functions.arcsin(x, EPS), DELTA);
    }

    @Test
    public void testFunc6Zone1(){
        double x = Double.POSITIVE_INFINITY;
        assertEquals(Math.asin(x), Functions.arcsin(x, EPS), DELTA);
    }

    @Test
    public void testFunc6Zone2(){
        double x = 2;
        assertEquals(Math.asin(x), Functions.arcsin(x, EPS), DELTA);
    }
}
