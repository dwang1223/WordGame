package util;

import java.util.ArrayList;

import junit.framework.TestCase;
import model.Word;

/**
 * TestConvertWildcard Class
 * 
 * @author Di Yu
 *
 */
public class TestConvertWildcard extends TestCase {

	public TestConvertWildcard(String name) {
		super(name);
	}

	public void testConvert1() {
		WordBean wb1 = new WordBean("noun", "*");

		Word other = new Word(20, 20, "yu", "noun", false, false, false, false);

		ArrayList<WordBean> wblist1 = new ArrayList<WordBean>();

		ArrayList<Word> wdlist = new ArrayList<Word>();
		wblist1.add(wb1);

		wdlist.add(other);

		ConvertWildcard cWC = new ConvertWildcard();

		cWC.convert(wdlist, wblist1);

	}

	public void testConvert2() {
		// WordBean wb1 = new WordBean("noun","*");
		WordBean wb2 = new WordBean("*", "yu");
		// WordBean wb3 = new WordBean("*","*");

		Word other = new Word(20, 20, "yu", "noun", false, false, false, false);

		// ArrayList<WordBean> wblist1 = new ArrayList<WordBean>();
		ArrayList<WordBean> wblist2 = new ArrayList<WordBean>();
		// ArrayList<WordBean> wblist3 = new ArrayList<WordBean>();

		ArrayList<Word> wdlist = new ArrayList<Word>();
		// wblist1.add(wb1);
		wblist2.add(wb2);
		// wblist3.add(wb3);
		wdlist.add(other);

		ConvertWildcard cWC = new ConvertWildcard();

		cWC.convert(wdlist, wblist2);

	}

	public void testConvert3() {

		WordBean wb3 = new WordBean("*", "*");

		Word other = new Word(20, 20, "yu", "noun", false, false, false, false);

		ArrayList<WordBean> wblist2 = new ArrayList<WordBean>();

		ArrayList<Word> wdlist = new ArrayList<Word>();

		wblist2.add(wb3);

		wdlist.add(other);

		ConvertWildcard cWC = new ConvertWildcard();

		cWC.convert(wdlist, wblist2);

	}

}
