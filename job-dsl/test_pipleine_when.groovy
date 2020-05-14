pipelineJob('release-jetbrains-license-server') {

  parameters {
    stringParam("GIT_COMMIT", "master", "Git hash to build.")
    booleanParam("DEPLOY_MASTER_ON_STAGING", false, "Allow deploying the master branch to staging, if selected production deployment will be skipped.")
  }

  definition {
    cpsScm {
      scm {
        git {
          remote {
              url('https://github.com/juanmacoo/jenkins-tests.git')
              credentials('juanmacoo-github')
              branch('$GIT_COMMIT')
          }
        }
        scriptPath('Jenkinsfile')
      }
    }
  }
}

