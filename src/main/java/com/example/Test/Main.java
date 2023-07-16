package com.example.Test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
//import org.springframework.web.bind.annotation.*;


@SpringBootApplication
@RestController
@RequestMapping("/api/v1/customers/")
public class Main {

	private final CustomerRepository customerRepository;

	public Main(CustomerRepository customerRepository){
		this.customerRepository = customerRepository;
	}

	public static void main(String[] args) {
//		String course= "Spring Boot";
//		System.out.println("Hello Joseph, This is:" + course);
		SpringApplication.run(Main.class, args);
	}

	/**
	 * READ List of all customers
	 * @return
	 */
	@GetMapping
	public List<Customer> getCustomer(){
		return customerRepository.findAll();
	}

	record NewCustomerRequest(
			String name,
			String email,
			String phone,
			Integer age
	){}

	@PostMapping()
	public void addCustomer(@RequestBody NewCustomerRequest request){
		Customer customer = new Customer();
		customer.setName(request.name());
		customer.setEmail(request.email());
		customer.setPhone(request.phone());
		customer.setAge(request.age());

		customerRepository.save(customer);
	}

	@DeleteMapping("{customerId}")
	public void deleteCustomer(@PathVariable("customerId") Integer id){
		customerRepository.deleteById(id);
	}


	@PutMapping("{customerId}")
	public void updateCustomer(@PathVariable("customerId") Integer id, @RequestBody NewCustomerRequest request) {
		// Find the existing customer by ID
		Optional<Customer> optionalCustomer = customerRepository.findById(id);

		if (optionalCustomer.isPresent()) {
			// Update the customer information
			Customer customer = optionalCustomer.get();
			customer.setName(request.name());
			customer.setEmail(request.email());
			customer.setPhone(request.phone());
			customer.setAge(request.age());

			// Save the updated customer
			customerRepository.save(customer);
		} else {
			// Handle the case where no customer was found with the given ID
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
		}
	}


//	READ
//	@GetMapping("/")

//

//	@GetMapping("/greet")
//	public String greet(){
//		return "Hello";
//	}
//
//	@GetMapping("/")
//	public String test(@RequestParam(defaultValue = "Joseph Mbote") String name,
//					   @RequestParam(defaultValue = "Angular Developer") String course) {
//		return name + " - " + course;
//	}
//
//	@GetMapping("/greet2")
//	public GreetResponse greets(){
//		 GreetResponse result =new GreetResponse(
//				"Hello",
//				List.of("Java", "C", "Golang", "JavaScript"),
//				new Frameworks(List.of("Spring Boot","Angular", "React", "Django")),
//				new Person("Joseph Mbote", 25, 100000)
//				);
//
//		 return result;
//	}
//
//	record Frameworks(List<String> frameworks){}
//
//	record  Person(String name, int age, double salary){}
//	record GreetResponse(
//			String greet,
//			List<String> languages,
//			Frameworks frameworks,
//			Person person
//			){}




//	class GreetResponse{
//		private final String greet;
//		GreetResponse(String greet){
//			this.greet = greet;
//		}
//
//		public String getGreet() {
//			return greet;
//		}
//
//		@Override
//		public String toString() {
//			return "GreetResponse{" + "greet'" + greet + '\''+ '}';
//		}
//
//		@Override
//		public boolean equals(Object o) {
//			if (this == o) return true;
//			if (o == null || getClass() != o.getClass()) return false;
//			GreetResponse that = (GreetResponse) o;
//			return Objects.equals(greet, that.greet);
//		}
//
//		@Override
//		public int hashCode() {
//			return Objects.hash(greet);
//		}
//	}
//


}

//GET

/**
 * @RequestMapping - Default request method is GET
 * @RequestMapping(value="/products")
 * public ResponseEntity<Object> getProducts() { }
 */

/**
 * @RequestBody annotation is used to define the request body content type.
 *
 * public ResponseEntity<Object> createProduct(@RequestBody Product product) { }
 */

/**
 * @RequestMapping(method=RequestMethod.GET, value="/path")
 */


/**
 * @GetMapping(value="/path")
 *
 * An abbreviation form of @RequestMapping for HTTP GET requests - read
 */


// Create
/**
 * @PostMapping(value="/path")
 * An abbreviation form of @RequestMapping for HTTP POST requests - create
 */


//Update
/**
 * @PutMapping(value="/path")
 * An abbreviation form of @RequestMapping for HTTP PUT requests - update
 */

//Delete
/**
 * @DeletMapping(value="/path")
 * An abbreviation form of @RequestMapping for HTTP DELETE requests - delete
 */