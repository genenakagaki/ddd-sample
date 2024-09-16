package com.genenakagaki.checklist.domain;

import java.nio.ByteBuffer;
import java.util.Base64;
import java.util.UUID;

public record Base64UUID(String value) {

    public static Base64UUID create() {
        UUID uuid = UUID.randomUUID();
        ByteBuffer byteBuffer = ByteBuffer.wrap(new byte[16]);
        byteBuffer.putLong(uuid.getMostSignificantBits());
        byteBuffer.putLong(uuid.getLeastSignificantBits());
        String encodedUUID = Base64.getUrlEncoder().encodeToString(byteBuffer.array());
        return new Base64UUID(encodedUUID);
    }
}
