pipeline {
    agent any
    environment {
        IMAGE_NAME = "kantpay-arsen-student-api:v1.0-kantpay-arsen-37705"
        STUDENT_NAME = "Arsen Kantpay"
    }
    stages {
        stage('Checkout') {
            steps {
                echo "Pipeline execution for: ${env.STUDENT_NAME}"
                checkout scm
            }
        }
        stage('Build') {
            steps {
                echo "Compiling application..."
            }
        }
        stage('Test') {
            steps {
                echo "Running tests..."
                sh 'exit 1'
            }
        }
        stage('Docker Build') {
            steps {
                echo "Building Docker image..."
            }
        }
        stage('Verify') {
            steps {
                echo "Verifying..."
            }
        }
    }
}
