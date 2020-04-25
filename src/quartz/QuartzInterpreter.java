package quartz;

import java.util.ArrayList;
import java.util.List;

public class QuartzInterpreter {
	
	List<String> tokens;
	int offset;
	
	public QuartzInterpreter (String source) {
		tokens = QuartzParser.getTokens(source);
		offset = 0;
	}
	
	public void interpretNextLine(VirtualMachine machine) {
		
		// Get next Statement.
		
		if(tokens.size() > offset) {
			int begin = offset;
			String token = tokens.get(offset++);
			if(token.equals("var")) {
				while(offset < tokens.size() && !tokens.get(offset ++).equals(";")) {}
				interpretDeclaration(tokens.subList(begin, offset), machine);
			}else if(token.equals("if")) {
				while(offset < tokens.size() && !tokens.get(offset ++).equals(";")) {}
				//interpretDeclaration(tokens.subList(begin, offset), machine);
			}else if(token.equals("for")) {
				while(offset < tokens.size() && !tokens.get(offset ++).equals(";")) {}
				//interpretDeclaration(tokens.subList(begin, offset), machine);
			}else if(token.equals("while")) {
				while(offset < tokens.size() && !tokens.get(offset ++).equals(";")) {}
				//interpretDeclaration(tokens.subList(begin, offset), machine);
			}else if(token.equals("return")) {
				while(offset < tokens.size() && !tokens.get(offset ++).equals(";")) {}
				//interpretDeclaration(tokens.subList(begin, offset), machine);
			}else {
				
			}
		}
	}
	
	public void interpretDeclaration(List <String> statement, VirtualMachine machine) {
		int i = 1;
		int numToken = statement.size();
		while(i < numToken) {
			String varName = statement.get(i);
			
			if(i + 1 < numToken) {
				String s =  statement.get(++i);
				if(s.equals(",")) {
					// Continue execution.
					machine.set(varName, new QuartzObject());
				}else if(s.equals(";")) {
					// End execution.
					machine.set(varName, new QuartzObject());
					return;
				}else if(s.equals("=")) {
					// Parse Expression.
					int parenCount = 0; 
					int begin = ++i;
					boolean finishedExpression = false;
					while(i < numToken && !finishedExpression) {
						String token = statement.get(++i);
						
						if(token.equals("(")) {
							parenCount ++;
						}else if(token.equals(")")) {
							parenCount --;
						}
						if(parenCount == 0) {
							if(token.equals(",")) {
								
								QuartzExpression out = QuartzParser.parseExpression(statement.subList(begin, i));
								
								// Continue execution.
								machine.set(varName, out.evaluate(machine));
								finishedExpression = true;
							}else if(token.equals(";")) {
								
								QuartzExpression out = QuartzParser.parseExpression(statement.subList(begin, i));
								
								// End execution.
								machine.set(varName, out.evaluate(machine));
								return;
							}
						}
					}
				}
			}
		}
	}
}
