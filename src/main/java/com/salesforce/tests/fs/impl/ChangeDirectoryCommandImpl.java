package com.salesforce.tests.fs.impl;

import java.util.Optional;

import com.salesforce.tests.fs.interfaces.ICommand;

public class ChangeDirectoryCommandImpl implements ICommand {

	private static final int MAX_FOLDER_NAME_SIZE = 100;

	@Override
	public FolderFileSystemNode execute(FolderFileSystemNode ffsn, String[] params) {
		if(params.length==0) {
			return invalidCommand(ffsn);
		}
		String folderName = params[0];

		if(folderName == null || folderName.length()==0 || folderName.length()>MAX_FOLDER_NAME_SIZE ) {
			return invalidCommand(ffsn);
		} else {
			FolderFileSystemNode currentNode;
			if(folderName.equals("..")) {
				currentNode = ffsn.getParent();
			} else {
				Optional<FileSystemNode>optionalCurrentNode =  ffsn.getChildren().stream().filter(n->n instanceof FolderFileSystemNode 
						&& n.getName().equals(folderName)).findFirst();
				if(optionalCurrentNode.isPresent()) {
					currentNode = (FolderFileSystemNode) optionalCurrentNode.get();
				} else {
					//invalid directory
					System.out.println(String.format("Directory not found"));
					currentNode = ffsn;
				}
				
			}
			if(currentNode != null) {
				return currentNode;
				
			} else {
				//cd .. in root, return root
				return ffsn;
			}
			
			
		}
	}

	private FolderFileSystemNode invalidCommand(FolderFileSystemNode ffsn) {
		System.out.println("Invalid Command");
		return ffsn;
	}

	@Override
	public String getName() {
		return "cd";
	}

}
