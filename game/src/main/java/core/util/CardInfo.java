package core.util;

import java.util.List;

public class CardInfo {
    private List<Integer> year;
    private boolean draft;


    public CardInfo(List<Integer> year, boolean draft) {
        this.year = year;
        this.draft = draft;
    }

    public List<Integer> getYear() {
        return year;
    }

    public boolean isDraft() {
        return draft;
    }
}
