package server.model.initialisers;

import java.io.File;
import java.util.Scanner;

public class ConsoleDBInitialiser extends DatabaseInitialiser {

	private final String DATABASE_PATH = "./database";
	private final String USER_LIST = DATABASE_PATH + "/user_list.xml";
	private final String KPS_DATA = DATABASE_PATH + "/kps_data.xml";
	private final String EVENT_LOGS = DATABASE_PATH + "/event_logs.xml";

	@Override
	public void initialiseDatabase() {
		System.out.println("Database Status:");
		String status = this.checkFiles(DATABASE_PATH, USER_LIST, KPS_DATA, EVENT_LOGS);
		System.out.println(status);
		fixErrors(status);
	}

	private void fixErrors(String status) {
		if (status.contains("[ERROR]")) {
			System.out.println("Fixing errors...");
			Scanner sc = new Scanner(status);
			String line = sc.nextLine();
			// First line always directory
			if (line.contains("[ERROR]")) {
				if (createDatabase(DATABASE_PATH)) {
					System.out.println("[FIX] Created database at " + new File(DATABASE_PATH).getAbsolutePath());
				} else {
					System.out.println("[ERROR] Failed creating database");
				}
			}
			// Fix the files inside directory
			fixFile(sc.nextLine(), USER_LIST);
			fixFile(sc.nextLine(), KPS_DATA);
			fixFile(sc.nextLine(), EVENT_LOGS);
			sc.close();
		}
	}

	private void fixFile(String line, String path) {
		if (line.contains("[ERROR]")) {
			if (createFile(path)) {
				System.out.println("[FIX] Created file : " + new File(path).getName());
			} else {
				System.out.println("[ERROR] Failed creating file : " + new File(path).getName());
			}
		}
	}

}
