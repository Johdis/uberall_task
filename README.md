# Uberall task

## Introduction, Technology, Problems

I tried to combine simplicity for the task and page objects into the code example. 
For the solution I used Selenium 3, Java, TestNG, gradleWrapper (based on maven) and Docker. 

Unfortunately I was not completely sure about the task itself. 
For the test documentation I created a _**second failing test case which gives you the possibility to check the error behaviour**_ and screenshots of the log file.
You can find it after the test finished in the projects root folder. 
The name is *Testresult "current date"*

From the test scenario I tried to fulfill the task, which is the successful test case. 
Please ping me if you expected a completely different flow.

## Execute the test

You can execute the test on each machine (Linux, Mac, Windows) by using docker. 
Please note: I did not create a cache image, if you want I can create it easily, but a server solution would be benefitial for the cache image.

[Please download and install docker here](https://www.docker.com/community-edition)

After installing just navigate in a terminal to the cloned repository and start the test with _**docker-compose run test_local**_

Everything what is not installed on your machine will be downloaded by docker and gradle.

After the execution you should use _**docker-compose down**_ to clear containers and the RAM of your machine.

Please let me know if you have any questions.
