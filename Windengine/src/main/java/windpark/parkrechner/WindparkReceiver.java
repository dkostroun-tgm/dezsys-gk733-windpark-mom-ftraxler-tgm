package windpark.parkrechner;

import javax.jms.*;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import windpark.model.WindengineData;
import windpark.model.WindengineMessage;

@Component
public class WindparkReceiver {

    @JmsListener(destination = "windengine",containerFactory = "myFactory")
    public void windengineMessage(ActiveMQTextMessage data){
        try {
            Thread.sleep(10000);
            String m = data.getText();
            System.out.println(m);
            this.writeMessage(m);
        } catch (JMSException e) {
            e.printStackTrace();
        }catch(InterruptedException ie){
            ie.printStackTrace();
        }
    }
    public  synchronized void writeMessage(String message){


    }

}
	
