package com.log.controller;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.Properties;

/**
 * @description:
 * @author: zhangshancheng
 **/
public class JettySeverBuilder {

    private ServletContextHandler getServletContextHandler() throws IOException {
        XmlWebApplicationContext context = new XmlWebApplicationContext();
        ServletContextHandler contextHandler = new ServletContextHandler();
        contextHandler.setErrorHandler(null);
        contextHandler.setContextPath("/app");
        contextHandler.addServlet(new ServletHolder(new DispatcherServlet(context)), "/*");
        contextHandler.addEventListener(new ContextLoaderListener(context));
        String webapp = new ClassPathResource("webapp").getURI().toString();
        contextHandler.setResourceBase(webapp);
        return contextHandler;
    }

    private InetSocketAddress getAddressByConf(String path) throws UnknownHostException {
        Properties pro = ClusterManagerParam.getFile();
        InetAddress addr = InetAddress.getByName(pro.getProperty("host"));
        InetSocketAddress addrSocket = new InetSocketAddress(addr,Integer.parseInt(pro.getProperty("prot")));
        return addrSocket;
    }

    public Server build() throws IOException {
        ServletContextHandler servletContextHandler = getServletContextHandler();
        String s = servletContextHandler.getBaseResource().getURI().toString();
        InetSocketAddress addressByConf = getAddressByConf(s);
        Server server = new Server(addressByConf);
        server.setHandler(servletContextHandler);
        return server;
    }
}
