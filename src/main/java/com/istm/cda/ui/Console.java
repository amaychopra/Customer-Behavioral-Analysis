package com.istm.cda.ui;

import java.util.Scanner;

/**
 * User interface on console.
 * @author Team 4(601)
 */
public class Console {

	private Scanner scanner;
	private String systemPrompt;
	
	public Console(String systemPrompt) {
		this.systemPrompt = systemPrompt;
		this.scanner = new Scanner(System.in);
	}

	public void write() {
		System.out.println();
	}
	
	public void write(Object object) {
		System.out.println(object.toString());
	}

	public void write(String format, Object... object) {
		System.out.printf(format, object);
	}

	public String read() {
		return read(systemPrompt);
	}
	
	public String read(String prompt) {
		System.out.print(prompt + ": ");
		return scanner.nextLine();
	}

	public void close() {
		scanner.close();
	}

}
