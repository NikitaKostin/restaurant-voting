package model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_vote", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "restaurant_id", "vote_date_time"}, name = "user_vote_unique_user_restaurant_vote_date_time_idx")})
public class UserVote extends AbstractBaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    @NotNull
    private Restaurant restaurant;

    @Column(name = "vote_date_time", nullable = false, columnDefinition = "timestamp default now()", updatable = false)
    @NotNull
    private LocalDateTime voteDateTime;

    public UserVote(User user, Restaurant restaurant, LocalDateTime voteDateTime) {
        this.user = user;
        this.restaurant = restaurant;
        this.voteDateTime = voteDateTime;
    }

    public UserVote() {
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

    public void setVoteDateTime(LocalDateTime voteDateTime) {
        this.voteDateTime = voteDateTime;
    }

    @Override
    public String toString() {
        return "UserVote{" +
                "user=" + user +
                ", restaurant=" + restaurant +
                ", voteDateTime=" + voteDateTime +
                '}';
    }
}
