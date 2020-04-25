package quartz;

import java.util.HashMap;
import java.util.List;

public class QuartzObject {
	public String type;
	public HashMap<String, QuartzObject> attributes;
	public QuartzObject() {
		type = "object";
		attributes = new HashMap<String, QuartzObject> ();
	}
	public String toString() {
		
		return "This is an object";
	}
	
	public QuartzObject get(List<String> varList) {
		if(varList.isEmpty()) {
			return this;
		}
		
		String curr = varList.get(0);
		
		return attributes.get(curr).get(varList.subList(1, varList.size()));
	}
}
class QuartzInt extends QuartzObject{
	public int value;
	public QuartzInt() {
		type = "int";
		value = 0;
		attributes.put("+", new QuartzIntAddFunction());
		attributes.put("-", new QuartzIntSubFunction());
		attributes.put("*", new QuartzIntMultiplyFunction());
		attributes.put("/", new QuartzIntDivideFunction());
	}
	public QuartzInt(int val) {
		type = "int";
		value = val;
		attributes.put("+", new QuartzIntAddFunction());
		attributes.put("-", new QuartzIntSubFunction());
		attributes.put("*", new QuartzIntMultiplyFunction());
		attributes.put("/", new QuartzIntDivideFunction());
	}
	
	public String toString() {
		
		return ""+value;
	}
}

class QuartzBoolean extends QuartzObject{
	public boolean value;
	public QuartzBoolean() {
		type = "boolean";
		value = false;
	}
	public QuartzBoolean(boolean val) {
		value = val;
		type = "boolean";
	}
	
	public String toString() {
		
		return ""+value;
	}
}

class QuartzDouble extends QuartzObject {
	public double value;
	public QuartzDouble() {
		type = "boolean";
		value = 0;
	}
	public QuartzDouble(double val) {
		value = val;
		type = "boolean";
	}
	
	public String toString() {
		
		return ""+value;
	}
}

class QuartzString extends QuartzObject {
	public String value;
	public QuartzString() {
		value = "";
		type = "string";
	}
	public QuartzString(String val) {
		this.value = val;
		type = "string";
	}
	public String toString() {
		return value;
	}
}