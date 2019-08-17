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
    private MethodsClass mclass = new MethodsClass();

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        alpha.init(hardwareMap);
        mclass.stopall();

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {
            mclass.drivemecanum();
        }
    }
}
