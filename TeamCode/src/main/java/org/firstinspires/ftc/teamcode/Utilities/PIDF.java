package org.firstinspires.ftc.teamcode.Utilities;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Hardware.Limelight;

public class PIDF {

    //define variables!
    double kP; //This makes the power output proportional to how big the error is
    double kI; //This takes a continued minor error and turns it into power
    double kD; //This dampens the P term by subtracting the rate of error change from power
    double kF; //This is to overcome something like static friction or gravity
    double target; //target is usually a Tx value of 0. Tx measures the horozontal
    // distance between a limelight target (such as the center of an apriltag)
    double error; //the difference between our target and where we are now
    double errorSum; //sum of errors over time
    double lastError; //storage variable
    double derivative; //storage variable
    double power;
    private ElapsedTime timer = new ElapsedTime();



    public void PIDF(double kP, Ki, Kd, Kf){
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
        this.kF = kF;
    }

    public double getPower(double currentValue){
        double error = target - currentValue;
        errorSum += error * timer.seconds();
        derivative = (error - lastError)/timer.seconds();

        double P =  error * kP;
        double I = errorSum * kI;
        double D =  derivative * kD;
        double F = kF;

        double power = P + I + D + F;

        lastError = error;
        timer.reset();
        return power;
    }

    public void setTarget(double target){
        this.target = target;
        errorSum = 0;
    }

}
/*
// In the shooter class
shooterPIDF = new PIDF(systemValues.shooterP, .01, .001, .005);

// Go to v = 100;
    if(buttonPressed){
        shooterMotor.setTarget(123);
    }

            shooterMotor.setPower(shooterPIDF.getPower(currentValue));

 */