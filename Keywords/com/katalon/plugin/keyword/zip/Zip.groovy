package com.katalon.plugin.keyword.zip
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.util.KeywordUtil

import net.lingala.zip4j.core.ZipFile
import net.lingala.zip4j.model.ZipParameters
import net.lingala.zip4j.util.Zip4jConstants


class Zip {

	@Keyword
	def zip(String path) {
		try {
			// code taken from https://windchill101.wordpress.com/2012/05/02/creating-a-zip-file-in-java-using-the-zip4j-library/
			Calendar calendar = Calendar.getInstance()
			Date time = calendar.getTime()
			long milliseconds = time.getTime()

			// Initiate ZipFile object with the path/name of the zip file.
			ZipFile zipFile = new ZipFile("${path}_${milliseconds}.zip")

			// Folder to add
			String folderToAdd = path

			// Initiate Zip Parameters which define various properties such
			// as compression method, etc.
			ZipParameters parameters = new ZipParameters()

			// set compression method to store compression
			parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE)

			// Set the compression level
			parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL)

			// Add folder to the zip file
			zipFile.addFolder(folderToAdd, parameters)
			
			KeywordUtil.logInfo("Output ${zipFile.getFile().getAbsolutePath()}")

		} catch (Exception e) {
			e.printStackTrace()
		}
	}
}