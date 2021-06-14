package com.eduonix.votingsysapp.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j; 
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGasPrice;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;
import org.web3j.utils.Convert.Unit;

public class EthereumConnection {

	public void Ethers(BigDecimal[] eth) throws Exception{
		//System.out.println("Connecting to Ethereum …");
		Web3j web3 = Web3j.build(new HttpService("https://ropsten.infura.io/v3/28f29ffe2c9b4a59bf30b457eb186315"));
		BigInteger[] bal= new BigInteger[6];
		//BigDecimal[] eth= new BigDecimal[6];
		bal[0]=BigInteger.ZERO;
		eth[0]=BigDecimal.ZERO;
		//System.out.println("Successfuly connected to Ethereum"); 
		
		try {
		Web3ClientVersion clientVersion = web3.web3ClientVersion().send();
		//System.out.println("Client version: " + clientVersion.getWeb3ClientVersion());
		EthGasPrice gasPrice = web3.ethGasPrice().send();
		//System.out.println("Default Gas Price: "+gasPrice.getGasPrice());
		
		//1
		EthGetBalance ethGetBalance1 = web3
		.ethGetBalance("0x05ac935148a343ab323a311ade81675ca91a7a3a", DefaultBlockParameterName.LATEST)
		.sendAsync().get();
		//System.out.println("Balance: of Account ‘0xc897e98fc9c5602bc6d0212b30778be451cf0ce1’ "
		//+ ethGetBalance.getBalance());
		bal[1] = ethGetBalance1.getBalance();
		//System.out.println("Balance in Ether format: "
		//+Convert.fromWei(web3.ethGetBalance("0xa3e67e20b4374e64e92cf8c92ffb060da5677fe5",
		//DefaultBlockParameterName.LATEST).send().getBalance().toString(),Unit.ETHER));
		eth[1] = Convert.fromWei(web3.ethGetBalance("0x05ac935148a343ab323a311ade81675ca91a7a3a",
				DefaultBlockParameterName.LATEST).send().getBalance().toString(),Unit.ETHER);
		
		//2
		EthGetBalance ethGetBalance2 = web3
			.ethGetBalance("0x443004ab25888014411a556851b9f3b8c2b40c3d", DefaultBlockParameterName.LATEST)
			.sendAsync().get();
		bal[2] = ethGetBalance2.getBalance();
		eth[2] = Convert.fromWei(web3.ethGetBalance("0x443004ab25888014411a556851b9f3b8c2b40c3d",
				DefaultBlockParameterName.LATEST).send().getBalance().toString(),Unit.ETHER);
		
		//3
		EthGetBalance ethGetBalance3 = web3
			.ethGetBalance("0xa208489ba9bb6c697d2e914b853cf3267280f97b", DefaultBlockParameterName.LATEST)
			.sendAsync().get();
		bal[3] = ethGetBalance3.getBalance();
		eth[3] = Convert.fromWei(web3.ethGetBalance("0xa208489ba9bb6c697d2e914b853cf3267280f97b",
				DefaultBlockParameterName.LATEST).send().getBalance().toString(),Unit.ETHER);
		
		//4
		EthGetBalance ethGetBalance4 = web3
			.ethGetBalance("0x0c8be7e27a8f858fad80d1157ae611b78122badb", DefaultBlockParameterName.LATEST)
			.sendAsync().get();
		bal[4] = ethGetBalance4.getBalance();
		eth[4] = Convert.fromWei(web3.ethGetBalance("0x0c8be7e27a8f858fad80d1157ae611b78122badb",
				DefaultBlockParameterName.LATEST).send().getBalance().toString(),Unit.ETHER);
		
		//5
		EthGetBalance ethGetBalance5 = web3
			.ethGetBalance("0xf1be671a8209e945d0c2235146624db54d08c7b7", DefaultBlockParameterName.LATEST)
			.sendAsync().get();
		bal[5] = ethGetBalance5.getBalance();
		eth[5] = Convert.fromWei(web3.ethGetBalance("0xf1be671a8209e945d0c2235146624db54d08c7b7",
				DefaultBlockParameterName.LATEST).send().getBalance().toString(),Unit.ETHER);
		}
		catch (IOException ex) {
		throw new RuntimeException("Error whilst sending json-rpc requests", ex);
		}
}

}
