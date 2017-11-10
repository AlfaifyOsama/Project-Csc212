
public class SubtitleIm implements Subtitle{
	/*
	 * Subtitle Contains 4 elements: Seq Number,StartTime,EndTime and Text
	 * Requirements:
	 * Time Format Must be Correct (Where to implement it ?)
	 * 
	 */
	
	//attribute:
	private Time StartTime;
	private Time EndTime;
	private String Text;
	private int sequence; //might not be needed
	
	
	//Constructor
	public SubtitleIm(Time startTime, Time endTime, String text, int sequence) {
		StartTime = startTime;
		EndTime = endTime;
		Text = text;
		this.sequence = sequence;
	}
	


	public void setStartTime(Time startTime) {
		StartTime=startTime;
		
	}

	public void setEndTime(Time endTime) {
		EndTime=endTime;
		
	}

	public void setText(String text) {
		Text=text;
		
	}

	public Time getStartTime() {
		// TODO Auto-generated method stub
		return StartTime;
	}

	public Time getEndTime() {
		// TODO Auto-generated method stub
		return EndTime;
	}

	public String getText() {
		// TODO Auto-generated method stub
		return Text;
	}

}
