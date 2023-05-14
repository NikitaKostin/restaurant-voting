package ru.javadiploma.restaurantvoting.web.vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.javadiploma.restaurantvoting.model.UserVote;
import ru.javadiploma.restaurantvoting.service.UserVoteService;
import ru.javadiploma.restaurantvoting.to.UserVoteTo;
import ru.javadiploma.restaurantvoting.web.AuthUser;

import javax.validation.Valid;

@RestController
@RequestMapping(value = UserVoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserVoteController {
    static final String REST_URL = "/api/rest/user/votes";

    @Autowired
    private UserVoteService userVoteService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public UserVote vote(@Valid @RequestBody UserVoteTo userVoteTo, @AuthenticationPrincipal AuthUser authUser) {
        return userVoteService.vote(userVoteTo, authUser.id());
    }
}
