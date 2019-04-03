package com.lv.plugins.weblogic.tasks

import com.lv.plugins.weblogic.util.LoggingHandler
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.TaskAction

/**
 * Custom Gradle task wrapping the Ant WLDeploy task
 * http://docs.oracle.com/cd/E11035_01/wls100/programming/wldeploy.html
 * http://docs.oracle.com/cd/E11035_01/wls100/deployment/wldeployer.html
 *
 * @author Sion Williams
 */
abstract class AbstractWLDeployTask extends DefaultTask {

    /**
     * The URL of the Administration Server.
     */
    @Input
    String adminurl = 't3://localhost:7001'


    /**
     * The version identifier of the deployed application.
     */
    @Input
    @Optional
    String appversion


    /**
     * The deployment name for the deployed application.
     */
    @Input
    String deploymentName = project.name


    /**
     * The archive file or exploded directory to deploy.
     */
    @Input
    @Optional
    String source


    /**
     * The list of target servers to which the application is deployed.
     */
    @Input
    @Optional
    String targets = 'AdminServer'


    /**
     * The administrative username.
     */
    @Input
    String user


    /**
     * The administrative password.
     */
    @Input
    String password

    /**
     * Enable wldeploy debugging messages.
     */
    @Input
    @Optional
    Boolean debug = false


    /**
     * Specifies whether wldeploy displays verbose output messages.
     */
    @Input
    @Optional
    Boolean verbose = false

    /**
     * Specifies whether the server is located on a different machine.
     */
    @Input
    @Optional
    Boolean remote = false

    /**
     * Specifies whether the source file(s) are copied to the Administration Server’s upload directory prior to deployment.
     */
    @Input
    @Optional
    Boolean upload = false

    /**
     * Specifies the security model of deployment.
     */
    @Input
    @Optional
    String securityModel

    /**
     * Constructor. (For invocation by subclass constructors)
     *
     * @param description A brief description of the task and what it does
     */
    AbstractWLDeployTask(String description) {
        this.description = description
        group = 'Weblogic'
    }

    @TaskAction
    void run() {
        ant.taskdef( name: 'wldeploy',
                classname: 'weblogic.ant.taskdefs.management.WLDeploy',
                classpath: project.configurations.weblogic.asPath )

        def antBuilderInput = getAntBuilderInput()

        logger.quiet "*****************************************"
        logger.quiet "Application name : ${antBuilderInput['name']}"
        logger.quiet " action : ${antBuilderInput['action']}"
        logger.quiet " source : ${antBuilderInput['source']}"
        logger.quiet " adminurl : ${antBuilderInput['adminurl']}"
        logger.quiet " targets : ${antBuilderInput['targets']}"
        logger.quiet "*****************************************"

        withExceptionHandling {
            LoggingHandler.withAntLoggingListener( ant ) {
                ant.wldeploy( antBuilderInput )
            }
        }
    }

    /**
     *
     * @param c
     * @throws GradleException If try/catch fails
     */
    private void withExceptionHandling(Closure c) {
        try {
            c.call()
        }
        catch (Exception e) {
            throw new GradleException(e.message)
        }
    }

    /**
     * Provide map representation of the antBuilder arguments
     *
     * @return Map of antBuilder arguments
     */
    private def getAntBuilderInput() {
        def antBuilderInput = [:]
        antBuilderInput += getConnectionArgs()
        antBuilderInput += getUserCredentialArgs()
        antBuilderInput += getCommonArgs()
        antBuilderInput += getCommandAndOptions()
        antBuilderInput
    }

    /**
     * Provide map representation of the connection arguments
     *
     * @return Map of connection arguments
     */
    private def getConnectionArgs() {
        def connectionArgs = [ adminurl: getAdminurl() ]
        connectionArgs
    }

    /**
     * Provide map representation of the user credentials arguments.
     *
     * @return Map of user credentials arguments
     */
    private def getUserCredentialArgs() {
        def userArgs = [ user: getUser(),
                         password: getPassword() ]
        userArgs
    }

    /**
     * Provide map representation of common arguments.
     *
     * @return Map of common arguments set by the user
     */
    private def getCommonArgs() {
        def commonArgs = [:]

        if( getDebug() ) {
            commonArgs << [ debug: getDebug() ]
        }

        if( getVerbose() ) {
            commonArgs << [ verbose: getVerbose() ]
        }

        if( getRemote() ) {
            commonArgs << [ remote: getRemote() ]
        }

        commonArgs
    }

    /**
     * Abstract task execution method for implementation by subclass.
     * Provide map representation of the wldeploy action and options.
     *
     * @return Map of the wldeploy command and options set by the user
     */
    abstract def getCommandAndOptions()
}
