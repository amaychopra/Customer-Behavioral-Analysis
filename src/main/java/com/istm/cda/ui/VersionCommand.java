package com.istm.cda.ui;

/**
 * Displays the system's name and version information.
 * @author Team 4(601)
 */
public class VersionCommand implements Command {

	Console console;
	
	public VersionCommand(Console console) {
		this.console = console;
	}
	
	@Override
    public void execute(String[] args) {
    		console.write("CDA version \"0.4\"");
    }

}
