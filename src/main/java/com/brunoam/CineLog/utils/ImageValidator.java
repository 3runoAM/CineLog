package com.brunoam.CineLog.utils;

import com.brunoam.CineLog.exceptions.InvalidImageException;
import org.springframework.web.multipart.MultipartFile;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageValidator {

    private final int maxWidth;
    private final int maxHeight;
    private final long maxFileSize;

    public ImageValidator(int maxWidth, int maxHeight, long maxFileSize) {
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
        this.maxFileSize = maxFileSize;
    }

    public void validate(MultipartFile file) throws InvalidImageException {
        validateNotEmpty(file);
        validateMimeType(file);
        validateFileSize(file);
        validateImageDimensions(file);
    }

    private void validateNotEmpty(MultipartFile file) throws InvalidImageException {
        if (file.isEmpty()) {
            throw new InvalidImageException("O arquivo enviado está vazio.");
        }
    }

    private void validateMimeType(MultipartFile file) throws InvalidImageException {
        String contentType = file.getContentType();
        if (!"image/jpeg".equals(contentType) && !"image/png".equals(contentType)) {
            throw new InvalidImageException("O arquivo deve ser no formato JPEG ou PNG.");
        }
    }

    private void validateFileSize(MultipartFile file) throws InvalidImageException {
        if (file.getSize() > maxFileSize) {
            throw new InvalidImageException("O arquivo excede o tamanho máximo permitido de " + (maxFileSize / (1024 * 1024)) + "MB.");
        }
    }

    private void validateImageDimensions(MultipartFile file) throws InvalidImageException {
        try {
            BufferedImage image = ImageIO.read(file.getInputStream());
            if (image == null) {
                throw new InvalidImageException("O arquivo enviado não é uma imagem válida.");
            }

            int width = image.getWidth();
            int height = image.getHeight();
            if (width > maxWidth || height > maxHeight) {
                throw new InvalidImageException("As dimensões máximas da imagem são " + maxWidth + "x" + maxHeight + "px.");
            }
        } catch (IOException e) {
            throw new InvalidImageException("Erro ao processar a imagem.", e);
        }
    }
}