# kafka-case

Example kafka Producer and Consumer apps.

Article: <https://www.javaworld.com/article/3060078/big-data/big-data-messaging-with-kafka-part-1.html>

Source: <https://github.com/denisecase/kafka-api>

Goal: Create a new CustomProducer to auto-generate and send at least 10 messages to the topic. You can create a random word generator, cycle through a list sending one message every second, or use Facebook or Twitter APIs to access live content. Experiment - the goal is to be comfortable adding external messaging to any Java app.

## Recommended Environment

* [Notepad++](https://notepad-plus-plus.org/)
* [VS Code](https://code.visualstudio.com/)
* [VS Code Extension - Java Extension Pack](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack)

Open PowerShell as Administrator and run:

```PowerShell
choco install notepadplusplus -y
choco install vscode -y
refreshenv
```

## Design and Implement a Custom Producer App

1. Plan and design a routine to create messages without user typing.
You may use an array of messages, generate them randomly, or access a public API.
If you want to test the CaseKafka example that accesses Twitter data, 
you'll need to follow the Twitter guides below and create your custom
src/main/resources/twitter4j.properties file. 

2. Implement your Custom Producer app.

3. Compile and build a new executable jar with maven using the mvn clean compile assembly:single command.

4. Start your consumer using a custom java -cp command (short for 'classpath').

5. Verify your messages are output by the Consumer.

6. Create a professional README.md file to explain how to setup and run your project.

7. Put your entire code solution in a repo and share a clickable link.

## Install Prerequisities

* OpenJDK or JDK (8 or up)
* Maven
* Apache Zookeeper
* Apache Kafka

Also:

* Configure system environment variables including Path.
* Configure Zookeeper
* Configure Kafka

## Configure pom.xml

* Determine your namespace.
* Follow the Maven standard project directory structure.
* Write your custom Kafka Producer in your namespace.
* Write your custome Kafka Consumer in your namespace.

## Compile and Build a Fat Jar File

Open PowerShell as Administrator in the root project folder, compile the code using Maven and create an executable jar file. Generated artificacts can be found in the new 'target' folder.

```PowerShell
mvn clean compile assembly:single
```

## 1 - Start Zookeeper Service

Start and keep running the Zookeeper service.

```PowerShell
zkserver
```

## 2 - Start Kafka Service

Start and keep running the Kafka service. What directory must you be in?

```PowerShell
 .\kafka-server-start.bat .\server.properties
```

## 3 - Start Consumer

Open PowerShell as Administrator in the root project folder, start the original consumer app:

```PowerShell
java -cp target/kafka-case-1.0-SNAPSHOT-jar-with-dependencies.jar edu.nwmissouri.isl.professorcase.kafka.CustomConsumer
```

## 4 - Start Producer

Open a new PowerShell as Administrator in the root project folder, start the Producer app using topic test:

```PowerShell
java -cp target/kafka-case-1.0-SNAPSHOT-jar-with-dependencies.jar edu.nwmissouri.isl.professorcase.kafka.CustomProducer
```

## Test Communications

Verify messages are output by the Consumer.

## Accessing Twitter with Code

See http://www.baeldung.com/twitter4j

For help registering your Twitter API see this Tweepy article: <http://www.compjour.org/tutorials/getting-started-with-tweepy/>

Register app:
https://apps.twitter.com

Developer Agreement: 
https://developer.twitter.com/en/developer-terms/agreement-and-policy

Twitter App Attributes Example:

* Name: KafkaCaseCustomProducer
* Description: Sample app to illustrate working with the Twitter API and using Kafka from a Java application
* Website: https://bitbucket.org/professorcase/h08
* Callback URL: (blank)

## See also

http://cloudurable.com/blog/kafka-tutorial-kafka-producer/index.html

## Reference

See <https://bitbucket.org/professorcase/h07>.
