package com.lv.plugins.weblogic.tasks

/**
 * @author Sion Williams
 */
class WLDeployTask extends AbstractWLDeployTask {

    /**
     * The deployment action to perform.
     */
    static final String ACTION = 'deploy'

    /**
     * Constructor.
     */
    WLDeployTask() {
        super( 'Deploys or redeploys an application or module.' )
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

        if( getSource() ) {
            commandArgs << [ source: getSource() ]
        }

        if( getUpload() ) {
            commandArgs << [ upload: getUpload() ]
        }

        if( getSecurityModel() ) {
            commandArgs << [ securityModel: getSecurityModel()]
        }

        commandArgs
    }
}
