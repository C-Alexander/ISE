package com.contritio.ise.engine;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

/**
 * Created by Alexander on 22/06/2014.
 */
public class GameManager {
   ArrayList<GameState> gameStates = new ArrayList<GameState>();
   String currentGameState;
   public static final GameManager gameManager = new GameManager();

    public static GameManager getInstance() { return gameManager; };

    public void switchTo(String GameStateName) {
        if (currentGameState != null) {
            if (!currentGameState.equalsIgnoreCase(GameStateName)) {
                Log.debug("Switching GameState: " + currentGameState + " for GameState: " + GameStateName);
                GameState().destroy();
                currentGameState = GameStateName;
                GameState().setup();
            }
        } else {
            Log.debug("Setting up a game state for the first time.");
            currentGameState = GameStateName;
            GameState().setup();
        }
    }

    public void run(SpriteBatch batch) {
        if (currentGameState != null) {
            GameState().update();
            GameState().draw(batch);
        } else {
            Log.debug("No GameState selected.");
        }
    }

    public void resume() {
        if (!currentGameState.isEmpty()) {
            GameState().resume();
        } else {
            Log.debug("No GameState selected.");
        }
    }

    public void pause() {
        if (!currentGameState.isEmpty()) {
            GameState().pause();
        } else {
            Log.debug("No GameState selected.");
        }
    }

    public void dispose() {
        if (!currentGameState.isEmpty()) {
            GameState().destroy();
        } else {
            Log.debug("No GameState selected.");
        }
    }

    public void addGameState(GameState gameState) {
        gameStates.add(gameState);
    }

    public void removeGameState(String gameState) {
        for (GameState e: gameStates) {
            Log.trace("GameState named " + e.name + " found.");
            if (gameState.equalsIgnoreCase(e.name)) {
                Log.trace("Deleting GameState:" + e.name);
                e.destroy();
                gameStates.remove(e);
            }
        }
    }

    public GameState GameState() {
        for (GameState e: gameStates) {
            Log.trace("GameState named " + e.name + " found.");
            if (currentGameState.equalsIgnoreCase(e.name)) {
                Log.trace("Returning GameState:" + e.name);
                return e;
            }
        }
        Log.error("Error, no GameState matching the currentGameState found.");
        return new GameState("NOT FOUND", new Texture("/legacy/twg.gif"));
    }
}
