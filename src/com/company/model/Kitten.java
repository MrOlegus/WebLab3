package com.company.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlRootElement
@XmlType(propOrder = {"name", "color", "age"})
public class Kitten implements Serializable {
	private static final long serialVersionUID = 1623140296706573943L;
	private int id;
	private String name;
	private String color;
	private int age;
	
	public Kitten() {
		id = 0;
		name = "";
		color = "";
		age = 0;
	}
	
	public Kitten(int id, String name, String color, int age) {
		this.id = id;
		this.name = name;
		this.color = color;
		this.age = age;
	}

	public int getId() {
		return id;
	}

	@XmlAttribute
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	@XmlElement
	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	@XmlElement
	public void setColor(String color) {
		this.color = color;
	}

	public int getAge() {
		return age;
	}

	@XmlElement
	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Kitten other = (Kitten) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Kitten [\r\n"
				+ "\tid=" + id + ",\r\n"
				+ "\tname=" + name + ",\r\n"
				+ "\tcolor=" + color + ",\r\n"
				+ "\tage=" + age + "]";
	}
	
}
