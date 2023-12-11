package subsystem.interbank;

import common.exception.UnrecognizedException;
import utils.ApplicationProgramingInterface;

public class InterbankBoundary {

	
	/** 
	 * @param url
	 * @param data
	 * @return String
	 */
	String query(String url, String data) {
		String response = null;
		try {
			response = ApplicationProgramingInterface.post(url, data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new UnrecognizedException();
		}
		return response;
	}

}
