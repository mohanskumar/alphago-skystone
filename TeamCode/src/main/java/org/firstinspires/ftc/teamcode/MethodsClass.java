package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

public class MethodsClass {

    private ElapsedTime runtime = new ElapsedTime();
    AlphaGoHardware alpha = new AlphaGoHardware();
    AutoDriveByEncoder auto = new AutoDriveByEncoder();
    MecanumDrive md = new MecanumDrive();

    static final double     COUNTS_PER_MOTOR_REV    = 383.6 ;    // eg: TETRIX Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 2.0 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double     DRIVE_SPEED             = 0.5;
    //static final double     TURN_SPEED              = 0.5;



    public void initecoder(){
        auto.telemetry.addData("Status", "Resetting Encoders");    //
        auto.telemetry.update();

        alpha.frontLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        alpha.frontRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        alpha.rearLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        alpha.rearRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        alpha.frontLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        alpha.frontRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        alpha.rearLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        alpha.rearRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        auto.telemetry.addData("Path0",  "Starting at %7d :%7d",
                alpha.frontLeftDrive.getCurrentPosition(),
                alpha.frontRightDrive.getCurrentPosition(),
                alpha.rearLeftDrive.getCurrentPosition(),
                alpha.rearRightDrive.getCurrentPosition());
        auto.telemetry.update();
    }


    public void encoderDrive(double speed, double leftInches, double rightInches,
                             double timeoutS) {

        // Ensure that the opmode is still active
        if (auto.opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            int newLeftTarget = alpha.frontLeftDrive.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            int newRightTarget = alpha.frontRightDrive.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            int newRearLeftTarget = alpha.rearLeftDrive.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            int newRearRightTarget = alpha.rearRightDrive.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);


            alpha.frontLeftDrive.setTargetPosition(newLeftTarget);
            alpha.frontRightDrive.setTargetPosition(newRightTarget);
            alpha.rearLeftDrive.setTargetPosition(newRearLeftTarget);
            alpha.rearRightDrive.setTargetPosition(newRearRightTarget);

            // Turn On RUN_TO_POSITION
            alpha.frontLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            alpha.frontRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            alpha.rearLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            alpha.rearRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            alpha.frontLeftDrive.setPower(Math.abs(speed));
            alpha.frontRightDrive.setPower(Math.abs(speed));
            alpha.rearLeftDrive.setPower(Math.abs(speed));
            alpha.rearRightDrive.setPower(Math.abs(speed));

            while (auto.opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (alpha.frontLeftDrive.isBusy()
                            && alpha.frontRightDrive.isBusy()
                            && alpha.rearLeftDrive.isBusy()
                            && alpha.rearLeftDrive.isBusy())) {

                // Display it for the driver.
                auto.telemetry.addData("Path1",  "Running to %7d :%7d", newLeftTarget,  newRightTarget);
                auto.telemetry.addData("Path2",  "Running at %7d :%7d",
                        alpha.frontLeftDrive.getCurrentPosition(),
                        alpha.frontRightDrive.getCurrentPosition(),
                        alpha.rearLeftDrive.getCurrentPosition(),
                        alpha.rearRightDrive.getCurrentPosition());
                auto.telemetry.update();
            }

            // Stop all motion;
            alpha.frontLeftDrive.setPower(0);
            alpha.frontRightDrive.setPower(0);
            alpha.rearLeftDrive.setPower(0);
            alpha.rearRightDrive.setPower(0);

            // Turn off RUN_TO_POSITION
            alpha.frontLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            alpha.frontRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            alpha.rearLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            alpha.rearRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            auto.telemetry.addData("Path", "Complete");
            auto.telemetry.update();
        }
    }

    public void stopall(){
        alpha.frontLeftDrive.setPower(0);
        alpha.frontRightDrive.setPower(0);
        alpha.rearLeftDrive.setPower(0);
        alpha.rearRightDrive.setPower(0);
    }

    public void drivemecanum(){
        double G1leftStickY = -md.gamepad1.left_stick_y;

        boolean G1LeftBumper = md.gamepad1.left_bumper;
        boolean G1RightBumper = md.gamepad1.right_bumper;

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

        }else if(md.gamepad1.dpad_up){//Forwards Slow

            alpha.frontLeftDrive.setPower(.5);
            alpha.rearLeftDrive.setPower(.5);
            alpha.frontRightDrive.setPower(-.5);
            alpha.rearRightDrive.setPower(-.5);

        }else if(md.gamepad1.dpad_down){//Backwards Slow

            alpha.frontLeftDrive.setPower(-.5);
            alpha.rearLeftDrive.setPower(-.5);
            alpha.frontRightDrive.setPower(.5);
            alpha.rearRightDrive.setPower(.5);

        }else if(md.gamepad1.dpad_left){//Strafe left Slow

            alpha.frontLeftDrive.setPower(-1);
            alpha.rearLeftDrive.setPower(1);
            alpha.frontRightDrive.setPower(-1);
            alpha.rearRightDrive.setPower(1);

        }else if(md.gamepad1.dpad_right){//Strafe right Slow

            alpha.frontLeftDrive.setPower(1);
            alpha.rearLeftDrive.setPower(-1);
            alpha.frontRightDrive.setPower(1);
            alpha.rearRightDrive.setPower(-1);

        }else if(md.gamepad1.right_stick_x>0 && md.gamepad1.right_stick_x<=1){//strafe left

            alpha.frontLeftDrive.setPower(1);
            alpha.rearLeftDrive.setPower(-1);
            alpha.frontRightDrive.setPower(1);
            alpha.rearRightDrive.setPower(-1);

        } else if(md.gamepad1.right_stick_x<0 && md.gamepad1.right_stick_x>=-1){//strafe right

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
