// Code Package
package org.firstinspires.ftc.teamcode;

// Import
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Basic: Omni Linear OpMode", group="Linear OpMode")

// Class 
public class Code2024 extends LinearOpMode {

    // Declare All OpMode Members (Lines 19-35)
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

        // Initialize the hardware variables. Note that the strings used here must correspond
        // to the names assigned during the robot configuration step on the DS or RC devices.
        leftFrontDrive  = hardwareMap.get(DcMotor.class, "left_front_drive");
        leftBackDrive  = hardwareMap.get(DcMotor.class, "left_back_drive");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "right_front_drive");
        rightBackDrive = hardwareMap.get(DcMotor.class, "right_back_drive");
        leftExtend = hardwareMap.get(DcMotor.class, "left_extend");
        rightExtend = hardwareMap.get(DcMotor.class, "right_extend");
        armRotate = hardwareMap.get(DcMotor.class, "armRotate");
        intakeServo = hardwareMap.get(CRServo.class, "intakeServo");
        extendServo = hardwareMap.get(Servo.class, "extendServo");
        
        //Servos
        //testServo = hardwareMap.get()

      
        leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        leftBackDrive.setDirection(DcMotor.Direction.REVERSE);
        rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotor.Direction.FORWARD);
        leftExtend.setDirection(DcMotor.Direction.FORWARD);
        rightExtend.setDirection(DcMotor.Direction.REVERSE);

        // Wait for the game to start (driver presses PLAY)
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        runtime.reset();
        
        // left extend
        leftExtend.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftExtend.setTargetPosition(0);
        leftExtend.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        // right extend
        rightExtend.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightExtend.setTargetPosition(0);
        rightExtend.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        
        armRotate.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armRotate.setTargetPosition(0);
        armRotate.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        
        
        
        
        
        
        // armRotate.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            
            rightPosition = rightExtend.getCurrentPosition();
            leftPosition = leftExtend.getCurrentPosition();
            armRotatePosition = armRotate.getCurrentPosition();
            
            double max;

            // POV Mode uses left joystick to go forward & strafe, and right joystick to rotate.
            double axial   = -gamepad1.left_stick_y;  // Note: pushing stick forward gives negative value
            double lateral =  gamepad1.left_stick_x;
            double yaw     =  gamepad1.right_stick_x;

            // Combine the joystick requests for each axis-motion to determine each wheel's power.
            // Set up a variable for each drive wheel to save the power level for telemetry.
            double leftFrontPower  = axial + lateral + yaw;
            double rightFrontPower = axial - lateral - yaw;
            double leftBackPower   = axial - lateral + yaw;
            double rightBackPower  = axial + lateral - yaw;

            // Normalize the values so no wheel power exceeds 100%
            // This ensures that the robot maintains the desired motion.
            max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
            max = Math.max(max, Math.abs(leftBackPower));
            max = Math.max(max, Math.abs(rightBackPower));

            if (max > 1.0) {
                leftFrontPower  /= max;
                rightFrontPower /= max;
                leftBackPower   /= max;
                rightBackPower  /= max;
            }
                
    
            // Send calculated power to wheels
            leftFrontDrive.setPower(leftFrontPower);
            rightFrontDrive.setPower(rightFrontPower);
            leftBackDrive.setPower(leftBackPower);
            rightBackDrive.setPower(rightBackPower);
    
    // LIFT ARM
            if(gamepad2.dpad_up){
               //setLeftPosition = 3000;
               leftExtend.setPower(.8);
               rightExtend.setPower(.8);
            } else if (gamepad2.dpad_down){
                //setLeftPosition = 0;
                leftExtend.setPower(-.5);
               rightExtend.setPower(-.5);
            }
            else{
                leftExtend.setPower(0.05);
                rightExtend.setPower(0.05);
            }
            
            // CrServo
            // INFEED
            if(gamepad2.left_bumper){
                intakeServo.setPower(-1);
            // OUTFEED
            } else if(gamepad2.right_bumper){
                intakeServo.setPower(1);
            } else{
                intakeServo.setPower(0);
            }
            
            // arm rotate variable
            // if(gamepad1.dpad_right){
            //     armRotate.setPower(.75);
            // } else if(gamepad1.dpad_left){
            //     armRotate.setPower(-.75);
            // } else{
            //     armRotate.setPower(0);
           // }
           
           //ROTATE ARM
           //FORWARD POSITION
            if(gamepad2.x){
                armRotateSetPosition = 1600; 
            //REVERSE POSITION
            }else if(gamepad2.b){
                armRotateSetPosition = 0;           
            // MIDDLE POSITION
            }else if (gamepad2.y){
                armRotateSetPosition = 800;
            }
            
            
            armRotate.setTargetPosition(armRotateSetPosition);
            armRotate.setPower(1.0);
        
            /* Setting "extendServoPosition" to .25 or .75
            NORMAL SERVO
            ARM WITH INTAKE */
            if(gamepad2.dpad_left){
                extendServoPosition = .25;
            } else if(gamepad2.dpad_right){
                extendServoPosition = .75;
            }
            extendServo.setPosition(extendServoPosition);

            // Right Extend
            // rightExtend.setTargetPosition(setRightPosition);
            // rightExtend.setPower(0.5);
            // Left Extend
            // leftExtend.setTargetPosition(setLeftPosition);
            // leftExtend.setPower(.3);
            
            //leftExtend.setPower(rightExtend.getPower());

            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Front left/Right", "%4.2f, %4.2f", leftFrontPower, rightFrontPower);
            telemetry.addData("Back  left/Right", "%4.2f, %4.2f", leftBackPower, rightBackPower);
            telemetry.addData("Right Position", rightPosition);
            telemetry.addData("Left Position", leftPosition);
            telemetry.addData("Right Position Set To", setRightPosition);
            telemetry.addData("Left Position Set To", setLeftPosition);
            telemetry.addData("armRotatePosition",armRotatePosition);
            telemetry.update();
            
        }
    }}
