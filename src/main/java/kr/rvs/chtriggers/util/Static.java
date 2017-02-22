package kr.rvs.chtriggers.util;

import com.laytonsmith.abstraction.bukkit.entities.BukkitMCPlayer;
import com.laytonsmith.commandhelper.CommandHelperFileLocations;
import com.laytonsmith.commandhelper.CommandHelperPlugin;
import com.laytonsmith.core.MethodScriptCompiler;
import com.laytonsmith.core.ParseTree;
import com.laytonsmith.core.constructs.Token;
import com.laytonsmith.core.environments.CommandHelperEnvironment;
import com.laytonsmith.core.environments.Environment;
import com.laytonsmith.core.environments.GlobalEnv;
import com.laytonsmith.core.exceptions.ConfigCompileException;
import com.laytonsmith.core.exceptions.ConfigCompileGroupException;
import com.laytonsmith.core.taskmanager.TaskManager;
import kr.rvs.chtriggers.CHTriggers;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.List;

/**
 * Created by Junhyeong Lim on 2017-02-22.
 */
public class Static {
    private static CommandHelperPlugin commandHelperPlugin;

    public static CommandHelperPlugin getCommandHelperPlugin() {
        return commandHelperPlugin;
    }

    public static void setCommandHelperPlugin(CommandHelperPlugin commandHelperPlugin) {
        Static.commandHelperPlugin = commandHelperPlugin;
    }

    public static void eval(Player player, List<String> scriptList) throws ConfigCompileException, ConfigCompileGroupException {
        if (scriptList == null
                || scriptList.isEmpty()) {
            return;
        }

        StringBuilder builder = new StringBuilder();
        File dataFile = getDataFile();
        for (String script : scriptList) {
            builder.append(script).append("\n");
        }

        try {
            GlobalEnv globalEnv = new GlobalEnv(commandHelperPlugin.executionQueue, commandHelperPlugin.profiler, commandHelperPlugin.persistenceNetwork,
                    CommandHelperFileLocations.getDefault().getConfigDirectory(), commandHelperPlugin.profiles, new TaskManager());
            CommandHelperEnvironment chEnv = new CommandHelperEnvironment();
            chEnv.SetPlayer(new BukkitMCPlayer(player));
            Environment env = Environment.createEnvironment(globalEnv, chEnv);
            List<Token> stream = MethodScriptCompiler.lex(builder.toString(), dataFile, true);
            ParseTree tree = MethodScriptCompiler.compile(stream);
            MethodScriptCompiler.execute(tree, env, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static File getDataFile() {
        return new File(CHTriggers.inst.getDataFolder(),
                "data.json");
    }
}
