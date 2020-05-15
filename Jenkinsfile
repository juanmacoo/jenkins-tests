pipeline {
  agent any
  options {
    timestamps()
  }

  stages{
    stage('Deploy staging') {
      when {
        anyOf {
          environment name: 'DEPLOY_MASTER_ON_STAGING', value: 'true'
          not {
            environment name: 'GIT_COMMIT', value: 'master'
          }
          not {
            expression {
              return sh(returnStdout: true, script: "test \$(git branch --contains $GIT_COMMIT | grep master)").trim()
            }
          }
        }
      }
      steps {
        sh 'echo Test Staging'
      }
    }

    stage('Deploy production') {
      when {
        not { environment name: 'DEPLOY_MASTER_ON_STAGING', value: 'true' }
        anyOf {
          environment name: 'GIT_COMMIT', value: 'master'
          expression {
            return sh(returnStdout: true, script: "test \$(git branch --contains $GIT_COMMIT | grep master)").trim()
          }
        }
      }
      steps {
        sh 'echo Production'
      }
    }
  }
}

