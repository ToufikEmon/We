
public class GenerateOutput {

	public static float checkTime = (float) 2;

	public int date1, min1, hour1, sec1;
	public int count = 0;

	public GenerateOutput() {
		count = 0;
	}

	public GenerateOutput(String all) {

		date1 = Integer.parseInt(all.substring(0, 2));
		hour1 = Integer.parseInt(all.substring(3, 5));
		min1 = Integer.parseInt(all.substring(6, 8));

		
		System.out.println(all);
		System.out.println(date1);
		System.out.println(hour1);
		System.out.println(min1);
		count = 0;

	}

	public void makeNext() {

		min1 = (int) (min1 + checkTime);

		min1 %= 60;
		if (min1 >= 60) {

			hour1 = hour1 + 1;
			if (hour1 >= 24) {
				date1 = date1 + 1;
				hour1 %= 24;
			}
		}

		count = 0;
	}

	public void addCount() {
		count++;
	}

	public String output() {
		int min2 = (int) (min1 + checkTime);
		int hour2 = hour1;
		int date2 = date1;
		if (min2 >= 60) {
			min2 %= 60;
			hour2 = hour1 + 1;
			if (hour2 >= 24) {
				date2 = date1 + 1;
				hour2 %= 24;
			}
		}

		return String.format("%d:%d:%d-%d:%d:%d,%d\n", date1, hour1, min1, date2, hour2, min2, count);
	}

}

// min2 = min1 + 2;
// if (min2 >= 60) {
// min2 %= 60;
// hour2 = hour1 + 1;
// if (hour2 >= 24) {
// date2 = date1 + 1;
// hour2 %= 24;
// }
// }