package telecomms;
import java.util.ArrayList;
/**
 * Class RAT specifies a RAT in terms of:
 * its name, capacity, threshold and the class of services it supports
 * */

public class RAT {
	private String name;
	private int capacity;
	private int threshold;
	private ArrayList <Type> types = new ArrayList <Type>() ;
	
	public RAT(int cap,int thr, String N){
		this.name = N;
		this.capacity = cap;
		this.threshold = thr;
		this.types.add(new Type("voice"));
		if(N.equals("UMTS")){
			this.types.add(new Type("video"));
		}
	}
	
	public String getName(){
		return this.name;
	}
	
	public ArrayList<Type> getType(){
		return this.types;
	}
	
	public int getCapacity(){
		return this.capacity;
	}
	
	public void setCapacity(int c){
		this.capacity = c;
	}
	
	public int getThreshold(){
		return this.threshold;
	}
	
	public void setThreshold(int t){
		this.threshold = t;
	}
 	class Type {
 		private int bbu_voice = 2;
 		private int bbu_video = 4;
		private String name;
		
		public Type(String name){
			this.name = name;
		}
		
		public int getBBU(String name){
			if(name.equals("voice")){return this.bbu_voice;}
			else if (name.equals("video")){return this.bbu_video;}
			else {return 1;}
		}
		
		public void setBBU(String name,int bbu){
			if(name.equals("voice")){this.bbu_video = bbu;}
			else if (name.equals("video")){this.bbu_video = bbu;}
		}
	}


}
