package org.gameServer.controller;

import org.gameServer.service.implemtations.GameServerService;
import org.gameServer.service.implemtations.GameServerSimulationService;

public class GameServerController {

    private final GameServerService gameServerService;
    private final GameServerSimulationService gameServerSimulationService;

    public GameServerController() {
        this.gameServerService = new GameServerService();
        this.gameServerSimulationService = new GameServerSimulationService();
    }

    public void run() {
        double expectedValueSimulation = gameServerSimulationService.simulateGameRounds(10000000);
        System.out.println("Expected reward from Simulation: ");
        System.out.printf("%.1f%n", expectedValueSimulation);

        double expectedValue = gameServerService.calculateReward();
        System.out.println("Expected reward from calculations: ");
        System.out.printf("%.1f%n", expectedValue);
    }
}

