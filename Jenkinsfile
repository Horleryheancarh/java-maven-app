#!/usr/bin/env groovy
@Library('jenkins-shared-library')

// Directly from repo
/*
library identifier: 'jenkins-shared-library@master', retriever: modernSCM(
  [
    $class: 'GitSCMSource',
    remote: 'https://github.com/Horleryheancarh/jenkins-shared-library.git',
    credentialsId: 'github-credentials'
  ]
)
*/

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
        stage("build jar") {
            when {
              expression {
                BRANCH_NAME == 'master'
              }
            }
            steps {
                script {
                    buildJar()
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
                      buildImage()
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