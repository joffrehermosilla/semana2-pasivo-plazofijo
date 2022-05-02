package nttdata.bootcamp.microservicios.pasivo.plazofijo.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	@Value("${config.balanceador.test}")
	private String balanceadorTest;

	@GetMapping("/balanceador-test")
	public ResponseEntity<?> balanceadorTest() {

		Map<String, Object> response = new HashMap<String, Object>();
		response.put("balanceador", balanceadorTest);
		response.put("passive_fixedterm", service.findAlls());
		return ResponseEntity.ok(response);

	}

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

	@PostMapping("/create-fixedterm-passive-account")
	public Mono<FixedTermPassiveAccount> fixedTermPassiveAccount(
			@Valid @RequestBody FixedTermPassiveAccount fixedTermAccount) {
		LOGGER.info("FIXED TERM PASSING ACCOUNT create: " + service.saves(fixedTermAccount));
		Mono.just(fixedTermAccount).doOnNext(t -> {

			t.setCreateAt(new Date());

		}).onErrorReturn(fixedTermAccount).onErrorResume(e -> Mono.just(fixedTermAccount))
				.onErrorMap(f -> new InterruptedException(f.getMessage())).subscribe(x -> LOGGER.info(x.toString()));

		Mono<FixedTermPassiveAccount> newFixedTermAccount = service.saves(fixedTermAccount);

		return newFixedTermAccount;
	}

	@PutMapping("/update-passive-fixedterm/{id}")
	public ResponseEntity<Mono<?>> updatePassiveCurrentAccount(@PathVariable String id,
			@Valid @RequestBody FixedTermPassiveAccount fixedTermAccount) {
		Mono.just(fixedTermAccount).doOnNext(t -> {
			fixedTermAccount.setId(id);
			t.setCreateAt(new Date());
		}).onErrorReturn(fixedTermAccount).onErrorResume(e -> Mono.just(fixedTermAccount))
				.onErrorMap(f -> new InterruptedException(f.getMessage())).subscribe(x -> LOGGER.info(x.toString()));
		Mono<FixedTermPassiveAccount> newFixedTermAccount = service.saves(fixedTermAccount);
		if (newFixedTermAccount != null) {
			return new ResponseEntity<>(newFixedTermAccount, HttpStatus.CREATED);
		}
		return new ResponseEntity<>(Mono.just(new FixedTermPassiveAccount()), HttpStatus.I_AM_A_TEAPOT);
	}

	@DeleteMapping("/delete-passive-fixedterm/{id}")
	public ResponseEntity<Mono<Void>> deletePassiveFixedTerm(@PathVariable String id) {
		FixedTermPassiveAccount passiveFixedTermn = new FixedTermPassiveAccount();
		passiveFixedTermn.setId(id);
		Mono<FixedTermPassiveAccount> newPassiveFixedTerm = service.findById(id);
		newPassiveFixedTerm.subscribe();
		Mono<Void> test = service.delete(passiveFixedTermn);
		test.subscribe();
		return ResponseEntity.noContent().build();
	}

}
