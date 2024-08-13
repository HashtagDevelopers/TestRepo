package LibraryFiles;


import java.util.List;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

public class UtilsClass {
public static void compareTwoList(List<String> actLst,List<String> expLst,SoftAssert soft ) {
	Reporter.log("expLst= "+expLst.toString(), true);
	Reporter.log("actLst= "+actLst.toString(), true);	
	for (int i = 0; i < actLst.size(); i++) {
		String ActEle = actLst.get(i);
		String ExpEle= expLst.get(i);
		soft.assertEquals(ActEle, ExpEle, "element at index" + i + "Dont Matched");
	}
	soft.assertEquals(actLst, expLst, "List data not matched");
	
}
}
