package com.lv.plugins.weblogic.tasks

/**
 * @author Sion Williams
 */
class WLUndeployTask extends AbstractWLDeployTask {

    /**
     * The deployment action to perform.
     */
    static final String ACTION = 'undeploy'

    /**
     * Constructor.
     */
    WLUndeployTask() {
        super( 'Stops the deployment unit and removes staged files from target servers.' )
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

        if( getAppversion() ) {
            commandArgs << [ appversion: getAppversion() ]
        }

        commandArgs
    }
}
