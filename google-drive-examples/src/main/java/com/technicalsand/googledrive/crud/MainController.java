package com.technicalsand.googledrive.crud;

import com.google.api.services.drive.model.File;
import com.technicalsand.googledrive.crud.model.Review;
import com.technicalsand.googledrive.crud.Repository.PostgresRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class MainController {
	private FileManager fileManager;

	@Autowired
	PostgresRepository repository;

	@GetMapping("/save_review")
	public String save_review(){
		// save a single Customer
		repository.save(new Review("Livio", "Olio fantastico, mi piace molto", "ID1;ID2"));
		
		return "Review created";
	}

	@GetMapping({"/"})
	public ResponseEntity<List<File>> listEverything() throws IOException, GeneralSecurityException {
		System.out.println("SONO IN LIST EVERYTHING");
		log.info("LOG - SONO IN LIST EVERYTHING ");
		List<File> files = fileManager.listEverything();
		return ResponseEntity.ok(files);
	}

	@GetMapping({"/list","/list/{parentId}"})
	public ResponseEntity<List<File>> list(@PathVariable(required = false) String parentId) throws IOException, GeneralSecurityException {
		List<File> files = fileManager.listFolderContent(parentId);
		return ResponseEntity.ok(files);
	}

	@GetMapping("/download/{id}")
	public void download(@PathVariable String id, HttpServletResponse response) throws IOException, GeneralSecurityException {
		fileManager.downloadFile(id, response.getOutputStream());
	}

	@GetMapping("/directory/create")
	public ResponseEntity<String> createDirecory(@RequestParam String path) throws Exception {
		String parentId = fileManager.getFolderId(path);
		return ResponseEntity.ok("parentId: "+parentId);
	}

	@PostMapping(value = "/upload",
			consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE} )
	public ResponseEntity<String> uploadSingleFileExample4(@RequestBody MultipartFile file) {
		System.out.println("SONO IN UPLOAD");
		log.info("Request contains, File: " + file.getOriginalFilename());
		String fileId = fileManager.uploadFile(file, "PROVA");
		System.out.println("SONO QUA");

		if(fileId == null){
			System.out.println("ID NULLO");

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		System.out.println("ID NON NULLO");

		return ResponseEntity.ok("Success, FileId: "+ fileId);
	}


	@GetMapping("/delete/{id}")
	public void delete(@PathVariable String id) throws Exception {
		fileManager.deleteFile(id);
	}
}
