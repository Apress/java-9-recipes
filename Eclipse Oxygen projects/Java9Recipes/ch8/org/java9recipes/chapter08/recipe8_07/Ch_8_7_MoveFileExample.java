/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.java9recipes.chapter08.recipe8_07;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;

/**
 *
 * @author Juneau
 * @author anatolij kosorukov
 */
public class Ch_8_7_MoveFileExample {
	public static void main(String[] args) {
		Ch_8_7_MoveFileExample exampleCh87 = new Ch_8_7_MoveFileExample();
		exampleCh87.moveFile();
	}

	private void moveFile() {
		FileSystem fileSystem = FileSystems.getDefault();
		Path sourcePath = fileSystem.getPath("file.log");
		Path targetPath = fileSystem.getPath("file2.log");

		if (Files.exists(sourcePath, new LinkOption[] { LinkOption.NOFOLLOW_LINKS })) {
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
		if (Files.exists(targetPath, new LinkOption[] { LinkOption.NOFOLLOW_LINKS })) {
			try {
				Files.delete(targetPath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		System.out.println("Move from " + sourcePath.toAbsolutePath().toString() + " to "
				+ targetPath.toAbsolutePath().toString());
		try {
			Files.move(sourcePath, targetPath);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			try {

				Files.delete(targetPath);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}
}
