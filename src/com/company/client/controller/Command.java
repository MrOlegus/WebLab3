package com.company.client.controller;

import com.company.model.Kitten;
import com.company.model.User;
import com.company.model.UserRole;

import java.io.IOException;

public class Command {
	public static final String MENU = "Возможные действия:\r\n"
			+ "\t help               - вывод доступных команд на экран,\r\n"
			+ "\t registration       - создать пользователя,\r\n"
			+ "\t authorization      - аторизация пользователя,\r\n"
			+ "\t getKitten          - получить дело на просмотр,\r\n"
			+ "\t createKitten       - создать новое дело,\r\n"
			+ "\t editKitten         - редактировать дело,\r\n"
	        + "\t exit               - редактировать дело";
	
	private Connection connection;
	private Kitten kitten;
	private Console console;
	private User user;
	
	public Command() {
		connection = new Connection();
		connection.connect();
		kitten = new Kitten();
		console = Console.getInstance();
		user = new User();
	}
	
	public void registration() {
		String username;
		String password;
		UserRole userRole;

		try {
			System.out.println("Введите логин:");
			username = console.nextUsername();
			System.out.println("Введите пароль:");
			password = console.nextPassword();
			System.out.println("1 - админ  2 - пользователь");
			userRole = console.nextBoolean() ? UserRole.ADMIN : UserRole.USER;
			user = new User(username, password, userRole);
			connection.registration(user);
		} catch (IOException e) {
		}
	}
	
	public void authorization() {
		String username;
		String password;

		try {
			System.out.println("Введите логин:");
			username = console.nextUsername();
			System.out.println("Введите пароль:");
			password = console.nextPassword();
			user = connection.authorization(username, password);
		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
		}
	}
	
	public void getKitten() {
		if (user == null || user.getUsername().equals("")) {
			System.out.println("Необходимо зарегестрироваться или авторизоваться");
			return;
		}
		
		System.out.println("Введите ID дела");
		int id = console.nextInt();
		
		try {
			connection.sendMessage("getkitten " + id);
			connection.waitForAnswer();
			kitten = (Kitten) connection.receiveFile();
			
			if (kitten.getId() != 0) {
				System.out.println(kitten);
			} else {
				System.out.println("Котенок не найден");
			}
		} catch (ClassNotFoundException e) {
		} catch (IOException e) {
		}
	}
	
	public void editKitten() {
		if (user == null || user.getRole() != UserRole.ADMIN) {
			System.out.println("Необходимы права администратора");
			return;
		}
		
		getKitten();
		
		if (kitten == null || kitten.getId() == 0) {
			System.out.println("Котенок не найден");
			return;
		}
		
		KittenBuilder b = new KittenBuilder();
		
		kitten = b.edit(kitten);
		
		try {
			connection.sendMessage("createkitten");
			connection.sendFile(kitten);
		} catch (IOException e) {
		}
	}
	
	public void createKitten() {
		if (user == null || user.getRole() != UserRole.ADMIN) {
			System.out.println("Необходимо  зарегестрироваться или авторизоваться");
			return;
		}
		
		KittenBuilder b = new KittenBuilder();
		Kitten kitten = b.create();
		
		try {
			connection.sendMessage("createkitten");
			connection.sendFile(kitten);
		} catch (IOException e) {
		}
	}
	
	public void exit() {
		try {
			connection.sendMessage("exit");
		} catch (IOException e) {
		}
	}
}
