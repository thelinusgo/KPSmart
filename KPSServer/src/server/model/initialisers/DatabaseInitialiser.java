package server.model.initialisers;

import java.io.File;
import java.io.IOException;

/**
 * This class provides methods for verifying the files needed for the directory.
 * 
 * @author Chris
 *
 */
public abstract class DatabaseInitialiser {

	/**
	 * Initialises the database
	 */
	public abstract void initialiseDatabase();

	protected String checkFiles(String databasePath, String... dbFiles) {
		StringBuilder reportBuilder = new StringBuilder();
		verifyDatabase(databasePath, reportBuilder);
		for (String file : dbFiles) {
			verifyDatabaseFile(file, reportBuilder);
		}
		return reportBuilder.toString();
	}

	protected boolean createDatabase(String databasePath) {
		File dir = new File(databasePath);
		return dir.mkdir();
	}

	protected boolean createFile(String file) {
		File f = new File(file);
		try {
			return f.createNewFile();
		} catch (IOException e) {
			return false;
		}
	}

	// Helper Methods

	private void verifyDatabaseFile(String file, StringBuilder reportBuilder) {
		File dbFile = new File(file);
		if (!dbFile.exists()) {
			error(reportBuilder, dbFile.getName());
		} else {
			success(reportBuilder, dbFile.getName());
		}
	}

	private void verifyDatabase(String databasePath, StringBuilder reportBuilder) {
		File dbDir = new File(databasePath);
		if (!dbDir.exists() || !dbDir.isDirectory()) {
			error(reportBuilder, dbDir.getName());
		} else {
			success(reportBuilder, dbDir.getName());
		}
	}

	private void error(StringBuilder reportBuilder, String path) {
		reportBuilder.append("[ERROR]" + path + "\n");
	}

	private void success(StringBuilder reportBuilder, String path) {
		reportBuilder.append("[OK]" + path + "\n");
	}
}
