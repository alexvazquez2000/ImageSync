package com.alex.images;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.List;
import java.util.Map.Entry;

import com.alex.images.bean.FileInfo;
import com.alex.utils.Utils;

/**
 * Quick utility to parse the history file from LinkedIn
 * 
 * @author Alex Vazquez <vazqueza2000@gmail.com> 2025-04-24
 */
public class ParseLinkedIn {

	public static void main(String[] args) {
		String startDir = "C:\\Users\\alexv\\Downloads\\Basic_LinkedInDataExport_04-24-2025";
		Map<String, FileInfo> fileList = Utils.findFiles(startDir, false);

		for (Entry<String, FileInfo> entry : fileList.entrySet()) {
			FileInfo f = entry.getValue();
			String fName = f.getFullName();
			processFile(fName, "hobby");
		}

	}

	/**
	 * @param fName - full path to file
	 * @param token - case insensitive token to search in lines that also contain
	 *              the "key" attribute
	 */
	private static void processFile(String fName, String token) {
		try {
			List<String> lines = Files.readAllLines(Paths.get(fName));
			for (String line : lines) {
				if (line.toLowerCase().contains("key") || line.toLowerCase().contains(token)) {
					System.out.println(line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
