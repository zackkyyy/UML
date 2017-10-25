package model.searchStrategies;

import model.Member;

/**
 * Created by zack on 2017-10-25.
 */
public class ByPersonalNumber implements ISearchStrategy {
    private String personalNr;

    public ByPersonalNumber(String personalNr) {
        this.personalNr = personalNr;
    }

    @Override
    public boolean findMembers(Member m) {
        return m.getPersonalNumber().equals(personalNr);
    }

    public ISearchStrategy getSearchByPersonalNr() {
        return new ByPersonalNumber(personalNr);
    }
}
