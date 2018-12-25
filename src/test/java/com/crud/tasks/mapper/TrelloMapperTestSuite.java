package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TrelloMapperTestSuite {

    @Test
    public void testMapToBoards(){
        //Given
        TrelloMapper trelloMapper = new TrelloMapper();
        List<TrelloBoardDto> trelloBoardsDto = new ArrayList<>();
        trelloBoardsDto.add(new TrelloBoardDto("1","B1",new ArrayList<TrelloListDto>()));
        trelloBoardsDto.add(new TrelloBoardDto("2","B2",new ArrayList<TrelloListDto>()));
        trelloBoardsDto.add(new TrelloBoardDto("3","B3",new ArrayList<TrelloListDto>()));
        //When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardsDto);
        //Then
        assertEquals(3, trelloBoards.size());
    }

    @Test
    public void testMapToBoardsDto(){
        //Given
        TrelloMapper trelloMapper = new TrelloMapper();
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("1","B1",new ArrayList<TrelloList>()));
        trelloBoards.add(new TrelloBoard("2","B2",new ArrayList<TrelloList>()));
        trelloBoards.add(new TrelloBoard("3","B3",new ArrayList<TrelloList>()));
        //When
        List<TrelloBoardDto> trelloBoardsDto = trelloMapper.mapToBoardsDto(trelloBoards);
        //Then
        assertEquals(3, trelloBoards.size());
    }


    @Test
    public void testMapToList(){
        //Given
        TrelloMapper trelloMapper = new TrelloMapper();
        List<TrelloListDto> trelloListDto = new ArrayList<>();
        trelloListDto.add(new TrelloListDto("1","L1",true));
        trelloListDto.add(new TrelloListDto("2","L2",false));
        trelloListDto.add(new TrelloListDto("3","L3",false));
        trelloListDto.add(new TrelloListDto("4","L4",true));
        //When
        List<TrelloList> trelloList = trelloMapper.mapToList(trelloListDto);
        //Then
        assertEquals(4, trelloList.size());
    }

    @Test
    public void testmapToListDto(){
        //Given
        TrelloMapper trelloMapper = new TrelloMapper();
        List<TrelloList> trelloList = new ArrayList<>();
        trelloList.add(new TrelloList("1","L1",true));
        trelloList.add(new TrelloList("2","L2",false));
        trelloList.add(new TrelloList("3","L3",false));
        trelloList.add(new TrelloList("4","L4",true));
        //When
        List<TrelloListDto> trelloListDto = trelloMapper.mapToListDto(trelloList);
        //Then
        assertEquals(4, trelloList.size());
    }

    @Test
    public void testMapToCard(){
    //Given
        TrelloMapper trelloMapper = new TrelloMapper();
        TrelloCardDto trelloCardDto = new TrelloCardDto("C1","C1 description","POS1", "1");
    //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
    //Then
        assertEquals("C1", trelloCard.getName());
        assertEquals("C1 description", trelloCard.getDescription());
        assertEquals("POS1", trelloCard.getPos());
        assertEquals("1", trelloCard.getListId());

    }

    @Test
    public void testMapToCardDto(){
        //Given
        TrelloMapper trelloMapper = new TrelloMapper();
        TrelloCard trelloCard = new TrelloCard("C1","C1 description","POS1","1");
        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        //Then
        assertEquals("C1", trelloCardDto.getName());
        assertEquals("C1 description", trelloCardDto.getDescription());
        assertEquals("POS1", trelloCardDto.getPos());
        assertEquals("1", trelloCardDto.getListId());
    }

}
