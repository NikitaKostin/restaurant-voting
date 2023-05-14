package ru.javadiploma.restaurantvoting.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_vote",  uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "restaurant_id", "vote_date_time"}, name = "user_vote_unique_user_restaurant_vote_date_time_idx")})
public class UserVote extends AbstractBaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    @NotNull
    private Restaurant restaurant;

    @Column(name = "vote_date_time", nullable = false, columnDefinition = "timestamp default now()", updatable = false)
    @NotNull
    private LocalDateTime voteDateTime;

    public UserVote() {
    }

    public UserVote(Integer id) {
        super(id);
    }

    public UserVote(Integer id, LocalDateTime voteDateTime) {
        super(id);
        this.voteDateTime = voteDateTime;
    }

    public UserVote(Integer id, User user, Restaurant restaurant, LocalDateTime voteDateTime) {
        super(id);
        this.user = user;
        this.restaurant = restaurant;
        this.voteDateTime = voteDateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public LocalDateTime getVoteDateTime() {
        return voteDateTime;
    }

    @Override
    public String toString() {
        return "UserVote{" +
                "id=" + id +
                ", voteDateTime=" + voteDateTime +
                '}';
    }
}
