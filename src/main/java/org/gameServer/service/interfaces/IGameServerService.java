package org.gameServer.service.interfaces;

import java.util.List;

public interface IGameServerService {
    double calculateReward();

    void setBoxes(List<String> boxes);

    void setFinalRewards(List<String> finalRewards);
}
