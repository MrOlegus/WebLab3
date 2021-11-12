package com.company.client.controller;

import com.company.model.User;

import java.io.*;
import java.net.Socket;

public class Connection {
	private static final String HOST = "localhost";
	private static final int PORT = 7777;
	
	private Socket socket;
	private BufferedReader in;
	private BufferedWriter out;
	
	public Connection() {}
	
	public void connect() {
		try {
			System.out.println("Подключение к серверу...");
			socket = new Socket(HOST, PORT);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			System.out.println("Соединение установлено");
		} catch (IOException e) {
		}
		
	}
	
	public void registration(User user) throws IOException {
		sendMessage("registration");
		sendFile(user);
	}
	
	public User authorization(String username, String password) throws IOException, ClassNotFoundException {
		sendMessage("authorization " + username + " " + password);
		waitForAnswer();
		return  (User) receiveFile();
	}
	
	
	public void sendMessage(String message) throws IOException {
		out.write(message);
		out.newLine();
		out.flush();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
	}
	
	
	public void waitForAnswer() {
		try {
			while (socket.getInputStream().available() == 0) {
				Thread.sleep(1000);
			}
		} catch (IOException e) {
		} catch (InterruptedException e) {
		}
	}
	
	public String recieveMessage() {
		StringBuilder message = new StringBuilder();
		
		try {
			while (in.ready()) {
				message.append(in.readLine());
			}
		} catch (IOException e) {
		}
		
		return message.toString();
	}
	
	public Object receiveFile() throws IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
		
		Object obj = ois.readObject();
		
		return obj;
	}
	
	public void sendFile(Object obj) throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		
		oos.writeObject(obj);
		oos.flush();
	}
	
	public void close() throws IOException {
		in.close();
		out.close();
		socket.close();
	}

}
