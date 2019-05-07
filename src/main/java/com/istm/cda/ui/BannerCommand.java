package com.istm.cda.ui;

/**
 * Displays the user interface on console.
 * @author Team 4(601)
 */
class BannerCommand implements Command {

	private Console console;
	
	public BannerCommand(Console console) {
		this.console = console;
	}
	
	@Override
	public void execute(String[] args) {
		console.write();
		console.write("Customer Data Analysis (CDA)");
		console.write("Type 'help' for available commands");
		console.write();
	}

}
