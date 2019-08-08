package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "SimpleDrive", group = "TeleOp")
public class SimpleDrive extends LinearOpMode
{
    private ElapsedTime runtime = new ElapsedTime();
    private AlphaGoHardware alpha = new AlphaGoHardware();


    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        alpha.init(hardwareMap);

        alpha.leftDrive.setDirection(DcMotor.Direction.FORWARD);
        alpha.rightDrive.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {

            double leftPower;
            double rightPower;

            double drive = -gamepad1.left_stick_y;
            double turn  =  gamepad1.right_stick_x;
            leftPower    = Range.clip(drive + turn, -1.0, 1.0) ;
            rightPower   = Range.clip(drive - turn, -1.0, 1.0) ;

            alpha.leftDrive.setPower(leftPower);
            alpha.rightDrive.setPower(rightPower);

            if(gamepad1.left_bumper){
                alpha.leftDrive.setPower(-1);
                alpha.rightDrive.setPower(1);
            }else if (gamepad1.right_bumper){
                alpha.leftDrive.setPower(1);
                alpha.rightDrive.setPower(-1);
            }else{
                alpha.leftDrive.setPower(0);
                alpha.rightDrive.setPower(0);
            }



            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
            telemetry.update();
        }

    }
}
