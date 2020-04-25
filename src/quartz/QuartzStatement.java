package quartz;

import java.util.ArrayList;
import java.util.HashMap;

public class QuartzStatement {
	
	public QuartzObject run(VirtualMachine machine) {
		return null;
	}
	
}

class QuartzDeclarationStatement extends QuartzStatement{
	
	ArrayList<String> name;
	
	public QuartzDeclarationStatement(ArrayList<String> name) {
		this.name = name;
	}
	
	public QuartzObject run(VirtualMachine machine) {
		if(name.size() == 1) {
			machine.memory.put(name.get(0), null);
		}
		return null;
	}
}

class QuartzAssignmentStatement extends QuartzStatement{
	
	ArrayList<String> assigned, copied;
	
	public QuartzAssignmentStatement(ArrayList<String> assigned, ArrayList<String> copied) {
		this.assigned = assigned;
		this.copied = copied;
	}
	
	public QuartzObject run(VirtualMachine machine) {
		if(assigned.size() == 1) {
			machine.memory.put(assigned.get(0), machine.memory.get(copied.get(0)));
		}
		return null;
	}
}

class QuartzConstantAssignmentStatement extends QuartzStatement{
	
	ArrayList<String> assigned;
	QuartzObject copied;
	
	public QuartzConstantAssignmentStatement(ArrayList<String> assigned, QuartzObject copied) {
		this.assigned = assigned;
		this.copied = copied;
	}
	
	public QuartzObject run(VirtualMachine machine) {
		if(assigned.size() == 1) {
			machine.memory.put(assigned.get(0), copied);
		}
		return null;
	}
}
class QuartzGlobalFunctionCallStatement extends QuartzStatement {
	String name;
	ArrayList<String> params;
	public QuartzGlobalFunctionCallStatement(String name, ArrayList<String> params) {
		this.name = name;
		this.params = params;
	}
}
class QuartzFunctionCallStatement extends QuartzStatement {
	
	ArrayList<String> name;
	ArrayList<String> params;
	
	public QuartzFunctionCallStatement(ArrayList<String> name, ArrayList<String> params) {
		this.name = name;
		this.params = params;
	}
}