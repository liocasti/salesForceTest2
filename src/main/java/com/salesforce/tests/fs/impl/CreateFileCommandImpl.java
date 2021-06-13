package com.salesforce.tests.fs.impl;

import com.salesforce.tests.fs.interfaces.ICommand;

public class CreateFileCommandImpl implements ICommand {

	private static final int MAX_FILE_NAME_SIZE = 100;

	@Override
	public FolderFileSystemNode execute(FolderFileSystemNode ffsn, String[] params) {
		String fileName = params[0];
		if(fileName == null || fileName.length()==0 || fileName.length()>MAX_FILE_NAME_SIZE ) {
			System.out.println("Invalid File or Folder Name");
			return ffsn;
		} else {
			if(validateName(fileName, ffsn)) {
				//create file in parent folder 
				FileFileSystemNode file = new FileFileSystemNode(fileName, ffsn);
				ffsn.addChild(file);
				
			}
			

		}
		return ffsn;
	}

	private boolean validateName(String fileName, FolderFileSystemNode ffsn) {
		// checks if there is another file with this name in its children
		return !ffsn.getChildren().stream().anyMatch(n->n.getType().equals("FILE") && n.getName().equals(fileName));
	}
	@Override
	public String getName() {
		return "touch";
	}

}
