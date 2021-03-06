package bean;

import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

@Slf4j
@Dependent
public class UpdateListener implements MessageListener {

    @Inject
    private StandView stand;

    @Override
    public void onMessage(Message message) {
        try {
            log.info("Message " + message.getJMSMessageID() + " received");
            processMessage(message);
        } catch (JMSException e) {
            log.error("An error occurred while processing the message", e);
        }
    }

    private void processMessage(Message message) throws JMSException {
        stand.updateStand();
        log.info("Message " + message.getJMSMessageID() + " has been processed");
    }


}