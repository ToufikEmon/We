package subevent.detect;

import java.util.Comparator;

public class SortByTime implements Comparator<CheckTime> {

	@Override
	public int compare(CheckTime o1, CheckTime o2) {
		if (o1.date == o2.date) {
			if (o1.hour == o2.hour) {
				if (o1.min == o2.min) {
					if (o1.sec == o2.sec) {
						return 0;
					} else if (o1.sec > o2.sec) {
						return 1;
					} else {
						return -1;
					}
				} else if (o1.min > o2.min) {
					return 1;
				} else {
					return -1;
				}
			} else if (o1.hour > o2.hour) {
				return 1;
			} else {
				return -1;
			}
		} else if (o1.date > o2.date) {
			return 1;
		} else {
			return -1;
		}
	}

}
