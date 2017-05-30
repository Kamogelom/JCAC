package telecomms;

public class Driver {
	public static void main(String[] args){

		int list [] = {1,2,4,6,8,16};
		//for ( int i:list){
			
		
		RAT GSM = new RAT(16,12,"GSM");
		RAT UMTS =new RAT(20,16,"UMTS");
		RAT[] rats = {UMTS,GSM}; 
		
	//	UMTS.getType().get(0).setBBU("video", i);
		
		int cap1 = GSM.getCapacity();
		int thr1 = GSM.getThreshold();
		int cap2 = UMTS.getCapacity();
		int thr2 = UMTS.getThreshold();
		System.out.println("\n\ncapacity GSM: "+cap1);
		System.out.println("Threshold GSM:" + thr1);
		System.out.println("capacity UMTS: "+cap2);
		System.out.println("Threshold UMTS:" + thr2);
		
		Group A = new Group(GSM);
		Group B = new Group(rats);
		
		
		System.out.println("____________________________________________");
		//System.out.println(i);

		float bp = calcBlockingProb(A,"voice");
		System.out.println("Blocking probability A voice: "+bp);
		
		System.out.println("\nBlocking probability of group B voice:");
		float bp1 = calcBlockingProb(B,"voice");
		System.out.println("Blocking probability: "+bp1);
		
		System.out.println("\nBlocking probability of group B video:");
		float bp2 = calcBlockingProb(B,"video");
		System.out.println("Blocking probability: "+bp2);
		System.out.println("-------------------------------");

		System.out.println("\nDropping probability of group A voice:");
		float dp1 = calcDroppingProb(A,"voice");
		System.out.println("Dropping probability: "+dp1);
				System.out.println("Blocking probability of group A voice:");
		System.out.println("\nDropping probability of group B voice:");
		float dp2 = calcDroppingProb(B,"voice");
		System.out.println("Dropping probability: "+dp2);
		
		System.out.println("\nDropping probability of group B video:");
		float dpv = calcDroppingProb(B,"video");
		System.out.println("Dropping probability: "+dpv);
		//}
	}
	
	public static float calcBlockingProb(Group T,String callType){
		RAT[] rat = T.getRats();
		float possibleStates = 0;
		float blockingStates =  0;
		if (rat.length ==1 || 
				callType.equals("video")){

			int cap = rat[0].getCapacity();
			int thr = rat[0].getThreshold();
			int bbu = rat[0].getType().get(0).getBBU(callType);
			for (int i = 0;  i<=cap; i+= bbu){
				for (int j = 0; j<= thr;j+= bbu){
					if (i+j <= cap){
						possibleStates++;
						if (i+j >= thr){blockingStates++;}
					}
					
					
					
				}
			}
		}else if(rat.length ==2){
			String rat1 =rat[0].getName();
			String rat2 =rat[1].getName();
			
			int cap1 = rat[0].getCapacity();
			int thr1 = rat[0].getThreshold();
			
			int index1 = rat1.equals("GSM") ? 0:1;
			int bbu1 = rat[0].getType().get(index1).getBBU(callType);
			
			int cap2 = rat[1].getCapacity();
			int thr2 = rat[1].getThreshold();
			int index2 = rat2.equals("UMTS") ? 1:0;
			int bbu2 = rat[1].getType().get(index2).getBBU(callType);
			
			for (int i = 0;  i <= cap1; i+=bbu1 ){
				for (int j = 0; j<= thr1; j+=bbu1){
					for (int k = 0; k<= cap2; k+=bbu2){
						for (int l = 0; l<= thr2; l+=bbu2){
							if (((i+j) <= cap1) && ((k+l) <= cap2) ){
								possibleStates++;
								if (((i+j+bbu1) > thr1) && ((k+l+bbu2) > thr2)){blockingStates++;}
							}
						}
					}
					
				}
			}
			
		}
		
		
		System.out.println("Admissible states: "+possibleStates);
		System.out.println("Blocking States: "+blockingStates);
		return blockingStates/possibleStates;
	}
	

	
	public static float calcDroppingProb(Group T,String callType){
		RAT[] rat = T.getRats();
		float possibleStates = 0;
		float droppingStates =  0;
		if (rat.length ==1 || 
				callType.equals("video")){ //video and GSM can only access 1 RAT 
			
			int cap = rat[0].getCapacity();
			int thr = rat[0].getThreshold();
			int bbu = rat[0].getType().get(0).getBBU(callType);
			for (int i = 0;  i<=cap; i+= bbu){
				for (int j = 0; j<= thr;j+= bbu){
					if (i+j <= cap){
						possibleStates++;
						if (i+j+bbu > cap){droppingStates++;}// if it can't take the next call
					}
					
					
					
				}
			}
		}else if(rat.length ==2){
			String rat1 =rat[0].getName();
			String rat2 =rat[1].getName();
			
			int cap1 = rat[0].getCapacity();
			int thr1 = rat[0].getThreshold();
			
			int index1 = rat1.equals("GSM") ? 0:1;
			int bbu1 = rat[0].getType().get(index1).getBBU(callType);
			
			int cap2 = rat[1].getCapacity();
			int thr2 = rat[1].getThreshold();
			int index2 = rat2.equals("UMTS") ? 1:0;
			int bbu2 = rat[1].getType().get(index2).getBBU(callType);
			
			for (int i = 0;  i <= cap1; i+=bbu1 ){
				for (int j = 0; j<= thr1; j+=bbu1){
					for (int k = 0; k<= cap2; k+=bbu2){
						for (int l = 0; l<= thr2; l+=bbu2){
							if (((i+j) <= cap1) && ((k+l) <= cap2) ){
								possibleStates++;
								if (((i+j+bbu1) > cap1) && ((k+l+bbu2) > cap2)){droppingStates++;}
							}
						}
					}
					
				}
			}
			
		}
		System.out.println("Admissible states: "+possibleStates);
		System.out.println("Dropping States "+droppingStates);
		return droppingStates/possibleStates;
		
	}

}
