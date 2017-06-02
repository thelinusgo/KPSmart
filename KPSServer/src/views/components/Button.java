package views.components;

import java.awt.Color;
import java.awt.Font;

import rabe_studios.components.buttons.BaseButton;
import rabe_studios.components.designs.ButtonColorPallet;
import rabe_studios.components.designs.ButtonDesign;

@SuppressWarnings("serial")
public class Button extends BaseButton{
	private String text;
	private int width;
	private int height;
	
	public Button(String text, int width, int height){
		this.text = text;
		this.width = width;
		this.height = height;
	}
	

	@Override
	protected ButtonDesign createButtonDesign() {
		return new ButtonDesign(this.text, this.width, this.height, new Font("Cooper Black", Font.PLAIN, 10), 2, true, null);
	}

	@Override
	protected ButtonColorPallet createColorPallet() {
		return new ButtonColorPallet(Color.WHITE, Color.WHITE, new Color(255, 227, 179),  Color.BLACK, new Color(203, 231, 224), Color.BLACK);
	}

}
