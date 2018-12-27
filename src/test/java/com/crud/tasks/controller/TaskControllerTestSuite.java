package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RequestBody;

import static org.hamcrest.Matchers.is;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTestSuite {

   @Autowired
   private MockMvc mockMvc;

   @MockBean
   private DbService dbService;

   @MockBean
   private TaskMapper taskMapper;

   @MockBean
   private TaskController taskController;

   @Test
   public void testGetTasksEmpty() throws Exception {
   //Given
       List<TaskDto> taskDtoLists = new ArrayList<>();

       when(taskController.getTasks()).thenReturn(taskDtoLists);

       //When & Then
       mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().is(200))
               .andExpect(jsonPath("$", hasSize(0)));
   }


    @Test
    public void testGetTasksMoreThan0() throws Exception {
        //Given
        List<TaskDto> taskDtoList = new ArrayList<>();
        taskDtoList.add(new TaskDto(1L, "Title", "Description"));

        when(taskController.getTasks()).thenReturn(taskDtoList);

        //When & Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                //Task Dto fields
                .andExpect(jsonPath("$",hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title",is("Title")))
                .andExpect(jsonPath("$[0].content",is("Description")))
   ;
    }

    @Test
    public void testGetTask1() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L,"Task","Task description");

        when(taskController.getTask(any())).thenReturn(taskDto);

        //When & Then
        mockMvc.perform(get("/v1/task/getTask/?id={id}", 1L).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Task")))
                .andExpect(jsonPath("$.content", is("Task description")));

    }


    @Test
    public void testDeleteTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L,"Task","Task description");


        //When & Then
        mockMvc.perform(delete("/v1/task/deleteTask/?id={id}", 1L).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                ;

    }


    @Test
    public void testCreateTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L,"Task","Task description");

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When & Then
        mockMvc.perform(post("/v1/task/createTask/").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200));
        ;

    }


    @Test
    public void testUpdateTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L,"Task","Task description");

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        when(taskController.updateTask(any())).thenReturn(taskDto);

        //When & Then
        mockMvc.perform(put("/v1/task/updateTask/").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Task")))
                .andExpect(jsonPath("$.content", is("Task description"))) ;
        ;

    }

}
