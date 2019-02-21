package it.vitalegi.ganttpptxrecap.bean.color;

import java.awt.Color;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Giorgio Vitale
 */
@Getter
@Setter
@ToString
public class ColorPaletteByName extends ColorPalette {

	private String code;

	@Override
	public Color getColor() {
		try {
			return (Color) Color.class.getField(code.toUpperCase()).get(null);
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			throw new RuntimeException("Errore nella generazione del colore [" + code + "]", e);
		}
	}
}
