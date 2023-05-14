package ru.javadiploma.restaurantvoting.to;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserVoteTo extends BaseTo{
    @NotNull
    private int restaurantId;

    @NotNull
    private LocalDateTime voteDateTime;

    public UserVoteTo(Integer id, int restaurantId, LocalDateTime voteDateTime) {
        super(id);
        this.restaurantId = restaurantId;
        this.voteDateTime = voteDateTime;
    }
}
