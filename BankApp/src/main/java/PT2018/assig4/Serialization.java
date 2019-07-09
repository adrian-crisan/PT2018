package PT2018.assig4;

import java.io.*;
import java.io.FileOutputStream;

/**
 * @author Crisan
 *
 */
public class Serialization {

	/**
	 * @param bank - bank instance to be serialized
	 */
	public static void serialize(Bank bank) {
		try {
			FileOutputStream outFile = new FileOutputStream("C:/Users/Wtp/Desktop/UTCN/An 3/Semestrul 2/PT2018/assig4/serialization.ser");
			ObjectOutputStream output = new ObjectOutputStream(outFile);
			output.writeObject(bank);
			output.close();
			outFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @return bank - returns a deserialized bank
	 */
	public static Bank deserialize() {
		try {
			Bank bank = new Bank();
			FileInputStream inFile = new FileInputStream("C:/Users/Wtp/Desktop/UTCN/An 3/Semestrul 2/PT2018/assig4/serialization.ser");
			ObjectInputStream input = new ObjectInputStream(inFile);
			bank = (Bank)input.readObject();
			input.close();
			inFile.close();
			return bank;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
		}
	return null;
	}
}

