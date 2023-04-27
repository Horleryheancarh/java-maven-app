def testApp() {
  echo "testing the application version"
  echo "running pipeline for branch $BRANCH_NAME"
  sh 'mvn test'
}

def buildJar() {
    echo "building the application..."
    sh 'mvn package'
} 

def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'nexus-docker-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh 'docker build -t 13.40.179.85:8083/java-maven-app:jma-2.0 .'
        sh "echo $PASS | docker login -u $USER --password-stdin 13.40.179.85:8083"
        sh 'docker push 13.40.179.85:8083/java-maven-app:jma-2.0'
    }
} 

def deployApp() {
    echo 'deploying the application...'
} 

return this