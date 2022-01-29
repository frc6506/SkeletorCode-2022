// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

// Imports
import java.util.logging.Logger;  // Logging based on https://github.com/frc6506/SkeletorCode-2022/releases/tag/v0.2.0l-Logging-Backup

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.Spark;

/**
 * This is a demo program showing the use of the DifferentialDrive class. Runs the motors with
 * arcade steering.
 *
 * <p>The VM is configured to automatically run this class, and to call the functions corresponding
 * to each mode, as described in the TimedRobot documentation. If you change the name of this class
 * or the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  // Get logger
  private final Logger logger = Logger.getLogger(this.getClass().getName());

  // Spark motor controller
  private final Spark m_leftMotor1 = new Spark(0);
  private final Spark m_leftMotor2 = new Spark(1);
  private final Spark m_rightMotor1 = new Spark(2);
  private final Spark m_rightMotor2 = new Spark(3);

  // Motor controller groups
  private final MotorControllerGroup leftMotors =
      new MotorControllerGroup(m_leftMotor1, m_leftMotor2);
  private final MotorControllerGroup rightMotors =
      new MotorControllerGroup(m_rightMotor1, m_rightMotor2);

  private final DifferentialDrive m_robotDrive = new DifferentialDrive(leftMotors, rightMotors);

  private final Joystick m_stick = new Joystick(0);

  @Override
  public void robotInit() {
    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    rightMotors.setInverted(true);

    // Setup USB camera
    // UsbCamera cam = CameraServer.getInstance().startAutomaticCapture();
    UsbCamera cam = CameraServer.startAutomaticCapture();
    cam.setResolution(160, 120);
    cam.setFPS(30);

    logger.info("robotInit complete");
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   *
   * <p>Missing defualt methods added from template:
   * https://github.com/BobSaidHi/FRC-2022.2.1-TimedRobotTemplate
   */
  @Override
  public void robotPeriodic() {
    logger.finer("robotPeriodic complete");
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    logger.info("autonomousInit complete");
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    logger.finer("autonomousPeriodic complete");
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {
    // Read the controller and store is as variable so that it can be logged
    double ySpeedRaw = m_stick.getY();
    double zRotatoinRaw = m_stick.getX();
    logger.fine("ySpeedRaw: " + ySpeedRaw + "\tzRotatoinRaw: " + zRotatoinRaw);

    // Scale the raw reading to make it more controllabe and/or limit power consumption
    // double ySpeedScaled = -ySpeedRaw * .75;
    double ySpeedScaled = -ySpeedRaw * 1;
    //ouble zRotatoinScaled = zRotatoinRaw * .65;
    double zRotatoinScaled = zRotatoinRaw * 1;
    logger.fine("xSpeedScaled: " + ySpeedScaled + "\tzRotatoinScaled: " + zRotatoinScaled);

    // Drive with arcade drive.
    // That means that the Y axis drives forward
    // and backward, and the X turns left and right.
    m_robotDrive.arcadeDrive(ySpeedScaled, zRotatoinScaled);

    logger.finer("autonomousPeriodic complete");
  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {
    logger.info("disabledInit complete");
  }

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {
    logger.finer("disabledPeriodic complete");
  }

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {
    logger.info("testInit complete");
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {
    logger.finer("testPeriodic complete");
  }
}
