/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.view.util;

import javafx.scene.control.TextFormatter;

/**
 * @author Gabriel L. P. Abreu
 */
public class TextFieldFormatterHelper {

	/**
	 * Validates data insertion per character entered according to charSetRegex.
	 *
	 * @param charSetRegex
	 */
	public static TextFormatter<Object> getTextFieldFormatter(String charSetRegex) {
		return new TextFormatter<>((TextFormatter.Change c) -> {
			String text = c.getText();

			if (!c.isContentChange()) {
				return c;
			}

			if (text.matches(charSetRegex) || text.isEmpty()) {
				return c;
			}

			return null;
		});

	}

	/**
	 * Validates data insertion per character entered according to charSetRegex and
	 * prevents insertion beyond the max limit.
	 *
	 * @param charSetRegex
	 * @param max
	 * @return
	 */
	public static TextFormatter<Object> getTextFieldFormatter(String charSetRegex, int max) {
		return new TextFormatter<>((TextFormatter.Change change) -> {

			return formatByCharset(change, charSetRegex, max);
		});

	}

	public static TextFormatter<Object> getTextFieldToUpperFormatter(String charSetRegex, int max) {
		return new TextFormatter<>((TextFormatter.Change change) -> {
			
			change.setText(change.getText().toUpperCase());
			
			return formatByCharset(change, charSetRegex, max);
		});

	}
	
    public static TextFormatter<Object> getUpperCaseTextFieldFormatter() {

        return new TextFormatter<>((TextFormatter.Change change) -> {

        if (!change.isContentChange()) 
        {
            return change;
        }
        if (change.isDeleted()) {
            return change;

        }            
        if (change.isReplaced() || change.isAdded() ) 
        {
            change.setText(change.getText().toUpperCase());
            return change;
        }

		return null;
	});

    }

	public static TextFormatter<Object> getTextFieldDoubleFormatter(int max, int maxscale) {

		return new TextFormatter<>((TextFormatter.Change change) -> {
			// DecimalFormatSymbols decimal = new DecimalFormatSymbols(Locale.getDefault());
			// String sep = String.valueOf(decimal.getDecimalSeparator());
			String localSep = ",";
			String sep = ".";

			//if(change.getText().contains(localSep)) {
			//	change.setText(change.getText().replace(localSep, sep));
			//}
			
			if (change.getControlNewText().length() > max) {
				return null;
			}

			String[] splitaux = change.getControlNewText().split("\\" + localSep);
			if ((splitaux.length == 2 && splitaux[1].length() > maxscale)) {
				return null;
			}
			if (change.isReplaced()) {
				if (change.getText().matches("[^0-9]")) {
					change.setText(change.getControlText().substring(change.getRangeStart(), change.getRangeEnd()));
				}
			}

			if (change.isAdded()) {
				if (change.getControlText().contains(localSep)) {

					if (change.getText().matches("[^0-9]")) {
						change.setText("");
					}
				} else if (change.getText().matches("[^0-9" + localSep + sep + "]")) {
					change.setText("");
				}
			}

			return change;
		});

	}

	/**
	 * Validates data insertion per character entered according to charSetRegex and
	 * mask, the mask is composed by '#' and any other character, valid characters
	 * entered are placed in place of '#' and other characters in the mask are
	 * cloned in the correct position, also, mask limits the size.
	 *
	 * @param charSetRegex
	 * @param mask
	 * @return TextFormatter.Change
	 */
	public static TextFormatter<Object> getTextFieldMaskFormatter(String charSetRegex, String mask) {
		return new TextFormatter<>((TextFormatter.Change c) -> {

			return formatByCharsetAndMask(c, charSetRegex, mask);
		});

	}

	/**
	 * Validates input for Phone number with mask (00) 000000000 or (00) 00000000
	 *
	 * @return TextFormatter.Change
	 */
	public static TextFormatter<Object> getTextFieldPhoneDDDAndNumberFormatter() {

		// throw new UnsupportedOperationException("Not concluded yet");

		return new TextFormatter<>((TextFormatter.Change change) -> {

			String newText = change.getControlNewText();

			if (newText.matches("((^\\((\\d{2})(\\))\\s(\\d{8,9})))"))
				return change;
			else {
				return formatByCharsetAndMask(change, "\\d*", "(##) #########");
			}
			/*
			 * String newText = change.getControlNewText();
			 * 
			 * // Text is not changed if (!change.isContentChange()) {
			 * 
			 * return change; }
			 * 
			 * // Text is being completely deleted if (newText.isEmpty()) { return change; }
			 * 
			 * if (newText.length() == 1) {
			 * 
			 * if (newText.matches("(^\\()")) return change;
			 * 
			 * if (newText.matches("(^\\d{1})")) { change.setText("(" + change.getText());
			 * 
			 * change.setCaretPosition(change.getCaretPosition() + 1);
			 * change.selectRange(change.getCaretPosition(), change.getCaretPosition());
			 * return change; } }
			 * 
			 * if (newText.length() >= 2 && newText.length() <= 3) {
			 * 
			 * if (newText.matches("(^\\(\\d{1,2})")) return change;
			 * 
			 * if (newText.matches("(^\\d{1,2})")) {
			 * 
			 * change.setText("(" + change.getText());
			 * 
			 * change.setCaretPosition(change.getCaretPosition() + 1);
			 * change.selectRange(change.getCaretPosition(), change.getCaretPosition());
			 * return change; } }
			 * 
			 * if (newText.length() == 4) {
			 * 
			 * if (newText.matches("(^\\(\\d{2}\\))")) return change;
			 * 
			 * if (newText.matches("(^\\(\\d{3})")) { change.setText((newText.substring(0,
			 * 2) + ") " + newText.substring(3)).substring(2));
			 * change.setCaretPosition(change.getControlNewText().length());
			 * change.selectRange(change.getCaretPosition(), change.getCaretPosition());
			 * 
			 * return change; } }
			 * 
			 * return null;
			 */
		});

	}

	/**
	 * Validates TextFormatter.Change per character entered according to
	 * charSetRegex and prevents insertion beyond the max limit.
	 * _
	 * @param charSetRegex
	 * @param max
	 * @return TextFormatter.Change
	 */
	private static TextFormatter.Change formatByCharset(TextFormatter.Change change, String charSetRegex, int max) {

		String text = change.getText();

		if (!change.isContentChange()) {
			return change;
		}

		if (change.getAnchor() > max) {
			return null;
		}

		if (!change.isDeleted() && (change.getControlText().length() == max)) {
			change.setRange(change.getRangeStart(), change.getRangeEnd() + 1);
			return change;

		}
		if (text.matches(charSetRegex) || text.isEmpty()) {
			return change;
		}

		return null;
	}

	/**
	 * Validates TextFormatter.Change instance per character entered according to
	 * charSetRegex and mask, the mask is composed by '#' and any other character,
	 * valid characters entered are placed in place of '#' and other characters in
	 * the mask are cloned in the correct position, also, mask limits the size.
	 *
	 * @param change
	 * @param charSetRegex
	 * @param mask
	 * @return TextFormatter.Change
	 */
	private static TextFormatter.Change formatByCharsetAndMask(TextFormatter.Change change, String charSetRegex,
			String mask) {

		String text = change.getText();

		if (!change.isContentChange()) {
			return change;
		}

		if (text.isEmpty()) {
			return change;
		}

		if (!change.isDeleted() && change.getRangeEnd() < mask.length() || change.isReplaced()) {

			if (change.getControlText().length() == mask.length() && !change.isReplaced()) {
				change.setRange(change.getRangeStart(), change.getRangeEnd() + 1);
			}

			if (mask.charAt(change.getRangeStart()) == '#' && text.matches(charSetRegex)) {
				return change;
			}

			if (mask.charAt(change.getRangeStart()) != '#' && text.equals("" + mask.charAt(change.getRangeStart()))) {
				return change;
			}

			// if the entry takes the place of a fixed character
			if (text.matches(charSetRegex)) {

				int index = change.getRangeStart();
				int count = 0;

				// the while statement will cover if there are two or
				// more sequential fixed characters in the mask
				change.setText("");
				do {
					change.setText(change.getText() + mask.charAt(index));
					index++;
					count++;
				} while (index < mask.length() && mask.charAt(index) != '#');

				change.setText(change.getText() + text);

				// you have to ask the same question
				// since the new text is changed
				if (change.getControlText().length() == mask.length() && !change.isReplaced()) {
					change.setRange(change.getRangeStart(), change.getRangeEnd() + count);
				}

				change.selectRange(change.getAnchor() + count, change.getCaretPosition() + count);
				return change;
			}

		}

		if (change.getText().length() == mask.length()) {

			boolean flag = true;

			for (int i = 0; i < mask.length() && flag; i++) {
				if (mask.charAt(i) == '#') {
					flag = ("" + change.getText().charAt(i)).matches(charSetRegex);
				} else {
					flag = change.getText().charAt(i) == mask.charAt(i);
				}
			}

			if (flag) {
				return change;
			}
		}

		return null;
	}
}
