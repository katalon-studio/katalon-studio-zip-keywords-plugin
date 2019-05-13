package com.katalon.plugin.keyword.zip
import java.util.regex.Pattern

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.util.KeywordUtil

import net.lingala.zip4j.core.*;
import net.lingala.zip4j.model.*;
import net.lingala.zip4j.util.*;

class ZipKeywords {

	/**
	 * Add folder to zip file
	 *
	 * @param path The path of the folder needs to be zipped
	 * @return The full path of the zipped folder
	 */
	@Keyword
	public String zipFolder(String path) {
		ZipFile zipFile = createZipFile(path)
		ZipParameters parameters = initZipParameter()
		zipFile.addFolder(path, parameters)
		return zipFile.getFile().getAbsolutePath()
	}

	/**
	 * Add file to zip file
	 *
	 * @param file The full path of the file needs to be zipped
	 * @return The full path of the zipped file
	 */
	@Keyword
	public String zipFile(String file) {
		String path = file.split(Pattern.quote("."))[0]
		ZipFile zipFile = createZipFile(path)
		ZipParameters parameters = initZipParameter()
		zipFile.addFile(new File(file), parameters)
		return zipFile.getFile().getAbsolutePath()
	}


	/**
	 * Unzip an file
	 *
	 * @param file The full path of the file needs to be unzipped
	 * @param password The password to unzip the file if any. If the zipped file is not encrypted, the password can be null
	 * @return The full path of the unzipped file
	 */
	@Keyword
	public String unZip(String file, String password) {
		String unZipFilePath = file.split(Pattern.quote("."))[0]
		ZipFile zipFile = new ZipFile(file)
		if (zipFile.isEncrypted()) {
			zipFile.setPassword(password)
		}
		long milliseconds = getTime()
		unZipFilePath = unZipFilePath + "_" + milliseconds
		zipFile.extractAll(unZipFilePath)
		return unZipFilePath
	}

	/**
	 * Initiate Zip Parameters
	 */
	@Keyword
	public ZipParameters initZipParameter() {
		ZipParameters parameters = new ZipParameters()
		parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE)
		parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL)
		return parameters
	}

	/**
	 * Initiate a ZipFile object
	 * @param path The path to create the zip file
	 * @return a ZipFile object
	 */
	@Keyword
	public ZipFile createZipFile(String path) {
		long milliseconds = getTime()
		ZipFile zipFile = new ZipFile("${path}_${milliseconds}.zip")
		return zipFile
	}

	private long getTime() {
		Calendar calendar = Calendar.getInstance()
		Date time = calendar.getTime()
		long milliseconds = time.getTime()
		return milliseconds
	}
}