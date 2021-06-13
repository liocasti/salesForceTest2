package com.salesforce.tests.fs.impl;

import com.salesforce.tests.fs.interfaces.ICommand;

public class QuitCommandImpl implements ICommand {

	@Override
	public FolderFileSystemNode execute(FolderFileSystemNode ffsn, String[] params) {
		if(params == null || params.length != 0) {
			System.out.println("Invalid Command");
			return ffsn;
		} else {
			return null;
		}
	}
	@Override
	public String getName() {
		return "quit";
	}
}
