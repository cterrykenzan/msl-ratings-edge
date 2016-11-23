package com.kenzan.msl.ratings.edge.config;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import com.kenzan.msl.ratings.edge.services.RatingsEdgeService;
import com.kenzan.msl.ratings.edge.services.RatingsEdgeServiceImpl;
import com.netflix.governator.guice.lazy.LazySingletonScope;
import io.swagger.api.RatingsEdgeApiService;
import io.swagger.api.factories.RatingsEdgeApiServiceFactory;
import io.swagger.api.impl.RatingsEdgeApiOriginFilter;
import io.swagger.api.impl.RatingsEdgeApiServiceImpl;
import io.swagger.api.impl.RatingsEdgeSessionToken;
import io.swagger.api.impl.RatingsEdgeSessionTokenImpl;

/**
 * @author Kenzan
 */
public class LocalRatingsEdgeModule extends AbstractModule {
    @Override
    public void configure() {
        bindConstant().annotatedWith(Names.named("clientPort")).to("3000");

        requestStaticInjection(RatingsEdgeApiServiceFactory.class);
        requestStaticInjection(RatingsEdgeApiOriginFilter.class);

        bind(RatingsEdgeSessionToken.class).to(RatingsEdgeSessionTokenImpl.class).in(LazySingletonScope.get());

        bind(RatingsEdgeService.class).to(RatingsEdgeServiceImpl.class).in(LazySingletonScope.get());
        bind(RatingsEdgeApiService.class).to(RatingsEdgeApiServiceImpl.class).in(LazySingletonScope.get());
    }
}
