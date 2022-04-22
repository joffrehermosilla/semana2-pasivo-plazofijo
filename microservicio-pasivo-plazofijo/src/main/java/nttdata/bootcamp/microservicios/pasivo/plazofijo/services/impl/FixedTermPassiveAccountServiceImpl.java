package nttdata.bootcamp.microservicios.pasivo.plazofijo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import nttdata.bootcamp.microservicios.pasivo.plazofijo.documents.FixedTermPassiveAccount;
import nttdata.bootcamp.microservicios.pasivo.plazofijo.repository.FixedTermPassiveAccountRepository;
import nttdata.bootcamp.microservicios.pasivo.plazofijo.services.FixedTermPassiveAccountService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FixedTermPassiveAccountServiceImpl implements FixedTermPassiveAccountService {

	@Autowired
	FixedTermPassiveAccountRepository repository;

	@Override
	public Mono<FixedTermPassiveAccount> findById(String id) {
		// TODO Auto-generated method stub
		return repository.findById(id);
	}

	@Override
	public Flux<FixedTermPassiveAccount> findAlls() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public Mono<FixedTermPassiveAccount> saves(FixedTermPassiveAccount document) {
		// TODO Auto-generated method stub
		return repository.save(document);
	}

	@Override
	public Mono<Void> delete(FixedTermPassiveAccount document) {
		// TODO Auto-generated method stub
		return repository.delete(document);
	}

}
