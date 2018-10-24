package org.firstinspires.ftc.teamcode.First;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name= "OutReachTestTeleOp", group = "Linear OpMode")

public class OutReachTestTeleOp extends LinearOpMode {
        //Initializing The Robot

        private ElapsedTime runtime = new ElapsedTime();

        //Declare Motors
        private DcMotor BackLeft;
        private DcMotor BackRight;
        private DcMotor FrontLeft;
        private DcMotor FrontRight;

        private Servo HopperServo;

        @Override
        public void runOpMode() throws InterruptedException {
            telemetry.addData("Status", "Initialized");
            telemetry.update();

            //HW Map DC Motors
            FrontLeft = hardwareMap.dcMotor.get("FrontLeft");
            FrontRight = hardwareMap.dcMotor.get("FrontRight");
            BackLeft = hardwareMap.dcMotor.get("BackLeft");
            BackRight = hardwareMap.dcMotor.get("BackRight");

            FrontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
            BackLeft.setDirection(DcMotorSimple.Direction.REVERSE);

            HopperServo = hardwareMap.servo.get("HopperServo");

            waitForStart();
            runtime.reset();

            while (opModeIsActive()) {

                if (gamepad1.left_stick_x != 0 || gamepad1.left_stick_y != 0) /*If the left stick is not neutral...*/ {
                    double drive = -gamepad1.left_stick_y; //Set the Drive to the negative value of the y-axis value
                    double turn = gamepad1.left_stick_x; //Set the turn to the value of the x-axis value

                    double leftPower;
                    double rightPower;
                    leftPower = Range.clip(drive + turn, -1.0, 1.0); //fun math
                    rightPower = Range.clip(drive - turn, -1.0, 1.0); //fun math 2

                    FrontLeft.setPower(leftPower * 1.5);
                    FrontRight.setPower(rightPower * 1.5);
                    BackLeft.setPower(leftPower * 1.5);
                    BackRight.setPower(rightPower * 1.5);
                }
                if (gamepad1.left_stick_x == 0 || gamepad1.left_stick_y == 0){

                    FrontLeft.setPower(0);
                    FrontRight.setPower(0);
                    BackLeft.setPower(0);
                    BackRight.setPower(0);
                }
            }


    }

}
