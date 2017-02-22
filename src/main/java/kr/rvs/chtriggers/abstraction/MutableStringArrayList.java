package kr.rvs.chtriggers.abstraction;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Junhyeong Lim on 2017-02-22.
 */
public class MutableStringArrayList extends ArrayList<String> {
    public MutableStringArrayList(int initialCapacity) {
        super(initialCapacity);
    }

    public MutableStringArrayList() {
    }

    public MutableStringArrayList(Collection<? extends String> c) {
        super(c);
    }

    public Integer getInteger(int index) {
        return Integer.parseInt(get(index));
    }

    @Override
    public String get(int index) {
        String str = super.get(index);
        if (str == null) {
            str = "";
        }
        return str;
    }
}
