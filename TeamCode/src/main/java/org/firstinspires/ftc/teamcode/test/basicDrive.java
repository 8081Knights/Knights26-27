package org.firstinspires.ftc.teamcode.test;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.HardwareSoftware;
import org.firstinspires.ftc.vision.VisionProcessor;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagPoseFtc;
import org.firstinspires.ftc.vision.opencv.Circle;
import org.firstinspires.ftc.vision.opencv.ColorBlobLocatorProcessor;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@TeleOp(name = "Basic Drive")
public class basicDrive extends OpMode {
    //current teleop for matches, both colors
    //MATCHES
    //TODO: need to do auto for this comp
    // then after that get the hooded shooter and turntable to work
    // then make sure the other code(turn and go to ball, shooting autonomously, drive stuff)
    // are all standard and implemented properly
    HardwareSoftware robot = new HardwareSoftware();

    double[] initPositions = {0, 0, 0};

   // CurrentRobotPose currentPose = new CurrentRobotPose();

    DecimalFormat df = new DecimalFormat("#.##");

  //  SparkFunOTOS.Pose2D pos;

    double cTreshold = .5;

    boolean isMovingToSetPos = false;


    double diffGyroStartingPosX = 0;
    double diffGyroStartingPosY = 0;



    // assuming field size roughly 144 x 144 inches (FTC field)
//    Pose2D redTagPos = new Pose2D(DistanceUnit.INCH, 132, 120, AngleUnit.DEGREES, 225);
//    Pose2D blueTagPos = new Pose2D(DistanceUnit.INCH, 12, 120, AngleUnit.DEGREES, 315);
//    Pose2D currentTagPos = null;
//
//    Pose2D absPosOfGryoStart = null;
//
//    Pose2D absPosOfRobot = null;


    //telemetry, not used for calculations


    double cError;
    double cX;
    double cY;
    double cH;
    double autoFlywheelVelo = 700;


    private final double targetRampTime = 10;

    boolean servoAPressed = false;

    boolean servoBPressed = false;

    private boolean servoCPressed;

    private boolean isRamped = false;


//    private AprilTagWebcam aprilTagWebcam = new AprilTagWebcam();
//    private TurretPDTUNE turret = new TurretPDTUNE();

    //this is the red teleop code
    // it can detect balls by color,
    // use april tags,
    // go to relative position on field based on gyro-origin
    // WIP: know where it is and where its gyro-origin is
    // WIP: go to an absolute position on the field


    @Override
    public void init() {
        robot.init(hardwareMap);
//        aprilTagWebcam.init(hardwareMap, telemetry);
//        turret.init(robot.turret);

//        robot.initGyro();

//        currentPose.init(robot, initPositions[0], initPositions[1], initPositions[2]);
    }

    @Override
    public void loop() {
        telemetry.clear();
        manualMechanumDrive();
//        aprilTagWebcam.update();
//        List<AprilTagDetection> detections = aprilTagWebcam.getDetectedTags();
//
//        turretTag = null;
//
//        for (AprilTagDetection tag : detections) {
//            if (tag.id == blueTagId) {
//                turretTag = tag;
//                break;
//            }
//        }

        // telemetry.addData("Voltage", hardwareMap.voltageSensor.get("Control Hub").getVoltage());


        //can change to mechanum by changing line 247


        //telemetry.addData("Has motif: ", hasMotif);
        //telemetry.addData("motif: ", greenBallPos);


        //TODO: figure out where the launch zone is
        //either this should be based on the april tags, or just make sure that
        // the gyro is reset in the same spot every time


//        if (gamepad1.bWasPressed()) {
//            robot.gyro.resetTracking();
//            absPosOfRobot = null;
//            absPosOfGryoStart = null;
//            turretTag = null;
//        }


//

//


    }
//

    /**
     * this method handles headless math and controls robot
     */
    public void manualHeadlessDrive () {
        double y = -gamepad1.left_stick_y;
        double x = gamepad1.left_stick_x;
        double rx = gamepad1.right_stick_x;


        SparkFunOTOS.Pose2D pos = robot.gyro.getPosition();
        double botHeading = -pos.h;

        double theta = botHeading;

        theta = Math.toRadians(theta);

        double rotX = x * Math.cos(theta) - y * Math.sin(theta);
        double rotY = x * Math.sin(theta) + y * Math.cos(theta);

        double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1);
        robot.FLdrive.setPower(((rotY + rx + rotX) / denominator));
        robot.FRdrive.setPower(((rotY - rx - rotX) / denominator));
        robot.BLdrive.setPower(((rotY + rx - rotX) / denominator));
        robot.BRdrive.setPower(((rotY - rx + rotX) / denominator));
    }

    public void manualMechanumDrive () {
        double y = -gamepad1.left_stick_y;
        double x = gamepad1.left_stick_x;
        double rx = gamepad1.right_stick_x;

        robot.FLdrive.setPower(y + rx - x);
        robot.FRdrive.setPower(y + rx - x);
        robot.BLdrive.setPower(y - rx + x);
        robot.BRdrive.setPower(y + rx + x);
    }
    }


