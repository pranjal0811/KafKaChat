/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kafkachat;

import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 *
 * @author pranjal
 */
public class MyConsumer extends Thread {
   
    public void run(){
            Properties prop2 = new Properties();
                prop2.put("bootstrap.servers","localhost:9092,localhost:9093");
                prop2.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
                prop2.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
                prop2.put("group.id","nogroup");
        
            KafkaConsumer<String, String> consumer = new KafkaConsumer<>(prop2);
            consumer.subscribe(Arrays.asList("line1"));
            while(true){
                ConsumerRecords<String, String> records = consumer.poll(100);
                for(ConsumerRecord<String, String> record: records){
                    System.out.println(record.value());
                }
            }
    }
        
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String topic = "line2";
        String key ="key";
        String value = "Chat begins";
        Properties prop1 = new Properties();
        prop1.put("bootstrap.servers","localhost:9092,localhost:9093");
        prop1.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        prop1.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
        System.out.println("type EXIT for end");
        Producer<String, String> producer = new KafkaProducer<>(prop1);
        MyConsumer consumer = new MyConsumer();
        consumer.start();
        while(!value.equals("EXIT")){
        ProducerRecord<String, String> record = new ProducerRecord<>(topic,key,value);
        producer.send(record);
        value = in.nextLine();
        }
        producer.close();
        System.exit(0);
   }
}
