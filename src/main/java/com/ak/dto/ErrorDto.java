package com.ak.dto;

import java.time.LocalDateTime;

public record ErrorDto(LocalDateTime timeStamp,
                       Integer HttpStatus,
                       String errorMsg,
                       String path) {
}
