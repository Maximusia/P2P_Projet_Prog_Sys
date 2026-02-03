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

        boolean ok = filesAreEqual(
            new File("shared/test.txt"),
            new File("downloads/test_rebuilt.txt")
        );

    System.out.println("Fichiers identiques ? " + ok);


        System.out.println("Reconstruction...");
        fm.rebuildFile(received, new File("downloads/test_rebuilt.txt"));

        System.out.println("✅ Démo terminée avec succès");
    }
}
private static boolean filesAreEqual(File f1, File f2) throws Exception {
    try (
        FileInputStream fis1 = new FileInputStream(f1);
        FileInputStream fis2 = new FileInputStream(f2)
    ) {
        int b1, b2;
        while ((b1 = fis1.read()) != -1) {
            b2 = fis2.read();
            if (b1 != b2) return false;
        }
        return fis2.read() == -1;
    }
}
