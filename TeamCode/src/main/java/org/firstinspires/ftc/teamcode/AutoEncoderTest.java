package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "Drive by Encoder", group = "Autonomous")
public class AutoEncoderTest extends LinearOpMode {

    private AlphaGoHardware alpha = new AlphaGoHardware();
    private ElapsedTime runtime = new ElapsedTime();

    static final double     COUNTS_PER_MOTOR_REV    = 383.6 ;
    static final double     DRIVE_GEAR_REDUCTION    = 2.0 ;
    static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double     DRIVE_SPEED             = 0.5;
    static final double     TURN_SPEED              = 0.5;

    @Override
    public void runOpMode() {

        alpha.initMecanum(hardwareMap);
        telemetry.addData("Status", "Resetting Encoders");    //
        telemetry.update();

        alpha.FL_Drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        alpha.FR_Drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        alpha.RL_Drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        alpha.RR_Drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        alpha.FL_Drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        alpha.FR_Drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        alpha.RL_Drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        alpha.RR_Drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addData("Path0",  "Starting at %7d :%7d",
                alpha.FL_Drive.getCurrentPosition(),
                alpha.FR_Drive.getCurrentPosition(),
                alpha.RL_Drive.getCurrentPosition(),
                alpha.RR_Drive.getCurrentPosition());
        telemetry.update();

        //Start
        waitForStart();

        encoderDrive(DRIVE_SPEED,  10,  10, 5.0);


        telemetry.addData("Path", "Complete");
        telemetry.update();
    }


    public void encoderDrive(double speed, double leftInches, double rightInches,
                             double timeoutS) {

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            int leftTarget = alpha.FL_Drive.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            int rightTarget = alpha.FR_Drive.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            int leftRearTarget = alpha.RL_Drive.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            int rightRearTarget = alpha.RR_Drive.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);

            alpha.FL_Drive.setTargetPosition(leftTarget);
            alpha.FR_Drive.setTargetPosition(rightTarget);
            alpha.RL_Drive.setTargetPosition(leftRearTarget);
            alpha.RR_Drive.setTargetPosition(rightRearTarget);

            // Turn On RUN_TO_POSITION
            alpha.FL_Drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            alpha.FR_Drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            alpha.RL_Drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            alpha.RR_Drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            alpha.FL_Drive.setPower(Math.abs(speed));
            alpha.FR_Drive.setPower(Math.abs(speed));
            alpha.RL_Drive.setPower(Math.abs(speed));
            alpha.RR_Drive.setPower(Math.abs(speed));

            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (alpha.FL_Drive.isBusy() && alpha.FR_Drive.isBusy() && alpha.RL_Drive.isBusy() && alpha.RR_Drive.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1",  "Running to %7d :%7d", leftTarget,  rightTarget, leftRearTarget, rightRearTarget);
                telemetry.addData("Path2",  "Running at %7d :%7d",
                        alpha.FL_Drive.getCurrentPosition(),
                        alpha.FR_Drive.getCurrentPosition(),
                        alpha.RL_Drive.getCurrentPosition(),
                        alpha.RR_Drive.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;
            alpha.FL_Drive.setPower(0);
            alpha.FR_Drive.setPower(0);
            alpha.RL_Drive.setPower(0);
            alpha.RR_Drive.setPower(0);

            // Turn off RUN_TO_POSITION
            alpha.FL_Drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            alpha.FR_Drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            alpha.RL_Drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            alpha.RR_Drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        }
    }
}
