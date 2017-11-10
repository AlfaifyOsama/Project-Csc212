
public class TimeIm implements Time {

	private int HH;
	private int MM;
	private int SS;
	private int MS;
	
	/*
	 * Setters and getters might need some validation later
	 * 
	 * 
	 * 
	 * (non-Javadoc)
	 * @see Time#getHH()
	 */
	
	//Constructor
	
	public TimeIm(int hH, int mM, int sS, int mS) { //Used to Create a "Time" Object

		HH = hH;
		MM = mM;
		SS = sS;
		MS = mS;
	}
	
	public int getHH() {
		return HH;
	}
	public void setHH(int hH) {
		HH = hH;
	}
	public int getMM() {
		return MM;
	}
	public void setMM(int mM) {
		MM = mM;
	}
	public int getSS() {
		return SS;
	}
	public void setSS(int sS) {
		SS = sS;
	}
	public int getMS() {
		return MS;
	}
	public void setMS(int mS) {
		MS = mS;
	}




}
