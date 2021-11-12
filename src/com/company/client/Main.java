package com.company.client;

import com.company.client.controller.Command;
import com.company.client.controller.Console;

public class Main {
	
	public static void main(String[] args) {
		Console console = Console.getInstance();
		Command client = new Command();
		String command;
		
		System.out.println(Command.MENU);
		
		do {
			System.out.println("Введите команду");
			command = console.nextCommand().toLowerCase();
			switch (command) {
				case "help":
					System.out.println(Command.MENU);
					break;
				case "registration":
					client.registration();
					break;
				case "authorization":
					client.authorization();
					break;
				case "getkitten":
					client.getKitten();
					break;
				case "createkitten":
					client.createKitten();
					break;
				case "editkitten":
					client.editKitten();
					break;
				case "exit":
					client.exit();
					continue;
			}
		} while(!command.equals("exit"));
	}
}
