package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="AutoDriveEncoder", group="Autonomous")
public class AutoDriveByEncoder extends LinearOpMode {

    AlphaGoHardware alpha = new AlphaGoHardware();
    MethodsClass mclass = new MethodsClass();

    @Override
    public void runOpMode() {

        alpha.init(hardwareMap);
        mclass.initecoder();

        waitForStart();

        mclass.encoderDrive(mclass.DRIVE_SPEED,  10,  10, 4.0);

    }
}
