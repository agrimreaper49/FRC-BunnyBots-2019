package us.ilite.common.lib.util;

import us.ilite.common.config.SystemSettings;

public class Conversions {
    
    public static double rotationsToInches(double rotations) {
        return rotations * (SystemSettings.kDriveWheelDiameterInches * Math.PI);
    }

    public static double rpmToInchesPerSecond(double rpm) {
        return rotationsToInches(rpm) / 60;
    }

    public static double inchesToRotations(double inches) {
        return inches / SystemSettings.kDriveWheelCircumference;
    }

    public static double inchesPerSecondToRpm(double inches_per_second) {
        return inchesToRotations(inches_per_second) * 60;
    }

    public static double radiansPerSecondToTicksPer100ms(double rad_s) {
        return rad_s / (Math.PI * 2.0) * SystemSettings.kDriveTicksPerRotation / SystemSettings.kDriveVelTimeScale;
    }

    public static double ticksToRotations(double ticks) {
        return ticks / SystemSettings.kDriveTicksPerRotation;
    }

    public static double ticksToInches(double ticks) {
        return ticksToRotations(ticks) * SystemSettings.kDriveWheelCircumference;
    }

    public static int inchesToTicks(double inches) {
        return (int)(inchesToRotations(inches) * SystemSettings.kDriveTicksPerRotation);
    }

    public static double ticksPerTimeUnitToRotationsPerSecond(double ticks) {
        return ticks / SystemSettings.kDriveTicksPerRotation * SystemSettings.kDriveVelTimeScale;
    }

    public static double ticksPerTimeUnitToInchesPerSecond(double ticks) {
        return ticksPerTimeUnitToRotationsPerSecond(ticks) * SystemSettings.kDriveWheelCircumference;
    }

    public static double ticksPerTimeUnitToRadiansPerSecond(double ticks) {
        return ticksPerTimeUnitToRotationsPerSecond(ticks) * (Math.PI * 2.0);
    }
    
}
