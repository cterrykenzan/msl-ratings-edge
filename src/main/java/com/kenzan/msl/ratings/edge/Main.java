package com.kenzan.msl.ratings.edge;

import com.netflix.governator.annotations.Modules;
import io.swagger.api.RatingsEdgeApi;
import io.swagger.api.impl.RatingsEdgeApiOriginFilter;
import netflix.adminresources.resources.KaryonWebAdminModule;
import netflix.karyon.KaryonBootstrap;
import netflix.karyon.ShutdownModule;
import netflix.karyon.archaius.ArchaiusBootstrap;
import netflix.karyon.servo.KaryonServoModule;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

@ArchaiusBootstrap
@KaryonBootstrap(name = "msl-ratings-edge")
@Modules(include = {ShutdownModule.class, KaryonWebAdminModule.class, // Uncomment this to enable
                                                                      // WebAdmin
    // KaryonEurekaModule.class, // Uncomment this to enable Eureka client.
    KaryonServoModule.class})
public class Main {
  /**
   * Runs jetty server to expose jersey API
   * 
   * @param args String array
   * @throws Exception if server doesn't start
   */
  public static void main(String[] args) throws Exception {

    Server jettyServer = new Server(9004);
    ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
    context.setContextPath("/");
    context.addFilter(RatingsEdgeApiOriginFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));
    jettyServer.setHandler(context);

    ServletHolder jerseyServlet = context.addServlet(ServletContainer.class, "/*");
    jerseyServlet.setInitOrder(0);

    jerseyServlet.setInitParameter("jersey.config.server.provider.classnames",
        RatingsEdgeApi.class.getCanonicalName());

    try {

      jettyServer.start();
      jettyServer.join();

    } finally {
      jettyServer.destroy();
    }
  }
}
