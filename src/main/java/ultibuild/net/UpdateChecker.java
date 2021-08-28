package ultibuild.net;

import org.bukkit.Rotation;
import org.bukkit.block.BlockFace;

import java.io.InputStream;
import java.net.URL;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Level;

public class UpdateChecker {
    private static String ver = "1.2";

    public static void checkForUpdates() {
        if(!Objects.equals(getLatestVersion(), "")) {
            if(!Objects.equals(ver, getLatestVersion())) {
                ItemGui.getInstance().getLogger().log(Level.INFO,"A new version is avalible!");
                ItemGui.getInstance().getLogger().log(Level.INFO,"Your version: " + ver + " Newest: " + getLatestVersion());
                ItemGui.getInstance().getLogger().log(Level.INFO,"Update here: https://www.spigotmc.org/resources/94792");
            }
        } else {
            ItemGui.getInstance().getLogger().log(Level.INFO,"An error occurred getting latest version!");
        }
    }

    public static String getLatestVersion() {
        try {
            URL url = new URL("https://api.spigotmc.org/legacy/update.php?resource=94792");
            InputStream inputStream = url.openStream();
            Scanner scanner = new Scanner(inputStream);
            String string = scanner.nextLine();
            return string;
        } catch (Throwable io) {
            return "";
        }

    }
}
