package ru.javadiploma.restaurantvoting.to;

public class RestaurantVoteTo extends BaseTo  {
    private final Integer id;

    private final String name;

    private final Integer voteCount;

    public RestaurantVoteTo(Integer id, String name, Integer voteCount) {
        this.id = id;
        this.name = name;
        this.voteCount = voteCount;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    @Override
    public String toString() {
        return "RestaurantTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
