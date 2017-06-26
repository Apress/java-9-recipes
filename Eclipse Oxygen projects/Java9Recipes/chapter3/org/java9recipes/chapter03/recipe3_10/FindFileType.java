
package org.java9recipes.chapter03.recipe3_10;

/**
 * Recipe 3-10
 * 
 * Determining If a File Name Ends with a Given String
 * 
 * @author juneau
 */
public class FindFileType {
    
    private static String fileName = "RegExExamples.java";
    
    public static void main(String[] args) {
        findFileType(fileName);
    }
    
    
    public static void findFileType(String filename){
        System.out.println("Finding file type of: " + filename);
        if(filename.endsWith(".txt")){
            System.out.println("Text file");
        } else if (filename.endsWith(".doc")){
            System.out.println("Document file");
        } else if (filename.endsWith(".xls")){
            System.out.println("Excel file");
        } else if (filename.endsWith(".java")){
            System.out.println("Java source file");
        } else {
            System.out.println("Other type of file");
        }
    }
    
    
}
