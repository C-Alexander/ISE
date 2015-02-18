package com.contritio.ise.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.contritio.ise.InterStellarEmpires;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                GwtApplicationConfiguration config = new GwtApplicationConfiguration(860, 620);
            config.alpha = true;
            return config;
        }

        @Override
        public ApplicationListener getApplicationListener () {
                return new InterStellarEmpires();
        }
}