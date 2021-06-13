package com.salesforce.tests.fs;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

import com.salesforce.tests.fs.impl.*;
import com.salesforce.tests.fs.interfaces.*;

/**
 * The entry point for the Test program
 */
public class Main {
	private static Set<ICommand> commandsSet = new HashSet<ICommand>();
	public static void initCommands() {

		commandsSet.add(new CurrentDirectoryCommandImpl());
		commandsSet.add(new ListDirectoryContentsCommandImpl());
		commandsSet.add(new MakeDirectoryCommandImpl());
		commandsSet.add(new ChangeDirectoryCommandImpl());
		commandsSet.add(new CreateFileCommandImpl());
		commandsSet.add(new QuitCommandImpl());
	}
    public static void main(String[] args) {
    	initCommands();
		Scanner scanner = new Scanner(new InputStreamReader(System.in));
    	FolderFileSystemNode currentNode = new FolderFileSystemNode("root", null);;
        while (currentNode != null) {
    		String input = scanner.nextLine();
    		String[] command = input.split("\\s+");
    		Optional<ICommand> currentCommand = commandsSet.stream().filter(n->n.getName().equals(command[0])).findFirst();
    		if(currentCommand.isPresent()) {
    			currentNode = currentCommand.get().execute(currentNode, Arrays.copyOfRange(command, 1, command.length));
    		} else {
    			System.out.println("Unrecognized command");
    		}
    	}
        scanner.close(); 
    }
}
