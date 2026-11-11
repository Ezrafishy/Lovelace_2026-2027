package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Utilities.PIDF;

public class Launcher{

    private DcMotorEx flywheel;
    private enum LauncherState {
        NORMAL,
        REVERSE,
        IDLE
    }
    private LauncherState state;
    double currentV;
    double targetV;

    //TODO: Figure out launcher calculations
    //targetV = calculations using odometry

    PIDF shooterPIDF;


    public Launcher(HardwareMap hardwareMap){
        flywheel = hardwareMap.get(DcMotorEx.class, "flywheel");
        flywheel.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        //flywheel.setDirection(DcMotorEx.Direction.REVERSE); IF NEEDED
        state = LauncherState.IDLE; //setting the state so it always starts idle when initialized
        shooterPIDF = new PIDF(SystemValues.shooter.P, SystemValues.shooter.I, SystemValues.shooter.D, SystemValues.shooter.F);

    }

    //State setter methods
    public void normal(){
        state = LauncherState.IDLE;}
    public void reverse(){
        state = LauncherState.REVERSE;
    }
    public void idle(){
        state = LauncherState.IDLE;
    }

    public void update(){
        switch (state) {
            case IDLE:
                flywheel.setPower(0);
                break;

            case NORMAL:
                flywheel.setPower(shooterPIDF.getPower(currentV));
                break;

            case REVERSE:
                flywheel.setPower(SystemValues.shooter.clearingSoeed);
                break;
            //TODO: determine clearing speed in testing

        }
        currentV = flywheel.getVelocity();
    }

}
