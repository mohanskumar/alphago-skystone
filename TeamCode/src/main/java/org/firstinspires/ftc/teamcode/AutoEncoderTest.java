package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "Drive by Encoder", group = "Autonomous")
public class AutoEncoderTest extends LinearOpMode {

    AlphaDriveLibrary alphaDriveLib;

    @Override
    public void runOpMode() {
        alphaDriveLib = new AlphaDriveLibrary(hardwareMap, telemetry);
        //Start
        waitForStart();

        alphaDriveLib.goForward(opModeIsActive(),  10);
        sleep(2000);
        alphaDriveLib.goBackward(opModeIsActive(),  10);

        telemetry.addData("Path", "Complete");
        telemetry.update();
    }
}
