CODE_CHANGES = getGitChanges()

pipeline {
    agent any
    stages {
        stage("init") {
            steps {
                script {
                    echo "initialization"
                }
            }
        }
        stage("build") {
            when {
              expression {
                BRANCH_NAME = 'dev' && CODE_CHANGES == true
              }
            }
            steps {
                script {
                    echo "building the application"
                }
            }
        }
        stage("test") {
            steps {
                script {
                    echo "testing the application"
                }
            }
        }
        stage("deploy") {
            steps {
                script {
                    echo "deploying the application"
                }
            }
        }
    }   
    post {
      always {
        echo "Post always"
      }
      success {
        echo "On success only"
      }
      failure {
        echo "On failure only"
      }
    }
}