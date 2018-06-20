package org.tadamski.examples.cloud;

import org.wildfly.swarm.Swarm;

import java.net.URL;

public class Main {
    public static void main(String[] args) throws Exception {
        ClassLoader cl = Main.class.getClassLoader();
        URL proxyConfig = cl.getResource("proxy-config.yml");

        Swarm swarm = new Swarm();
        swarm.withConfig(proxyConfig);
        swarm.start().deploy();
    }
}
