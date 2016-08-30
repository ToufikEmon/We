package subevent.detect;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import org.supercsv.io.CsvListWriter;
import org.supercsv.io.ICsvListWriter;
import org.supercsv.prefs.CsvPreference;

public class LastOuput {

	public void giveOuput(List<OutputGenerator> now) {

		MyDatabase myDatabase = new MyDatabase();

		int len = now.size();

		for (int i = 0; i < len; i++) {

			String qq = "SELECT * FROM `eventdataset` WHERE Time BETWEEN ";
			qq += "'Sun Aug ";
			qq += now.get(i).getStart();
			qq += " BDT 2016' AND";
			qq += " 'Sun Aug ";
			qq += now.get(i).getEnd();
			qq += " BDT 2016'";

			ResultSet rs = myDatabase.executeQueryAndGiveBackTheDataList(qq);

			try {
				Vector<String> csvMatrix = new Vector<>();

				int total = 0;
				while (rs.next()) {
					csvMatrix.add(rs.getString(1));
					csvMatrix.add(rs.getString(2));
					csvMatrix.add(rs.getString(3));
					total++;
				}

				String[][] str = new String[total][total];
				for (int j = 0, k = 0; j < total; j++) {
					str[j][0] = csvMatrix.get(k++);
					str[j][1] = csvMatrix.get(k++);
					str[j][2] = csvMatrix.get(k++);

				}

				writeCsv(str, i);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

	private void writeCsv(String[][] csvMatrix, int fileNumber) {

		ICsvListWriter csvWriter = null;
		try {
			csvWriter = new CsvListWriter(new FileWriter("Subevent_" + String.valueOf(fileNumber) + ".csv"),
					CsvPreference.STANDARD_PREFERENCE);

			for (int i = 0; i < csvMatrix.length; i++) {
				csvWriter.write(csvMatrix[i]);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				csvWriter.close();
			} catch (IOException e) {
			}
		}

	}

}

/*
 * String string = rs.getString("Status"); for (int j = 0; j < string.length();
 * j++) { char ch = string.charAt(i); if (ch == ',' || ch == '\n' || ch == '\t')
 * { output1.append(" "); System.out.println(ch); } else { output1.append(ch); }
 * 
 * }
 */