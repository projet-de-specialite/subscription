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
        return this.subscriptionRepository.save(subscription);
    }

    @Transactional
    public void delete(Subscription subscription){
        this.subscriptionRepository.deleteSubscriptionByFollowerIdAndUserId(subscription.getFollowerId(), subscription.getUserId());
    }

    public boolean subscriptionExist(Subscription subscription){
        return this.subscriptionRepository.existsSubscriptionByUserIdAndFollowerId(subscription.getUserId(), subscription.getFollowerId());
    }

}