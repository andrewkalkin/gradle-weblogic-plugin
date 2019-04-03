package com.lv.plugins.weblogic.tasks

/**
 * @author Sion Williams
 */
class WLStartTask extends AbstractWLDeployTask {

    /**
     * The deployment action to perform.
     */
    static final String ACTION = 'start'

    /**
     * Constructor.
     */
    WLStartTask() {
        super( 'Makes a stopped (inactive) application available to clients on target servers.' )
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
