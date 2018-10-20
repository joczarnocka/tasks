package com.crud.tasks;

import com.crud.tasks.domain.TaskDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TasksApplication {

	public static void main(String[] args) {

		/*TaskDto taskDto = new TaskDto((long)1,"Test title","Test title 1");
		Long id = taskDto.getId();
		String title = taskDto.getTitle();
		String content = taskDto.getContent();

		System.out.println("id:" + id + ", title: " + title +", content:" + content);
		*/

		SpringApplication.run(TasksApplication.class, args);
	}
}