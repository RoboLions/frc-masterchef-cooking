// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.arm;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants;
import frc.robot.RobotMap;
import frc.robot.Constants.ManipulatorButtons;
import frc.robot.lib.statemachine.State;
import frc.robot.lib.statemachine.Transition;

/** Add your docs here. */
public class ManualMoveState extends State {

    @Override
    public void build() {
        // idle if idle button
        transitions.add(new Transition(() -> {
            return RobotMap.manipulatorController.getRawButtonPressed(Constants.ManipulatorButtons.IDLE_BUTTON);
        }, ArmStateMachine.idleState));
    }

    @Override
    public void init() {
    }

    @Override
    public void execute() {

        // TODO: figure out how to manually control arm
        
        double wristInput = RobotMap.arm.applyDeadband(RobotMap.manipulatorController.getRawAxis(ManipulatorButtons.WRIST_BACKWARD_BUTTON)) 
                            - RobotMap.arm.applyDeadband(RobotMap.manipulatorController.getRawAxis(ManipulatorButtons.WRIST_FORWARD_BUTTON));
        RobotMap.wristMotor.set(ControlMode.PercentOutput, wristInput);

        // TODO: check motors match button
        double elbowInput = RobotMap.manipulatorController.getRawAxis(ManipulatorButtons.BICEP_BUTTON);
        RobotMap.elbowMotor.set(ControlMode.PercentOutput, RobotMap.arm.applyDeadband(elbowInput));

        double shoulderInput = RobotMap.manipulatorController.getRawAxis(ManipulatorButtons.FOREARM_BUTTON);
        RobotMap.shoulderMotor.set(ControlMode.PercentOutput, RobotMap.arm.applyDeadband(shoulderInput));
    }

    @Override
    public void exit() {
        
    }
}