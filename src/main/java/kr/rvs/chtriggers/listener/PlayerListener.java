package kr.rvs.chtriggers.listener;

import kr.rvs.chtriggers.util.Config;
import kr.rvs.chtriggers.util.Trigger;
import kr.rvs.chtriggers.util.Type;
import kr.rvs.chtriggers.util.TypeAndScriptsStorage;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.EquipmentSlot;

/**
 * Created by Junhyeong Lim on 2017-02-22.
 */
public class PlayerListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onClick(PlayerInteractEvent e) {
        if (e.getHand() != EquipmentSlot.HAND) {
            return;
        }
        Player player = e.getPlayer();
        Location loc = e.getClickedBlock().getLocation();
        Trigger.trig(Type.CLICK, player, loc);

        if (!player.isOp() || e.getItem() != null &&
                e.getItem().getType() != Config.getInteractItem()) {
            return;
        }
        TypeAndScriptsStorage storage = Trigger.getTypeAndScriptsStorage(player);
        if (storage != null) {
            Trigger.processRequest(storage, loc, player);
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onWalk(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        Location loc = e.getTo();
        Trigger.trig(Type.WALK, player, loc);
    }
}
