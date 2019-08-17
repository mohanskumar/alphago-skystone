package org.firstinspires.ftc.teamcode;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class AlphaGoHardware {

    public DcMotor leftDrive;
    public DcMotor rightDrive;
    public DcMotor RL_Drive = null;
    public DcMotor RR_Drive = null;
    public DcMotor FL_Drive = null;
    public DcMotor FR_Drive = null;

    HardwareMap hMap = null;

    public AlphaGoHardware() {

    }

    public void initBasic(HardwareMap ahwMap) {
        hMap = ahwMap;

        leftDrive = hMap.dcMotor.get("left_drive");
        rightDrive = hMap.dcMotor.get("right_drive");

        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);

    }

    public void initMecanum(HardwareMap bhwMap) {
        hMap = bhwMap;

        RL_Drive = hMap.dcMotor.get("RL_drive");
        RR_Drive = hMap.dcMotor.get("RR_drive");
        FL_Drive = hMap.dcMotor.get("FL_drive");
        FR_Drive = hMap.dcMotor.get("FR_drive");

        FL_Drive.setDirection(DcMotor.Direction.FORWARD);
        RL_Drive.setDirection(DcMotor.Direction.FORWARD);
        FR_Drive.setDirection(DcMotor.Direction.REVERSE);
        RR_Drive.setDirection(DcMotor.Direction.REVERSE);

    }
}
