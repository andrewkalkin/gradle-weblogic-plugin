package com.lv.plugins.weblogic

import com.lv.plugins.weblogic.extension.WLDeployPluginExtension
import com.lv.plugins.weblogic.tasks.AbstractWLDeployTask
import com.lv.plugins.weblogic.tasks.WLCancelTask
import com.lv.plugins.weblogic.tasks.WLDeployTask
import com.lv.plugins.weblogic.tasks.WLRedeployTask
import com.lv.plugins.weblogic.tasks.WLStartTask
import com.lv.plugins.weblogic.tasks.WLStopTask
import com.lv.plugins.weblogic.tasks.WLUndeployTask
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Created by Sion Williams on 14/08/2014.
 */
class WeblogicPlugin implements Plugin<Project> {
    static final WL_DEPLOY_EXTENSION = 'weblogic'
    static final WL_DEPLOY_TASK_NAME = 'wlDeploy'
    static final WL_UNDEPLOY_TASK_NAME = 'wlUndeploy'
    static final WL_CANCEL_TASK_NAME = 'wlCancel'
    static final WL_REDEPLOY_TASK_NAME = 'wlRedeploy'
    static final WL_START_TASK_NAME = 'wlStart'
    static final WL_STOP_TASK_NAME = 'wlStop'


    @Override
    void apply(Project project){

        // users to provide the dependency via the weblogic custom configuration
        project.configurations { weblogic }

        project.extensions.create( WL_DEPLOY_EXTENSION, WLDeployPluginExtension )

        addTasks( project )
    }

    /**
     * Add tasks to the plugin
     * @param project the target Gradle project
     */
    void addTasks( Project project ){
        def extension = project.extensions.findByName( WL_DEPLOY_EXTENSION )
        project.tasks.withType( AbstractWLDeployTask ){
            conventionMapping.adminurl = { extension.adminurl }
            conventionMapping.appversion = { extension.appversion }
            conventionMapping.deploymentName = { extension.deploymentName }
            conventionMapping.source = { extension.source }
            conventionMapping.targets = { extension.targets }
            conventionMapping.user = { extension.user }
            conventionMapping.password = { extension.password }
            conventionMapping.debug = { extension.debug }
            conventionMapping.verbose = { extension.verbose }
            conventionMapping.remote = { extension.remote }
            conventionMapping.upload = { extension.upload }

        }

        project.task( WL_DEPLOY_TASK_NAME, type: WLDeployTask )
        project.task( WL_UNDEPLOY_TASK_NAME, type: WLUndeployTask )
        project.task( WL_CANCEL_TASK_NAME, type: WLCancelTask )
        project.task( WL_REDEPLOY_TASK_NAME, type: WLRedeployTask )
        project.task( WL_START_TASK_NAME, type: WLStartTask )
        project.task( WL_STOP_TASK_NAME, type: WLStopTask )
    }
}
