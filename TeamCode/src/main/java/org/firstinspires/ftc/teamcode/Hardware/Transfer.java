package org.firstinspires.ftc.teamcode.Hardware;

//These are imports, they let us use certain commands
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;


/*
classes can be public or private, public lets other classes control what goes on inside of the
class. Our transfer class needs to be public so our OpMode (class that pulls everything together
and maps it to controls) can access each of the subsystems and run them when needed.
*/
public class Transfer{

    private DcMotor transfer; //defining our transfer variable
    private enum TransferState { //Our list of states transfer can be in
        IN,
        OUT,
        IDLE
    }
    //creating a variable that allows us to switch
    // states, and also sets the state to IDLE, so it is always stopped when we start the code

    private TransferState state;

    public Transfer(HardwareMap hardwareMap){ //This is a constructor! This is where we tell the
        // code what exactly we are coding (in this case a DcMotor we've named and configured as
        // "transfer." Here we can also set specific motor settings such as reversing it. Passing
        // HardwareMap through it allows the transfer to initalize it's own motors without needing
        // to extend linearOpMode, which is the OpMode's job
        transfer = hardwareMap.get(DcMotor.class, "transfer");
        transfer.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //transfer.setDirection(DcMotorSimple.Direction.REVERSE); IF NEEDED
        state = TransferState.IDLE; //setting the state so it always starts idle when initialized
    }

    //State setter methods
    public void in(){state = TransferState.IN;}
    public void out(){
        state =TransferState.OUT;
    }
    public void idle(){
        state = TransferState.IDLE;
    }

    public void update(){ //changes state based on most recent input
        switch (state) { //Check state
            case IDLE: // if state is IDLE
                transfer.setPower(0);
                break;

            case IN: // If state is IN
                transfer.setPower(SystemValues.transferSpeed);
                break;

            case OUT: // if state is OUT
                transfer.setPower(SystemValues.outTakeSpeed);
                break;
            //TODO: determine transferSpeed and clearing in testing

        }
    }

}
