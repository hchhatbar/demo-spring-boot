package demo;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}

@Component
class BookingCommandLineRunner implements CommandLineRunner {
	
	@Autowired
	BookingRepository bookingRepository;
	
	@Override
	public void run(String... arg0) throws Exception {
		for (Booking b: bookingRepository.findAll()) {
			System.out.println(b.toString());
		}		
	}
	
}

interface BookingRepository extends JpaRepository<Booking, Long> {
	Collection<Booking> findByBookingName(String bookingName);
}

@RestController
class BookingRestController {
	
	@Autowired
	BookingRepository bookingRepository;
	
	@RequestMapping("/bookings")
	Collection<Booking> bookings() {
		return bookingRepository.findAll();
	}
}

@Entity
class Booking {
	@Id @GeneratedValue
	private Long id;
	private String bookingName;
	
	public Booking() {				
	}

	public Booking(String bookingName) {
		super();
		this.bookingName = bookingName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBookingName() {
		return bookingName;
	}

	public void setBookingName(String bookingName) {
		this.bookingName = bookingName;
	}

	@Override
	public String toString() {
		return "Booking [id=" + id + ", bookingName=" + bookingName + "]";
	}	
}