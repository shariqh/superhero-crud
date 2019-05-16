pipeline {
//    agent any
    stages {
        stage('build') {}
        stage('unit-test') {
            when {
                expression {
                    return env.GIT_BRANCH != 'origin/master'
                }
            }
            steps {
                script {
                    try {
                        sh './gradlew clean test'
                        updateGitlabCommitStatus name: 'unit test', state: 'success'
                    } catch (exc) {
                        // this is so we can capture the results in 'finally' below
                        throw exec
                    } finally {
                        junit '**/build/test-results/test/*.xml'
                    }
                }
            }
        }
        stage('integration-test') {
            when {
                expression {
                    return env.GIT_BRANCH != 'origin/master'
                }
            }
            steps {
                script {
                    try {
                        sh './gradlew clean iTest'
                    } catch (exc) {
                        // this is so we can capture the results in 'finally' below
                        throw exec
                    } finally {
                        junit '**/build/test-results/integrationTest/*.xml'
                    }
                }
            }
        }
        stage('deploy-develop') {
//            when {
//                expression {
//                    return env.GIT_BRANCH == 'origin/develop'
//                }
//            }
            steps {
                sh 'heroku push origin master'
            }
        }
    }
    post {
        always {
            // This deletes the workspace so that jenkins does not run out of memory
            deleteDir()
        }
//        success {
//            updateGitlabCommitStatus state: 'success'
//        }
//        failure {
//            updateGitlabCommitStatus state: 'failed'
//        }
    }
}
