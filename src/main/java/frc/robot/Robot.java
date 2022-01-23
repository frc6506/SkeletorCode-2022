// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.cscore.UsbCamera;

import java.util.logging.Logger;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  // Logger test
  private final Logger logger = Logger.getLogger(this.getClass().getName());

  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  Spark leftMotor1 = new Spark(0);
  Spark leftMotor2 = new Spark(1);
  Spark rightMotor1 = new Spark(2);
  Spark rightMotor2 = new Spark(3);
  MotorControllerGroup leftMotors = new MotorControllerGroup(leftMotor1, leftMotor2);
  MotorControllerGroup rightMotors = new MotorControllerGroup(rightMotor1, rightMotor2);
  // drivetrain
  DifferentialDrive drive = new DifferentialDrive(leftMotors, rightMotors);

  // joystick
  Joystick joystick = new Joystick(0);

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

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
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
    
    logger.info("autonomousInit complete");
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }

    logger.finer("autonomousPeriodic complete");
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {}

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    double xSpeedRaw = joystick.getRawAxis(1);
    double zRotatoinRaw = joystick.getRawAxis(0);
    logger.fine("xSpeedRaw: " + xSpeedRaw + "\tzRotatoinRaw: " + zRotatoinRaw);
    double xSpeedScaled = xSpeedRaw * .75;
    double zRotatoinScaled = zRotatoinRaw * .65;
    logger.fine("xSpeedScaled: " + xSpeedScaled + "\tzRotatoinScaled: " + zRotatoinScaled);
    drive.arcadeDrive(xSpeedScaled,  zRotatoinScaled);

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
