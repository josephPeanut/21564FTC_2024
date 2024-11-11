// last worked on 11/10/24
// TUESDAY 11/12 AGENDA: ADD RIGHT / LEFT STRAFE TO BEGINNING TO LINE UP WITH HC. (TWO SEPERATE FILES WILL BE MADE IF NEEDED.)
// NOTES
// THIS FILE SHOULD SCORE 13 POINTS IF DONE CORRECTLY
// PLEASE TEST THIS FILE PRIOR TO BEDFORD COMPETITION, PREFERABLY TUESDAY
// ARM ROTATE MUST BE IN HOME POSITION PRIOR TO TELEOP. IF IT IS NOT, NOTIFY THE DRIVER TO BRING IN THE ARM ROTATE WITH CONTROLS ON "gamepad.1"
// Specimen MUST be placed into the robot’s intake PRIOR to use
// Please place robot right in front of the HC (High Chamber)
// Motor Speed is set right before the while statement.
// The number right after the '<' symbol is the amount of time the motor speed will be set for (counted in seconds)

// package
package  org.firstinspires.ftc.teamcode;

// imports
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

// no encoder use

@Autonomous(name="Robot: Auto Drive By Time", group="Robot")
@Disabled
public class SpecimenAutonoumous extends LinearOpMode {

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

        // Initialize the drive system variables.
        leftFrontDrive  = hardwareMap.get(DcMotor.class, "left_front_drive");
        leftBackDrive  = hardwareMap.get(DcMotor.class, "left_back_drive");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "right_front_drive");
        rightBackDrive = hardwareMap.get(DcMotor.class, "right_back_drive");
        leftExtend = hardwareMap.get(DcMotor.class, "left_extend");
        rightExtend = hardwareMap.get(DcMotor.class, "right_extend");
        armRotate = hardwareMap.get(DcMotor.class, "armRotate");
        intakeServo = hardwareMap.get(CRServo.class, "intakeServo");
        extendServo = hardwareMap.get(Servo.class, "extendServo");

        // To drive forward, most robots need the motor on one side to be reversed, because the axles point in opposite directions.
        // When run, this OpMode should start both motors driving forward. So adjust these two lines based on your first test drive.
        // Note: The settings here assume direct drive on left and right wheels.  Gear Reduction or 90 Deg drives may require direction flips
        leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        leftBackDrive.setDirection(DcMotor.Direction.REVERSE);
        rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotor.Direction.FORWARD);
        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");    
        telemetry.update();

        // Wait for the game to start (driver presses START)
        waitForStart();

        // Step through each leg of the path, ensuring that the OpMode has not been stopped along the way.

        // Step 1:  Drive forward for a second (Time = 1.0 Seconds / 30 Seconds)
        leftFrontDrive.setPower(0.5);
        leftBackDrive.setPower(0.5);
        rightFrontDrive.setPower(0.5);
        rightBackDrive.setPower(0.5);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1.0)) {
            telemetry.addData("Path", "Leg 1: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        // Step 2:  Rotate Intake Arm to Middle Position (Time = 1.75 Seconds / 30 Seconds)
        armRotate.setTargetPosition(.5);
        armRotate.setPower(1);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 0.75)) {
            telemetry.addData("Path", "Leg 2: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        // Step 3:  Extend arm to get ready to place specimen (Time = 2.75 Seconds / 30 Seconds)
        leftExtend.setPower(.5);
        rightExtend.setPower(.5);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1.0)) {
            telemetry.addData("Path", "Leg 3: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        // Step 4:  Drive Forward to Further Continue to Place on High Chamber (HC) (Time = 2.25 Seconds / 30 Seconds)
        leftFrontDrive.setPower(0.5);
        leftBackDrive.setPower(0.5);
        rightFrontDrive.setPower(0.5);
        rightBackDrive.setPower(0.5);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 0.5)) {
            telemetry.addData("Path", "Leg 4: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        // Step 5:  Bring down lift to place specimen on high bar (Time = 3.25 Seconds / 30 Seconds)
       leftExtend.setPower(-0.5);
       rightExtend.setPower(-0.5);
       runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1.0)) {
            telemetry.addData("Path", "Leg 5: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        // Step 6:  Drive Backward To Line Up With Observation Zone To Park & Bring in Arm Rotation (Time = 4.15 Seconds / 30 Seconds)
        leftFrontDrive.setPower(0.5);
        leftBackDrive.setPower(0.5);
        rightFrontDrive.setPower(0.5);
        rightBackDrive.setPower(0.5);
        armRotate.setTargetPosition(0);
        armRotate.setPower(-1);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1.0)) {
            telemetry.addData("Path", "Leg 6: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        // Step 7:  Drive To The Right To Park In The Observation Zone (Time = 6.15 Seconds / 30 Seconds)
        leftFrontDrive.setPower(0.5);
        leftBackDrive.setPower(-0.5);
        rightFrontDrive.setPower(0.5);
        rightBackDrive.setPower(-0.5);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 2.0)) {
            telemetry.addData("Path", "Leg 7: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }



        // End Of Autonomous (End Time = 6.15 Seconds / 30 Seconds)
        telemetry.addData("Autonomous", "Complete");
        telemetry.addData("Good Luck!");
        telemetry.update();
        sleep(1000);
    }
}
