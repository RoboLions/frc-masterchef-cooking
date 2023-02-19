// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.arm.back;

import frc.robot.Constants;
import frc.robot.RobotMap;
import frc.robot.lib.statemachine.State;
import frc.robot.lib.statemachine.Transition;
import frc.robot.subsystems.arm.ArmStateMachine;

/** Add your docs here. */
public class BHighPurple extends State {

    @Override
    public void build() {
        // return to idle automatically after scored
        transitions.add(new Transition(() -> {
            return RobotMap.arm.getClawOpen();
        }, ArmStateMachine.idleState));

        // transition to mid level
        transitions.add(new Transition(() -> {
            return RobotMap.manipulatorController.getRawButtonPressed(Constants.ManipulatorButtons.MID_SCORE_BUTTON);
        }, ArmStateMachine.bMidPurple));

        // transition to hybrid level
        transitions.add(new Transition(() -> {
            return RobotMap.manipulatorController.getRawButtonPressed(Constants.ManipulatorButtons.LOW_SCORE_BUTTON);
        }, ArmStateMachine.bHybrid));

        // return to idle manually
        transitions.add(new Transition(() -> {
            return RobotMap.manipulatorController.getRawButtonPressed(Constants.ManipulatorButtons.IDLE_BUTTON);
        }, ArmStateMachine.idleState));

        // transition to control arm manually
        transitions.add(new Transition(() -> {
            return RobotMap.manipulatorController.getRawButtonPressed(Constants.ManipulatorButtons.MANUAL_MODE_BUTTON);
        }, ArmStateMachine.manualMoveState));
    }
    
    @Override
    public void init() {
        RobotMap.arm.moveArmPosition(
            Constants.BHighPurple.SHOULDER_POSITION, 
            Constants.BHighPurple.ELBOW_POSITION, 
            Constants.BHighPurple.WRIST_POSITION
        );
    }

    @Override
    public void execute() {

    }

    @Override
    public void exit() {
        
    }

}
