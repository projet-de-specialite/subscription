package fr.serkox.SubscriptionModule.model;

import jakarta.persistence.*;

@Entity
@Table(name="subscription")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="userId")
    private Integer userId;

    @Column(name="followerId")
    private Integer followerId;

    public Subscription(Integer user_id, Integer follower_id){
        super();
        this.userId = user_id;
        this.followerId = follower_id;
    }

    public Subscription() {
        super();
    }

    public Long getId(){
        return this.id;
    }

    public Integer getUserId(){
        return this.userId;
    }

    public Integer getFollowerId(){
        return this.followerId;
    }

    public void setUserId(Integer id){
        this.userId=id;
    }

    public void setFollowerId(Integer id){
        this.followerId=id;
    }

}
