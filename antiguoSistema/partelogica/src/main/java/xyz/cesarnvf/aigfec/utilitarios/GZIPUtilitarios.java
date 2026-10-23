package xyz.cesarnvf.aigfec.utilitarios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;
import org.springframework.stereotype.Component;

@Component
public class GZIPUtilitarios {

    private final byte[] BUFFER = new byte[1024];

    public boolean comprimirArchivo(File archivo) {
        try (GZIPOutputStream out = new GZIPOutputStream(new FileOutputStream(archivo.getAbsolutePath() + ".zip"));
             FileInputStream in = new FileInputStream(archivo)) {
            int len;
            while ((len = in.read(BUFFER)) != -1) {
                out.write(BUFFER, 0, len);
            }
            out.close();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
        return true;
    }

    public boolean comprimirDirectorio(Path archivo, List<Path> archivos) throws IOException {
        try (OutputStream salida = Files.newOutputStream(archivo);
             GzipCompressorOutputStream gzipSalida = new GzipCompressorOutputStream(salida);
             TarArchiveOutputStream tarSalida = new TarArchiveOutputStream(gzipSalida)) {

            for (Path archivoEntrada : archivos) {
                TarArchiveEntry entrada = new TarArchiveEntry(archivoEntrada.toFile());
                tarSalida.putArchiveEntry(entrada);
                Files.copy(archivoEntrada, tarSalida);
                tarSalida.closeArchiveEntry();
            }
            tarSalida.finish();
            return true;
        }
    }

}
