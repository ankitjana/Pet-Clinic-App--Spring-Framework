node{
  stage('SCM Checkout'){
    git 'https://github.com/ankitjana/Pet-Clinic-App--Spring-Framework'
    }
    stage('Compile-Package'){
      def mvnHome = tool name: 'maven-3', type: 'maven'
      def mvnCMD = "${mvnHome}/bin/mvn"
      sh "${mvnCMD} clean package"
    }
   }
