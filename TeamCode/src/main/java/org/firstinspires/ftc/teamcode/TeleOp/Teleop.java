package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Hardware.Drivetrain;
import org.firstinspires.ftc.teamcode.Hardware.Intake;

@TeleOp
public class Teleop extends LinearOpMode {

    private enum OpState { //states our opmode can be in using subsystems
        INTAKING,
        IDLE,
        CLEARING,
        FIRING,
    }
    private enum OpDriveState{ //driving states our opmode can be in
        AIMING,
        MANUAL
    }

    private OpState state = OpState.IDLE;
    private OpDriveState drivestate = OpDriveState.MANUAL;

    //creating subsystem variables that we can control (just like the motors in the subsystems!)
    Drivetrain drivetrain;
    Intake intake;


    //TELEOP MAIN LOOP
    @Override
    public void runOpMode() {//When we press initialize...

        drivetrain = new Drivetrain(hardwareMap);
        intake = new Intake(hardwareMap);

        waitForStart();
        while (opModeIsActive()) {//While the code is running...

            //drivetrain movement
           drivetrain.drive(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);

           //TODO: ensure joysticks override aiming mechanism in testing
           if(gamepad1.leftTriggerWasPressed()) {
               if (drivestate == OpDriveState.MANUAL) {
                   drivestate = OpDriveState.AIMING;
               }
               else{
                   drivestate = OpDriveState.MANUAL;
               }
           }

           //other subsystem controls
           if (gamepad1.rightBumperWasPressed()){ //intake button
                if(state == OpState.INTAKING){
                    state = OpState.IDLE;
                }
                else{
                    state = OpState.INTAKING;
                }
            }

            if (gamepad1.leftBumperWasPressed()){ //clearing button
                if(state == OpState.CLEARING){
                    state = OpState.IDLE;
                }
                else{
                    state = OpState.CLEARING;
                }
            }

            //TODO: rest of control mapping & toggles




            switch(state){
                case IDLE:
                    intake.idle();
                    break;

                case CLEARING:
                    intake.out();
                    break;

                case INTAKING:
                    intake.in();
                    break;

                    //TODO: Rest of state switching
            }

            telemetry.update();
        }
    }
}

