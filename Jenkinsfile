pipeline {
    agent any
    environment {
      NEW_VERSION = '1.3.0'
      SERVER_CREDENTIALS = credentials('server_credentials')
    }
    stages {
        stage("init") {
            steps {
                script {
                    echo "initialization"
                }
            }
        }
        stage("build") {
            steps {
                script {
                    echo "building the application version ${NEW_VERSION}"
                }
            }
        }
        stage("test") {
            steps {
                script {
                    echo "testing the application version ${NEW_VERSION}"
                }
            }
        }
        stage("deploy") {
            steps {
                script {
                    echo "deploying the application version ${NEW_VERSION}"
                    withCredentials ([
                      usernamePassword(credentials: 'server_credentials', usernameVariable: USER, passwordVariable: PWD)
                    ]) {
                      sh "cat ${USER} ${PWD}"
                    }
                    echo "deploying using ${SERVER_CREDENTIALS}"
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