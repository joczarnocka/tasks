package com.crud.tasks;

import com.crud.tasks.domain.TaskDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class TasksApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {

		/*TaskDto taskDto = new TaskDto((long)1,"Test title","Test title 1");
		Long id = taskDto.getId();
		String title = taskDto.getTitle();
		String content = taskDto.getContent();

		System.out.println("id:" + id + ", title: " + title +", content:" + content);
		*/

		SpringApplication.run(TasksApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
		return application.sources(TasksApplication.class);
	}

}
