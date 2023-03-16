package fr.serkox.SubscriptionModule.controllers;

import fr.serkox.SubscriptionModule.model.Subscription;
import fr.serkox.SubscriptionModule.model.SubscriptionResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import fr.serkox.SubscriptionModule.service.SubscriptionService;

import java.util.List;
import java.util.Objects;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("/subscription")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;


    private static boolean userIdOrFollowerIdIsNull(Subscription subscription){
        return isNull(subscription.getUserId()) || isNull(subscription.getFollowerId());
    }

    private static boolean userIdAndFollowerIdAreEquals(Subscription subscription){
        return Objects.equals(subscription.getFollowerId(), subscription.getUserId());
    }

    @PostMapping("")
    public ResponseEntity<SubscriptionResponse> createSubscription(@RequestBody Subscription subscription){
        if(userIdOrFollowerIdIsNull(subscription)){
            return new ResponseEntity<>(new SubscriptionResponse(subscription,"One of the ID is null !"), HttpStatus.BAD_REQUEST);
        }
        if(userIdAndFollowerIdAreEquals(subscription)){
            return new ResponseEntity<>(new SubscriptionResponse(subscription,"Both ID are equals !"), HttpStatus.BAD_REQUEST);
        }
        if(subscriptionService.subscriptionExist(subscription)){
            return new ResponseEntity<>(new SubscriptionResponse(subscription,"The subscription already exist !"), HttpStatus.CONFLICT);
        }
        if(subscriptionService.save(subscription) != null){
            return new ResponseEntity<>(new SubscriptionResponse(subscription,"The subscription has been created !"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new SubscriptionResponse(subscription,"An error has occured !"), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("")
    public ResponseEntity<SubscriptionResponse> deleteSubscription(@RequestBody Subscription subscription){
        if(userIdOrFollowerIdIsNull(subscription)) {
            return new ResponseEntity<>(new SubscriptionResponse(subscription,"One of the ID is null !"), HttpStatus.BAD_REQUEST);
        }
        if(userIdAndFollowerIdAreEquals(subscription)){
            return new ResponseEntity<>(new SubscriptionResponse(subscription,"Both ID are equals !"), HttpStatus.BAD_REQUEST);
        }
        if(subscriptionService.subscriptionExist(subscription)){
            subscriptionService.delete(subscription);
            return new ResponseEntity<>(new SubscriptionResponse(subscription,"The subscription has been deleted !"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new SubscriptionResponse(subscription,"The subscription does not exist !"), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getAllSubscriptions/{userId}")
    public List<Subscription> getAllSubscriptions(@PathVariable Integer userId){
        return subscriptionService.getAllSubscriptions(userId);
    }

    @GetMapping("/getAllSubscribers/{followerId}")
    public List<Subscription> getAllSubscribers(@PathVariable Integer followerId){
        return subscriptionService.getAllSubscribers(followerId);
    }


}
