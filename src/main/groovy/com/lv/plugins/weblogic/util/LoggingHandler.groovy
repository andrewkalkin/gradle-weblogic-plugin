package com.lv.plugins.weblogic.util

import org.apache.tools.ant.BuildListener
import org.gradle.api.AntBuilder

/**
 * Logging handler support.
 *
 * @author Benjamin Muschko
 */
class LoggingHandler {
    /**
     * Attaches Ant logging handler for closure execution.
     *
     * @param antBuilder Ant builder
     * @param c Closure
     */
    static void withAntLoggingListener(AntBuilder antBuilder, Closure c) {
        BuildListener listener = new WLDeployAntLoggingListener()

        try {
            antBuilder.project.addBuildListener(listener)
            c()
        }
        finally {
            antBuilder.project.removeBuildListener(listener)
        }
    }
}
