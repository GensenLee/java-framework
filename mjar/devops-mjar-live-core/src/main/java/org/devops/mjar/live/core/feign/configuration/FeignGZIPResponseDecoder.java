package org.devops.mjar.live.core.feign.configuration;

import org.devops.mjar.live.core.handler.MjarLiveContext;
import feign.Response;
import feign.Util;
import feign.codec.Decoder;
import org.springframework.cloud.openfeign.encoding.HttpEncoding;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Objects;
import java.util.zip.GZIPInputStream;

/**
 * gzip压缩解压工具
 * @author GENSEN
 */
public class FeignGZIPResponseDecoder implements Decoder {

    final Decoder delegate;

    public FeignGZIPResponseDecoder(Decoder delegate) {
        Objects.requireNonNull(delegate, "Decoder must not be null. ");
        this.delegate = delegate;
    }

    @Override
    public Object decode(Response response, Type type) throws IOException {
        MjarLiveContext.getContext().getMjarLiveFeignResponseHandler().handle(response);
        Collection<String> values = response.headers().get(HttpEncoding.CONTENT_ENCODING_HEADER);
        if(Objects.nonNull(values) && !values.isEmpty() && values.contains(HttpEncoding.GZIP_ENCODING)){
            byte[] compressed = Util.toByteArray(response.body().asInputStream());
            if ((compressed == null) || (compressed.length == 0)) {
               return delegate.decode(response, type);
            }
            //decompression part
            //after decompress we are delegating the decompressed response to default 
            //decoder
            if (isCompressed(compressed)) {
                final StringBuilder output = new StringBuilder();
                final GZIPInputStream gis = new GZIPInputStream(new ByteArrayInputStream(compressed));
                final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(gis, StandardCharsets.UTF_8));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    output.append(line);
                }
                Response uncompressedResponse = response.toBuilder().body(output.toString().getBytes()).build();
                return delegate.decode(uncompressedResponse, type);
            }else{
                return delegate.decode(response, type);
            }
        }else{
            return delegate.decode(response, type);
        }
    }

    private static boolean isCompressed(final byte[] compressed) {
        return (compressed[0] == (byte) (GZIPInputStream.GZIP_MAGIC)) && (compressed[1] == (byte) (GZIPInputStream.GZIP_MAGIC >> 8));
    }
}