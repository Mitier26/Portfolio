package SWING2;

public class SaveAdmin {
	boolean isLogin = false;
	String id = "";
	String pw = "";
	
	public void save(boolean b, String id, String pw)
	{
		isLogin = b;
		this.id = id;
		this.pw = pw;
	}
	
}
