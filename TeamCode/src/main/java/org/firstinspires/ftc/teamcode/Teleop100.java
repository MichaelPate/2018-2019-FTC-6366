/*
    Teleop code for Team 6366
    Created by Michael Pate on November 26, 2018
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Teleop 100", group="Iterative Opmode")
@Disabled
public class Teleop100 extends OpMode
{
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private DcMotor leftMech = null;
    private DcMotor rightMech = null;

    private Servo colorServo = null;
    ColorSensor colorSensor = null;

    @Override
    public void init() {
        telemetry.addData("Status", "Mapping Hardware...");
        leftDrive  = hardwareMap.get(DcMotor.class, "left_drive");
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");
        leftMech = hardwareMap.get(DcMotor.class, "left_mech");
        rightMech = hardwareMap.get(DcMotor.class, "right_mech");
        colorServo = hardwareMap.get(Servo.class, "color_servo");
        telemetry.addData("Status", "Hardware Mapped.");

        telemetry.addData("Status", "Initializing Color Sensor...");
        colorSensor = hardwareMap.colorSensor.get("color_sensor");
        telemetry.addData("Status", "Sensor on " + colorSensor.getI2cAddress());
        telemetry.addData("Status", "Color Sensor LED enabled.");
        colorSensor.enableLed(true);
        telemetry.addData("Status", "Color Sensor Ready.");

        telemetry.addData("Status", "Setting Motors...");
        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        leftMech.setDirection(DcMotor.Direction.FORWARD);
        rightMech.setDirection(DcMotor.Direction.REVERSE);
        telemetry.addData("Status", "Motors Set.");

        telemetry.addData("Status", "Robot Initialized.");
    }

    @Override
    public void start() {
        runtime.reset();
    }

    @Override
    public void loop() {
        telemetry.addData("Status", "Run Time: " + runtime.toString());

        leftDrive.setPower(-gamepad1.left_stick_y);
        rightDrive.setPower(-gamepad1.right_stick_y);

        leftMech.setPower(-gamepad2.left_stick_y);
        rightDrive.setPower(-gamepad2.left_stick_y);
    }

    @Override
    public void stop() {
        telemetry.addData("Status", "Done. Total Run Time: " + runtime.toString());
    }

}
