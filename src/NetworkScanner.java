import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class NetworkScanner {

    public static void main(String[] args) {
        String startIP = "134.168.1.1";
        String endIP = "192.168.1.254";

        System.out.println("Scanning for active hostsпапавп...");

        try {
            scanRange(startIP, endIP);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void scanRange(String startIP, String endIP) throws IOException {
        String[] startParts = startIP.split("\\.");
        String[] endParts = endIP.split("\\.");

        for (int i = Integer.parseInt(startParts[3]); i <= Integer.parseInt(endParts[3]); i++) {
            String ip = startParts[0] + "." + startParts[1] + "." + startParts[2] + "." + i;
            if (isReachable(ip)) {
                System.out.println("Host " + ip + " is active.");
            }
        }
    }

    public static boolean isReachable(String ip) throws IOException {
        int timeout = 1000; // Timeout in milliseconds
        InetSocketAddress socketAddress = new InetSocketAddress(ip, 80);

        try (Socket socket = new Socket()) {
            socket.connect(socketAddress, timeout);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
