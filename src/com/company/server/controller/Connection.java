package com.company.server.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Connection {
	private Socket clientSocket;
	private BufferedReader in;
	private BufferedWriter out;
	
	public Connection(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}
	
	public void connect() throws IOException {
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
	}
	
	public void disconnect() {
		try {
			clientSocket.close();
			in.close();
			out.close();
		} catch (IOException e) {
		}
		
	}
	
	public void waitForMessage() {
		try {
			while(clientSocket.getInputStream().available() == 0) {
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
		ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
		
		Object obj = ois.readObject();
		
		return obj;
	}
	
	public void sendFile(Object obj) throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
		
		oos.writeObject(obj);
		oos.flush();
	}
}
