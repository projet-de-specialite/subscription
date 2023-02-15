package fr.serkox.SubscriptionModule.controllers;

import fr.serkox.SubscriptionModule.model.Subscription;
import fr.serkox.SubscriptionModule.model.SubscriptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import fr.serkox.SubscriptionModule.service.SubscriptionService;

import java.util.Objects;

import static java.lang.Character.isDigit;
import static java.util.Objects.isNull;

@RestController
@RequestMapping("/subscription")
@Slf4j
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService){
        this.subscriptionService = subscriptionService;
    }

    @PostMapping("")
    public SubscriptionResponse createSubscription(@RequestBody Subscription subscription){
        if(isNull(subscription.getUserId()) || isNull(subscription.getFollowerId())) return SubscriptionResponse.ERROR;
        if(Objects.equals(subscription.getFollowerId(), subscription.getUserId())) return SubscriptionResponse.ERROR;
        if(subscriptionService.subscriptionExist(subscription) != null) return SubscriptionResponse.ERROR;
        if(!isDigit(subscription.getUserId()) || !isDigit(subscription.getFollowerId())) return SubscriptionResponse.ERROR;
        if(this.subscriptionService.save(subscription) != null) return SubscriptionResponse.OK;
        return SubscriptionResponse.ERROR;
    }


}
