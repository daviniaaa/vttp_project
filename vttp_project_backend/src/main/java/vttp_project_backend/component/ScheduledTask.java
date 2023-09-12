package vttp_project_backend.component;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import vttp_project_backend.models.ExternalApi.DataObject;
import vttp_project_backend.service.EventService;
import vttp_project_backend.service.ExternalApiService;

@Component
public class ScheduledTask {
	private final String[] searchValuesArray = {
		"a", "b", "c", "d", "e", "f", "g", "h",
		"i", "j", "k", "l", "m", "n", "o", "p",
		"q", "r", "s", "t", "u", "v", "w", "x",
		"y", "z", "0", "1", "2", "3", "4", "5",
		"6", "7", "8", "9"
	}; 

	private int count = 0;

	@Autowired ExternalApiService extApiService;
	@Autowired EventService eventService;

    // @Scheduled(fixedRate = 20 * 1000) // in milliseconds
	// public void reportCurrentTime() {
	// 	System.out.println("The time is now " + (new Date()).toString());
	// 	System.out.println("count >> " + count);

	// 	List<DataObject> list = extApiService.getFromExternalApi(searchValuesArray[count%36], 50);
	// 	for (DataObject d : list) {
	// 		eventService.addToDatabase(d);
	// 	}

	// 	count ++;
	// }
}
