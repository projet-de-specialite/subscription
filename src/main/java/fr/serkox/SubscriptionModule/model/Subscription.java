package fr.serkox.SubscriptionModule.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="subscription")
@Getter
@Setter
@RequiredArgsConstructor
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="userId")
    private Integer userId;

    @Column(name="followerId")
    private Integer followerId;

}
