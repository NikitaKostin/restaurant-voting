package ru.javadiploma.restaurantvoting.model;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_vote",  uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "restaurant_id", "vote_date_time"}, name = "user_vote_unique_user_restaurant_vote_date_time_idx")})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true, exclude = {"user", "restaurant"})
public class UserVote extends BaseEntity {
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
}
