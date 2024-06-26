package org.gameServer;

import org.gameServer.service.implemtations.GameServerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class GameServerTest {

    private GameServerService gameServer;
    private List<String> testBoxes;
    private List<String> testFinalRewards;

    @BeforeEach
    public void setUp() {
        testBoxes = List.of("100", "20", "20", "5", "5", "5", "5", "5", "Extra Life", "Game Over", "Game Over", "Game Over");
        testFinalRewards = List.of("5", "10", "20", "Second Chance");
        gameServer = new GameServerService(testBoxes, testFinalRewards);
    }

    @Test
    public void testCalculateReward() {
        double expectedReward = gameServer.calculateReward();
        assertTrue(expectedReward > 73, "Expected reward should be greater than 73");
    }

    @Test
    public void testGetBoxes() {
        List<String> boxes = gameServer.getBoxes();
        assertEquals(testBoxes, boxes, "Boxes should match the initialized values");
    }

    @Test
    public void testSetBoxes() {
        List<String> newBoxes = List.of("10", "20", "30", "40", "50", "Extra Life", "Game Over", "Game Over");
        gameServer.setBoxes(newBoxes);
        assertEquals(newBoxes, gameServer.getBoxes(), "Boxes should match the newly set values");
    }

    @Test
    public void testGetFinalRewards() {
        List<String> finalRewards = gameServer.getFinalRewards();
        assertEquals(testFinalRewards, finalRewards, "Final rewards should match the initialized values");
    }

    @Test
    public void testSetFinalRewards() {
        List<String> newFinalRewards = List.of("15", "25", "35", "Second Chance");
        gameServer.setFinalRewards(newFinalRewards);
        assertEquals(newFinalRewards, gameServer.getFinalRewards(), "Final rewards should match the newly set values");
    }
}
