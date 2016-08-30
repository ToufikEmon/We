package subevent.detect;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CheckTimeManager {

	private List<CheckTime> timeList;
	private List<CheckTime> unFilteredTimeList;

	public CheckTimeManager(List<CheckTime> timeList) {
		unFilteredTimeList = timeList;
		this.timeList = new ArrayList<>();
	}

	public void printOuput() {
		int len = timeList.size();
		for (int i = 0; i < len; i++) {
			System.out.println(timeList.get(i));
		}
	}

	public void sortAll() {
		Collections.sort(unFilteredTimeList, new SortByTime());
	}

	public void combineSameTimes() {

		int len = unFilteredTimeList.size();
		if (len == 0) {
			System.err.println("your time list is empty");
			return;
		}
		CheckTime ct = unFilteredTimeList.get(0);
		for (int i = 0, counter = 0; i < len; i += counter) {
			counter = 0;
			for (int j = 0; i + j < len; j++) {
				CheckTime ct2 = unFilteredTimeList.get(i + j);
				if (ct.isEqual(ct2)) {
					counter++;
				} else {
					break;
				}
			}
			ct.setCount(counter);
			timeList.add(ct);
			ct = new CheckTime(ct.nextSecond());
		}

	}

	public void giveOutputOnColsole(int howManySecondPerClock, int overFlowBoundary) {
		int x = howManySecondPerClock;
		int y = overFlowBoundary;

		int len = timeList.size();
		List<OutputGenerator> now = new ArrayList<>();
		boolean st = true, end = false;
		OutputGenerator og = new OutputGenerator();

		for (int i = 0, l = 0; i < len; i += l) {
			l = 0;

			int counter = timeList.get(i).getCounter();

			for (int j = 0; j < x && i + j < len; j++) {
				counter += timeList.get(i + j).getCounter();
				l++;
			}

			if (counter > y) {
				if (st == true) {
					og.setStart(timeList.get(i).toString());
					end = true;
					st = false;
				}
			} else {
				if (end == true) {
					end = false;
					st = true;
					og.setEnd(timeList.get(i).toString());
					now.add(og);
					og = new OutputGenerator();
				}
			}
		}

		len = now.size();
		for (int i = 0; i < len; i++) {
			System.out.println(i+" -----> "+now.get(i));
		}
		System.err.println("Console print is done!!");

	}

	public void giveOutputToFile(int howManySecondPerClock, int overFlowBoundary, String fileName) {
		int x = howManySecondPerClock;
		int y = overFlowBoundary;

		int len = timeList.size();
		List<OutputGenerator> now = new ArrayList<>();
		boolean st = true, end = false;
		OutputGenerator og = new OutputGenerator();

		for (int i = 0, l = 0; i < len; i += l) {
			l = 0;

			int counter = timeList.get(i).getCounter();

			for (int j = 0; j < x && i + j < len; j++) {
				counter += timeList.get(i + j).getCounter();
				l++;
			}

			if (counter > y) {
				if (st == true) {
					og.setStart(timeList.get(i).toString());
					end = true;
					st = false;
				}
			} else {
				if (end == true) {
					end = false;
					st = true;
					og.setEnd(timeList.get(i).toString());
					now.add(og);
					og = new OutputGenerator();
				}
			}
		}

		len = now.size();
		StringBuilder output1 = new StringBuilder();
		output1.append("FromF , Total \n");
		for (int i = 0; i < len; i++) {
			output1.append(now.get(i));
		}

		try {
			PrintWriter pw1 = new PrintWriter(new File(fileName));
			pw1.write(output1.toString());
			pw1.close();
			System.err.println("Saving to A File is Done");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		LastOuput lastOuput = new LastOuput();
		lastOuput.giveOuput(now);

	}

	public void giveOutputToFileAllUsingGivenTime(int howManySecondPerClock, int overFlowBoundary, String fileName) {
		int x = howManySecondPerClock;
		int y = overFlowBoundary;

		int len = timeList.size();
		List<OutputGenerator> now = new ArrayList<>();
		for (int i = 0, l = 0; i < len; i += l) {
			l = 0;
			OutputGenerator og = new OutputGenerator();
			og.setStart(timeList.get(i).toString());
			int counter = timeList.get(i).getCounter();

			for (int j = 0; j < x && i + j < len; j++) {
				counter += timeList.get(i + j).getCounter();
				l++;
			}
			og.setCounter(counter);
			og.setEnd(timeList.get(i + l - 1).toString());

			now.add(og);
		}

		len = now.size();
		StringBuilder output1 = new StringBuilder();
		output1.append("From , To , Total \n");
		for (int i = 0; i < len; i++) {
			if (now.get(i).inBoundary(y)) {
				output1.append(now.get(i));
			}
		}

		try {
			PrintWriter pw1 = new PrintWriter(new File(fileName));
			pw1.write(output1.toString());
			pw1.close();
			System.err.println("Saving to A File is Done");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
