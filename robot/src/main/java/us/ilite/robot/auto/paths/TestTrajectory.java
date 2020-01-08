package us.ilite.robot.auto.paths;

import com.flybotix.hfr.util.log.ILog;
import com.flybotix.hfr.util.log.Logger;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import us.ilite.common.lib.trajectory.TrajectoryGenerator;
import us.ilite.robot.commands.FeedToFlywheel;
import us.ilite.robot.commands.ICommand;
import us.ilite.robot.commands.MoveForNCycles;
import us.ilite.robot.commands.ShootFlywheel;
import us.ilite.robot.modules.*;

public class TestTrajectory extends AutoSequence {
  private Drive mDrive;
  private Shooter mShooter;
 // private Limelight mAimBot;
 // private ETrackingType mTrackingType;
  private Hopper mHopper;
  private DriveMessage mMessage;
  private Conveyor mConveyor;


  private ILog mLogger  = Logger.createLog(TestTrajectory.class);
    public TestTrajectory(TrajectoryGenerator pTrajectoryGenerator , Drive pDrive , Shooter pShooter,
                           Hopper pHopper , Conveyor pConveyer) {
        super(pTrajectoryGenerator);
        this.mDrive = pDrive;
        this.mShooter = pShooter;
        // this.mAimBot = pAimBot;
        // this.mTrackingType = pTrackingType;
        this.mHopper = pHopper;
        // this.mThrottleProvider = pThrottleProvider;
        this.mConveyor = pConveyer;
    }
    @Override
    public ICommand[] generateSequence() {
        mLogger.info("---------------------390287490128734-------------------");

        SmartDashboard.putNumber("Velocity" , mShooter.getCurrentVelocity());
        SmartDashboard.putNumber("Left velocity" , mMessage.leftOutput );
        SmartDashboard.putNumber("Right velocity" , mMessage.rightOutput );


        return new ICommand[] { new MoveForNCycles ( 0.5d , 0.5d , 2 , false , 10 , mDrive) ,
                new ShootFlywheel(mShooter , mHopper , mConveyor),  new FeedToFlywheel(mHopper , mConveyor), new MoveForNCycles (1d , 1d ,
                5 , true , 10 , mDrive) };
    }
}
