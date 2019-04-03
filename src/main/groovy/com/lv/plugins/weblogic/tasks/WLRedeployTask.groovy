package com.lv.plugins.weblogic.tasks

import org.gradle.api.tasks.Input

/**
 * @author Sion Williams
 */
class WLRedeployTask extends AbstractWLDeployTask {

    /**
     * The deployment action to perform.
     */
    static final String ACTION = 'redeploy'

    /**
     * Specifies a comma- or space-separated list of files, relative to the root directory of the application,
     * which are to be redeployed.
     */
    @Input
    String deltaFiles

    /**
     * Constructor.
     */
    WLRedeployTask() {
        super( 'Redeploys a running application or part of a running application.' )
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

        if( getDeltaFiles() ) {
            commandArgs << [ appversion: getDeltaFiles() ]
        }

        if( getSource() ) {
            commandArgs << [ source: getSource() ]
        }

        if( getUpload() ) {
            commandArgs << [ upload: getUpload() ]
        }

        commandArgs
    }
}
