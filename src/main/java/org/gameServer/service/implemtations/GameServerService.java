package org.gameServer.service.implemtations;

import org.gameServer.service.interfaces.IGameServerService;

import java.util.List;
import java.util.logging.Logger;

public class GameServerService implements IGameServerService {

    private static final Logger LOGGER = Logger.getLogger(GameServerService.class.getName());
    private List<String> boxes;
    private List<String> finalRewards;
    private final String GAME_OVER = "Game Over";
    private final String EXTRA_LIFE = "Extra Life";
    private final String SECOND_CHANCE = "Second Chance";

    public GameServerService() {
        this.boxes = List.of("100", "20", "20", "5", "5", "5", "5", "5", EXTRA_LIFE, GAME_OVER, GAME_OVER, GAME_OVER);
        this.finalRewards = List.of("5", "10", "20", SECOND_CHANCE);
    }

    public GameServerService(List<String> boxes, List<String> finalRewards) {
        this.boxes = boxes;
        this.finalRewards = finalRewards;
    }

    public double calculateReward() {
        double rewardPerTurn = calculateRewardPerTurn();
        double probabilityGameOver = calcProbability(boxes, GAME_OVER);
        double baseExpectedValue = rewardPerTurn / probabilityGameOver;

        double additionalExpectedValue = calculateFinalRewards(baseExpectedValue);
        double totalExpectedValue = baseExpectedValue + additionalExpectedValue;

        return totalExpectedValue;
    }

    private double calculateRewardPerTurn() {
        double expectedValue = 0.0;
        int totalValues = boxes.size();
        for (String reward : boxes) {
            if (isNumber(reward)) {
                expectedValue += Double.parseDouble(reward) / totalValues;
            }
        }
        return expectedValue;
    }

    private double calculateFinalRewards(double baseExpectedValue) {
        double expectedValue = 0.0;
        int totalValues = finalRewards.size();
        for (String reward : finalRewards) {
            if (isNumber(reward)) {
                expectedValue += Double.parseDouble(reward) / totalValues;
            } else if (SECOND_CHANCE.equals(reward)) {
                expectedValue += baseExpectedValue / totalValues;
            }
        }
        return expectedValue;
    }

    private double calcProbability(List<String> list, String target) {
        long count = list.stream().filter(target::equals).count();
        return (double) count / list.size();
    }

    private boolean isNumber(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public List<String> getBoxes() {
        return boxes;
    }

    public void setBoxes(List<String> boxes) {
        this.boxes = boxes;
    }

    public List<String> getFinalRewards() {
        return finalRewards;
    }

    public void setFinalRewards(List<String> finalRewards) {
        this.finalRewards = finalRewards;
    }
}
