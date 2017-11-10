
public class SubtitleSeqIm implements SubtitleSeq {
	public LinkedList<Subtitle> sub;

	@Override
	public SubtitleSeqIm() {
		sub = new LinkedList<Subtitle>();
	}

	public double timeToSec(Time tm) {
		double sec = 0;

		sec = +(tm.getHH() * 60 * 60); // hours to seconds
		sec = +(tm.getMM() * 60);// minues to sec
		sec = +tm.getSS();
		sec = +tm.getMS() / 1000.0; // milli seconds to seconds

		return sec;
	}

	public void addSubtitle(Subtitle st) {
		/*
		 * need work, what about the placement of the subtitle? is it always the
		 * last subtitle
		 */

		if (sub.full())
			return;
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
			if (inTime(time)) // returns true if the given time match the time
								// in the current
				return sub.retrieve();
			sub.findNext();
		}
		if (inTime(time)) // checking the last element
			return sub.retrieve();
		return null;
	}

	private boolean inTime(Time time) { // created to make code cleaner and
										// might need to use the some method
										// again

		Time start = sub.retrieve().getStartTime();
		Time end = sub.retrieve().getEndTime();
		double startt = timeToSec(start);
		double endt = timeToSec(end);
		double givent = timeToSec(time);
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
		LinkedList<Subtitle> sub2 = null;
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
	if(sup.empty())
		return;

	}

	@Override
	public void cut(Time startTime, Time endTime) {


	}

}
