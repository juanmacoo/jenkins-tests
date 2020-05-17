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
            expression {
              return sh(returnStdout: true, script: "git rev-parse origin/master").trim() == GIT_COMMIT
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
        expression {
          return sh(returnStdout: true, script: "git rev-parse origin/master").trim() == GIT_COMMIT
        }
      }
      steps {
        sh 'echo Production'
      }
    }
  }
}

