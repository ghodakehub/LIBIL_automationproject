package Libil.Utility;

public class ConfingDataProvider {
	public static String URL= ConfigReader.getConfigData("URL");
	public static String Email= ConfigReader.getConfigData("Email");
	public static String Password = ConfigReader.getConfigData("Password");
	public static String Browser = ConfigReader.getConfigData("Browser");
	public static String Headless = ConfigReader.getConfigData("Headless");
}
