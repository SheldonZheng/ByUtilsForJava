import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class computerDate {
	public int computeBetweenDate (String datestart,String dateend) throws ParseException
	{

		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		Date dNow = sdf.parse(datestart);
		Calendar calendar = Calendar.getInstance();
		Date dBefore = sdf.parse(dateend);
		Calendar calendar2 = Calendar.getInstance();
		calendar.setTime(dNow);
		calendar2.setTime(dBefore);
		long temp = calendar.getTimeInMillis() - calendar2.getTimeInMillis();
		temp = temp /1000/60/60/24;
		return new Long(temp).intValue();
	}
}
