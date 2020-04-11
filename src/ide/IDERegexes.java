package ide;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class contains the method needed to color code the text in an IDE
 * 
 * Its really bad i know
 * 
 * @author andrew
 *
 */

enum IDEStyle {
	COMMENT, JAVA, CLASS, METHOD, STRING, VARIABLE, OTHER
}

public class IDERegexes {
	public static ArrayList<ColorCodedInterval> colorCode(String source) {
		Pattern findSemanticsPattern = Pattern.compile("(?<comment>\\/\\/.*\\n)|(?<multilinecomment>\\/\\*[\\s\\S]*\\*\\/)|(?<java>package|import|public|static|class|void|new|int|boolean|double|float|char|true|false|null|\\d+)|(?<class>(?<=\\W)[A-Z]\\w+)|(?<methods>(?<=[\\. ])\\w+(?=\\(.*\\)))|(?<string>\\\".*\\\")|(?<variables>\\w+)");
		Matcher findSemanticsMatcher = findSemanticsPattern.matcher(source);
		
		ArrayList<ColorCodedInterval> intervals = new ArrayList<ColorCodedInterval>();
		
		int lastindex = 0;
		
		while(findSemanticsMatcher.find()) {
			
			int start = findSemanticsMatcher.start();
			if(lastindex != start) {
				intervals.add(new ColorCodedInterval(source.substring(lastindex, start),IDEStyle.OTHER));
			}
			lastindex = findSemanticsMatcher.end();
			
			if(findSemanticsMatcher.group("comment") != null) {
				intervals.add(new ColorCodedInterval(findSemanticsMatcher.group(), IDEStyle.COMMENT));
			}else if(findSemanticsMatcher.group("multilinecomment") != null) {
				intervals.add(new ColorCodedInterval(findSemanticsMatcher.group(), IDEStyle.COMMENT));
			}else if(findSemanticsMatcher.group("java") != null) {
				intervals.add(new ColorCodedInterval(findSemanticsMatcher.group(), IDEStyle.JAVA));
			}else if(findSemanticsMatcher.group("class") != null) {
				intervals.add(new ColorCodedInterval(findSemanticsMatcher.group(), IDEStyle.CLASS));
			}else if(findSemanticsMatcher.group("methods") != null) {
				intervals.add(new ColorCodedInterval(findSemanticsMatcher.group(), IDEStyle.METHOD));
			}else if(findSemanticsMatcher.group("string") != null) {
				intervals.add(new ColorCodedInterval(findSemanticsMatcher.group(), IDEStyle.STRING));
			}else if(findSemanticsMatcher.group("variables") != null) {
				intervals.add(new ColorCodedInterval(findSemanticsMatcher.group(), IDEStyle.VARIABLE));
			}
			
			}
		if(lastindex != source.length()) {
			intervals.add(new ColorCodedInterval(source.substring(lastindex),IDEStyle.OTHER));
		}
		return intervals;
	}
}
class ColorCodedInterval{
	
	public String data;
	public IDEStyle style;
	
	public ColorCodedInterval(String data, IDEStyle style) {
		this.data = data;
		this.style = style;
	}
}
