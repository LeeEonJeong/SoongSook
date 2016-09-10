package com.haniumpkg.myapp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KeyboardVO implements Serializable {
	private String type ;
	private List<String> buttons;

	public KeyboardVO() {
		type = "buttons";
		buttons = new ArrayList<String>();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void addMenu(String data) {
		buttons.add(data);
	}

	public void addMenu(String data1, String data2) {
		buttons.add(data1);
		buttons.add(data2);
	}
	
	public void addMenu(String data1, String data2, String data3) {
		buttons.add(data1);
		buttons.add(data2);
		buttons.add(data3);
	}
	
	public List<String> getButtons() {
		return buttons;
	}

	public void setButtons(List<String> buttons) {
		this.buttons = buttons;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((buttons == null) ? 0 : buttons.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KeyboardVO other = (KeyboardVO) obj;
		if (buttons == null) {
			if (other.buttons != null)
				return false;
		} else if (!buttons.equals(other.buttons))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "KeyboardVO [type=" + type + ", buttons=" + buttons + "]";
	}

}