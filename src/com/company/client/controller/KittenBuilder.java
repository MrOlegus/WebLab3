package com.company.client.controller;

import com.company.model.Kitten;

public class KittenBuilder {
	private Console console;
	private Kitten kitten;
	
	public KittenBuilder() {
		console = Console.getInstance();
		kitten = new Kitten();
	}
	
	public KittenBuilder(Kitten kitten) {
		console = Console.getInstance();
		this.kitten = kitten;
	}
	
	public Kitten create() {
		kitten = new Kitten();
		
		inputId();
		inputName();
		inputColor();
		inputAge();
		
		return kitten;
	}
	
	public Kitten edit(Kitten std) {
		this.kitten = std;
		
		System.out.println("Меню:\r\n"
				+ "\t1 - ID\r\n"
				+ "\t2 - Имя\r\n"
				+ "\t3 - Цвет\r\n"
				+ "\t4 - Возраст\r\n"
				+ "\t5 - Закончить редактирование\r\n");
		System.out.println(kitten);
		
		while(true) {
			System.out.println("Введите номер пункта для редактирования:");
			int number = console.nextInt();
			if (number == 5) {
				break;
			}
			switch (number) {
				case 1:
					inputId();
					break;
				case 2:
					inputName();
					break;
				case 3:
					inputColor();
					break;
				case 4:
					inputAge();
					break;
			}
		}
		
		return kitten;
	}
	
	private void inputId() {
		System.out.println("Введите ID");
		kitten.setId(console.nextInt());
	}
	
	private void inputName() {
		System.out.println("Введите имя");
		kitten.setName(console.nextCommand());
	}
	
	private void inputColor() {
		System.out.println("Введите цвет");
		kitten.setColor(console.nextCommand());
	}
	
	private void inputAge() {
		System.out.println("Введите возраст");
		kitten.setAge(console.nextInt());
	}
}
