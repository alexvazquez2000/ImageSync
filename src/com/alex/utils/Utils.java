package com.alex.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import com.alex.images.bean.FileInfo;

/**
 * @author Alex Vazquez <vazqueza2000@gmail.com>
 */
public class Utils {

	private Utils() {
		// only static methods
	}

	/**
	 * @param startDir
	 * @param includeSha256
	 * @return The returned hashMap key is the SHA256, but if includeSha256==false
	 *         then key is the absolute path
	 */
	public static Map<String, FileInfo> findFiles(String startDir, boolean includeSha256) {
		HashMap<String, FileInfo> filesFound = new HashMap<>();
		File dir = new File(startDir);
		if (dir.isDirectory()) {
			File[] files = dir.listFiles();
			for (File f : files) {
				if (f.isFile()) {
					FileInfo fileInfo = new FileInfo(f.getAbsolutePath(), f.getName(), f.length(), f.lastModified());
					if (includeSha256) {
						fileInfo.setSha256(getFileSHA256(f.getAbsolutePath()));
						filesFound.put(fileInfo.getSha256(), fileInfo);
					} else {
						filesFound.put(f.getAbsolutePath(), fileInfo);
					}

				} else if (f.isDirectory()) {
					filesFound.putAll(findFiles(f.getAbsolutePath(), includeSha256));
				}
//				if (filesFound.size() > 20 ) {
//					break;
//				}
			}
		}
		return filesFound;
	}

	/**
	 * @param filePath
	 * @return
	 */
	public static String getFileSHA256(String filePath) {
		try (FileInputStream fis = new FileInputStream(filePath)) {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] buffer = new byte[8192];
			int bytesRead;
			while ((bytesRead = fis.read(buffer)) != -1) {
				digest.update(buffer, 0, bytesRead);
			}
			byte[] hashBytes = digest.digest();
			StringBuilder hexString = new StringBuilder();
			for (byte b : hashBytes) {
				String hex = String.format("%02x", b);
				hexString.append(hex);
			}
			return hexString.toString();
		} catch (IOException | NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

}
