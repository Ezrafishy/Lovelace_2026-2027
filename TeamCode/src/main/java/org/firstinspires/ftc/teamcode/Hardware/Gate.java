package org.firstinspires.ftc.teamcode.Hardware;

//These are imports, they let us use certain commands
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


/*
classes can be public or private, public lets other classes control what goes on inside of the
class. Our gate class needs to be public so our OpMode (class that pulls everything together
and maps it to controls) can access each of the subsystems and run them when needed.
*/
public class Gate{

    private Servo gate; //defining our gate variable
    private enum GateState { //Our list of states gate can be in
        OPEN,
        CLOSE
    }
    //creating a variable that allows us to switch
    // states, and also sets the state to IDLE, so it is always stopped when we start the code

    private GateState state;

    public Gate(HardwareMap hardwareMap){ //This is a constructor! This is where we tell the
        // code what exactly we are coding (setting the servo to 1 and -1)
        // Passing HardwareMap through it allows the gate to initalize it's own servo without needing
        // to extend linearOpMode, which is the OpMode's job
        gate = hardwareMap.get(Servo.class, "gate");
        state = GateState.CLOSE; //setting the state so it always starts close when initialized
    }

    //State setter methods
    public void open(){state = GateState.OPEN;}
    public void close(){
        state =GateState.CLOSE;
    }

    public void update(){ //changes state based on most recent input
        switch (state) { //Check state


            case OPEN: // If state is OPEN
                gate.setPosition(SystemValues.gateOpenServoValue);
                break;

            case CLOSE: // if state is CLOSE
                gate.setPosition(SystemValues.gateCloseServoValue);
                break;

        }
    }

}
