package kr.rvs.chtriggers.util;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Junhyeong Lim on 2017-02-22.
 */
public class Trigger {
    private static final Map<Type, Map<Location, List<String>>> typeMap = new HashMap<>();
    private static final Map<Player, TypeAndScriptsStorage> requestMap = new HashMap<>();

    public static void trig(Type type, Player player, Location loc) {
        Map<Location, List<String>> scriptMap = typeMap.get(type);
        if (scriptMap == null
                || scriptMap.isEmpty()) {
            return;
        }
        List<String> scriptList = scriptMap.get(loc);
        if (scriptList == null
                || scriptList.isEmpty()) {
            return;
        }

        try {
            Static.eval(player, scriptList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addScript(Type type, Location loc, List<String> scripts) {
        Map<Location, List<String>> scriptMap = typeMap.computeIfAbsent(type, k -> new HashMap<>());
        List<String> scriptList = scriptMap.get(loc);
        if (scriptList == null) {
            scriptList = new ArrayList<>();
        }
        scriptList.addAll(scripts);
        scriptMap.put(loc, scriptList);
    }

    public static void addRequest(Type type, Player player, String script) {
        requestMap.put(player, new TypeAndScriptsStorage(type, script));
    }

    public static TypeAndScriptsStorage getTypeAndScriptsStorage(Player player) {
        return requestMap.get(player);
    }

    public static void processRequest(TypeAndScriptsStorage storage, Location loc, Player player) {
        Type type = storage.getType();
        if (type.getSubType() != null) {
            List<String> scriptList = typeMap.get(type.getSubType()).get(loc);
            for (String script : scriptList) {
                player.sendMessage(script + "\n");
            }
        } else {
            addScript(storage.getType(), loc, Collections.singletonList(storage.getScript()));
        }
        requestMap.remove(player);
        player.sendMessage("완료");
    }
}
