package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@TeleOp(name = "MecanumDrive", group = "test_drive")
public class MecanumDrive extends LinearOpMode
{
    private ElapsedTime runtime = new ElapsedTime();
    private MecanumDrive mecanumDrive = new MecanumDrive();
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

            double G1leftStickY = -gamepad1.left_stick_y;

            boolean G1LeftBumper = gamepad1.left_bumper;
            boolean G1RightBumper = gamepad1.right_bumper;

            if (G1LeftBumper){//Turn Left

                alpha.frontLeftDrive.setPower(-1);
                alpha.rearLeftDrive.setPower(-1);
                alpha.frontRightDrive.setPower(-1);
                alpha.rearRightDrive.setPower(-1);

            }else if(G1RightBumper){//Turn Right

                alpha.frontLeftDrive.setPower(1);
                alpha.rearLeftDrive.setPower(1);
                alpha.frontRightDrive.setPower(1);
                alpha.rearRightDrive.setPower(1);

            }else if(gamepad1.dpad_up){//Forwards Slow

                alpha.frontLeftDrive.setPower(.5);
                alpha.rearLeftDrive.setPower(.5);
                alpha.frontRightDrive.setPower(-.5);
                alpha.rearRightDrive.setPower(-.5);

            }else if(gamepad1.dpad_down){//Backwards Slow

                alpha.frontLeftDrive.setPower(-.5);
                alpha.rearLeftDrive.setPower(-.5);
                alpha.frontRightDrive.setPower(.5);
                alpha.rearRightDrive.setPower(.5);

            }else if(gamepad1.dpad_left){//Strafe left Slow

                alpha.frontLeftDrive.setPower(-1);
                alpha.rearLeftDrive.setPower(1);
                alpha.frontRightDrive.setPower(-1);
                alpha.rearRightDrive.setPower(1);

            }else if(gamepad1.dpad_right){//Strafe right Slow

                alpha.frontLeftDrive.setPower(1);
                alpha.rearLeftDrive.setPower(-1);
                alpha.frontRightDrive.setPower(1);
                alpha.rearRightDrive.setPower(-1);

            }else if(gamepad1.right_stick_x>0 && gamepad1.right_stick_x<=1){//strafe left

                alpha.frontLeftDrive.setPower(1);
                alpha.rearLeftDrive.setPower(-1);
                alpha.frontRightDrive.setPower(1);
                alpha.rearRightDrive.setPower(-1);

            } else if(gamepad1.right_stick_x<0 && gamepad1.right_stick_x>=-1){//strafe right

                alpha.frontLeftDrive.setPower(-1);
                alpha.rearLeftDrive.setPower(1);
                alpha.frontRightDrive.setPower(-1);
                alpha.rearRightDrive.setPower(1);

            } else {//Regular Joystick Drive

                alpha.frontLeftDrive.setPower(G1leftStickY);
                alpha.rearLeftDrive.setPower(G1leftStickY);
                alpha.frontRightDrive.setPower(-G1leftStickY);
                alpha.rearRightDrive.setPower(-G1leftStickY);

            }
        }
    }
}
