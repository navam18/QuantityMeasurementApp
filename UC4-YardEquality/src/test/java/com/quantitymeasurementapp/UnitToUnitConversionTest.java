package com.QuantityMeasurementApp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UnitToUnitConversionTest {

    private static final double EPS=1e-6;

    @Test
    void testConversion_FeetToInches(){

        double result=UnitToUnitConversion.QuantityLength.convert(1.0,UnitToUnitConversion.LengthUnit.FEET,UnitToUnitConversion.LengthUnit.INCHES);
        assertEquals(12.0,result,EPS);
    }

    @Test
    void testConversion_InchesToFeet(){

        double result=UnitToUnitConversion.QuantityLength.convert(24.0,UnitToUnitConversion.LengthUnit.INCHES,UnitToUnitConversion.LengthUnit.FEET);
        assertEquals(2.0,result,EPS);
    }

    @Test
    void testConversion_YardsToInches(){

        double result=UnitToUnitConversion.QuantityLength.convert(1.0,UnitToUnitConversion.LengthUnit.YARDS,UnitToUnitConversion.LengthUnit.INCHES);
        assertEquals(36.0,result,EPS);
    }

    @Test
    void testConversion_InchesToYards(){

        double result=UnitToUnitConversion.QuantityLength.convert(72.0,UnitToUnitConversion.LengthUnit.INCHES,UnitToUnitConversion.LengthUnit.YARDS);
        assertEquals(2.0,result,EPS);
    }

    @Test
    void testConversion_CentimetersToInches(){

        double result=UnitToUnitConversion.QuantityLength.convert(2.54,UnitToUnitConversion.LengthUnit.CENTIMETERS,UnitToUnitConversion.LengthUnit.INCHES);
        assertEquals(1.0,result,1e-3);
    }

    @Test
    void testConversion_FeatToYard(){

        double result=UnitToUnitConversion.QuantityLength.convert(6.0,UnitToUnitConversion.LengthUnit.FEET,UnitToUnitConversion.LengthUnit.YARDS);
        assertEquals(2.0,result,EPS);
    }

    @Test
    void testConversion_RoundTrip_PreservesValue(){

        double v=5.5;
        double step1=UnitToUnitConversion.QuantityLength.convert(v,UnitToUnitConversion.LengthUnit.FEET,UnitToUnitConversion.LengthUnit.INCHES);
        double step2=UnitToUnitConversion.QuantityLength.convert(step1,UnitToUnitConversion.LengthUnit.INCHES,UnitToUnitConversion.LengthUnit.FEET);
        assertEquals(v,step2,EPS);
    }

    @Test
    void testConversion_ZeroValue(){

        double result=UnitToUnitConversion.QuantityLength.convert(0.0,UnitToUnitConversion.LengthUnit.FEET,UnitToUnitConversion.LengthUnit.INCHES);
        assertEquals(0.0,result,EPS);
    }

    @Test
    void testConversion_NegativeValue(){

        double result=UnitToUnitConversion.QuantityLength.convert(-1.0,UnitToUnitConversion.LengthUnit.FEET,UnitToUnitConversion.LengthUnit.INCHES);
        assertEquals(-12.0,result,EPS);
    }

    @Test
    void testConversion_InvalidUnit_Throws(){

        assertThrows(IllegalArgumentException.class,()->{
            UnitToUnitConversion.QuantityLength.convert(1.0,null,UnitToUnitConversion.LengthUnit.FEET);
        });
    }

    @Test
    void testConversion_NaNOrInfinite_Throws(){

        assertThrows(IllegalArgumentException.class,()->{
            UnitToUnitConversion.QuantityLength.convert(Double.NaN,UnitToUnitConversion.LengthUnit.FEET,UnitToUnitConversion.LengthUnit.INCHES);
        });

        assertThrows(IllegalArgumentException.class,()->{
            UnitToUnitConversion.QuantityLength.convert(Double.POSITIVE_INFINITY,UnitToUnitConversion.LengthUnit.FEET,UnitToUnitConversion.LengthUnit.INCHES);
        });
    }

    @Test
    void testConversion_PrecisionTolerance(){

        double result=UnitToUnitConversion.QuantityLength.convert(1.0,UnitToUnitConversion.LengthUnit.CENTIMETERS,UnitToUnitConversion.LengthUnit.INCHES);
        assertEquals(0.393701,result,EPS);
    }
}