// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.arm.back;

import frc.robot.Constants;
import frc.robot.lib.statemachine.State;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.RobotMap;
import frc.robot.lib.statemachine.Transition;
import frc.robot.subsystems.arm.ArmStateMachine;
import frc.robot.subsystems.claw.ClawStateMachine;

/** Add your docs here. */
public class BMidYellow extends State {

    private static XboxController manipulatorController = RobotMap.manipulatorController;

    static final double FIRST_STAGE_POSITION = Constants.BMidYellow.FIRST_STAGE_POSITION;
    static final double SECOND_STAGE_POSITION = Constants.BMidYellow.SECOND_STAGE_POSITION;
    static final double WRIST_POSITION = Constants.BMidYellow.WRIST_POSITION;
    static final double ALLOWANCE = Constants.BMidYellow.ALLOWANCE;
    static final double TIME = Constants.BMidYellow.TIME;
    
    @Override
    public void build() {
        // return to idle automatically after scored
        transitions.add(new Transition(() -> {
            return RobotMap.arm.getClawOpen();
        }, ArmStateMachine.idleState));

        // return to idle manually
        transitions.add(new Transition(() -> {
            return manipulatorController.getBButton();
        }, ArmStateMachine.idleState));

        // open claw manually
        transitions.add(new Transition(() -> {
            return manipulatorController.getLeftBumper();
        }, ClawStateMachine.openState));
    }
    
    @Override
    public void init() {
        RobotMap.arm.moveArmPosition(FIRST_STAGE_POSITION, SECOND_STAGE_POSITION, WRIST_POSITION);
    }

    @Override
    public void execute() {

        /* if arm has arrived at position and stayed at position for 0.5 seconds, 
        send open request to claw */
        if (RobotMap.arm.getArrived(allowance, time)) {
            RobotMap.openRequest = true;
        }
    }

    @Override
    public void exit() {
        
    }

}
