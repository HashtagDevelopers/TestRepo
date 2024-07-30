package com.hashTagMIS.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import org.testng.annotations.Test;
import LibraryFiles.BaseClass;

public class DevTC extends BaseClass {

	@Test(enabled = true, dataProvider = "DevTask", dataProviderClass = DataProviders.DataSupplierDevTask.class)
	public void adminLogin(String id, String task, String count) throws IOException, InterruptedException {
		ArrayList<String> al = new ArrayList<String>();
//al.addAll(Arrays.asList(null));
		String t = task;

		String t1 = "";
		for (int i = 0; i <= t.length() - 1; i++) {
			if (t.charAt(i) != ' ' && t.charAt(i) != '-' && t.charAt(i) != '\'' && t.charAt(i) != '/') //
			{
				t1 = t1 + t.charAt(i);
			}
		}
		// Remove Bracket from short Form
		String[] f = task.split("\\(");
		String[] ss = t1.split("\\(");
		// Krunal
		System.out.println("\"" + task.trim().toLowerCase() + "\",");
		// Jay
		// System.out.println(" { id: " + id + ", shortForm: \"" + t1 + "\", longForm:
		// \"" + task
		// + "\", count: \" \", placeholder: \"" + count + "\"},");
		// Davis1
		// System.out.println("\""+task+"\": \"$reports."+ss[0].trim()+"\",");
		// Davis2
		// System.out.println(ss[0].trim()+": "+"\"$$Kiran."+ss[0].trim()+"\",");
		// Davis3
		// System.out.println(ss[0].trim()+": Number,");
	}

	@Test(enabled = false)
	public void adminLogin123() throws IOException, InterruptedException {
		LocalDateTime currentDate = LocalDateTime.now();
		long currentTimeMillis = System.currentTimeMillis();
		int milliseconds = (int) (System.currentTimeMillis() % 1000); // Extract milliseconds (0-999)
		System.out.println(milliseconds);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss.SSS a");
		System.out.println(currentDate.format(formatter));
		while (true) {
			// Get current millisecond value using JavaScript (adjust if needed)
			if ((int) (System.currentTimeMillis() % 1000) == 100) {
				System.out.println("Button clicked at millisecond 1!");
				long currentTimeMillis1 = System.currentTimeMillis();
				int milliseconds1 = (int) (currentTimeMillis1 % 1000); // Extract milliseconds (0-999)
				System.out.println(milliseconds1);
				break; // Exit the loop after clicking
			}

			// Adjust sleep time based on your needs (consider shorter intervals)
			Thread.sleep(1); // Sleep for 10 milliseconds
		}

	}
}
