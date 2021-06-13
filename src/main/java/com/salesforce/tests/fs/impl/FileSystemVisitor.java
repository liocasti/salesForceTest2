package com.salesforce.tests.fs.impl;

public interface FileSystemVisitor {
	public void visitFolder(String path, FolderFileSystemNode nodet);
	public void visitFile(FileFileSystemNode file);
}
