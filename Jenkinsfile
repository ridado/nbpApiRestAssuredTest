pipeline {
    agent any
    options {
        disableConcurrentBuilds()
    }
    stages {
        }
        stage('Clean') {
            steps {
                sh "mvn clean"
            }
        }
        stage('Checkout') {
                    steps {
                        // Klonowanie repozytorium
                        git branch: 'main', url: 'https://github.com/ridado/nbpApiRestAssuredTest.git'
                    }
        stage('Test') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    sh "mvn test"
                }
            }
        }
        stage('Report') {
            steps {
                script {
                    allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: 'target/allure-results']]
                    ])
                }
            }
        }
    }
}