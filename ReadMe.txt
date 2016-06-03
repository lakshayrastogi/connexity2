
Instructions for running/setting up the website:
	Requites:
	1)  Have either connexity2-0.0.1-SNAPSHOT.jar or the root folder and contents of project 
	2)  Have MAMP installed (this is for the mySQL database)

	Steps to run if given root folder and contents:
	1)  Ensure you are in the root folder
	2)  Type and run command:  mvn clean install
	3)  Type and run command:  java -jar target/connexity2-0.0.1-SNAPSHOT.jar
		If error occurs where “address in use” is sited as the error follow these instructions and then redo #2:
			1) Find pid that is running at port 8080 via the command: lsof -i:8080
			2) Kill the pid via the command: kill <pid>
	4)  Run MAMP and click start server (ensure that MySQL server box is checked)
	5)  In MAMP check to ensure port number is correct by going to preferences->ports and see if the
	MySQL Port number is 8889.
	6)  Now go to favorite browser and go to: http://localhost:8080 to begin using the website!

	Steps to run given only jar:
	1)  Navigate to folder where jar is located
	2)  Type and run command:  java -jar connexity2-0.0.1-SNAPSHOT.jar
		If error occurs where “address in use” is sited as the error follow these instructions and then redo #2:
			1) Find pid that is running at port 8080 via the command: lsof -i:8080
			2) Kill the pid via the command: kill <pid>
	3)  Run MAMP and click start server (ensure that MySQL server box is checked)
	4)  In MAMP check to ensure port number is correct by going to preferences->ports and see if the
	MySQL Port number is 8889.
	5)  Now go to favorite browser and go to: http://localhost:8080 to begin using the website!
	
