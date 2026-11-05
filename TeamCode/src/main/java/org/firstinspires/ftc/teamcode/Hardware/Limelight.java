package org.firstinspires.ftc.teamcode.Hardware;


import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


public class Limelight extends LinearOpMode {

    //defining variables
    Limelight3A limelight;

    //these will be moved to systemValues class
    double tagHeight = 28;
    double cameraHeight = 13;
    double cameraAngle = 15;
    public static double TPS = 0;
    public static double shooterTarget = 0;

    //P applies power proportional to how far it is from the target
    // the farther it is away from the target the faster it will move
    public static double Kp = 0.04; //~0.04

    //
    public static double Ki = 0.067;

    //D calculates the rate of change going towards the desired setpoint
    //when it starts to move closer, d will slow the rate of change so it doesn't overshoot. It also helps with oscillations
    public static double Kd = 0;

    //this is the minimum amount of power required for the robot to move
    public double integral = 1;



    double lastError = 0;


    public void runOpMode(){
        limelight = hardwareMap.get(Limelight3A.class, "LL");

        telemetry.setMsTransmissionInterval(11);

        limelight.pipelineSwitch(0);

        limelight.start();
        LLResult result = null;
        double limelightTa = 0;
        double limelightTx = 0;
        double limelightTy = 0;
        double power = 0;
        double lastTx = 0;

    }

}
