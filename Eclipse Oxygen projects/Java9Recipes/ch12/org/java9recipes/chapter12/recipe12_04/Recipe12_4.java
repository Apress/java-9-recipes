/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.java9recipes.chapter12.recipe12_04;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Recipe 12-4: 
 * @author Juneau
 * @author anatolij kosorukov
 */
public class Recipe12_4 {

    public static void main(String[] args) {
        filterTags();
        localeMatching();
    }
    
    public static void filterTags(){
        List<Locale.LanguageRange> list1 = Locale.LanguageRange.parse("ja-JP, en-US");
        list1.stream().forEach((range) -> {
            System.out.println("Range:" + range.getRange());
        });
        ArrayList<String> localeList = new ArrayList<>();
        localeList.add("en-US");
        localeList.add("en-JP");

        List<String> tags1 = Locale.filterTags(list1, localeList);
        System.out.println("The following is the filtered list of Locales:");
        tags1.stream().forEach((tag) -> {
            System.out.println(tag);
        });
    }
    
    public static void localeMatching(){
        String localeTags = Locale.ENGLISH.toLanguageTag() + "," +
                            Locale.CANADA.toLanguageTag();
        List<Locale.LanguageRange> list1 = Locale.LanguageRange.parse(localeTags);
        list1.stream().forEach((range) -> {
            System.out.println("Range:" + range.getRange());
        });
        ArrayList<Locale> localeList = new ArrayList<>();
        localeList.add(new Locale("en"));
        localeList.add(new Locale("en-JP"));

        List<Locale> tags1 = Locale.filter(list1, localeList);
        System.out.println("The following is the matching list of Locales:");
        tags1.stream().forEach((tag) -> {
            System.out.println(tag);
        });
    }
}
