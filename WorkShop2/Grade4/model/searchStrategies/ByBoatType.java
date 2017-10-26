package model.searchStrategies;

import model.Boat;
import model.BoatType;
import model.Member;

/**
 * Created by zack on 2017-10-25.
 */
public class ByBoatType implements ISearchStrategy {
    private BoatType type;

    public ByBoatType(BoatType type) {
        this.type = type;
    }


    @Override
    public boolean findMembers(Member m) {
        for (Boat b : m.getBoatList()) {
            if (b.getBoatType() == type) {
                return true;
            }

        }

        return false;
    }

    public ISearchStrategy getSearchByBoatType(BoatType type) {
        return new ByBoatType(type);
    }
}

