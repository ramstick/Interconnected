package ide;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

/**
 * 
 * The IDE im trying to design
 * 
 * dont worry about this one just yet.
 * 
 * @author andrew
 *
 */

public class ProgramPanel extends JPanel{
	
	TextArea text;
	
	public ProgramPanel() {
		text = new TextArea(5,20);
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		setLayout(new GridLayout(1,1));
		add(text);
		text.setEditable(true);
		System.out.println("Fuck");
	}
}
class TextArea extends JTextPane implements DocumentListener{
	
	String program = "public class Main {\n\tpublic static void main(String [] args) {\n\t\tSystem.out.println(\"Hello World!\");\n\t}\n}";
	
	StyleContext sc;
	Style JAVA_STYLE, COMMENT_STYLE, VARIABLE_STYLE, OTHER_STYLE, CLASS_STYLE, METHOD_STYLE, STRING_STYLE;
	
	public TextArea(int r, int c) {
		
	    sc = new StyleContext();
		
		final String fontfamily = "courier";
		
	    JAVA_STYLE = sc.addStyle("JAVA", null);
	    JAVA_STYLE.addAttribute(StyleConstants.Foreground, new Color(91, 48, 156));
	    JAVA_STYLE.addAttribute(StyleConstants.FontSize, 16);
	    JAVA_STYLE.addAttribute(StyleConstants.FontFamily, fontfamily);
	    
	    VARIABLE_STYLE = sc.addStyle("VARIABLE", null);
	    VARIABLE_STYLE.addAttribute(StyleConstants.Foreground, new Color(30, 54, 150));
	    VARIABLE_STYLE.addAttribute(StyleConstants.FontSize, 16);
	    VARIABLE_STYLE.addAttribute(StyleConstants.FontFamily, fontfamily);	
	    
	    OTHER_STYLE = sc.addStyle("OTHER", null);
	    OTHER_STYLE.addAttribute(StyleConstants.Foreground, new Color(200, 200, 200));
	    OTHER_STYLE.addAttribute(StyleConstants.FontSize, 16);
	    OTHER_STYLE.addAttribute(StyleConstants.FontFamily, fontfamily);	
	    
	    CLASS_STYLE = sc.addStyle("CLASS", null);
	    CLASS_STYLE.addAttribute(StyleConstants.Foreground, new Color(78, 161, 74));
	    CLASS_STYLE.addAttribute(StyleConstants.FontSize, 16);
	    CLASS_STYLE.addAttribute(StyleConstants.FontFamily, fontfamily);	
	    
	    METHOD_STYLE = sc.addStyle("METHOD", null);
	    METHOD_STYLE.addAttribute(StyleConstants.Foreground, new Color(74, 161, 135));
	    METHOD_STYLE.addAttribute(StyleConstants.FontSize, 16);
	    METHOD_STYLE.addAttribute(StyleConstants.FontFamily, fontfamily);	
	    
	    COMMENT_STYLE = sc.addStyle("COMMENT", null);
	    COMMENT_STYLE.addAttribute(StyleConstants.Foreground, new Color(100, 100, 100));
	    COMMENT_STYLE.addAttribute(StyleConstants.FontSize, 16);
	    COMMENT_STYLE.addAttribute(StyleConstants.FontFamily, fontfamily);	
	    
	    STRING_STYLE = sc.addStyle("STRING", null);
	    STRING_STYLE.addAttribute(StyleConstants.Foreground, new Color(227, 152, 39));
	    STRING_STYLE.addAttribute(StyleConstants.FontSize, 16);
	    STRING_STYLE.addAttribute(StyleConstants.FontFamily, fontfamily);	
	    
		try {
			parseString(program);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void parseString(String source) throws BadLocationException {

		StyledDocument doc = new DefaultStyledDocument(sc);
		setDocument(doc);
		
		ArrayList<ColorCodedInterval> tokens = IDERegexes.colorCode(source);
		
		for (int i=0; i < tokens.size(); i++) {
	        doc.insertString(doc.getLength(), tokens.get(i).data,
	                         doc.getStyle(tokens.get(i).style.toString()));
	    }
		doc.addDocumentListener(this);
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
	
		try {
			parseString(getText());
		} catch (BadLocationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
	}
}
