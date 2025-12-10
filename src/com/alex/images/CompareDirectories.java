package com.alex.images;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alex.images.bean.FileInfo;
import com.alex.utils.Utils;

/**
 * Compare the files on two directories.  Gets the SHA256 for all files and compares the list
 */
public class CompareDirectories {

	public static void main(String[] args) {
		String dir1 = "E:\\cheryLinuxLaptop\\workspaces\\workspace_alex\\";
		String dir2 = "E:\\cheryLinuxLaptop\\workspace_alex_git\\home\\vazqueza\\workspace_alex\\";
		
		Map<String, FileInfo> dir1Files = Utils.findFiles(dir1, true);
		Map<String, FileInfo> dir2Files = Utils.findFiles(dir2, true);

		System.out.println("dir1Files=" + dir1Files.size());
		System.out.println("dir2Files=" + dir2Files.size());

		
		List<String> arrayList = new ArrayList<>(dir1Files.keySet());
		for (String key : arrayList) {
			FileInfo archiveFile1 = dir1Files.get(key);
			String relativeFileName = archiveFile1.getFullName().substring(dir1.length());
			FileInfo archiveFile2 = dir2Files.get(dir2 + relativeFileName);
			
			if (archiveFile2 == null) {
				System.err.println(archiveFile1 + "\n is missing on second dir " + dir2 + relativeFileName );
				dir1Files.remove(key);
			} else if (! archiveFile1.getSha256().equals(archiveFile2.getSha256())) {
				System.err.println(archiveFile1 + "\n is different than " + dir2 + relativeFileName );
				//they are different, remove both
				dir1Files.remove(key);
				dir2Files.remove(dir2 + relativeFileName);
			} else {
				//they are the same, remove both
				dir1Files.remove(key);
				dir2Files.remove(dir2 + relativeFileName);
			}
		}

		System.out.println("dir1Files=" + dir1Files.size());
		System.out.println("dir2Files=" + dir2Files.size());
		
		if (dir2Files.size() > 0) {
			//these files don't exist on the first directory
			for (String key : dir2Files.keySet()) {
				FileInfo archiveFile2 = dir2Files.get(key);
				String relativeFileName = archiveFile2.getFullName().substring(dir2.length());
				System.err.println(archiveFile2 + "\n only on second dir" + dir1 + relativeFileName );
			}
		}

	}

}
