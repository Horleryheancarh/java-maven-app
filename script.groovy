def testApp() {
  echo "testing the application version"
  echo "running pipeline for branch $BRANCH_NAME"
  sh 'mvn test'
}


def deployApp() {
    echo 'deploying the application...'
} 

return this