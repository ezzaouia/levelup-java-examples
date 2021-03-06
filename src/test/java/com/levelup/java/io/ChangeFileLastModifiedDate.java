package com.levelup.java.io;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import com.google.common.io.Files;

/**
 * This java example will demonstrate changing a files last modified date.
 * 
 * @author Justin Musgrove
 * @see <a href=
 *      'http://www.leveluplunch.com/java/examples/change-file-last-modified-date/'>Chang
 *      e files last modified date</a>
 * 
 */
public class ChangeFileLastModifiedDate {

	private static final Logger logger = Logger
			.getLogger(ChangeFileLastModifiedDate.class);

	private static final String OUTPUT_FILE_NAME = "output/ChangeFileLastModifiedDate.txt";

	@Before
	public void setUp() throws IOException {

		// create new empty file
		File file = new File(OUTPUT_FILE_NAME);
		file.createNewFile();
	}

	@Test
	public void change_last_modified_date_java() {

		SimpleDateFormat dateFormatter = new SimpleDateFormat(
				"MM/dd/yyyy HH:mm:ss");

		File file = new File(OUTPUT_FILE_NAME);

		// display current lastmodified
		logger.info(dateFormatter.format(file.lastModified()));

		// set current datetime to lastmodified
		DateTime today = new DateTime(file.lastModified());

		// minus 5 days
		file.setLastModified(today.minusDays(5).getMillis());

		// display latest lastmodified
		logger.info(dateFormatter.format(file.lastModified()));

	}

	@Test
	public void change_last_modified_date_java7()
			throws URISyntaxException, IOException {

		Path path = Paths.get(OUTPUT_FILE_NAME);

		DateTime today = new DateTime(new Date());

		FileTime fileTime = FileTime.from(today.minusDays(5).getMillis(),
				TimeUnit.MILLISECONDS);

		java.nio.file.Files.setLastModifiedTime(path, fileTime);
	}

	/**
	 * Files.touch creates an empty file or updates the last updated timestamp
	 * on the same as the unix command of the same name.
	 * 
	 * @throws IOException
	 */
	@Test
	public void touch_file_guava() throws IOException {

		SimpleDateFormat dateFormatter = new SimpleDateFormat(
				"MM/dd/yyyy HH:mm:ss");
		DateTime today = new DateTime();

		File file = new File(OUTPUT_FILE_NAME);

		// set last modified to 5 days ago
		file.setLastModified(today.minusDays(5).getMillis());

		// display latest lastmodified
		logger.info(dateFormatter.format(file.lastModified()));

		Files.touch(file);

		// display latest lastmodified
		logger.info(dateFormatter.format(file.lastModified()));
	}

	/**
	 * FileUtils.touch Implements the same behaviour as the "touch" utility on
	 * Unix. It creates a new file with size 0 or, if the file exists already,
	 * it is opened and closed without modifying it, but updating the file date
	 * and time.
	 * 
	 * 
	 * @throws IOException
	 */
	@Test
	public void touch_file_apache() throws IOException {

		SimpleDateFormat dateFormatter = new SimpleDateFormat(
				"MM/dd/yyyy HH:mm:ss");
		DateTime today = new DateTime();

		File file = new File(OUTPUT_FILE_NAME);

		// set last modified to 5 days ago
		file.setLastModified(today.minusDays(5).getMillis());

		// display latest lastmodified
		logger.info(dateFormatter.format(file.lastModified()));

		FileUtils.touch(file);

		// display latest lastmodified
		logger.info(dateFormatter.format(file.lastModified()));
	}

}
