package PT2018.assig5;

import java.util.*;
import org.joda.time.Duration;

import java.util.Map.Entry;
import java.util.stream.Stream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.*;
import java.util.stream.*;

public class Activities {

	public static void main(String args[]) throws IOException, ParseException {
		
		//file to write the results in
		FileWriter writer = null;
		try {
			writer = new FileWriter("result.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		//list to store the data read from the file
		List <MonitoredData> list = new ArrayList<MonitoredData>();
		
		//date format as found in given file
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		//read the data from the given file in a stream and store it in the above list
		try (Stream<String> stream = Files.lines(Paths.get("Activities.txt"))) {
			
			String []data;
			Iterator<String> it = stream.iterator();
			
			while (it.hasNext()) {
				data = it.next().split("\\s{2,}");
				MonitoredData m = new MonitoredData();
				m.setStartTime(date.parse(data[0]));
				m.setEndTime(date.parse(data[1]));
				m.setLabel(data[2]);
				list.add(m);
			}
		} catch (IOException e ) {
			e.printStackTrace();
		}
		
		//get from the list stream all the distinct days
		long days = list.stream().map(y -> y.getStartTimeDay()).distinct().count();

		System.out.println("1. Total number of days: "+ Long.toString(days)); 
		writer.write("1. Total number of days: " + Long.toString(days) + "\n");
		
		//create a map to  store the label and it's number of occurrences
		Map<String, Long> map1 = new HashMap<String, Long>();
		map1 = list.stream().collect(Collectors.groupingBy(y -> y.getLabel(), Collectors.counting()));
		
		System.out.println("\n2. Each distinct action types' number of occurences:\n" );
		writer.write("\n2. Each distinct action types' number of occurences:\n");
		
		for (Entry<String, Long> entry : map1.entrySet()) {
			writer.write(entry.getKey() + "  " + entry.getValue() + "\n");
			System.out.println(entry.getKey() + "  " + entry.getValue());
		}
		
		//create a map to store the each distinct day as a key, and as a value another map containing the activity and the number of occurrences in that day
		Map<Integer, Map<String, Long>> map2 = new HashMap<Integer, Map<String, Long>>();
		map2 = list.stream().collect(Collectors.groupingBy(y -> y.getStartTimeDay(), Collectors.groupingBy(z -> z.getLabel(), Collectors.counting())));
		
		System.out.println("3. Activity count for each day: ");
		writer.write("3. Activity count for each dat: \n");
		
		for (Entry<Integer, Map<String, Long>> entry : map2.entrySet()) {
			writer.write(entry.getKey());
			System.out.println(entry.getKey());
			for (Entry<String, Long> entry2 : entry.getValue().entrySet()) {
				writer.write("  " + entry2.getKey() + "  " + entry2.getValue() + "\n");
				System.out.println("  " + entry2.getKey() + "  " + entry2.getValue());
			}
		}
		
		//create a map containing as a key the activity label and as a value the total duration of the activity for all the days
		Map<String, Long> map3 = new HashMap<String, Long>();
		map3 = list.stream().collect(Collectors.groupingBy(y -> y.getLabel(), Collectors.summingLong(z -> z.getDuration())));
		writer.write("\n4. Total duration of the activities: \n");
		System.out.println("\n4. Total duration of the activities: \n");
		
		for (Entry<String, Long> entry : map3.entrySet()) {
			Duration duration = new Duration(Math.abs(entry.getValue()));
			System.out.println(entry.getKey() + "  " + duration.toPeriod().getHours() + "H " + duration.toPeriod().getMinutes() + "M " + duration.toPeriod().getSeconds() + "S");
			writer.write(entry.getKey() + "  " + duration.toPeriod().getHours() + "H " + duration.toPeriod().getMinutes() + "M " + duration.toPeriod().getSeconds() + "S" + "\n");
		}
		
		//create a map containing as key the activity label and as value a list containing the durations of the activity for all the days
		//create a second map that contains the same data, but filtered for duration 
		Map<String, List<Long>> map4 =list.stream().collect(Collectors.groupingBy(y -> y.getLabel(), Collectors.mapping(z -> z.getDuration(), Collectors.toList())));
		Map<String, List<Long>> map4Filtered =list.stream().filter(y -> y.getDuration()/10*9<30000).collect(Collectors.groupingBy(z -> z.getLabel(), Collectors.mapping(w -> w.getDuration(), Collectors.toList())));
		
		for (Entry<String, List<Long>> entry : map4Filtered.entrySet()) {
			for (Entry<String, List<Long>> entry2 : map4.entrySet()) {
				if (entry.getKey().equals(entry2.getKey())) {
					if (entry.getValue().size() >= 0.9 * entry2.getValue().size()) {
						System.out.println("5. Activities with 90% or more duration less than 5 min: " + entry.getKey());
						writer.write("5. Activities with 90% or more duration less than 5 min :" + entry.getKey() + "\n");
					}
				}
			
			}
		}
		
		System.out.println("6. Duration of each activity: ");
		writer.write("6. Duration for each activity: \n");
		
		//from the first map containing the durations for each activity, for each line of the input data, print its duration 
		for (Entry<String, List<Long>> entry : map4.entrySet()) {
			for (Long l : entry.getValue()) {
				Duration duration = new Duration(Math.abs(l));
				System.out.println("Label: " + entry.getKey() + "Duration: "  + duration.toPeriod().getHours() + "H " + duration.toPeriod().getMinutes() + "M " + duration.toPeriod().getSeconds() + "S");
				writer.write("Label: " + entry.getKey() + "Duration: "  + duration.toPeriod().getHours() + "H " + duration.toPeriod().getMinutes() + "M " + duration.toPeriod().getSeconds() + "S\n");
			}
		}
		
		writer.flush();
		writer.close();
		
	}
}
