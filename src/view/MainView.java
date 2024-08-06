package view;

import controller.ContaController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView {

    private ContaController contaController = new ContaController();

    public static void main(String[] args) {
        new MainView().createAndShowGUI();
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Sistema Bancário");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel labelNome = new JLabel("Nome do Cliente:");
        labelNome.setBounds(10, 10, 120, 25);
        frame.add(labelNome);

        JTextField textNome = new JTextField();
        textNome.setBounds(150, 10, 200, 25);
        frame.add(textNome);

        JButton btnCriarContaCorrente = new JButton("Criar Conta Corrente");
        btnCriarContaCorrente.setBounds(10, 50, 170, 25);
        frame.add(btnCriarContaCorrente);

        JButton btnCriarContaPoupanca = new JButton("Criar Conta Poupança");
        btnCriarContaPoupanca.setBounds(190, 50, 170, 25);
        frame.add(btnCriarContaPoupanca);

        JLabel labelNumeroConta = new JLabel("Número da Conta:");
        labelNumeroConta.setBounds(10, 90, 120, 25);
        frame.add(labelNumeroConta);

        JTextField textNumeroConta = new JTextField();
        textNumeroConta.setBounds(150, 90, 200, 25);
        frame.add(textNumeroConta);

        JLabel labelValor = new JLabel("Valor:");
        labelValor.setBounds(10, 130, 120, 25);
        frame.add(labelValor);

        JTextField textValor = new JTextField();
        textValor.setBounds(150, 130, 200, 25);
        frame.add(textValor);

        JButton btnSacar = new JButton("Sacar");
        btnSacar.setBounds(10, 170, 80, 25);
        frame.add(btnSacar);

        JButton btnDepositar = new JButton("Depositar");
        btnDepositar.setBounds(100, 170, 100, 25);
        frame.add(btnDepositar);

        JButton btnTransferir = new JButton("Transferir");
        btnTransferir.setBounds(210, 170, 100, 25);
        frame.add(btnTransferir);

        JButton btnImprimirExtrato = new JButton("Imprimir Extrato");
        btnImprimirExtrato.setBounds(320, 170, 150, 25);
        frame.add(btnImprimirExtrato);

        JLabel labelNumeroContaDestino = new JLabel("Conta Destino:");
        labelNumeroContaDestino.setBounds(10, 210, 120, 25);
        frame.add(labelNumeroContaDestino);

        JTextField textNumeroContaDestino = new JTextField();
        textNumeroContaDestino.setBounds(150, 210, 200, 25);
        frame.add(textNumeroContaDestino);

        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(10, 250, 460, 200);
        frame.add(scrollPane);

        btnCriarContaCorrente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contaController.criarContaCorrente(textNome.getText());
                textArea.append("Conta Corrente criada para " + textNome.getText() + "\n");
            }
        });

        btnCriarContaPoupanca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contaController.criarContaPoupanca(textNome.getText());
                textArea.append("Conta Poupança criada para " + textNome.getText() + "\n");
            }
        });

        btnSacar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numeroConta = Integer.parseInt(textNumeroConta.getText());
                double valor = Double.parseDouble(textValor.getText());
                contaController.sacar(numeroConta, valor);
                textArea.append("Saque de R$" + valor + " realizado na conta " + numeroConta + "\n");
            }
        });

        btnDepositar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numeroConta = Integer.parseInt(textNumeroConta.getText());
                double valor = Double.parseDouble(textValor.getText());
                contaController.depositar(numeroConta, valor);
                textArea.append("Depósito de R$" + valor + " realizado na conta " + numeroConta + "\n");
            }
        });

        btnTransferir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numeroContaOrigem = Integer.parseInt(textNumeroConta.getText());
                int numeroContaDestino = Integer.parseInt(textNumeroContaDestino.getText());
                double valor = Double.parseDouble(textValor.getText());
                contaController.transferir(numeroContaOrigem, numeroContaDestino, valor);
                textArea.append("Transferência de R$" + valor + " da conta " + numeroContaOrigem + " para a conta " + numeroContaDestino + "\n");
            }
        });

        btnImprimirExtrato.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numeroConta = Integer.parseInt(textNumeroConta.getText());
                contaController.imprimirExtrato(numeroConta, textArea);
            }
        });

        frame.setVisible(true);
    }
}