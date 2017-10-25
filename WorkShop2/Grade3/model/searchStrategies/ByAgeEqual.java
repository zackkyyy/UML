package model.searchStrategies;

import model.Member;


/**
 * Created by zack on 2017-10-25.
 */
public class ByAgeEqual implements ISearchStrategy {
    private int age;

    public ByAgeEqual(int age) {

        this.age = age;
    }


    @Override
    public boolean findMembers(Member m) {
        return m.getAge() == age;
    }

    public ISearchStrategy getSearchByAgeEqualTo() {
        return new ByAgeEqual(age);
    }

}

