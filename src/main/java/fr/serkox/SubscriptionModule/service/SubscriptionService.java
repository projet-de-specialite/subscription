package fr.serkox.SubscriptionModule.service;

import jakarta.transaction.Transactional;
import fr.serkox.SubscriptionModule.model.Subscription;
import org.springframework.stereotype.Service;
import fr.serkox.SubscriptionModule.repository.SubscriptionRepository;

@Service
public class SubscriptionService {

    private SubscriptionRepository subscriptionRepository;

    public SubscriptionService(SubscriptionRepository subscriptionRepository){
        this.subscriptionRepository = subscriptionRepository;
    }

    @Transactional
    public Subscription save(Subscription subscription){
        return subscriptionRepository.save(subscription);
    }

    public Subscription subscriptionExist(Subscription subscription){
        return subscriptionRepository.findSubscriptionByUserIdAndFollowerId(subscription.getUserId(),subscription.getFollowerId());
    }

}
