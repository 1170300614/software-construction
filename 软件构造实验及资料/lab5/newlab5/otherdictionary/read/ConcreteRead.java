package read;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import read.ReadStrategy;

public class ConcreteRead implements ReadStrategy {
	/** Rank. */
	private static final int RANK = 26;
	/** Max length. */
	private static final int LENGTH = 1 << RANK;

	@Override
	public List<String> readFile2Lines(final String fileName) {
		try {
			List<String> list = new ArrayList<>();
			FileReader in = new FileReader(new File(fileName));
			char[] chars = new char[LENGTH];
			int flag = in.read(chars, 0, chars.length);
			if (flag != -1) {
				String s = new String(chars, 0, flag);
				list.addAll(Arrays.asList(s.split("\n")));
			}
			in.close();
			return list;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
