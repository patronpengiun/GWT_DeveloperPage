package devPage.client;

public class SessionInfo {
	private String signature;
	private String devId;
	private String devName;

	private SessionInfo(){
		signature = "";
		devId = "";
		devName = "";
	}
	
	private static SessionInfo info;
	
	public static SessionInfo getSessionInfo(){
		if (info == null)
			info = new SessionInfo(); 
		return info;
	}
	
	public void setSignature(String sig){
		signature = sig;
	}
	
	public String getSignature(){
		return signature;
	}
	
	public void setDevId(String id){
		devId = id;
	}
	
	public String getDevId(){
		return devId;
	}
	
	public void setDevName(String name){
		devName = name;
	}
	
	public String getDevName(){
		return devName;
	}
}
