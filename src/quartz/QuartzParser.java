package quartz;

import java.util.ArrayList;
import java.util.List;

enum CharType {
	DIGIT, ALPHA, SYMBOL
}

public class QuartzParser {
	public static ArrayList<String> getTokens(String source){
		ArrayList<String> tokens = new ArrayList<String>();
		
		String currToken = "";
		boolean inQuotes = false;
		CharType currType = null;
		
		for(int i = 0; i < source.length(); i ++) {
			char currChar = source.charAt(i);
			
			if(currChar == '"') {
				if(inQuotes) {
					tokens.add(currToken +'"');
					currToken = "";
					inQuotes = false;
					continue;
				}else {
					inQuotes = true;
					if(!currToken.isEmpty()) {
						tokens.add(currToken);
						currToken = "";
					}
				}
			}
			
			if(!inQuotes) {
				if(Stuff.isWhitespace(currChar)) {
					if(!currToken.isEmpty()) {
						tokens.add(currToken);
						currToken = "";
					}
					currType = null;
				}else if(Stuff.isAlphabetic(currChar)) {
					if(!(currType == null || currType == CharType.ALPHA)) {
						tokens.add(currToken);
						currToken = "";
					}
					currToken += currChar;
					currType = CharType.ALPHA;
				}else if(Stuff.isDigit(currChar)){
					if(!(currType == null || currType == CharType.DIGIT || currType == CharType.ALPHA)) {
						tokens.add(currToken);
						currToken = "";
					}
					currToken += currChar;
					currType = CharType.DIGIT;
				}else if(currChar == ';' || currChar == ')' || currChar == '(' || currChar == '{' || currChar == '}'){
					if(!currToken.isEmpty()) {
						tokens.add(currToken);
						currToken = "";
					}
					tokens.add(currChar + "");
					currType = null;
				}else {
					if(!(currType == null || currType == CharType.SYMBOL)) {
						tokens.add(currToken);
						currToken = "";
					}
					currToken += currChar;
					currType = CharType.SYMBOL;
				}
			}else {
				currToken += currChar;
			}
		}
		
		return tokens;
	}

	public static QuartzExpression parseExpression(List<String> tokens) {
		
		int parenCount = 0;
		int numTokens = tokens.size();
		for(int i = numTokens - 1; i >= 0; i --) {
			String token = tokens.get(i);
			if(token.equals("(")) {
				parenCount --;
			}else if(token.equals(")")) {
				parenCount ++;
			}else if(parenCount == 0) {
				if(token.equals("+")) {
					return new QuartzBinaryAdditionExpression(parseExpression(tokens.subList(0, i)), parseExpression(tokens.subList(i+1, numTokens)));
				}else if(token.equals("-")) {
					return new QuartzBinarySubtractionExpression(parseExpression(tokens.subList(0, i)), parseExpression(tokens.subList(i+1, numTokens)));
				}
			}
		}
		
		parenCount = 0;
		for(int i = numTokens - 1; i >= 0; i --) {
			String token = tokens.get(i);
			if(token.equals("(")) {
				parenCount --;
			}else if(token.equals(")")) {
				parenCount ++;
			}else if(parenCount == 0) {
				if(token.equals("*")) {
					return new QuartzBinaryMultiplicationExpression(parseExpression(tokens.subList(0, i)), parseExpression(tokens.subList(i+1, numTokens)));
				}else if(token.equals("/")) {
					return new QuartzBinaryDivisionExpression(parseExpression(tokens.subList(0, i)), parseExpression(tokens.subList(i+1, numTokens)));
				}
			}
		}
		
		return parseTerm(tokens);
	}
	
	public static QuartzExpression parseTerm(List<String> tokens) {
		System.out.println(tokens.get(0));
		
		if(tokens.size() == 1) {
			String token = tokens.get(0);
			System.out.println("TOKEN PARSING - "+token);
			if(Stuff.checkedClosedString(token)) {
				return new QuartzConstantStringTerm(token.substring(1,token.length() - 1));
			}else if(Stuff.isInteger(token)) {
				return new QuartzConstantIntTerm(Integer.parseInt(token));
			}else if(false) {
				
			}else {
				return new QuartzTerm(token);
			}
		}
		
		return null;
	}
	
	public static boolean isTerm(String token) {
		
		for(int i = 0; i < token.length(); i++) {
			char curr = token.charAt(i);
			if(curr == '+' || curr == '-' || curr == '/' || curr == '*') {
				return false;
			}
		}
		
		return true;
	}
}

class Stuff {
	public static boolean isWhitespace(char c) {
		return c == ' ' || c == '\n' || c == '\t';
	}
	public static boolean isDigit(char c) {
		return (c >= 48 && c <= 57) || c == '.';
	}
	public static boolean isUppercase(char c) {
		return c >= 65 && c <= 90;
	}
	public static boolean isLowercase(char c) {
		return c >= 97 && c <= 122;
	}
	public static boolean isAlphabetic(char c) {
		return (c >= 65 && c <= 90) || (c >= 97 && c <= 122);
	}
	public static boolean isSymbol(char c) {
		return !((c >= 65 && c <= 90) || (c >= 97 && c <= 122)) && !((c >= 48 && c <= 57) || c == '.');
	}
	public static boolean checkedClosedString(String s) {
		return s.length() > 2 && s.charAt(0) == '"' && s.charAt(s.length() - 1) == '"';
	}
	public static boolean isInteger(String s) {
		for(int i = 0; i < s.length(); i ++) {
			if(!isDigit(s.charAt(i)) || s.charAt(i) == '.') {
				return false;
			}
		}
		return true;
	}
}
