// NOTES
// THIS TEST CODE WILL TRY TO PLACE SPECIMEN ON HC, PLACE YELLOW SAMPLE INTO HB, AND REACH A LVL 1 ASCENT, GETTING 21 POINTS PRIOR TO TELEOP.
// TUESDAY 11/12 AGENDA: ADD RIGHT / LEFT STRAFE TO BEGINNING TO LINE UP WITH HC & HB. (TWO SEPERATE FILES WILL BE MADE IF NEEDED.)
// PLEASE TEST THIS FILE PRIOR TO BEDFORD COMPETITION, PREFERABLY TUESDAY
// ARM ROTATE MUST BE IN HOME POSITION PRIOR TO TELEOP. IF IT IS NOT, NOTIFY THE DRIVER TO BRING IN THE ARM ROTATE WITH CONTROLS ON "gamepad.1"
// Specimen MUST be placed into the robot’s intake PRIOR to use
// Please place robot right in front of the HC
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
public class DriveRightSpecimenAutonoumous extends LinearOpMode {

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
      
    
        // Step 1: Stafe Right To Line Up With HC (Time = 0.5 Seconds / 30 Seconds)
        leftFrontDrive.setPower(0.5);
        leftBackDrive.setPower(-0.5);
        rightFrontDrive.setPower(0.5);
        rightBackDrive.setPower(-0.5);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 0.5)) {
            telemetry.addData("Path", "Leg 1: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        // Step 1:  Drive forward for a second (Time = 1.5 Seconds / 30 Seconds)
        leftFrontDrive.setPower(0.5);
        leftBackDrive.setPower(0.5);
        rightFrontDrive.setPower(0.5);
        rightBackDrive.setPower(0.5);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1.0)) {
            telemetry.addData("Path", "Leg 1: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        // Step 2:  Rotate Intake Arm to Middle Position (Time = 2.25 Seconds / 30 Seconds)
        armRotate.setTargetPosition(.5);
        armRotate.setPower(1);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 0.75)) {
            telemetry.addData("Path", "Leg 2: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        // Step 3:  Extend arm to get ready to place specimen (Time = 3.25 Seconds / 30 Seconds)
        leftExtend.setPower(.5);
        rightExtend.setPower(.5);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1.0)) {
            telemetry.addData("Path", "Leg 3: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        // Step 4:  Drive Forward to Further Continue to Place on High Chamber (HC) (Time = 3.75 Seconds / 30 Seconds)
        leftFrontDrive.setPower(0.5);
        leftBackDrive.setPower(0.5);
        rightFrontDrive.setPower(0.5);
        rightBackDrive.setPower(0.5);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 0.5)) {
            telemetry.addData("Path", "Leg 4: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        // Step 5:  Bring down lift to place specimen on high bar (Time = 4.75 Seconds / 30 Seconds)
       leftExtend.setPower(-0.5);
       rightExtend.setPower(-0.5);
       runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1.0)) {
            telemetry.addData("Path", "Leg 5: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        // Step 6:  Drive Backward To Prepare to Put Yellow Sample into HB (Time = 5.25 Seconds / 30 Seconds)
        leftFrontDrive.setPower(-0.5);
        leftBackDrive.setPower(-0.5);
        rightFrontDrive.setPower(-0.5);
        rightBackDrive.setPower(-0.5);
        armRotate.setTargetPosition(0);
        armRotate.setPower(-1);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 0.5)) {
            telemetry.addData("Path", "Leg 6: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        // Step 7:  Drive to Left Spikes & Drop Servo Arm to Pick up Yellow Sample (Time = 6.75 Seconds / 30 Seconds)
        leftFrontDrive.setPower(-0.5);
        leftBackDrive.setPower(0.5);
        rightFrontDrive.setPower(-0.5);
        rightBackDrive.setPower(0.5);
        armRotate.setTargetPosition(1);
        armRotate.setPower(1);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1.5)) {
            telemetry.addData("Path", "Leg 7: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        // Step 8:  Turn on INFEED and Drive through yellow sample to pick up (Time = 7.25 Seconds / 30 Seconds)
        intakeServo.setPower(-0.5);
        leftFrontDrive.setPower(0.5)
        leftBackDrive.setPower(0.5);
        rightFrontDrive.setPower(0.5);
        rightBackDrive.setPower(0.5);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 0.5)) {
            telemetry.addData("Path", "Leg 8: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }        

        // Step 9:  Turn 180 degrees to line up with HB (Time = 7.75 Seconds / 30 Seconds)
        armRotate.setTargetPosition(0.5);
        armRotate.setPower(-1);
        leftFrontDrive.setPower(0.5)
        leftBackDrive.setPower(0.5);
        rightFrontDrive.setPower(-0.5);
        rightBackDrive.setPower(-0.5);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 0.5)) {
            telemetry.addData("Path", "Leg 9: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }                

        // Step 10:  Turn Right line up with HB (Time = 8.00 Seconds / 30 Seconds)
        leftFrontDrive.setPower(0.5);
        leftBackDrive.setPower(-0.5);
        rightFrontDrive.setPower(0.5);
        rightBackDrive.setPower(-0.5);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 0.25)) {
            telemetry.addData("Path", "Leg 10: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }           

        // Step 11:  Extend Lift to Place Sample into HB (Time = 8.50 Seconds / 30 Seconds)
        rightExtend.setPower(0.5)
        leftExtend.setPower(-0.5);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 0.5)) {
            telemetry.addData("Path", "Leg 11: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }   

        // Step 12:  Extend Servo Arm Place Sample into HB (Time = 8.75 Seconds / 30 Seconds)
        extendServo.setPower(0.5);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 0.25)) {
            telemetry.addData("Path", "Leg 12: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        // Step 13:  Drive SLIGHTLY Forward to line up with HB (Time = 8.85 Seconds / 30 Seconds)
        leftFrontDrive.setPower(0.5);
        leftBackDrive.setPower(0.5);
        righttFrontDrive.setPower(0.5);
        rightBackDrive.setPower(0.5);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 0.1)) {
            telemetry.addData("Path", "Leg 13: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        // Step 14:  Drop Sample into HB (Time = 9.35 Seconds / 30 Seconds)
        intakeServo.setPower(-0.5);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 0.5)) {
            telemetry.addData("Path", "Leg 14: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        // Step 15:  Drive back & bring everything in (Time = 10.35 Seconds / 30 Seconds)
        leftFrontDrive.setPower(-0.5);
        leftBackDrive.setPower(-0.5);
        rightFrontDrive.setPower(-0.5);
        rightBackDrive.setPower(-0.5);
        rightExtend.setPower(-0.5);
        leftExtend.setPower(-0.5);
        extendServo.setPower(-0.5)
        armRotate.setTargetPosition(0);
        armRotate.setPower(-0.5);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1.0)) {
            telemetry.addData("Path", "Leg 15: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        // Step 16:  Drive backward to line up for LVL 1 ascent (Time = 11.85 Seconds / 30 Seconds)
        leftFrontDrive.setPower(-0.5);
        leftBackDrive.setPower(-0.5);
        rightFrontDrive.setPower(-0.5);
        rightBackDrive.setPower(-0.5);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1.5)) {
            telemetry.addData("Path", "Leg 16: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        // Step 17:  Rotate -90 Degrees line up for LVL 1 ascent (Time = 12.35 Seconds / 30 Seconds)
        leftFrontDrive.setPower(-0.5);
        leftBackDrive.setPower(-0.5);
        rightFrontDrive.setPower(0.5);
        rightBackDrive.setPower(0.5);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 0.5)) {
            telemetry.addData("Path", "Leg 17: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        // Step 18:  Drive forward & extend lift to line up for LVL 1 ascent (Time = 15.85 Seconds / 30 Seconds)
        leftFrontDrive.setPower(0.5);
        leftBackDrive.setPower(0.5);
        rightFrontDrive.setPower(0.5);
        rightBackDrive.setPower(0.5);
        rightExtend.setPower(0.2);
        leftExtend.setPower(0.2);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 3.5)) {
            telemetry.addData("Path", "Leg 18: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        
        // End Of Autonomous (End Time = 15.85 Seconds / 30 Seconds)
        telemetry.addData("Autonomous", "Complete");
        telemetry.addData("Good Luck Drive Team!");
        telemetry.update();
        sleep(1000);
    }
}
