package com.contritio.ise;

import com.badlogic.gdx.utils.Json;
import com.contritio.ise.engine.GameManager;
import com.contritio.ise.engine.GameState;
import com.contritio.ise.engine.Log;
import com.sksamuel.gwt.websockets.*;
/**
 * Created by Alexander on 25/01/2015.
 */
public class Networking implements WebsocketListener {
    Json json = new Json();

    public Networking() {
    }
    @Override
    public void onClose() {
        Log.error("Connection Lost");
    }

    @Override
    public void onMessage(String msg) {
        Log.trace("Message Received from Server: " + msg);
        Log.debug("Message contained following JSON: " + json.prettyPrint(msg));
        for (GameState gs : GameManager.getInstance().GameStates()) {
            gs.newData(json.fromJson(PacketData.class, msg));
        }
    }

    @Override
    public void onOpen() {
        Log.debug("Connection Made");
        GameManager.getInstance().GameState().login();
    }
}
