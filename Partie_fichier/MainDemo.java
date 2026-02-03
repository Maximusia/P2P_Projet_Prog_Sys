import chunk.Chunk;
import file.FileManager;
import protocol.ProtocolHandler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainDemo {

    public static void main(String[] args) throws Exception {

        FileManager fm = new FileManager();
        ProtocolHandler ph = new ProtocolHandler();

        File original = new File("shared/test.txt");
        int fileId = 1;

        System.out.println("Découpage du fichier...");
        List<Chunk> chunks = fm.splitFile(original, fileId);

        System.out.println("Simulation envoi/réception...");
        List<Chunk> received = new ArrayList<>();
        for (Chunk c : chunks) {
            String msg = ph.buildDataMessage(c);
            received.add(ph.parseDataMessage(msg));
        }

        System.out.println("Reconstruction...");
        fm.rebuildFile(received, new File("downloads/test_rebuilt.txt"));

        System.out.println("✅ Démo terminée avec succès");
    }
}
