package com.mhra.mcm.appian.session;

/**
 * Will be used in ScenarioSession
 * 
 * Helps with maintaining data between steps
 * 
 * @author TPD_Auto
 *
 */
public class SessionKey {
	//Current env and scenario
	public static String environment;
	public static String scenarioName;

	public static final String ECID = "EC ID";
	public static String storedValue = "StoredValue";

}
