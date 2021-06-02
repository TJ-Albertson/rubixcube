package org.lwjglb.game;

import lwjgui.LWJGUIApplication;
import lwjgui.scene.Window;
import org.lwjglb.engine.GameEngine;
import org.lwjglb.engine.IGameLogic;
 
public class Main extends LWJGUIApplication{
 
    public static void main(String[] args) {
        try {
            boolean vSync = true;
            IGameLogic gameLogic = new DummyGame();
            GameEngine gameEng = new GameEngine("GAME", 600, 480, vSync, gameLogic);
            gameEng.run();
        } catch (Exception excp) {
            excp.printStackTrace();
            System.exit(-1);
        }
    }

    @Override
    public void start(String[] strings, Window window) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}   