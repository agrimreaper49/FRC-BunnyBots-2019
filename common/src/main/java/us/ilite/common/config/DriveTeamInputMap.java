package us.ilite.common.config;


import us.ilite.common.types.input.ELogitech310;

public class    DriveTeamInputMap {

    public static final ELogitech310
  
    DRIVER_TURN_AXIS = ELogitech310.RIGHT_X_AXIS,
    DRIVER_THROTTLE_AXIS = ELogitech310.LEFT_Y_AXIS,
    DRIVER_SUB_WARP_AXIS = ELogitech310.RIGHT_TRIGGER_AXIS,
    DRIVER_ACCEL_LIMIT_BYPASS = ELogitech310.LEFT_TRIGGER_AXIS,

    DRIVER_NUDGE_SEEK_LEFT = ELogitech310.L_BTN,
    DRIVER_NUDGE_SEEK_RIGHT = ELogitech310.R_BTN,

    DRIVER_TRACK_CARGO_BTN = ELogitech310.X_BTN,
    DRIVER_TRACK_TARGET_BTN = ELogitech310.A_BTN,
    DRIVER_TRACK_HATCH_BTN = ELogitech310.B_BTN,

    // Elevator
    OPERATOR_GROUND_POSITION_ELEVATOR = ELogitech310.X_BTN,
    OPERATOR_BOTTOM_POSITION_ELEVATOR = ELogitech310.A_BTN,
    OPERATOR_LOW_POSITION_ELEVATOR = ELogitech310.B_BTN,
    OPERATOR_MIDDLE_POSITION_ELEVATOR = ELogitech310.Y_BTN,
    OPERATOR_TOP_POSITION_ELEVATOR = ELogitech310.R_BTN,
    OPERATOR_CONTROL_ELEVATOR = ELogitech310.RIGHT_Y_AXIS,

    //TODO Refactor to OPERATOR
    // Cargo vs Hatch are toggle buttons
    OPERATOR_CARGO_SELECT = ELogitech310.DPAD_LEFT,
    OPERATOR_HATCH_SELECT = ELogitech310.DPAD_RIGHT,

    // Human Intake/Outake
    OPERATOR_SCORE = ELogitech310.RIGHT_TRIGGER_AXIS,

    // Floor Intake Buttons
    OPERATOR_INTAKE_GROUND = ELogitech310.LEFT_TRIGGER_AXIS,
    OPERATOR_INTAKE_LOADING_STATION = ELogitech310.L_BTN,
    OPERATOR_INTAKE_HANDOFF = ELogitech310.START,
    OPERATOR_INTAKE_STOWED = ELogitech310.R_BTN,
    OPERATOR_MANUAL_ARM_UP = ELogitech310.DPAD_UP,
    OPERATOR_MANUAL_ARM_DOWN = ELogitech310.DPAD_DOWN,

    DRIVER_CLIMBER_ALLOW = ELogitech310.START,
    OPERATOR_CLIMBER_AXIS = ELogitech310.LEFT_Y_AXIS,
    OPERATOR_PUSHER_BUTTON = ELogitech310.START,

    // Change these driver inputs upon driver's request.

    FLYWHEEL_STARTUP_BUTTON = ELogitech310.START,
    OUTTAKE = ELogitech310.BACK;

}
