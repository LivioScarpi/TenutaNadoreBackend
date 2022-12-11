package com.technicalsand.googledrive.crud;

import com.google.api.services.drive.model.File;
import com.technicalsand.googledrive.crud.model.Review;
import com.technicalsand.googledrive.crud.model.ReviewUI;
import com.technicalsand.googledrive.crud.Repository.PostgresRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBufferLimitException;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin("http://localhost:3000")
public class MainController {
	private FileManager fileManager;

	@Autowired
	PostgresRepository repository;

	@RequestMapping("/get_all_reviews")
    public ResponseEntity<List<Review>> loadAll() {
        log.info("start loadAll users");
        try {
            List<Review> users = repository.findAll();
            log.info("Found {} users", users.size());
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (DataBufferLimitException e) {
            log.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

	@GetMapping("/save_review")
	public String save_review() {
		// save a single Customer
		repository.save(new Review("Livio", "Olio fantastico, mi piace molto", "ID1;ID2"));

		return "Review created";
	}

	// QUESTA FUNZIONA
	@PostMapping("/save_review_test")
	public ResponseEntity<String> save_review_test(@RequestBody Review review) {
		// save a single Customer
		System.out.println("SONO IN SAVE REVIEW TEST");
		System.out.println(review);
		repository.save(review);

		return ResponseEntity.ok("Ok");
	}

	// @PostMapping(value = "/save_review_test_images", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE }, produces = {
	// 		MediaType.APPLICATION_JSON_VALUE })
	// public ResponseEntity<String> save_review_test_images(@RequestBody MultipartFile formData) {
	// 	// save a single Customer
	// 	System.out.println("SONO IN SAVE REVIEW TEST IMAGES");
	// 	System.out.println(formData);

	// 	// System.out.println("SONO DOPO LA STAMPA DI REVIEW");
	// 	// log.info("Request contains, File: " + formData.getOriginalFilename());

	// 	// repository.save(review);

	// 	return ResponseEntity.ok("Ok");
	// }

	@GetMapping({ "/" })
	public ResponseEntity<List<File>> listEverything() throws IOException, GeneralSecurityException {
		System.out.println("SONO IN LIST EVERYTHING");
		log.info("LOG - SONO IN LIST EVERYTHING ");
		List<File> files = fileManager.listEverything();
		return ResponseEntity.ok(files);
	}

	@GetMapping({ "/list", "/list/{parentId}" })
	public ResponseEntity<List<File>> list(@PathVariable(required = false) String parentId)
			throws IOException, GeneralSecurityException {
		List<File> files = fileManager.listFolderContent(parentId);
		return ResponseEntity.ok(files);
	}

	@GetMapping("/download/{id}")
	public void download(@PathVariable String id, HttpServletResponse response)
			throws IOException, GeneralSecurityException {
		fileManager.downloadFile(id, response.getOutputStream());
	}

	@GetMapping("/directory/create")
	public ResponseEntity<String> createDirecory(@RequestParam String path) throws Exception {
		String parentId = fileManager.getFolderId(path);
		return ResponseEntity.ok("parentId: " + parentId);
	}

	@PostMapping(value = "/upload", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity uploadSingleFileExample4(@RequestBody MultipartFile file) throws java.io.IOException{
		System.out.println("SONO IN UPLOAD");
		System.out.println(file);
		// String content = new String(file.getBytes(), StandardCharsets.UTF_8);
		// System.out.println(content);

		System.out.println(file.getName());
		log.info("Request contains, File: " + file.getOriginalFilename());
		String fileId = fileManager.uploadFile(file, "PROVA");
		System.out.println("SONO QUA");

		if (fileId == null) {
			System.out.println("ID NULLO");

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		System.out.println("ID NON NULLO");

		return ResponseEntity.ok(fileId);
	}

	@GetMapping("/delete/{id}")
	public void delete(@PathVariable String id) throws Exception {
		fileManager.deleteFile(id);
	}

}
