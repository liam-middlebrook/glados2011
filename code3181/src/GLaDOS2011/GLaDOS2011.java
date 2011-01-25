package GLaDOS2011;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.DriverStation;
import GLaDOS2011.autono.*; // autonomous files
import GLaDOS2011.util.*; // useful utilities

/**
 * The main class for our robot
 * @author Chris Cheng
 * @author Ben
 * @author Eric
 */
public class GLaDOS2011 extends IterativeRobot {
    // <editor-fold defaultstate="collapsed" desc="Variables">
    // Autonomous mode
    int autonoMode = 0;
    // Driver station
    DriverStation driverStation;
    // Low speed for testing
    final double LOW_SPEED = .1;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="public void robotInit()">
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        driverStation = DriverStation.getInstance();
        Hardware.txtout.clearOutput();
        Hardware.txtout.say(1, "State:    DISABLED    ");
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Autonomous Methods">
    // <editor-fold defaultstate="collapsed" desc="public void autonomousInit()">
    /**
     * This function is run when autonomous mode begins. It determines which
     * autonomous mode we are using.
     */
    public void autonomousInit() {
        Hardware.txtout.clearOutput();
        // Determine which autonomous mode we want and tell the drivers
        // The switches are the binary representation of the autonomous mode
        // Left worth 4
        autonoMode = autonoMode + 4 * Utils.toInt(Hardware.autonoSwitches[0].get());
        // Middle worth 2
        autonoMode = autonoMode + 2 * Utils.toInt(Hardware.autonoSwitches[1].get());
        // Right worth 1
        autonoMode = autonoMode + 1 * Utils.toInt(Hardware.autonoSwitches[2].get());

        Hardware.txtout.say(1, "State:  AUTONOMOUS " + autonoMode + "  ");
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="public void autonomousPeriodic()">
    /**
     * This function is called periodically during autonomous. It runs the
     * selected autonomous mode.
     */
    public void autonomousPeriodic() {
        //Run the autonomous mode that was selected
        switch (autonoMode) {
            case 0:
                Autono0.run();
                break;
            case 1:
                Autono1.run();
                break;
            case 2:
                Autono2.run();
                break;
            case 3:
                Autono3.run();
                break;
            case 4:
                Autono4.run();
                break;
        }
    }
    // </editor-fold>
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Teleoperated Methods">

    // <editor-fold defaultstate="collapsed" desc="public void teleopInit()">
    public void teleopInit() {
        Hardware.txtout.clearOutput();
        Hardware.txtout.say(1, "State:  TELEOPERATED  ");
        // Test lines
        for(int i=2; i<7; i++)
            Hardware.txtout.say(i, "This is line "+i+".");
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="public void teleopPeriodic()">
    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        // Drive
        if(Hardware.checkButton(2, Hardware.LEFT)){
            // Low speed button for testing
            Hardware.drive.driveAtSpeed(LOW_SPEED, 0);
            Hardware.txtout.say(4, "Low speed left        ");
        }
        if(Hardware.checkButton(2, Hardware.RIGHT)){
            // Low speed button for testing
            Hardware.drive.driveAtSpeed(0, LOW_SPEED);
            Hardware.txtout.say(4, "Low speed right       ");
        }
        if(Hardware.checkButton(1)){
            // 70% speed button
            Hardware.drive.driveAtSpeed(Hardware.leftJoystick.getY() * .7, Hardware.rightJoystick.getY() * .7);
            Hardware.txtout.say(4, "70% speed             ");
        } else {
            // Regular speed
            Hardware.drive.driveAtSpeed(Hardware.leftJoystick.getY(), Hardware.rightJoystick.getY());
            Hardware.txtout.say(4, "Regular drive         ");
        }
        if(Hardware.checkButton(3)){
            // Stop button
            Hardware.drive.stop();
        }

        // Calls method from lifter that controls the the forklift
        Lifter.controlLifter();
    }
    // </editor-fold>
    // </editor-fold>

}
