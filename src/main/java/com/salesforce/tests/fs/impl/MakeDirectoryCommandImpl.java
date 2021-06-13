package com.salesforce.tests.fs.impl;

import com.salesforce.tests.fs.interfaces.ICommand;

public class MakeDirectoryCommandImpl implements ICommand {

	private static final int MAX_FOLDER_NAME_SIZE = 100;

	@Override
	public FolderFileSystemNode execute(FolderFileSystemNode ffsn, String[] params) {
		String folderName = params[0];
		if(folderName == null || folderName.length()==0 || folderName.length()>MAX_FOLDER_NAME_SIZE ) {
			System.out.println("Invalid File or Folder Name");
			return ffsn;
		} else {
			if(validateName(folderName, ffsn)) {
				//create folder in parent folder 
				FolderFileSystemNode folder = new FolderFileSystemNode(folderName, ffsn);
				ffsn.addChild(folder);
			} else {
				System.out.println("Directory already exists");
			}
			
		}
		return ffsn;	
		}

	private boolean validateName(String folderName, FolderFileSystemNode ffsn) {
		return !ffsn.getChildren().stream().anyMatch(n->n instanceof FolderFileSystemNode && n.getName().equals(folderName));
	}
	
	@Override
	public String getName() {
		return "mkdir";
	}
}
