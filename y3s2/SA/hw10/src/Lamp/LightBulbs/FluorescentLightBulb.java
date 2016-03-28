package Lamp.LightBulbs;

import Lamp.LightBulb;

public class FluorescentLightBulb extends LightBulb {
	private ColorRendering colorRendering;

	public FluorescentLightBulb(ColorRendering colorRendering) {
		this.colorRendering = colorRendering;
	}

	public enum ColorRendering {
		Basic, Lumilux, LumiluxDeluxe
	}
}
