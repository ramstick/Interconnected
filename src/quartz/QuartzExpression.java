package quartz;

import java.util.ArrayList;

public class QuartzExpression {


	public QuartzObject evaluate(VirtualMachine machine) {

		return null;
	}
}

class QuartzBinaryExpression extends QuartzExpression{
	QuartzExpression a, b;
}

class QuartzBinaryAdditionExpression extends QuartzBinaryExpression {
	
	public QuartzBinaryAdditionExpression(QuartzExpression a, QuartzExpression b){
		this.a = a;
		this.b = b;
	}
	
	public QuartzObject evaluate(VirtualMachine machine) {
		QuartzObject leftVal = a.evaluate(machine);
		QuartzObject rightVal = b.evaluate(machine);
		ArrayList<QuartzObject> parameters = new ArrayList<QuartzObject>();
		parameters.add(leftVal);
		parameters.add(rightVal);
		return machine.runFunction(((QuartzFunction) (leftVal.attributes.get("+"))), parameters);
	}
}

class QuartzBinaryMultiplicationExpression extends QuartzBinaryExpression {
	
	public QuartzBinaryMultiplicationExpression(QuartzExpression a, QuartzExpression b){
		this.a = a;
		this.b = b;
	}
	
	public QuartzObject evaluate(VirtualMachine machine) {
		QuartzObject leftVal = a.evaluate(machine);
		QuartzObject rightVal = b.evaluate(machine);
		ArrayList<QuartzObject> parameters = new ArrayList<QuartzObject>();
		parameters.add(leftVal);
		parameters.add(rightVal);
		return machine.runFunction(((QuartzFunction) leftVal.attributes.get("*")), parameters);
	}
}

class QuartzBinarySubtractionExpression extends QuartzBinaryExpression {
	
	public QuartzBinarySubtractionExpression(QuartzExpression a, QuartzExpression b){
		this.a = a;
		this.b = b;
	}
	
	public QuartzObject evaluate(VirtualMachine machine) {
		QuartzObject leftVal = a.evaluate(machine);
		QuartzObject rightVal = b.evaluate(machine);
		ArrayList<QuartzObject> parameters = new ArrayList<QuartzObject>();
		parameters.add(leftVal);
		parameters.add(rightVal);
		return machine.runFunction(((QuartzFunction) leftVal.attributes.get("-")), parameters);
	}
	
}

class QuartzBinaryDivisionExpression extends QuartzBinaryExpression {
	
	public QuartzBinaryDivisionExpression(QuartzExpression a, QuartzExpression b){
		this.a = a;
		this.b = b;
	}
	
	public QuartzObject evaluate(VirtualMachine machine) {
		QuartzObject leftVal = a.evaluate(machine);
		QuartzObject rightVal = b.evaluate(machine);
		ArrayList<QuartzObject> parameters = new ArrayList<QuartzObject>();
		parameters.add(leftVal);
		parameters.add(rightVal);
		return machine.runFunction(((QuartzFunction) leftVal.attributes.get("/")), parameters);
	}
}

class QuartzTerm extends QuartzExpression {

	String varName;
	public QuartzTerm(String varName) {
		this.varName = varName;
	}

	public QuartzObject evaluate(VirtualMachine machine) {
		System.out.println("GETTING - "+varName);
		return machine.get(varName);
	}
}

class QuartzConstantIntTerm extends QuartzExpression {

	QuartzInt value;

	public QuartzConstantIntTerm(int x) {
		this.value = new QuartzInt(x);
	}

	public QuartzObject evaluate(VirtualMachine machine) {
		return value;
	}
}

class QuartzConstantStringTerm extends QuartzExpression {

	QuartzString value;

	public QuartzConstantStringTerm(String x) {
		this.value = new QuartzString(x);
	}

	public QuartzObject evaluate(VirtualMachine machine) {
		return value;
	}
}