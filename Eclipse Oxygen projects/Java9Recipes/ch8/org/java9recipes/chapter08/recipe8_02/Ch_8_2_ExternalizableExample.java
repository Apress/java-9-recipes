package org.java9recipes.chapter08.recipe8_02;

import java.awt.*;
import java.io.*;

/**
 * User: Freddy Externalizable Example
 * 
 * @author anatolij kosorukov
 */
public class Ch_8_2_ExternalizableExample {
	public static void main(String[] args) {
		Ch_8_2_ExternalizableExample example = new Ch_8_2_ExternalizableExample();
		example.start();
	}

	private void start() {
		ExternalizableProgramSettings settings = new ExternalizableProgramSettings(new Point(10, 10),
				new Dimension(300, 200), Color.blue, "The title of the application");
		saveSettings(settings, "settingsExternalizable.bin");
		ExternalizableProgramSettings loadedSettings = loadSettings("settingsExternalizable.bin");
		System.out.println("Are settings are equal? :" + loadedSettings.equals(settings));

	}

	private void saveSettings(ExternalizableProgramSettings settings, String filename) {
		try (FileOutputStream fos = new FileOutputStream(filename)) {

			try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
				oos.writeObject(settings);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private ExternalizableProgramSettings loadSettings(String filename) {
		try (FileInputStream fis = new FileInputStream(filename); ObjectInputStream ois = new ObjectInputStream(fis);) {
			return (ExternalizableProgramSettings) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;

	}

}
