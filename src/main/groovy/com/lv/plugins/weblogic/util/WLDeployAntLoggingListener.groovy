package com.lv.plugins.weblogic.util

import org.apache.tools.ant.BuildEvent
import org.apache.tools.ant.BuildListener
import org.apache.tools.ant.Project
import org.apache.tools.ant.Task
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging

/**
 * WLDeploy Ant build listener
 *
 * @author Benjamin Muschko
 * @author Sion Williams
 */
class WLDeployAntLoggingListener implements BuildListener{
    Logger logger = Logging.getLogger( WLDeployAntLoggingListener )
    static final String ANT_WLDEPLOY_TASK_NAME = 'weblogic.ant.taskdefs.management.WLDeploy'

    @Override
    void buildStarted(BuildEvent buildEvent) { }

    @Override
    void buildFinished(BuildEvent buildEvent) { }

    @Override
    void targetStarted(BuildEvent buildEvent) { }

    @Override
    void targetFinished(BuildEvent buildEvent) { }

    @Override
    void taskStarted(BuildEvent buildEvent) { }

    @Override
    void taskFinished(BuildEvent buildEvent) { }

    /**
     * Wraps the subclasses action code in a listener that raises info priority Ant task messages to
     * be lifecycle messages for Gradle. This allows messages from the Ant task, such as
     * "Press Ctrl-C to stop the container..." to be passed through.
     *
     * @param buildEvent Build event
     */
    @Override
    void messageLogged(BuildEvent buildEvent) {
        if( buildEvent.task && isWLDeployTask( buildEvent.task ) && isInfoPriority( buildEvent.priority )) {
            logger.lifecycle( buildEvent.message )
        }
    }

    private boolean isWLDeployTask(Task task) {
        task.class.canonicalName == ANT_WLDEPLOY_TASK_NAME
    }

    private boolean isInfoPriority( int priority ) {
        priority == Project.MSG_INFO
    }
}
