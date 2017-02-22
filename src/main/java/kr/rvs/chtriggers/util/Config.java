package kr.rvs.chtriggers.util;

import kr.rvs.chtriggers.CHTriggers;
import org.bukkit.Material;

/**
 * Created by Junhyeong Lim on 2017-02-22.
 */
public class Config {
    public static final String INTERACT_ITEM = "interact-item";

    @SuppressWarnings("deprecation")
    public static Material getInteractItem() {
        return Material.getMaterial(CHTriggers.inst.getConfig().getInt(
                INTERACT_ITEM, Material.BONE.getId()));
    }
}
