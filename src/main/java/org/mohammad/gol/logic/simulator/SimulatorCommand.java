package org.mohammad.gol.logic.simulator;

import org.mohammad.app.command.Command;
import org.mohammad.gol.state.SimulatorState;

public interface SimulatorCommand extends Command<SimulatorState> {
    @Override
    void execute(SimulatorState simulatorState);


    @Override
    default Class<SimulatorState> getInsClass() {
        return SimulatorState.class;
    }
}
