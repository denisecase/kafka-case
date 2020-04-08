# kafka-case

Custom Kafka Producer and Consumer apps.

Article: <https://www.javaworld.com/article/3060078/big-data/big-data-messaging-with-kafka-part-1.html>

Source: <https://github.com/denisecase/kafka-api>

Goal:

* Create your own CustomConsumer.
* Create your own CustomProducer to auto-generate and send at least 10 messages to a topic using the Twitter API.
* Use a custom namespace to avoid naming conflicts with other "CustomProducer" classes.

Alternate:

* Use Facebook API, or create a random word generator, choose a random noun-verb-noun, or cycle through a list sending one message every second.
* Experiment - the goal is to be comfortable adding external messaging to any Java app.

## Recommended Environment

* [Notepad++](https://notepad-plus-plus.org/)
* [VS Code](https://code.visualstudio.com/)
* [VS Code Extension - Java Extension Pack](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack)

## Prerequisities

* Twitter Developer Account
* OpenJDK or JDK (8 or up)
* Apache Maven
* Apache Zookeeper
* Apache Kafka

Also:

* Configure system environment variables including Path.
* Configure Zookeeper
* Configure Kafka

## Complete your Project

* Determine your namespace.
* Follow the Maven standard project directory structure.
* Use src\main\java for your code (with namespace folders)
* Use src\main\resources for your proporties files.
* Write your custom Kafka Producer in your namespace.
* Write your custome Kafka Consumer in your namespace.
* Add pom.xml
* Add README.md
* Add .gitignore

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

## Reference

* Simple examples at <https://github.com/denisecase/kafka-api>

## See also

* <http://cloudurable.com/blog/kafka-tutorial-kafka-producer/index.html>
