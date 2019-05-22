pipeline {
 	tools{
 		maven "M3"
 	}
 	agent any 
 	stages{
 		
 		 stage('Preparation') { // for display purposes
      // Aqui copiamos el codigo de github

      // Get some code from a GitHub repository
      		steps{
      		git 'https://github.com/agonzalezgarz/PracticaAISFinal.git'
      		}
      // Get the Maven tool.
      // ** NOTE: This 'M3' Maven tool must be configured
      // **       in the global configuration.           
     	 mvnHome = tool 'M3'
   		}


 	}
  
 

   stage('Create jar'){
   	steps{
   		sh "cd a.gonzalezgarz ; mvn package -DskipTests"
   	}
   }

   stage('Start app'){
   	steps{
   		sh "cd a.gonzalezgarz/target;java -jar a.gonzalezgarz-0.0.1-SNAPSHOT.jar > out.log & echo \$! > pid"
   	}

   stage('Test') {
   	steps{
   		sh "cd a.gonzalezgarz ; mvn test"
   	}
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