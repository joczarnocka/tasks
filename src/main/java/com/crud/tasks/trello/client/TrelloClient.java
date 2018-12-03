package com.crud.tasks.trello.client;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.config.TrelloConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TrelloClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(TrelloClient.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TrelloConfig trelloConfig;

    /*@Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;

    @Value("${trello.app.key}")
    private String trelloAppKey;

    @Value("${trello.app.token}")
    private String trelloToken;

    @Value("${trello.app.username}")
    private String trelloUsername;
*/

    private URI getURL(){

        return UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint()
                + "/members/" + trelloConfig.getTrelloUsername() + "/boards")
                .queryParam("key", trelloConfig.getTrelloAppKey())
                .queryParam("token", trelloConfig.getTrelloToken())
                .queryParam("fields", "name,id")
                .queryParam("lists", "all")
                .build()
                .encode()
                .toUri();
    }

    public List<TrelloBoardDto> getTrelloBoards() {

        URI url = getURL();
        try {


            TrelloBoardDto[] boardsResponse = restTemplate.getForObject(
                    url, TrelloBoardDto[].class
            );

            //if (boardsResponse != null){

            return Arrays.asList(Optional.ofNullable(boardsResponse)
                    .orElse(new TrelloBoardDto[0]))
                    .stream()
                    /* .filter(b -> (b.getId() != null
                                    && b.getName() != null
                                    && b.getName().toLowerCase().contains("kodilla")))*/
                    .collect(Collectors.toList());
            //}

        } catch (RestClientException e){
            LOGGER.error(e.getMessage(),e);
            return new ArrayList<>();
        }
        //return new ArrayList<>();
    }

    public CreatedTrelloCard createCard(TrelloCardDto trelloCardDto){
        URI url = UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + "/cards")
        .queryParam("key", trelloConfig.getTrelloAppKey())
        .queryParam("token", trelloConfig.getTrelloToken())
        .queryParam("name",trelloCardDto.getName())
        .queryParam("desc",trelloCardDto.getDescription())
        .queryParam("pos",trelloCardDto.getPos())
        .queryParam("idList", trelloCardDto.getListId()).build().encode().toUri();

       return restTemplate.postForObject(url, null, CreatedTrelloCard.class);
    }


}
