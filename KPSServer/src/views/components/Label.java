package views.components;

import java.awt.Color;
import java.awt.Font;

import rabe_studios.components.designs.LabelDesign;
import rabe_studios.components.labels.BaseLabel;

@SuppressWarnings("serial")
public class Label extends BaseLabel{

	public Label(String text, int fontSize) {
		super(new LabelDesign(text, new Font("Cooper Black", Font.PLAIN, fontSize), new Color(92, 86, 118)));
	}

}
