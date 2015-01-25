package com.contritio.ise.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.contritio.ise.InterStellarEmpires;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                return new GwtApplicationConfiguration(860, 620);
        }

        @Override
        public ApplicationListener getApplicationListener () {
                return new InterStellarEmpires();
        }
}