package de.fraunhofer.iosb.maypadbackend.services.sse;

import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.DirectProcessor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxProcessor;
import reactor.core.publisher.FluxSink;



@Service
public class SseService {
    private final FluxProcessor<EventData, EventData> processor;
    private final FluxSink<EventData> sink;

    public SseService() {
        this.processor = DirectProcessor.<EventData>create().serialize();
        this.sink = processor.sink();
    }

    public Flux<ServerSentEvent<EventData>> get() {
        return processor.map(e -> ServerSentEvent.builder(e).event(e.getEventId()).build());
    }

    /**
     * Pushes an event with the given EventData to the connected clients.
     * @param e the EventData
     */
    public void push(EventData e) {
        sink.next(e);
    }
}
