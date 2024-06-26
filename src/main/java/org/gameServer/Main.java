package org.gameServer;

import org.gameServer.controller.GameServerController;

public class Main {
    public static void main(String[] args) {
        GameServerController controller = new GameServerController();
        controller.run();
    }
}
