/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.java9recipes.chapter02.recipe2_03;

import java.time.Duration;
import java.util.Optional;

/**
 * Recipe 2-3:  Easily Managing OS Processes
 * @author Juneau
 */
public class Recipe02_03 {
    public static void main (String[] args){
        listProcesses();
        detailListProcesses();
        detailListProcessUsers();
    }
    
    public static void listProcesses(){
        ProcessHandle.allProcesses()
                .forEach(System.out::println);
    }
    
    public static void detailListProcesses(){
        ProcessHandle.allProcesses()
                .forEach(h->System.out.println(formattedProcess(h)));
    }
    
    public static void detailListProcessUsers(){
        ProcessHandle.allProcesses()
                .forEach(h->System.out.println(listOsUser(h)));
    }

    public static ProcessHandle.Info obtainInfo(ProcessHandle handle){
        return handle.info();
    }
    
    public static String formattedProcess(ProcessHandle handle){
        long pid = handle.pid();
        boolean alive = handle.isAlive();
        Optional<Duration> cpuDuration = handle.info().totalCpuDuration();
        Optional<String> handleName = handle.info().command();
        return pid + " " + alive + " " + handleName + ":"+ cpuDuration;
     }
    
    public static String listOsUser(ProcessHandle handle){
        ProcessHandle.Info procInfo = handle.info();
        return handle.pid() + ": " +procInfo.user();
    }
}
