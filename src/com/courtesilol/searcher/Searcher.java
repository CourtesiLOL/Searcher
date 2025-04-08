package com.courtesilol.searcher;

import java.io.File;

public class Searcher {

	private static String format; 
	private static int totalFilesFound;
	
	public static void main(String[] args) {		
		
		if(args.length < 2) {
			System.out.println("Error: Parameters mising");
			return; 
		}
		
		File inputPath = new File(args[0]);		
		if(!inputPath.exists()) {
			System.out.println("Error: This directory not exist");
			return;
		}		
		
		if(!inputPath.isDirectory()) {
			System.out.println("Error: Is not a Directory");
			return;
		}
		
		totalFilesFound = 0;
		format = args[1];
		
		System.out.println(putScuareText("Searching Files with format "+format));
		recursiveSearch(inputPath, format);		
		System.out.println(putScuareText("Total files found: "+totalFilesFound));

	}
	
	private static void recursiveSearch(File parentPath, String fileFormat) {
		
		String[] itemList = parentPath.list();
		
		if(itemList.length <= 0) {
			return;
		}
		
		
		File temp;
		for(String file : itemList) {
			temp = new File(parentPath.getAbsolutePath() + File.separator + file);
			
			if(!temp.canRead() || temp.isHidden()) {
				continue;
			}
			
			if(temp.isDirectory()) {
				recursiveSearch(temp, fileFormat);
			}
			
			String[] splitedString = temp.getName().split("\\.");			
			if(splitedString.length >= 2) {
				if(splitedString[splitedString.length - 1].equals(fileFormat)) {
					System.out.println("│"+splitedString[0]+": "+temp.getAbsolutePath());
					totalFilesFound++;
				}
			}
			
			
			
		}
		
	}	
	
	private static String putScuareText(String text) {

        StringBuilder builder = new StringBuilder();

        builder.append("┌");
        for(short i = 0; i<text.length(); i++) {
            builder.append("─");
        }
        builder.append("┐\n");

        builder.append("│");
        builder.append(text);
        builder.append("│\n");

        builder.append("└");
        for(short i = 0; i<text.length(); i++) {
            builder.append("─");
        }
        builder.append("┘");

        return builder.toString();
    }
	
}
