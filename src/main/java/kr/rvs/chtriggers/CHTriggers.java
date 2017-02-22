package kr.rvs.chtriggers;

import com.laytonsmith.commandhelper.CommandHelperPlugin;
import kr.rvs.chtriggers.abstraction.MutableStringArrayList;
import kr.rvs.chtriggers.listener.PlayerListener;
import kr.rvs.chtriggers.util.Config;
import kr.rvs.chtriggers.util.Static;
import kr.rvs.chtriggers.util.Trigger;
import kr.rvs.chtriggers.util.Type;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Junhyeong Lim on 2017-02-22.
 */
public class CHTriggers extends JavaPlugin {
    public static Plugin inst;

    public CHTriggers() {
        inst = this;
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
        Static.setCommandHelperPlugin((CommandHelperPlugin) Bukkit.getPluginManager().getPlugin("CommandHelper"));
    }

    @Override
    public void onDisable() {
        saveConfig();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("플레이어만 사용 가능");
            return true;
        }

        Player player = (Player) sender;
        MutableStringArrayList list = new MutableStringArrayList(Arrays.asList(args));

        if (label.equalsIgnoreCase("ctc")) {
            request(player, Type.CLICK, list);
        } else if (label.equalsIgnoreCase("ctw")) {
            request(player, Type.WALK, list);
        } else if (label.equalsIgnoreCase("ctcv")) {
            request(player, Type.CLICK_VIEW, null);
        } else if (label.equalsIgnoreCase("ctwv")) {
            request(player, Type.WALK_VIEW, null);
        }
        return true;
    }

    private void request(Player player, Type type, List<String> list) {
        Trigger.addRequest(type, player, toStr(list));
        player.sendMessage("아이템 " + Config.getInteractItem() + " 로 클릭해주세요.");
    }

    private String toStr(List<String> strs) {
        if (strs == null) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        for (String str : strs) {
            builder.append(str).append(" ");
        }
        return builder.toString();
    }
}
