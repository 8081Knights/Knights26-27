package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;


public class HardwareSoftware {

    private HardwareMap hw = null;


    //wheels
    public DcMotorEx FRdrive = null;
    public DcMotorEx BRdrive = null;
    public DcMotorEx BLdrive = null;
    public DcMotorEx FLdrive = null;


    public CRServo FRintake = null;
    public Servo Flip = null;


    public SparkFunOTOS gyro;



    /**
     * initializes the motors and servos
     *
     * @param ahw
     */
    public void init(HardwareMap ahw) {

        hw = ahw;

        FLdrive = hw.get(DcMotorEx.class, "FLdrive");
        FRdrive = hw.get(DcMotorEx.class, "FRdrive");
        BLdrive = hw.get(DcMotorEx.class, "BLdrive");
        BRdrive = hw.get(DcMotorEx.class, "BRdrive");


        //sorterTJ = new Sorter(hw, "sorterA", BallState.EMPTY, 0.7, 0.5, "ColorSensor1", "ColorSensor2");
        gyro = hw.get(SparkFunOTOS.class, "gyro");


        FLdrive.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        BRdrive.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        FRdrive.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        BLdrive.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);

        FLdrive.setDirection(DcMotorEx.Direction.REVERSE);
        BLdrive.setDirection(DcMotorEx.Direction.FORWARD);
        FRdrive.setDirection(DcMotorEx.Direction.REVERSE);
        BRdrive.setDirection(DcMotorEx.Direction.REVERSE);

        FLdrive.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        BRdrive.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        FRdrive.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        BLdrive.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);


    }

    public void initHalfGyro(){
        gyro.calibrateImu();
        gyro.resetTracking();

        gyro.setLinearUnit(DistanceUnit.INCH);
        gyro.setAngularUnit(AngleUnit.RADIANS);
        //was pi
        SparkFunOTOS.Pose2D offset = new SparkFunOTOS.Pose2D(0, 0, Math.PI);
        gyro.setOffset(offset);
        gyro.setLinearScalar(1.0);
        gyro.setAngularScalar(1.0);
        gyro.calibrateImu();
        gyro.resetTracking();
    }

    /**
     * initialize the gyro stuff
     */
    public void initGyro() {
        initHalfGyro();
        SparkFunOTOS.Pose2D currentPosition = new SparkFunOTOS.Pose2D(0, 0, 0);
        gyro.setPosition(currentPosition);
    }


}
