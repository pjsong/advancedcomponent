package ruking.velocity;

import org.apache.velocity.app.VelocityEngine;

public class VelocityParserFactory {
	private static VelocityParser vp = null;
	public static VelocityParser getVP(){
		if(vp == null){
			vp = new VelocityParser();
		}
		return vp;
	}
}
