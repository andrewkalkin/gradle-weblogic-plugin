package com.lv.plugins.weblogic.tasks

/**
 * @author Sion Williams
 */
class WLCancelTask extends AbstractWLDeployTask {

    /**
     * The deployment action to perform.
     */
    static final String ACTION = 'cancel'

    /**
     * Constructor.
     */
    WLCancelTask() {
        super( 'Attempt to cancel a running deployment task.' )
    }

    @Override
    def getCommandAndOptions() {
        def commandArgs = [:]

        commandArgs << [ action: ACTION ]

        if( getDeploymentName() ) {
            commandArgs << [ name: getDeploymentName() ]
        }

        if( getTargets() ) {
            commandArgs << [ targets: getTargets() ]
        }

        commandArgs
    }
}
