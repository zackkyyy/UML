package model.searchStrategies;

import model.Member;

/**
 * Created by zack on 2017-10-27.
 */
public class ByAndComplexity implements ISearchStrategy {
    private ISearchStrategy firstSearch;
    private ISearchStrategy secondSearch;

    public ByAndComplexity( ISearchStrategy firstSearch , ISearchStrategy secondSearch){
        this.firstSearch=firstSearch;
        this.secondSearch=secondSearch;
    }

    @Override
    public boolean findMembers(Member m) {
        return firstSearch.findMembers(m) && secondSearch.findMembers(m) ;
    }

    public ISearchStrategy getByAndComplexity() {
        return new ByAndComplexity( firstSearch ,  secondSearch);
    }
}
