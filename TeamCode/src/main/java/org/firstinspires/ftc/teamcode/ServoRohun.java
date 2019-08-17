package org.firstinspires.ftc.teamcode;

        import com.qualcomm.robotcore.eventloop.opmode.OpMode;
        import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
        import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name = "ServoRohun", group = "TeleOp")
public class ServoRohun extends OpMode {

    Servo testServo;
    @Override
    public void init() {
        testServo = hardwareMap.servo.get("servo_rohun");
    }

    @Override
    public void loop() {
        if(gamepad1.left_bumper){
            testServo.setPosition(1);
        }else if(gamepad1.right_bumper){
            testServo.setPosition(-1);
        }
    }
}