package com.istm.cda.ui;

import java.util.Scanner;

/**
 * Displays available reports
 * @author Team 4(601)
 */
class GenerateReportCommand implements Command {

	private Console console;

	public GenerateReportCommand(Console console) {
		this.console = console;
	}

	@Override
	public void execute(String[] args) {

		System.out.println("\n Enter 1 to generate 'customer demograph - membership type' analysis report \n Enter 2 to generate 'customer age group - days since last order' analysis report \n Enter 3 to generate a graphical analysis of 'order frequency each year'");
		
		}
	}

