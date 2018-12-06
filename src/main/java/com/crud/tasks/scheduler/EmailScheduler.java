package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {

    @Autowired
    private SimpleEmailService simpleEmailService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AdminConfig adminConfig;

    private static final String SUBJECT ="Tasks: once a day email";

    private String message(long tasksNumber){
        String begin ="Currently on database you got ";
        String end   =" tasks";
        String end1  =" task";
        String message = begin + tasksNumber;
        if (tasksNumber == 1)
            return message + end1;
        return message + end;
    }

    @Scheduled(cron="0 0 10 * * *")
    //@Scheduled(fixedDelay = 10000)
    public void sendInformationEmail(){

        long size = taskRepository.count();
        //size = 1;
        simpleEmailService.send(new Mail(adminConfig.getAdminMail(),SUBJECT,
                message(size), null));

    }
}