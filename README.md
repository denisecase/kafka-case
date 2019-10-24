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

* 7-zip
* OpenJDK or JDK (8 or up)
* Maven
* Apache Zookeeper
* Kafka

Open PowerShell as Administrator and run:

```PowerShell
choco install 7zip -y
choco install openjdk -y
choco install maven -y
refreshenv
```

## Install Apache Zookeeper Binary Version

Go to <https://www.apache.org/dyn/closer.cgi/zookeeper/>. Choose site.  Chose the most current version (e.g., zookeeper-3.5.6), then download the file with "bin" in the name, e.g., apache-zookeeper-3.5.6-bin.tar.gz.

Extract and move to C:

1. Right-click the tar.gz file / 7-zip / extract here.
1. Right-click the .tar file / 7-zip / extract to the default apache-zookeeper folder (with version numbers).
1. When done, move the apache-zookeeper-3.5.6-bin folder to C:\.

## Install Kafka Binary Version

For Kafka, go to <https://kafka.apache.org/downloads>. Chose the most current binary version (e.g., 2.12), then download the tgz file, e.g.,  kafka_2.12-2.3.0.tgz.

Extract and move to C:

1. Right-click the tgz file / 7-zip / extract here.
1. Right-click the .tar file / 7-zip / extract to the default folder.
1. When done, move the kafka_2.12-2.2.0 folder to C:\.

## Set Zookeeper System Environment Variable

Windows + Edit System Environment Variables / Environment Variables / System Variables:

Variable name: ZOOKEEPER_HOME
Variable value: C:\apache-zookeeper-3.5.6-bin

## Verify Other Environment Variables

Your paths may be different - these are the ones on my machine:

```Bash
JAVA_HOME   C:\Program Files\OpenJDK\jdk-13.0.1
KAFKA_HOME  C:\kafka_2.11-2.0.0
M2_HOME     C:\ProgramData\chocolatey\lib\maven\apache-maven-3.6.2
ZOOKEEPER_HOME C:\apache-zookeeper-3.5.6-bin
```

## Update / Verify Path

System Path should include

```Bash
%JAVA_HOME%\bin
%KAFKA_HOME%\bin
%KAFKA_HOME%\bin\windows
%M2_HOME%\bin
%ZOOKEEPER_HOME%\bin
```

## Verify Versions

Open PowerShell here as Administrator and run:

```PowerShell
java -version
mvn -v
```

## Configure Zookeeper

Create the required zoo.cfg. In C:\apache-zookeeper-3.5.6-bin\conf, copy zoo_sample.cfg to zoo.cfg. Open zoo.cfg in Notepad++. Find and edit dataDir=/tmp/zookeeper to dataDir=C:\apache-zookeeper-3.5.6-bin\data

## Configure Kafka

For convenience, copy the C:\kafka_2.12-2.2.0\config\server.properties file from the config folder to the C:\kafka_2.12-2.2.0\bin\windows folder. Open server.properties in Notepad++. Find and edit log.dirs=/tmp/kafka-logs to log.dirs=C:\kafka_2.12-2.2.0\logs

## Start Zookeeper Service

Start the Zookeeper service. Open PowerShell As Administrator (from anywhere) and run the following. Note the port and keep the window open. Zookeeper will run on default port 2181, you can change the default port in <C:\apache-zookeeper-3.5.6-bin\conf\zoo.cfg> file.

```PowerShell
zkserver
```

## Start Kafka Service

Start the Kafka service. Open a new PowerShell As Administrator window in the C:\kafka_2.12-2.2.0\bin\windows folder and run the following. Keep the window open.

```PowerShell
 .\kafka-server-start.bat .\server.properties
```

## Working with Kafka

To run these, open PowerShell as Admininstrator in <C:\kafka_2.12-2.2.0\bin\windows>.

To create a topic called test, run:

```PowerShell
./kafka-topics.bat --zookeeper localhost:2181 --create  --replication-factor 1 --partitions 1 --topic test
```

To delete a topic (e.g. test), run:

```PowerShell
./kafka-topics.bat --zookeeper localhost:2181 --delete --topic test

To list topics, run:

```PowerShell
./kafka-topics.bat --zookeeper localhost:2181 --list
```

To describe a topic (e.g. test), run:

```PowerShell
./kafka-topics.bat --zookeeper localhost:2181 --describe --topic test
```

To write messages to a topic, run:

```PowerShell
./kafka-console-producer.bat --broker-list localhost:9092 --topic test
```

To view the messages on a topic (e.g. test), run:

```PowerShell
./kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic test --from-beginning
```

To list the groups, run:

```PowerShell
./kafka-consumer-groups.bat --bootstrap-server localhost:9092 --list
```

## Compile and Build the Fat Jar File

Open PowerShell as Administrator in the root project folder, compile the code using Maven and create an executable jar file. Generated artificacts can be found in the new 'target' folder.

```PowerShell
mvn clean compile assembly:single
```

## Start Consumer

Open PowerShell as Administrator in the root project folder, start the original consumer app:

```PowerShell
java -cp target/kafka-case-1.0-SNAPSHOT-jar-with-dependencies.jar edu.nwmissouri.isl.professorcase.kafka.CustomConsumer
```

## Start Producer

Open a new PowerShell as Administrator in the root project folder, start the Producer app using topic test:

```PowerShell
java -cp target/kafka-case-1.0-SNAPSHOT-jar-with-dependencies.jar edu.nwmissouri.isl.professorcase.kafka.CustomProducer
```

## Test Communications

1. Type some messages for the Producer.
1. Verify the messages are output by the Consumer.

## Accessing Twitter with Code

See http://www.baeldung.com/twitter4j

For help registering your Twitter API see this Tweepy article: <http://www.compjour.org/tutorials/getting-started-with-tweepy/>

Register app: 
https://apps.twitter.com

Developer Agreement: 
https://developer.twitter.com/en/developer-terms/agreement-and-policy

Twitter App Attributes Example:

- Name: KafkaCaseCustomProducer
- Description: Sample app to illustrate working with the Twitter API and using Kafka from a Java application
- Website: https://bitbucket.org/professorcase/h08
- Callback URL: (blank)

## See also

http://cloudurable.com/blog/kafka-tutorial-kafka-producer/index.html

## Reference

See <https://bitbucket.org/professorcase/h07>.
