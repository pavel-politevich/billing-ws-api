package by.com.lifetech.billingapi.utils.compressor;

import lombok.AllArgsConstructor;
import lombok.Setter;

import java.io.IOException;
import java.nio.charset.Charset;

@Setter
@AllArgsConstructor
public class Compressor {
    private CompressionStrategy compressionStrategy;

    public byte[] compressString(String input, String charset) throws IOException {
        byte[] inputBytes = input.getBytes(charset);
        return compressBytes(inputBytes);
    };
    public byte[] compressBytes(byte[] input) throws IOException {
        return compressionStrategy.compress(input);
    };
    public String decompressBytesToString(byte[] input, String charset) throws IOException {
        return new String(decompressBytes(input), Charset.forName(charset));
    };
    public byte[] decompressBytes(byte[] input) throws IOException {
        return compressionStrategy.decompress(input);
    };
}
