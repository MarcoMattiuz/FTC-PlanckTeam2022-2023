package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Library.Movement;

@TeleOp
public class ControllerTest extends LinearOpMode {
    private DcMotor leftMotor;
    private DcMotor rightMotor;
    private DcMotor upMotor;
    private Movement move;
    private void log(String caption,String value){
        telemetry.addData(caption, value);
        telemetry.update();
    }

    @Override
    public void runOpMode() {
        leftMotor = hardwareMap.get(DcMotor.class, "left");
        rightMotor = hardwareMap.get(DcMotor.class, "right");
        upMotor = hardwareMap.get(DcMotor.class,"up");
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        move = new Movement(gamepad1,telemetry);
        log("Status","Initialized");
        // Wait for the game to start (driver presses PLAY)

        waitForStart();
        // run until the end of the match (driver presses STOP
        while (opModeIsActive()) {
            move.power(0.5,leftMotor,rightMotor);
            move.tower(upMotor);




        }
    }
}