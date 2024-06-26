package org.gameServer.service.implemtations;

import org.gameServer.service.interfaces.IGameServerSimulationService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameServerSimulationService implements IGameServerSimulationService {

    private static final Logger LOGGER = Logger.getLogger(GameServerSimulationService.class.getName());
    private List<String> boxes;
    private List<String> finalRewards;
    private final String GAME_OVER = "Game Over";
    private final String EXTRA_LIFE = "Extra Life";
    private final String SECOND_CHANCE = "Second Chance";

    public GameServerSimulationService(List<String> boxes, List<String> finalRewards) {
        this.boxes = boxes;
        this.finalRewards = finalRewards;
    }

    public GameServerSimulationService() {
        this.boxes = List.of("100", "20", "20", "5", "5", "5", "5", "5", EXTRA_LIFE, GAME_OVER, GAME_OVER, GAME_OVER);
        this.finalRewards = List.of("5", "10", "20", SECOND_CHANCE);
    }

    public double simulateGameRounds(int rounds) {
        double totalReward = 0;

        for (int i = 0; i < rounds; i++) {
            try {
                totalReward += playGame(this.boxes, this.finalRewards);
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Error during game round simulation", e);
            }
        }

        return totalReward / rounds;
    }

    private double playGame(List<String> boxes, List<String> finalRewards) {
        double reward = 0;
        Random random = new Random();
        List<String> shuffledBoxes = new ArrayList<>(boxes);
        Collections.shuffle(shuffledBoxes, random);

        List<String> shuffledFinalRewards = new ArrayList<>(finalRewards);
        Collections.shuffle(shuffledFinalRewards, random);
        int extraLife = 0;

        try {
            for (String box : shuffledBoxes) {
                if (box.equals(GAME_OVER)) {
                    if (extraLife > 0) {
                        extraLife--;
                        continue;
                    } else {
                        String finalReward = shuffledFinalRewards.get(random.nextInt(shuffledFinalRewards.size()));
                        if (finalReward.equals(SECOND_CHANCE)) {
                            shuffledFinalRewards.remove(SECOND_CHANCE);
                            reward += playGame(new ArrayList<>(boxes), new ArrayList<>(shuffledFinalRewards));
                        } else {
                            reward += Double.parseDouble(finalReward);
                        }
                        break;
                    }
                } else if (box.equals(EXTRA_LIFE)) {
                    extraLife++;
                } else {
                    reward += Integer.parseInt(box);
                }
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error during game play", e);
        }

        return reward;
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
