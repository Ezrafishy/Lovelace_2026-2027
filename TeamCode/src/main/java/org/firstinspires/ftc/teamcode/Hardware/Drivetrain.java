package org.firstinspires.ftc.teamcode.Hardware;


import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import com.qualcomm.robotcore.util.Range;

public class Drivetrain{

    //defining drivetrain motor variables
    private DcMotor BR;
    private DcMotor BL;
    private DcMotor FL;
    private DcMotor FR;

    //odometry wheels
    private DcMotorEx par; //parallel deadwheel
    private DcMotorEx perp; //perpendicular deadwheel

    private double r; //Variable that stores joystick movement/direction
    private double v1, v2, v3, v4;

    private enum driveState {
        MANUAL,
        AIMING,
    }

    private driveState drivestate = driveState.MANUAL;

    public Drivetrain(HardwareMap hardwareMap){
            //Mapping motor variables to configured motors
            BR = hardwareMap.get(DcMotor.class, "BR");
            BL = hardwareMap.get(DcMotor.class, "BL");
            FL = hardwareMap.get(DcMotor.class, "FL");
            FR = hardwareMap.get(DcMotor.class, "FR");

            //motor settings
            BL.setDirection(DcMotorSimple.Direction.REVERSE);
            BR.setDirection(DcMotorSimple.Direction.REVERSE);

            BR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            FR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            BL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            FL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }

        //Drive method does the calculations for what power to set to the motors using joystick
        //input
        public void drive(double left_stick_x, double left_stick_y, double right_stick_x) {

            //TeleOp drivetrain/movement calculations using joystick controls
            r = Math.hypot(-left_stick_y, left_stick_x);
            double robotAngle = Math.atan2(left_stick_x, -left_stick_y) - Math.PI / 4;
            v4 = r * Math.cos(robotAngle) - right_stick_x;
            v3 = r * Math.sin(robotAngle) - right_stick_x;
            v2 = r * Math.sin(robotAngle) + right_stick_x;
            v1 = r * Math.cos(robotAngle) + right_stick_x;

        }

        public void update(){
            switch (drivestate) {
                case MANUAL:
                    BR.setPower(Range.clip(-v4, -1D, 1D));
                    BL.setPower(Range.clip(-v3, -1D, 1D));
                    FR.setPower(Range.clip(-v2, -1D, 1D));
                    FL.setPower(Range.clip(-v1, -1D, 1D));
                    break;

                case AIMING: //calculations are run in PID/LL classes?
                    //TODO: where are calculations run?
                    /*
                    rotationPower = aimingPID(aiming.getError());
                    BR.setPower(rotationPower);
                    BL.setPower(-rotationPower);
                    FR.setPower(rotationPower);
                    FL.setPower(-rotationPower);
                    */
                    break;
                    }
            }

    }
