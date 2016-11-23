package com.kenzan.msl.ratings.edge.config;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import com.kenzan.msl.ratings.edge.services.RatingsEdgeService;
import com.kenzan.msl.ratings.edge.services.RatingsEdgeServiceImpl;
import com.netflix.config.DynamicPropertyFactory;
import com.netflix.config.DynamicStringProperty;
import com.netflix.governator.guice.lazy.LazySingletonScope;
import io.swagger.api.RatingsEdgeApiService;
import io.swagger.api.factories.RatingsEdgeApiServiceFactory;
import io.swagger.api.impl.RatingsEdgeApiOriginFilter;
import io.swagger.api.impl.RatingsEdgeApiServiceImpl;
import io.swagger.api.impl.RatingsEdgeSessionToken;
import io.swagger.api.impl.RatingsEdgeSessionTokenImpl;

/**
 * Ratings Edge Module, a support class for Modules which reduces repetition and results in a more readable configuration
 * if no archaius.configurationSource.additionalUrls property is passed in, archaius uses default configuration. See readme to
 * understand how to pass in these variables
 *
 * @author Kenzan
 */
public class RatingsEdgeModule extends AbstractModule {

    private String DEFAULT_CLIENT_PORT = "3000";

    private final DynamicStringProperty CLIENT_PORT =
            DynamicPropertyFactory.getInstance().getStringProperty("clientPort", DEFAULT_CLIENT_PORT);

    @Override
    public void configure() {
        bindConstant().annotatedWith(Names.named("clientPort")).to(CLIENT_PORT.get());

        requestStaticInjection(RatingsEdgeApiServiceFactory.class);
        requestStaticInjection(RatingsEdgeApiOriginFilter.class);

        bind(RatingsEdgeSessionToken.class).to(RatingsEdgeSessionTokenImpl.class).in(LazySingletonScope.get());

        bind(RatingsEdgeService.class).to(RatingsEdgeServiceImpl.class).in(LazySingletonScope.get());
        bind(RatingsEdgeApiService.class).to(RatingsEdgeApiServiceImpl.class).in(LazySingletonScope.get());
    }
}
