package com.contritio.ise.engine;

import com.badlogic.gdx.utils.Json;
import com.contritio.ise.LoginData;
import com.contritio.ise.Networking;
import com.contritio.ise.PacketData;
import com.google.gwt.user.client.Cookies;
import com.sksamuel.gwt.websockets.Websocket;

/**
 * Created by Alexander on 22/02/2015.
 */
public class NetworkManager {
    private Websocket client = new Websocket("ws://contritio.com:11984/");
    private Json json = new Json();

    public NetworkManager() {
        client.addListener(new Networking());
        client.open();
    }

    private static NetworkManager networkManager = null;
    public static NetworkManager getInstance() {
        if (networkManager == null) {
            return networkManager = new NetworkManager();
        } else {
            return networkManager;
        }
    }

    public void login() {
        PacketData loginPacket = new PacketData();
        loginPacket.loginData = new LoginData();
        loginPacket.loginData.user_hash = Cookies.getCookie("user_hash");
        loginPacket.loginData.user_id = Integer.parseInt(Cookies.getCookie("user_id"));
        client.send(json.toJson(loginPacket));
    }

    private static native void sendNativeMessage(final int id, final String channel, final String avatar, final String icon, final String player, final String guild, final String playerLocation, final String content) /*-{
        $wnd.newChatMessage(id, channel, avatar, icon, player, guild, playerLocation, content);
    }-*/;
}
