package com.salesforce.tests.fs.impl;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.salesforce.tests.fs.interfaces.ICommand;

public class ListDirectoryContentsCommandImpl implements ICommand {
	private static String RECURSIVE_RESULTS_MODIFIER = "-r";
	@Override
	public FolderFileSystemNode execute(FolderFileSystemNode ffsn, String[] params) {
		String modifier = params.length > 0 ? params[0] : null;
		if(modifier == null) {
//			show only current directory listing by its first level childen 
			System.out.println("/" + ffsn.getName());
			ffsn.getChildren().stream().forEach(n->System.out.println(n.getName()));
		}  else if (RECURSIVE_RESULTS_MODIFIER.equals(modifier)) {
			printNodes(ffsn, ffsn);
			
			//visit children
			
//			FilesAndFolderFileSystemNodeVisitor visitor = new FilesAndFolderFileSystemNodeVisitor();
//			ffsn.accept(visitor);
		} else {
			System.out.println("Invalid Command");
		}
		
		
		return ffsn;
	}
	@Override
	public String getName() {
		return "ls";
	}
	
	private void printNodes(FileSystemNode node, FolderFileSystemNode root) {
		if(node instanceof FolderFileSystemNode) {
//			System.out.println("/" + node.getName());
			printParents(node, root);
			for(FileSystemNode child : ((FolderFileSystemNode)node).getChildren()) {
				printNodes(child, root);
			}
		} else {
			System.out.println(node.getName());

		}
			
	}
	private void printParents(FileSystemNode node, FolderFileSystemNode root) {
		if(node instanceof FolderFileSystemNode) {
			List<String> paths = new LinkedList<>();
			FolderFileSystemNode current = (FolderFileSystemNode) node;
			while(current != root.getParent()) {
				paths.add(current.getName());
				current = current.getParent();
			} 
			Collections.reverse(paths);
			paths.stream().forEach(i -> System.out.print("/" + i));
			System.out.println();
		}
		
	}
}
