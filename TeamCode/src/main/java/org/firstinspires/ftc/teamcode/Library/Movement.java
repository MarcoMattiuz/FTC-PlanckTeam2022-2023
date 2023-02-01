package org.firstinspires.ftc.teamcode.Library;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;


public class Movement {
    private Gamepad gamepad;
    private Telemetry telemetry;
    private double maxBoostF;
    private double maxBoostH;
    private double direction;
    public Movement(Gamepad _gamepad, Telemetry _telemetry){
        this.gamepad = _gamepad;
        this.telemetry = _telemetry;
        maxBoostF = 0.5;
        maxBoostH = 0.5;
        direction=1;
    }


    private void log(String caption,String value){
        telemetry.addData(caption, value);
        telemetry.update();

    }
    public void tower(DcMotor up){
        if(gamepad.y)
            up.setPower(0.1);
        else
            up.setPower(-0.1);

    }
    public void power(double powerCut,DcMotor leftMotor,DcMotor rightMotor){

        // Run wheels in POV mode (note: The joystick goes negative when pushed forward, so negate it)
        // In this mode the Left stick moves the robot fwd and back, the Right stick turns left and right.
        // This way it's also easy to just drive straight, or just turn.
        if(gamepad.dpad_down){
            maxBoostF = maxBoostF > 0? maxBoostF-0.1:0;
        }else if(gamepad.dpad_up){
            maxBoostF = maxBoostF < 1? maxBoostF+0.1:1;
        } else if(gamepad.dpad_left){
            maxBoostH= maxBoostH > 0? maxBoostH-0.1:0;
        } else if(gamepad.dpad_right){
            maxBoostH = maxBoostH < 1? maxBoostH+0.1:1;
        }
        log("MaxboostH",""+maxBoostH);
        powerCut = gamepad.right_trigger > 0 ? 1 : powerCut;
        double power = gamepad.left_stick_y;
        power = power > powerCut ? powerCut : power;
        log("Power",""+power);
        double powerT = -gamepad.right_stick_x;
        double turn  =  powerT < -powerCut ? -powerCut : powerT;
        log("Turn",""+turn);
        // Combine drive and turn for blended motion.
        double left  = power + turn;
        double right = power - turn;

        // Normalize the values so neither exceed +/- 1.0
        double max = Math.max(Math.abs(left), Math.abs(right));
        if (max > 1.0)
        {
            left /= max;
            right /= max;
        }

        // Output the safe vales to the motor drives.
        leftMotor.setPower(left);
        rightMotor.setPower(right);

    }
}

