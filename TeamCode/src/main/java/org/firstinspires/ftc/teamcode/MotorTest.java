package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "MotorTest", group = "TeleOp")
public class MotorTest extends OpMode {

    DcMotor testMotor;
    float testMotorPower;
    @Override
    public void init() {
        testMotor = hardwareMap.dcMotor.get("test_motor");
        testMotor.setDirection(DcMotor.Direction.FORWARD);
    }

    @Override
    public void loop() {
        testMotorPower = gamepad1.left_stick_y;
        testMotor.setPower(testMotorPower);
    }
}