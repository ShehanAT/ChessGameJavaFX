package application;

import javafx.scene.control.Skin; // this is a interface 
import javafx.scene.control.SkinBase; // this is a abstract class 


public class ControlSkin extends SkinBase<CustomControl> implements Skin<CustomControl>{
	public ControlSkin(CustomControl customControl) {
		super(customControl); // this calls the super class constructor(CustomControl's constructor)
	}
}
