package file;

import chunk.Chunk;
import util.HashUtils;

import java.io.*;
import java.util.*;

public class FileManager {

    private static final int CHUNK_SIZE = 1024; // petit pour la démo

    public List<Chunk> splitFile(File file, int fileId) throws IOException {
        List<Chunk> chunks = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] buffer = new byte[CHUNK_SIZE];
            int index = 0;
            int bytesRead;

            while ((bytesRead = fis.read(buffer)) != -1) {
                byte[] data = Arrays.copyOf(buffer, bytesRead);
                String hash = HashUtils.sha256(data);
                chunks.add(new Chunk(fileId, index++, data, hash));
            }
        }
        return chunks;
    }

    public void rebuildFile(List<Chunk> chunks, File outputFile) throws IOException {
        chunks.sort(Comparator.comparingInt(Chunk::getIndex));

        try (FileOutputStream fos = new FileOutputStream(outputFile)) {
            for (Chunk c : chunks) {
                fos.write(c.getData());
            }
        }
    }
}
