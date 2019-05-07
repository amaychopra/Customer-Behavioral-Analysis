package com.istm.cda.ui;

/**
 * Exit the system
 * @author Team 4(601)
 */
class ExitCommand implements Command {

	private Console console;
	
	public ExitCommand(Console console) {
		this.console = console;
	}
	
	@Override
	public void execute(String[] args) {
		console.write("Exited");
		console.close();
		System.exit(0);
	}

}
