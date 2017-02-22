package kr.rvs.chtriggers.util;

/**
 * Created by Junhyeong Lim on 2017-02-22.
 */
public enum Type {
    CLICK,
    WALK,
    CLICK_VIEW(CLICK),
    WALK_VIEW(WALK);

    private Type subType;

    Type() {
    }

    Type(Type sub) {
        this.subType = sub;
    }

    public Type getSubType() {
        return subType;
    }

    public void setSubType(Type subType) {
        this.subType = subType;
    }
}
