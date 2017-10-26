package model.searchStrategies;

import model.Member;

/**
 * Created by zack on 2017-10-27.
 */
public class ByOrComplexity implements ISearchStrategy {
    private ISearchStrategy firstSearch;
    private ISearchStrategy secondSearch;

    public ByOrComplexity( ISearchStrategy firstSearch , ISearchStrategy secondSearch){
        this.firstSearch=firstSearch;
        this.secondSearch=secondSearch;
    }

    @Override
    public boolean findMembers(Member m) {
        return firstSearch.findMembers(m) || secondSearch.findMembers(m) ;
    }

    public ISearchStrategy getByOrComplexity() {
        return new ByOrComplexity( firstSearch ,  secondSearch);
    }
}
