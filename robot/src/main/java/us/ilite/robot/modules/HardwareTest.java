package us.ilite.robot.modules;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import us.ilite.lib.drivers.SparkMaxFactory;

import java.net.SocketAddress;

public class HardwareTest extends Module {
    private CANSparkMax mCANSparkMax1;
    private CANSparkMax mCANSparkMax2;


    public HardwareTest() {
        mCANSparkMax1 = SparkMaxFactory.createDefaultSparkMax(2, CANSparkMaxLowLevel.MotorType.kBrushless);
        mCANSparkMax2 = SparkMaxFactory.createDefaultSparkMax(3, CANSparkMaxLowLevel.MotorType.kBrushless);

        mCANSparkMax1.setInverted(true);
    }

    @Override
    public void modeInit(double pNow) {

    }

    @Override
    public void periodicInput(double pNow) {

    }

    @Override
    public void update(double pNow) {
        mCANSparkMax1.set(1.0);
        mCANSparkMax2.set(1.0);
    }

    @Override
    public void shutdown(double pNow) {

    }
}
