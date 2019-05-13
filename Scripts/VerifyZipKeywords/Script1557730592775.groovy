import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.util.KeywordUtil

String path = RunConfiguration.getProjectDir() + "/Data Files/"
//zip folder
KeywordUtil.logInfo(CustomKeywords.'com.katalon.plugin.keyword.zip.ZipKeywords.zipFolder'(path + "temp"))

//zip file
KeywordUtil.logInfo(CustomKeywords.'com.katalon.plugin.keyword.zip.ZipKeywords.zipFile'(path + "test.jar"))

//unzip encrypted file
KeywordUtil.logInfo(CustomKeywords.'com.katalon.plugin.keyword.zip.ZipKeywords.unZip'(path + "test.zip", "1234"))

//unzip unencrypted file
KeywordUtil.logInfo(CustomKeywords.'com.katalon.plugin.keyword.zip.ZipKeywords.unZip'(path + "temp.zip",null))

