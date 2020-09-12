package org.mohammad.gol.logic.simulator;

import org.mohammad.gol.command.Command;
import org.mohammad.gol.state.SimulatorState;

public interface SimulatorStateCommand extends Command<SimulatorState> {
    @Override
    void execute(SimulatorState simulatorState);
}
