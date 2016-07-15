package com.mhra.mcm.appian.utils.reporter;

import net.masterthought.cucumber.sandwich.CucumberReportMonitor;

import java.io.File;
import java.util.Date;

/**
 * 
 * IF YOUR USING JENKINS THAN DON'T USE THIS
 * 
 * USE JENKINS PLUGINS FROM MASTERTHOUGHT
 * 
 * This will be automatically run once before starting the tests
 * This will monitor the specified folders and generate tests automatically
 * 
 * assumptions:
 * 	input folder is the target folder

 * @author Noor
 *
 */
public class CreatePrettyReport {

	public static void main(String[] args) {
		CreatePrettyReport cpr = new CreatePrettyReport();
		cpr.monitorFolder("Pretty");
	}

	/**
	 * Monitors folder for changes and than generates pretty reports
	 */
	public void monitorFolder(String outFolderName) {

		if(outFolderName == null){
			outFolderName = "PrettyReport";
		}
		//Where to put pretty reports
		String res = new File("").getAbsolutePath();
	
		String [] aaa = new String[4];
		aaa[0] = "-f";
		aaa[1] = res + File.separatorChar + "target" ;
		aaa[2] = "-o";
		String outFile = res + File.separatorChar + "target" + File.separatorChar + outFolderName + File.separatorChar + new Date().toString().replace(":", "").substring(0,16).replace(" ", "");
		
		//Create folder
		File f = new File(outFile);
		f.mkdirs();
		aaa[3] = outFile;
		try {
			CucumberReportMonitor.main(aaa);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}





//	private void generateReports() {
//		File reportOutputDirectory = new File("target");
//		List<String> list = new ArrayList<String>();
//		list.add("test-report.json");
//		list.add("cucumber-report2.json");
//
//		String pluginUrlPath = "";
//		String buildNumber = "1";
//		String buildProject = "cucumber-jvm";
//		boolean skippedFails = true;
//		boolean pendingFails = true;
//		boolean undefinedFails = true;
//		boolean missingFails = true;
//		boolean flashCharts = true;
//		boolean runWithJenkins = false;
//		boolean artifactsEnabled = false;
//		String artifactConfig = "";
//		boolean highCharts = false;
//		boolean parallelTesting = false;
//
//		ReportBuilder reportBuilder;
//		try {
//			reportBuilder = new ReportBuilder(list, reportOutputDirectory, pluginUrlPath, buildNumber,
//			    buildProject, skippedFails, pendingFails, undefinedFails, missingFails, flashCharts, artifactConfig, highCharts);
//			reportBuilder.generateReports();
//		} catch (VelocityException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	
}
