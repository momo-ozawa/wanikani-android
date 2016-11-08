package sharedTest;

import com.mozawa.wanikaniandroid.data.model.KanjiResponse;

import java.util.ArrayList;
import java.util.List;

public class TestDataFactory {

    public static List<KanjiResponse.Kanji> makeKanjiList(int number) {
        List<KanjiResponse.Kanji> kanjiList = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            kanjiList.add(makeKanji());
        }
        return kanjiList;
    }

    public static KanjiResponse.Kanji makeKanji() {
        KanjiResponse.Kanji kanji = new KanjiResponse.Kanji();
        kanji.setCharacter("字");
        kanji.setMeaning("character");
        kanji.setOnyomi("ji");
        return kanji;
    }

}
