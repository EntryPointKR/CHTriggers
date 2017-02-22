package kr.rvs.chtriggers.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Junhyeong Lim on 2017-02-22.
 */
public class TypeAndScriptsStorage {
    private Type type;
    private String script;

    public TypeAndScriptsStorage(Type type, String script) {
        this.type = type;
        this.script = script;
    }

    public TypeAndScriptsStorage(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }
}
