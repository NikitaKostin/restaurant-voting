package ru.javadiploma.restaurantvoting.to;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class UserVoteTo {
    @NotNull
    private int restaurantId;

    @NotNull
    private LocalDateTime voteDateTime;

    public UserVoteTo() {
    }

    public UserVoteTo(int restaurantId, LocalDateTime voteDateTime) {
        this.restaurantId = restaurantId;
        this.voteDateTime = voteDateTime;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public LocalDateTime getVoteDateTime() {
        return voteDateTime;
    }

    public void setVoteDateTime(LocalDateTime voteDateTime) {
        this.voteDateTime = voteDateTime;
    }

    @Override
    public String toString() {
        return "UserVoteTo{" +
                ", restaurantId=" + restaurantId +
                ", voteDateTime=" + voteDateTime +
                '}';
    }
}
