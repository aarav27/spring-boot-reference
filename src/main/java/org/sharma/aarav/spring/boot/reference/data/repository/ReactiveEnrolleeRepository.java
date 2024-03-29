package org.sharma.aarav.spring.boot.reference.data.repository;

import org.sharma.aarav.spring.boot.reference.data.model.Enrollee;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Aarav Sharma
 */
public interface ReactiveEnrolleeRepository extends ReactiveCrudRepository<Enrollee, String> {

    Mono<Enrollee> findByEnrolleeId(String enrolleeId);

    Mono<Enrollee> findByPersonId(String personId);

    Flux<Enrollee> findByEnrolleeName(String enrolleeName);

}
