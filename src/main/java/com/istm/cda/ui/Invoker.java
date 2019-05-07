package com.istm.cda.ui;

import java.util.HashMap;
import java.util.Map;

/**
 * Responds to user input by invoking commands
 * @author Team 4(601)
 */
public class Invoker {

	private Console console;
	private Map<String, Command> commands;

	public Invoker(Console console) {
		this.console = console;
		this.commands = new HashMap<String, Command>();
		add("banner", new BannerCommand(console));
		add("help", new HelpCommand(console));
		add("exit", new ExitCommand(console));
		add("generate", new GenerateReportCommand(console));
		add("1", new GenerateCommandOne(console));
		add("2", new GenerateCommandTwo(console));
		add("3", new GenerateCommandThree(console));
		add("version", new VersionCommand(console));
	}
	
	public void add(String name, Command command) {
		this.commands.put(name, command);
	}

	public void start() {
		String[] args = new String[] { "" };
		commands.get("banner").execute(args);
		while (!args[0].equalsIgnoreCase("exit")) {
			args = console.read().toLowerCase().split(" ");
			if (commands.containsKey(args[0])) {
				try {
					commands.get(args[0]).execute(args);
				} catch (Exception e) {
					console.write(e.getMessage());
				}
			} else if (!args[0].isEmpty()){
				console.write("unrecognized command");
			}
		}
		commands.get("exit").execute(args);
	}

}
