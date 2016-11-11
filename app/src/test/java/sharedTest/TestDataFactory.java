package sharedTest;

import com.mozawa.wanikaniandroid.data.model.Kanji;
import com.mozawa.wanikaniandroid.data.model.Radical;

import java.util.ArrayList;
import java.util.List;

public class TestDataFactory {

    public static List<Kanji> makeKanjiList(int number) {
        List<Kanji> kanjiList = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            kanjiList.add(makeKanji());
        }
        return kanjiList;
    }

    public static Kanji makeKanji() {
        Kanji kanji = new Kanji();
        kanji.setCharacter("字");
        kanji.setMeaning("character");
        kanji.setOnyomi("ji");
        return kanji;
    }

    public static List<Radical> makeRadicalList(int number) {
        List<Radical> radicalList = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            radicalList.add(new Radical());
        }
        return radicalList;
    }
}
