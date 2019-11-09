package us.ilite.robot.commands;

import us.ilite.robot.modules.Elevator;

public class SetElevatorPosition implements ICommand {

    private final Elevator mElevator;

    private final Elevator.EElevatorPosition mDesiredPosition;

    public SetElevatorPosition(Elevator pElevator, Elevator.EElevatorPosition pDesiredPosition) {
        mElevator = pElevator;
        mDesiredPosition = pDesiredPosition;
    }

    @Override
    public void init(double pNow) {

        mElevator.setDesiredPosition(mDesiredPosition);

    }

    @Override
    public boolean update(double pNow) {
        return mElevator.isAtPosition(mDesiredPosition);
    }

    @Override
    public void shutdown(double pNow) {

    }

}
