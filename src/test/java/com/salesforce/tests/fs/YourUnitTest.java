package com.salesforce.tests.fs;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import com.salesforce.tests.fs.impl.ChangeDirectoryCommandImpl;
import com.salesforce.tests.fs.impl.CreateFileCommandImpl;
import com.salesforce.tests.fs.impl.FileFileSystemNode;
import com.salesforce.tests.fs.impl.FolderFileSystemNode;
import com.salesforce.tests.fs.impl.MakeDirectoryCommandImpl;
import com.salesforce.tests.fs.impl.QuitCommandImpl;

/**
 * Place holder for your unit tests
 */
public class YourUnitTest {
	private FolderFileSystemNode currentNode = new FolderFileSystemNode("", null);;
	private String[] params ; 
	protected FolderFileSystemNode getRootNode() {
		return currentNode;
	}
	protected String[] getParams() {
		return params;
	}
	
	protected  String[] addParam(String param) {
		if(params == null) {
			params = new String[1];
			params[0] = param;
		} else {
			
			int currentLength = params.length;
			params = Arrays.copyOf(params, currentLength + 1);
			params[currentLength] = param;
		}
		return params;
	}

	@Test
	public void testChangeDirectoryCommandImpl() {
		ChangeDirectoryCommandImpl sut = new ChangeDirectoryCommandImpl();
		String name = "subFolder1";
		FolderFileSystemNode rootNode = getRootNode();
		FolderFileSystemNode currentNode = (FolderFileSystemNode) rootNode.addChild(new FolderFileSystemNode(name, rootNode));
		addParam(name);
		assertTrue(sut.execute(getRootNode(), getParams()).equals(currentNode));
		
	}
	
	
	@Test
	public void testMakeOneFile() {
		CreateFileCommandImpl sut = new CreateFileCommandImpl();
		String param = "file1";
		addParam(param);
		sut.execute(getRootNode(), getParams());
		assertTrue(getRootNode().getChildren().size() == 1);
		
		FileFileSystemNode childFile = (FileFileSystemNode) getRootNode().getChildren().iterator().next();
		assertTrue(childFile.getName().equals(param));
		assertTrue(childFile.getType().equals("FILE"));
		assertTrue(childFile.getParent().equals(getRootNode()));
		
	}
	
	@Test
	public void testMakeTwoFilesWithSameNameInSameDirectory() {
		testMakeOneDirectory();
		testMakeOneDirectory();
		
	}
	
	@Test
	public void testMakeOneDirectory() {
		MakeDirectoryCommandImpl sut = new MakeDirectoryCommandImpl();
		String param = "folder1";
		addParam(param);
		sut.execute(getRootNode(), getParams());
		assertTrue(getRootNode().getChildren().size() == 1);
		
		FolderFileSystemNode childFolder = (FolderFileSystemNode) getRootNode().getChildren().iterator().next();
		assertTrue(childFolder.getName().equals(param));
		assertTrue(childFolder.getType().equals("FOLDER"));
		assertTrue(childFolder.getParent().equals(getRootNode()));
		
	}

	
	@Test
	public void testQuitCommandImpl() {
		QuitCommandImpl sut = new QuitCommandImpl();
		assertTrue(sut.execute(getRootNode(), new String[0]) == null);
	}
}
