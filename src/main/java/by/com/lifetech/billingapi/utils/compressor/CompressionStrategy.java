package by.com.lifetech.billingapi.utils.compressor;

import java.io.IOException;

public interface CompressionStrategy {
    byte[] compress(byte[] input) throws IOException;
    byte[] decompress(byte[] input) throws IOException;
}
