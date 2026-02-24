package com.QuantityMeasurementApp;
import java.util.*;

public class UnitToUnitConversion {

    enum LengthUnit{

        FEET(1.0),
        INCHES(1.0/12.0),
        YARDS(3.0),
        CENTIMETERS(0.393701/12.0);

        private final double factorToFeet;

        LengthUnit(double factorToFeet){
            this.factorToFeet=factorToFeet;
        }

        double toFeet(double value){
            return value*factorToFeet;
        }

        double fromFeet(double feetValue){
            return feetValue/factorToFeet;
        }
    }

    static class QuantityLength{

        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value,LengthUnit unit){
            if(unit==null){
                throw new IllegalArgumentException("Unit cannot be null");
            }
            if(!Double.isFinite(value)){
                throw new IllegalArgumentException("Invalid numeric value");
            }
            this.value=value;
            this.unit=unit;
        }

        private double toBaseFeet(){
            return unit.toFeet(value);
        }

        public QuantityLength convertTo(LengthUnit targetUnit){
            double converted=convert(this.value,this.unit,targetUnit);
            return new QuantityLength(converted,targetUnit);
        }

        public static double convert(double value,LengthUnit source,LengthUnit target){

            if(source==null||target==null){
                throw new IllegalArgumentException("Unit cannot be null");
            }

            if(!Double.isFinite(value)){
                throw new IllegalArgumentException("Invalid value");
            }

            double baseFeet=source.toFeet(value);
            return target.fromFeet(baseFeet);
        }

        @Override
        public boolean equals(Object obj){

            if(this==obj){
                return true;
            }

            if(obj==null||getClass()!=obj.getClass()){
                return false;
            }

            QuantityLength other=(QuantityLength)obj;

            return Double.compare(this.toBaseFeet(),other.toBaseFeet())==0;
        }

        @Override
        public String toString(){
            return value+" "+unit;
        }
    }


    // Overload 1
    public static void demonstrateLengthConversion(double value,LengthUnit from,LengthUnit to){

        double result=QuantityLength.convert(value,from,to);
        System.out.println("convert("+value+", "+from+", "+to+") -> "+result);
    }

    // Overload 2
    public static void demonstrateLengthConversion(QuantityLength quantity,LengthUnit to){

        QuantityLength converted=quantity.convertTo(to);
        System.out.println(quantity+" -> "+converted);
    }

    // Equality demo
    public static void demonstrateLengthEquality(QuantityLength q1,QuantityLength q2){

        System.out.println(q1+" equals "+q2+" : "+q1.equals(q2));
    }

    public static void main(String[] args){

        Scanner scanner=new Scanner (System.in);

        System.out.println("Enter value to convert:");
        double value=scanner.nextDouble();

        System.out.println("Enter source unit (FEET/INCHES/YARDS/CENTIMETERS):");
        String sourceInput=scanner.next();

        System.out.println("Enter target unit (FEET/INCHES/YARDS/CENTIMETERS):");
        String targetInput=scanner.next();

        LengthUnit source=LengthUnit.valueOf(sourceInput.toUpperCase());
        LengthUnit target=LengthUnit.valueOf(targetInput.toUpperCase());

        double result=QuantityLength.convert(value,source,target);

        System.out.println("Converted Value: "+result);

        scanner.close();

    }
}