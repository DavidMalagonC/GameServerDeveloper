package org.gameServer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.gameServer.service.implemtations.GameServerSimulationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GameServerSimulationTest {

    private GameServerSimulationService gameServerSimulation;
    private List<String> testBoxes;
    private List<String> testFinalRewards;

    @BeforeEach
    public void setUp() {
        testBoxes = List.of("100", "20", "20", "5", "5", "5", "5", "5", "Extra Life", "Game Over", "Game Over", "Game Over");
        testFinalRewards = List.of("5", "10", "20", "Second Chance");
        gameServerSimulation = new GameServerSimulationService(testBoxes, testFinalRewards);
    }

    @Test
    public void testDefaultSimulation() {
        GameServerSimulationService game = new GameServerSimulationService();
        double result = game.simulateGameRounds(100000);
        assertTrue(result > 73, "The expected value should be greater than 70");
    }

    @Test
    public void testCustomBoxesAndRewards() {
        List<String> customBoxes = List.of("100", "10", "20", "10", "5", "5", "5", "5", "Extra Life", "Game Over", "Game Over", "Game Over");
        List<String> customFinalRewards = List.of("5", "10", "25", "Second Chance");
        GameServerSimulationService game = new GameServerSimulationService(customBoxes, customFinalRewards);
        double result = game.simulateGameRounds(100000);
        assertTrue(result > 73, "The expected value should be greater than 70");
    }

    @Test
    public void testNoGameOvers() {
        List<String> customBoxes = List.of("100", "20", "20", "5", "5", "5", "5", "5", "Extra Life", "Extra Life", "Extra Life", "Extra Life");
        List<String> customFinalRewards = List.of("5", "10", "20", "Second Chance");
        GameServerSimulationService game = new GameServerSimulationService(customBoxes, customFinalRewards);
        double result = game.simulateGameRounds(100000);
        assertTrue(result > 73, "The expected value should be greater than 73 even with no game overs");
    }

    @Test
    public void testAllGameOvers() {
        List<String> customBoxes = List.of("Game Over", "Game Over", "Game Over", "Game Over", "Game Over", "Game Over", "Game Over", "Game Over", "Game Over", "Game Over", "Game Over", "Game Over");
        List<String> customFinalRewards = List.of("5", "10", "20");
        GameServerSimulationService game = new GameServerSimulationService(customBoxes, customFinalRewards);
        double result = game.simulateGameRounds(100000);
        assertTrue(result <= 20, "The expected value should be <= 20 with all game overs");
    }

    @Test
    public void testAllGameOversWithSecondChance() {
        List<String> customBoxes = List.of("Game Over", "Game Over", "Game Over", "Game Over", "Game Over", "Game Over", "Game Over", "Game Over", "Game Over", "Game Over", "Game Over", "Game Over");
        List<String> customFinalRewards = List.of("5", "10", "20", "Second Chance");
        GameServerSimulationService game = new GameServerSimulationService(customBoxes, customFinalRewards);
        double result = game.simulateGameRounds(100000);
        assertTrue(result >= 10 && result <= 40, "The expected value should be => 10 and <40 with all game overs");
    }

    @Test
    public void testGetBoxes() {
        List<String> boxes = gameServerSimulation.getBoxes();
        assertEquals(testBoxes, boxes, "Boxes should match the initialized values");
    }

    @Test
    public void testSetBoxes() {
        List<String> newBoxes = List.of("10", "20", "30", "40", "50", "Extra Life", "Game Over", "Game Over");
        gameServerSimulation.setBoxes(newBoxes);
        assertEquals(newBoxes, gameServerSimulation.getBoxes(), "Boxes should match the newly set values");
    }

    @Test
    public void testGetFinalRewards() {
        List<String> finalRewards = gameServerSimulation.getFinalRewards();
        assertEquals(testFinalRewards, finalRewards, "Final rewards should match the initialized values");
    }

    @Test
    public void testSetFinalRewards() {
        GameServerSimulationService game = new GameServerSimulationService();
        List<String> newFinalRewards = List.of("15", "25", "35", "Second Chance");
        game.setFinalRewards(newFinalRewards);
        assertEquals(newFinalRewards, game.getFinalRewards(), "Final rewards should match the newly set values");
    }
}
