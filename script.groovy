def testApp() {
  echo "testing the application version"
  echo "running pipeline for branch $BRANCH_NAME"
  sh 'mvn test'
}

def versionApp() {
  echo "versioning app"
  sh 'mvn build-helper:parse-version versions:set \
  -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.newIncrementalVersion} \
  versions:commit'
  def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
  def version = matcher[0][1]
  env.IMAGE_NAME = "$version-$BUILD_NUMBER"
}

def buildJar() {
    echo "building the application..."
    sh 'mvn clean package'
} 

def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'nexus-docker-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        echo "$USER $PASS"
        sh "docker build -t 13.40.179.85:8083/java-maven-app:${IMAGE_NAME} ."
        sh "echo $PASS | docker login -u $USER --password-stdin 13.40.179.85:8083"
        sh "docker push 13.40.179.85:8083/java-maven-app:${IMAGE_NAME}"
    }
} 

def deployApp() {
    echo 'deploying the application...'
} 

def versionUpdate() {
  withCredentials([usernamePassword(credentialsId: 'github-cred', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
      sh 'git config --global user.email "jenkins@example.com"'
      sh 'git config --global user.name "jenkins"'

      sh 'git status'
      sh 'git branch'
      sh 'git config --list'

      echo "$USER $PASS"
      sh "git remote set-url origin https://${USER}:${PASS}@github.com/Horleryheancarh/java-maven-app.git"
      sh 'git add .'
      sh 'git commit -m "ci: version update"'
      sh 'git push origin HEAD:jenkins-jobs'
    }
}

return this