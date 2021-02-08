package com.example.task4itunes.logging;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogToConsole {

    // This class serves to log the messages to the console, this is to remove the responsibility from the repos.
         public void log(String message){
            System.out.println(message);
        }

}
