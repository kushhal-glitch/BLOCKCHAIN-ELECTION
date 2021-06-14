package com.eduonix.votingsysapp.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.crypto.CipherException;

import com.eduonix.votingsysapp.entity.Citizen;
import com.eduonix.votingsysapp.repositories.CandidateRepo;
import com.eduonix.votingsysapp.repositories.CitizenRepo;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.eduonix.votingsysapp.entity.Candidate;

@Controller
public class VotingController {

	@Autowired
	CitizenRepo citizenRepo;
	
	@Autowired
	CandidateRepo candidateRepo;
	
	int can,smar,i;
	String pkey,aadh;
	BigDecimal[] eth = new BigDecimal[6];
	
	@RequestMapping("/")
	public String home() {
		return "home.html";
	}
	
	@RequestMapping("/dohome")
	public String dohome() {
		return "home.html";
		//1
	}
	
	@RequestMapping("/dosignup")
	public String dosignup(Model model){
			model.addAttribute("citizen", new Citizen());
			return "signup.html";
		//2
	}
	
	@RequestMapping("/doprocessregister")
	public String doprocessregister(Citizen citizen,@RequestParam String aadharid,
			@RequestParam String password, @RequestParam String email) 
					throws IOException, InterruptedException, CipherException {
		Aadhar_validation av=new Aadhar_validation();
		if(av.aadharvalidate(aadharid)=="valid") {
			aadh=aadharid;
			CreateAccountWithSeeds acc=new CreateAccountWithSeeds();
			acc.account(password,email);
			citizenRepo.save(citizen);
			return "registersuccess.html";
		}
		else {
			return "registerfail.html";
		}
		//3
	}
	
	@RequestMapping("/dologin")
	public String dologin() {
		return "login.html";
		//4
	}
	
	@RequestMapping("/doafterlogin")
	public String doafterlogin(@RequestParam String aadharid, @RequestParam String privatekey, @RequestParam String password) {
		Citizen c = citizenRepo.findByAadharid(aadharid);
		c.setPrivatekey(privatekey);
		citizenRepo.save(c);
		pkey=privatekey;
		return "afterlogin.html";
		//5
	}
	

	@RequestMapping("/doreturn")
	public String dovotes() {
		return "afterlogin.html";
		//5a
	}

	
	@RequestMapping("/doqr")
	public String doqr() throws NotFoundException, WriterException, IOException {
		MyQr q=new MyQr();
		q.qr(pkey);
		return "qr.html";
		//6
	}
	
	@RequestMapping("/dolearnmore")
	public String dolearnmore() {
		return "learnmore.html";
		//7
	}
	
	@RequestMapping("/docandidates")
	public String docandidates() {
		return "candidates.html";
		//8
	}
	
	@RequestMapping("/doresult")
	public String doresult(Model model) throws Exception {
		EthereumConnection e=new EthereumConnection();
		e.Ethers(eth);
		for (int i = 1; i < 6; i++) {
			Candidate ca = candidateRepo.findById(i);
			ca.setNumberofvotes(eth[i].intValue());
			candidateRepo.save(ca);		
		}
		List<Candidate> candidates=candidateRepo.findAll();
		model.addAttribute("candidates",candidates);
		return "result.html";
		//9
	}
	
	@RequestMapping("/doaadhar")
	public String aadhar() {
		return "aadhar.html";
		//10
	}
	
	@RequestMapping("/docastvote")
	public String docastvote(@RequestParam String aadharid, Model model, HttpSession session,@RequestParam String password) {
		Citizen citizen=citizenRepo.findByAadharid(aadharid);
		session.setAttribute("citizen", citizen);
		if(!citizen.getHasvoted()) {
			List<Candidate> candidates=candidateRepo.findAll();
			model.addAttribute("candidates",candidates);
			return "/castvote.html";
		}
		else
		{
			return "/alreadyVoted.html";
		}
		//11
	}
	
	@RequestMapping("/dosuccess")
	public String dosuccess(@RequestParam Integer id, Model model, HttpSession session) {
		Citizen citizen=citizenRepo.findByAadharid(aadh);
		session.setAttribute("citizen", citizen);
		Candidate c = candidateRepo.findById((int)id);
		can=id-1;
		smart_contract sm=new smart_contract();
		smar=sm.smart(can,pkey);
		List<Candidate> candidates=candidateRepo.findAll();
		model.addAttribute("candidates",candidates);
		if(smar==1) {
			citizen.setHasvoted(true);
			citizenRepo.save(citizen);
			return "success.html";
		}
		else {
			return "failure.html";
		}
		//12
	}
	
}
