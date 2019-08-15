package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@TeleOp(name = "MecanumDrive", group = "test_drive")
public class MecanumDrive extends LinearOpMode
{
    private ElapsedTime runtime = new ElapsedTime();
    private AlphaGoHardware alpha = new AlphaGoHardware();

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        alpha.init(hardwareMap);
        alpha.FL_Drive.setPower(0);
        alpha.FR_Drive.setPower(0);
        alpha.RL_Drive.setPower(0);
        alpha.RR_Drive.setPower(0);

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {
            double G1leftStickY = -gamepad1.left_stick_y;

            boolean G1LeftBumper = gamepad1.left_bumper;
            boolean G1RightBumper = gamepad1.right_bumper;

            if (G1LeftBumper){//Turn Left

                alpha.FL_Drive.setPower(-1);
                alpha.RL_Drive.setPower(-1);
                alpha.FR_Drive.setPower(-1);
                alpha.RR_Drive.setPower(-1);

            }else if(G1RightBumper){//Turn Right

                alpha.FL_Drive.setPower(1);
                alpha.RL_Drive.setPower(1);
                alpha.FR_Drive.setPower(1);
                alpha.RR_Drive.setPower(1);

            }else if(gamepad1.dpad_up){//Forwards Slow

                alpha.FL_Drive.setPower(.5);
                alpha.RL_Drive.setPower(.5);
                alpha.FR_Drive.setPower(-.5);
                alpha.RR_Drive.setPower(-.5);

            }else if(gamepad1.dpad_down){//Backwards Slow

                alpha.FL_Drive.setPower(-.5);
                alpha.RL_Drive.setPower(-.5);
                alpha.FR_Drive.setPower(.5);
                alpha.RR_Drive.setPower(.5);

            }else if(gamepad1.dpad_left){//Strafe left Slow

                alpha.FL_Drive.setPower(-1);
                alpha.RL_Drive.setPower(1);
                alpha.FR_Drive.setPower(-1);
                alpha.RR_Drive.setPower(1);

            }else if(gamepad1.dpad_right){//Strafe right Slow

                alpha.FL_Drive.setPower(1);
                alpha.RL_Drive.setPower(-1);
                alpha.FL_Drive.setPower(1);
                alpha.RL_Drive.setPower(-1);

            }else if(gamepad1.right_stick_x>0 && gamepad1.right_stick_x<=1){//strafe left

                alpha.FL_Drive.setPower(1);
                alpha.RL_Drive.setPower(-1);
                alpha.FR_Drive.setPower(1);
                alpha.RR_Drive.setPower(-1);

            } else if(gamepad1.right_stick_x<0 && gamepad1.right_stick_x>=-1){//strafe right

                alpha.FL_Drive.setPower(-1);
                alpha.RL_Drive.setPower(1);
                alpha.FR_Drive.setPower(-1);
                alpha.RR_Drive.setPower(1);

            } else {//Regular Joystick Drive

                alpha.FL_Drive.setPower(G1leftStickY);
                alpha.RL_Drive.setPower(G1leftStickY);
                alpha.FR_Drive.setPower(-G1leftStickY);
                alpha.RR_Drive.setPower(-G1leftStickY);

            }
        }
    }
}
