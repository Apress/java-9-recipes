package org.java9recipes.chapter08.recipe8_08;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;
import java.util.function.Consumer;

/**
 * User: Freddy Create Directory
 * @author anatolij kosorukov
 */
public class Ch_8_8_CreateDirectory {

	Consumer<Path> action;
	public Ch_8_8_CreateDirectory() {
		
		String property = System.getProperty("os.name");
		if (property.startsWith("Windows")) {
			action =(directory) -> {
				try {
					Path newDir = Files.createDirectory(directory);
					System.out.printf("%s is created",newDir);
				} catch (IOException e) {
					e.printStackTrace();
				}
	        };
		} else {
			action =(directory) -> {
				try {
					Set<PosixFilePermission> perms = PosixFilePermissions.fromString("rwxr-x---");
					FileAttribute<Set<PosixFilePermission>> attr = PosixFilePermissions.asFileAttribute(perms);
					Files.createDirectory(directory, attr);

				} catch (IOException e) {
					e.printStackTrace();
				}
	        };
		}
	}
	public void start() {
		FileSystem fileSystem = FileSystems.getDefault();
		Path directory = fileSystem.getPath("./newDirectory");
		if(Files.exists(directory, new LinkOption[] {LinkOption.NOFOLLOW_LINKS})) {
			try {
				Files.delete(directory);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		action.accept(directory);
		
			try {
				Files.delete(directory);
			} catch (IOException e) {
				e.printStackTrace();
			}
		
	}

	

	public static void main(String[] args) {
		final Ch_8_8_CreateDirectory createDirectory = new Ch_8_8_CreateDirectory();

		createDirectory.start();

	}

}
