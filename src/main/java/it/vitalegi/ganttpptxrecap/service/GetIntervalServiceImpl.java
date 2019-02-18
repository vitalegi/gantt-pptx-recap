package it.vitalegi.ganttpptxrecap.service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * @author Giorgio Vitale
 */
@Service
public class GetIntervalServiceImpl {

	public List<LocalDate> getIntervals(Date from, Date to) {

		List<LocalDate> dates = new ArrayList<>();

		LocalDate firstDate = YearMonth.from(toLocalDate(from)).atDay(1);
		LocalDate lastDate = YearMonth.from(toLocalDate(to)).plusMonths(1).atDay(1);

		dates.add(firstDate);

		LocalDate currDate = increment(firstDate);

		while (currDate.isBefore(lastDate)) {
			dates.add(currDate);
			currDate = increment(currDate);
		}
		return dates;
	}

	private LocalDate increment(LocalDate date) {
		return YearMonth.from(date).plusMonths(1).atDay(1);
	}

	private LocalDate toLocalDate(Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
}
