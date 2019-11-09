package us.ilite.robot.commands;

import us.ilite.robot.driverinput.DriverInput.EGamePiece;
import us.ilite.robot.modules.Elevator;
import us.ilite.robot.modules.HatchFlower;
import us.ilite.robot.modules.Intake;

public class GroundIntakeHatch extends CommandQueue {

    public GroundIntakeHatch(Elevator pElevator, Intake pIntake, HatchFlower pHatchFlower) {
        setCommands(
                new ParallelCommand(
                        new SetElevatorPosition(pElevator, Elevator.EElevatorPosition.HATCH_BOTTOM),
                        new ReleaseHatch(pHatchFlower),
                        new SetHatchGrabberExtension(pHatchFlower, HatchFlower.ExtensionState.DOWN)
                ),
                new SetIntakeState(pIntake, Intake.EIntakeState.GROUND, EGamePiece.HATCH)
        );
    }
}
