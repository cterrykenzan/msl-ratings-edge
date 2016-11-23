package com.kenzan.msl.ratings.edge.config;

import com.kenzan.msl.ratings.client.config.RatingsDataClientModule;
import com.netflix.governator.guice.BootstrapBinder;
import com.netflix.karyon.server.ServerBootstrap;

/**
 * This class is the point where the karyon environment in bootstrapped which more or less is the bootstrapping of Governator
 *
 * @author Kenzan
 */
public class Bootstrap  extends ServerBootstrap {

    @Override
    protected void configureBootstrapBinder(BootstrapBinder binder) {
        binder.install(new RatingsDataClientModule());
        binder.install(new RatingsEdgeModule());
        binder.install(new RestModule());
    }
}
