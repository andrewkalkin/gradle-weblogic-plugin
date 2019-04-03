package com.lv.plugins.weblogic.tasks

/**
 * @author Sion Williams
 */
class WLStopTask extends AbstractWLDeployTask {

    /**
     * The deployment action to perform.
     */
    static final String ACTION = 'stop'

    /**
     * Constructor.
     */
    WLStopTask() {
        super( 'Makes an application inactive and unavailable administration and client requests.' )
    }

    @Override
    def getCommandAndOptions() {
        def commandArgs = [:]

        commandArgs << [ action: ACTION ]

        if( getDeploymentName() ) {
            commandArgs << [ name: getDeploymentName() ]
        }

        commandArgs
    }
}
