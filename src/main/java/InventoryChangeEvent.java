import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class InventoryChangeEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private final Player p;
    private final ItemStack[] itemStacks;

    public InventoryChangeEvent(Player p, ItemStack[] itemStacks) {
        this.p = p;
        this.itemStacks = itemStacks;
    }

    public Player getPlayer() {
        return p;
    }

    public ItemStack[] getItemStacks() {
        return itemStacks;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
