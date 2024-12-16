package com.xh;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @author Xiao Hong
 * @since 2023-03-02
 */
@RestController
public class StatusController {
    private final Flux<Status> statusStream;

    public StatusController(StatusService statusService) {
        this.statusStream = statusService.getStatusStream();
    }

    @GetMapping(value = "/status-stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Status> getStatusStream() {
        return statusStream;
    }
}
