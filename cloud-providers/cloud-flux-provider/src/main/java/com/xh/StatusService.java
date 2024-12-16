package com.xh;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

/**
 * @author Xiao Hong
 * @since 2023-03-02
 */
@Service
public class StatusService {

    private FluxSink<Status> sink;

    private final Flux<Status> statusStream;

    public StatusService() {
        this.statusStream = Flux.<Status>create(sink -> this.sink = sink).share();
    }

    public void updateStatus(Status status) {
        sink.next(status);
    }

    public Flux<Status> getStatusStream() {
        return statusStream;
    }


    @Scheduled(fixedRate = 1000 * 10)
    public void update() {
        long i = 1;
        Status status = new Status();
        status.setId(1L);
        status.setValue(++i);
        updateStatus(status);
    }
}

