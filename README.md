# Gradle Weblogic plugin

This plugin is a direct port from the Ant equivalents found at [wldeploy Ant Task Reference](http://docs.oracle.com/cd/E13222_01/wls/docs92/programming/wldeploy.html)


## Usage

To use the plugin, configure your `build.gradle` script and add the plugin:
```groovy
    buildscript {
        repositories {
            mavenCentral()
            maven { url 'http://dl.bintray.com/lv/gradle-plugins/' }
        }
        dependencies {
            classpath 'com.lv.plugins:gradle-weblogic-plugin:<VERSION>'
        }
    }
    apply plugin: 'com.lv.weblogic'
    
    dependencies {
        weblogic files('/path/to/wlfullclient.jar')
    }
```
The weblogic configuration is added by the plugin, but you will need to specify where the wlfullclient jar is located.


# Tasks
The plugin adds 7 tasks to your project; `wlDeploy`, `wlUndeploy`, `wlCancel`, `wlRedeploy`, `wlDistribute`, `wlStart` and `wlStop`.

## Configuration

### build.gradle
```groovy
    wlDeploy {
            adminurl = 't3://localhost:7001'
            appversion
            deploymentName = 'myUberApp'
            source
            targets
            user = 'weblogic'
            password = 'secret'
            debug = false
            verbose = false
            remote = false
            upload = false
            securityModel
            
    }
```

## Task properties
### generic properties

* `adminurl` : The URL of the Administration Server. Default 't3://localhost:7001'.
* `appversion` : The version identifier of the deployed application.
* `deploymentName` : The deployment name for the deployed application. Default ${project.name}.
* `source` : The archive file or exploded directory to deploy.
* `targets` : The list of target servers to which the application is deployed.
* `user` : The administrative username. Default 'weblogic'.
* `password` : The administrative password. Default 'welcome1'
* `debug` : Enable wldeploy debugging messages. Default 'false'.
* `verbose` : Specifies whether wldeploy displays verbose output messages. Default 'false'. 
* `remote` : Specifies whether the server is located on a different machine.
* `upload` : Specifies whether the source file(s) are copied to the Administration Server’s upload directory prior to deployment.
* `securityModel` : Specifies the security model of deployment.
  
