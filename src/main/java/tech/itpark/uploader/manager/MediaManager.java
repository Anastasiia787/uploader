package tech.itpark.uploader.manager;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import tech.itpark.uploader.domain.Media;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Component
public class MediaManager {
    private final Path mediaPath = Path.of("./static");

    public MediaManager() {
        try {
            Files.createDirectories(mediaPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Media save(MultipartFile file) {
        try {
            String name = UUID.randomUUID().toString();

            file.transferTo(mediaPath.resolve(name));
            return new Media(name);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
