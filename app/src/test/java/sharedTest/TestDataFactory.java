package sharedTest;

import com.mozawa.wanikaniandroid.data.model.Kanji;

import java.util.ArrayList;
import java.util.List;

public class TestDataFactory {

    public static List<Kanji.KanjiInformation> makeKanjiList(int number) {
        List<Kanji.KanjiInformation> kanjiList = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            kanjiList.add(makeKanji());
        }
        return kanjiList;
    }

    public static Kanji.KanjiInformation makeKanji() {
        Kanji.KanjiInformation kanji = new Kanji.KanjiInformation();
        kanji.setCharacter("字");
        kanji.setMeaning("character");
        kanji.setOnyomi("ji");
        return kanji;
    }

}
