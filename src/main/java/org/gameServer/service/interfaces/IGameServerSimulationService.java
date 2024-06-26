package org.gameServer.service.interfaces;

import java.util.List;

public interface IGameServerSimulationService {
    double simulateGameRounds(int rounds);

    void setBoxes(List<String> boxes);

    void setFinalRewards(List<String> finalRewards);
}
