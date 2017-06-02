package views.components;

import java.awt.Color;
import java.awt.Font;

import rabe_studios.components.designs.TextComponentDesign;
import rabe_studios.components.texts.BaseTextField;

@SuppressWarnings("serial")
public class TextField extends BaseTextField {

	public TextField(int width, int height, String text, int fontSize) {
		super(new TextComponentDesign(height, width, new Font("Cooper Black", Font.PLAIN, fontSize), true, new Color(255, 227, 179), new Color(203, 231, 224), new Color(92, 86, 118), new Color(92, 86, 118)));
	}

}
