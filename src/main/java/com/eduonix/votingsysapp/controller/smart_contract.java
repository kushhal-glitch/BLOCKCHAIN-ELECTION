package com.eduonix.votingsysapp.controller;

import java.util.*;

import com.eduonix.votingsysapp.controller.smart_contract;
import com.eduonix.votingsysapp.controller.voting;

public class smart_contract {
int s,sm;
	public int add_candidate(int i,String voter_pvt)
	{

	    HashMap<Integer, String> candidate_key = new HashMap<>();
	    candidate_key.put(0,"0x05ac935148a343ab323a311ade81675ca91a7a3a");
	    candidate_key.put(1,"0x443004ab25888014411a556851b9f3b8c2b40c3d");
	    candidate_key.put(2,"0xa208489ba9bb6c697d2e914b853cf3267280f97b");
	    candidate_key.put(3,"0x0c8be7e27a8f858fad80d1157ae611b78122badb");
	    candidate_key.put(4,"0xf1be671a8209e945d0c2235146624db54d08c7b7");
	    
		s=voting.vote(i,candidate_key.get(i),voter_pvt);
		return s;
	}

	    public int smart(int c, String pvt) {
	    	 { 
	        // TODO code application logic here
	    	//smart_contract s=new smart_contract();
	    	
	    	
	    	//System.out.println("ENTER THE VOTERS PRIVATE KEY FOR VOTING");
	       	//Scanner sc = new Scanner(System.in);
	       	//String pvt = sc.next();
	    	sm=new smart_contract().add_candidate(c,pvt);
	    	return sm;
	    }
	}

}
