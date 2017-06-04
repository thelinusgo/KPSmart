package server.handlers.initialisers;

import java.io.File;
import java.io.IOException;

/**
 * Creates the database directory if it doesn't exist
 * 
 * @author Chris
 *
 */
public class DatabaseInitialiser {

	// Path
	private final String DATABASE_PATH = "./database";
	// Files inside the database
	private final String USER_LIST_FILE = DATABASE_PATH + "/user_list.xml";
	private final String LOGS_FILE = DATABASE_PATH + "/logs.xml";

	public void initialiseDatabase() {
		System.out.println("Initialising Database...");
		createDatabase();
		try {
			createDatabaseFiles();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void createDatabase() {
		File dbPath = new File(DATABASE_PATH);
		if (!dbPath.exists() || !dbPath.isDirectory()) {
			System.out.println("Database [ERROR]");
			System.out.println("Creating Database at " + dbPath.getAbsolutePath() + "...");
			boolean successful = dbPath.mkdir();
			if (successful) {
				System.out.println("Successfully created database directory.");
			} else {
				System.out.println("Error: Database cannot be created.");
			}
		} else {
			System.out.println("Database [OK]");
		}
	}

	private void createDatabaseFiles() throws IOException {
		File userList = new File(USER_LIST_FILE);
		File logsFile = new File(LOGS_FILE);
		createFile(userList);
		createFile(logsFile);
	}

	private void createFile(File file) throws IOException {
		System.out.println("Checking if " + file.getName() + " exists...");
		if (!file.exists()) {
			System.out.println(file.getName() + "[ERROR]");
			System.out.println("Creating " + file.getName() + "...");
			if (file.createNewFile()) {
				System.out.println("Successfully created " + file.getName());
			} else {
				System.out.println("Error: " + file.getName() + " couldn't be created");
			}
		} else {
			System.out.println(file.getName() + " [OK]");
		}
	}
}
