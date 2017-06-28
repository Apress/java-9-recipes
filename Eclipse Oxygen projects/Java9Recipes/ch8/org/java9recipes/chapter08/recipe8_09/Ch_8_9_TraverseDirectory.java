package org.java9recipes.chapter08.recipe8_09;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * User: Freddy
 * Traverse File Example
 */
public class Ch_8_9_TraverseDirectory {

    private void start() {
        FileVisitor<Path> myFileVisitor = new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println("Visited File: "+file.toString());
                return FileVisitResult.CONTINUE;
            }
        };

        FileSystem fileSystem = FileSystems.getDefault();
        Path directory= fileSystem.getPath(".");
        try {
            Files.walkFileTree(directory, myFileVisitor);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main (String args[]) {
        Ch_8_9_TraverseDirectory traverseDirectory = new Ch_8_9_TraverseDirectory();
        traverseDirectory.start();
    }




}
