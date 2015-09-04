package com.growcontrol.plugins.arduinogc.server.hardware.serialcontrol;

import com.poixson.commonjava.Utils.utils;


public class ArduinoPin {


	protected volatile int     number;
	protected volatile PinMode mode;
	protected volatile int     state = 0;



//	public ArduinoPin() {
//		this(-1, null);
//	}
	public ArduinoPin(final int number, final PinMode mode) {
		if(number < 0) throw new IllegalArgumentException("Pin number out of range! Cannot be less than 0.");
		if(mode == null) throw new NullPointerException("Pin mode argument is required!");
		this.number = number;
		this.mode = mode;
	}



	public void setMode(final String modeStr) {
		if(utils.isEmpty(modeStr)) throw new NullPointerException("mode argument is required!");
		final PinMode mode = PinMode.fromString(modeStr);
		if(mode == null) throw new IllegalArgumentException("Unknown pin mode: "+modeStr);
		this.setMode(mode);
	}
	public void setMode(final PinMode mode) {
		if(mode == null) throw new NullPointerException("mode argument is required!");
		this.mode = mode;
	}



	public void setState(final String value) {
		final String stateStr = value
				.trim()
				.toLowerCase();
		switch(stateStr) {
		case "on":
		case "high":
			this.setState(1);
			break;
		case "off":
		case "low":
			this.setState(0);
			break;
		default:
			this.setState(
					Integer.valueOf(value)
			);
			break;
		}
	}
	public void setState(final int value) {
		this.state = value;
	}



}
