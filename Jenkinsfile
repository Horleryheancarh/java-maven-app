def gv

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
                    gv = load "script.groovy"
                }
            }
        }
        stage("test") {
            steps {
                script {
                    gv.testApp()
                }
            }
        }
        stage("version") {
          steps {
            script {
              echo "Incrementing version"
              gv.versionApp()
            }
          }
        }
        stage("build jar") {
            when {
              expression {
                BRANCH_NAME == 'master'
              }
            }
            steps {
                script {
                    gv.buildJar()
                }
            }
        }
        stage("build docker image") {
            when {
              expression {
                BRANCH_NAME == 'master'
              }
            }
            steps {
                script {
                      gv.buildImage()
                }
            }
        }
        stage("deploy") {
            when {
              expression {
                BRANCH_NAME == 'master'
              }
            }
            steps {
                script {
                    gv.deployApp()
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