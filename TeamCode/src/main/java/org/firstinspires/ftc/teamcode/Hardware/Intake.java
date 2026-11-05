package org.firstinspires.ftc.teamcode.Hardware;

//These are imports, they let us use certain commands
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;


/*
classes can be public or private, public lets other classes control what goes on inside of the
class. Our intake class needs to be public so our OpMode (class that pulls everything together
and maps it to controls) can access each of the subsystems and run them when needed.
*/
public class Intake{

    private DcMotor intake; //defining our intake variable
    //TODO: change name to state
    private enum IntakeState { //Our list of states Intake can be in
        IN,
        OUT,
        IDLE
    }
     //creating a variable that allows us to switch
    // states, and also sets the state to IDLE, so it is always stopped when we start the code

    private IntakeState intakeState;

    public Intake(HardwareMap hardwareMap) { //This is a constructor! This is where we tell the
        // code what exactly we are coding (in this case a DcMotor we've named and configured as
        // "intake." Here we can also set specific motor settings such as reversing it. Passing
        // HardwareMap through it allows the intake to initalize it's own motors without needing
        // to extend linearOpMode
       intake = hardwareMap.get(DcMotor.class, "intake");

       intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
       //intake.setDirection(DcMotorSimple.Direction.REVERSE); IF NEEDED

        intakeState = IntakeState.IDLE;
   }
//TODO: change names to "in"
    public void intakeIn(){
        intakeState = IntakeState.IN;
    }
    public void intakeOut(){
        intakeState =IntakeState.OUT;
    }
    public void intakeIdle(){
        intakeState = IntakeState.IDLE;
    }

    public void update(){
        switch (intakeState) { //Check state
            case IDLE: // if state is IDLE
                intake.setPower(0); //intake motor is off
                break;

            case IN: // If state is IN
                intake.setPower(SystemValues.intakeSpeed); //intake motor is on
                //TODO: determine intakeSpeed and clearing in testing
                break;

            case OUT: // if state is OUT
                intake.setPower(SystemValues.outTakeSpeed); //intake motor is on (backwards)
                break;
        }
    }

}
