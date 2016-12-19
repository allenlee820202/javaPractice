import java.net.*;
import java.io.*;

public class URLReader {
	public static void main(String[] args) throws Exception{
		URL bank = new URL("http://rate.bot.com.tw/xrt?Lang=zh-TW");
		BufferedReader in = new BufferedReader( new InputStreamReader(bank.openStream()));
		File file;
		FileOutputStream fos = null;
		file = new File("bank.html");
		fos = new FileOutputStream(file);
		if(!file.exists())
			file.createNewFile();
		
		String inputLine;
		while((inputLine = in.readLine()) != null){
			fos.write(inputLine.getBytes());
			fos.write("\n".getBytes());
			fos.flush();
		}
		in.close();
	}
}
