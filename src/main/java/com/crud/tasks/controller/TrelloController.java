package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.facade.TrelloFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/trello")
public class TrelloController {

    //@Autowired
    //private TrelloService trelloService;
    //private TrelloClient trelloClient;

    @Autowired
    private TrelloFacade trelloFacade;

    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
    public  List<TrelloBoardDto> getTrelloBoards() {

        //System.out.println("getTrelloBoards");

        //List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();

        //trelloBoards.forEach(trelloBoardDto -> System.out.println(trelloBoardDto.getId() + " " + trelloBoardDto.getName()));

        // GET request
       return trelloFacade.fetchTrelloBoards();

//        trelloBoards.forEach(trelloBoardDto -> {
//
//            System.out.println(trelloBoardDto.getName() + " - " + trelloBoardDto.getId());
//
//            System.out.println("This board contains lists: ");
//
//            trelloBoardDto.getLists().forEach(trelloList ->
//                    System.out.println(trelloList.getName() + " - " + trelloList.getId() + " - " + trelloList.isClosed()));
//
//        });
//
//        System.out.println("getTrelloBoards2");
    }


    @RequestMapping(method = RequestMethod.POST, value = "createTrelloCard")
    public CreatedTrelloCardDto createTrelloCard(@RequestBody TrelloCardDto trelloCardDto){
        return trelloFacade.createCard(trelloCardDto);
    }
}