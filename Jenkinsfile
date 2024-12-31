pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/ridado/nbpApiRestAssuredTest.git'
            }
        }

        stage('Build') {
            steps {
                // Budowanie projektu Maven
                sh 'mvn clean'
            }
        }

        stage('Test') {
            steps {
                // Uruchamianie testów Maven
                sh 'mvn test'
            }
        }

        stage('Post Results') {
            steps {
                // Publikacja wyników testów w konsoli
                junit 'target/surefire-reports/*.xml' // Zmodyfikuj ścieżkę, jeśli używasz innego raportowania
            }
        }
    }

    post {
        always {
            echo 'Pipeline zakończony.'
        }
        success {
            echo 'Pipeline zakończył się sukcesem.'
        }
        failure {
            echo 'Pipeline zakończył się niepowodzeniem. Sprawdź logi.'
        }
    }
}