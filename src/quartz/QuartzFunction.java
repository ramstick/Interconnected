package quartz;

import java.util.List;

public class QuartzFunction extends QuartzObject{
	
	public QuartzObject run(VirtualMachine machine, List<QuartzObject> parameters) {
		
		return null;
	}
	
}

class QuartzPrintFunction extends QuartzFunction{
	
	public QuartzObject run(VirtualMachine machine, List<QuartzObject> parameters) {
		for(QuartzObject param : parameters) {
			System.out.println(param);
		}
		return null;
	}
}


class QuartzIntAddFunction extends QuartzFunction {
	public QuartzObject run(VirtualMachine machine, List<QuartzObject> parameters) {
		QuartzInt left = (QuartzInt) parameters.get(0);
		QuartzInt right = (QuartzInt) parameters.get(1);
		System.out.println(left +" "+right);
		return new QuartzInt(left.value + right.value);
	}
}
class QuartzIntSubFunction extends QuartzFunction {
	public QuartzObject run(VirtualMachine machine, List<QuartzObject> parameters) {
		QuartzInt left = (QuartzInt) parameters.get(0);
		QuartzInt right = (QuartzInt) parameters.get(1);
		return new QuartzInt(left.value - right.value);
	}
}
class QuartzIntMultiplyFunction extends QuartzFunction {
	public QuartzObject run(VirtualMachine machine, List<QuartzObject> parameters) {
		QuartzInt left = (QuartzInt) parameters.get(0);
		QuartzInt right = (QuartzInt) parameters.get(1);
		return new QuartzInt(left.value * right.value);
	}
}
class QuartzIntDivideFunction extends QuartzFunction {
	public QuartzObject run(VirtualMachine machine, List<QuartzObject> parameters) {
		QuartzInt left = (QuartzInt) parameters.get(0);
		QuartzInt right = (QuartzInt) parameters.get(1);
		return new QuartzInt(left.value / right.value);
	}
}