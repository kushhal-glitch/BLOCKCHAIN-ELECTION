package com.eduonix.votingsysapp.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;

import javax.mail.MessagingException;

import org.bson.Document;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;
import org.web3j.utils.Convert.Unit;

import com.eduonix.votingsysapp.controller.PKC_send;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.web3j.utils.Numeric;

public class voting {

	public static int vote(int i,String recipientAddress,String privetKey)
	{
		int tran=0;
		//System.out.println("Connecting to Ethereum ...");
		Web3j web3 = Web3j.build(new HttpService("https://ropsten.infura.io/v3/28f29ffe2c9b4a59bf30b457eb186315"));
		//System.out.println("Successfuly connected to Ethereum");
		ConnectionString connString = new ConnectionString(
				"mongodb+srv://kushhal:mack@cluster0.fdyi0.mongodb.net/test"
		);
		MongoClientSettings settings = MongoClientSettings.builder()
		    .applyConnectionString(connString)
		    .retryWrites(true)
		    .build();
		MongoClient mongoClient = MongoClients.create(settings);
		MongoDatabase mongoDatabase = mongoClient.getDatabase("Block");
		MongoCollection collection = mongoDatabase.getCollection("test");
		try {
		//String  privetKey= "0x792017603d210ce3d3f5983c0715f7fc09963e7a02716467eac93e5da169ecaf"; // Add a private key here

		Credentials credentials = Credentials.create(privetKey);
		//System.out.println("Account address: " + credentials.getAddress());
		//System.out.println("Balance: "
		//+ Convert.fromWei(web3.ethGetBalance(credentials.getAddress(), DefaultBlockParameterName.LATEST)
		//.send().getBalance().toString(), Unit.ETHER));


		EthGetTransactionCount ethGetTransactionCount = web3
		.ethGetTransactionCount(credentials.getAddress(), DefaultBlockParameterName.LATEST).send();
		BigInteger nonce = ethGetTransactionCount.getTransactionCount();


		//String recipientAddress = "0x48853693793a32af680a39c7936e9b8d18f3657d";



		//System.out.println("Enter Amount to be sent:");
		String amountToBeSent="1";
		
		BigInteger value = Convert.toWei(amountToBeSent, Unit.ETHER).toBigInteger();



		BigInteger gasLimit = BigInteger.valueOf(21000);
		BigInteger gasPrice = Convert.toWei("1", Unit.GWEI).toBigInteger();


		RawTransaction rawTransaction = RawTransaction.createEtherTransaction(nonce, gasPrice, gasLimit,
		recipientAddress, value);


		byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
		String hexValue = Numeric.toHexString(signedMessage);


		EthSendTransaction ethSendTransaction = web3.ethSendRawTransaction(hexValue).send();
		String transactionHash = ethSendTransaction.getTransactionHash();
		//System.out.println("transactionHash: " + transactionHash);
		Document document = new Document("TransactionHash",transactionHash);
		
		switch(recipientAddress) {
		case "0x05ac935148a343ab323a311ade81675ca91a7a3a": document.append("candidate Party","IRONMAN");
		break;
		case "0x443004ab25888014411a556851b9f3b8c2b40c3d": document.append("candidate Party","THOR");
		break;
		case "0xa208489ba9bb6c697d2e914b853cf3267280f97b": document.append("candidate Party", "HULK");
		break;
		case "0x0c8be7e27a8f858fad80d1157ae611b78122badb": document.append("candidate Party", "SPIDER_MAN");
		break;
		case "0xf1be671a8209e945d0c2235146624db54d08c7b7": document.append("candidate Party", "LOKI");
		break;
		}
		document.append("voters secretKEY",privetKey);
		document.append("Candidate Address",recipientAddress);
		

		Optional<TransactionReceipt> transactionReceipt = null;

		//System.out.println("checking if transaction " + transactionHash + " is mined....");
		EthGetTransactionReceipt ethGetTransactionReceiptResp = web3.ethGetTransactionReceipt(transactionHash)
		.send();
		transactionReceipt = ethGetTransactionReceiptResp.getTransactionReceipt();
		Thread.sleep(3000);

		document.append("Voting Status","success");
		collection.insertOne(document);
		try {
			if(transactionHash!=null) {
				tran=1;
			PKC_send.sendMail("kushhalsathish08@gmail.com");
			}
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	/*System.out.println("Transaction " + transactionHash + " was mined in block  "
		+ transactionReceipt.get().getBlockNumber());
		
		
		Credentials credentials1 = Credentials.create("3fd9a20896916a699a633bdf27c2679b7b08806007c5113a16c694c3d0acedfb");
		System.out.println("Account address: " + credentials1.getAddress());
		BigDecimal balance1 = Convert.fromWei(web3.ethGetBalance(credentials1.getAddress(), DefaultBlockParameterName.LATEST)
				.send().getBalance().toString(), Unit.ETHER);
				BigDecimal balance3 = new BigDecimal("99.99979");
				BigDecimal balance2 = balance1.divide(balance3);
				System.out.println("SANGHIS VOTE");
				System.out.println(balance2);*/
				
				
		} catch (IOException | InterruptedException ex) {
		throw new RuntimeException(ex);
		}
		return tran;
	}

}
