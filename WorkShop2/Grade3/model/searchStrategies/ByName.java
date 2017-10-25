package model.searchStrategies;

import model.Member;

/**
 * Created by zack on 2017-10-25.
 */
public class ByName implements ISearchStrategy {
    private String name;

    public ByName(String name) {
        this.name = name;

    }

    @Override
    public boolean findMembers(Member m) {

        return m.getName().equals(name);
    }

    public ISearchStrategy getSearchByName() {
        return new ByName(name);
    }

}
