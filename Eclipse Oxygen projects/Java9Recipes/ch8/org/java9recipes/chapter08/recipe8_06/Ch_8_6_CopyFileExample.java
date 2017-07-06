package org.java9recipes.chapter08.recipe8_06;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;

/**
 * Created by IntelliJ IDEA.
 * User: Freddy
 * Date: 8/13/11
 * Time: 8:53 AM
 * Copy a file
 * @author anatolij kosorukov
 */
public class Ch_8_6_CopyFileExample {

    public static void main (String[] args) {
        Ch_8_6_CopyFileExample exampleCh86 = new Ch_8_6_CopyFileExample();
        exampleCh86.copyFile();
    }

    private void copyFile() {
        FileSystem fileSystem = FileSystems.getDefault();
        System.out.println("FileSystem:" + fileSystem);
        
        
        Path sourcePath = fileSystem.getPath("file.log");
        Path targetPath = fileSystem.getPath("file2.log");
        
        if(Files.exists(sourcePath, new LinkOption[] {LinkOption.NOFOLLOW_LINKS})) {
        	try {
				Files.delete(sourcePath);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        try {
			Files.createFile(sourcePath, new FileAttribute<?>[0]);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        
        
        if(Files.exists(targetPath, new LinkOption[] {LinkOption.NOFOLLOW_LINKS})) {
        	try {
				Files.delete(targetPath);
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
 
        System.out.println("Copy from "+sourcePath.toAbsolutePath().toString()+" to "+targetPath.toAbsolutePath().toString());
        try {
            Files.copy(sourcePath, targetPath);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

        	try {
				Files.delete(sourcePath);
				Files.delete(targetPath);
        	} catch (IOException e) {
				e.printStackTrace();
			}
        	
        }

    }
}
