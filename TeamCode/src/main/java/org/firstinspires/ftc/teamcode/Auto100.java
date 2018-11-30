package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Auto 100", group="Iterative Opmode")
@Disabled
public class Auto100 extends OpMode {
    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private DcMotor leftMech = null;
    private DcMotor rightMech = null;

    private Servo lockServo0 = null;
    private Servo lockServo1 = null;

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
        lockServo0 = hardwareMap.get(Servo.class, "lock_servo_0");
        lockServo1 = hardwareMap.get(Servo.class, "lock_servo_1");
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

    public void start() {
        runtime.reset();

        // unlock the servos so that the mechanism unfolds
        telemetry.addData("Action", "Unlocking Mechanism...");
        lockServo0.setPosition(1);
        lockServo1.setPosition(1);
        telemetry.addData("Action", "Mechanism Unlocked.");

        telemetry.addData("Action", "Unfolding Mechanism...");
        leftMech.setPower(-0.1);
        rightMech.setPower(-0.1);
        double currentMillis = runtime.milliseconds();

        int UNFOLD_DELAY = 500;

        while(runtime.milliseconds() < currentMillis + UNFOLD_DELAY);
        leftMech.setPower(0);
        rightMech.setPower(0);
        telemetry.addData("Action", "Mechanism Unfolded.");
    }

    @Override
    public void loop() {
        telemetry.addData("Notice", "Waiting for Autonomous Completion...");
    }
}
