package com.cuzz.rookieCrates.core.scene;

import com.cuzz.rookieCrates.core.model.CrateModel;
import com.cuzz.rookieCrates.core.model.LootModel;
import com.cuzz.rookieCrates.RookieCrates;
import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.util.Vector3d;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerCamera;
import me.tofaa.entitylib.wrapper.WrapperEntity;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public class Scene {

    CrateModel crateEntity;

    Location originLocation;


//    public Scene(CrateModel crateEntity){
//
//        this.crateEntity= crateEntity;
//    }

    public Scene setUp(Player player){


        //召唤实体
        Location crateloc = RookieCrates.getInstance().getLocation(player, "crateloc");
        CrateModel crate = new CrateModel().spawnModel(crateloc, "xi_crate1", player);
        this.crateEntity= crate;
        crate.playAnimation(player,"open2");



        //布置玩家位置
        Location cameraloc = RookieCrates.getInstance().getLocation(player, "cameraloc");
        player.teleport(cameraloc);
        WrapperEntity entity = RookieCrates.getInstance().createEntityPack(player);
        // 创建一个向上的速度向量，设置 Y 轴为正数，表示向上移动
        Vector3d velocity = new Vector3d(0, 0.1, 0); // 0.1 表示缓慢向上的速度，你可以调整这个数值来控制速度
         // 设置实体的速度
        entity.setVelocity(velocity);

        WrapperPlayServerCamera wrapperPlayServerCamera = new WrapperPlayServerCamera(entity.getEntityId());
        PacketEvents.getAPI().getPlayerManager().sendPacket(player, wrapperPlayServerCamera);
        final int[] taskId=new int[1];
        BukkitTask bukkitTask = Bukkit.getServer().getScheduler().runTaskTimerAsynchronously(
                RookieCrates.getInstance(),
                new Runnable(){
                    //时间轴
                    float[] timeTickAxis = {0};
                    // 定义初始和目标仰角
                    float targetPitch = -32.0f; // 目标仰角 -45°
                    float defaultPitch = entity.getPitch();
                    //定义每次增加的角度
                    float step = -2.5f;
                    //当前仰角
                     float[] currentPitch = {entity.getPitch()};
                    final int endPoint=10;
                    @Override
                    public void run() {
//                        timeTickAxis[0]++;
                        //如果时间轴还未启动且还没抬到指定的角度,则需要继续抬头
                        if (timeTickAxis[0]==0 &&currentPitch[0]>targetPitch){
                            currentPitch[0] += step;
                            entity.rotateHead(entity.getYaw(), currentPitch[0]);


                        //已经抬头到目标角度
                        //停留Ntick
                        }else if (timeTickAxis[0]<endPoint){
                            timeTickAxis[0]++;

                        //移除宝箱模型
                        }else if (timeTickAxis[0]==endPoint){
                            timeTickAxis[0]++;
                            crateEntity.removeModel(player);


                        //开始低头程序
                        } else if (currentPitch[0]<defaultPitch){
                            currentPitch[0]-=step;
                            entity.rotateHead(entity.getYaw(), currentPitch[0]);

                        }else {

                            for (int i =1;i <= 7;i++){

                                Location location = RookieCrates.getInstance().getLocation(player, "loot" + i);
                                LootModel crate = new LootModel().spawnModel(location, "loot_white", player);
                                crate.playAnimation(player,"idle");
                            }

                            Bukkit.getServer().getScheduler().cancelTask(taskId[0]);
                            System.out.println("取消任务");
                        }

                    }

                }, 95L, 1L

        );
       taskId[0]= bukkitTask.getTaskId();


//        BukkitTask task = RookieCrates.getInstance().getServer().getScheduler().runTaskTimer(RookieCrates.getInstance(),
//
//                new Runnable() {
//                    // 定义初始和目标仰角
//                    final float targetPitch = -32.0f; // 目标仰角 -45°
//                    final float defaultPitch = entity.getPitch();
//                    final float step = -2.5f;
//                    final int[] dropTick = {0};
//                    final float[] currentPitch = {entity.getPitch()};
//
//                    @Override
//                    public void run() {
//                        //抬头视角
//                        if (dropTick[0] == 0 && currentPitch[0] > targetPitch) {
//                            System.out.println("开始抬头" + currentPitch[0]);
//                            currentPitch[0] += step; // 每次减少 2.5°
//                            entity.rotateHead(entity.getYaw(), currentPitch[0]);
//                            //停留10tick 再次低头
//                        } else if (dropTick[0] < 10) {
//
//                            System.out.println("TICK ++");
//                            dropTick[0]++;
//                        } else if (dropTick[0] == 10) {
//                            if (currentPitch[0] < defaultPitch) {
//                                System.out.println("开始低头" + currentPitch[0]);
//                                currentPitch[0] -= step; // 每次增加 2.5°
//                                entity.rotateHead(entity.getYaw(), currentPitch[0]);
//
//                            } else {
//                                task.cancel();
//                                System.out.println("取消任务");
//                            }
//
//                        }
//
//                    }
//
//                }
//
//
//                ,
//                95L, 1L
//
//
//        );


        return this;


    }
}
