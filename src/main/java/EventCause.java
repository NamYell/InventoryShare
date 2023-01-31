import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.DoubleChestInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EventCause implements Listener {

    public static ItemStack[] itemStacks;

    @EventHandler
    public void onInventoryInteract(InventoryInteractEvent e) {
        new DelayedTask(() -> {
            Bukkit.getPluginManager().callEvent(new InventoryChangeEvent((Player) e.getWhoClicked(),e.getWhoClicked().getInventory().getContents()));
        }, 1);
    }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        new DelayedTask(() -> {
            Bukkit.getPluginManager().callEvent(new InventoryChangeEvent((Player) e.getWhoClicked(),e.getWhoClicked().getInventory().getContents()));
        }, 1);
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            new DelayedTask(() -> {
                Bukkit.getPluginManager().callEvent(new InventoryChangeEvent(e.getPlayer(),e.getPlayer().getInventory().getContents()));
            }, 1);
        }
    }

    @EventHandler
    public void onInventoryClickWithHotbar(InventoryClickEvent e) {
        Integer i = e.getRawSlot();
        if (e.getInventory() instanceof CraftingInventory) {
            if (!(i >= 38 && i <= 42) && !(i >= 0 && i <= 8)) {
                e.setCancelled(true);
            }
        } else if (e.getInventory() instanceof DoubleChestInventory) {
            if ((!(e.getRawSlot() >= 83 && e.getRawSlot() <= 87)) && e.getRawSlot() > 53) {
                e.setCancelled(true);
            }
        } else if (e.getInventory().getType().toString().equals("CHEST") || e.getInventory().getType().toString().equals("ENDER_CHEST") ||
                e.getInventory().getType().toString().equals("SHULKER_BOX")) {
            if ((!(e.getRawSlot() >= 56 && e.getRawSlot() <= 60)) && e.getRawSlot() > 26) {
                e.setCancelled(true);
            }
        }

        if (e.getClick().isKeyboardClick()) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent e) {
        new DelayedTask(() -> {
            Bukkit.getPluginManager().callEvent(new InventoryChangeEvent((Player) e.getWhoClicked(),e.getWhoClicked().getInventory().getContents()));
        }, 1);
    }

    @EventHandler
    public void onInventoryPickup(PlayerPickupItemEvent e) {
        new DelayedTask(() -> {
            Bukkit.getPluginManager().callEvent(new InventoryChangeEvent(e.getPlayer(),e.getPlayer().getInventory().getContents()));
        }, 1);
    }

    @EventHandler
    public void onInventoryDrop(PlayerDropItemEvent e) {
        new DelayedTask(() -> {
            Bukkit.getPluginManager().callEvent(new InventoryChangeEvent(e.getPlayer(),e.getPlayer().getInventory().getContents()));
        }, 1);
    }

    @EventHandler
    public void onItemSwap(PlayerSwapHandItemsEvent e) {
        if (e.getOffHandItem().getType().equals(Material.BARRIER)) {
            e.setCancelled(true);
        }

        new DelayedTask(() -> {
            Bukkit.getPluginManager().callEvent(new InventoryChangeEvent(e.getPlayer(),e.getPlayer().getInventory().getContents()));
        }, 1);
    }

    @EventHandler
    public void onHotbarChange(PlayerItemHeldEvent e) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.getInventory().setHeldItemSlot(e.getNewSlot());
        }
    }

    @EventHandler
    public void onInventoryChange(InventoryChangeEvent e) {
        Player p = e.getPlayer();

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (!player.equals(p)) {
                player.getInventory().setContents(e.getItemStacks());
                itemStacks = e.getItemStacks();
            }
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        ItemStack itemStack = new ItemStack(Material.BARRIER);
        ItemMeta itemMeta  = itemStack.getItemMeta();
        itemMeta.setDisplayName(" ");
        itemStack.setItemMeta(itemMeta);

        p.getInventory().setItem(0, itemStack);
        p.getInventory().setItem(1, itemStack);

        for (int i = 7; i <= 35; i++) {
            p.getInventory().setItem(i, itemStack);
        }
    }
}
