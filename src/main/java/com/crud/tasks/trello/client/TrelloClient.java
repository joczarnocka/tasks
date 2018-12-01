package com.crud.tasks.trello.client;

import com.crud.tasks.domain.TrelloBoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TrelloClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;

    @Value("${trello.app.key}")
    private String trelloAppKey;

    @Value("${trello.app.token}")
    private String trelloToken;

    @Value("${trello.app.username}")
    private String trelloUsername;


    private URI getURL(){

        return UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint
                + "/members/" + trelloUsername + "/boards")
                .queryParam("key", trelloAppKey)
                .queryParam("token", trelloToken)
                .queryParam("fields", "name,id")
                .queryParam("lists", "all")
                .build()
                .encode()
                .toUri();
    }

    public List<TrelloBoardDto> getTrelloBoards() {

        URI url = getURL();

        TrelloBoardDto[] boardsResponse = restTemplate.getForObject(
                url, TrelloBoardDto[].class
        );

        //if (boardsResponse != null){

        return Arrays.asList(Optional.ofNullable(boardsResponse)
                                     .orElse(new TrelloBoardDto[0]))
                     .stream()
                     .filter(b -> (b.getId() != null
                                    && b.getName() != null
                                    && b.getName().toLowerCase().contains("kodilla")))
                     .collect(Collectors.toList());
                    //}

        //return new ArrayList<>();
    }
}