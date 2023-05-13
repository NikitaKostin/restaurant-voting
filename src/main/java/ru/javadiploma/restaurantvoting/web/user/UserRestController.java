package ru.javadiploma.restaurantvoting.web.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javadiploma.restaurantvoting.model.UserVote;
import ru.javadiploma.restaurantvoting.service.UserVoteService;
import ru.javadiploma.restaurantvoting.to.UserVoteTo;
import ru.javadiploma.restaurantvoting.util.SecurityUtil;

@RestController
@RequestMapping(value = UserRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestController {
    static final String REST_URL = "/rest/user/votes";

    @Autowired
    private UserVoteService userVoteService;

    @GetMapping("/{id}/restaurants/{restaurantId}")
    public UserVote get(@PathVariable int id, @PathVariable int restaurantId) {
        return userVoteService.get(id, restaurantId, SecurityUtil.authUserId());
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public UserVote vote(@RequestBody UserVoteTo userVoteTo) {
        return userVoteService.vote(userVoteTo, SecurityUtil.authUserId());
    }
}
