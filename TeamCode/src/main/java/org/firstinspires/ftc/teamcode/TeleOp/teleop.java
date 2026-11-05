package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class teleop extends LinearOpMode {

    private enum OpState { //states our opmode can be in
        INTAKING,
        IDLE,
        CLEARING,
        FIRING,
        AIMING
    }

    private OpState state = OpState.IDLE;

    //creating subsystem variables that we can control (just like the motors in the subsystems!)
    Drivetrain drivetrain;
    Intake intake;

    private boolean intakeOn = false;
    private boolean clearing = false;


    //TELEOP MAIN LOOP
    @Override
    public void runOpMode() {//When we press initialize...

        drivetrain = new Drivetrain(hardwareMap);
        intake = new Intake(hardwareMap);

        waitForStart();
        while (opModeIsActive()) {
            //drivetrain movement
            drivetrain.drive(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);

            //control mapping & toggle switches!
            if (gamepad1.right_bumper && !intakeOn) {
                state = OpState.INTAKING;
                intakeOn = true;
            }
            if (gamepad1.right_bumper && intakeOn) {
                state = OpState.IDLE;
                intakeOn = false;
            }
            if (gamepad1.left_bumper && !clearing){
                state = OpState.CLEARING;
                clearing = true;
            }
            if (gamepad1.left_bumper && clearing){
                state = OpState.IDLE;
                clearing = false;
            }

            switch(state){
                case IDLE:
                    intake.intakeIdle();
                    break;

                case CLEARING:
                    intake.intakeOut();
                    break;

                case INTAKING:
                    intake.intakeIn();
                    break;
            }

            telemetry.update();
        }
    }
}

