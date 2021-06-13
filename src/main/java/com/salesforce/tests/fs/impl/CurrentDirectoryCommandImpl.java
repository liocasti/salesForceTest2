package com.salesforce.tests.fs.impl;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.salesforce.tests.fs.interfaces.ICommand;

public class CurrentDirectoryCommandImpl implements ICommand {

	@Override
	public FolderFileSystemNode execute(FolderFileSystemNode ffsn, String[] params) {
		List<String> paths = new LinkedList<>();
		FolderFileSystemNode current = ffsn;
		while(current != null) {
			paths.add(current.getName());
			current = current.getParent();
		} 
		Collections.reverse(paths);
		paths.stream().forEach(i -> System.out.print("/" + i));
		System.out.println();
		return ffsn;
	}

	@Override
	public String getName() {
		return "pwd";
	}

}
