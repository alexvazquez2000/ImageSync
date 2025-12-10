package com.alex.images;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Map.Entry;

import com.alex.images.bean.FileInfo;
import com.alex.utils.Utils;

public class DeleteDupFilesThreeWaySync {

	public static void main(String[] args) throws IOException {
		String oneDriveDir = "C:/Users/alexv/OneDrive/Pictures";
		Map<String, FileInfo> oneDriveFiles= Utils.findFiles(oneDriveDir, true);
//		for (Entry<String, FileInfo> entry : oneDriveFiles.entrySet()) {
//			FileInfo f = entry.getValue();
//			String fName = f.getFullName();
//			System.out.println(fName) ;
//			System.out.println("\t" + f.getSha256() ) ;
//			
//		}
		
		String backupDriveDir = "D:/galaxyUltra22/photos";
		Map<String, FileInfo> backupDriveFiles = Utils.findFiles(backupDriveDir, true);
		
		String cellDrive = "C:/Users/alexv/CrossDevice/AlexUltra/storage/DCIM/Camera";
		Map<String, FileInfo> cellDirFiles= Utils.findFiles(cellDrive, true);
		
		//Look for all the files on the backup directory
		for (Entry<String, FileInfo> entry : backupDriveFiles.entrySet()) {
			FileInfo archiveFile = entry.getValue();
			//the files that are already archived can be deleted from OneDrive and from the cell phone
			deleteFileOn("Cell", archiveFile, cellDirFiles);
			deleteFileOn("OneDrive", archiveFile, oneDriveFiles);
		}
	}

	
	
	private static void deleteFileOn(String deleteLocation, FileInfo archiveFile, Map<String, FileInfo> deleteOnDirFiles) throws IOException {
		//FIXME: The key of the map is no longer the SHA256 -
		if (deleteOnDirFiles.containsKey(archiveFile.getSha256()) ) {
			FileInfo fileInfoToBeDeleted = deleteOnDirFiles.get(archiveFile.getSha256());
			if (fileInfoToBeDeleted.getFileName().equals(archiveFile.getFileName() )) {
				System.out.println("Delete on  " + deleteLocation + " " + fileInfoToBeDeleted.getFullName());
				Files.delete(Paths.get(fileInfoToBeDeleted.getFullName()) );
			} else {
				System.err.println("No Match on file name for SHA " + deleteLocation);
			}
		}
	}


}
