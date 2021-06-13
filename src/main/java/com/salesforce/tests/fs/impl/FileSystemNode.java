package com.salesforce.tests.fs.impl;

public abstract class FileSystemNode {
	private String name;
	private FolderFileSystemNode parent;
	
	public FileSystemNode(String name, FolderFileSystemNode parent) {
		this.name = name;
		this.parent = parent;
	}
	public String getName() {
		return this.name;
	}
	public FolderFileSystemNode getParent() {
		return parent;
	}
	
	public abstract String getType();
	public abstract void accept(FileSystemVisitor visitor);

}
