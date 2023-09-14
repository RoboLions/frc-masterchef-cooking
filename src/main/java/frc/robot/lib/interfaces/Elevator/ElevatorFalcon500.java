// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.lib.interfaces.Elevator;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

/** Add your docs here. */
public class ElevatorFalcon500 implements ElevatorIO {
    public TalonFX elevatorMotor;

    public ElevatorFalcon500(int elevatorMotorID){
        elevatorMotor = new TalonFX(elevatorMotorID);
    }
    public void setBrakeMode(){
        elevatorMotor.setNeutralMode(NeutralMode.Brake);
    }
    public void setMotorPercentOutput(double output){
        elevatorMotor.set(TalonFXControlMode.PercentOutput, output);
    }
    public void setMotorPositionOutput(double position){
        elevatorMotor.set(TalonFXControlMode.Position, position);
    }

    public void resetEncoder(){
        elevatorMotor.setSelectedSensorPosition(0);
    }

    
    public void updateInputs(ElevatorIOInputs inputs) {
        inputs.elevatorSensorPosition = elevatorMotor.getSelectedSensorPosition();
        inputs.elevatorSensorvelocity = elevatorMotor.getSelectedSensorVelocity();
        inputs.elevetorPercentOutput = elevatorMotor.getMotorOutputPercent();
    }
}
