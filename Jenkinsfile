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
          expression {
            return sh(returnStdout: true, script: "git branch --all --contains $GIT_COMMIT | grep master").trim().isEmpty()
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
        not {
          expression {
            return sh(returnStdout: true, script: "git branch --all --contains $GIT_COMMIT | grep master").trim().isEmpty()
          }
        }
      }
      steps {
        sh 'echo Production'
      }
    }
  }
}

