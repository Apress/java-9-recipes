package org.java9recipes.chapter08.recipe8_12;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Properties;

/**
 * User: freddy Reading and setting file Property
 * @author anatolij kosorukov
 */
public class Ch_8_12_PropertiesExample {

	private String filename;

	public Ch_8_12_PropertiesExample() {
		this.filename = "properties.conf";

		checkFile();
	}

	private void checkFile() {
		File file = new File(filename);
		
		if (!file.exists() || file.length() == 0) {
			file.delete();
			try {
				file.createNewFile();
				
				Properties properties = new Properties();
				properties.setProperty("ShouldWakeup", Boolean.TRUE.toString());
				properties.setProperty("StartCounter", Integer.valueOf(99).toString());

				LocalDate date = LocalDate.now();
				String dateFormationgString = date.format(DateTimeFormatter.ofPattern("MMM dd yy",Locale.US));
				properties.setProperty("DateFormatString", dateFormationgString);

				try {
					properties.store(new FileOutputStream(filename), "Properties Description");
				} catch (IOException e) {
					e.printStackTrace();
				}

				
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}
	}

	private void start() {
		// create property file (at least)
		File file = new File(filename);
		Properties properties = null;
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			properties = new Properties();

			properties.load(new FileInputStream("properties.conf"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		boolean shouldWakeUp = false;
		int startCounter = 100;
		String shouldWakeUpProperty = properties.getProperty("ShouldWakeup");
		shouldWakeUp = (shouldWakeUpProperty == null) ? false : Boolean.parseBoolean(shouldWakeUpProperty.trim());

		String startCounterProperty = properties.getProperty("StartCounter");
		try {
			startCounter = Integer.parseInt(startCounterProperty);
		} catch (Exception e) {
			System.out.println("Couldn't read startCounter, defaulting to " + startCounter);
		}
		String dateFormatStringProperty = properties.getProperty("DateFormatString", "MMM dd yy");

		System.out.println("Should Wake up? " + shouldWakeUp);
		System.out.println("Start Counter: " + startCounter);
		System.out.println("Date Format String:" + dateFormatStringProperty);

		// setting property
		properties.setProperty("StartCounter", "250");
		try {
			properties.store(new FileOutputStream("properties.conf"), "Properties Description");
		} catch (IOException e) {
			e.printStackTrace();
		}
		properties.list(System.out);
	}

	public static void main(String[] args) {
		Ch_8_12_PropertiesExample propertiesExample = new Ch_8_12_PropertiesExample();
		propertiesExample.start();
	}

}
