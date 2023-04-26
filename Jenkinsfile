pipeline {
    agent any
    tools {
      maven 'maven-3.6'
    }
    stages {
        stage("init") {
            steps {
                script {
                    echo "initialization"
                }
            }
        }
        stage("test") {
            steps {
                script {
                    echo "testing the application version"
                    sh 'mvn test'
                }
            }
        }
        stage("build jar") {
            steps {
                script {
                    echo "building the application version"
                    sh 'mvn package'
                }
            }
        }
        stage("build docker image") {
            steps {
                script {
                    echo "building the docker image"
                    withCredentials([usernamePassword(credentialsId: 'docker-credentials', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
                      sh 'docker build -t yheancarh/java-maven-app:jf .'
                      sh "docker login -u $USER -p"
                      sh "echo $PASS"
                      sh 'docker push yheancarh/java-maven-app:jf'
                    }
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