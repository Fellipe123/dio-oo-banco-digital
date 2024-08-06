package controller;

import model.Cliente;
import model.Conta;
import model.ContaCorrente;
import model.ContaPoupanca;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.JTextArea;

public class ContaController {

    private List<Conta> contas = new ArrayList<>();

    public void criarContaCorrente(String nomeCliente) {
        Cliente cliente = new Cliente();
        cliente.setNome(nomeCliente);
        Conta conta = new ContaCorrente(cliente);
        contas.add(conta);
    }

    public void criarContaPoupanca(String nomeCliente) {
        Cliente cliente = new Cliente();
        cliente.setNome(nomeCliente);
        Conta conta = new ContaPoupanca(cliente);
        contas.add(conta);
    }

    public Optional<Conta> encontrarContaPorNumero(int numeroConta) {
        return contas.stream().filter(conta -> conta.getNumero() == numeroConta).findFirst();
    }

    public void sacar(int numeroConta, double valor) {
        encontrarContaPorNumero(numeroConta).ifPresent(conta -> conta.sacar(valor));
    }

    public void depositar(int numeroConta, double valor) {
        encontrarContaPorNumero(numeroConta).ifPresent(conta -> conta.depositar(valor));
    }

    public void transferir(int numeroContaOrigem, int numeroContaDestino, double valor) {
        Optional<Conta> contaOrigem = encontrarContaPorNumero(numeroContaOrigem);
        Optional<Conta> contaDestino = encontrarContaPorNumero(numeroContaDestino);
        if (contaOrigem.isPresent() && contaDestino.isPresent()) {
            contaOrigem.get().transferir(valor, contaDestino.get());
        }
    }

    public void imprimirExtrato(int numeroConta, JTextArea textArea) {
        encontrarContaPorNumero(numeroConta).ifPresent(conta -> {
            textArea.append("=== Extrato ===\n");
            textArea.append(String.format("Titular: %s\n", conta.getCliente().getNome()));
            textArea.append(String.format("Agencia: %d\n", conta.getAgencia()));
            textArea.append(String.format("Numero: %d\n", conta.getNumero()));
            textArea.append(String.format("Saldo: %.2f\n", conta.getSaldo()));
        });
    }
}