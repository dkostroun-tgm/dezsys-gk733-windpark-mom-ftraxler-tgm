package windpark.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import windpark.Gk73Application;
import windpark.mom.WindengineSimulation;

import java.util.Random;

public class Message {


    private static final Logger LOGGER =
            LoggerFactory.getLogger(Message.class);
    public void run(){
        String id=  ""+(new Random().nextInt(50)+1);
        while (true) {
            try {
                WindengineData data = new WindengineSimulation().getData(id);
                ConfigurableApplicationContext context = SpringApplication.run(Gk73Application.class);

                JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);

                // Send a message with a POJO - the template reuse the message converter
                System.out.println("Sending Windengine Data from "+id);
                jmsTemplate.convertAndSend("windengine", data);
                LOGGER.info("'subscriber1' send message='{}'", data);
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
