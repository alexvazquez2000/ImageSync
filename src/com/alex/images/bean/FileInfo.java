package com.alex.images.bean;

import java.io.Serializable;

/**
 * @author Alex Vazquez <vazqueza2000@gmail.com>
 */
public class FileInfo implements Serializable {
	private static final long serialVersionUID = -4369276613002417767L;

	private String fullName;
	private String fileName;
	private long timestamp;
	private long size;
	private String sha256;

	public FileInfo(String fullName, String fileName, long timestamp, long size) {
		super();
		this.fullName = fullName;
		this.fileName = fileName;
		this.timestamp = timestamp;
		this.size = size;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the timestamp
	 */
	public long getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the size
	 */
	public long getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(long size) {
		this.size = size;
	}

	/**
	 * @return the sha256
	 */
	public String getSha256() {
		return sha256;
	}

	/**
	 * @param sha256 the sha256 to set
	 */
	public void setSha256(String sha256) {
		this.sha256 = sha256;
	}

	@Override
	public String toString() {
		return "FileInfo [fullName=" + fullName + "\n"
				+ "\tfileName=" + fileName + "\n"
				+ "\ttimestamp=" + timestamp + "\n"
				+ "\tsize=" + size + "\n"
				+ "\tsha256=" + sha256 + "]";
	}

}
