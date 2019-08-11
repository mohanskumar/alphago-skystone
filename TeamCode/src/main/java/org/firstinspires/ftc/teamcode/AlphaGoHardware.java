package org.firstinspires.ftc.teamcode;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class AlphaGoHardware {

    public DcMotor leftDrive;
    public DcMotor rightDrive;
    public DcMotor rearLeftDrive = null;
    public DcMotor rearRightDrive = null;
    public DcMotor frontLeftDrive = null;
    public DcMotor frontRightDrive = null;

    HardwareMap hMap = null;

    public AlphaGoHardware() {

    }

    public void init(HardwareMap ahwMap) {
        hMap = ahwMap;

        //leftDrive = hMap.dcMotor.get("left_drive");
        //rightDrive = hMap.dcMotor.get("right_drive");
        rearLeftDrive = hMap.dcMotor.get("rear_left_drive");
        rearRightDrive = hMap.dcMotor.get("rear_right_drive");
        frontLeftDrive = hMap.dcMotor.get("front_left_drive");
        frontRightDrive = hMap.dcMotor.get("front_right_drive");

    }
}
