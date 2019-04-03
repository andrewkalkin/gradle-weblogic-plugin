package com.lv.plugins.weblogic.extension

/**
 * @author Sion Williams
 */
class WLDeployPluginExtension {
    String adminurl = 't3://localhost:7001'
    String appversion
    String deploymentName
    String source
    String targets = 'AdminServer'
    String user
    String password
    Boolean debug = false
    Boolean verbose = false
    Boolean remote = false
    Boolean upload = false
    String securityModel
}
