package com.salesforce.tests.fs.impl;

public class FileFileSystemNode extends FileSystemNode {

	public FileFileSystemNode(String name, FolderFileSystemNode parent) {
		super(name, parent);
	}

	@Override
	public String getType() {
		return "FILE";
	}
	
	@Override
	public void accept(FileSystemVisitor visitor) {
		visitor.visitFile(this);
	}

}
