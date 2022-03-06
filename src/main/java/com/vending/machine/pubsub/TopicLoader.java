package com.vending.machine.pubsub;

import com.vending.machine.pubsub.impl.MyOrderSubscriber;
import com.vending.machine.pubsub.impl.MyOrderTopic;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TopicLoader {

    private static MyOrderTopic topic;

    public static MyOrderTopic getTopic() {
        if (topic == null) {
            Observer orderSubscriber = new MyOrderSubscriber("order-observer");
            topic = new MyOrderTopic();
            topic.register(orderSubscriber);
        }
        return topic;
    }
}
