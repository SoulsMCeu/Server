package io.papermc.paper.event.player;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * Called when a player sets the effect for a beacon
 */
@NullMarked
public class PlayerChangeBeaconEffectEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Block beacon;
    private @Nullable PotionEffectType primary;
    private @Nullable PotionEffectType secondary;
    private boolean consumeItem = true;

    private boolean cancelled;

    @ApiStatus.Internal
    public PlayerChangeBeaconEffectEvent(final Player player, final @Nullable PotionEffectType primary, final @Nullable PotionEffectType secondary, final Block beacon) {
        super(player);
        this.primary = primary;
        this.secondary = secondary;
        this.beacon = beacon;
    }

    /**
     * @return the primary effect
     */
    public @Nullable PotionEffectType getPrimary() {
        return this.primary;
    }

    /**
     * Sets the primary effect
     * <p>
     * NOTE: The primary effect still has to be one of the valid effects for a beacon.
     *
     * @param primary the primary effect
     */
    public void setPrimary(final @Nullable PotionEffectType primary) {
        this.primary = primary;
    }

    /**
     * @return the secondary effect
     */
    public @Nullable PotionEffectType getSecondary() {
        return this.secondary;
    }

    /**
     * Sets the secondary effect
     * <p>
     * This only has an effect when the beacon is able to accept a secondary effect.
     * NOTE: The secondary effect still has to be a valid effect for a beacon.
     *
     * @param secondary the secondary effect
     */
    public void setSecondary(final @Nullable PotionEffectType secondary) {
        this.secondary = secondary;
    }

    /**
     * @return the beacon block associated with this event
     */
    public Block getBeacon() {
        return this.beacon;
    }

    /**
     * Gets if the item used to change the beacon will be consumed.
     * <p>
     * Independent of {@link #isCancelled()}. If the event is cancelled
     * the item will <b>NOT</b> be consumed.
     *
     * @return {@code true} if item will be consumed
     */
    public boolean willConsumeItem() {
        return this.consumeItem;
    }

    /**
     * Sets if the item used to change the beacon should be consumed.
     * <p>
     * Independent of {@link #isCancelled()}. If the event is cancelled
     * the item will <b>NOT</b> be consumed.
     *
     * @param consumeItem {@code true} if item should be consumed
     */
    public void setConsumeItem(final boolean consumeItem) {
        this.consumeItem = consumeItem;
    }

    /**
     * {@inheritDoc}
     * <p>
     * If a {@link PlayerChangeBeaconEffectEvent} is cancelled, the changes will
     * not take effect
     */
    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    /**
     * {@inheritDoc}
     * <p>
     * If cancelled, the item will <b>NOT</b> be consumed regardless of what {@link #willConsumeItem()} says
     * <p>
     * If a {@link PlayerChangeBeaconEffectEvent} is cancelled, the changes will not be applied
     * or saved.
     */
    @Override
    public void setCancelled(final boolean cancel) {
        this.cancelled = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
