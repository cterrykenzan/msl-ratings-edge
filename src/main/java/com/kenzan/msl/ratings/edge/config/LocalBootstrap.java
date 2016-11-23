package com.kenzan.msl.ratings.edge.config;

import com.kenzan.msl.ratings.client.config.LocalRatingsDataClientModule;
import com.netflix.governator.guice.BootstrapBinder;
import com.netflix.karyon.server.ServerBootstrap;

/**
 * @author Kenzan
 */
public class LocalBootstrap extends ServerBootstrap {

    @Override
    protected void configureBootstrapBinder(BootstrapBinder binder) {
        binder.install(new LocalRatingsDataClientModule());
        binder.install(new LocalRatingsEdgeModule());
        binder.install(new RestModule());
    }
}

