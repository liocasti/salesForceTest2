package com.salesforce.tests.fs.interfaces;

import com.salesforce.tests.fs.impl.FolderFileSystemNode;

public interface ICommand {
	public FolderFileSystemNode execute(FolderFileSystemNode ffsn, String[] params);
	public String getName();
}
 