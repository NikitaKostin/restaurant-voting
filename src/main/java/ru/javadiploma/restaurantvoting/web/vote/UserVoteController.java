package ru.javadiploma.restaurantvoting.web.vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.javadiploma.restaurantvoting.error.ResourceNotFoundException;
import ru.javadiploma.restaurantvoting.model.UserVote;
import ru.javadiploma.restaurantvoting.repository.UserRepository;
import ru.javadiploma.restaurantvoting.repository.UserVoteRepository;
import ru.javadiploma.restaurantvoting.service.UserVoteService;
import ru.javadiploma.restaurantvoting.to.UserVoteTo;
import ru.javadiploma.restaurantvoting.web.AuthUser;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = UserVoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserVoteController {
    static final String REST_URL = "/api/rest/user/votes";

    @Autowired
    private UserVoteService userVoteService;

    @Autowired
    private UserVoteRepository userVoteRepository;

    @Autowired
    protected UserRepository userRepository;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public UserVote vote(@Valid @RequestBody UserVoteTo userVoteTo, @AuthenticationPrincipal AuthUser authUser) {
        return userVoteService.vote(userVoteTo, authUser.id());
    }

    @GetMapping("/today")
    public UserVote get(@AuthenticationPrincipal AuthUser authUser) {
        return userVoteRepository.getByUserAndVoteDateEquals(userRepository.getById(authUser.id()), LocalDate.now()).orElseThrow(
                () -> new ResourceNotFoundException("You haven't voted today")
        );
    }

    @GetMapping("/all")
    public List<UserVote> getAll(@AuthenticationPrincipal AuthUser authUser) {
        return userVoteRepository.getAllByUser(userRepository.getById(authUser.id()));
    }
}
