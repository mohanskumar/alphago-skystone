package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;



@Autonomous(name="AutoDriveEncoder", group="Autonomous")
public class AutoDriveByEncoder extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    AlphaGoHardware alpha = new AlphaGoHardware();

    static final double     COUNTS_PER_MOTOR_REV    = 383.6 ;    // eg: TETRIX Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 2.0 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
                                                      (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double     DRIVE_SPEED             = 0.5;
    //static final double     TURN_SPEED              = 0.5;

    @Override
    public void runOpMode() {

        alpha.init(hardwareMap);

        telemetry.addData("Status", "Resetting Encoders");    //
        telemetry.update();

        alpha.frontLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        alpha.frontRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        alpha.rearLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        alpha.rearRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        alpha.frontLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        alpha.frontRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        alpha.rearLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        alpha.rearRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Send telemetry message to indicate successful Encoder reset
        telemetry.addData("Path0",  "Starting at %7d :%7d",
                alpha.frontLeftDrive.getCurrentPosition(),
                alpha.frontRightDrive.getCurrentPosition(),
                alpha.rearLeftDrive.getCurrentPosition(),
                alpha.rearRightDrive.getCurrentPosition());
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // Step through each leg of the path,
        // Note: Reverse movement is obtained by setting a negative distance (not speed)
        encoderDrive(DRIVE_SPEED,  10,  10, 4.0);  // S1: Forward 47 Inches with 5 Sec timeout

        telemetry.addData("Path", "Complete");
        telemetry.update();
    }

    /*
     *  Method to perfmorm a relative move, based on encoder counts.
     *  Encoders are not reset as the move is based on the current position.
     *  Move will stop if any of three conditions occur:
     *  1) Move gets to the desired position
     *  2) Move runs out of time
     *  3) Driver stops the opmode running.
     */
    public void encoderDrive(double speed, double leftInches, double rightInches,
                             double timeoutS) {

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

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

            while (opModeIsActive() &&
                   (runtime.seconds() < timeoutS) &&
                    (alpha.frontLeftDrive.isBusy()
                           && alpha.frontRightDrive.isBusy()
                           && alpha.rearLeftDrive.isBusy()
                           && alpha.rearLeftDrive.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1",  "Running to %7d :%7d", newLeftTarget,  newRightTarget);
                telemetry.addData("Path2",  "Running at %7d :%7d",
                                            alpha.frontLeftDrive.getCurrentPosition(),
                                            alpha.frontRightDrive.getCurrentPosition(),
                                            alpha.rearLeftDrive.getCurrentPosition(),
                                            alpha.rearRightDrive.getCurrentPosition());
                telemetry.update();
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

        }
    }
}
