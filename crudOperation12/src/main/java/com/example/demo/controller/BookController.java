package com.example.demo.controller;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.Book;
import com.example.demo.model.JobDetails;
import com.example.demo.model.Login;
import com.example.demo.repo.JobDetailsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.io.DataOutputStream;

@RestController
@CrossOrigin(origins = {"*"})
public class BookController {
	
	@Autowired
	com.example.demo.repo.BookRepository bookRepository;
	@Autowired 
	private AuthenticationManager authenticationManager;
	@Autowired 
	private MyUserDetailsService userDetailsService;
     @Autowired
     private JwtUtil jwtTokenUtil;
     @Autowired
 	 com.example.demo.repo.LoginRepository loginRepository;
     @Autowired
  	 JobDetailsRepository jobDetailsRepository;
     
     AuthenticationRequest authenticationCredentials;
     String userTypeData;
     private final ObjectMapper objectMapper = new ObjectMapper();

	@PostMapping("/bookSave")
	public String insertBook(@RequestBody Book book) 
	{  System.out.println("chevcuhydvcue");
		bookRepository.save(book);
		return "Your record is saved successfully !!";
	}
		
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody Login login)
	{   
		System.out.println(login);
		String response;
		try {
			loginRepository.save(login);	
			response="User Registered Successfully";
		}
		catch(Exception e)
		{
			response="Duplicate Username, Please try with another username";

		}
		
		 return ResponseEntity.ok().body(response);
	}
	
	@PostMapping("/jobPost")
	public ResponseEntity<String> jobPost(@RequestBody JobDetails JobDetails)
	{   
		System.out.println(JobDetails);
		String response;
		try {
			jobDetailsRepository.save(JobDetails);	
			response="Job Posted Successfully";
		}
		catch(Exception e)
		{
			response=e.toString();

		}
		
		 return ResponseEntity.ok().body(response);
	}
	
	
	@GetMapping("updateHiredStatus/{id}")
	public ResponseEntity<String> updateHiredStatus(@PathVariable("id") String id)
	{   
		System.out.println("updateHiredStatus"+id);
		String response;
		try {
			jobDetailsRepository.updateHiredStatus(id);
			response="updated";
		}
		catch(Exception e)
		{
			response=e.toString();

		}
		
		 return ResponseEntity.ok().body(response);
	}
	
	@GetMapping("hey")
	public String hello()
	{   
		
		
		 return "yes";
	}
	
	@GetMapping("/jobDetails/{username}")
	public List<JobDetails>  jobDetails(@PathVariable("username") String username)
	{   
		System.out.println("COming in job Details"+username.substring(1,username.length()-1));
		 return jobDetailsRepository.findByUsername(username.substring(1,username.length()-1));
	}
	
	
	
	
	@PostMapping("/multipleBookSave")
	public String insertBook(@RequestBody List<Book> book) 
	{   
		bookRepository.saveAll(book);
		return "Record is saved successfully !!";
	}
	
	@GetMapping("/getAllBook")
	public List<Book> getBook()
	{   
		return (List<Book>) bookRepository.findAll();
	}
	
	
	@GetMapping("/getByBookName/{name}")
	public List<Book> getBookByName(@PathVariable("name") String bookName)
	{   
		return (List<Book>) bookRepository.findBybookName(bookName);
	
	}
	
	@GetMapping("/getByBookId/{bookId}")
	public Optional<Book> getBookById(@PathVariable("bookId") Long id)
	{   
		return bookRepository.findById(id);
	}
	
	@GetMapping("/getUserList")
	public List<Login> getUserList()
	{  
		System.out.println("getUserList called");
		return loginRepository.findAll();
	}
	
	
	

	
	
	
	@PutMapping("/update/{bookId}")
	public Book updateProduct(@RequestBody Book book,@PathVariable("bookId") Long id)
	{
		Book existingBook=bookRepository.findById(id
				).orElse(null);
		existingBook.setBookAuthor(book.getBookAuthor());
		existingBook.setBookName(book.getBookName());
		
		
		return bookRepository.save(existingBook);
	}

	



	@DeleteMapping("/delete/{bookId}")
	public String deleteBook(@PathVariable("bookId") Long id)
	{  
		
	 bookRepository.deleteById(id);
	 return "Deleted Successfully";

	}
	
	
	 private static HttpURLConnection con;
	@GetMapping("/getHttpRequest")
    public static String abc(String[] args) throws IOException {

        var url = "https://jsonplaceholder.typicode.com/todos/1";

        try {

            var myurl = new URL(url);
            con = (HttpURLConnection) myurl.openConnection();

            con.setRequestMethod("GET");

            StringBuilder content;

            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()))) {

                String line;
                content = new StringBuilder();

                while ((line = in.readLine()) != null) {

                    content.append(line);
                    content.append(System.lineSeparator());
                }
            }

            return content.toString()+con.getResponseCode();


        } finally {

            con.disconnect();
        }
    }
	
	@PostMapping("/acess_token")
	private String token(@RequestBody HashMap<String,String> code) {
		System.out.println("Coming here in acess_token"+code);
		String CodeValue,token_id;
		CodeValue=code.get("code");
		token_id=code.get("token_id");
		String uri="https://graph.facebook.com/v14.0/oauth/access_token?client_id="+token_id+"&client_secret=1ac9c3132beea719be175bf4047e4173&redirect_uri=https://theengineerss.com/facebookCode.php&code="+CodeValue;
	    System.out.println(uri);
		RestTemplate restTemplate =new RestTemplate();
	    String result=restTemplate.getForObject(uri, String.class);
	    return result;
	
	}

	@PostMapping("/postHttpRequest")
	public static String abc1(@RequestBody HashMap<String,String> tokenValue) throws IOException {
		
       String token,client_id,message;
		token=tokenValue.get("token");
		
		client_id=tokenValue.get("client_id");
		message=tokenValue.get("message");
      String data= tokenValue.get("message");
        String[] arrOfStr = data.split("--", 3);
        String newlineusinglinesep = System.lineSeparator();
        System.out.println(arrOfStr);
       
        String data1=arrOfStr[2]+ newlineusinglinesep + arrOfStr[1] + newlineusinglinesep + arrOfStr[0];
       var url="https://graph.facebook.com/"+client_id+"/feed?message="+arrOfStr[2].substring(0,arrOfStr[2].length()-2) + newlineusinglinesep + arrOfStr[1] + newlineusinglinesep + arrOfStr[0].substring(1,arrOfStr[0].length()-1) +"&access_token="+token;
       String url2 = url.replace("\n", "%0A");
       String url3=url2.replace(" ", "%20");
       String url4 = url3.replace("##", "%0A");
       var urlParameters = "";
        byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);

        try {

            var myurl = new URL(url4);
            con = (HttpURLConnection) myurl.openConnection();

            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", "Java client");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
             
            try (var wr = new DataOutputStream(con.getOutputStream())) {

                wr.write(postData);
            }
            System.out.println(postData.toString());

            StringBuilder content;

            try (var br = new BufferedReader(
                    new InputStreamReader(con.getInputStream()))) {

                String line;
                content = new StringBuilder();

                while ((line = br.readLine()) != null) {
                    content.append(line);
                    content.append(System.lineSeparator());
                }
            }
            
            return content.toString();

        } finally {

            con.disconnect();
        }
    }
	
	
	
	@PostMapping("/postLinkedIn")
	public static String LinkedIn(@RequestBody Object requestBody) throws IOException {
		
            var objectMapper = new ObjectMapper();
            String requestBodyy = objectMapper
                    .writeValueAsString(requestBody);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.linkedin.com/v2/ugcPosts"))
                    .POST(HttpRequest.BodyPublishers.ofString(requestBodyy))
                    .setHeader("Authorization", "Bearer AQV9mEf-HOe0nyRDqHm1Y3Pbz5Rcwp8gEvOr0wAkqgPjXoiuCRM9chbkR2G6FXkWSP1FiXs7U1pN_-uP3wLSB96VlhCT3AukYuHZjd-WZZvC7pHBVRftRGyB_Rwd18Y5TJNf1DEU69fOGcdBaR7XhBKi-os4sYDd-eo-hWvydwXcljylrOtqnbNSvoaSExkPVOIO5umDkVEkQrcCmUxMaQEUehDR0utsdq5BESABGGZ5GJPQNfqqNagvHswios4P_cm1nCdl2DU33_QdyNEvUgc_2QW7tFCRNdrvLmowTU3rFpFODuGEj5nYNVabPCvkV7j2bsVM5u-AX1piFyNzCVZRpLLKTQ")
                    .build();

            HttpResponse<String> response=null;
			try {
				response = client.send(request,
				        HttpResponse.BodyHandlers.ofString());
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
            System.out.println("here"+response.body());
 

String urlParameters=null;

		return urlParameters;
    }
	

	 @PostMapping(value="/authenticate")
		public ResponseEntity <?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest,@RequestParam String userType) throws Exception{
		

		 
		 authenticationCredentials=authenticationRequest;
		 userTypeData=userType;
			try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken("authenticate", authenticationRequest.getPassword())
					
					);
		}
			catch(BadCredentialsException e)
			{
				throw new Exception("Incorrect username or password",e);
			}

			final UserDetails userDetails =userDetailsService
		    .loadUserByUsername("authenticate");
			
		
			
			final String token =jwtTokenUtil.generateToken(userDetails);
			
			return ResponseEntity.ok(new AuthenticationResponse(token));
		}
		
	
	
	
}
