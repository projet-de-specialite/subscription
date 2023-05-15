package fr.serkox.SubscriptionModule.repository;

import fr.serkox.SubscriptionModule.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    void deleteSubscriptionByFollowerIdAndUserId(Integer followerId, Integer userId);
    boolean existsSubscriptionByUserIdAndFollowerId(Integer userId, Integer followerId);
    List<Subscription> findAllByUserId(Integer userId);
    List<Subscription> findAllByFollowerId(Integer followerId);
}
