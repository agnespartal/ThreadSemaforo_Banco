package controller;

import java.util.concurrent.Semaphore;

public class BancoController extends Thread {

	private int tipoTransacao;
	private int codigoConta;
	private int saldoConta;
	private int valorTransacao;
	private Semaphore saque;
	private Semaphore deposito;

	public BancoController(Semaphore saque, Semaphore deposito) {
		this.saque = saque;
		this.deposito = deposito;
	}

	@Override
	public void run() {
		transacao();
	}

	private void transacao() {
		tipoTransacao = (int) ((Math.random() * 2) + 1);

		if (tipoTransacao == 1) {
			
			try {
				saque.acquire();
				System.out.println("#" + getId() + " ===> Saque");
				calcularTransacao();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				saque.release();
			}
		} else {
			
			try {
				deposito.acquire();
				System.out.println("#" + getId() + " ===> Deposito");
				calcularTransacao();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				deposito.release();
			}
		}
	}

	private void calcularTransacao() {
		codigoConta = (int) ((Math.random() * 2054) + 2026);
		saldoConta = (int) ((Math.random() * 105) + 102);
		valorTransacao = (int) ((Math.random() * 51) + 20);

		if (tipoTransacao == 1) {
			System.out.println("#" + getId() + "==> Conta: " + codigoConta + "\nSaldo da conta: " + saldoConta
					+ "\nValor do saque: " + valorTransacao);
		} else {
			System.out.println("#" + getId() + "==> Conta: " + codigoConta + "\nSaldo da conta: " + saldoConta
					+ "\nValor do deposito: " + valorTransacao);
		}

	}

}
