package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "ServoTest", group = "TeleOp")
public class ServoTest extends OpMode {

    Servo testServo;
    @Override
    public void init() {
        testServo = hardwareMap.servo.get("test_servo");
    }

    @Override
    public void loop() {
        if(gamepad1.x){
            testServo.setPosition(1);
        }else if(gamepad1.b){
            testServo.setPosition(0);
        }
    }
}