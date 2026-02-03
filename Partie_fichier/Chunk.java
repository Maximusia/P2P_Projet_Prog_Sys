package Partie_fichier;

public class Chunk {
    private final int fileId;
    private final int index;
    private final byte[] data;
    private final String hash;

    public Chunk(int fileId, int index, byte[] data, String hash) {
        this.fileId = fileId;
        this.index = index;
        this.data = data;
        this.hash = hash;
    }

    public int getFileId() {
        return fileId;
    }
    public int getIndex() {
        return index;
    }
    public byte[] getData() {
        return data;
    }
    public String getHash() {
        return hash;
    }
    public void setData(byte[] data) {
        this.data = data;
    }
    public void setHash(String hash) {
        this.hash = hash;
    }
    public void setIndex(int index) {
        this.index = index;
    }
    public void setFileId(int fileId) {
        this.fileId = fileId;
    }
}