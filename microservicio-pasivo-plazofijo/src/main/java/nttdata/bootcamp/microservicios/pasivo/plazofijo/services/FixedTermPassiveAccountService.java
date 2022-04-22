package nttdata.bootcamp.microservicios.pasivo.plazofijo.services;

import nttdata.bootcamp.microservicios.pasivo.plazofijo.documents.FixedTermPassiveAccount;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FixedTermPassiveAccountService {
	public Mono<FixedTermPassiveAccount> findById(String id);

	public Flux<FixedTermPassiveAccount> findAlls();

	public Mono<FixedTermPassiveAccount> saves(FixedTermPassiveAccount document);

	public Mono<Void> delete(FixedTermPassiveAccount document);
}
