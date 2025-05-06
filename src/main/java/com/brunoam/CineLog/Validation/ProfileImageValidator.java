package com.brunoam.CineLog.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ProfileImageValidator implements ConstraintValidator<ValidProfileImage, MultipartFile> {
    private static final List<String> ALLOWED_MIME_TYPES = List.of("image/jpeg", "image/png");
    private static final int MAX_WIDTH = 4096;
    private static final int MAX_HEIGHT = 4096;
    private static final long MAX_FILE_SIZE_BYTES = 2 * 1024 * 1024;

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        if (file == null || file.isEmpty()) {
            return true;
        }

        if (!ALLOWED_MIME_TYPES.contains(file.getContentType())) {
            addError(context, "Formato inválido. Use JPEG ou PNG.");
            return false;
        }

        if (file.getSize() > MAX_FILE_SIZE_BYTES) {
            addError(context, "Tamanho máximo permitido: 2MB.");
            return false;
        }

        try (InputStream is = file.getInputStream()) {
            BufferedImage image = ImageIO.read(is);
            if (image == null) {
                addError(context, "Arquivo não é uma imagem válida.");
                return false;
            }

            if (image.getWidth() > MAX_WIDTH || image.getHeight() > MAX_HEIGHT) {
                addError(context, String.format("Dimensões máximas permitidas: %dx%dpx.", MAX_WIDTH, MAX_HEIGHT));
                return false;
            }

            return true;
        } catch (IOException e) {
            addError(context, "Erro ao processar a imagem.");
            return false;
        }
    }

    private void addError(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
    }
}