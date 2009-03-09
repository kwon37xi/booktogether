package com.google.code.booktogether.service.scheduling;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.google.code.booktogether.service.LibraryService;

public class RefeshLibraryRankJob extends QuartzJobBean {

	private LibraryService libraryService;

	public void setLibraryService(LibraryService libraryService) {
		this.libraryService = libraryService;
	}

	// 로그 표시를 위하여
	private Logger log = Logger.getLogger(this.getClass());

	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {

		boolean result = libraryService.deleteLibraryRank();

		if (result) {
			result = libraryService.refeshLibraryRank();
		}

		if (result) {
			if (log.isInfoEnabled()) {
				log.info("새로고침 제대로 되었음");
			}
		} else {
			if (log.isInfoEnabled()) {
				log.info("새로고침 실패");
			}

		}

	}

}
