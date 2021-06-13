package com.salesforce.tests.fs.impl;

import java.util.*;

public class FolderFileSystemNode extends FileSystemNode {

	private List<FileSystemNode> children = new ArrayList<>();

	public FolderFileSystemNode(String name, FolderFileSystemNode parent) {
		super(name, parent);
	}
	public FileSystemNode addChild(FileSystemNode fsn) {
		this.children.add(fsn);
		return fsn;
	}
	
	public List<FileSystemNode> getChildren() {
		return children;
	}
	
	@Override
	public String getType() {
		return "FOLDER";
	}

	@Override
	public void accept(FileSystemVisitor visitor) {
		visitor.visitFolder("/", this);
		for(FileSystemNode fsn : children) {
			fsn.accept(visitor);
		}

	}
	
}
