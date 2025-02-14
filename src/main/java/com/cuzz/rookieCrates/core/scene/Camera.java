package com.cuzz.rookieCrates.core.scene;

import org.bukkit.*;

public class Camera {

    //相机位置
    Location originLocation;
}
//
//    private ItemStack[] originalHelmets= {new ItemStack(Material.AIR)};
//    private LivingEntity spectate;
//    Map<String,Action> actions;
//    public void stopSpectate(Player player) {
//        if (player == null || this.spectate == null) {
//            return;
//        }
//        player.setGameMode(GameMode.SURVIVAL);
//        EntityPlayer entityPlayer = ((CraftPlayer)player).getHandle();
//        entityPlayer.c.a((Packet)new PacketPlayOutCamera(entityPlayer)); // 切换视角回到玩家
//
//        // 删除盔甲架实体
//        getOriginalHelmet(player);
//        this.spectate.remove();
//    }
//    public void spawnParticleEffect(Location location, Player player,NpcModel npc){
//        AtomicReference<BukkitTask> taskRef = new AtomicReference<>();
//        final int[] times={0};
//        BukkitTask bukkitTask2 = RookieBlackSmith.getInstance().getServer().getScheduler().runTaskTimer((Plugin)RookieBlackSmith.getInstance(), () -> {
//            // 跳动五次粒子
//            times[0]++;
//            spawnParticleSphere(new Location(location.getWorld(), location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch()), Particle.CLOUD, 1, 3);
//            //生成Lava粒子 移除物品
//            if(times[0]==2){
//                npc.playAnimation(player,"anvil_smash");
//            }
//            if(times[0]==5){
//                taskRef.get().cancel();
//                spawnParticleSphere(new Location(location.getWorld(), location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch()), Particle.LAVA, 2, 3);
//                for (Item item : this.spawnedItems) {
//                    removeItem(player,item);
//                }
//                this.spawnedItems.clear();
//                spawnItem(location, player,npc);
//            }
//
//        }, 20L, 20);
//        //移除物品
//
//        taskRef.set(bukkitTask2);
//    }
//    public void setFakeHelmet(Player player, ItemStack itemStack) {
//        originalHelmets[0]= player.getInventory().getHelmet();
//        EntityPlayer entityPlayer = ((CraftPlayer)player).getHandle();
//        entityPlayer.setItemSlot(EnumItemSlot.f, CraftItemStack.asNMSCopy(itemStack), true);
//
//
//    }
//    public void createSpectate(Player player,Location location) {
//        if (player == null)
//            return;
//        EntityPlayer entityPlayer = ((CraftPlayer)player).getHandle();
//        EntityArmorStand entityArmorStand = new EntityArmorStand(EntityTypes.d, (World)((CraftWorld)player.getWorld()).getHandle());
//        entityArmorStand.j(true);
//        entityArmorStand.m(true);
//        entityArmorStand.a(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
//        entityPlayer.c.a((Packet)new PacketPlayOutSpawnEntity((Entity)entityArmorStand));
//        entityArmorStand.aj().refresh(entityPlayer);
//        entityPlayer.c.a((Packet)new PacketPlayOutEntityTeleport((Entity)entityArmorStand));
//        entityPlayer.c.a((Packet)new PacketPlayOutCamera((Entity)entityArmorStand));
//        this.spectate = (LivingEntity)entityArmorStand.getBukkitEntity();
//        final double[] movedDistance = {0.0};


//        AtomicReference<BukkitTask> taskRef = new AtomicReference<>();
//        BukkitTask bukkitTask2 = RookieBlackSmith.getInstance().getServer().getScheduler().runTaskTimer((Plugin)RookieBlackSmith.getInstance(), () -> {
//            if (movedDistance[0] >= 2) {
//                // 如果已经移动了2格或更多，则取消任务
//                taskRef.get().cancel();
//                return;
//            }
//            Location current = spectate.getLocation();
//            current.setY(current.getY() + 0.05);
//            spectate.teleport(current);
//
//            // 更新已经移动的距离
//            movedDistance[0] += 0.05;
//
//            // 同步玩家视角
//            entityPlayer.c.a((Packet)new PacketPlayOutEntityTeleport((Entity)entityArmorStand));
//            entityPlayer.c.a((Packet)new PacketPlayOutCamera((Entity)entityArmorStand));
//        }, 20L, 1);
//        taskRef.set(bukkitTask2);
//    }
//    public void removeItem(Player player,Item item) {
//        if (player == null)
//            return;
//        EntityPlayer entityPlayer = ((CraftPlayer)player).getHandle();
//        entityPlayer.c.a((Packet)new PacketPlayOutEntityDestroy(new int[] { item.getEntityId() }));
//    }
//
//    public void getOriginalHelmet(Player player) {
//
//        // 恢复原始头盔
//        if (! (originalHelmets.length==0)) {
//            player.getInventory().setHelmet(originalHelmets[0]);
//        }
//    }

//    public void setSpectator(Player player) {
//        player.setGameMode(GameMode.SPECTATOR);
//        setFakeHelmet(player,new ItemStack(Material.CARVED_PUMPKIN));
//        EntityPlayer p = ((CraftPlayer)player).getHandle();
//        ClientboundPlayerInfoUpdatePacket packet = new ClientboundPlayerInfoUpdatePacket(ClientboundPlayerInfoUpdatePacket.a.c, p);
//        try {
//            Field packetField = packet.getClass().getDeclaredField("b");
//            packetField.setAccessible(true);
//            ArrayList<ClientboundPlayerInfoUpdatePacket.b> list = Lists.newArrayList();
//            list.add(new ClientboundPlayerInfoUpdatePacket.b(player.getUniqueId(), p.getBukkitEntity().getProfile(), false, 0, EnumGamemode.b, p.listName, null));
//            packetField.set(packet, list);
//            p.c.a((Packet)packet);
//            PacketPlayOutGameStateChange packetPlayOutGameStateChange = new PacketPlayOutGameStateChange(PacketPlayOutGameStateChange.d, 3.0F);
//            p.c.a((Packet)packetPlayOutGameStateChange);
//        } catch (NoSuchFieldException|IllegalAccessException e) {
//            e.printStackTrace();
//        }
//    }


//    public void spawnParticleSphere(Location center, Particle particle, int radius, int density) {
//        // Step 1: Create a sphere of particles
//        for (double theta = 0; theta <= Math.PI; theta += Math.PI / density) {
//            double y = radius * Math.sin(theta);
//            double r = radius * Math.cos(theta);
//
//            for (double phi = 0; phi <= 2 * Math.PI; phi += Math.PI / density) {
//                double x = r * Math.cos(phi);
//                double z = r * Math.sin(phi);
//
//                org.bukkit.util.Vector velocity = new Vector(0.05, 0.05, 0.05);  // 这将给粒子一个轻微的向上和稍微的X和Z方向上的运动。
//                center.getWorld().spawnParticle(particle, center.getX() + x, center.getY() + y, center.getZ() + z, 1, 0, 0, 0, 0.1);
//            }
//        }

        // Step 2: Create an explosion effect (wait for a few ticks for dramatic effect)
//    }
//}
