package fr.serkox.SubscriptionModule.service;

import fr.serkox.SubscriptionModule.model.Subscription;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import fr.serkox.SubscriptionModule.repository.SubscriptionRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    @Transactional
    public Subscription save(Subscription subscription){
        return subscriptionRepository.save(subscription);
    }

    @Transactional
    public void delete(Subscription subscription){
        subscriptionRepository.deleteSubscriptionByFollowerIdAndUserId(subscription.getFollowerId(), subscription.getUserId());
    }

    public boolean subscriptionExist(Subscription subscription){
        return subscriptionRepository.existsSubscriptionByUserIdAndFollowerId(subscription.getUserId(), subscription.getFollowerId());
    }

    public List<Subscription> getAllSubscriptions(Integer userId){
        return subscriptionRepository.findAllByUserId(userId);
    }

    public List<Subscription> getAllSubscribers(Integer followerId){
        return subscriptionRepository.findAllByFollowerId(followerId);
    }
}
