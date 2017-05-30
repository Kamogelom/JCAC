package telecomms;
/**
 * 
 * class Group specifies a user group 
 * and the RATs this user can access
 * 
 * */
public class Group {
	private RAT[] rats;
	
	public Group(RAT[] rat){
		this.rats = rat;
	}
	
	public Group(RAT rat){
		
		this.rats = new RAT[]{rat};
	}
	
	public RAT[] getRats() {
		return rats;
	};
	
}
