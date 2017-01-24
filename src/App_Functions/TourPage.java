package App_Functions;

import BugRegressionSuite.BaseTestBugRegression;
import Page_Objects.BugRegressionAppConstants;
//import Utility.UserDefined;
import Page_Objects.SanitySuiteAppConstants;

public class TourPage extends BaseTestBugRegression {
	static String a1;

	public static void tourSkip() {
		try {
			if (packName.contains("Functional_Scenarios")) {
				clickId(SanitySuiteAppConstants.Lets_Go_Shopping_Id);
			} else {
				clickId(BugRegressionAppConstants.Lets_Go_Shopping_Id);
			}

		} catch (Exception e) {
			e.printStackTrace();
			// UserDefined.takeSnapShot("D:\\takeScreenshots",
			// "gmailLogin function");
			throw (e);
		}
	}

	@Override
	public void executeTestCase() throws Exception {
		// TODO Auto-generated method stub

	}

	public static void recursionString() {
		int fact=1 ,no=4;
		for(int i=1;i<=no;i++){
			System.out.println(fact=fact*i);
		}

	}

	public static void main(String args[]) {
		recursionString();

	}

}
