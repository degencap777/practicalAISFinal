node {
   def mvnHome
   stage('Preparation') { // for display purposes
      // Aqui copiamos el codigo de github

      // Get some code from a GitHub repository
      git 'https://github.com/agonzalezgarz/PracticaAISFinal.git'
      // Get the Maven tool.
      // ** NOTE: This 'M3' Maven tool must be configured
      // **       in the global configuration.           
      mvnHome = tool 'M3'
   }
   stage('Build') {
      // Run the maven build
      // Compilamos
      env.JAVA_HOME = "${tool 'JDK8'}"
      env.PATH="${env.JAVA_HOME}/bin:${env.PATH}"
      withEnv(["MVN_HOME=$mvnHome"]) {
         if (isUnix()) {
            sh '"$MVN_HOME/bin/mvn" -Dmaven.test.failure.ignore clean package'
         } else {
            bat(/"%MVN_HOME%\bin\mvn" -Dmaven.test.failure.ignore clean package/)
         }
      }
   }

   stage('Create jar'){
   	steps{
   		sh "cd a.gonzalezgarz ; mvn package -DskipTests"
   	}
   }

   stage('Start app'){
   	steps{
   		sh "cd a.gonzalezgarz/target; java -jar a.gonzalezgarz-0.0.1-SNAPSHOT.jar > out.log & echo \$! > pid"
   	}
   }
   stage('Test') {
   	steps{
   		sh "cd a.gonzalezgarz ; mvn test"
   	}
   }
   
   post {
   	always{
   		junit "a.gonzalezgarz/**/target/surefire-reports/TEST-*-xml"
   		sh "kill \$(cat a.gonzalezgarz/target/pid)"
   		archive "a.gonzalezgarz/target/out.log"
   	}
   	sucess{
   		archive "a.gonzalezgarz/target/*.jar"
   	}
   }
   

}