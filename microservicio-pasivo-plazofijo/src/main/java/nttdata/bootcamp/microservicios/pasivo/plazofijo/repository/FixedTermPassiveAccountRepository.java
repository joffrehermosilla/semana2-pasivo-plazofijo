package nttdata.bootcamp.microservicios.pasivo.plazofijo.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import nttdata.bootcamp.microservicios.pasivo.plazofijo.documents.FixedTermPassiveAccount;

public interface FixedTermPassiveAccountRepository extends ReactiveMongoRepository<FixedTermPassiveAccount, String> {

}
