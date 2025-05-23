package org.bukkit.craftbukkit.inventory;

import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.level.LevelAccessor;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.block.CraftBlock;
import org.bukkit.inventory.BlockInventoryHolder;
import org.bukkit.inventory.Inventory;

public class CraftBlockInventoryHolder implements BlockInventoryHolder {

    private final Block block;
    private final Inventory inventory;

    public CraftBlockInventoryHolder(LevelAccessor levelAccessor, BlockPos pos, Container container) {
        this.block = CraftBlock.at(levelAccessor, pos);
        this.inventory = new CraftInventory(container);
    }
    // Paper start - Add missing InventoryHolders
    public CraftBlockInventoryHolder(net.minecraft.world.inventory.ContainerLevelAccess levelAccess, Inventory inventory) {
        com.google.common.base.Preconditions.checkArgument(levelAccess.isBlock());
        this.block = CraftBlock.at(levelAccess.getWorld(), levelAccess.getPosition());
        this.inventory = inventory;
    }
    // Paper end - Add missing InventoryHolders

    @Override
    public Block getBlock() {
        return this.block;
    }

    @Override
    public Inventory getInventory() {
        return this.inventory;
    }
}
