package quartz;

import java.util.HashMap;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

enum MachineCallType {
	SET, GLOBAL_SET, 
}

public class VirtualMachine {
	HashMap<String, QuartzObject> memory;
	
	QuartzInterpreter interpreter;
	
	public VirtualMachine(String source) {
		
		interpreter = new QuartzInterpreter(source);
		memory = new HashMap<String, QuartzObject> ();
	}
	
	public void interpretNextLine() {
		interpreter.interpretNextLine(this);
	}
	
	public void printMemory() {
		System.out.println("\n\n-- Current Global Memory --\n");
		for(String s : memory.keySet()) {
			System.out.println(s +" - "+ memory.get(s));
		}
		System.out.println("---------------------------");
	}
	
	public QuartzObject runFunction(QuartzFunction function, List<QuartzObject> parameters) {
		if(function != null) {
			return function.run(this, parameters);
		}
		return null;
	}
	
	public void set(List<String> setting, List<String> copying) {
		QuartzObject copied = get(copying);
		
		if(setting.size() == 1) {
			memory.put(setting.get(0), copied);
		}else {
			QuartzObject setted = get(setting.subList(0, setting.size()));
			setted.attributes.put(setting.get(setting.size() - 1), copied);
		}
	}
	
	public QuartzObject get(List<String> varList) {
		
		if(varList.isEmpty()) {
			return null;
		}
		
		String curr = varList.get(0);
		
		return memory.get(curr).get(varList.subList(1, varList.size()));
	}
	
	public QuartzObject get(String var) {
		return memory.get(var);
	}
	
	public void set(String var, QuartzObject val) {
		memory.put(var, val);
	}
}