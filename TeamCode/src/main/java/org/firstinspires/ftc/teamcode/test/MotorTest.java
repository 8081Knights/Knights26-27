package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp(name="Robot: Motor Test", group="Robot")

public class MotorTest extends OpMode {

DcMotorEx FLdrive = null;
DcMotorEx FRdrive = null;
DcMotorEx BLdrive = null;
DcMotorEx BRdrive = null;

double FR;
double FL;
double BR;
double BL;



    @Override
    public void init() {
        FLdrive  = hardwareMap.get(DcMotorEx.class, "FLdrive");
        FRdrive  = hardwareMap.get(DcMotorEx.class, "FRdrive");
        BLdrive  = hardwareMap.get(DcMotorEx.class, "BLdrive");
        BRdrive  = hardwareMap.get(DcMotorEx.class, "BRdrive");

        FLdrive.setDirection(DcMotorEx.Direction.FORWARD);
        BLdrive.setDirection(DcMotorEx.Direction.FORWARD);
        FRdrive.setDirection(DcMotorEx.Direction.FORWARD);
        BRdrive.setDirection(DcMotorEx.Direction.FORWARD);
    }

    @Override
    public void loop() {
        FL = -gamepad1.left_stick_y;
        BL = -gamepad1.left_stick_x;
        FR = -gamepad1.right_stick_y;
        BR = -gamepad1.right_stick_x;

        FLdrive.setPower(FL);
        FRdrive.setPower(FR);
        BLdrive.setPower(BL);
        BRdrive.setPower(BR);



    }
}
