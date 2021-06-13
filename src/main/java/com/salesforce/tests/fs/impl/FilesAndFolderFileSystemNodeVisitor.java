package com.salesforce.tests.fs.impl;

public class FilesAndFolderFileSystemNodeVisitor implements FileSystemVisitor {

	@Override
	public void visitFolder(String path, FolderFileSystemNode node) {
		System.out.print(path + node.getName());
		
	}
	@Override
	public void visitFile(FileFileSystemNode file) {
		System.out.println( file.getName());
		
	}

}
