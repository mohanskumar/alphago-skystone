package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "MecanumDrive", group = "TeleOp")
public class MecanumDrive extends LinearOpMode
{
    private ElapsedTime runtime = new ElapsedTime();
    private AlphaGoHardware alpha = new AlphaGoHardware();

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        alpha.init(hardwareMap);
        alpha.frontLeftDrive.setPower(0);
        alpha.frontRightDrive.setPower(0);
        alpha.rearLeftDrive.setPower(0);
        alpha.rearRightDrive.setPower(0);

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {

        }
    }
}
