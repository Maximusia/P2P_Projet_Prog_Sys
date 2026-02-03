package protocol;

import chunk.Chunk;
import util.HashUtils;

import java.util.Base64;

public class ProtocolHandler {

    public String buildDataMessage(Chunk chunk) {
        String payload = Base64.getEncoder().encodeToString(chunk.getData());
        return "DATA " + chunk.getFileId() + " " + chunk.getIndex() +
               " " + chunk.getHash() + " " + payload;
    }

    public Chunk parseDataMessage(String msg) {
        String[] parts = msg.split(" ");
        int fileId = Integer.parseInt(parts[1]);
        int index = Integer.parseInt(parts[2]);
        String hash = parts[3];
        byte[] data = Base64.getDecoder().decode(parts[4]);

        if (!HashUtils.sha256(data).equals(hash)) {
            throw new RuntimeException("Chunk corrompu !");
        }

        return new Chunk(fileId, index, data, hash);
    }
}
