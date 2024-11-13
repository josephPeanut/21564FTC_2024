// package
package org.firstinspires.ftc.teamcode;
// import
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Robot: Auto Drive By Time", group="Robot")
@Disabled
public class DosSampleAuto extends LinearOpMode {

    /* Declare OpMode members. */
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftFrontDrive = null;
    private DcMotor leftBackDrive = null;
    private DcMotor leftExtend = null;
    private DcMotor rightFrontDrive = null;
    private DcMotor rightBackDrive = null;
    private DcMotor rightExtend = null;
    private DcMotor armRotate = null;
    private CRServo intakeServo = null;
    private Servo extendServo = null;
    double extendServoPosition = 0;
    double leftPosition;
    double rightPosition;
    int setRightPosition = 0;
    int setLeftPosition = 0;
    int armRotateSetPosition = 0;
    int armRotatePosition = 0;

    
    @Override
    public void runOpMode() {

        // Initialize variables
        leftFrontDrive  = hardwareMap.get(DcMotor.class, "left_front_drive");
        leftBackDrive  = hardwareMap.get(DcMotor.class, "left_back_drive");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "right_front_drive");
        rightBackDrive = hardwareMap.get(DcMotor.class, "right_back_drive");
        leftExtend = hardwareMap.get(DcMotor.class, "left_extend");
        rightExtend = hardwareMap.get(DcMotor.class, "right_extend");
        armRotate = hardwareMap.get(DcMotor.class, "armRotate");
        intakeServo = hardwareMap.get(CRServo.class, "intakeServo");
        extendServo = hardwareMap.get(Servo.class, "extendServo");

        // Set Motor Directions
        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // Step 1:  Drive forward for 3 seconds
        leftFrontDrive.setPower(0.5);
        leftBackDrive.setPower(0.5);
        rightFrontDrive.setPower(0.5);
        rightBackDrive.setPower(0.5);
        
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 3.0)) {
            telemetry.addData("Path", "Leg 1: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        leftFrontDrive.setPower(-0.5);
        leftBackDrive.setPower(-0.5);
        rightFrontDrive.setPower(0.5);
        rightBackDrive.setPower(0.5);
        extendServo.setPower(0.5);
        armRotate.setTargetPosition(0.5);
        armRotate.setPower(1);
        
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1.5)) {
            telemetry.addData("Path", "Leg 1: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        leftFrontDrive.setPower(0.5);
        leftBackDrive.setPower(0.5);
        rightFrontDrive.setPower(0.5);
        rightBackDrive.setPower(0.5);
        
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 0.5)) {
            telemetry.addData("Path", "Leg 1: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        leftFrontDrive.setPower(0.5);
        leftBackDrive.setPower(0.5);
        rightFrontDrive.setPower(0.5);
        rightBackDrive.setPower(0.5);
        
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 0.25)) {
            telemetry.addData("Path", "Leg 1: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }


        leftFrontDrive.setPower(0.5);
        leftBackDrive.setPower(0.5);
        rightFrontDrive.setPower(0.5);
        rightBackDrive.setPower(0.5);
    
        
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 0.25)) {
            telemetry.addData("Path", "Leg 1: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        leftFrontDrive.setPower(0.5);
        leftBackDrive.setPower(0.5);
        rightFrontDrive.setPower(0.5);
        rightBackDrive.setPower(0.5);
        rightExtend.setPower(0.5);
        leftExtend.setPower(0.5);
        
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 0.50)) {
            telemetry.addData("Path", "Leg 1: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }


        intakeServo.setPower(.25);
        
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1)) {
            telemetry.addData("Path", "Leg 1: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }


        rightExtend.setPower(-1);
        leftExtend.setPower(-1);
        extendServo.setPower(-1);
        leftFrontDrive.setPower(-0.25);
        leftBackDrive.setPower(-0.25);
        rightFrontDrive.setPower(0.25);
        rightBackDrive.setPower(0.25);
    
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1)) {
            telemetry.addData("Path", "Leg 1: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        armRotate.setTargetPosition(1);
        armRotate.setPower(1);
        
    
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1)) {
            telemetry.addData("Path", "Leg 1: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }


        leftFrontDrive.setPower(0.5);
        leftBackDrive.setPower(0.5);
        rightFrontDrive.setPower(0.5);
        rightBackDrive.setPower(0.5);
        intakeServo(1);
        
    
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1)) {
            telemetry.addData("Path", "Leg 1: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }






        
        // End of Auto
        leftDrive.setPower(0);
        rightDrive.setPower(0);

        telemetry.addData("Path", "Complete");
        telemetry.addData("Good Luck Drive Team!", "Please Don't Panic, Stay Calm");
        telemetry.update();
        sleep(1000);
    }
}
