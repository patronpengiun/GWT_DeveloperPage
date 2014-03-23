package devPage.client;

public class SessionInfo {
	private String signature;

	private SessionInfo(){
		
	}
	
	private static SessionInfo info;
	
	public static SessionInfo getSessionInfo(){
		if (info == null)
			info = new SessionInfo(); 
		return info;
	}
}
