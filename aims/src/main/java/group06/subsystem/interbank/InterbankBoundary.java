package group06.subsystem.interbank;

import group06.common.exception.UnrecognizedException;
import group06.utils.API;

public class InterbankBoundary {

	//Sequential cohesion do nhận output từ hàm InterbankSubsystemController.generateData
	String query(String url, String data) {
		String response = null;
		try {
			response = API.post(url, data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new UnrecognizedException();
		}
		return response;
	}

}
