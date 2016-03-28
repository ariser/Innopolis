import Lamp.AdjustableLamp;
import Lamp.Lamp;
import Lamp.LightBulbs.FluorescentLightBulb;
import Lamp.LightBulbs.IncandescentLightBulb;
import Lamp.LightBulbs.LEDLightBulb;
import Lamp.Switches.DimmerSwitch;
import Lamp.Switches.ToggleSwitch;

public class Main {
	public static void main(String[] args) {
		Lamp incandescentLamp = new Lamp(new IncandescentLightBulb(), new ToggleSwitch());
		Lamp fluorescentLamp = new Lamp(new FluorescentLightBulb(FluorescentLightBulb.ColorRendering.Basic), new ToggleSwitch());
		AdjustableLamp LEDLamp = new AdjustableLamp(new LEDLightBulb(), new DimmerSwitch());
	}
}
