package nttdata.bootcamp.microservicios.pasivo.plazofijo.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import nttdata.bootcamp.microservicios.pasivo.plazofijo.documents.FixedTermPassiveAccount;
import nttdata.bootcamp.microservicios.pasivo.plazofijo.services.FixedTermPassiveAccountService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class FixedTermPassiveAccountController {
	private static final Logger LOGGER = LoggerFactory.getLogger(FixedTermPassiveAccountController.class);
	@Autowired
	private FixedTermPassiveAccountService service;

	@GetMapping("/all")
	public Flux<FixedTermPassiveAccount> searchAll() {
		Flux<FixedTermPassiveAccount> fixedTermPassingAccount = service.findAlls();
		LOGGER.info("FIXED TERM PASSING ACCOUNT registered: " + fixedTermPassingAccount);
		return fixedTermPassingAccount;
	}

	@GetMapping("/id/{id}")
	public Mono<FixedTermPassiveAccount> searchById(@PathVariable String id) {
		LOGGER.info("FIXED TERM PASSING ACCOUNT id: " + service.findById(id) + " code : " + id);
		return service.findById(id);
	}

	@PostMapping("/fixed-term-passive-account")
	public Mono<FixedTermPassiveAccount> fixedTermPassiveAccount(
			@Valid @RequestBody FixedTermPassiveAccount passiveSavingAccount) {
		LOGGER.info("FIXED TERM PASSING ACCOUNT create: " + service.saves(passiveSavingAccount));
		return service.saves(passiveSavingAccount);
	}

}
