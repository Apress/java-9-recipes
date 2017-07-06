package org.java9recipes.chapter08.recipe8_13;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * User: freddy Working w/Compressed Files
 * @author anatolij kosorukov
 */
public class Ch_8_13_ZipExample {

	private static final String FILE_ZIP;
	static {
		FILE_ZIP = "file.zip";
	}
	private String dirName;

	public Ch_8_13_ZipExample() {
		dirName = "uncompressed/";

		this.initInput();

	}

	private void initInput() {
		FileSystem fileSystem = FileSystems.getDefault();
		Path dirPath = fileSystem.getPath(dirName);
		if (!Files.exists(dirPath, new LinkOption[] { LinkOption.NOFOLLOW_LINKS })) {
			try {
				Files.createDirectory(dirPath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		Path targetPath = fileSystem.getPath(FILE_ZIP);

		if (Files.exists(targetPath, new LinkOption[] { LinkOption.NOFOLLOW_LINKS })) {
			try {
				Files.delete(targetPath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (!Files.exists(targetPath, new LinkOption[] { LinkOption.NOFOLLOW_LINKS })) {
			String sourceFileName = "chapter08.zip";
			Path sourcePath = fileSystem.getPath(sourceFileName);

			if (!Files.exists(sourcePath, new LinkOption[] { LinkOption.NOFOLLOW_LINKS })) {
				return;
			}

			try {
				Files.copy(sourcePath, targetPath);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	public static void main(String[] args) {
		Ch_8_13_ZipExample example = new Ch_8_13_ZipExample();
		example.start();
	}

	public void start() {
		ZipFile file = null;
		FileSystem fileSystem = FileSystems.getDefault();
		try {
			file = new ZipFile(FILE_ZIP);

			Enumeration<? extends ZipEntry> entries = file.entries();

			String uncompressedDirectory = dirName;

			while (entries.hasMoreElements()) {
				ZipEntry entry = entries.nextElement();
				if (entry.isDirectory()) {
					System.out.println("Creating Directory:" + uncompressedDirectory + entry.getName());
					Files.createDirectories(fileSystem.getPath(uncompressedDirectory + entry.getName()));
				} else {
					InputStream is = file.getInputStream(entry);
					System.out.println("File :" + entry.getName());
					BufferedInputStream bis = new BufferedInputStream(is);

					String uncompressedFileName = uncompressedDirectory + entry.getName();
					Path uncompressedFilePath = fileSystem.getPath(uncompressedFileName);
					Files.createFile(uncompressedFilePath);
					try (FileOutputStream fileOutput = new FileOutputStream(uncompressedFileName)) {
						while (bis.available() > 0) {
							fileOutput.write(bis.read());
						}
					}
					System.out.println("Written :" + entry.getName());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				Files.walkFileTree(fileSystem.getPath(dirName), new SimpleFileVisitor<Path>() {

					@Override
					public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
						Files.delete(file);
						return FileVisitResult.CONTINUE;
					}

					@Override
					public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
						Files.delete(dir);
						return FileVisitResult.CONTINUE;
					}
				});
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
