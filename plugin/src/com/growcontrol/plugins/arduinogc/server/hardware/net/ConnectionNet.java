package com.growcontrol.plugins.arduinogc.server.hardware.net;

import com.growcontrol.plugins.arduinogc.server.hardware.ArduinoConnection;
import com.growcontrol.plugins.arduinogc.server.hardware.serial.ConnectionSerial;
import com.poixson.commonjava.Utils.utils;
import com.poixson.commonjava.Utils.xHashable;


public class ConnectionNet extends ArduinoConnection {

	// socket info
	protected final String host;
	protected final int    port;
	public final String key;

	// netty socket
//TODO:

	private volatile boolean stopping = false;



	public ConnectionNet(final String host, final int port) {
		if(utils.isEmpty(host)) throw new NullPointerException("host argument is required!");
		this.host = host;
		this.port = port;
		this.key = this.genKey();

//TODO:

		log().info("Connecting to.. "+this.key);
	}



	@Override
	public void close() {
		this.stopping = true;
	}
	@Override
	public boolean isClosed() {
		return this.stopping;
	}



	@Override
	public String toString() {
		return this.key;
	}
	@Override
	public String getKey() {
		return this.key;
	}
	private String genKey() {
		return (new StringBuilder())
				.append(this.host)
				.append(':')
				.append(this.port)
				.toString();
	}
	@Override
	public boolean matches(final xHashable hashable) {
		if(hashable == null || !(hashable instanceof ConnectionSerial))
			return false;
		return this.getKey().equalsIgnoreCase(hashable.getKey());
	}



}
