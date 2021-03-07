package demo.project.chatbot.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import demo.project.chatbot.utils.CalendarUtils;

public class Utilities {
	private static final Log log = LogFactory.getLog(Utilities.class);

	/**
	 * Excel sometimes adds mysterious formatting to CSV files. This function
	 * tries to clean it up.
	 *
	 * @param line
	 *            line from AIMLIF file
	 * @return reformatted line
	 */
	public static String fixCSV(String line) {
		while (line.endsWith(";"))
			line = line.substring(0, line.length() - 1);
		if (line.startsWith("\""))
			line = line.substring(1, line.length());
		if (line.endsWith("\""))
			line = line.substring(0, line.length() - 1);
		line = line.replaceAll("\"\"", "\"");
		return line;
	}

	public static String tagTrim(String xmlExpression, String tagName) {
		String stag = "<" + tagName + ">";
		String etag = "</" + tagName + ">";
		if (xmlExpression.length() >= (stag + etag).length()) {
			xmlExpression = xmlExpression.substring(stag.length());
			xmlExpression = xmlExpression.substring(0, xmlExpression.length() - etag.length());
		}
		return xmlExpression;
	}

	public static HashSet<String> stringSet(String... strings) {
		HashSet<String> set = new HashSet<String>();
		for (String s : strings)
			set.add(s);
		return set;
	}

	public static String getFileFromInputStream(InputStream in) {
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine;
		// Read File Line By Line
		String contents = "";
		try {
			while ((strLine = br.readLine()) != null) {
				if (strLine.length() == 0)
					contents += "\n";
				else
					contents += strLine + "\n";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return contents.trim();
	}

	public static String getFile(String filename) {
		String contents = "";
		try {
			File file = new File(filename);
			if (file.exists()) {
				// log.info("Found file "+filename);
				FileInputStream fstream = new FileInputStream(filename);
				// Get the object
				contents = getFileFromInputStream(fstream);
				fstream.close();
			}
		} catch (Exception e) {// Catch exception if any
			log.error("Cannot get file '" + filename + "': " + e, e);
		}
		// log.info("getFile: "+contents);
		return contents;
	}

	public static String getCopyrightFromInputStream(InputStream in) {
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine;
		// Read File Line By Line
		String copyright = "";
		try {
			while ((strLine = br.readLine()) != null) {
				if (strLine.length() == 0)
					copyright += "\n";
				else
					copyright += "<!-- " + strLine + " -->\n";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return copyright;
	}

	public static String getCopyright(Bot bot, String AIMLFilename) {
		String copyright = "";
		String year = CalendarUtils.year();
		String date = CalendarUtils.date();
		try {
			copyright = getFile(MagicStrings.config_path + "/copyright.txt");
			String[] splitCopyright = copyright.split("\n");
			copyright = "";
			for (int i = 0; i < splitCopyright.length; i++) {
				copyright += "<!-- " + splitCopyright[i] + " -->\n";
			}
			copyright = copyright.replace("[url]", bot.properties.get("url"));
			copyright = copyright.replace("[date]", date);
			copyright = copyright.replace("[YYYY]", year);
			copyright = copyright.replace("[version]", bot.properties.get("version"));
			copyright = copyright.replace("[botname]", bot.name.toUpperCase());
			copyright = copyright.replace("[filename]", AIMLFilename);
			copyright = copyright.replace("[botmaster]", bot.properties.get("botmaster"));
			copyright = copyright.replace("[organization]", bot.properties.get("organization"));
		} catch (Exception e) {// Catch exception if any
			log.error("Cannot get copyright from '" + AIMLFilename + "': " + e, e);
		}
		// log.info("Copyright: "+copyright);
		return copyright;
	}

	public static String getPannousAPIKey() {
		String apiKey = getFile(MagicStrings.config_path + "/pannous-apikey.txt");
		if (apiKey.equals(""))
			apiKey = MagicStrings.pannous_api_key;
		return apiKey;
	}

	public static String getPannousLogin() {
		String login = getFile(MagicStrings.config_path + "/pannous-login.txt");
		if (login.equals(""))
			login = MagicStrings.pannous_login;
		return login;
	}

	/**
	 * Returns if a character is one of Chinese-Japanese-Korean characters.
	 *
	 * @param c
	 *            the character to be tested
	 * @return true if CJK, false otherwise
	 */
	public static boolean isCharCJK(final char c) {
		if ((Character.UnicodeBlock.of(c) == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS)
				|| (Character.UnicodeBlock.of(c) == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A)
				|| (Character.UnicodeBlock.of(c) == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B)
				|| (Character.UnicodeBlock.of(c) == Character.UnicodeBlock.CJK_COMPATIBILITY_FORMS)
				|| (Character.UnicodeBlock.of(c) == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS)
				|| (Character.UnicodeBlock.of(c) == Character.UnicodeBlock.CJK_RADICALS_SUPPLEMENT)
				|| (Character.UnicodeBlock.of(c) == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION)
				|| (Character.UnicodeBlock.of(c) == Character.UnicodeBlock.ENCLOSED_CJK_LETTERS_AND_MONTHS)) {
			return true;
		}
		return false;
	}

}