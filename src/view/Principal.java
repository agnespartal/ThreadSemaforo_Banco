package view;

import java.util.concurrent.Semaphore;

import controller.BancoController;

public class Principal {

	public static void main(String[] args) {
		
		int permissao = 1;
		Semaphore saque = new Semaphore(permissao);
		Semaphore deposito = new Semaphore(permissao);
		
		for (int i = 0; i < 20; i++) {
			Thread banco = new BancoController(saque, deposito);
			banco.start();
		}

	}

}
