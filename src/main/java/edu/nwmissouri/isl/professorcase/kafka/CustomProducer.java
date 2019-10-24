package edu.nwmissouri.isl.professorcase.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.Paging;
import twitter4j.Status;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;

/**
 * Custom Producer using Kafka for messaging. 
 * Reads properties from the run.properties file in 
 * src/main/resources.
 */
public class CustomProducer {
  private static FileInputStream runStream = null;
  private static Properties runProperties = new Properties();

  public static void main(String[] argv) throws Exception {

    // Create an input stream for the run properties ................
    String runFile = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator
        + "resources" + File.separator + "run.properties";
    System.out.println("Reading config from " + runFile);
    runStream = new FileInputStream(runFile);

    // Load properties and display
    runProperties.load(runStream);
    System.out.println("Run properties.................");
    System.out.println("BOOTSTRAP_SERVERS_CONFIG =      " + runProperties.getProperty("BOOTSTRAP_SERVERS_CONFIG"));
    System.out.println("KEY_SERIALIZER_CLASS_CONFIG =   " + runProperties.getProperty("KEY_SERIALIZER_CLASS_CONFIG"));
    System.out.println("VALUE_SERIALIZER_CLASS_CONFIG = " + runProperties.getProperty("VALUE_SERIALIZER_CLASS_CONFIG"));
    System.out.println("TOPIC =                         " + runProperties.getProperty("TOPIC"));
    System.out.println("TWITTER_USER =                  " + runProperties.getProperty("TWITTER_USER"));
    System.out.println("DELAY_MS =                      " + runProperties.getProperty("DELAY_MS"));

    String topicName = runProperties.getProperty("TOPIC");
    String user = runProperties.getProperty("TWITTER_USER");
    int delay_ms = Integer.parseInt(runProperties.getProperty("DELAY_MS"));

    //Configure the Producer
    Properties configProperties = new Properties();
    configProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
        runProperties.getProperty("BOOTSTRAP_SERVERS_CONFIG"));
    configProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
        runProperties.getProperty("KEY_SERIALIZER_CLASS_CONFIG"));
    configProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
        runProperties.getProperty("VALUE_SERIALIZER_CLASS_CONFIG"));
    org.apache.kafka.clients.producer.Producer<String, String> producer = new KafkaProducer<String, String>(configProperties);

    System.out.println("==========================================");
    System.out.println("You must start a consumer to see messages.");
    System.out.println("==========================================");
    System.out.println("\nStarting custom producer..............\n");

    Twitter twitter = getTwitterinstance();
    int pageno = 1;
    int i = 1;
    List<Status> statuses = new ArrayList<Status>();
    try {
      Paging page = new Paging(pageno, 20);
      statuses.addAll(twitter.getUserTimeline(user, page));
      System.out.println("Total: " + statuses.size());
      for (Status status : statuses) {
        String s = "@" + status.getUser().getScreenName() + ":" + status.getText();
        System.out.println("Posting tweet number " + i++ +". See Consumer for details.");
        ProducerRecord<String, String> rec = new ProducerRecord<String, String>(topicName, s);
        producer.send(rec);
        Thread.sleep(delay_ms);
      }
    } catch (TwitterException e) {
      e.printStackTrace();
    }
    producer.close();
  }

public static Twitter getTwitterinstance() throws IOException {

    FileInputStream twitterStream = null;
    Properties twitterProperties = new Properties();

    String twitterFile = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
        + File.separator + "resources" + File.separator + "twitter4j.properties";
    System.out.println("Reading config from " + twitterFile +"\n");
    twitterStream = new FileInputStream(twitterFile);

    twitterProperties.load(twitterStream);
    System.out.println("Displaying Twitter properties:\n");
    System.out.println("  oauth.consumerKey =       "+ twitterProperties.getProperty("oauth.consumerKey"));
    System.out.println("  oauth.consumerSecret =    "+ twitterProperties.getProperty("oauth.consumerSecret"));
    System.out.println("  oauth.accessToken =       "+ twitterProperties.getProperty("oauth.accessToken"));
    System.out.println("  oauth.accessTokenSecret = "+ twitterProperties.getProperty("oauth.accessTokenSecret"));

    ConfigurationBuilder cb = new ConfigurationBuilder();

    cb.setDebugEnabled(true).setOAuthConsumerKey(twitterProperties.getProperty("oauth.consumerKey"))
        .setOAuthConsumerSecret(twitterProperties.getProperty("oauth.consumerSecret"))
        .setOAuthAccessToken(twitterProperties.getProperty("oauth.accessToken"))
        .setOAuthAccessTokenSecret(twitterProperties.getProperty("oauth.accessTokenSecret"));

    TwitterFactory tf = new TwitterFactory(cb.build());
    Twitter twitter = tf.getInstance();
    System.out.println("\nReturning twitter instance ..............\n");
    return twitter;
  }
}
