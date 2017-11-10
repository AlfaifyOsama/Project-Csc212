
public class SubtitleSeqIm implements SubtitleSeq {
	public LinkedList<Subtitle> sub;


	public SubtitleSeqIm() {
		sub = new LinkedList<Subtitle>();
	}




	public void addSubtitle(Subtitle st) {
		

		if (sub.full())
			return;
		if(sub.empty()){
			sub.insert(st);
			return;
		}
		Time1 given = (Time1) st.getStartTime();
		sub.findFirst();
		while(((Time1) sub.retrieve().getEndTime()).timeToMS() < given.timeToMS() && !sub.last()){
			sub.findNext();
		}
		if(((Time1) sub.retrieve().getEndTime()).timeToMS() < given.timeToMS()&& !!sub.empty())
			sub.findNext();
		sub.insert(st);
		}
	

	@Override
	public List<Subtitle> getSubtitles() {
		// TODO Auto-generated method stub
		if (!sub.empty()) { // you can't find first when the list is empty
			sub.findFirst(); // because of the requirement "Return all subtitles
								// in their chronological order."

		} // sub.empty must return a list even if the list is empty
		return sub;
	}

	@Override
	public Subtitle getSubtitle(Time time) {
		if (sub.empty())
			return null; // if the list is empty, return null ,requirment for
							// findFirst too
		sub.findFirst(); // need to start from the fist subtitle

		while (!sub.last()) {
			if (inTime((Time1)time)) // returns true if the given time match the time
								// in the current
				return sub.retrieve();
			sub.findNext();
		}
		if (inTime((Time1)time)) // checking the last element
			return sub.retrieve();
		return null;
	}

	private boolean inTime(Time1 time) { // created to make code cleaner and
										// might need to use the some method
										// again

		Time1 start =(Time1) sub.retrieve().getStartTime();
		Time1 end = (Time1)sub.retrieve().getEndTime();
		double startt = start.timeToMS(); 
		double endt = end.timeToMS();
		double givent = time.timeToMS();
		if (givent >= startt && givent < endt)
			return true;
		return false;
	}

	@Override
	public List<Subtitle> getSubtitles(Time startTime, Time endTime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Subtitle> getSubtitles(String str) {
		LinkedList<Subtitle> sub2 = new LinkedList<Subtitle>();
		if (!sub.empty()) {// so we can use findfirst
			sub.findFirst();// have to start from the first element
			while (!sub.last()) {
				if (sub.retrieve().getText().contains(str)) // contains return
															// true if the left
															// string have a
															// sub-string of the
															// right side
					sub2.insert(sub.retrieve()); // need checking
													// ************************(regarding
													// the chronological order)
				sub.findNext();
			}
			if (sub.retrieve().getText().contains(str)) // checking the last
														// element
				sub2.insert(sub.retrieve());

		}
		return sub2;
	}

	@Override
	public void remove(String str) {
		if (sub.empty())
			return;
		sub.findFirst();
		while (!sub.last()) {
			if (sub.retrieve().getText().contains(str)) {
				sub.remove();
				continue;
			}
			sub.findNext();
		}
		if (sub.retrieve().getText().contains(str))
			sub.remove();

	}

	@Override
	public void replace(String str1, String str2) {
		if (sub.empty())
			return;
		sub.findFirst();
		while (!sub.last()) {
			if (sub.retrieve().getText().contains(str1)) {
				Subtitle temp = sub.retrieve();
				temp.setText(temp.getText().replace(str1, str2));
				sub.update(temp);
			}
			sub.findNext();
		}
		if (sub.retrieve().getText().contains(str1)) {
			Subtitle temp = sub.retrieve();
			temp.setText(temp.getText().replace(str1, str2));
			sub.update(temp);
		}
	}

	@Override
	public void shift(int offset) {
	if(sub.empty())
		return;
	sub.findFirst();
		while(!sub.last()){
			Subtitle temp = sub.retrieve();
			((Time1) temp.getStartTime()).timeShifter(offset); //shifting the start time by the given offset
			((Time1) temp.getEndTime()).timeShifter(offset); //shifting the end time by the given offset
			sub.update(temp); 
			sub.findNext();
		}
		if(sub.last()){ //checking the last element
			Subtitle temp = sub.retrieve();
			((Time1) temp.getStartTime()).timeShifter(offset); //shifting the start time by the given offset
			((Time1) temp.getEndTime()).timeShifter(offset); //shifting the end time by the given offset
			sub.update(temp); 
		}
	

	}

	@Override
	public void cut(Time startTime, Time endTime) {

		


	}
	
	// testing only
	public void print(){
		if(sub.empty())
			return;
		sub.findFirst();
		while(!sub.last()){
			SubtitleIm a = (SubtitleIm) sub.retrieve();
			System.out.println(a.toString());
			sub.findNext();
		}
		SubtitleIm a = (SubtitleIm) sub.retrieve();
		System.out.println(a.toString());

	}

}
