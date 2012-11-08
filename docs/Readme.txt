Project Structure:
	cs414-as5-client	- Client Module
	cs414-as5-common	- Common Module
	cs414-as5-server	- Server Module
	cs414-as5-package	- Packaging Module
	docs				- Project Documentation
	pom.xml				- Maven POM
	.project			- Eclipse Project

Module Structure:
	src/  		- Module Source Code
	pom.xml		- Maven POM
	.project	- Eclipse Project

To Test:
	Execute 'mvn test'
	Follow Test Plan
	
To Run:
	1) Execute 'java -jar dist/A5-btsaunde-server-executable-jar-with-dependencies.jar'
	2) Execute 'java -jar dist/A5-btsaunde-client-executable-jar-with-dependencies.jar'
	Note: See User Guide for Brief Tutorial
	Note: Admin Pin for All Clients is '1234'

Patterns Used:
	Low Coupling was used in the Design of all of the classes
	High Cohesion was used in the Design of all of the classes
	Polymorphism was used in the Design of the Service's that are used with RMI
	
	The Observer Pattern was used between the Server and Client so that the Client's will be notified when changes are made to the state of the garage.
	The Controller Pattern was used in the Client to separate the business code from the user interface
	
Assignment Thoughts:
I think the Iterations for the Assignment could do a better job at re-enforcing the need to use Design Patterns. I would rather the first Iteration include the requirements for multiple gates, but no reporting. The second Iteration would add the use of Design Patterns and reporting. Also, I would use REST or SOAP web services to do the communication in place of RMI. Since the on-campus students are already using it to communicate to the Android client, it makes sense to use it for all the communication and to allow the distance students to use it. This also cuts down on the amount of lecture time that has to be taken up to teach all of these things.