package fr.serkox.SubscriptionModule;

import fr.serkox.SubscriptionModule.model.Subscription;
import fr.serkox.SubscriptionModule.repository.SubscriptionRepository;
import fr.serkox.SubscriptionModule.service.SubscriptionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = SubscriptionService.class)
@ExtendWith(SpringExtension.class)
public class SubscriptionServiceTest {

    @Autowired
    private SubscriptionService subscriptionService;

    @MockBean
    private SubscriptionRepository subscriptionRepository;

    @Test
    void saveSubscription() {
        Subscription subscription = new Subscription();
        subscription.setUserId(1);
        subscription.setFollowerId(2);

        when(subscriptionRepository.save(subscription)).thenReturn(subscription);

        Subscription savedSubscription = subscriptionService.save(subscription);

        assertNotNull(savedSubscription);
        assertEquals(subscription.getUserId(), savedSubscription.getUserId());
        assertEquals(subscription.getFollowerId(), savedSubscription.getFollowerId());
    }

    @Test
    void deleteSubscription() {
        Subscription subscription = new Subscription();
        subscription.setUserId(1);
        subscription.setFollowerId(2);

        doNothing().when(subscriptionRepository).deleteSubscriptionByFollowerIdAndUserId(subscription.getFollowerId(), subscription.getUserId());

        subscriptionService.delete(subscription);

        verify(subscriptionRepository, times(1)).deleteSubscriptionByFollowerIdAndUserId(subscription.getFollowerId(), subscription.getUserId());
    }

    @Test
    void checkSubscriptionExist() {
        Subscription subscription = new Subscription();
        subscription.setUserId(1);
        subscription.setFollowerId(2);

        when(subscriptionRepository.existsSubscriptionByUserIdAndFollowerId(subscription.getUserId(), subscription.getFollowerId())).thenReturn(true);

        boolean exists = subscriptionService.subscriptionExist(subscription);

        assertTrue(exists);
    }

    @Test
    void getAllSubscriptions() {
        Integer userId = 1;

        List<Subscription> subscriptions = new ArrayList<>();
        Subscription subscription = new Subscription();
        subscription.setFollowerId(1);
        subscription.setUserId(2);
        subscriptions.add(subscription);
        Subscription subscription1 = new Subscription();
        subscription1.setFollowerId(2);
        subscription1.setUserId(3);
        subscriptions.add(subscription1);

        when(subscriptionRepository.findAllByUserId(userId)).thenReturn(subscriptions);

        List<Subscription> foundSubscriptions = subscriptionService.getAllSubscriptions(userId);

        assertNotNull(foundSubscriptions);
        assertEquals(subscriptions.size(), foundSubscriptions.size());
        assertEquals(subscriptions.get(0).getFollowerId(), foundSubscriptions.get(0).getFollowerId());
        assertEquals(subscriptions.get(1).getFollowerId(), foundSubscriptions.get(1).getFollowerId());
    }

    @Test
    void getAllSubscribers() {
        Integer followerId = 2;

        List<Subscription> subscriptions = new ArrayList<>();
        Subscription subscription = new Subscription();
        subscription.setFollowerId(1);
        subscription.setUserId(2);
        subscriptions.add(subscription);
        Subscription subscription1 = new Subscription();
        subscription1.setFollowerId(2);
        subscription1.setUserId(3);
        subscriptions.add(subscription1);

        when(subscriptionRepository.findAllByFollowerId(followerId)).thenReturn(subscriptions);

        List<Subscription> foundSubscriptions = subscriptionService.getAllSubscribers(followerId);

        assertNotNull(foundSubscriptions);
        assertEquals(subscriptions.size(), foundSubscriptions.size());
        assertEquals(subscriptions.get(0).getUserId(), foundSubscriptions.get(0).getUserId());
        assertEquals(subscriptions.get(1).getUserId(), foundSubscriptions.get(1).getUserId());
    }
}
