package sharedTest;

import com.mozawa.wanikaniandroid.data.model.Kanji;

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

}
