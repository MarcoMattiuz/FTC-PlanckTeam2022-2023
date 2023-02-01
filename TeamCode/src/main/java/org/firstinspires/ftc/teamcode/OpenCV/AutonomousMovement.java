package org.firstinspires.ftc.teamcode.OpenCV;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class AutonomousMovement {
    private Telemetry telemetry;
    private double direction;
    private DcMotor leftMotor;
    private DcMotor rightMotor;
    private DcMotor upMotor;
    public AutonomousMovement( Telemetry _telemetry,DcMotor left,DcMotor right,DcMotor tower){
        this.telemetry = _telemetry;
        leftMotor = left;
        rightMotor = right;
        upMotor = tower;
    }

    public void turn(String direction,double power){

        switch (direction){
            case "left":{
                leftMotor.setPower(-power);
                rightMotor.setPower(power);
                break;
            }
            case "right":{
                leftMotor.setPower(power);
                rightMotor.setPower(-power);
                break;
            }
            case "front":{
                leftMotor.setPower(power);
                rightMotor.setPower(power);
                break;
            }
            case "back":{
                leftMotor.setPower(-power);
                rightMotor.setPower(-power);
                break;
            }
            default: break;
        }

    }


}
