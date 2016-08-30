package subevent.detect;

public class CheckTime {

	public int date, min, hour, sec;
	private int count = 0;

	public CheckTime(String all) {
		date = Integer.parseInt(all.substring(0, 2));
		hour = Integer.parseInt(all.substring(3, 5));
		min = Integer.parseInt(all.substring(6, 8));
		sec = Integer.parseInt(all.substring(9, 11));
		count = 0;
	}

	public boolean isEqual(CheckTime o) {
		if (this.date == o.date) {
			if (this.hour == o.hour) {
				if (this.min == o.min) {
					if (this.sec == o.sec) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public String toString() {

		String ret = new String();

		if (date < 10) {
			ret += "0" + String.valueOf(date);
		} else {
			ret += String.valueOf(date);
		}
		ret += " ";
		if (hour < 10) {
			ret += "0" + String.valueOf(hour);
		} else {
			ret += String.valueOf(hour);
		}
		ret += ":";
		if (min < 10) {
			ret += "0" + String.valueOf(min);
		} else {
			ret += String.valueOf(min);
		}

		ret += ":";
		if (sec < 10) {
			ret += "0" + String.valueOf(sec);
		} else {
			ret += String.valueOf(sec);
		}
		ret += "-";
		if (count < 10) {
			ret += "0" + String.valueOf(count);
		} else {
			ret += String.valueOf(count);
		}

		return ret;
	}

	public String nextSecond() {
		sec++;
		if (sec == 60) {
			sec = 0;
			min++;
			if (min == 60) {
				min = 0;
				hour++;
				if (hour == 24) {
					hour = 0;
				}
			}
		}

		String ret = new String();

		if (date < 10) {
			ret += "0" + String.valueOf(date);
		} else {
			ret += String.valueOf(date);
		}
		ret += " ";
		if (hour < 10) {
			ret += "0" + String.valueOf(hour);
		} else {
			ret += String.valueOf(hour);
		}
		ret += ":";
		if (min < 10) {
			ret += "0" + String.valueOf(min);
		} else {
			ret += String.valueOf(min);
		}

		ret += ":";
		if (sec < 10) {
			ret += "0" + String.valueOf(sec);
		} else {
			ret += String.valueOf(sec);
		}

		return ret;
	}

	public void setCount(int counter) {
		this.count = counter;
	}

	public int getCounter() {
		return count;
	}

}
