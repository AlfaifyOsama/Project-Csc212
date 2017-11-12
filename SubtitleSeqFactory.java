import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SubtitleSeqFactory {

	// Return an empty subtitles sequence
	public static SubtitleSeq getSubtitleSeq() {
		return new SubtitleSeqIm();
	}

	// Load a subtitle sequence from an SRT file. If the file does not exist or
	// is corrupted (incorrect format), null is returned.
	public static SubtitleSeq loadSubtitleSeq(String fileName) {
		SubtitleSeqIm seq = new SubtitleSeqIm();
		int seqNum = 1;
		String pattern = "^\\d{2}+[:]+\\d{2}+[:]+\\d{2}+[,]+\\d{3}+ --> +\\d{2}+[:]+\\d{2}+[:]+\\d{2}+[,]+\\d{3}$";
		try {
			Scanner in = new Scanner(new File(fileName));
			while (in.hasNext()) {

				// Check if it has valid sequential number
				String seqNumString = in.nextLine();
				if (!seqNumString.equals("" + seqNum++)) {
					return null;
				}

				// Check if it has valid time pattern
				String time = in.nextLine();
				if (!time.matches(pattern)) {
					return null;
				}
				String[] timeArray = time.split(" ");
				String startTime = timeArray[0];
				String endTime = timeArray[2];

				// Skip text line
				String text = in.nextLine();

				// Check if it is not the last subtitle, then check for break
				// line
				if (in.hasNext()) {

					if (!in.nextLine().matches("^\\s*$")) {
						return null;
					}
				} else { // THIS CHECKS IF THE LAST SUBTITLE HAS A TEXT
					if(text.equals("")){
						return null;
					}
				}
				


				// add to the subtitleSeq
				Time startTimeObj = new TimeIm(Integer.parseInt(startTime.substring(0, 2)),
						Integer.parseInt(startTime.substring(3, 5)), Integer.parseInt(startTime.substring(6, 8)),
						Integer.parseInt(startTime.substring(9, 12)));
				Time endTimeObj = new TimeIm(Integer.parseInt(endTime.substring(0, 2)),
						Integer.parseInt(endTime.substring(3, 5)), Integer.parseInt(endTime.substring(6, 8)),
						Integer.parseInt(endTime.substring(9, 12)));
				SubtitleIm tmp = new SubtitleIm(startTimeObj, endTimeObj, text);
				seq.addSubtitle(tmp);

			}
		} catch (FileNotFoundException e) {
			return null;
		}
		return seq;

	}

	public static void main(String[] args) {
		SubtitleSeqFactory s = new SubtitleSeqFactory();
		System.out.println(s.loadSubtitleSeq("/Users/osama/Desktop/test.srt"));
	}
}
