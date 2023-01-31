import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        super.onEnable();
        getServer().getPluginManager().registerEvents(new EventCause(), this);
        new DelayedTask(this);
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}
