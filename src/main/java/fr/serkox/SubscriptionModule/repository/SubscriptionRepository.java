package fr.serkox.SubscriptionModule.repository;

import fr.serkox.SubscriptionModule.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    void deleteSubscriptionByFollowerIdAndUserId(Integer followerId, Integer userId);

    boolean existsSubscriptionByUserIdAndFollowerId(Integer userId, Integer followerId);

}
