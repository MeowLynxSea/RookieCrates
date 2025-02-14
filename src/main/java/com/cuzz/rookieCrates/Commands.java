package com.cuzz.rookieCrates;

import com.cuzz.rookieCrates.core.model.CrateModel;
import com.cuzz.rookieCrates.core.model.LootModel;
import com.cuzz.rookieCrates.core.scene.Scene;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Commands implements TabExecutor {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        if (args.length==1){
            return new ArrayList<String>() {{
                add("spawn");
                add("spawn2");
                add("saveLoc");
                add("see");
                add("fake");
                add("summonloot");
            }};
        }
        if (args.length==2&&args[0].equalsIgnoreCase("saveLoc")){
            return new ArrayList<String>() {{
                add("crateloc");
                add("cameraloc");
                add("loot1");
                add("loot2");
                add("loot3");
                add("loot4");
                add("loot5");
                add("loot6");
                add("loot7");
            }};
        }

        return new ArrayList<String>() {{
            add("spawn");
        }};
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.isOp()) {
            return true;
        }
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if(args[0].equalsIgnoreCase("spawn")){

                CrateModel crate = new CrateModel().spawnModel(player.getLocation(), "xi_crate1", player);
                crate.playAnimation(player,args[1]);
                return true;
            }
            if(args[0].equalsIgnoreCase("spawn2")){

                LootModel crate = new LootModel().spawnModel(player.getLocation(), "loot_white", player);
                crate.playAnimation(player,"idle");
                return true;
            }
            if(args[0].equalsIgnoreCase("saveLoc")){
                RookieCrates.getInstance().saveLocation(player,args[1]);
                return true;
            }
            if(args[0].equalsIgnoreCase("see")){
                new Scene().setUp(player);
                return true;
            }
            if(args[0].equalsIgnoreCase("summonloot")){

                for (int i =1;i <= 7;i++){

                    Location location = RookieCrates.getInstance().getLocation(player, "loot" + i);
                    LootModel crate = new LootModel().spawnModel(location, "loot_white", player);
                    crate.playAnimation(player,"idle");


                }


                return true;
            }
            if(args[0].equalsIgnoreCase("fake")){
                RookieCrates.getInstance().createEntityPack(player);
                return true;
            }
        }
        return true;
    }

}
