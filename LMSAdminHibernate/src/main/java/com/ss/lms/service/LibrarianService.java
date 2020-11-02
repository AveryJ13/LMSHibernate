package com.ss.lms.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.lms.entity.Branch;
import com.ss.lms.entity.Copies;
import com.ss.lms.entity.CopiesId;
import com.ss.lms.repo.BranchRepo;
import com.ss.lms.repo.CopiesRepo;

@RestController
public class LibrarianService {

	@Autowired
	CopiesRepo crepo;
	BranchRepo bchrepo;
	
	//Updating branch values is implemented in AdminServices
	@Transactional
	@RequestMapping(value = "/librarian/updateBranch", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	public List<Branch> librarianUpdateBranch(@RequestParam Integer branchId, @RequestBody Branch branch) throws SQLException {
				List<Branch> oldBranch = bchrepo.readBranchesById(branchId);
				branch.setBranchId(branchId);
				
				if(branch.getBranchName() == null) {
					branch.setBranchName(oldBranch.get(0).getBranchName());
				}
				if(branch.getBranchAddress() == null) {
					branch.setBranchAddress(oldBranch.get(0).getBranchAddress());
				}
				bchrepo.save(branch);
				return bchrepo.findAll();
	}
	
	//read copies
	@RequestMapping(value = "/getCopiesByBookIdBranchId", method = RequestMethod.GET, produces = "application/json")
	public List<Copies> getCopiesByBookIdBranchId(@RequestParam Integer bookId, @RequestParam Integer branchId) {
		List<Copies> copies = new ArrayList<>();
		if(bookId != null && branchId != null) {
			copies = crepo.readCopiesById(bookId, branchId);
		} else {
			copies = crepo.findAll();
		}
		return copies;
	}
	
	
	@RequestMapping(value="/updateCopies", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	public List<Copies> updateCopies(@RequestParam Integer bookId, @RequestParam Integer branchId, @RequestBody Copies copy){
		List<Copies> oldCopies = crepo.readCopiesById(bookId, branchId);
		CopiesId id = oldCopies.get(0).getId();
		copy.setId(id);
		if(copy.getNoOfCopies() == null) {
			copy.setNoOfCopies(oldCopies.get(0).getNoOfCopies());
		}
		crepo.save(copy);
		return crepo.findAll();
	}


	@RequestMapping(value="/addCopies", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public List<Copies> addCopies(@RequestParam Integer bookId, @RequestParam Integer branchId, @RequestBody Copies copy){
		CopiesId id = new CopiesId(bookId, branchId);
		copy.setId(id);
		crepo.save(copy);
		return crepo.findAll();
	}


}