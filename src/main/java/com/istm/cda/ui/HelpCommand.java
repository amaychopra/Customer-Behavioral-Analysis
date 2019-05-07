package com.istm.cda.ui;

/**
 * Displays available commands
 * @author Team 4(601)
 */
class HelpCommand implements Command {

	private static final String FORMAT = "%-10s %s %s%n";
	
	private Console console;
	
	public HelpCommand(Console console) {
		this.console = console;
	}

	@Override
	public void execute(String[] args) {
		console.write("available commands: ");
		console.write(FORMAT, "generate", "", "generate customer behavior analysis reports");
		console.write(FORMAT, "help", "", "show available commands");
		console.write(FORMAT, "version", "", "show version of the release");
		console.write(FORMAT, "exit", "", "terminate and leave the system");
		
		

	}

}
