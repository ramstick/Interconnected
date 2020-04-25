package quartz;


import java.io.IOException;

public class Test {
	public static void main(String[] args) throws IOException {
		String program = ""
				+ "var x1=1 + 10 / 10;"
				+ "var y = 10;"
				+ "y = x1;";
		System.out.println("\n\n\n\n\n\n");
		VirtualMachine machine = new VirtualMachine(program);
		
		machine.interpretNextLine();
		machine.printMemory();
		machine.interpretNextLine();
		machine.printMemory();
		machine.interpretNextLine();
		machine.printMemory();
	}
}