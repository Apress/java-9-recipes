/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.java9recipes.chapter02.recipe2_04;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Juneau
 */
public class Recipe02_04 {

    public static void main(String[] args) {
        try {
            writeFile(new BufferedWriter(
                    new FileWriter("Easy TryWithResources")),
                    "This is easy in Java");
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    public static void writeFile(BufferedWriter writer, String text) {
        try (writer) {
            writer.write(text);
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }
}
