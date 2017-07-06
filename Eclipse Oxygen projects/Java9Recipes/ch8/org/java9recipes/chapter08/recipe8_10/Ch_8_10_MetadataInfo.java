package org.java9recipes.chapter08.recipe8_10;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.DosFileAttributeView;
import java.nio.file.attribute.DosFileAttributes;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.util.Set;
import java.util.function.Consumer;

/**
 * User: Freddy Metadata Example
 * 
 * @author anatolij kosorukov
 */
public class Ch_8_10_MetadataInfo {

	private final Path path;
	private final  Consumer<Path> action;
	
	public Ch_8_10_MetadataInfo() {
		
		path = FileSystems.getDefault().getPath("./file2.log");
		
		String property = System.getProperty("os.name");
		
		if (property.startsWith("Windows")) {
			action = (path) -> {
				DosFileAttributeView view = Files.getFileAttributeView(path, DosFileAttributeView.class);
				System.out.println("\nDOS File Attributes\n------------------------------------\n");
				DosFileAttributes readAttributes;
				try {
					readAttributes = view.readAttributes();

					System.out.println("Archive  :" + readAttributes.isArchive());
					System.out.println("Hidden   :" + readAttributes.isHidden());
					System.out.println("Read-only:" + readAttributes.isReadOnly());
					System.out.println("System   :" + readAttributes.isSystem());
					
					view.setHidden(false);			
				} catch (IOException e) {
					e.printStackTrace();
				}
			};
		} else {
			action = (Void) -> {
				PosixFileAttributeView view = Files.getFileAttributeView(path, PosixFileAttributeView.class);
				try {
					PosixFileAttributes posixAttributes = view.readAttributes();
					System.out.println("Is other  :" + posixAttributes.isOther());
					Set<PosixFilePermission> permissions = posixAttributes.permissions();
					permissions.stream().forEach(permition->{
						System.out.println(permition.name());
					});
				} catch (IOException e) {
					e.printStackTrace();
				}
			};
		}
	}
	
	private void start() {

		

		if (Files.exists(path, new LinkOption[] { LinkOption.NOFOLLOW_LINKS })) {
			try {
				Files.delete(path);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			Files.createFile(path, new FileAttribute<?>[0]);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {
			// General file attributes, supported by all Java systems
			System.out.println("File Size:" + Files.size(path));
			System.out.println("Is Directory:" + Files.isDirectory(path));
			System.out.println("Is Regular File:" + Files.isRegularFile(path));
			System.out.println("Is Symbolic Link:" + Files.isSymbolicLink(path));
			System.out.println("Is Hidden:" + Files.isHidden(path));
			System.out.println("Last Modified Time:" + Files.getLastModifiedTime(path));
			System.out.println("Owner:" + Files.getOwner(path));

			// Specific attribute views.
			action.accept(path);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				Files.delete(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		Ch_8_10_MetadataInfo info = new Ch_8_10_MetadataInfo();
		info.start();
	}

}
