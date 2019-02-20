package it.vitalegi.ganttpptxrecap.service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * @author Giorgio Vitale
 */
@Service
public class GetIntervalServiceImpl {

	public List<LocalDate> getIntervals(LocalDate from, LocalDate to) {

		List<LocalDate> dates = new ArrayList<>();

		dates.add(from);

		LocalDate currDate = increment(from);

		while (currDate.isBefore(to)) {
			dates.add(currDate);
			currDate = increment(currDate);
		}
		return dates;
	}

	private LocalDate increment(LocalDate date) {
		return YearMonth.from(date).plusMonths(1).atDay(1);
	}
}
