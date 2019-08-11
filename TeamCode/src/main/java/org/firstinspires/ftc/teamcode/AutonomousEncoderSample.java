package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "Demo Encoder", group = "Autonomous")
public class AutonomousEncoderSample extends LinearOpMode {

    private AlphaGoHardware alpha = new AlphaGoHardware();

    static final double TICKS_PER_REV = 383.6;//Tick per revolution

    @Override
    public void runOpMode() {

        alpha.init(hardwareMap);
        waitForStart();



    }
}
