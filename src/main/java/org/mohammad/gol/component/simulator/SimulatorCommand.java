package org.mohammad.gol.component.simulator;

import org.mohammad.app.command.Command;

public interface SimulatorCommand extends Command<SimulatorState> {
    @Override
    void execute(SimulatorState simulatorState);


    @Override
    default Class<SimulatorState> getInsClass() {
        return SimulatorState.class;
    }
}
