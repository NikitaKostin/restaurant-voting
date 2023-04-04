package to;

public class RestaurantTo {
    private final Integer id;

    private final String name;

    private final Integer voteCount;

    public RestaurantTo(Integer id, String name, Integer voteCount) {
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
                ", voteCount=" + voteCount +
                '}';
    }
}
