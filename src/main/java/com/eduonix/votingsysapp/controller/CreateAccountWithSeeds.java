package com.eduonix.votingsysapp.controller;

import java.io.*;
import java.util.*;

import javax.mail.MessagingException;

import org.web3j.crypto.Bip32ECKeyPair;
import org.web3j.crypto.Bip39Wallet;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.MnemonicUtils;
import org.web3j.crypto.WalletUtils;

import com.eduonix.votingsysapp.controller.JavaMailUtil1;

public class CreateAccountWithSeeds {

	public void account(String pwd, String mail)
			throws IOException ,InterruptedException, CipherException{
		// TODO Auto-generated method stub
		//System.out.println("Creating New Account for the voter");
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//System.out.println("Enter New Password");
		String walletPassword =pwd;
		
		File walletDirectory = new File("D:\\Amrutha\\College\\6th Sem\\CIP\\AANew3");
		Bip39Wallet walletName = WalletUtils.generateBip39Wallet(walletPassword, walletDirectory);
		
		//System.out.println(" Voters wallet location: " + walletDirectory + "/" + walletName);
		
		Credentials credentials = WalletUtils.loadBip39Credentials(walletPassword, walletName.getMnemonic());
		String accountAddress = credentials.getAddress();
		//System.out.println("Voters Account address: " + credentials.getAddress());
		ECKeyPair privateKey = credentials.getEcKeyPair();
		String seedPhrase = walletName.getMnemonic();
		//System.out.println("Voters Account Details:");
		//System.out.println("Your New Account : " + credentials.getAddress());
		//System.out.println("Mneminic Code:"  + walletName.getMnemonic());
		//System.out.println("Private Key: " + privateKey.getPrivateKey().toString(16));
		//System.out.println("Public Key: " + privateKey.getPublicKey().toString(16));
		try {
			JavaMailUtil1.sendMail(mail,credentials.getAddress(),walletName.getMnemonic(), privateKey.getPrivateKey().toString(16), privateKey.getPublicKey().toString(16));
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
 }
}
