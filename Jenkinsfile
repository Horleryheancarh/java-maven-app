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
            steps {
                script {
                    echo "building the application version"
                }
            }
        }
        stage("test") {
            steps {
                script {
                    echo "testing the application version"
                }
            }
        }
        stage("deploy") {
            steps {
                script {
                    echo "deploying the application version"
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