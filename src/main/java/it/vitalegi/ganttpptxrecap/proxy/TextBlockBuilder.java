package it.vitalegi.ganttpptxrecap.proxy;

import org.apache.poi.xslf.usermodel.XSLFTextBox;
import org.apache.poi.xslf.usermodel.XSLFTextRun;

/**
 * @author Giorgio Vitale
 */
public class TextBlockBuilder extends AutoShapeBuilder<XSLFTextBox> {

	public TextBlockBuilder(XSLFTextBox textBox) {
		super(textBox);
		XSLFTextRun run = textBox.addNewTextParagraph().addNewTextRun();
		run.setFontSize(5d);
		
	}
}
