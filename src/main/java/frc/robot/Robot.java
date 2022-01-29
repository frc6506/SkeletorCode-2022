// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

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
 */
public class Robot extends TimedRobot {
  private final Spark m_leftMotor1 = new Spark(0);
  private final Spark m_leftMotor2 = new Spark(1);
  private final Spark m_rightMotor1 = new Spark(2);
  private final Spark m_rightMotor2 = new Spark(3);

  private final MotorControllerGroup leftMotors = new MotorControllerGroup(m_leftMotor1, m_leftMotor2);
  private final MotorControllerGroup rightMotors = new MotorControllerGroup(m_rightMotor1, m_rightMotor2);

  private final DifferentialDrive m_robotDrive = new DifferentialDrive(leftMotors, rightMotors);

  private final Joystick m_stick = new Joystick(0);

  @Override
  public void robotInit() {
    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    rightMotors.setInverted(true);

    //UsbCamera cam = CameraServer.getInstance().startAutomaticCapture();
    UsbCamera cam = CameraServer.startAutomaticCapture();
    cam.setResolution(160, 120);
    cam.setFPS(30);
  }

  @Override
  public void teleopPeriodic() {
    // Drive with arcade drive.
    // That means that the Y axis drives forward
    // and backward, and the X turns left and right.
    m_robotDrive.arcadeDrive(-m_stick.getY(), m_stick.getX());
  }
}
