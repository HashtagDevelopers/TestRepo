package LibraryFiles;

import java.util.ArrayList;
import java.util.Arrays;

public class Placeholders {
	public static ArrayList<String> getActPlaceholderList(String Department) {
		ArrayList<String> actPlaceholderList = new ArrayList<String>();

		switch (Department) {

		case "Maintenance":
			actPlaceholderList.addAll(Arrays.asList("Specify Counts", "Specify Counts", "Specify Counts",
					"Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts",
					"Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts",
					"Specify Counts", "Type 1 For Done", "Type 1 For Done", "Type 1 For Done", "Type 1 For Done",
					"Specify Counts", "Specify Counts", "Specify Duration in Mins", "Type 1 For Done", "Specify Tasks",
					"Specify in Brief"));
			break;
		case "Care Support":
			actPlaceholderList.addAll(Arrays.asList("Specify Counts", "Specify Counts", "Specify Counts",
					"Specify Counts", "Specify Counts", "Type 1 For Done", "Type 1 For Done", "Type 1 For Done",
					"Specify Counts", "Specify Counts", "Specify Duration in Mins", "Type 1 For Done", "Specify Tasks",
					"Specify In Brief"));
			break;
		case "IT":
			actPlaceholderList.addAll(Arrays.asList("Specify Counts", "Specify Counts", "Specify Counts",
					"Specify Counts", "Type 1 For Done", "Type 1 For Done", "Type 1 For Done", "Type 1 For Done",
					"Type 1 For Done", "Specify Task", "Specify Counts", "Specify Counts", "Type 1 For Done",
					"Type 1 For Done", "Type 1 For Done", "Type 1 For Done", "Type 1 For Done", "Type 1 For Done",
					"Type 1 For Done", "Type 1 For Done", "Type 1 For Done", "Specify Counts", "Type 1 For Done",
					"Type 1 For Done", "Type 1 For Done", "Type 1 For Done", "Specify Site Count", "Specify Counts",
					"Specify Counts", "Type 1 For Done", "Specify Counts", "Specify Counts", "Specify Duration in Mins",
					"Type 1 For Done", "Specify Tasks", "Specify In Brief"));
			break;
		case "Food":
			actPlaceholderList.addAll(Arrays.asList("Specify Counts", "Type 1 For Done", "Specify Counts",
					"Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts",
					"Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts",
					"Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts",
					"Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts",
					"Specify Counts", "Specify No. Of Days", "Specify Counts", "Specify Counts", "Specify Counts",
					"Specify No. Of Days", "Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts",
					"Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts",
					"Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts",
					"Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts",
					"Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts",
					"Specify Counts", "Specify Counts", "Type 1 For Done", "Type 1 For Done", "Type 1 For Done",
					"Type 1 For Done", "Specify Week Counts", "Type 1 For Done", "Specify Caterer Counts",
					"Specify Caterer Counts", "Type 1 For Done", "Specify Counts", "Specify Counts", "Specify Counts",
					"Type 1 For Done", "Type 1 For Done", "Type 1 For Done", "Type 1 For Done", "Type 1 For Done",
					"Specify Counts", "Specify Counts", "Specify No. Of Days", "Type 1 For Done", "Specify Sites Count",
					"Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts",
					"Specify Counts", "Specify Counts", "Specify Duration in Mins", "Type 1 For Done", "Specify Counts",
					"Specify Task", "Specify In Brief"));
			break;
		case "Data Analyst":
			actPlaceholderList.addAll(Arrays.asList("Specify Counts", "Specify Counts", "Specify Counts",
					"Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts",
					"Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts",
					"Specify Counts", "Specify Counts", "Type 1 For Done", "Type 1 For Done", "Type 1 For Done",
					"Specify Number Of Days", "Type 1 For Done", "Type 1 For Done", "Type 1 For Done", "Type 1 For Done",
					"Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts",
					"Specify Counts", "Specify Counts", "Specify Counts", "Type 1 For Done", "Type 1 For Done",
					"Type 1 For Done", "Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts",
					"Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts",
					"Type 1 For Done", "Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts",
					"Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts",
					"Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts",
					"Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts",
					"Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts",
					"Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts",
					"Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts",
					"Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts", "Specify Duration in Mins",
					"Type 1 For Done", "Specify Task", "Specify in Brief"));
			break;
		case "Incident":
			actPlaceholderList.addAll(Arrays.asList("Specify Counts", "Specify Counts", "Specify Counts",
					"Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts",
					"Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts",
					"Specify No. Of Days", "Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts",
					"Specify Counts", "Specify Counts", "Specify Counts", "Specify No. Of Days", "Specify No. Of Days",
					"Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts",
					"Specify Duration in Mins", "Type 1 For Done", "Specify Counts", "Specify Task", "Specify In Brief"));
			break;
		case "Info":
			actPlaceholderList.addAll(Arrays.asList("Specify Counts", "Specify Counts", "Specify Counts",
					"Specify Counts", "Specify No. Of Days", "Specify Counts", "Specify Email Counts", "Specify Counts",
					"Specify Counts", "Specify Counts", "Specify Email Counts", "Specify Individual Counts",
					"Specify Hotels Counts", "Specify Email Counts", "Specify Counts", "Specify Counts",
					"Specify Counts", "Specify Counts", "Specify Counts", "Specify Site Counts", "Specify Site Counts",
					"Specify Counts", "Specify Counts", "Specify Counts", "Type 1 For Done", "Type 1 For Done",
					"Type 1 For Done", "Type 1 For Done", "Type 1 For Done", "Type 1 For Done", "Type 1 For Done",
					"Type 1 For Done", "Type 1 For Done", "Type 1 For Done", "Type 1 For Done", "Type 1 For Done",
					"Type 1 For Done", "Type 1 For Done", "Type 1 For Done", "Type 1 For Done", "Type 1 For Done",
					"Type 1 For Done", "Type 1 For Done", "Type 1 For Done", "Type 1 For Done", "Type 1 For Done",
					"Type 1 For Done", "Type 1 For Done", "Type 1 For Done", "Specify Counts", "Specify Counts",
					"Specify Duration in Mins", "Type 1 For Done", "Specify Counts", "Specify Task", "Specify In Brief"));
			break;
		case "MPR":
			actPlaceholderList.addAll(Arrays.asList("Specify Counts", "Specify Counts", "Specify Counts",
					"Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts",
					"Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts",
					"Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts",
					"Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts",
					"Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts",
					"Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts",
					"Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts",
					"Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts",
					"Specify Counts", "Specify Counts", "Type 1 For Done", "Type 1 For Done", "Type 1 For Done",
					"Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts", "Specify Duration in Mins",
					"Type 1 For Done", "Specify Task", "Specify In Brief"));
			break;
		case "Laundry":
			actPlaceholderList.addAll(Arrays.asList("Specify Counts", "Specify Counts", "Specify Counts",
					"Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts",
					"Specify Counts", "Specify Counts", "Specify Counts", "Type 1 For Done", "Type 1 For Done",
					"Specify Counts", "Specify Counts", "Specify No. Of Days", "Specify Counts", "Specify Counts",
					"Specify Duration in Mins", "Type 1 For Done", "Specify Counts", "Specify Task",
					"Specify in Brief"));
			break;
		case "Security":
			actPlaceholderList.addAll(Arrays.asList("Specify Counts", "Specify Counts", "Specify Counts",
					"Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts",
					"Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts",
					"Type 1 For Done", "Type 1 For Done", "Specify Counts", "Specify Counts", "Specify Counts",
					"Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts", "Specify No. Of Days",
					"Specify Counts", "Specify Counts", "Specify Duration in Mins", "Type 1 For Done", "Specify Counts",
					"Specify Task", "Specify In Brief"));
			break;
		case "Training":
			actPlaceholderList
					.addAll(Arrays.asList("Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts",
							"Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts",
							"Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts",
							"Specify Counts", "Specify Counts", "Specify Counts", "Specify Task", "Specify Counts",
							"Specify Counts", "Type 1 For Done", "Type 1 For Done", "Specify Counts", "Specify Counts",
							"Specify Duration in Mins", "Type 1 For Done", "Specify Task", "Specify in Brief"));
			break;
		case "Procurement":
			actPlaceholderList.addAll(Arrays.asList("Specify Counts", "Specify Items Count", "Specify Order Count",
					"Specify Item Count", "Specify Order Count", "Specify Counts", "Specify Counts", "Specify Counts",
					"Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts",
					"Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts", "Specify Counts",
					"Specify Counts", "Type 1 For Done", "Specify Counts", "Specify Task", "Type 1 For Done",
					"Specify Counts", "Specify Counts", "Type 1 For Done", "Specify Counts", "Specify Counts",
					"Specify Counts", "Specify No. Of Days", "Type 1 For Done", "Specify Counts", "Specify Counts",
					"Specify Counts", "Specify Counts", "Specify Counts", "Specify Duration in Mins", "Type 1 For Done",
					"Specify Counts", "Specify Task", "Specify in Brief"));
			break;
		default:
			break;
		}
		return actPlaceholderList;
	}
}
